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

import br.org.serratec.model.Pedido;
import br.org.serratec.repository.PedidoRepository;
import br.org.serratec.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    private PedidoRepository pedidoRepository;

    @GetMapping
    @ApiOperation(value = "Listar todos os pedidos")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="200", description = "Retorna todos os pedidos"),
    		@ApiResponse(responseCode="404", description = "Recurso não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<Pedido> listar() {
        return pedidoService.listar();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar um pedido", notes = "Preencha com o ID do pedido")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="200", description = "Retorna o pedido"),
    		@ApiResponse(responseCode="404", description = "Pedido não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscar(id);

        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ApiOperation(value = "Registrar um pedido")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="201", description = "Pedido registrado"),
    		@ApiResponse(responseCode="404", description = "Recurso não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pedido> inserir(@Valid @RequestBody Pedido pedido) {
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        } else {
            pedidoService.inserir(pedido);
            return ResponseEntity.ok(pedido);
        }
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Alterar um pedido", notes = "Preencha com o ID do pedido")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="201", description = "Novo pedido registrado"),
    		@ApiResponse(responseCode="202", description = "Pedido alterado"),
    		@ApiResponse(responseCode="404", description = "Recurso não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Pedido pedidoAtualizada = pedidoService.update(pedido, id);
        return ResponseEntity.ok(pedidoAtualizada);
    }
}
