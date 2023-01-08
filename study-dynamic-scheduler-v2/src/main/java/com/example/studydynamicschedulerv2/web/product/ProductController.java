package com.example.studydynamicschedulerv2.web.product;

import com.example.studydynamicschedulerv2.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("product")
public class ProductController extends BaseController {

    @RequestMapping(value = {"", "/"})
    public String index() {
        return "redirect:/product/list";
    }

    @RequestMapping("list")
    public String list() {
        return "product/list";
    }

    @RequestMapping("edit/{productId}")
    public String edit(@PathVariable String productId) {
        return "product/edit";
    }

    @RequestMapping("register")
    public String register() {
        return "product/register";
    }

}
