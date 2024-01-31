package com.unir.library.data;

import com.unir.library.model.pojo.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

interface GenderJpaRepository extends JpaRepository<Gender, Long>, JpaSpecificationExecutor<Gender> {

    List<Gender> findByDescription(String description);

}