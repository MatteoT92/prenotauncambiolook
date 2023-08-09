import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdiniComponent } from './ordini.component';

describe('OrdiniComponent', () => {
  let component: OrdiniComponent;
  let fixture: ComponentFixture<OrdiniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrdiniComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrdiniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
