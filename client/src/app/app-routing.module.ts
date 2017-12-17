import { NgModule } from '@angular/core';
import {RouterModule,Routes} from '@angular/router';
import {IndexComponent} from "./index/index.component";
import {VenueComponent} from "./venue/venue.component";

const routes: Routes = [
    {
        path: 'venues',
        component: VenueComponent,
        data: { title: 'Venue List' }
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
