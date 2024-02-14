import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultCheckboxComponent } from './default-checkbox.component';

describe('DefaultCheckboxComponent', () => {
  let component: DefaultCheckboxComponent;
  let fixture: ComponentFixture<DefaultCheckboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DefaultCheckboxComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DefaultCheckboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
