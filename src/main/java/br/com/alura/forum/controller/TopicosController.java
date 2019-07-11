package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Dto data transfre object ou Vo value object
//Para utilizar o JPA no projeto, devemos incluir o módulo Spring Boot Data JPA, que utiliza o Hibernate, por padrão, como sua implementação
//Para configurar o banco de dados da aplicação, devemos adicionar as propriedades do datasource e do JPA no arquivo src/main/resources/application.properties
//Para que o Spring Boot popule automaticamente o banco de dados da aplicação, devemos criar o arquivo src/main/resources/data.sql

@RestController
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @RequestMapping("/topicos")
    public List<TopicoDto> lista(String nomeCurso){
        if(nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso); //"_" separa os relacionamentos
            return TopicoDto.converter(topicos);
        }

    }
}
