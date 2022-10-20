package br.org.serratec.controller;


import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.ProdutoDTO;
import br.org.serratec.dto.ProdutoInserirDTO;
import br.org.serratec.model.Produto;
import br.org.serratec.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;
    
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @PostMapping
    public ResponseEntity<Object> inserir(@RequestParam MultipartFile file, @RequestPart ProdutoInserirDTO produtoInserirDTO) throws IOException{

        try {
            ProdutoDTO produtoDTO = produtoService.inserir(produtoInserirDTO, file);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoDTO.getIdProduto()).toUri();

            return ResponseEntity.created(uri).body(produtoDTO);
        } catch(Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }
    
    @PutMapping
    public ResponseEntity<Produto> atualizar (@PathVariable Long id, @RequestBody Produto produto){
        Produto produtoAtualizado = produtoService.update(produto, id);
        return ResponseEntity.ok(produtoAtualizado);
    }

}
