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

import br.org.serratec.dto.ItemPedidoDTO;
import br.org.serratec.dto.ItemPedidoInserirDTO;
import br.org.serratec.dto.ItemPedidoTotalDTO;
import br.org.serratec.repository.ItemPedidoRepository;
import br.org.serratec.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "http://localhost:3000")
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
            @ApiResponse(responseCode = "200", description = "Retorna todos os item-pedido"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<ItemPedidoDTO> listar() {
        return itemPedidoService.listar();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar um item-pedido", notes = "Preencha com o ID do item-pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o item-pedido"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ItemPedidoDTO> buscar(@PathVariable Long id) {
        ItemPedidoDTO itemPedido = itemPedidoService.buscar(id);
        if (itemPedido != null) {
            return ResponseEntity.ok(itemPedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{idPedido}/totalPedido")
    @ApiOperation(value = "Buscar o total de um pedido", notes = "Preencha com o ID pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o total do pedido"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<ItemPedidoTotalDTO> buscarTotalPedido(@PathVariable Long idPedido) {
        return ResponseEntity.ok(itemPedidoService.buscarTotalPedido(idPedido));
    }

    @PostMapping
    @ApiOperation(value = "Registrar um item-pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item-pedido registrado"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemPedidoDTO> inserir(@Valid @RequestBody ItemPedidoInserirDTO itemPedido) {

        if (null != itemPedido) {
            ItemPedidoDTO itemPedidos = itemPedidoService.inserir(itemPedido);
            return ResponseEntity.ok(itemPedidos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    @ApiOperation(value = "Alterar um item-pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Item-pedido alterado"),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ItemPedidoDTO> atualizar(@PathVariable Long id,
            @RequestBody ItemPedidoInserirDTO itemPedido) {

        if (!itemPedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        ItemPedidoDTO itemPedidoAtualizado = itemPedidoService.update(itemPedido, id);
        return ResponseEntity.ok(itemPedidoAtualizado);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "Excluir um cliente", notes = "Preencha com o ID do itemPedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "itemPedido excluído"),
            @ApiResponse(responseCode = "204", description = "itemPedido não encontrado"),
            @ApiResponse(responseCode = "401", description = "Erro na autenticação"),
            @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
            @ApiResponse(responseCode = "500", description = "Erro na aplicação")
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Boolean response = itemPedidoService.delete(id);
        if (response == true) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.notFound().build();

    }
}
