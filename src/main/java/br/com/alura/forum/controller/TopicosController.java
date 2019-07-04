package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.model.Curso;
import br.com.alura.forum.model.Topico;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//Dto data transfre object ou Vo value object

@RestController
public class TopicosController {

    @RequestMapping("/topicos")
    //@ResponseBody para não repetir essa notação em todos os métodos dos Controllers basta utilizar o @RestController
    //para um método no controller não encaminhar a requisição a uma página JSP, ou Thymeleaf @ResponseBody
    public List<TopicoDto> lista(){
        Topico topico = new Topico("Dúvida", "Dúvida com Spring", new Curso("Spring","Programação"));

        //return Arrays.asList(topico, topico, topico);
        //criado método para encapsular e converter topico para TopicoDto
        return TopicoDto.converter(Arrays.asList(topico, topico, topico));
    }
}
