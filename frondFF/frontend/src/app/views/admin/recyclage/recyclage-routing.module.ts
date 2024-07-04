import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecyclageComponent } from './recyclage.component';

const routes: Routes = [
  {path: '' , component:RecyclageComponent}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RecyclageRoutingModule { }
