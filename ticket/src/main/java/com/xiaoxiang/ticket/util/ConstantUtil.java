package com.xiaoxiang.ticket.util;

import java.util.*;

/**
 * 常量导入
 *
 * @author xiang.xiaox
 */
public class ConstantUtil {
    //属性文件获取
    private Properties seatProp;
    private Properties stationProp;
    private Properties configProp;
    //邮件发送信息
    private String mailServerHost;
    private String mailServerPort;
    private String mailValidate;
    private String mailUsername;
    private String mailPassword;
    private String mailFromAddress;
    private String mailToAddress;
    private String mailSubject;
    private String mailContent;
    //车票网站信息
    private String trainDate;
    private String trainUsername;
    private String trainPassword;
    private String trainName;
    private String trainCardId;
    private String trainMobile;
    private String trainUrl;
    private String trainStart;
    private String trainEnd;
    private String trainNumber;
    private String trainSeatType;
    //验证码相关信息
    private String verificationUrl;
    private String verificationCodeLoginPicPath; //登陆验证码
    private String verificationCodeOrderPicPath; //订单验证码
    //其它
    private String tempFilePath;
    //站点信息映射
    public Map<String, String> stationNameMap = new HashMap<String, String>();
    //座位在官网提交数据对应的值
    public Map<String, String> seatNameValueMap = new LinkedHashMap<String, String>();
    //座位在官网搜索数据中所在的序号
    public Map<String, Integer> seatNameIndexMap = new LinkedHashMap<String, Integer>();

    //需要自动订票的信息
    public List<String[]> autoOrderList = new ArrayList<String[]>();

    //请求订单参数
    public Map<String, String> queryOrderParams = new HashMap<String, String>();

    // 请求订单参数名称
    public String[] queryOrderParamNames = {"station_train_code", "lishi", "train_start_time", "trainno",
            "from_station_telecode", "to_station_telecode", "arrive_time", "from_station_name",
            "from_station_telecode_name", "to_station_name", "to_station_telecode_name", "ypInfoDetail"};

    //请求订单参数索引
    public int[] queryOrderParamIndexs = {0, 1, 2, 3, 4, 5, 6, 7, 7, 8, 8, 9};

    //提交订单参数
    public Map<String, String> submitOrderParams = new HashMap<String, String>();

    public String now;

    @SuppressWarnings("unchecked")
    public void init() {
        //
        now = StringUtil.getDateTime(new Date(), "yyyy-MM-dd");
        //
        for (Map.Entry<Object, Object> objectObjectEntry : stationProp.entrySet()) {
            Map.Entry entry = (Map.Entry) objectObjectEntry;
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            stationNameMap.put(key, value);
        }
        //
        for (Map.Entry<Object, Object> objectObjectEntry : seatProp.entrySet()) {
            Map.Entry entry = (Map.Entry) objectObjectEntry;
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            seatNameValueMap.put(key, value.split("_")[0]);
            seatNameIndexMap.put(key, Integer.parseInt(value.split("_")[1]));
        }
        //
        String[] trainNumbers = trainNumber.split(",");
        for (String trainNumber1 : trainNumbers) {
            String[] tempNum = new String[4];
            tempNum[0] = trainStart;
            tempNum[1] = trainEnd;
            tempNum[2] = trainNumber1;
            tempNum[3] = trainSeatType;
            autoOrderList.add(tempNum);
        }
        //
        queryOrderParams.put("round_start_time_str", "00:00--24:00");
        queryOrderParams.put("start_time_str", "00:00--24:00");
        queryOrderParams.put("round_train_date", now);
        queryOrderParams.put("train_date", trainDate);
        queryOrderParams.put("train_class_arr", "QB#D#Z#T#K#QT#");
        queryOrderParams.put("train_pass_type", "QB");
        queryOrderParams.put("single_round_type", "1");
        queryOrderParams.put("include_student", "00");
        //
        submitOrderParams.put("oldPassengers", trainName + ",1," + trainCardId);
        submitOrderParams.put("passenger_1_cardno", trainCardId);
        submitOrderParams.put("passenger_1_cardtype", "1");
        submitOrderParams.put("passenger_1_mobileno", trainMobile);
        submitOrderParams.put("passenger_1_name", trainName);
        submitOrderParams.put("passenger_1_ticket", "1");
        submitOrderParams.put("include_student", "00");
        submitOrderParams.put("orderRequest.reserve_flag", "A");
        submitOrderParams.put("passengerTickets", "3,1," + trainName + ",1," + trainCardId + "," + trainMobile + ",Y"); //3 是硬卧
        submitOrderParams.put("passenger_1_seat", "3");

    }

