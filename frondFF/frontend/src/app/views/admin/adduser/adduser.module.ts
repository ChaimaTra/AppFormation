import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdduserRoutingModule } from './adduser-routing.module';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AdduserComponent } from './adduser.component';



@NgModule({
  declarations: [  
 
  
    AdduserComponent
  ],
  imports: [
    
    CommonModule,
    AdduserRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,

    
    
 

  ]
})
export class AdduserModule { }
