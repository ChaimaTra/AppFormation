import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SignalementRoutingModule } from './signalement-routing.module';
import { SignalementComponent } from './signalement.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    SignalementComponent
  ],
  imports: [
    CommonModule,
    SignalementRoutingModule,
    FormsModule,
    HttpClientModule
  ]
})
export class SignalementModule { }
