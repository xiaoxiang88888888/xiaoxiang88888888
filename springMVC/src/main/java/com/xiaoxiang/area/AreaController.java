package com.xiaoxiang.area;

import com.xiaoxiang.area.service.AreaService;
import com.xiaoxiang.base.controller.AbstractController;
import com.xiaoxiang.model.Area;
import com.xiaoxiang.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Spring MVC 3.x是完全支持Restful的，把URI做好规划。
 * 建议的URI规划如下：{Domain}[/{SubDomain}]/{BusinessAction}/{ID}。比如：
 * www.xiaoxiang.com/area/add/{id} ——表示此URI匹配www.xiaoxiang.com域的area子域，将要进行的是增加相应的id的记录
 * <p/>
 * 由于Spring的DefaultAnnotationHandlerMapping.java在做Mapping的时候，先做是否有类匹配，再找方法，
 * 把基本的mapping放在类上面，可以加速匹配效率
 *
 * @author xiang.xiaox
 */

@Controller
@RequestMapping(value = "/area")
public class AreaController extends AbstractController {
    @Autowired
    private AreaService areaService;

    /**
     * 查询相应的地区
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult get(@PathVariable String id) {
        JSONResult result = new JSONResult();
        try {
            Area area = areaService.findEntityBykey(id);
            result.setResult(true);
            result.setData(area);
        } catch (Exception e) {
            result.setResult(false);
            result.setErrorDesc("出现异常:" + e.getMessage());
        }
        return result;
    }


    /**
     * 查询所有地区
     *
     * @return
     */
    @RequestMapping(value = "/list.json", method = RequestMethod.GET)
    @ResponseBody
    public JSONResult list() {
        JSONResult result = new JSONResult();
        try {
            List<Area> areas = areaService.getAllEntity();
            result.setResult(true);
            result.setData(areas);
        } catch (Exception e) {
            result.setResult(false);
            result.setErrorDesc("出现异常:" + e.getMessage());
        }
        return result;
    }


    /**
     * 添加或修改 地区
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}.json", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addOrUpdate(@PathVariable String id, Area area) {
        JSONResult result = new JSONResult();
        try {
            if (areaService.exists(id)) { //数据库中已经存在
                areaService.updateEntity(area);
            } else {
                areaService.addEntity(area);
            }
            result.setResult(true);
            result.setData(area);
        } catch (Exception e) {
            result.setResult(false);
            result.setErrorDesc("出现异常:" + e.getMessage());
        }
        return result;
    }

    /**
     * 删除 地区
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}.json", method = RequestMethod.DELETE)
    @ResponseBody
    public JSONResult delete(@PathVariable String id) {
        JSONResult result = new JSONResult();
        try {
            areaService.deleteEntityByKey(id);
            result.setResult(true);
            result.setData(null);
        } catch (Exception e) {
            result.setResult(false);
            result.setErrorDesc("出现异常:" + e.getMessage());
        }
        return result;
    }


}
