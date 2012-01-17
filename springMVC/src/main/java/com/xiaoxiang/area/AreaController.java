package com.xiaoxiang.area;

import com.xiaoxiang.area.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 说明
 *
 * @author xiang.xiaox
 */
@Controller
public class AreaController {
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/jsp")
    public ModelAndView helloJsp() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/area/areaList.vm");
        modelAndView.addObject("message", "Hello,JSP,中文测试");
        return modelAndView;
    }


    @RequestMapping(value = "/velocity")
    public ModelAndView helloVm() {
        System.out.println(areaService+"=============");
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setView(new VelocityView());
        modelAndView.setViewName("/area/areaList.vm");
        modelAndView.addObject("message", "Hello,Velocity,中文测试");
        return modelAndView;
    }

    @RequestMapping(value = "/login/{user}", method = RequestMethod.GET)
    public ModelAndView myMethod(@PathVariable("user") String user, ModelMap modelMap) throws Exception {
        modelMap.put("loginUser", user);
        return new ModelAndView("/area/areaList.vm", modelMap);
    }

}
