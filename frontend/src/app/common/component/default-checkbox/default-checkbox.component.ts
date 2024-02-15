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
  @Input() disabled: boolean = false;
  @Output() changed = new EventEmitter<boolean>();

  change($event: Event) {
    this.changed.emit(($event.target as HTMLInputElement).checked);
  }
}
