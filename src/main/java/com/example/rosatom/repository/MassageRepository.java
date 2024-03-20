package com.example.rosatom.repository;

import com.example.rosatom.entity.Massage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MassageRepository extends CrudRepository<Massage,Long> {
}
