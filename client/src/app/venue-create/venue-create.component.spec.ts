import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VenueCreateComponent } from './venue-create.component';

describe('VenueCreateComponent', () => {
  let component: VenueCreateComponent;
  let fixture: ComponentFixture<VenueCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VenueCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VenueCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
