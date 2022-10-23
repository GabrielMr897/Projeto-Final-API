package br.org.serratec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

    /*
     * @Query(nativeQuery = true, value =
     * "select id_pedido, sum(preco_venda) from item_pedido where :idPedido group by id_pedido"
     * )
     */
    // @Query(nativeQuery = true, value = "SELECT i FROM item_pedido i WHERE
    // :idPedido")
    //public Page<ItemPedido> buscarTotalPedido(Long idPedido, Pageable pageable);
	
	List<ItemPedido> findByPedidoIdPedido(Long idPedido);
}
