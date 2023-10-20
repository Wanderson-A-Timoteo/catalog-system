package br.com.wandersontimoteo.apicatalog.services;


import br.com.wandersontimoteo.apicatalog.dto.CategoryDTO;
import br.com.wandersontimoteo.apicatalog.entities.Category;
import br.com.wandersontimoteo.apicatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> listCategory = categoryRepository.findAll();
        return listCategory.stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = categoryRepository.findById(id);
        Category entity = obj.get();
        return new CategoryDTO(entity);
    }

}
