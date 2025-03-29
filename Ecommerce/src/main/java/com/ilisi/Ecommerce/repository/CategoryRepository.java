package com.ilisi.Ecommerce.repository;

import com.ilisi.Ecommerce.bo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@Repository

public interface CategoryRepository extends JpaRepository<Category, Integer > {

@Query("Select c from Category c where c.label LIKE %?1%")
List<Category> findByLabel(String name);

}
