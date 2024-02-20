import { Component } from '@angular/core';
import { Repository } from '../../common/model/repository';
import { RepositoryService } from '../../service/repository.service';
import { NgForOf } from '@angular/common';
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faCodePullRequest, faPlus} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NgForOf, FaIconComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss',
})
export class DashboardComponent {
  repositories: Repository[] = [];

  constructor(private repositoryService: RepositoryService) {
    repositoryService
      .getRepositories()
      .subscribe((repositories) => (this.repositories = repositories));
  }

  protected readonly faCodePullRequest = faCodePullRequest;
  protected readonly faPlus = faPlus;
}
