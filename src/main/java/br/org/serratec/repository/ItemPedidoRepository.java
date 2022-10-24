package br.org.serratec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.org.serratec.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    @Query(nativeQuery = true, value = "select sum(preco_venda) from item_pedido where id_pedido = :idPedido")
    Double totalPedido(Long idPedido);

    List<ItemPedido> findByPedidoIdPedido(Long idPedido);
}
