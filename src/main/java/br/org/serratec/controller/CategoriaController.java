package br.org.serratec.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Categoria> listar() {
        return categoriaService.listar();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.buscar(id);

        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Categoria> inserir(@Valid @RequestBody Categoria categoria) {
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(categoriaService.inserir(categoria));
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {

        if (!categoriaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Categoria categoriaAtualizada = categoriaService.update(categoria, id);
        return ResponseEntity.ok(categoriaAtualizada);
    }
}
