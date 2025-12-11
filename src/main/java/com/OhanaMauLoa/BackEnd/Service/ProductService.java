package com.OhanaMauLoa.BackEnd.Service;

import com.OhanaMauLoa.BackEnd.Entity.Product;
import com.OhanaMauLoa.BackEnd.Repository.ProductRepository;
import com.OhanaMauLoa.BackEnd.Utilities.StringUtils;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
  private final ProductRepository productRepository;
  private final StringUtils stringUtils;
  public ProductService(ProductRepository productRepository, StringUtils stringUtils) {
    this.productRepository = productRepository;
    this.stringUtils = stringUtils;
  }
  public Product createProduct(Product product){
    System.out.println("Producto Ingresado: " + product);

    return this.productRepository.save(product);
  }
  public Product getProductById(Long id){
    Optional<Product> productOptional = this.productRepository.findById(id);
    if (productOptional.isEmpty()){
      throw new RuntimeException("Producto no encontrado con id: " + id);
    }
    return productOptional.get();
  }
  public List<Product> findAllProducts(String name, String category){
    if (!name.isEmpty() && !category.isEmpty()){
      return this.productRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCase(name, category);
    }
    if (!name.isEmpty()){
      return this.productRepository.findByNameContainingIgnoreCase(name);
    }
    if (!category.isEmpty()){
      return this.productRepository.findByCategoryContainingIgnoreCase(category);
    }
    return this.productRepository.findAll();
  }
  public Product editProductById(Long id, Product dataToEdit){
    Product product = this.getProductById(id);
    if (stringUtils.isEmpty(dataToEdit.getName())){
      System.out.printf("Editando el nombre del producto: viejo:%s - nuevo:%s", product.getName(), dataToEdit.getName());
      product.setName(dataToEdit.getName());
    }
    return this.productRepository.save(product);
  }
  public Product deleteProductById(Long id){
    Product product = this.getProductById(id);
    this.productRepository.delete(product);
    return product;
  }
}
