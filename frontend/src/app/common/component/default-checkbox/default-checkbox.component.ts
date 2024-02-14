import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-default-checkbox',
  standalone: true,
  imports: [],
  templateUrl: './default-checkbox.component.html',
  styleUrl: './default-checkbox.component.scss',
})
export class DefaultCheckboxComponent {
  @Input() checked = false;
}
