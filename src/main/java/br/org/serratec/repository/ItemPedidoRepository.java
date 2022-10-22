package br.org.serratec.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.org.serratec.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    @Query(nativeQuery = true, value = "select id_pedido, sum(preco_venda) from item_pedido where :idPedido group by id_pedido")
    public Page<ItemPedido> buscarTotalPedido(Long idPedido, Pageable pageable);

}
