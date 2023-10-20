package br.com.wandersontimoteo.apicatalog.services;


import br.com.wandersontimoteo.apicatalog.entities.Category;
import br.com.wandersontimoteo.apicatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
