package ru.koleslena.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by elenko on 23.06.15.
 */
@Controller
public class IndexController {

    @RequestMapping(value="/")
    public String index() {
        return "index";
    }
}
