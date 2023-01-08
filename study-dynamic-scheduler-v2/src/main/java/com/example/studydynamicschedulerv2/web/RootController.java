package com.example.studydynamicschedulerv2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class RootController {

    @RequestMapping(value = {"", "/"})
    public String root() {
        return "redirect:/product";
    }

}