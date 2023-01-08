package com.example.studydynamicschedulerv2.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }
}
