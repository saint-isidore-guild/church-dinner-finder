import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'app-venue-edit',
    templateUrl: './venue-edit.component.html',
    styleUrls: ['./venue-edit.component.css']
})
export class VenueEditComponent implements OnInit {

    venue = {};

    constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) { }

    ngOnInit() {
        this.getVenue(this.route.snapshot.params['id']);
    }

    getVenue(id) {
        this.http.get('http://localhost:8080/venue/'+id).subscribe(data => {
            this.venue = data;
        });
    }

    updateVenue(id) {
        this.http.put('http://localhost:8080/venue/'+id, this.venue)
            .subscribe(res => {
                    let id = res['id'];
                    this.router.navigate(['/venue-details', id]);
                }, (err) => {
                    console.log(err);
                }
            );
    }

}
