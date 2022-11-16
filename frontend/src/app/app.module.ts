import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Router, RouterModule, Routes } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './modules/public/navigation/navigation.component';
import { RegisterUserComponent } from './modules/public/register-user/register-user.component';
import { HomeComponentComponent } from './home-component/home-component.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
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
    UserProfileViewComponent
  ],

  imports: [
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
    MatRadioModule,
    FileUploadModule,
    provideFirebaseApp(()=> initializeApp(environment.firebase)),
    provideAuth(() => getAuth()),
    provideFirestore(() => getFirestore()),
    provideStorage(() => getStorage()),
    ReactiveFormsModule,
    MatFormFieldModule,
    MatSelectModule,
    FormsModule,
    ToastrModule.forRoot(),
    TitleCasePipe

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
