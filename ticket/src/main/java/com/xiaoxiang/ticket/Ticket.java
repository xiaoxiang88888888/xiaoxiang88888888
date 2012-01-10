package com.xiaoxiang.ticket;

import com.xiaoxiang.ticket.email.SenderMail;
import com.xiaoxiang.ticket.util.ConstantUtil;
import com.xiaoxiang.ticket.util.HttpUtil;
import com.xiaoxiang.ticket.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.xpath.XPathAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 说明
 *
 * @author xiang.xiaox
 */

public class Ticket {
    private static final Logger logger = LoggerFactory.getLogger(Ticket.class);

    private ConstantUtil constant;
    private HttpUtil http;
    private SenderMail mailSender;

    private static String cookie = null;
    private static String queryUrlTemp = null;

    //站点信息映射
    public Map<String, String> stationNameMap = new HashMap<String, String>();
    //座位在官网提交数据对应的值
    public Map<String, String> seatNameValueMap = new LinkedHashMap<String, String>();
    //座位在官网搜索数据中所在的序号
    public Map<String, Integer> seatNameIndexMap = new LinkedHashMap<String, Integer>();


    public void init() {
        queryUrlTemp = constant.getTrainUrl();
        stationNameMap = constant.getStationNameMap();
        seatNameValueMap = constant.getSeatNameValueMap();
        seatNameIndexMap = constant.getSeatNameIndexMap();
    }

