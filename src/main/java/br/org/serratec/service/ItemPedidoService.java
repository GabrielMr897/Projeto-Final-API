package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.ItemPedidoDTO;
import br.org.serratec.dto.ItemPedidoInserirDTO;
import br.org.serratec.dto.ItemPedidoTotalDTO;
import br.org.serratec.exception.ItemPedidoException;
import br.org.serratec.model.ItemPedido;
import br.org.serratec.model.Pedido;
import br.org.serratec.model.Produto;
import br.org.serratec.repository.ItemPedidoRepository;
import br.org.serratec.repository.PedidoRepository;
import br.org.serratec.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    public ItemPedidoDTO inserir(ItemPedidoInserirDTO ips) {
        Optional<Produto> produto = produtoRepository.findById(ips.getProduto().getIdProduto());

        Optional<Pedido> pedido = pedidoRepository.findById(ips.getPedido().getIdPedido());

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPrecoVenda(ips.getPrecoVenda());
        itemPedido.setQuantidade(ips.getQuantidade());
        itemPedido.setProduto(produto.get());
        itemPedido.setPedido(pedido.get());
        itemPedido = itemPedidoRepository.save(itemPedido);

        return new ItemPedidoDTO(itemPedido);

    }

    public List<ItemPedidoDTO> listar() {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
        List<ItemPedidoDTO> itemPedidoDTOs = new ArrayList<>();

        for (ItemPedido itemPedido : itemPedidos) {
            itemPedidoDTOs.add(new ItemPedidoDTO(itemPedido));
        }
        return itemPedidoDTOs;
    }

    public ItemPedidoDTO buscar(Long id) {
        Optional<ItemPedido> itemPedido = itemPedidoRepository.findById(id);

        if (!itemPedido.isPresent()) {
            return null;
        }
        return new ItemPedidoDTO(itemPedido.get());
    }

    public ItemPedidoDTO update(ItemPedidoInserirDTO itemPedidoInserirDTO, Long id) {

        Optional<Produto> produto = produtoRepository.findById(itemPedidoInserirDTO.getProduto().getIdProduto());

        Optional<Pedido> pedido = pedidoRepository.findById(itemPedidoInserirDTO.getPedido().getIdPedido());

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setIdItemPedido(id);
        itemPedido.setPrecoVenda(itemPedidoInserirDTO.getPrecoVenda());
        itemPedido.setQuantidade(itemPedidoInserirDTO.getQuantidade());
        itemPedido.setProduto(produto.get());
        itemPedido.setPedido(pedido.get());
        itemPedido = itemPedidoRepository.save(itemPedido);

        return new ItemPedidoDTO(itemPedido);
    }

    public ItemPedidoTotalDTO buscarTotalPedido(Long idPedido) {
        Double totalPedido = itemPedidoRepository.totalPedido(idPedido);
        if (totalPedido == null) {
            throw new ItemPedidoException("Pedido não existe");
        }

        ItemPedidoTotalDTO itemPedidoDTO = new ItemPedidoTotalDTO();
        itemPedidoDTO.setIdPedido(idPedido);
        itemPedidoDTO.setTotalPedido(totalPedido);
        return itemPedidoDTO;
    }
}
