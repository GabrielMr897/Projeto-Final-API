package br.org.serratec.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.ProdutoDTO;
import br.org.serratec.dto.ProdutoInserirDTO;
import br.org.serratec.model.Produto;
import br.org.serratec.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoDTO inserirUriImagem(Produto produto) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/{id}/foto").buildAndExpand(produto.getIdProduto()).toUri();

        ProdutoDTO dto = new ProdutoDTO();
        dto.setIdProduto(produto.getIdProduto());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setDataCadastro(produto.getDataCadastro());
        dto.setValorUnitario(produto.getValorUnitario());
        dto.setCategoria(produto.getCategoria());
        dto.setUri(uri.toString());

        return dto;
    }
    
    public ProdutoDTO inserir(ProdutoInserirDTO produtoInserirDTO, MultipartFile file) throws IOException {
        Produto produto = new Produto();
        produto.setNome(produtoInserirDTO.getNome());
        produto.setDescricao(produtoInserirDTO.getDescricao());
        produto.setQuantidadeEstoque(produtoInserirDTO.getQuantidadeEstoque());
        produto.setDataCadastro(produtoInserirDTO.getDataCadastro());
        produto.setValorUnitario(produtoInserirDTO.getValorUnitario());
        produto.setCategoria(produtoInserirDTO.getCategoria());
        produto.setImagem(file.getBytes());
        
       
        
        produto = produtoRepository.save(produto);
        return inserirUriImagem(produto);
    }
    
    public List<ProdutoDTO> listar(){
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtoDTO = new ArrayList<>();

        for(Produto produto : produtos) {
            produtoDTO.add(inserirUriImagem(produto));
        }
        return produtoDTO;
    }
    
    public ProdutoDTO buscar(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);

        
        if (!produto.isPresent()) {
            return null;
		}
        return new ProdutoDTO(produto.get());
	}

    public Produto buscarPorFoto(Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        
        if (!produto.isPresent()) {
            return null;
		}
        return produto.get();
    }
    
    
    public Produto update (Produto produto, Long id) {
        produto.setIdProduto(id);
        return produtoRepository.save(produto);
    }
}
