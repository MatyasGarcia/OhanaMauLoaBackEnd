package com.OhanaMauLoa.BackEnd.Repository;

import com.OhanaMauLoa.BackEnd.Entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findByNameContainingIgnoreCase(String name, Sort sort);
  List<Product> findByCategoryContainingIgnoreCase(String category, Sort sort);
  List<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCase(
      String name,
      String category,
      Sort sort
  );
}
