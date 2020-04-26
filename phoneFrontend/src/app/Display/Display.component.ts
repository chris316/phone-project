import { Component, Injectable } from '@angular/core';
import {NgForm} from '@angular/forms';

import { NumberService } from '../number-service';
import { NumberData } from "../Number-data.model";

@Component({
    selector: 'app-display',
    templateUrl: './Display.component.html',
    styleUrls: ['./Display.component.css']
  })
  export class DisplayComponent {
    inputNumber:string;
    NumberArray:Array<NumberData>=[];
    NumbersArray:Array<number>=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30];
    linesArray:Array<number>=[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20];
    totalCombos:number;
    totalPages:number;
    currentPage:number;
    pagesArray:Array<number>=[];

    constructor(public numberService:NumberService){
        console.log("in the constructor for display");
        this.currentPage=1;
        this.inputNumber=numberService.getCurrentNum();
        this.totalCombos=numberService.getTotalCombos();
        this.totalPages=numberService.getTotalPages();
        this.NumberArray=numberService.getTotalAlphas();
        for(let i=0;i<this.totalPages;i++)
        {
            this.pagesArray.push(i+1);
        }
        console.log("current pages array: "+this.pagesArray);
      }

    back(){
        console.log("Going back to generate another number");
        this.numberService.goToHome();
    }

    goToPage(page:number){
        console.log("Going to page "+page);
        this.currentPage=page;
        this.numberService.getAlphas(this.inputNumber,page);
        setTimeout(()=>{
            this.NumberArray=this.numberService.getTotalAlphas();
        },1500)
    }






  }