package com.thanhle.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thanhle.domain.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer>{
    public Trainer findTrainerByTrainerName(String name);
}
