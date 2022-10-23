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

import br.org.serratec.dto.ItemPedidoDTO;
import br.org.serratec.model.ItemPedido;
import br.org.serratec.repository.ItemPedidoRepository;
import br.org.serratec.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/itensPedidos")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoService itemPedidoService;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @GetMapping
    @ApiOperation(value = "Listar todos item-pedido")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="200", description = "Retorna todos os item-pedido"),
    		@ApiResponse(responseCode="404", description = "Recurso não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<ItemPedido> listar() {
        return itemPedidoService.listar();
    }

    @GetMapping({"id"})
    @ApiOperation(value = "Buscar um item-pedido", notes = "Preencha com o ID do item-pedido")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="200", description = "Retorna o item-pedido"),
    		@ApiResponse(responseCode="404", description = "Recurso não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ItemPedido> buscar(@PathVariable Long id) {
        Optional<ItemPedido> itemPedido = itemPedidoService.buscar(id);
        if (itemPedido.isPresent()) {
            return ResponseEntity.ok(itemPedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{idPedido}/totalPedido")
    @ApiOperation(value = "Buscar o total de um pedido", notes = "Preencha com o ID pedido")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="200", description = "Retorna o total do pedido"),
    		@ApiResponse(responseCode="404", description = "Recurso não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    public ResponseEntity<ItemPedidoDTO> buscarTotalPedido(@PathVariable Long idPedido) {
    	return ResponseEntity.ok(itemPedidoService.buscarTotalPedido(idPedido));
    }

    @PostMapping
    @ApiOperation(value = "Registrar um item-pedido")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="201", description = "Item-pedido registrado"),
    		@ApiResponse(responseCode="404", description = "Recurso não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemPedido> inserir(@Valid @RequestBody ItemPedido itemPedido) {

        if (null != itemPedido) {
            ItemPedido itemPedidos = itemPedidoService.inserir(itemPedido);
            return ResponseEntity.ok(itemPedidos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Alterar um item-pedido")
    @ApiResponses(value = {
    		@ApiResponse(responseCode="201", description = "Novo item-pedido registrado"),
    		@ApiResponse(responseCode="202", description = "Item-pedido alterado"),
    		@ApiResponse(responseCode="404", description = "Recurso não encontrado"),
    		@ApiResponse(responseCode="401", description = "Erro na autenticação"),
    		@ApiResponse(responseCode="403", description = "Você não tem permissão para o recurso"),
    		@ApiResponse(responseCode="500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ItemPedido> atualizar(@PathVariable Long id, @RequestBody ItemPedido itemPedido) {
        ItemPedido itemPedidoAtualizado = itemPedidoService.update(itemPedido, id);
        return ResponseEntity.ok(itemPedidoAtualizado);
    }
}
