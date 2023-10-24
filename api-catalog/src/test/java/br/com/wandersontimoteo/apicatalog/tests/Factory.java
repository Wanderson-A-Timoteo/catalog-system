package br.com.wandersontimoteo.apicatalog.tests;

import br.com.wandersontimoteo.apicatalog.dto.ProductDTO;
import br.com.wandersontimoteo.apicatalog.entities.Category;
import br.com.wandersontimoteo.apicatalog.entities.Product;

import java.math.BigDecimal;
import java.time.Instant;

public class Factory {

    public static Product createProduct() {

        Product product = new Product();
        product.setName("Phone");
        product.setDescription("Good Phone");
        product.setPrice(BigDecimal.valueOf(800.0));
        product.setImgUrl("https://img.com/img.png");
        product.setDate(Instant.now());
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(null);
        product.getCategories().add(new Category(2L, "Eletronics", Instant.now(), null));
        return product;
    }

    public static ProductDTO createProductDTO() {
        Product product = createProduct();
        return new ProductDTO(product, product.getCategories());
    }
}
