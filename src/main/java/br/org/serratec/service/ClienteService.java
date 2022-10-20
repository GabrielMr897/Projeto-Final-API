package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.dto.ClienteDTO;
import br.org.serratec.dto.ClienteInserirDTO;
import br.org.serratec.dto.ClienteListDTO;
import br.org.serratec.dto.EnderecoInserirDTO;
import br.org.serratec.model.Cliente;
import br.org.serratec.model.Endereco;
import br.org.serratec.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<ClienteListDTO> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteListDTO> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {
            clientesDTO.add(new ClienteListDTO(cliente));
        }

        return clientesDTO;
    }

    public ClienteListDTO buscar(Long id) {
        Optional<Cliente> clientes = clienteRepository.findById(id);
        if (!clientes.isPresent()) {
            return null;
        }
        return new ClienteListDTO(clientes.get());
    }

    public ClienteDTO inserir(ClienteInserirDTO c) {

        EnderecoInserirDTO endereco = c.getEndereco();
        Endereco enderecoViaCep = enderecoService.salvar(endereco.getCep(), endereco.getComplemento(),
                endereco.getNumero());

        Cliente cliente = new Cliente();
        cliente.setNomeCompleto(c.getNomeCompleto());
        cliente.setNomeUsuario(c.getNomeUsuario());
        cliente.setEmail(c.getEmail());
        cliente.setCpf(c.getCpf());
        cliente.setTelefone(c.getTelefone());
        cliente.setDataNascimento(c.getDataNascimento());
        cliente.setSenha(bCryptPasswordEncoder.encode(c.getSenha()));
        cliente.setEndereco(enderecoViaCep);
        cliente = clienteRepository.save(cliente);

        /*
         * mailConfig.sendEmail(c.getEmail(), "Cadastro de Usuário",
         * cliente.toString());
         */
        return new ClienteDTO(cliente);
    }

    public Boolean delete(Long id) {
        Optional<Cliente> clientes = clienteRepository.findById(id);
        if (clientes.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
