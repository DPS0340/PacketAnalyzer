package com.dps0340.packetAnalyzer.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testAPI {
    @RequestMapping(value = "/testapi", method = RequestMethod.GET)
    @ResponseBody
    public String getData() {
        return "Hello world!";
    }
}
