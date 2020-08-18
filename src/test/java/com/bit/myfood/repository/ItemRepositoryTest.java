package com.bit.myfood.repository;

import com.bit.myfood.ApplicationTests;
import com.bit.myfood.model.entity.AdminUser;
import com.bit.myfood.model.entity.CookRcp;
import com.bit.myfood.model.entity.Item;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ItemRepositoryTest extends ApplicationTests {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PartnerRepository partnerRepository;

    // region CRUD
    @Test
    @Transactional
    void create() {

        try {
            Item entity = Item.builder()
                    .name("Test Name")
                    .title("Test Title")
                    .content("Test Content")
                    .price(BigDecimal.valueOf(1000))
                    .createdAt(LocalDateTime.now())
                    .createdBy("admin")
                    .build();

            entity.setPartner(partnerRepository.findById(1L).get());

            Item newEntity = itemRepository.save(entity);

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
            Optional<Item> selectedEntity = itemRepository.findById(id);
            assertNotNull(selectedEntity.get());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    void update() {

        try {

            Optional<Item> maxRow = itemRepository.getFirstByOrderByIdDesc();

            Item entity = maxRow.get();

            maxRow.ifPresent(perform -> {
                entity.setName("Test Update");
                entity.setUpdatedAt(LocalDateTime.now());
            });

            Item newEntity = itemRepository.save(entity);

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
        Optional<Long> before = itemRepository.countBy();
        Long beforeCount = before.get();

        try {
            Optional<Item> maxRow = itemRepository.getFirstByOrderByIdDesc();

            itemRepository.delete(maxRow.get());

            Optional<Long> after = itemRepository.countBy();
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