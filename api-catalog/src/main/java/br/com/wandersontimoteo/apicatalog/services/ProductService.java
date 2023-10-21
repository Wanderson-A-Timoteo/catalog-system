package br.com.wandersontimoteo.apicatalog.services;

import br.com.wandersontimoteo.apicatalog.dto.ProductDTO;
import br.com.wandersontimoteo.apicatalog.entities.Product;
import br.com.wandersontimoteo.apicatalog.repositories.ProductRepository;
import br.com.wandersontimoteo.apicatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
        Page<Product> listProduct = productRepository.findAll(pageRequest);
        return listProduct.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = productRepository.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Produto com id: " + id +
                ", não encontrada"));
        return new ProductDTO(entity, entity.getCategories());
    }

}