    public void task() throws Exception {
        waitSetSubmitCode();
        login();
        for (final String[] orderMessage : constant.getAutoOrderList()) {
            final Thread thread = new Thread() {
                public void run() {
                    String fromStationTelecode = stationNameMap.get(orderMessage[0]);//出发站点代码
                    String toStationTelecode = stationNameMap.get(orderMessage[1]);//到达站点代码
                    String queryUrl = queryUrlTemp.replace("${train_date}", constant.getTrainDate())
                            .replace("${from_station_telecode}", fromStationTelecode)
                            .replace("${to_station_telecode}", toStationTelecode);

                    String seatValue = seatNameValueMap.get(orderMessage[3]);//座位在官网提交数据中的值
                    int seatIndex = seatNameIndexMap.get(orderMessage[3]);//座位在官网搜索数据中所在的序号
                    next:
                    while (true) {
                        try {
                            String queryBody = "";
                            while (true) {
                                queryBody = http.doGetBody(queryUrl, cookie);
                                if (queryBody.contains("系统维护中")) {
                                    logger.info("--系统维护中, 一分钟再重新搜索--");
                                    Thread.sleep(60000);
                                    continue;
                                }

                                if (queryBody.contains("登录名：")) {
                                    login();
                                    continue;
                                }
                                if (!queryBody.contains("getSelected")) {
                                    continue next;
                                }
                                break;
                            }
                            queryBody= queryBody.replace("\\n", "@@@");
                            for(String train :StringUtils.split(queryBody, "@@@")){
                                String[] trainInfos = train.split(",");
                                if (!trainInfos[1].contains(orderMessage[2].toUpperCase())) {
                                    continue;
                                }
                                if (!trainInfos[seatIndex].contains("--") && !trainInfos[seatIndex].contains("无")) {
                                    String[] matchs = StringUtil.match(trainInfos[16], "getSelected\\('(.*?)'\\)");
                                    String[] orderInfo = matchs[1].split("#");

                                    Map<String, String> orderParam = new HashMap<String, String>();
                                    orderParam.putAll(constant.queryOrderParams);


                                    for (int i = 0; i < constant.queryOrderParamIndexs.length; i++) {
                                        orderParam.put(constant.queryOrderParamNames[i], orderInfo[constant.queryOrderParamIndexs[i]]);
                                    }
                                    String postOrder = StringUtil.toPostParam(orderParam);
                                    logger.info("--搜索到目标：" + orderMessage[0] + "到" + orderMessage[1] + (orderMessage.length > 2 ? orderMessage[2] : ""));
                                    int count = 100;
                                    while (count > 0) {
                                        logger.error("--第" + (101 - count) + "次发送预订,目标:" + orderMessage[0] + "到" + orderMessage[1] + (orderMessage.length > 2 ? orderMessage[2] : ""));
                                        String postOrderBody = http.doPostBody("https://dynamic.12306.cn/otsweb/order/querySingleAction.do?method=submutOrderRequest", postOrder, null, "UTF-8", true);
                                        if (postOrderBody.contains("var train_date_str_ = ")) {
                                            Document httpDoc = StringUtil.getDocument(postOrderBody);
                                            String lastThreadsXpath = "//FORM[@method='post']//INPUT";
                                            NodeList threads = XPathAPI.selectNodeList(httpDoc, lastThreadsXpath);

                                            Map<String, String> postOrderParam = new HashMap<String, String>();
                                            for (int i = 0; i < threads.getLength(); i++) {
                                                Node node = threads.item(i);
                                                String name = StringUtil.getNodeValue(node, "name");
                                                if (name != null && name.length() > 0) {
                                                    String value = StringUtil.getNodeValue(node, "value");
                                                    if (value == null)
                                                        value = "";
                                                    postOrderParam.put(name, value);
                                                }
                                            }
                                            postOrderParam.putAll(constant.submitOrderParams);
                                            //设置座位信息
                                            postOrderParam.put("passengerTickets", seatValue + ",1," + constant.getTrainName() + ",1," + constant.getTrainCardId() + "," + constant.getTrainMobile() + ",Y");
                                            postOrderParam.put("passenger_1_seat", seatValue);

                                            String train_date_str_ = StringUtil.match(postOrderBody, "var train_date_str_ = \"(.*?)\"")[1];
                                            String station_train_code_ = StringUtil.match(postOrderBody, "var station_train_code_ = \"(.*?)\"")[1];
                                            String start_time_str_ = StringUtil.match(postOrderBody, "var start_time_str_ = \"(.*?)\"")[1];
                                            String arrive_time_str_ = StringUtil.match(postOrderBody, "var arrive_time_str_ = \"(.*?)\"")[1];
                                            //								String lishi_ = StringUtil.match(postOrderBody, "var lishi_ = \"(.*?)\"")[1];

                                            postOrderParam.put("orderRequest.train_date", train_date_str_);
                                            postOrderParam.put("orderRequest.start_time", start_time_str_);
                                            postOrderParam.put("orderRequest.end_time", arrive_time_str_);
                                            postOrderParam.put("orderRequest.station_train_code", station_train_code_);
                                            postOrderParam.put("randCode", submitCode);
                                            String param = StringUtil.toPostParam(postOrderParam);
                                            String body = http.doPostBody("https://dynamic.12306.cn/otsweb/order/confirmPassengerAction.do?method=confirmPassengerInfoSingle", param, null, "UTF-8", true);
//
                                            if (body.contains("45分钟")) {
                                                Date now = new Date();
                                                mailSender.sendMail(constant.getNow() + "_订票成功",
                                                        constant.getNow() + " " +
                                                                orderMessage[0] + "到" + orderMessage[1] + (orderMessage.length > 2 ? orderMessage[2] : "") + " --订票时间："
                                                                + StringUtil.getDateTime(now, "HH:mm:ss")
                                                );
                                                constant.getAutoOrderList().remove(orderMessage);
                                                break next;
                                            }
                                            if (body.contains("没有足够的票")) {
                                                logger.error("--没有足够的票,重新搜索--!");
                                                count = 0;
                                            }
                                            if (body.contains("验证码不正确")) {
                                                waitSetSubmitCode();
                                            }
                                            if (body.contains("系统忙！")) {
                                                logger.error("--请求失败:系统忙!");
                                            }
                                            if (body.contains("当前提交订单用户过多！")) {
                                                logger.error("--当前提交订单用户过多!");
                                            }
                                            count--;
                                        }
                                    }
                                }
                            }
                        } catch (Exception e) {
                            logger.error("出现异常:", e);
                        }
                    }
                }
            };
            Date now = new Date();
            logger.info(StringUtil.getDateTime(now, "HH:mm:ss") + "--开始搜索：" + orderMessage[0] + "到" + orderMessage[1]);
            thread.start();
        }
    }

    private static boolean firstPostOrderCode = true;//第一次输入验证码时，不需要发邮件
    private static String submitCode = "reload";
    private static Object submitCodeLock = new Object[0];
    private static int submitCodeSerialNum = 0;
    private static ThreadLocal<Integer> waitSubmitCodeSerialNum = new ThreadLocal<Integer>() {
        protected synchronized Integer initialValue() {
            return 0;
        }
    };

