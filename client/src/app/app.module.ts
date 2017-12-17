import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { IndexComponent } from './index/index.component';
import { AppComponent } from './app.component';
// import { NavComponent } from './nav/nav.component';
// import { NavService } from './nav/nav.service';
import { AppRoutingModule } from "./app-routing.module";
import { VenueComponent } from './venue/venue.component';

@NgModule({
  declarations: [
    AppComponent,
    // NavComponent,
    IndexComponent,
    VenueComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    NgbModule.forRoot()
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
