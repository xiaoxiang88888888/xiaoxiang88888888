package com.xiaoxiang.area;

import com.xiaoxiang.area.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
        modelAndView.setViewName("template/area/area_list.jsp");
        modelAndView.addObject("message", "Hello,JSP");
        return modelAndView;
    }


    @RequestMapping(value = "/velocity")
    public ModelAndView helloVm() {
        System.out.println(areaService+"=============");
        ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setView(new VelocityView());
        modelAndView.setViewName("/area/areaList.vm");
        modelAndView.addObject("message", "Hello,Velocity");
        return modelAndView;
    }

}