    /**
     * 等待请求输入提交订单的验证码，只需要有一个线程来请求提交订单的验证码就可以
     *
     * @throws Exception
     */
    private void waitSetSubmitCode() throws Exception {
        synchronized (submitCodeLock) {
            if (submitCodeSerialNum == waitSubmitCodeSerialNum.get()) {
                submitCodeSerialNum++;
                waitSubmitCodeSerialNum.set(submitCodeSerialNum);
            } else {
                waitSubmitCodeSerialNum.set(submitCodeSerialNum);
                return;
            }
            if (!firstPostOrderCode)
                mailSender.sendMail("重新输入订单验证码", "请输入订单验证码");
            firstPostOrderCode = false;
            submitCode = "reload";
            while (submitCode.equals("reload")) {
                File file = http.doGetFile("https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=randp", cookie);
                File codeFile = new File(constant.getVerificationCodeOrderPicPath());
                if (!codeFile.exists())
                    codeFile.createNewFile();
                //TODO 暂时屏蔽测试
                FileUtils.copyFile(file, codeFile);
                submitCode = readString("请输入订单验证码");
                logger.info("--验证码：" + submitCode + "--");
            }
        }
    }

    private Object loginLock = new Object[0];
    private boolean firstLogin = true;//第一次登录输入验证码时，不需要发邮件
    private int loginCodeSerialNum = 0;
    private ThreadLocal<Integer> waitLoginCodeSerialNum = new ThreadLocal<Integer>() {
        protected synchronized Integer initialValue() {
            return 0;
        }
    };

    /**
     * 登录系统，在多线程中，只需要有一个线程来请求登录就可以
     *
     * @throws Exception
     */
    private void login() throws Exception {
        synchronized (loginLock) {
            if (loginCodeSerialNum == waitLoginCodeSerialNum.get()) {
                loginCodeSerialNum++;
                waitLoginCodeSerialNum.set(loginCodeSerialNum);
            } else {
                waitLoginCodeSerialNum.set(loginCodeSerialNum);
                return;
            }
            logger.info("--正在登录--");
            String params = "nameErrorFocus=&passwordErrorFocus=&randErrorFocus=";
            params += "&loginUser.user_name=" + constant.getTrainUsername();
            params += "&user.password=" + constant.getTrainPassword();

            String postParams = "";

            boolean hasSendMail = false;
            String body = "请输入正确的验证码";
            do {
                if (body.contains("请输入正确的验证码")) {
                    File file = http.doGetFile("https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=lrand", null);
                    File codeFile = new File(constant.getVerificationCodeLoginPicPath());
                    if (!codeFile.exists())
                        codeFile.createNewFile();
                    //TODO 暂时屏蔽测试
                    FileUtils.copyFile(file, codeFile);
                    if (!firstLogin && !hasSendMail) {
                        mailSender.sendMail("重新输入登录验证码", "请输入登录验证码");
                        hasSendMail = true;
                    }
                    String code = readString("请输入登录验证码");
                    postParams = params + "&randCode=" + code;
                    firstLogin = false;
                }
                logger.info("--发送登录请求--");
                body = http.doPostBody("https://dynamic.12306.cn/otsweb/loginAction.do?method=login", postParams, null, null, false);
                if (StringUtils.isEmpty(body)) {
                    logger.error("官方服务没有响应.");
                    continue;
                }
                if (body.contains("当前访问用户过多，请稍后重试")) {
                    logger.error("登录失败：当前访问用户过多！");
                }
            }
            while (body.contains("请输入正确的验证码") || body.contains("当前访问用户过多，请稍后重试") || !body.contains("您最后一次登录时间为"));

            body = http.doGetBody("https://dynamic.12306.cn/otsweb/order/querySingleAction.do?method=init", null);
            logger.info("--登录成功--");
        }
    }

    /**
     * 多控制台读取验证码
     *
     * @param msg
     * @return
     * @throws Exception
     */
    public String readString(String msg) throws Exception {
        //jdk1.5以后提供
        /* String value = null;
       while (StringUtil.isEmpty(value)) {
           Scanner scanner = new Scanner(System.in);
           System.out.print(msg+": ");
           value = scanner.nextLine();
       }
       return value;*/
        String value = null;
        System.out.print(msg + ": ");
        while (StringUtil.isEmpty(value)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            value = bufferedReader.readLine();
        }
        return value;
    }

    public void setConstant(ConstantUtil constant) {
        this.constant = constant;
    }

    public void setHttp(HttpUtil http) {
        this.http = http;
    }

    public void setMailSender(SenderMail mailSender) {
        this.mailSender = mailSender;
    }
}