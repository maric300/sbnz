import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MineralDetailsComponent } from './mineral-details.component';

describe('MineralDetailsComponent', () => {
  let component: MineralDetailsComponent;
  let fixture: ComponentFixture<MineralDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MineralDetailsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MineralDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
