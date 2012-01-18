package com.xiaoxiang.area;

import com.xiaoxiang.area.service.AreaService;
import com.xiaoxiang.base.controller.AbstractController;
import com.xiaoxiang.model.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring MVC 3.x是完全支持Restful的，把URI做好规划。
 * 建议的URI规划如下：{Domain}[/{SubDomain}]/{BusinessAction}/{ID}。比如：
 * www.xiaoxiang.com/area/add/{id} ——表示此URI匹配www.xiaoxiang.com域的area子域，将要进行的是增加相应的id的记录
 *
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

    @RequestMapping(value = "/hello.htm")
    public ModelAndView helloJsp() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/area/areaList.vm");
        modelAndView.addObject("message", "Hello,JSP,中文测试");
        return modelAndView;
    }


    @RequestMapping(value = "/vm",method = RequestMethod.GET)
    public ModelAndView helloVm() {
        System.out.println(areaService+"=============");
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setView(new VelocityView());
        modelAndView.setViewName("/area/areaList.vm");
        modelAndView.addObject("message", "Hello,Velocity,中文测试");
        return modelAndView;
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public ModelAndView home() {
        System.out.println(areaService+"=============");
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setView(new VelocityView());
        modelAndView.setViewName("/area/areaList.vm");
        modelAndView.addObject("message", "Hello,Velocity,中文测试  ,home");
        return modelAndView;
    }

   /* @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public ModelAndView myMethod(@PathVariable("user") String user, ModelMap modelMap) throws Exception {
        modelMap.put("loginUser", user);
        return new ModelAndView("/area/areaList.vm", modelMap);
    }*/
    
    @RequestMapping(value = "/sdf", method = RequestMethod.GET)
    public ModelAndView sdf(ModelMap modelMap) throws Exception {
        modelMap.put("loginUser", "sdf76767");
        return new ModelAndView("/area/areaList.vm", modelMap);
    }


    @RequestMapping(value = "/compare", method = RequestMethod.POST)
    public ModelAndView compare(@RequestParam("input1") String input1) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("output", input1);
        modelAndView.setViewName("area/compareResult.vm");
        return modelAndView;
    }


    @ResponseBody
    @RequestMapping(value = "/ajax5.json",produces="application/json")
    public Object ajax(){
        List<Area> areas= areaService.getAllEntity();
        System.out.println(areas);
        return areas;
    }



}
