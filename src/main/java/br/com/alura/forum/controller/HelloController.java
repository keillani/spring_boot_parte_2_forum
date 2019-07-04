package br.com.alura.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//o Spring, por padrão, converte os dados no formato JSON, utilizando a biblioteca Jackson
//para criar um controller, utilizamos as anotações @Controller e @RequestMapping

@Controller
public class HelloController {
    @RequestMapping ("/")  //qual a url e quando o spring vai chamar o método
    @ResponseBody          //Indicar ao Spring que o retorno do método deve ser devolvido como resposta
    public String hello(){
        return "Hello World!";
    }
}
