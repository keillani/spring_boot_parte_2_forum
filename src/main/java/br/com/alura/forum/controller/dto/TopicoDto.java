package br.com.alura.forum.controller.dto;

//Dto somente tipos primitivos: int, Long, String, data
//Utilizando uma classe Dto existe a flexibilidade de escolher quais campos eu quero devolver

import br.com.alura.forum.model.Topico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    //criado construtor para retornar nos getters
    public TopicoDto (Topico topico){
        this.id = topico.getId();
        this.titulo =topico.getTitulo();
        this.mensagem=topico.getMensagem();
        this.dataCriacao=topico.getDataCriacao();
    }

    //java 7 -pegar a lista de topicos, fazer um for para cada topico, dar new no TopicoDto, guardar em uma lista de TopicoDto e devolver essa lista de TopicoDto
    //java 8: segue abaixo:
    public static List<TopicoDto> converter(List<Topico> topicos) {
        return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
