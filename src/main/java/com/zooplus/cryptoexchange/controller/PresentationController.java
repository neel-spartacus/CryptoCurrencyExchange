package com.zooplus.cryptoexchange.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
public class PresentationController {
    
    @RequestMapping(value="/presentation", method=GET)
    public String presentation() {
        return "presentation/index"; 
    }
}
