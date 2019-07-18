package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.DetalhesTopicoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizacaoTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.controller.repository.CursoRepository;
import br.com.alura.forum.model.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

//Dto data transfre object ou Vo value object
//Para utilizar o JPA no projeto, devemos incluir o módulo Spring Boot Data JPA, que utiliza o Hibernate, por padrão, como sua implementação
//Para configurar o banco de dados da aplicação, devemos adicionar as propriedades do datasource e do JPA no arquivo src/main/resources/application.properties
//Para que o Spring Boot popule automaticamente o banco de dados da aplicação, devemos criar o arquivo src/main/resources/data.sql

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    //    @RequestMapping(value="/topicos", method= RequestMethod.GET)
    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){
        if(nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else {
            List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso); //"_" separa os relacionamentos
            return TopicoDto.converter(topicos);
        }

    }

//    @RequestMapping(value="/topicos", method= RequestMethod.POST)
//    Indicar ao Spring que os parâmetros enviados no corpo da requisição devem ser atribuídos ao parâmetro do método
//    O Spring devolverá o código HTTP 200 (OK), caso a requisição seja processada com sucesso
//    public void cadastrar(@RequestBody TopicoForm form){

    //    para montar uma resposta a ser devolvida ao cliente da API, devemos utilizar a classe ResponseEntity do Spring
//    @Valid: Indicar ao Spring para executar as validações do Bean Validation no parâmetro do método
    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder){
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        //boa prática: quando o cadastro for realizado com sucesso deve retornar codigo HTTP 201
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping("/{id}") //path com partes dinâmicas utilizar as chaves
    public DetalhesTopicoDto detalhar(@PathVariable Long id){

        Topico topico=topicoRepository.getOne(id);
        return new DetalhesTopicoDto(topico);
    }

    @PutMapping("/{id}") //sobrescrever o recurso por inteiro diferente do path
    @Transactional //avisar pro Spring que precisar atualizar em base e Efetuar o commit automático da transação, caso não ocorra uma exception
    //não utilizar o mesmo form pois existe conteúdo que não deve ser atualizado
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){
       Topico topico =form.atualizar(id, topicoRepository);
       return ResponseEntity.ok(new TopicoDto(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remover (@PathVariable Long id){
        topicoRepository.deleteById(id);
        return ResponseEntity.ok().build();

    }
}
