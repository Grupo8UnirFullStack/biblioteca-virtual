package com.unir.library.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BooksController {
    @GetMapping("/")
    public String index(){
        log.info("Estoy ejecutando el controlador rest");
        return "Mi primer controlador";
    }
}
