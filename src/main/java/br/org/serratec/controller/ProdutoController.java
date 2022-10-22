package br.org.serratec.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    @GetMapping("{id}")
    public ResponseEntity<ProdutoDTO> buscar(@PathVariable Long id) {
        ProdutoDTO produtoDto = produtoService.buscar(id);

        if (produtoDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoDto);
    }

    @GetMapping("/{id}/foto")
    public ResponseEntity<byte[]> buscarPorFoto(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorFoto(id);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
        httpHeaders.add("content-type", "image/png");
        httpHeaders.add("content-length", String.valueOf(produto.getImagem().length));
        return new ResponseEntity<>(produto.getImagem(), httpHeaders, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> inserir(@RequestParam MultipartFile file,
            @RequestPart ProdutoInserirDTO produtoInserirDTO) throws IOException {

        try {
            ProdutoDTO produtoDTO = produtoService.inserir(produtoInserirDTO, file);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(produtoDTO.getIdProduto()).toUri();

            return ResponseEntity.created(uri).body(produtoDTO);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.update(produto, id);
        return ResponseEntity.ok(produtoAtualizado);
    }

}
