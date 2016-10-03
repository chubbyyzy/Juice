package com.tribeofspirit.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tribeofspirit.domain.model.HuangAlmanac;
import com.tribeofspirit.domain.service.HuangAlmanacService;
import com.tribeofspirit.domain.service.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Author : gonwang
 * Create time : 2015/12/5.
 */
//@Controller
//@RequestMapping("/huangAlmanac")
public class HuangAlmanacController {

    @Autowired
    private HuangAlmanacService huangAlmanacService;

    @RequestMapping(method = RequestMethod.GET)
    public String edit() {
        return "edit";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Result delete(@PathVariable Long toDeleteId) {
        return huangAlmanacService.delete(toDeleteId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Result<HuangAlmanac> save(@ModelAttribute HuangAlmanac huangAlmanac) throws JsonProcessingException {
        return huangAlmanacService.save(huangAlmanac);
    }

//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public Result<HuangAlmanac> list(@ModelAttribute HuangAlmanac huangAlmanac) throws JsonProcessingException {
//        return huangAlmanacService.save(huangAlmanac);
//    }
}
