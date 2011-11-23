package com.xiaoxiang.base.action;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.xiaoxiang.base.service.BaseService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

/**
 * Struts2中CRUD典型Action规范类.
 * 规定使用Preparable,ModelDriven接口,规范了一些函数的命名.
 *
 * @author xiaoxiang
 */
public abstract class BaseAction<T, FK extends Serializable> extends SimpleActionSupport implements ModelDriven<T>, Preparable, ServletRequestAware, ServletResponseAware {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected Collection<T> entities; //实体列表
    protected BaseService<T, FK> service;//  获得基本服务
    protected ActionContext context = ActionContext.getContext(); // 可以取得上下文
    protected static final String RELOAD = "reload";  //进行CUD操作后,以redirect方式重新打开action默认页的result名.
    protected HttpServletRequest request;
    protected HttpServletResponse response;


    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Collection<T> getEntities() {
        return entities;
    }

    public void setEntities(Collection<T> entities) {
        this.entities = entities;
    }

    public BaseService<T, FK> getService() {
        return service;
    }

    public void setService(BaseService<T, FK> service) {
        this.service = service;
    }

    /**
     * Action函数,默认action函数，默认指向showJmesaList函数.
     */
    @Override
    public String execute() throws Exception {
        return showJmesaList();
    }

    /**
     * Action函数,显示Entity列表.
     *
     * @return SUCCESS.
     */
    public String showList() throws Exception {
        return null;
    }

    /**
     * 使用Jmesa进行列表显示
     *
     * @return
     * @throws Exception
     */
    public String showJmesaList() throws Exception {
        return null;
    }

    /**
     * Action函数,在增加之前的准备工作.
     * return SHOWSAVE.
     *
     * @return
     */
    public String showSave() throws Exception {
        return null;
    }

    /**
     * Action函数,在修改之前的准备工作
     * return SHOWUPDATE.
     *
     * @return
     */
    public String showUpdate() throws Exception {
        return null;
    }

    /**
     * Action函数,新增Entity.
     * return SAVE.
     *
     * @return
     */
    public String save() throws Exception {
        return null;
    }

    /**
     * Action函数,修改Entity.
     * return UPDATE.
     *
     * @return
     */
    public String update() throws Exception {
        return null;
    }

    /**
     * Action函数,删除Entity.
     *
     * @return delete.
     */
    public String delete() throws Exception {
        return null;
    }

    /**
     * Action函数,删除Entity.
     *
     * @return batchDelete.
     */
    public String batchDelete() throws Exception {
        return null;
    }

    /**
     * 在save()前执行二次绑定.
     *
     * @throws Exception
     */
    public void prepareShowSave() throws Exception {
        prepareModel();
    }

    /**
     * 在update()前执行二次绑定.
     *
     * @throws Exception
     */
    public void prepareShowUpdate() throws Exception {
        prepareModel();
    }

    /**
     * 在delete()前执行二次绑定.
     *
     * @throws Exception
     */
    public void prepareDelete() throws Exception {
        prepareModel();
    }

    /**
     * 在input()前执行二次绑定.
     *
     * @throws Exception
     */
    public void prepareInput() throws Exception {
        prepareModel();
    }

    /**
     * 屏蔽公共的二次绑定.
     */
    public void prepare() throws Exception {
    }

    /**
     * 等同于prepare()的内部函数.
     *
     * @throws Exception
     */
    protected void prepareModel() throws Exception {

    }

    /**
     * @return the model to be pushed onto the ValueStack instead of the Action itself
     */
    public T getModel() {
        return null;
    }

    /**
     * 当前线程
     */
    protected static ThreadLocal thread = new ThreadLocal();

    /**
     * 获得当前线程定义对象
     *
     * @param key 健值
     * @return 对象
     */
    public static Object getThreadVariable(String key) {
        HashMap map = (HashMap) thread.get();
        if (map != null)
            return map.get(key);
        else
            return null;
    }

    /**
     * 设置当前线程定义对象
     *
     * @param key   健值
     * @param value 对象
     */
    public static void setThreadVariable(Object key, Object value) {
        HashMap map = (HashMap) thread.get();
        if (map != null) {
            map.put(key, value);
        } else {
            map = new HashMap();
            thread.set(map);
        }
    }

    /**
     * 需要登录时调用一次，以便将当前用户信息放入map中
     */
    public void setThreadVariableMap() {
        //nothing
    }

}