package br.org.serratec.dto;

public class ItemPedidoTotalDTO {
	
	private Long idPedido;
	private Double totalPedido;
	
	
	public ItemPedidoTotalDTO() {
	}

	public ItemPedidoTotalDTO(Long idPedido, Double totalPedido) {
		this.idPedido = idPedido;
		this.totalPedido = totalPedido;
	}
	
	public Long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	public Double getTotalPedido() {
		return totalPedido;
	}
	public void setTotalPedido(Double totalPedido) {
		this.totalPedido = totalPedido;
	}
	

}
