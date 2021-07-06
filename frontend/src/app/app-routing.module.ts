import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CalculateComponent } from './components/calculate/calculate.component';
import { HomeComponent } from './components/home/home.component';
import { ReportComponent } from './components/report/report.component';

const routes: Routes = [{
  path: '', component: HomeComponent
},
{
  path: 'reportService', component: ReportComponent
},
{
  path: 'calculate', component: CalculateComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
