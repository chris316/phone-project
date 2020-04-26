package com.itlize.phonenumassign.ServiceTests;

import com.itlize.phonenumassign.entity.*;
import com.itlize.phonenumassign.repository.PhoneRepository;
import com.itlize.phonenumassign.service.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneServiceTests {

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    PhoneService phoneService;


    @Test
    public void calculate_total_test(){
        //given
        String number="1234567890";
        int actual=102400;

        //when
        int received=phoneService.calculateTotal(number);

        //then
        Assert.assertEquals(actual,received);
    }

    @Test
    public void calculate_total_test1(){
        //given
        String number="8273468127";
        int actual=409600;

        //when
        int received=phoneService.calculateTotal(number);

        //then
        Assert.assertEquals(actual,received);
    }

    @Test
    public void calculate_total_test2(){
        //given
        String number="1498650";
        int actual=1280;

        //when
        int received=phoneService.calculateTotal(number);

        //then
        Assert.assertEquals(actual,received);
    }

        /*@Test
    public void createAlphas(){
        //given
        String number="1234567890";
        PhoneEntity phoneEntity=new PhoneEntity(number);
        boolean isTrue=phoneService.insertNumber(phoneEntity);
        int actual=102400;

        //when
        List<String> received=phoneService.addAlphaNums(number);
        int sizeReceived=received.size();

        //then
        Assert.assertEquals(actual,sizeReceived);
    }*/


    @Test
    public void clearRepos(){
        //given
        boolean actual=true;

        //when
        boolean received=phoneService.deleteAll();

        //then
        Assert.assertEquals(actual,received);

    }

    @Test
    public void createAlphas1(){
        //given
        String number="1498650";
        PhoneEntity phoneEntity=new PhoneEntity(number);
        boolean isTrue=phoneService.insertNumber(phoneEntity);
        int actual=1280;

        //when
        List<String> received=phoneService.addAlphaNums(number);
        int sizeReceived=received.size();

        //then
        Assert.assertEquals(actual,sizeReceived);
    }

    @Test
    public void getNumAlphaPages(){
        //given
        String number="1498650";
        Integer actual=13;

        //when
        Integer received=phoneService.getNumAlphaPages(number);

        //Then
        Assert.assertEquals(actual,received);
    }

    @Test
    public void getAlphas1(){
        //given
        String number="1498650";
        int actual=100;

        //when
        List<AlphaEntity> received=phoneService.getAlphaNums(number,0);

        //Then
        Assert.assertEquals(actual,received.size());
    }

    @Test
    public void getAlphas2(){
        //given
        String number="1498650";
        int actual=80;

        //when
        List<AlphaEntity> received=phoneService.getAlphaNums(number,12);

        //then
        Assert.assertEquals(actual,received.size());
    }
}
