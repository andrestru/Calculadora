import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { report } from '../model/reporte';

@Injectable({
  providedIn: 'root'
})
export class ReportserviceService {

  constructor(private http:HttpClient) { }

  url = "http://localhost:8080/report"

  saveReport(Report:report){
    return this.http.post(this.url, Report)
  }

  getById(id:string){
    var json = JSON.parse(`{"identificationT": "${id}"}`)
    return this.http.post(this.url+"/listid/",json)
  }

  prueba(tec:any){
    return this.http.post(this.url+"/calculo/"+`${tec}`,"")
  }

}
