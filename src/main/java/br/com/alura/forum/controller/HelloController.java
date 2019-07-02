package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//para criar um controller, utilizamos as anotações @Controller e @RequestMapping

@Controller
public class HelloController {
    @RequestMapping ("/")  //qual a url e quando o spring vai chamar o método
    @ResponseBody          //devolve a string e não uma página do projeto
    public String hello(){
        return "Hello World!";
    }
}
