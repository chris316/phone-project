import { Injectable} from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { Router } from "@angular/router";
import { Subject, Observable } from "rxjs";

import { NumberData } from "./Number-data.model";

const BACKEND_URL = "http://localhost:8080/" + "/phone";


@Injectable({providedIn: "root"})
export class NumberService{
    currentNum:string='';
    totalCombos:number=0;
    totalPages:number=0;
    alphaArray:Array<NumberData>=[];



    constructor(private http: HttpClient, private router:Router){}

    goToDetails(){
        this.router.navigate(['display']);
    }

    goToHome(){
        this.clearDatabase();
        this.router.navigate(['home']);
    }

    getCurrentNum(){
        return this.currentNum;
    }

    calculateTotal(passNum:string){
        console.log("inserting "+passNum+" to the database, and finding total combos");
        this.currentNum=passNum;
        let params=new HttpParams().set('number',passNum);
        console.log("adding: "+params.toString());
        this.http.get<number>(BACKEND_URL+"/calculate",{params})
        .subscribe(
            response=>{
                console.log("got a response");
                this.totalCombos=response;
                if(this.totalCombos==0)
                {
                    console.log("The number entered was not correct");
                }
                else{
                    console.log("adding the alphas");
                    this.addAlphas(passNum);
                }
            },
            error=>{
                console.log("error connecting");
            }
        )
    }

     getTotalCombos(){
        return this.totalCombos;
    }



    addAlphas(passNum:string){
        const promise=new Promise((resolve,reject)=>{
            this.http.post<boolean>(BACKEND_URL+"/addAlphas?number="+passNum,null)
            .toPromise()
            .then((res)=>{
                console.log("added all of the alphas!");
                this.getPages(passNum);
                resolve();
            },
            err=>{
                console.log("didn't add alphas");
                reject(err);
            }
            );
        });
        return promise;
        }


    getPages(passNum:string){
        const promise=new Promise((resolve,reject)=>{
            let params=new HttpParams().set('number',passNum);
            this.http.get<number>(BACKEND_URL+"/getPages",{params})
            .toPromise()
            .then((res)=>{
                console.log("got the total pages: "+res);
                this.totalPages=res;
                this.getAlphas(passNum,1);
                resolve();
            },
            err=>{
                console.log("didn't get the pages");
                reject(err);
            });
        });
        return promise;
    }

    getTotalPages(){
        return this.totalPages;
    }


    getAlphas(passNum:string, passPage:number){
        const promise=new Promise((resolve,reject)=>{
            let params=new HttpParams().set('number',passNum).set('page',(passPage-1).toString());
            this.alphaArray=[];
            this.http.get<Array<NumberData>>(BACKEND_URL+"/getAlphas",{params})
            .toPromise()
            .then((res)=>{
                for(let numData of res)
                {
                    this.alphaArray.push(numData);
                }
                this.goToDetails();
                resolve();
            },
            err=>{
                console.log("didn't get the alphas");
                reject(err);
            });
        });
        return promise;
    }

    getTotalAlphas(){
        return this.alphaArray;
    }

    clearDatabase(){
        const promise=new Promise((resolve,reject)=>{
            this.http.get<boolean>(BACKEND_URL+"/clearRepos")
            .toPromise()
            .then((res)=>{
                console.log("cleared the repositories");
                resolve();
            },
            err=>{
                reject(err);
            });
        });
        return promise;
    }






}