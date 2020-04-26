package com.itlize.phonenumassign.service.impl;


import com.itlize.phonenumassign.entity.AlphaEntity;
import com.itlize.phonenumassign.entity.PhoneEntity;
import com.itlize.phonenumassign.repository.AlphaRepository;
import com.itlize.phonenumassign.repository.PhoneRepository;
import com.itlize.phonenumassign.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.*;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private AlphaRepository alphaRepository;

    @Autowired
    private PhoneService phoneService;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    public boolean insertNumber(PhoneEntity phoneEntity)
    {
        String number=phoneEntity.getPhoneNumber();
        System.out.println(number.length());
        if(number.length()==7||number.length()==10)
        {
            System.out.println("number fits the length parameters");
            if(isNumeric(number))
            {
                System.out.println("number is numeric");
                phoneRepository.save(phoneEntity);
                return true;
            }
            else {
                System.out.println("number is not numeric");
                return false;
            }

        }
        else
        {
            System.out.println("number doesn't fit length parameters");
            return false;
        }
    }

    public PhoneEntity get(String number)
    {
        return phoneRepository.findByPhoneNumber(number);
    }

    public PhoneEntity get(Integer id)
    {
        Optional<PhoneEntity> number=phoneRepository.findById(id);
        return number.get();
    }

    public List<PhoneEntity> getAll()
    {
        List<PhoneEntity> numbers=phoneRepository.findAll();
        return numbers;
    }

    public boolean deleteNumber(PhoneEntity phoneEntity)
    {
        if(phoneEntity==null)
        {
            System.out.println("number not found");
        }
        try{
            phoneRepository.delete(phoneEntity);
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
    }
        return true;
    }

    public Integer calculateTotal(String number)
    {
        int length=number.length();
        int total=1;
        for(int i=0;i<length;i++)
        {
            if(number.charAt(i)=='0'||number.charAt(i)=='1') {
                total *= 1;
            }
            else if(number.charAt(i)=='2'||number.charAt(i)=='3'||number.charAt(i)=='4'||number.charAt(i)=='5'||number.charAt(i)=='6'||number.charAt(i)=='8') {
                total *= 4;
            }
            else if(number.charAt(i)=='7'||number.charAt(i)=='9') {
                total *= 5;
            }
        }
        return total;
    }

    public boolean insertAlpha(String alpha,PhoneEntity phoneEntity)
    {
        AlphaEntity alphaEntity=new AlphaEntity(alpha,phoneEntity);
        alphaRepository.save(alphaEntity);
        return true;
    }

    public boolean deleteAll()
    {
        phoneRepository.deleteAll();
        alphaRepository.deleteAll();
        return true;
    }

    @Transactional
    public List<String> addAlphaNums(String number)
    {
        PhoneEntity phoneEntity=phoneRepository.findByPhoneNumber(number);
        String digits=phoneEntity.getPhoneNumber();
        List<String> ans = new ArrayList<String>();
        List<AlphaEntity>alphas=new ArrayList<AlphaEntity>();
        if(digits==null||digits.length()==0) return ans;
        char[][] map = new char[10][];
        map[0]="0".toCharArray();
        map[1]="1".toCharArray();
        map[2]="2ABC".toCharArray();
        map[3]="3DEF".toCharArray();
        map[4]="4GHI".toCharArray();
        map[5]="5JKL".toCharArray();
        map[6]="6MNO".toCharArray();
        map[7]="7PQRS".toCharArray();
        map[8]="8TUV".toCharArray();
        map[9]="9WXYZ".toCharArray();

        char[] input = digits.toCharArray();
        ans.add("");
        for(char c:input) {
            ans = expand(ans, map[c-'0']);
        }
        for(int i=0;i<ans.size();i++)
        {
            AlphaEntity alpha=new AlphaEntity(ans.get(i),phoneEntity);
            alphas.add(alpha);
            if(i%batchSize==0&&i>0)
            {
                alphaRepository.saveAll(alphas);
                alphaRepository.flush();
                alphas.clear();
            }

        }
        if(alphas.size()>0)
        {
            System.out.println("saving the rest");
            alphaRepository.saveAll(alphas);
            alphaRepository.flush();
            alphas.clear();
        }
        return ans;
    }

    private List<String> expand(List<String> l,char[] arr)
    {
        List<String> next = new ArrayList<String>();
        for(String s:l) {
            for (char c : arr) {
                next.add(s + c);
            }
        }
        return next;
    }

    public List<AlphaEntity> getAlphaNums(String number,Integer page)
    {
        PhoneEntity phoneEntity=phoneRepository.findByPhoneNumber(number);
        org.springframework.data.domain.Pageable PageWith500Requests=PageRequest.of(page,500);
        Page<AlphaEntity> pages= alphaRepository.findAllByNumber(phoneEntity,PageWith500Requests);
        return pages.getContent();
    }

    public Integer getNumAlphaPages(String number)
    {
        PhoneEntity phoneEntity=phoneRepository.findByPhoneNumber(number);
        org.springframework.data.domain.Pageable pageWith500Requests=PageRequest.of(0,500);
        Page<AlphaEntity> alphaPage=alphaRepository.findAllByNumber(phoneEntity,pageWith500Requests);
        return alphaPage.getTotalPages();
    }


}
