import { Component, OnInit } from '@angular/core';
import { HttpClient} from "@angular/common/http";

export class Event {
    public id: number;
    public name: string;
    public description: string;
    public website: string;
    public email: string;
    public phone: string;
    public organizer: string;
    public startTime: any;
    public endTime: any;
    public venue: any;

    constructor(id: number, name: string, description: string, website: string, email: string, phone: string, organizer: string, startTime: any, endTime: any, venue: any){
        this.id = id;
        this.name = name;
        this.description = description;
        this.website = website;
        this.email = email;
        this.phone = phone;
        this.organizer = organizer;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
    }
}

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
