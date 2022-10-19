package br.org.serratec.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.ClienteDTO;
import br.org.serratec.dto.ClienteInserirDTO;
import br.org.serratec.exception.EmailException;
import br.org.serratec.service.ClienteService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar(){
		return ResponseEntity.ok(clienteService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody ClienteInserirDTO clienteInserirDTO) {
		try {
			ClienteDTO clienteDTO = clienteService.inserir(clienteInserirDTO);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(clienteDTO.getIdCliente()).toUri();
			return ResponseEntity.created(uri).body(clienteDTO);

		} catch (EmailException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

}
