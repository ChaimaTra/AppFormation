import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FrontLayoutComponent } from './layout/front-layout/front-layout.component';
import { AdminLayoutComponent } from './layout/admin-layout/admin-layout.component';
import { UserLayoutComponent } from './layout/user-layout/user-layout.component';


const routes: Routes = [

    {path:'',component:FrontLayoutComponent,children:[
      
          {path:'',loadChildren:()=>import('./views/front/home/home.module').then(m=>m.HomeModule)},
          {path:'login',loadChildren:()=>import('./views/front/login/login.module').then(m=>m.LoginModule)},
          {path:'register',loadChildren:()=>import('./views/front/register/register.module').then(m=>m.RegisterModule)},
        



        
        ]},
    
        {path:'',component:AdminLayoutComponent,children:[
          {path:'statistique',loadChildren:()=>import('./views/admin/statistique/statistique.module').then(m=>m.StatistiqueModule)},

          {path:'add',loadChildren:()=>import('./views/admin/adduser/adduser.module').then(m=>m.AdduserModule)},
          {path:'admin-calendar',loadChildren:()=>import('./views/admin/admin-calendar/admin-calendar.module').then(m=>m.AdminCalendarModule)},
          {path:'signalement',loadChildren:()=>import('./views/admin/signalement/signalement.module').then(m=>m.SignalementModule)},
          {path:'recyclage',loadChildren:()=>import('./views/admin/recyclage/recyclage.module').then(m=>m.RecyclageModule)},
          {path:'users',loadChildren:()=>import('./views/admin/users/users.module').then(m=>m.UsersModule)},




         
          
        
        ]},

        {path:'',component:UserLayoutComponent,children:[
          {path:'',loadChildren:()=>import('./views/front/profile/profile.module').then(m=>m.ProfileModule)},
          {path:'profile',loadChildren:()=>import('./views/front/profile/profile.module').then(m=>m.ProfileModule)},
          {path:'report',loadChildren:()=>import('./views/front/report/report.module').then(m=>m.ReportModule)},
          {path:'report-list',loadChildren:()=>import('./views/front/report-list/report-list.module').then(m=>m.ReportListModule)},
          {path:'collecte',loadChildren:()=>import('./views/front/collecte/collecte.module').then(m=>m.CollecteModule)},
          {path:'articles',loadChildren:()=>import('./views/front/articles/articles.module').then(m=>m.ArticlesModule)},









         
          
        
        ]},

       
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
