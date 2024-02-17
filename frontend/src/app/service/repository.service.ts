import { Injectable } from '@angular/core';
import { Repository } from '../common/model/repository';
import { Observable } from 'rxjs';
import {Branch} from "../common/model/branch";

@Injectable({
  providedIn: 'root',
})
export class RepositoryService {
  getRepositories(): Observable<Repository[]> {
    return new Observable<Repository[]>((observer) => {

      let branches = [
        { id: 1, name: 'main' },
        { id: 2, name: 'dev' },
        { id: 3, name: 'release' },
      ];

      const repo1 = {} as Repository;
      repo1.name = 'test-repo-1';
      repo1.pullRequestsCount = 1;
      repo1.commitsCount = 30;
      repo1.url = 'https://github.com/stevi-test/test-repo-1';
      repo1.branches = branches;

      const repo2 = {} as Repository;
      repo2.name = 'test-repo-2';
      repo2.pullRequestsCount = 3;
      repo2.commitsCount = 40;
      repo2.url = 'https://github.com/stevi-test/test-repo-1';
      repo2.branches = branches;

      const repo3 = {} as Repository;
      repo3.name = 'test-repo-3';
      repo3.pullRequestsCount = 5;
      repo3.commitsCount = 50;
      repo3.url = 'https://github.com/stevi-test/test-repo-1';
      repo3.branches = branches;

      observer.next([repo1, repo2, repo3]);
      observer.complete();
    });
  }
}
