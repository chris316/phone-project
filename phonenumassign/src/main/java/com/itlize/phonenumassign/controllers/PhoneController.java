package com.itlize.phonenumassign.controllers;


import com.itlize.phonenumassign.entity.AlphaEntity;
import com.itlize.phonenumassign.entity.PhoneEntity;
import com.itlize.phonenumassign.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phone")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @GetMapping("list")
    public List<PhoneEntity> listNumbers(){
        return phoneService.getAll();
    }

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/calculate")
    public Integer calculateTotal(@RequestParam(name="number")String number)
    {
        PhoneEntity phone=new PhoneEntity();
        phone.setPhoneNumber(number);
        boolean isSuccessful=phoneService.insertNumber(phone);
        if(isSuccessful)
        {
            System.out.println("was successful");
            return phoneService.calculateTotal(phone.getPhoneNumber());
        }
        else
            return 0;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @PostMapping("/addAlphas")
    public boolean addAlphas(@RequestParam(name="number")String number)
    {
        phoneService.addAlphaNums(number);
        return true;
    }

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/getPages")
    public Integer getPages(@RequestParam(name="number")String number){
        return phoneService.getNumAlphaPages(number);
    }

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/getAlphas")
    public List<AlphaEntity> getAlphas(@RequestParam(name="number")String number,@RequestParam(name="page")Integer page)
    {
        return phoneService.getAlphaNums(number,page);
    }

    @CrossOrigin(origins="http://localhost:4200")
    @GetMapping("/clearRepos")
    public boolean clearRepositories(){
        phoneService.deleteAll();
        return true;
    }




}
