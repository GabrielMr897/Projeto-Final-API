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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.ProdutoDTO;
import br.org.serratec.dto.ProdutoInserirDTO;
import br.org.serratec.model.Produto;
import br.org.serratec.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @ApiOperation(value = "Listar todos produtos")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="200", description = "Retorna todos os produtos"),
    		@ApiResponse(responseCode="404", description = "Recurso não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProdutoDTO>> listar() {
        return ResponseEntity.ok(produtoService.listar());
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar um produto", notes = "Preencha com o ID do produto")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="200", description = "Retorna o produto"),
    		@ApiResponse(responseCode="404", description = "Produto não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProdutoDTO> buscar(@PathVariable Long id) {
        ProdutoDTO produtoDto = produtoService.buscar(id);

        if (produtoDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoDto);
    }

    @GetMapping("/{id}/foto")
    @ApiOperation(value = "Buscar um produto por foto", notes = "Preencha com o ID produto")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="200", description = "Retorna o produto"),
    		@ApiResponse(responseCode="404", description = "Produto não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
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
    @ApiOperation(value = "Registrar um produto")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="201", description = "Produto registrado"),
    		@ApiResponse(responseCode="404", description = "Recurso não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.CREATED)
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

    @PutMapping("{id}")
    @ApiOperation(value = "Alterar um produto", notes = "Preencha com o ID do produto")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="201", description = "Novo produto registrado"),
    		@ApiResponse(responseCode="202", description = "Produto alterado"),
    		@ApiResponse(responseCode="404", description = "Recurso não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long id, @RequestPart ProdutoInserirDTO produtoInserirDTO,
            @RequestParam MultipartFile file) throws IOException {
        ProdutoDTO produtoAtualizado = produtoService.update(produtoInserirDTO, id, file);
        return ResponseEntity.ok(produtoAtualizado);
    }

}
