import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { BigNumber } from 'bignumber.js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  //styleUrls: ['./app.component.css']
})
export class AppComponent {
  valueN?: number; //value passed in the input box of the UI
  result?: string; //result of the labsec funtion showed in the UI

  constructor(private http: HttpClient) { }

  /*
  Funtion used to make the Rest request to the backend
  The valueN can only be a number
  The function catches the 400 bad request exceptions
  from the backend.
   */
  getResult(value: number | undefined) {
    if (value!=undefined) {
      fetch(`http://localhost:8080/labseq/${this.valueN}`)
        .then(response => {
          if (!response.ok) {
            throw response.status
          }
          return response.text();
        })
        .then(result => {
          if (result !== undefined) {
            this.result = "The result is:"+ result;
          }
        })
        .catch(error => {
          if (error == 400){
            this.result = "Error: n value need to be an Integer >= 0";
          }
        });
    }else{
      this.result = "The n value need to be selected.";
    }
  }
}
