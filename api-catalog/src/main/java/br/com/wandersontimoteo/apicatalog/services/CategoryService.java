package br.com.wandersontimoteo.apicatalog.services;


import br.com.wandersontimoteo.apicatalog.dto.CategoryDTO;
import br.com.wandersontimoteo.apicatalog.entities.Category;
import br.com.wandersontimoteo.apicatalog.repositories.CategoryRepository;
import br.com.wandersontimoteo.apicatalog.services.exceptions.DatabaseException;
import br.com.wandersontimoteo.apicatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> listCategory = categoryRepository.findAll(pageable);
        return listCategory.map(CategoryDTO::new);
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = categoryRepository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Categoria com id: " + id +
                ", não encontrada"));
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO categoryDTO) {
        Category entity = new Category();
        entity.setName(categoryDTO.getName());
        entity = categoryRepository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        try {
            Category entity = categoryRepository.getOne(id);
            entity.setName(categoryDTO.getName());
            entity = categoryRepository.save(entity);
            return new CategoryDTO(entity);
        } catch (EntityNotFoundException error) {
            throw new ResourceNotFoundException("Categoria com id: " + id + ", não encontrada para atualizar");
        }
    }

    public void delete(Long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResourceNotFoundException("Categoria com id: " + id + ", não encontrada para excluir");
        } catch (DataIntegrityViolationException err) {
            throw new DatabaseException("Não é possível excluir a categoria de id: "+ id +
                    ", pois existe um ou mais produtos cadastrados no banco de dados"+
                    " com esta categoria");
        }
    }
}
