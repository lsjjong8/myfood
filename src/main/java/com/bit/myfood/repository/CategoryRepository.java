package com.bit.myfood.repository;

import com.bit.myfood.model.entity.Category;
import com.bit.myfood.model.entity.CookRcp;
import com.bit.myfood.model.enumclass.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);

    Optional<Category> getFirstByOrderByIdDesc();

    Optional<Long> countBy();

    Optional<Category> findByType(CategoryType type);
}
