import { Component, NgIterable } from '@angular/core';
import {
  faCodeCommit,
  faCodePullRequest,
  faInfo,
} from '@fortawesome/free-solid-svg-icons';
import { DefaultCheckboxComponent } from '../../common/component/default-checkbox/default-checkbox.component';
import { FaIconComponent } from '@fortawesome/angular-fontawesome';
import { NgForOf, NgIf } from '@angular/common';

@Component({
  selector: 'app-view-release',
  standalone: true,
  imports: [DefaultCheckboxComponent, FaIconComponent, NgForOf, NgIf],
  templateUrl: './view-release.component.html',
  styleUrl: './view-release.component.scss',
})
export class ViewReleaseComponent {
  protected readonly faCodePullRequest = faCodePullRequest;
  protected releaseName: string = '';
  protected repositories: string[] = [];

  constructor() {
    this.releaseName = '1.10.0';
    this.repositories.push(this.releaseName);
    this.repositories.push(this.releaseName);
  }
}
