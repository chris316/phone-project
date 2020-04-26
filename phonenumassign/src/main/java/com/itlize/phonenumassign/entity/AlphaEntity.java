package com.itlize.phonenumassign.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="phone_alpha")
public class AlphaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name="alphaNumber")
    private String alphaNumber;

    @ManyToOne(targetEntity = PhoneEntity.class,cascade = CascadeType.DETACH)
    private PhoneEntity number;

    public AlphaEntity() {
    }

    public AlphaEntity(String alphaNumber, PhoneEntity phoneEntity)
    {
        this.alphaNumber=alphaNumber;
        this.number=phoneEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlphaNumber() {
        return alphaNumber;
    }

    public void setAlphaNumber(String alphaNumber) {
        this.alphaNumber = alphaNumber;
    }

    @Override
    public String toString() {
        return alphaNumber;
    }

    public PhoneEntity getNumber() {
        return number;
    }

    public void setNumber(PhoneEntity number) {
        this.number = number;
    }
}
