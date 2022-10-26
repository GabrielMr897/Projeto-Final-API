package br.org.serratec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.dto.PedidoDTO;
import br.org.serratec.dto.PedidoInserirDTO;
import br.org.serratec.repository.PedidoRepository;
import br.org.serratec.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    @ApiOperation(value = "Listar todos os pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna todos os pedidos"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<PedidoDTO> listar() {
        return pedidoService.listar();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar um pedido", notes = "Preencha com o ID do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o pedido baseado no id"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PedidoDTO> buscar(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.buscar(id);

        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ApiOperation(value = "Registrar um pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido registrado"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PedidoDTO> inserir(@Valid @RequestBody PedidoInserirDTO pedido) {
        if (null != pedido) {
            PedidoDTO pedidos = pedidoService.inserir(pedido);
            return ResponseEntity.ok(pedidos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Alterar um pedido", notes = "Preencha com o ID do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Pedido alterado"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<PedidoDTO> atualizar(@PathVariable Long id, @RequestBody PedidoInserirDTO pedido) {
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        PedidoDTO pedidoAtualizada = pedidoService.update(pedido, id);
        return ResponseEntity.ok(pedidoAtualizada);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Excluir um cliente", notes = "Preencha com o ID do pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido excluído"),
            @ApiResponse(responseCode = "204", description = "Pedido não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Boolean response = pedidoService.delete(id);
        if (response == true) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.notFound().build();

    }
}
