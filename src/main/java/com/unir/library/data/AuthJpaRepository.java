package com.unir.library.data;

import com.unir.library.model.pojo.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

interface AuthJpaRepository extends JpaRepository<Auth, Long>, JpaSpecificationExecutor<Auth> {

    List<Auth> findByName(String name);

    List<Auth> findByLastname(String lastname);

}