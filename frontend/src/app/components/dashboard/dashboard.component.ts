import { Component } from '@angular/core';
import { Repository } from '../../common/model/repository';
import { RepositoryService } from '../../service/repository.service';
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [NgForOf],
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
}
