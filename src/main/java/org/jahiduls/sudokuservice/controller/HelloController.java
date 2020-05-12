package org.jahiduls.sudokuservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {

    @GetMapping("/")
    public String index() {
        return "Hello Spring from Docker!";
    }

}
