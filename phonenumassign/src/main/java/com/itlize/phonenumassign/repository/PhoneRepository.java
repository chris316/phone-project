package com.itlize.phonenumassign.repository;

import com.itlize.phonenumassign.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneEntity,Integer> {
    public PhoneEntity findByPhoneNumber(String number);

}
