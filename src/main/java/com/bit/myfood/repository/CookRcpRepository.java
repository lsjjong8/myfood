package com.bit.myfood.repository;


import com.bit.myfood.model.entity.CookRcp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CookRcpRepository extends JpaRepository<CookRcp, Long> {

    Optional<CookRcp> findById(Long id);

    Optional<CookRcp> getFirstByOrderByIdDesc();

    Optional<Long> countBy();

}
