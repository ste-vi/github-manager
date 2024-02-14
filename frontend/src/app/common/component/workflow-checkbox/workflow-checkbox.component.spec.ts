import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkflowCheckboxComponent } from './workflow-checkbox.component';

describe('WorkflowCheckboxComponent', () => {
  let component: WorkflowCheckboxComponent;
  let fixture: ComponentFixture<WorkflowCheckboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkflowCheckboxComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(WorkflowCheckboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
