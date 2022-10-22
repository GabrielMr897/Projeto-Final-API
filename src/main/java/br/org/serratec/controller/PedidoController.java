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

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;
    private PedidoRepository pedidoRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Pedido> listar() {
        return pedidoService.listar();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        Optional<Pedido> pedido = pedidoService.buscar(id);

        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
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
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        if (!pedidoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Pedido pedidoAtualizada = pedidoService.update(pedido, id);
        return ResponseEntity.ok(pedidoAtualizada);
    }
}
