package com.xiaoxiang.area;

import com.xiaoxiang.area.service.AreaService;
import com.xiaoxiang.base.controller.AbstractController;
import com.xiaoxiang.model.Area;
import com.xiaoxiang.pagination.Pagination;
import com.xiaoxiang.result.JSONResult;
import com.xiaoxiang.util.PaginationUtil;
import com.xiaoxiang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 得到地区列表页面
     *
     * @return
     */
    @RequestMapping(value = "/index.htm", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/area/areaList");
        return modelAndView;
    }

    /**
     * 新增页面
     *
     * @return
     */
    @RequestMapping(value = "/add.htm", method = RequestMethod.GET)
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/area/areaAddOrUpdate");
        return modelAndView;
    }

    /**
     * 修改页面
     *
     * @return
     */
    @RequestMapping(value = "/update.htm", method = RequestMethod.GET)
    public ModelAndView update(@RequestParam String areaId) {
        ModelAndView modelAndView = new ModelAndView();
        Area area = areaService.findEntityBykey(areaId);
        modelAndView.setViewName("/area/areaAddOrUpdate");
        modelAndView.addObject("area",area);
        return modelAndView;
    }

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
     * 分页获取地区
     *
     * @return
     */
    @RequestMapping(value = "/list.htm", method = RequestMethod.POST)
    public ModelAndView listPagination(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/area/areaData");
        //分页处理
        Map<String, Object> params = new HashMap<String, Object>();
        Pagination p = PaginationUtil.parse(request);
        params.put("start", (p.getCurPage()-1)*p.getPageSize()+1);
        //mysql
        params.put("end", p.getPageSize());
        //oracle
        //params.put("end", p.getCurPage()*p.getPageSize());
        //排序
        String sort = request.getParameter("sort");//状态
        String order = request.getParameter("order");//创建 时间
        params.put("sort", sort);
        params.put("order", order);//
        //总数量
        int total = areaService.getAllEntityCount(params);
        p = PaginationUtil.parse(request, total);
        //分页记录集
        List<Area> areas = areaService.getAllEntity(params);
        modelAndView.addObject("pagination", p);
        modelAndView.addObject("datas",areas);
        return modelAndView;
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
            area.setAreaname(StringUtil.urlDecode(area.getAreaname()));
            area.setAreacode(StringUtil.urlDecode(area.getAreacode()));
            area.setParentAreaId(StringUtil.urlDecode(area.getParentAreaId()));
            area.setRemark(StringUtil.decode(area.getRemark()));
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
