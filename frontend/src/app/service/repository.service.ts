import { Injectable } from '@angular/core';
import { Repository } from '../common/model/repository';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class RepositoryService {
  getRepositories(): Observable<Repository[]> {
    return new Observable<Repository[]>((observer) => {
      const repo1 = {} as Repository;
      repo1.name = 'test-repo-1';
      repo1.pullRequestsCount = 1;

      const repo2 = {} as Repository;
      repo2.name = 'test-repo-2';
      repo2.pullRequestsCount = 3;

      observer.next([repo1, repo2]);
      observer.complete();
    });
  }
}
