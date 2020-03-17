import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AadharCardComponent } from './aadhar-card/aadhar-card.component';
import { AadharCardService } from './aadhar-card/aadhar-card.service';
import { HttpClientModule } from '@angular/common/http';
import { AadharNumberPipe } from './aadhar-card/aadhar-number.pipe';


@NgModule({
  //User defined Components and filters needs to be registered 
  declarations: [
    AppComponent,
    AadharCardComponent,
    AadharNumberPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule // module to be included for creating ReactiveForms
  ],
  providers: [AadharCardService],//AadharCardService to be included to call web service
  bootstrap: [AppComponent]
})
export class AppModule { }
