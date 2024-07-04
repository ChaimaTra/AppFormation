import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CollecteRoutingModule } from './collecte-routing.module';
import { CollecteComponent } from './collecte.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FullCalendarModule } from '@fullcalendar/angular';


@NgModule({
  declarations: [
    CollecteComponent
  ],
  imports: [
    CommonModule,
    CollecteRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    FullCalendarModule,
    

  ]
})
export class CollecteModule { }
