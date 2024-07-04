import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CollecteComponent } from './collecte.component';

const routes: Routes = [
  {path: '' , component:CollecteComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CollecteRoutingModule { }
