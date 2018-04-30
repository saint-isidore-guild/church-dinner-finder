import { Component, OnInit } from '@angular/core';
import { HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {

    events: any;

    constructor(private http: HttpClient) { }

    ngOnInit() {
        this.http.get('http://localhost:8080/event/').subscribe(data => {
            this.events = data;
        });
    }

}
