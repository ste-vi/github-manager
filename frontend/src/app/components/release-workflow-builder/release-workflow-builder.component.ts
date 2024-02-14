import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";
import {Repository} from "../../common/model/repository";
import {RepositoryService} from "../../service/repository.service";
import {WorkflowCheckboxComponent} from "../../common/component/workflow-checkbox/workflow-checkbox.component";
import {DefaultCheckboxComponent} from "../../common/component/default-checkbox/default-checkbox.component";

@Component({
  selector: 'app-release-workflow-builder',
  standalone: true,
  imports: [NgForOf, WorkflowCheckboxComponent, DefaultCheckboxComponent],
  templateUrl: './release-workflow-builder.component.html',
  styleUrl: './release-workflow-builder.component.scss',
})
export class ReleaseWorkflowBuilderComponent {
  repositories: Repository[] = [];

  constructor(private repositoryService: RepositoryService) {
    repositoryService
      .getRepositories()
      .subscribe((repositories) => (this.repositories = repositories));
  }
}
