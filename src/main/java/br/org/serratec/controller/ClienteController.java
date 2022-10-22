package br.org.serratec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.dto.ClienteDTO;
import br.org.serratec.dto.ClienteInserirDTO;
import br.org.serratec.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ClienteDTO>> listar() {
		return ResponseEntity.ok(clienteService.listar());
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ClienteDTO> buscar(@PathVariable Long id) {
		ClienteDTO cliente = clienteService.buscar(id);

		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ClienteDTO> inserir(@Valid @RequestBody ClienteInserirDTO cliente) {

		ClienteDTO clienteDTO = clienteService.inserir(cliente);

		return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);

	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Boolean response = clienteService.delete(id);
		if (response == true) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("{id}")
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteInserirDTO clienteInserirDTO) {
		ClienteDTO clienteDTO = clienteService.update(clienteInserirDTO, id);

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteDTO);
	}

}