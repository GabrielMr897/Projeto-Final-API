package br.org.serratec.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.model.Categoria;
import br.org.serratec.repository.CategoriaRepository;
import br.org.serratec.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    @ApiOperation(value = "Lista todas as categorias", notes = "Coloque a url correta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a lista de todas as categorias"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<Categoria> listar() {
        return categoriaService.listar();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar uma categoria", notes = "Preencha com o ID da categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a categoria pelo ID"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.buscar(id);

        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ApiOperation(value = "Criar uma nova categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> inserir(@Valid @RequestBody Categoria categoria) {
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(categoriaService.inserir(categoria));
        }
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Alterar uma categoria", notes = "Preencha com o ID da categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Categoria alterada"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {

        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Categoria categoriaAtualizada = categoriaService.update(categoria, id);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Excluir um cliente", notes = "Preencha com o ID da categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria excluída"),
            @ApiResponse(responseCode = "204", description = "Categoria não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Boolean response = categoriaService.delete(id);
        if (response == true) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.notFound().build();

    }

}
