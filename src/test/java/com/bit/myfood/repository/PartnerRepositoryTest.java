package com.bit.myfood.repository;

import com.bit.myfood.ApplicationTests;
import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.entity.CookRcp;
import com.bit.myfood.model.entity.Partner;
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

class PartnerRepositoryTest extends ApplicationTests {

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    // region CRUD
    @Test
    @Transactional
    void create() {

        try {
            Partner entity = Partner.builder()
                    .name("Test Name")
                    .build();

            entity.setCategory(categoryRepository.findById(1L).get());

            Partner newEntity = partnerRepository.save(entity);

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
            Optional<Partner> selectedEntity = partnerRepository.findById(id);
            assertNotNull(selectedEntity.get());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void update() {

        try {

            Optional<Partner> maxRow = partnerRepository.getFirstByOrderByIdDesc();

            Partner entity = maxRow.get();

            maxRow.ifPresent(perform -> {
                entity.setName("Test Update")
                        .setUpdatedAt(LocalDateTime.now());
            });

            Partner newEntity = partnerRepository.save(entity);

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
        Optional<Long> before = partnerRepository.countBy();
        Long beforeCount = before.get();

        try {
            Optional<Partner> maxRow = partnerRepository.getFirstByOrderByIdDesc();

            partnerRepository.delete(maxRow.get());

            Optional<Long> after = partnerRepository.countBy();
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