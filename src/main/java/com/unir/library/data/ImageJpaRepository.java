package com.unir.library.data;

import com.unir.library.model.pojo.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

interface ImageJpaRepository extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {

    List<Image> findByTitle(String path);

}