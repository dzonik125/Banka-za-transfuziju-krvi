import { UserService } from 'src/app/services/user.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Router, RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './modules/public/navigation/navigation.component';
import { RegisterUserComponent } from './modules/authentication/register-user/register-user.component';
import { HomeComponentComponent } from './home-component/home-component.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SaveApiKeyComponent } from './save-api-key/save-api-key.component';
import { AdminDashboardComponent } from './modules/administrator/admin-dashboard/admin-dashboard.component';
import { RegisterBloodBankComponent } from './modules/administrator/register-blood-bank/register-blood-bank.component';
import { SendNewsComponent } from './modules/administrator/send-news/send-news.component';
import { DisplayAllCentersComponent } from './modules/public/display-all-centers/display-all-centers.component';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatIconModule } from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatRadioModule } from '@angular/material/radio';
import { FileUploadModule } from "ng2-file-upload";
import { provideFirestore, getFirestore } from '@angular/fire/firestore';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { provideAuth, getAuth} from '@angular/fire/auth';
import { environment } from 'src/environments/environment';
import { provideStorage, getStorage } from '@angular/fire/storage';
import { RegisterMedicalWorkerComponent } from './modules/administrator/register-medical-worker/register-medical-worker.component';
import { DisplayAllUsersComponent } from './modules/administrator/display-all-users/display-all-users.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { ReactiveFormsModule } from '@angular/forms';
import { FormlySelectModule } from '@ngx-formly/core/select';
import { SearchFilterPipe } from './modules/util/pipes/search-filter.pipe';
import { NgxRerenderModule } from 'ngx-rerender';
import { MatDialogModule } from '@angular/material/dialog';
import { BloodBankViewComponent } from './modules/medical_worker/blood-bank-view/blood-bank-view.component';
import { NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import { AddWorkerToBankDialogComponent } from './modules/medical_worker/blood-bank-view/add-worker-to-bank-dialog/add-worker-to-bank-dialog.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { TitleCasePipe } from '@angular/common';
import { UserProfileViewComponent } from './modules/public/user-profile-view/user-profile-view.component';
import { CreateSurveyComponent } from './modules/public/create-survey/create-survey.component';
import { ToastrModule } from 'ngx-toastr';
import { NgxPaginationModule } from 'ngx-pagination';
import {MatInputModule} from '@angular/material/input';
import { JwtInterceptor } from "./modules/authentication/helpers/jwt.interceptor";
import { JwtModule } from "@auth0/angular-jwt";
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { AddAppointmentSlotComponent } from './modules/medical_worker/blood-bank-view/add-appointment-slot/add-appointment-slot.component';
import { ScheduleExsistingAppointmentComponent } from './modules/public/schedule-exsisting-appointment/schedule-exsisting-appointment.component';
import { ScheduledAppointmentsComponent } from './modules/public/scheduled-appointments/scheduled-appointments.component';
import { CalendarComponent } from './calendar/calendar.component'
import { FullCalendarModule } from '@fullcalendar/angular';
import { RegisterAdministratorComponent } from './modules/administrator/register-administrator/register-administrator.component';
import { ChangePasswordComponent } from './modules/administrator/admin-dashboard/change-password/change-password.component';
import { AdminComplaintsComponent } from './modules/administrator/admin-complaints/admin-complaints.component';
import { ComplaintAnswerComponent } from './modules/administrator/complaint-answer/complaint-answer.component';
import { ScheduleNewAppointmentComponent } from './modules/public/schedule-new-appointment/schedule-new-appointment.component';
import { PasswordPipe } from './modules/util/pipes/password.pipe';
import * as jsQR from 'jsqr';
import { NgxQRCodeModule } from 'ngx-qrcode2';
import { AppointmentSlotByQrCodeComponent } from './modules/administrator/appointment-slot-by-qr-code/appointment-slot-by-qr-code.component';
//I keep the new line


@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    RegisterUserComponent,
    HomeComponentComponent,
    SaveApiKeyComponent,
    RegisterBloodBankComponent,
    AdminDashboardComponent,
    RegisterBloodBankComponent,
    SendNewsComponent,
    DisplayAllCentersComponent,
    RegisterMedicalWorkerComponent,
    DisplayAllUsersComponent,
    SearchFilterPipe,
    RegisterMedicalWorkerComponent,
    BloodBankViewComponent,
    AddWorkerToBankDialogComponent,
    CreateSurveyComponent,
    UserProfileViewComponent,
    AddAppointmentSlotComponent,
    ScheduleExsistingAppointmentComponent,
    ScheduledAppointmentsComponent,
    CalendarComponent,
    RegisterAdministratorComponent,
    ChangePasswordComponent,
    AdminComplaintsComponent,
    ComplaintAnswerComponent,

    ScheduleNewAppointmentComponent,
      PasswordPipe,
      AppointmentSlotByQrCodeComponent
  ],

  imports: [
    NgxQRCodeModule,
    FullCalendarModule,
    NgMultiSelectDropDownModule,
    MatDialogModule,
    BrowserModule,
    ReactiveFormsModule,
    FormlySelectModule,
    Ng2SearchPipeModule,
    HttpClientModule,
    NgbModule,
    AppRoutingModule,
    FormsModule,
    NgxRerenderModule,
    RouterModule,
    MatTableModule,
    MatSortModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatIconModule,
    BrowserAnimationsModule,
    BrowserModule,
    FlexLayoutModule,
    MatCardModule,
    MatToolbarModule,
    FileUploadModule,
    MatRadioModule,
    provideFirebaseApp(()=> initializeApp(environment.firebase)),
    provideAuth(() => getAuth()),
    provideFirestore(() => getFirestore()),
    provideStorage(() => getStorage()),
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    ToastrModule.forRoot(),
    TitleCasePipe,
    NgxPaginationModule,
    MatCardModule,
    MatButtonModule,
    MatInputModule,
    AuthenticationModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: () => localStorage.getItem('token')
      }
    })

  ],
  providers: [
    {
    provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    UserService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
