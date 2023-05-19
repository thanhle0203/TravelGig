package com.thanhle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.thanhle.domain.Insurer;


@Repository
@CrossOrigin(origins = "http://localhost:8282")
public interface InsurerRepository extends JpaRepository<Insurer, Long> {

}
