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

import br.org.serratec.model.ItemPedido;
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
    public List<ItemPedido> listar() {
        return itemPedidoService.listar();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ItemPedido> buscar(@PathVariable Long id) {
        Optional<ItemPedido> itemPedido = itemPedidoService.buscar(id);
        if (itemPedido.isPresent()) {
            return ResponseEntity.ok(itemPedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * @GetMapping("/totalPedido")
     * public ResponseEntity<Page<ItemPedido>> buscarPorFaixaSalarial(@RequestParam
     * Long idPedido, Pageable pageable) {
     * return ResponseEntity.ok(itemPedidoRepository.buscarTotalPedido(idPedido,
     * pageable));
     * }
     */

    @PostMapping
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ItemPedido> atualizar(@PathVariable Long id, @RequestBody ItemPedido itemPedido) {
        ItemPedido itemPedidoAtualizado = itemPedidoService.update(itemPedido, id);
        return ResponseEntity.ok(itemPedidoAtualizado);
    }
}
