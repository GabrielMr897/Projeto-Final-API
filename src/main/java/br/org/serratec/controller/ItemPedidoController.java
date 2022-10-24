package br.org.serratec.controller;

import java.util.List;

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
import br.org.serratec.dto.ItemPedidoInserirDTO;
import br.org.serratec.dto.ItemPedidoTotalDTO;
import br.org.serratec.repository.ItemPedidoRepository;
import br.org.serratec.service.ItemPedidoService;

@RestController
@RequestMapping("/itensPedidos")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoService itemPedidoService;
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ItemPedidoDTO> listar() {
        return itemPedidoService.listar();
    }

    @GetMapping("{id}")
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
    public ResponseEntity<ItemPedidoTotalDTO> buscarTotalPedido(@PathVariable Long idPedido) {
        return ResponseEntity.ok(itemPedidoService.buscarTotalPedido(idPedido));
    }

    @PostMapping
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ItemPedidoDTO> atualizar(@PathVariable Long id,
            @RequestBody ItemPedidoInserirDTO itemPedido) {

        if (!itemPedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        ItemPedidoDTO itemPedidoAtualizado = itemPedidoService.update(itemPedido, id);
        return ResponseEntity.ok(itemPedidoAtualizado);
    }
}
