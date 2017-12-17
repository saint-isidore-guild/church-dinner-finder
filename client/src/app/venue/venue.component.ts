import { Component, OnInit } from '@angular/core';
import { HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-venue',
  templateUrl: './venue.component.html',
  styleUrls: ['./venue.component.css']
})
export class VenueComponent implements OnInit {

    venues: any;

    constructor(private http: HttpClient) { }

    ngOnInit() {
        this.http.get('http://localhost:8080/venue').subscribe(data => {
            this.venues = data;
        });
    }

}
