package br.org.serratec.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.PedidoDTO;
import br.org.serratec.dto.PedidoInserirDTO;
import br.org.serratec.model.Cliente;
import br.org.serratec.model.Pedido;
import br.org.serratec.repository.ClienteRepository;
import br.org.serratec.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public PedidoDTO inserir(PedidoInserirDTO pedidoInserirDTO) {
        Optional<Cliente> cliente = clienteRepository.findById(pedidoInserirDTO.getCliente().getId());

        Pedido pedido = new Pedido();
        pedido.setDataEntrega(pedidoInserirDTO.getDataEntrega());
        pedido.setDataEnvio(pedidoInserirDTO.getDataEnvio());
        pedido.setDataPedido(LocalDate.now());
        pedido.setStatus(pedidoInserirDTO.getStatus());
        pedido.setCliente(cliente.get());
        pedido = pedidoRepository.save(pedido);

        return new PedidoDTO(pedido);

    }

    public List<PedidoDTO> listar() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoDTO> pedidoDTOs = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            pedidoDTOs.add(new PedidoDTO(pedido));
        }
        return pedidoDTOs;
    }

    public PedidoDTO buscar(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (!pedido.isPresent()) {
            return null;
        }
        return new PedidoDTO(pedido.get());
    }

    public PedidoDTO update(PedidoInserirDTO pedidoInserirDTO, Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(pedidoInserirDTO.getCliente().getId());

        Pedido pedido = new Pedido();
        pedido.setIdPedido(id);
        pedido.setDataEntrega(pedidoInserirDTO.getDataEntrega());
        pedido.setDataEnvio(pedidoInserirDTO.getDataEnvio());
        pedido.setDataPedido(LocalDate.now());
        pedido.setStatus(pedidoInserirDTO.getStatus());
        pedido.setCliente(cliente.get());
        pedido = pedidoRepository.save(pedido);

        return new PedidoDTO(pedido);
    }

    public Boolean delete(Long id) {
        Optional<Pedido> pedidos = pedidoRepository.findById(id);
        if (pedidos.isPresent()) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
