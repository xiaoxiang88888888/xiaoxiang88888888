package com.xiaoxiang.area;

import com.xiaoxiang.base.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 说明
 *
 * @author xiang.xiaox
 */
@Controller
@RequestMapping(value = "/area")
public class AreaControllerTwo extends AbstractController {

    @RequestMapping(value = "/area/compare", method = RequestMethod.POST)
    public ModelAndView compare(@RequestParam("input1") String input1) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("output", input1);
        modelAndView.setViewName("area/compareResult.vm");
        return modelAndView;
    }


}
