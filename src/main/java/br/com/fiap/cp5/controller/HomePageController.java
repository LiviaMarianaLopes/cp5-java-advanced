package br.com.fiap.cp5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/playsphere")
public class HomePageController {

    @GetMapping("/home")
    public String homePage(){
        return "homePage";
    }
}
