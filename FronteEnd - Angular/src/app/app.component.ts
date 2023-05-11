import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { BigNumber } from 'bignumber.js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  //styleUrls: ['./app.component.css']
})
export class AppComponent {
  valueN?: number;
  result?: string;

  constructor(private http: HttpClient) { }

  getResult(value: number | undefined) {
    if (value!=undefined && value >0) {

      fetch(`http://localhost:8080/labseq/${this.valueN}`)
        .then(response => response.text())
        .then(result => {
          console.log(result); // "9062959782884117635"
          if (result !== undefined) {
            this.result = "The result is:"+ result;
          }
        });
    }else{
      this.result = "The n value need to be selected. And it needs to be >0.";
    }
  }
}
