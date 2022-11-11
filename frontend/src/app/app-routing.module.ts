import { CreateSurveyComponent } from './modules/public/create-survey/create-survey.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterUserComponent } from './modules/public/register-user/register-user.component';
import { AppComponent } from './app.component';
import { HomeComponentComponent } from './home-component/home-component.component';
import { SaveApiKeyComponent } from './save-api-key/save-api-key.component';
import { AdminDashboardComponent } from './modules/administrator/admin-dashboard/admin-dashboard.component';
import { SendNewsComponent } from './modules/administrator/send-news/send-news.component';
import { UserProfileViewComponent } from './modules/public/user-profile-view/user-profile-view.component';
import { DisplayAllCentersComponent } from './modules/public/display-all-centers/display-all-centers.component';
import { RegisterBloodBankComponent } from './modules/administrator/register-blood-bank/register-blood-bank.component';
import { RegisterMedicalWorkerComponent } from './modules/administrator/register-medical-worker/register-medical-worker.component';

const routes: Routes = [
  {path: '', component: HomeComponentComponent},
  {path :'register', component: RegisterUserComponent},
  {path:'saveApi/:id', component: SaveApiKeyComponent},
  {path:'sendNews', component: SendNewsComponent},
  {path: 'userProfile/:id', component: UserProfileViewComponent},
  {path:'adminDashboard/registerBloodBank', component: RegisterBloodBankComponent},
  {path:'adminDashboard/registerMedicalWorker', component: RegisterMedicalWorkerComponent},
  {path: 'homePage', component: DisplayAllCentersComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
