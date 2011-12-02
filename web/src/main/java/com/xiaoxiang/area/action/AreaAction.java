package com.xiaoxiang.area.action;

import com.xiaoxiang.base.action.BaseAction;
import com.xiaoxiang.model.Area;
import com.xiaoxiang.area.service.AreaService;
import com.xiaoxiang.util.SpringContextUtil;
import com.xiaoxiang.util.StringUtil;

/**
 * Area 的action
 * 地区
 *
 * @author xiaoxiang
 */
public class AreaAction extends BaseAction<Area, String> {

    private AreaService areaService = (AreaService) SpringContextUtil.getBean("areaService");

    protected Area entity;

    protected String id;  //实体ID

    protected String are_areaid;//地区编号
    protected String are_areaid_name;//地区编号的显示内容                     

    public void setAre_areaid(String are_areaid) {
        this.are_areaid = are_areaid;
    }

    public void setAre_areaid_name(String are_areaid_name) {
        this.are_areaid_name = are_areaid_name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Area getModel() {
        //return null;
        return entity;
    }

    /**
     * 等同于prepare()的内部函数.
     */
    protected void prepareModel() throws Exception {
        String id = request.getParameter("id");
        if (id != null) {
            entity = service.findEntityBykey(id);
        } else {
            entity = new Area();
        }
    }


    /**
     * Action函数,显示Entity列表.
     * return SUCCESS.
     */
    public String showList() throws Exception {
        
    	entities = getService().getAllEntity();
    	for(int i=0;i<10;i++){
    		getService().exists(""+i);
    	}  
    	/*for(int i=1020;i<1030;i++){
    		Area entity  = new Area();
    		entity.setId(""+i);
    		entity.setAreaId(""+i);
    		entity.setAreacode(""+i);
            entity.setAreaname("地区"+i);
            entity.setOrderno(i);
            entity.setParentAreaId("1");
            entity.setRemark("标记"+i);
    		getService().addEntity(entity);
    	} */
        return SUCCESS;
    }

    /**
     * 得到分页数据
     *
     * @return
     * @throws Exception
     */
    public String showDataList() throws Exception {
        return SUCCESS;
    }

    /**
     * Action函数,显示排序页.
     * return SUCCESS.
     */
    public String showOrder() throws Exception {
        return SUCCESS;
    }

    /**
     * Action函数,显示查看页面.
     * return SUCCESS.
     */
    public String showView() throws Exception {
        return SUCCESS;
    }

    /**
     * Action函数,在增加之前的准备工作.
     * return SHOWSAVE.
     *
     * @return
     */
    public String showSave() throws Exception {
        request.setAttribute("operate", "save");
        request.setAttribute("areaId", StringUtil.getUUID());
        return SUCCESS;
    }

    /**
     * Action函数,在修改之前的准备工作
     * return SHOWUPDATE.
     *
     * @return
     */
    public String showUpdate() throws Exception {

        return SUCCESS;
    }

    /**
     * Action函数,新增Entity.
     * return SAVE.
     *
     * @return
     */
    public String save() throws Exception {

        return SUCCESS;
    }

    /**
     * Action函数,修改Entity.
     * return UPDATE.
     *
     * @return
     */
    public String update() throws Exception {

        return SUCCESS;
    }

    /**
     * Action函数,删除Entity.
     * return RELOAD.
     */
    public String delete() throws Exception {
        getService().deleteEntity(entity);
        // getService().deleteEntityByKey(entity.getAreaid());
        return SUCCESS;
    }

    /**
     * Action函数,删除Entity.
     *
     * @return batchDelete.
     */
    public String batchDelete() throws Exception {
        String[] ids = request.getParameter("ids").split(",");
        getService().deleteBatchEntityByKeys(ids);
        return SUCCESS;
    }


}

