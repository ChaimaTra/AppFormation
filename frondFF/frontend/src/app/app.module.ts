import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FrontLayoutComponent } from './layout/front-layout/front-layout.component';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { AdminLayoutComponent } from './layout/admin-layout/admin-layout.component';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { AdminCalendarModule } from './views/admin/admin-calendar/admin-calendar.module';
import { UserLayoutComponent } from './layout/user-layout/user-layout.component';
import { ReportListComponent } from './views/front/report-list/report-list.component';
import { HttpInterceptor } from './views/services/http.interceptor.service';
import { BaseURL } from './shared/baseurl';


@NgModule({
  declarations: [
    AppComponent,
    FrontLayoutComponent,
    AdminLayoutComponent,
    UserLayoutComponent,
    ReportListComponent,
   

   
   
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    
   
    
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory }),
    AdminCalendarModule

  ],
  providers: [ { provide: HTTP_INTERCEPTORS, useClass: HttpInterceptor, multi: true },
    { provide: 'BaseURL', useValue: BaseURL}],
  bootstrap: [AppComponent]
})
export class AppModule { }

