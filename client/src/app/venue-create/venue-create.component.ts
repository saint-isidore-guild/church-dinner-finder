import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-venue-create',
    templateUrl: './venue-create.component.html',
    styleUrls: ['./venue-create.component.css']
})
export class VenueCreateComponent implements OnInit {

    venue = {};

    constructor(private http: HttpClient, private router: Router) { }

    ngOnInit() {
    }

    saveVenue() {
        this.http.post('http://localhost:8080/venue', this.venue)
            .subscribe(res => {
                    let id = res['id'];
                    this.router.navigate(['/venue-details/', id]);
                }, (err) => {
                    console.log(err);
                }
            );
    }

}