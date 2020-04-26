package com.itlize.phonenumassign.service;

import com.itlize.phonenumassign.entity.AlphaEntity;
import com.itlize.phonenumassign.entity.PhoneEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PhoneService {
    public boolean insertNumber(PhoneEntity phoneEntity);
    public PhoneEntity get(String number);
    public PhoneEntity get(Integer id);
    public List<PhoneEntity> getAll();
    public boolean deleteNumber(PhoneEntity phoneEntity);
    public boolean insertAlpha(String alpha, PhoneEntity phoneEntity);
    public Integer calculateTotal(String number);
    public List<String> addAlphaNums(String number);
    public List<AlphaEntity> getAlphaNums(String number, Integer page);
    public Integer getNumAlphaPages(String number);
    public boolean deleteAll();

}
