package com.base;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IMGXD on 2017/8/10.
 */
public class BaseController {
    @ModelAttribute
    protected void init(HttpServletRequest request, Model model) {
        String ctx = request.getContextPath();
        model.addAttribute("ctx", ctx);
    }
}
