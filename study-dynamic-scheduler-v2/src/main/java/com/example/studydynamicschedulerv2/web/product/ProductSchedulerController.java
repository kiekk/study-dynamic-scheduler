package com.example.studydynamicschedulerv2.web.product;

import com.example.studydynamicschedulerv2.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product-scheduler")
public class ProductSchedulerController extends BaseController {

    @RequestMapping(value = {"", "/"})
    public String index() {
        return "redirect:/product-scheduler/list";
    }

    @RequestMapping("list")
    public String list() {
        return "product/scheduler/list";
    }

    @RequestMapping("edit/{productSchedulerId}")
    public String edit(@PathVariable String productSchedulerId) {
        return "product/scheduler/edit";
    }

    @RequestMapping("register")
    public String register() {
        return "product/scheduler/register";
    }

}
