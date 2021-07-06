import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { report } from 'src/app/model/reporte';
import { ReportserviceService } from 'src/app/service/reportservice.service';


@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent implements OnInit {

  constructor(private service:ReportserviceService, private router:Router) { }

  public add!: FormGroup;
  ngOnInit(): void {
    this.add = this.createForm()
  }

  createForm(){
    return new FormGroup({
      identificationT: new FormControl('', Validators.required),
      identificationS: new FormControl('', Validators.required),
      startDate: new FormControl(null,Validators.required),
      finalDate: new FormControl(null,Validators.required)
     })
  }

  addReport(){
    let Report = new report();
    Report.identificationT = `${this.add.value.identificationT}`;
    Report.identificationS = `${this.add.value.identificationS}`;
    Report.startDate = `${this.add.value.startDate}`+":00";
    Report.finalDate = `${this.add.value.finalDate}`+":00";
    if(`${this.add.value.finalDate}`+":00"<`${this.add.value.startDate}`+":00"){
      alert("Fecha Final NO puede ser inferior a la inicial!!!")
    }else{
    console.log(Report)
    this.service.saveReport(Report).subscribe(()=>{
      alert("Servicio almacenado con exito...")
      this.router.navigate([''])
    })
    }
  }

  goback(){
    this.router.navigate([''])
  }

}
