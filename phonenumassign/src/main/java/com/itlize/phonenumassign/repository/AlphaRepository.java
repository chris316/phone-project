package com.itlize.phonenumassign.repository;

import com.itlize.phonenumassign.entity.AlphaEntity;
import com.itlize.phonenumassign.entity.PhoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.parser.Entity;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface AlphaRepository extends JpaRepository<AlphaEntity,Integer> {
    Page<AlphaEntity> findAllByNumber(PhoneEntity phoneEntity, Pageable pageable);
}
