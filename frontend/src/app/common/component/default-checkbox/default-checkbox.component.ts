import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-default-checkbox',
  standalone: true,
  imports: [],
  templateUrl: './default-checkbox.component.html',
  styleUrl: './default-checkbox.component.scss',
})
export class DefaultCheckboxComponent {
  @Input() checked = false;
  @Output() changed = new EventEmitter<boolean>();

  randomId: string;

  constructor() {
    this.randomId = 'input_' + Math.random().toString(36).substring(7);
  }

  change($event: Event) {
    this.changed.emit(($event.target as HTMLInputElement).checked);
  }
}