    public Properties getSeatProp() {
        return seatProp;
    }

    public void setSeatProp(Properties seatProp) {
        this.seatProp = seatProp;
    }

    public Properties getStationProp() {
        return stationProp;
    }

    public void setStationProp(Properties stationProp) {
        this.stationProp = stationProp;
    }

    public Properties getConfigProp() {
        return configProp;
    }

    public void setConfigProp(Properties configProp) {
        this.configProp = configProp;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailValidate(String mailValidate) {
        this.mailValidate = mailValidate;
    }

    public String getMailValidate() {
        return mailValidate;
    }

    public void setMailUsername(String mailUsername) {
        this.mailUsername = mailUsername;
    }

    public String getMailUsername() {
        return mailUsername;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailFromAddress(String mailFromAddress) {
        this.mailFromAddress = mailFromAddress;
    }

    public String getMailFromAddress() {
        return mailFromAddress;
    }

    public void setMailToAddress(String mailToAddress) {
        this.mailToAddress = mailToAddress;
    }

    public String getMailToAddress() {
        return mailToAddress;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailContent(String mailContent) {
        this.mailContent = mailContent;
    }

    public String getMailContent() {
        return mailContent;
    }

    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }

    public String getTrainDate() {
        return trainDate;
    }

    public void setTrainUsername(String trainUsername) {
        this.trainUsername = trainUsername;
    }

    public String getTrainUsername() {
        return trainUsername;
    }

    public void setTrainPassword(String trainPassword) {
        this.trainPassword = trainPassword;
    }

    public String getTrainPassword() {
        return trainPassword;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainCardId(String trainCardId) {
        this.trainCardId = trainCardId;
    }

    public String getTrainCardId() {
        return trainCardId;
    }

    public void setTrainMobile(String trainMobile) {
        this.trainMobile = trainMobile;
    }

    public String getTrainMobile() {
        return trainMobile;
    }

    public void setTrainUrl(String trainUrl) {
        this.trainUrl = trainUrl;
    }

    public String getTrainUrl() {
        return trainUrl;
    }

    public void setTrainStart(String trainStart) {
        this.trainStart = trainStart;
    }

    public String getTrainStart() {
        return trainStart;
    }

    public void setTrainEnd(String trainEnd) {
        this.trainEnd = trainEnd;
    }

    public String getTrainEnd() {
        return trainEnd;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainSeatType(String trainSeatType) {
        this.trainSeatType = trainSeatType;
    }

    public String getTrainSeatType() {
        return trainSeatType;
    }

    public void setVerificationUrl(String verificationUrl) {
        this.verificationUrl = verificationUrl;
    }

    public String getVerificationUrl() {
        return verificationUrl;
    }

    public void setVerificationCodeLoginPicPath(String verificationCodeLoginPicPath) {
        this.verificationCodeLoginPicPath = verificationCodeLoginPicPath;
    }

    public String getVerificationCodeLoginPicPath() {
        return verificationCodeLoginPicPath;
    }

    public void setVerificationCodeOrderPicPath(String verificationCodeOrderPicPath) {
        this.verificationCodeOrderPicPath = verificationCodeOrderPicPath;
    }

    public String getVerificationCodeOrderPicPath() {
        return verificationCodeOrderPicPath;
    }

    public void setTempFilePath(String tempFilePath) {
        this.tempFilePath = tempFilePath;
    }

    public String getTempFilePath() {
        return tempFilePath;
    }

    public Map<String, String> getStationNameMap() {
        return stationNameMap;
    }

    public Map<String, String> getSeatNameValueMap() {
        return seatNameValueMap;
    }

    public Map<String, Integer> getSeatNameIndexMap() {
        return seatNameIndexMap;
    }

    public List<String[]> getAutoOrderList() {
        return autoOrderList;
    }

    public Map<String, String> getQueryOrderParams() {
        return queryOrderParams;
    }

    public String[] getQueryOrderParamNames() {
        return queryOrderParamNames;
    }

    public int[] getQueryOrderParamIndexs() {
        return queryOrderParamIndexs;
    }

    public Map<String, String> getSubmitOrderParams() {
        return submitOrderParams;
    }

    public String getNow() {
        return now;
    }
}
