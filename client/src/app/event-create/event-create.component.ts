import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import moment = require("moment");

@Component({
  selector: 'app-event-create',
  templateUrl: './event-create.component.html',
  styleUrls: ['./event-create.component.css']
})
export class EventCreateComponent implements OnInit {

  event = {};
  venues: any;

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit() {
      this.http.get('http://localhost:8080/venue').subscribe(data => {
          this.venues = data;
      });
  }

  saveEvent() {
      this.event.startTime = moment(this.event.startTime).valueOf();
      this.event.endTime = moment(this.event.endTime).valueOf();
      this.http.post('http://localhost:8080/event', this.event)
          .subscribe(res => {
                  let id = res['id'];
                  this.router.navigate(['/event-details/', id]);
              }, (err) => {
                  console.log(err);
              }
          );
  }
}
