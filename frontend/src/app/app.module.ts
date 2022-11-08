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
import {MatButtonModule} from '@angular/material/button';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatIconModule} from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule} from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';




@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    RegisterUserComponent,
    HomeComponentComponent,
    SaveApiKeyComponent,
    AdminDashboardComponent,
    RegisterBloodBankComponent,
    SendNewsComponent,
    UserProfileViewComponent,
    DisplayAllCentersComponent,

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
    MatToolbarModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
