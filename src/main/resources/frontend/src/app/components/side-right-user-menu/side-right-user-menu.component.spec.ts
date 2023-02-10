import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SideRightUserMenuComponent } from './side-right-user-menu.component';

describe('SideRightUserMenuComponent', () => {
  let component: SideRightUserMenuComponent;
  let fixture: ComponentFixture<SideRightUserMenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SideRightUserMenuComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SideRightUserMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
