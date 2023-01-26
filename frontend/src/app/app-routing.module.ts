import { VisitHistoryComponent } from './modules/public/visit-history/visit-history.component';
import { ScheduledAppointmentsComponent } from './modules/public/scheduled-appointments/scheduled-appointments.component';
import { ScheduleExsistingAppointmentComponent } from './modules/public/schedule-exsisting-appointment/schedule-exsisting-appointment.component';
import { CreateSurveyComponent } from './modules/public/create-survey/create-survey.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterUserComponent } from './modules/authentication/register-user/register-user.component';
import { AppComponent } from './app.component';
import { HomeComponentComponent } from './home-component/home-component.component';
import { SaveApiKeyComponent } from './save-api-key/save-api-key.component';
import { AdminDashboardComponent } from './modules/administrator/admin-dashboard/admin-dashboard.component';
import { SendNewsComponent } from './modules/administrator/send-news/send-news.component';
import { UserProfileViewComponent } from './modules/public/user-profile-view/user-profile-view.component';
import { DisplayAllCentersComponent } from './modules/public/display-all-centers/display-all-centers.component';
import { RegisterBloodBankComponent } from './modules/administrator/register-blood-bank/register-blood-bank.component';
import { RegisterMedicalWorkerComponent } from './modules/administrator/register-medical-worker/register-medical-worker.component';
import { DisplayAllUsersComponent } from './modules/administrator/display-all-users/display-all-users.component';
import { BloodBankViewComponent } from './modules/medical_worker/blood-bank-view/blood-bank-view.component';
import { LoginComponent } from './modules/authentication/login/login/login.component';
import { AddAppointmentSlotComponent } from './modules/medical_worker/blood-bank-view/add-appointment-slot/add-appointment-slot.component';
import { AuthGuard } from './modules/authentication/helpers/auth.guard';
import { RoleGuard } from './modules/authentication/helpers/role.guard';
import { CalendarComponent } from './calendar/calendar.component';
import { RegisterAdministratorComponent } from './modules/administrator/register-administrator/register-administrator.component';
import { ChangePasswordComponent } from './modules/administrator/admin-dashboard/change-password/change-password.component';
import { AdminComplaintsComponent } from './modules/administrator/admin-complaints/admin-complaints.component';
import { ScheduleNewAppointmentComponent } from './modules/public/schedule-new-appointment/schedule-new-appointment.component';
import { AppointmentSlotByQrCodeComponent } from './modules/administrator/appointment-slot-by-qr-code/appointment-slot-by-qr-code.component';
import { MedicalExaminationComponent } from './modules/administrator/medical-examination/medical-examination.component';

const routes: Routes = [
  {path: 'home', component: HomeComponentComponent},
  {path :'register', component: RegisterUserComponent},
  {path: 'login', component: LoginComponent},
  {path:'saveApi/:id', component: SaveApiKeyComponent},
  {path:'sendNews/:id', component: SendNewsComponent},
  {path: 'userProfile', component: UserProfileViewComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_MEDICALWORKER', 'ROLE_DONOR', 'ROLE_ADMIN'] }},
  {path:'registerBloodBank', component: RegisterBloodBankComponent},
  {path:'adminDashboard/calendar/:id', component: CalendarComponent},
  {path:'registerMedicalWorker', component: RegisterMedicalWorkerComponent},
  {path:'addAppointmentSlot', component: AddAppointmentSlotComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_MEDICALWORKER'] }},
  {path:'registerAdministrator', component: RegisterAdministratorComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_ADMIN'] }},
  {path:'adminDashboard/complaints', component: AdminComplaintsComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_ADMIN'] }},
  {path:'addAppointmentSlot', component: AddAppointmentSlotComponent},
  {path: 'displayAllUsers', component: DisplayAllUsersComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_ADMIN'] }},
  {path: '', component: DisplayAllCentersComponent},
  {path: 'bloodBank/:id', component: BloodBankViewComponent},
  {path: 'changepassword', component: ChangePasswordComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_NEW_ADMIN'] }},
  {path :'survey', component: CreateSurveyComponent},
  {path :'getAll', component: ScheduleExsistingAppointmentComponent},
  {path :'survey', component: CreateSurveyComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_DONOR'] }},
  {path :'schedule', component: ScheduleExsistingAppointmentComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_DONOR'] }},
  {path :'scheduledAppointments', component: ScheduledAppointmentsComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_DONOR'] }},
  {path: 'scheduleNewAppointment', component: ScheduleNewAppointmentComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_DONOR'] }},
  {path: 'appointmentSlotByQrCode', component: AppointmentSlotByQrCodeComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_ADMIN'] }},
  {path: 'medicalExamination', component: MedicalExaminationComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_ADMIN'] }},
  {path: 'visitHistory', component: VisitHistoryComponent, canActivate: [AuthGuard, RoleGuard], data: { roles: ['ROLE_DONOR'] }}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
