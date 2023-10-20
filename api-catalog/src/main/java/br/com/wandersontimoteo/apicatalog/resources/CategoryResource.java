package br.com.wandersontimoteo.apicatalog.resources;

import br.com.wandersontimoteo.apicatalog.dto.CategoryDTO;
import br.com.wandersontimoteo.apicatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> listCategory = categoryService.findAll();
        return ResponseEntity.ok().body(listCategory);
    }

}
