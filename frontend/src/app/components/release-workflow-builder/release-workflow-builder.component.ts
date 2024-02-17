import { Component } from '@angular/core';
import { NgForOf, NgIf } from '@angular/common';
import { Repository } from '../../common/model/repository';
import { RepositoryService } from '../../service/repository.service';
import { WorkflowCheckboxComponent } from '../../common/component/workflow-checkbox/workflow-checkbox.component';
import { DefaultCheckboxComponent } from '../../common/component/default-checkbox/default-checkbox.component';
import {
  faArrowLeftLong,
  faCodeCommit,
  faCodePullRequest,
} from '@fortawesome/free-solid-svg-icons';
import { FaIconComponent } from '@fortawesome/angular-fontawesome';
import {
  animate,
  state,
  style,
  transition,
  trigger,
} from '@angular/animations';
import { SelectComponent } from '../../common/component/select/select.component';
import { Branch } from '../../common/model/branch';
import { SelectedBranch } from '../../common/model/selected-branch';

@Component({
  selector: 'app-release-workflow-builder',
  standalone: true,
  imports: [
    NgForOf,
    WorkflowCheckboxComponent,
    DefaultCheckboxComponent,
    FaIconComponent,
    NgIf,
    SelectComponent,
  ],
  templateUrl: './release-workflow-builder.component.html',
  styleUrl: './release-workflow-builder.component.scss',
  animations: [
    trigger('showHide', [
      state('true', style({ opacity: 1, color: 'red' })),
      state('void', style({ opacity: 0, color: 'blue' })),
      transition(':enter', animate('300ms ease-in')),
      transition(':leave', animate('300ms ease-in')),
    ]),
  ],
})
export class ReleaseWorkflowBuilderComponent {
  protected readonly faCodePullRequest = faCodePullRequest;
  protected readonly faCodeCommit = faCodeCommit;
  protected readonly faArrowLeftLong = faArrowLeftLong;

  protected repositories: Repository[] = [];
  protected selectedRepositories: Repository[] = [];
  protected selectedBranches: SelectedBranch[] = [];

  protected isSelectBranchesStepShow = true;
  protected isNextStepForSelectBranchesDisabled = true;
  protected isSelectRepositoriesStepCompleted = true;

  constructor(private repositoryService: RepositoryService) {
    repositoryService
      .getRepositories()
      .subscribe((repositories) => (this.repositories = repositories));

    // remove
    this.selectedRepositories.push(this.repositories[0]);
    this.selectedRepositories.push(this.repositories[1]);
    this.selectedRepositories.push(this.repositories[2]);
  }

  addRepositoryToSelected(result: boolean, repo: Repository) {
    if (result) {
      this.selectedRepositories.push(repo);
    } else {
      this.selectedRepositories = this.selectedRepositories.filter(
        (selectedRepo) => selectedRepo !== repo,
      );
    }

    this.isNextStepForSelectBranchesDisabled =
      this.selectedRepositories.length === 0;

    this.selectedBranches.push({
      fromBranch: repo.branches[0],
      toBranch: repo.branches[0],
      repository: repo,
    });
  }

  selectRepositories() {
    this.isSelectRepositoriesStepCompleted = true;
    this.isSelectBranchesStepShow = true;
  }

  selectBranchTo($event: Branch, repository: Repository) {
    const existingBranch = this.selectedBranches.find(
      (branch) => branch.repository === repository,
    );
    if (existingBranch) {
      existingBranch.toBranch = $event;
    }
  }

  selectBranchFrom($event: Branch, repository: Repository) {
    const existingBranch = this.selectedBranches.find(
      (branch) => branch.repository === repository,
    );
    if (existingBranch) {
      existingBranch.fromBranch = $event;
    }
  }
}
