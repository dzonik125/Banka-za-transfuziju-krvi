import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterUserComponent } from './modules/public/register-user/register-user.component';
import { AppComponent } from './app.component';
import { HomeComponentComponent } from './home-component/home-component.component';
import { SaveApiKeyComponent } from './save-api-key/save-api-key.component';
import { AdminDashboardComponent } from './modules/administrator/admin-dashboard/admin-dashboard.component';

import { UserProfileViewComponent } from './modules/public/user-profile-view/user-profile-view.component';

const routes: Routes = [
  {path: '', component: HomeComponentComponent},
  {path :'register', component: RegisterUserComponent},
  {path:'saveApi/:id', component: SaveApiKeyComponent},
  {path: 'userProfile/:id', component: UserProfileViewComponent},
  {path:'adminDashboard', component: AdminDashboardComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
