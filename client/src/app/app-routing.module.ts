import { NgModule } from '@angular/core';
import {RouterModule,Routes} from '@angular/router';
import {IndexComponent} from "./index/index.component";
import {VenueComponent} from "./venue/venue.component";
import {VenueCreateComponent } from "./venue-create/venue-create.component";
import {VenueDetailComponent} from "./venue-detail/venue-detail.component";
import {VenueEditComponent} from "./venue-edit/venue-edit.component";
import {EventComponent} from "./event/event.component";
import {EventCreateComponent} from "./event-create/event-create.component";


const routes: Routes = [
    {
        path: 'venues',
        component: VenueComponent,
        data: { title: 'Venue List' }
    },
    {
        path: 'venue-create',
        component: VenueCreateComponent,
        data: { title: 'Add Venue' }
    },
    {
        path: 'venue-details/:id',
        component: VenueDetailComponent,
        data: { title: 'Venue Details' }
    },
    {
        path: 'venue-edit/:id',
        component: VenueEditComponent,
        data: { title: 'Edit Venue' }
    },
    {
        path: 'events',
        component: EventComponent,
        data: { title: 'Event List' }
    },
    {
        path: 'event-create',
        component: EventCreateComponent,
        data: { title: 'Add Event' }
    },
    { path: '',
        redirectTo: '/venues',
        pathMatch: 'full'
    }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
