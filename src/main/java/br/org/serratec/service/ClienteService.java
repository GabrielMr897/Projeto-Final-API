package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.ClienteDTO;
import br.org.serratec.dto.ClienteInserirDTO;
import br.org.serratec.exception.CpfException;
import br.org.serratec.exception.EmailException;
import br.org.serratec.model.Cliente;
import br.org.serratec.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EnderecoService enderecoService;
	
	public List<ClienteDTO> listar() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		
		for (Cliente cliente : clientes) {
			clientesDTO.add(new ClienteDTO(cliente));
		}

		return clientesDTO;
	}
	
	
	public ClienteDTO inserir(ClienteInserirDTO clienteInserirDTO) {
		if (clienteRepository.findByCpf(clienteInserirDTO.getCpf()) != null) {
			throw new CpfException("CPF já cadastrado");
		}
		if (clienteRepository.findByEmail(clienteInserirDTO.getEmail()) != null) {
			throw new EmailException("Email já cadastrado");
		}
		Cliente cliente = new Cliente();
		cliente.setNomeUsuario(clienteInserirDTO.getNomeUsuario());
		cliente.setEmail(clienteInserirDTO.getEmail());
		cliente.setEndereco(clienteInserirDTO.getEndereco()); //Talvez tenha que consumir o VIA CEP aqui.
		cliente.setSenha(bCryptPasswordEncoder.encode(clienteInserirDTO.getSenha()));
		cliente = clienteRepository.save(cliente);
		return new ClienteDTO(cliente);
		
	}
		
	
}
