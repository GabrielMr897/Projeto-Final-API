package br.org.serratec.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.ClienteDTO;
import br.org.serratec.dto.ClienteInserirDTO;
import br.org.serratec.exception.EmailException;
import br.org.serratec.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar() {
		return ResponseEntity.ok(clienteService.listar());
	}

	@GetMapping("{id}")
	public ResponseEntity<ClienteDTO> buscar(@PathVariable Long id) {
		ClienteDTO cliente = clienteService.buscar(id);

		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Object> inserir(@Valid @RequestBody ClienteInserirDTO cliente) {
		try {
			ClienteDTO clienteDTO = clienteService.inserir(cliente);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clienteDTO.getId())
					.toUri();
			return ResponseEntity.created(uri).body(clienteDTO);
		} catch (EmailException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Boolean response = clienteService.delete(id);
		System.out.println(response);
		if (response != true) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED).build();

	}

}