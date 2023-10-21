package br.com.wandersontimoteo.apicatalog.repositories;

import br.com.wandersontimoteo.apicatalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
