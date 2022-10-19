package br.org.serratec.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.model.Pedido;
import br.org.serratec.repository.PedidoRepository;
import br.org.serratec.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @Autowired
    private PedidoService pedidoService;
    @Autowired
    private PedidoRepository pedidoRepository;
    
    
    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listar();
    }
    
    @GetMapping({"id"})
    public ResponseEntity<Pedido> buscar(@PathVariable Long id){
        Optional<Pedido> pedido=pedidoRepository.findById(id);
        if(pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Pedido> inserir(@Valid @RequestBody Pedido pedido){

        if (null != pedido){
            pedidoService.inserir(pedido);
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping
    public ResponseEntity<Pedido> atualizar (@PathVariable Long id, @RequestBody Pedido pedido){
        Pedido pedidoAtualizado = pedidoService.update(pedido, id);
        return ResponseEntity.ok(pedidoAtualizado);
    }

}
