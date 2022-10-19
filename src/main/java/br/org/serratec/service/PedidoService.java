package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.model.Pedido;
import br.org.serratec.repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    
    public Pedido inserir(Pedido p) {
        pedidoRepository.save(p);
        return p;
    }
    
    public List<Pedido> listar(){
        return pedidoRepository.findAll();
    }
    
    public Optional<Pedido> buscar(Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido;
    }
    
    public Pedido update (Pedido pedido, Long id) {
        pedido.setIdPedido(id);
        return pedidoRepository.save(pedido);
    }
}

