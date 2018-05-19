import { Component, OnInit } from '@angular/core';
import { Event} from "../event/event.component";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {

  event: Event;

  constructor(private router: Router, private route: ActivatedRoute, private http: HttpClient) { }

  ngOnInit() {
      this.getEventDetail(this.route.snapshot.params['id']);
  }
    getEventDetail(id) {
        this.http.get('http://localhost:8080/event/'+id).subscribe((res: Response) => {
            this.event = res as any;
        });
    }

    deleteEvent(id) {
        this.http.delete('http://localhost:8080/event/'+id)
            .subscribe(res => {
                    this.router.navigate(['/events']);
                }, (err) => {
                    console.log(err);
                }
            );
    }
}
