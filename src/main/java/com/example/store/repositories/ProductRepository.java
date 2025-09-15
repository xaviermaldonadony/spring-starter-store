package com.example.store.repositories;

import com.example.store.dtos.ProductSummaryDTO;
import com.example.store.entities.Category;
import com.example.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

//public interface ProductRepository extends CrudRepository<Product, Long> {
public interface ProductRepository extends JpaRepository<Product, Long>, ProductCriteriaRepository, JpaSpecificationExecutor <Product> {
    // String conventions
    // Select * from products where name = ?
    List<Product> findByName(String name);

    // working with Strings
    // Select * from products where name  like  ?
    List<Product> findByNameLike(String name);

    // case-sensitive
    List<Product> findByNameNotLike(String name);

    List<Product> findByNameStartingWith(String name);

    List<Product> findByNameEndingWith(String name);

    List<Product> findByNameEndingWithIgnoreCase(String name);

    // Numbers
    // Select * from products where price = ?
    List<Product> findByPrice(BigDecimal price);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceGreaterThanEqual(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    // Null
    List<Product> findByDescriptionNull();

    List<Product> findByDescriptionNotNull();

    // Multiple conditions
    List<Product> findByDescriptionNullAndNameNull();

    // Sort
    // parameter would filter by that argument
    List<Product> findByNameOrderByPrice(String name);

    // Limit (Top/First)
    List<Product> findTop5ByNameOrderByPrice(String name);

    // filter by name, sort by price Asc and pick first 5 records.
    // limitation by first 5
    List<Product> findFirst5ByNameLikeOrderByPrice(String name);

    // Find products whose prices  are in a given range and sort by name
//    List<Product> findByPriceBetweenOrderByName(BigDecimal min, BigDecimal max);

    // rewrite
    //SQL
//    @Query(value = "select * from products p where p.price between : min and :max order by p.name", nativeQuery = true)
//    List<Product> findProducts(@Param("min") BigDecimal min, @Param("min")BigDecimal max);

    // JPQL
//    @Query("select p from Product p where p.price between :min and :min order by p.name")
//    List<Product> findProducts(@Param("min") BigDecimal min, @Param("min") BigDecimal max);
    //Store Proc
    @Procedure("findProductsByPrice")
    List<Product> findProducts(BigDecimal min, BigDecimal max);


    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("min") BigDecimal max);

    @Modifying // update not a select, method calling it needs @Transactional
    @Query("update Product p set p.price = :newPrice where p.category.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    // if AbstractJPAQuery error
    @Query("select new com.example.store.dtos.ProductSummaryDTO(p.id, p.name) from Product p where p.category = :category")
//    @Query("select p.id, p.name from Product p where p.category = :category")
    List <ProductSummaryDTO> findByCategory(@Param("category") Category category);
}
