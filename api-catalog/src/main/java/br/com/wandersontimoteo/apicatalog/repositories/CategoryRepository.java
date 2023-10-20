package br.com.wandersontimoteo.apicatalog.repositories;

import br.com.wandersontimoteo.apicatalog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
