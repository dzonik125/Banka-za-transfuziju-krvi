import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterUserComponent } from './register-user/register-user.component';
import { AppComponent } from './app.component';
import { HomeComponentComponent } from './home-component/home-component.component';

const routes: Routes = [
  {path: '', component: HomeComponentComponent},
  {path :'register', component: RegisterUserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
