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

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    RegisterUserComponent,
    HomeComponentComponent,
    SaveApiKeyComponent,
    AdminDashboardComponent,
    RegisterBloodBankComponent,
    SendNewsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    NgbModule,
    AppRoutingModule,
    FormsModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
