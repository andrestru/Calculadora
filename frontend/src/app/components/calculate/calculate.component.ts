import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ReportserviceService } from 'src/app/service/reportservice.service';
import { report } from 'src/app/model/reporte';

@Component({
  selector: 'app-calculate',
  templateUrl: './calculate.component.html',
  styleUrls: ['./calculate.component.css']
})
export class CalculateComponent implements OnInit {
  public calcul!: FormGroup;
  cal:Array<any>=[]
  constructor(private router:Router, private service:ReportserviceService) { }

  ngOnInit(): void {
    this.calcul = this.calculateForm()
  }

  calculateForm(){
    return new FormGroup({
      identification: new FormControl('', Validators.required),
      weeks: new FormControl(null,Validators.required)
    })
  }

  public listado:any=[];
  load:boolean = false;
  calculate(){
    let i = 0;
    let listado2:Array<any> = []
    this.service.getById(`${this.calcul.value.identification}`).subscribe(data=>{
      this.cal = Object.values(data)
      
      const a: Array<any> = Array.from(this.cal[0]) 
        listado2.push(`${this.calcul.value.weeks}`)
        for(i; i<a.length; i++){ 
          listado2.push(a[i].startDate)
          listado2.push(a[i].finalDate)
        }
        this.service.prueba(listado2).subscribe(data=>{
          this.load = true
          this.listado = data
        })
    })
  }

  goback(){
    this.router.navigate([''])
  }


  validate(data:any){
    if(data==null || data == [] || data == ''){

    }
  }

}
