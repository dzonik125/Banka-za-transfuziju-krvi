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
import { UserProfileViewComponent } from './modules/public/user-profile-view/user-profile-view.component';
import { AdminDashboardComponent } from './modules/administrator/admin-dashboard/admin-dashboard.component';
import { RegisterBloodBankComponent } from './modules/administrator/register-blood-bank/register-blood-bank.component';
import { SendNewsComponent } from './modules/administrator/send-news/send-news.component';
import { DisplayAllCentersComponent } from './modules/public/display-all-centers/display-all-centers.component';
import { MatTableModule } from '@angular/material/table';
import {MatSortModule} from '@angular/material/sort';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatIconModule} from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule} from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatRadioModule} from '@angular/material/radio';
import { FileUploadModule } from "ng2-file-upload";
import { provideFirestore, getFirestore } from '@angular/fire/firestore';
import { initializeApp, provideFirebaseApp } from '@angular/fire/app';
import { provideAuth, getAuth} from '@angular/fire/auth';
import { environment } from 'src/environments/environment';
import { provideStorage, getStorage } from '@angular/fire/storage';
import { RegisterMedicalWorkerComponent } from './modules/administrator/register-medical-worker/register-medical-worker.component';
import { CreateSurveyComponent } from './modules/public/create-survey/create-survey.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';




@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    RegisterUserComponent,
    HomeComponentComponent,
    SaveApiKeyComponent,
    RegisterBloodBankComponent,
    AdminDashboardComponent,
    UserProfileViewComponent,
    AdminDashboardComponent,
    RegisterBloodBankComponent,
    SendNewsComponent,
    UserProfileViewComponent,
    DisplayAllCentersComponent,
    RegisterMedicalWorkerComponent,
    CreateSurveyComponent

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgbModule,
    AppRoutingModule,
    FormsModule,
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
    MatDialogModule,
    provideFirebaseApp(()=> initializeApp(environment.firebase)),
    provideAuth(() => getAuth()),
    provideFirestore(() => getFirestore()),
    provideStorage(() => getStorage())
  ],
  providers: [],
  entryComponents: [CreateSurveyComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
