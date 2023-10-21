package br.com.wandersontimoteo.apicatalog.resources;

import br.com.wandersontimoteo.apicatalog.dto.ProductDTO;
import br.com.wandersontimoteo.apicatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(
        @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
        @RequestParam(value = "linhasPorPagina", defaultValue = "10") Integer linhasPorPagina,
        @RequestParam(value = "ordenarPor", defaultValue = "name") String ordenarPor,
        @RequestParam(value = "direcao", defaultValue = "ASC") String direcao
    ) {
        PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direcao), ordenarPor);

        Page<ProductDTO> listProduct = productService.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(listProduct);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findById(id);
        return ResponseEntity.ok().body(productDTO);
    }

}
