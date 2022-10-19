package br.org.serratec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.model.Categoria;
import br.org.serratec.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.listar();
    }

    @PostMapping
    public ResponseEntity<Categoria> inserir(@Valid @RequestBody Categoria categoria) {
        if(categoria == null) {
            return ResponseEntity.notFound().build();
        } else {
            categoriaService.inserir(categoria);
            return ResponseEntity.ok(categoria);
        }
    }
}
