import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RecyclageRoutingModule } from './recyclage-routing.module';
import { RecyclageComponent } from './recyclage.component';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';


@NgModule({
  declarations: [
    RecyclageComponent
  ],
  imports: [
    CommonModule,
    RecyclageRoutingModule,
    FormsModule,
    ReactiveFormsModule
  
  ]
})
export class RecyclageModule { }
