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
import { VenueCreateComponent } from './venue-create/venue-create.component';
import { VenueDetailComponent } from './venue-detail/venue-detail.component';
import { VenueEditComponent } from './venue-edit/venue-edit.component';
import { EventComponent } from './event/event.component';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { EventCreateComponent } from './event-create/event-create.component';


@NgModule({
  declarations: [
    AppComponent,
    // NavComponent,
    IndexComponent,
    VenueComponent,
    VenueCreateComponent,
    VenueDetailComponent,
    VenueEditComponent,
    EventComponent,
    EventCreateComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    AngularFontAwesomeModule,
    NgbModule.forRoot()
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule { }
