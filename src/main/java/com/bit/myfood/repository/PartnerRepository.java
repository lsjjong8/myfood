package com.bit.myfood.repository;

import com.bit.myfood.model.entity.Category;
import com.bit.myfood.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findById(Long id);

    Optional<Partner> getFirstByOrderByIdDesc();

    Optional<Long> countBy();

    List<Partner> findByCategory(Category category);
}
