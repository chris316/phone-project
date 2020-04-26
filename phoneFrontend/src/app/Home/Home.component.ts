import { Component, Injectable } from '@angular/core';
import {NgForm} from '@angular/forms';

import { NumberService } from '../number-service';

@Component({
    selector: 'app-home',
    templateUrl: './Home.component.html',
    styleUrls: ['./Home.component.css']
  })
  export class HomeComponent {

    number:string;
    submitted = false;
    generating=false;
    correctNum=false;
    succeeded:boolean; 
    totalCombos:number=0;
    
    constructor(public numberService:NumberService){
        this.submitted=false;
        this.generating=false;
        this.number='';
      }

      onSubmit(form: NgForm) { 
        if(form.invalid)
        {
          return;
        }   
        console.log("submitted.  Generating numbers for: "+this.number);
        this.submitted=true;
        this.generating=true;
        this.correctNum=false;
        this.numberService.calculateTotal(this.number);
        setTimeout(()=>{
            this.totalCombos=this.numberService.getTotalCombos();
            console.log("total combos: "+this.totalCombos);
            if(this.totalCombos==0)
            {
                this.correctNum=true;
                this.generating=false;
                form.reset();
            }
            else
            form.reset();
        },1300)
        


        
      }

  }