package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.model.Categoria;
import br.org.serratec.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria inserir(Categoria c) {
        categoriaRepository.save(c);
        return c;
    }


    public List<Categoria> listar() {
        return categoriaRepository.findAll(); 
    }
    
    public Optional<Categoria> buscar(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        return categoria;
    }

    public Categoria update (Categoria categoria, Long id) {
        categoria.getIdCategoria();
        return categoriaRepository.save(categoria);
    }
}
