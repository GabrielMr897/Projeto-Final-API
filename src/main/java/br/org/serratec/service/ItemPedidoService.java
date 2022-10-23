package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.ItemPedidoDTO;
import br.org.serratec.model.ItemPedido;
import br.org.serratec.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public ItemPedido inserir(ItemPedido ips) {
        return itemPedidoRepository.save(ips);
    }

    public List<ItemPedido> listar() {
        return itemPedidoRepository.findAll();
    }

    public Optional<ItemPedido> buscar(Long id) {
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);
        return itemPedido;
    }

    public ItemPedido update(ItemPedido itemPedido, Long id) {
        itemPedido.setIdItemPedido(id);
        return itemPedidoRepository.save(itemPedido);
    }
    
    public ItemPedidoDTO buscarTotalPedido(Long idPedido) {
    	List<ItemPedido> itemPedidoTotal = itemPedidoRepository.findByPedidoIdPedido(idPedido);
    	Double soma = 0d;
    	for (ItemPedido itemPedido : itemPedidoTotal) {
    		soma = itemPedido.getPrecoVenda() + soma;
    		}
        return new ItemPedidoDTO(idPedido, soma);
    }
}
