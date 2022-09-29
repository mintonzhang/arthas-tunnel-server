package com.alibaba.arthas.tunnel.server.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author minsin/mintonzhang@163.com
 * @since 2022/9/28
 */
@Controller
public class IndexController {

    @GetMapping
    public String index() {
        return "index.html";
    }
}
