import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignalementComponent } from './signalement.component';

const routes: Routes = [
  {path: '' , component:SignalementComponent},
  { path: '', redirectTo: '/signalements', pathMatch: 'full' }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SignalementRoutingModule { }
