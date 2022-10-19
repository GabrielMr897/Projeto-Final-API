package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.org.serratec.model.Produto;
import br.org.serratec.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    
    public Produto inserir(Produto p) {
        produtoRepository.save(p);
        return p;
    }
    
    public List<Produto> listar(){
        return produtoRepository.findAll();
    }
    
    public Optional<Produto> buscar(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto;
    }
    
    public Produto update (Produto produto, Long id) {
        produto.setIdProduto(id);
        return produtoRepository.save(produto);
    }
}
