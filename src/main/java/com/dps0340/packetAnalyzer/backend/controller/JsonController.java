package com.dps0340.packetAnalyzer.backend.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

public class JsonController {
    @RequestMapping(value = "/getdata", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public String getData(@RequestBody Map<String, String> body) {
        
    }
}
