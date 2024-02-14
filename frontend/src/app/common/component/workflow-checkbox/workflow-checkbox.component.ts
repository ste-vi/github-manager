import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-workflow-checkbox',
  standalone: true,
  imports: [],
  templateUrl: './workflow-checkbox.component.html',
  styleUrl: './workflow-checkbox.component.scss',
})
export class WorkflowCheckboxComponent {
  @Input() checked = false;
}
