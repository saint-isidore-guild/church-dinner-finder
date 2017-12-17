import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
    selector: 'app-venue-detail',
    templateUrl: './venue-detail.component.html',
    styleUrls: ['./venue-detail.component.css']
})
export class VenueDetailComponent implements OnInit {

    venue = {};

    constructor(private router: Router, private route: ActivatedRoute, private http: HttpClient) { }

    ngOnInit() {
        this.getVenueDetail(this.route.snapshot.params['id']);
    }

    getVenueDetail(id) {
        this.http.get('http://localhost:8080/venue/'+id).subscribe(data => {
            this.venue = data;
        });
    }

    deleteVenue(id) {
        this.http.delete('http://localhost:8080/venue/'+id)
            .subscribe(res => {
                    this.router.navigate(['/venues']);
                }, (err) => {
                    console.log(err);
                }
            );
    }

}
