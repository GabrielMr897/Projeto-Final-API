package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.config.MailConfig;
import br.org.serratec.dto.ClienteDTO;
import br.org.serratec.dto.ClienteInserirDTO;
import br.org.serratec.dto.EnderecoInserirDTO;
import br.org.serratec.model.Cliente;
import br.org.serratec.model.Endereco;
import br.org.serratec.repository.ClienteRepository;
import br.org.serratec.repository.EnderecoRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private MailConfig mailConfig;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<ClienteDTO> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDTO> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {
            clientesDTO.add(new ClienteDTO(cliente));
        }

        return clientesDTO;
    }

    public ClienteDTO buscar(Long id) {
        Optional<Cliente> clientes = clienteRepository.findById(id);
        if (!clientes.isPresent()) {
            return null;
        }
        return new ClienteDTO(clientes.get());
    }

    public ClienteDTO inserir(ClienteInserirDTO c) {

        EnderecoInserirDTO endereco = c.getEndereco();
        Endereco enderecoViaCep = enderecoService.inserir(endereco.getCep(), endereco.getComplemento(),
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

    public ClienteDTO update(ClienteInserirDTO c, Long id) {

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNomeCompleto(c.getNomeCompleto());
        cliente.setNomeUsuario(c.getNomeUsuario());
        cliente.setEmail(c.getEmail());
        cliente.setCpf(c.getCpf());
        cliente.setTelefone(c.getTelefone());
        cliente.setDataNascimento(c.getDataNascimento());
        cliente.setSenha(bCryptPasswordEncoder.encode(c.getSenha()));
        cliente = clienteRepository.save(cliente);

        mailConfig.sendEmail(cliente.getEmail(), "Cadastro de Usuário", cliente.toString());
        return new ClienteDTO(cliente);
    }
}
