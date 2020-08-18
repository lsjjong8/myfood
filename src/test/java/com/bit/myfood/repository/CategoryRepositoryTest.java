package com.bit.myfood.repository;

import com.bit.myfood.ApplicationTests;
import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.entity.Category;
import com.bit.myfood.model.entity.CookRcp;
import com.bit.myfood.model.enumclass.AdminUserStatus;
import com.bit.myfood.model.enumclass.CategoryType;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CategoryRepositoryTest extends ApplicationTests {

    @Autowired
    CategoryRepository categoryRepository;

    //region CRUD
    @Test
    @Transactional
    void create() {

        try {
            Category entity = Category.builder()
                    .type(CategoryType.BEAUTY)
                    .title("Test")
                    .build();


            Category newEntity = categoryRepository.save(entity);

            assertEquals(entity, newEntity);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (JpaObjectRetrievalFailureException e) {
            e.printStackTrace();
        }
    }

    @Test
    void read() {
        Long id = 1L;

        try {
            Optional<Category> selectedEntity = categoryRepository.findById(id);
            assertNotNull(selectedEntity.get());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void update() {

        try {

            Optional<Category> maxRow = categoryRepository.getFirstByOrderByIdDesc();

            Category entity = maxRow.get();

            maxRow.ifPresent(perform -> {
                entity.setType(CategoryType.CLOTHING)
                        .setTitle("Test2")
                        .setUpdatedAt(LocalDateTime.now());
            });

            Category newEntity = categoryRepository.save(entity);

            assertEquals(entity, newEntity);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (JpaObjectRetrievalFailureException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void delete() throws DataIntegrityViolationException {
        Optional<Long> before = categoryRepository.countBy();
        Long beforeCount = before.get();

        try {
            Optional<Category> maxRow = categoryRepository.getFirstByOrderByIdDesc();

            categoryRepository.delete(maxRow.get());

            Optional<Long> after = categoryRepository.countBy();
            Long afterCount = after.get();

            assertEquals(beforeCount, afterCount + 1L);

        } catch (ConstraintViolationException e){
            e.printStackTrace();
        } catch (DataIntegrityViolationException e){
            e.printStackTrace();
        } catch (javax.persistence.EntityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion
}