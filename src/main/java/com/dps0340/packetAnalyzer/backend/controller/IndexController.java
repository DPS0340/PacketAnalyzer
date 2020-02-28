package com.dps0340.packetAnalyzer.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String Index() {
        return "index.html";
    }

}
