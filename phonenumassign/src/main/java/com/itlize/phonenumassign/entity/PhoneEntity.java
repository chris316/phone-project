package com.itlize.phonenumassign.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name="phones")
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private int id;

    @Column(name="phone_number")
    private String phoneNumber;

    @JsonIgnore
    @OneToMany(targetEntity = AlphaEntity.class,cascade = CascadeType.REMOVE,mappedBy = "number")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<AlphaEntity> entries = new ArrayList<AlphaEntity>();

    public PhoneEntity() {
    }

    public PhoneEntity(String phoneNumber)
    {
        this.phoneNumber=phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<AlphaEntity> getEntries() {
        return entries;
    }

    public void setEntries(List<AlphaEntity> entries) {
        this.entries = entries;
    }
}
