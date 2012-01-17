package com.xiaoxiang.area;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 说明
 *
 * @author xiang.xiaox
 */
@Controller
public class AreaControllerTwo {


    @RequestMapping(value = "/area/areaList")
    public String home() {
        System.out.println("AreaController: Passing through...");
        return "area/areaList";
    }

    @RequestMapping(value = "/area/compare", method = RequestMethod.GET)
    public String compare(@RequestParam("input1") String input1,
                          @RequestParam("input2") String input2, Model model) {

        return "compareResult";
    }


}
