package br.org.serratec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import br.org.serratec.repository.ClienteRepository;
import br.org.serratec.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	@ApiOperation(value = "Listar todos os clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna os clientes"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<ClienteDTO>> listar() {
		return ResponseEntity.ok(clienteService.listar());
	}

	@GetMapping("{id}")
	@ApiOperation(value = "Buscar um cliente", notes = "Preencha com o ID do cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna o cliente baseado no id"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ClienteDTO> buscar(@PathVariable Long id) {
		ClienteDTO cliente = clienteService.buscar(id);

		if (cliente != null) {
			return ResponseEntity.ok(cliente);
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ApiOperation(value = "Registrar um novo cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente registrado"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ClienteDTO> inserir(@Valid @RequestBody ClienteInserirDTO cliente) {

		ClienteDTO clienteDTO = clienteService.inserir(cliente);

		return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);

	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "Excluir um cliente", notes = "Preencha com o ID do cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente excluído"),
			@ApiResponse(responseCode = "204", description = "Cliente não encontrado"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Boolean response = clienteService.delete(id);
		if (response == true) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("{id}")
	@ApiOperation(value = "Alterar um cliente", notes = "Preencha com o ID do cliente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", description = "Cliente alterado"),
			@ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
			@ApiResponse(responseCode = "401", description = "Erro na autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	public ResponseEntity<ClienteDTO> update(@PathVariable Long id, @RequestBody ClienteInserirDTO clienteInserirDTO) {

		if (!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		ClienteDTO clienteDTO = clienteService.update(clienteInserirDTO, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(clienteDTO);
	}

}