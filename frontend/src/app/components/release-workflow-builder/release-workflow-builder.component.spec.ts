import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReleaseWorkflowBuilderComponent } from './release-workflow-builder.component';

describe('ReleaseWorkflowBuilderComponent', () => {
  let component: ReleaseWorkflowBuilderComponent;
  let fixture: ComponentFixture<ReleaseWorkflowBuilderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReleaseWorkflowBuilderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReleaseWorkflowBuilderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
