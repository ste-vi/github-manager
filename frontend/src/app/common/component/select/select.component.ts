import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NgForOf, NgIf } from '@angular/common';
import { FaIconComponent } from '@fortawesome/angular-fontawesome';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-select',
  standalone: true,
  imports: [NgForOf, NgIf, FaIconComponent, FormsModule],
  templateUrl: './select.component.html',
  styleUrl: './select.component.scss',
})
export class SelectComponent {
  @Input() values: any[] = [];
  @Output() selected = new EventEmitter<any>();
  protected selectedValue: any = this.values[0];

  ngOnInit() {
    if (this.values && this.values.length > 0) {
      this.selectedValue = this.values[0];
    }
  }

  onSelected() {
    this.selected.emit(this.selectedValue);
  }
}
