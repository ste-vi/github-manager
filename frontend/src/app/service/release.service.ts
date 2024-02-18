import { Injectable, OnInit } from '@angular/core';
import { Repository } from '../common/model/repository';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { SelectedBranch } from '../common/model/selected-branch';

@Injectable({
  providedIn: 'root',
})
export class ReleaseService implements OnInit {
  constructor(private http: HttpClient) {}

  ngOnInit() {}

  createRelease(
    name: string,
    prTitle: string,
    repos: SelectedBranch[],
  ): Observable<any> {
    let pullRequestRequests = repos.map((repo) => {
      return {
        title: prTitle,
        repo: repo.repository.name,
        fromBranch: repo.fromBranch,
        toBranch: repo.toBranch,
      };
    });

    let request = {
      name: name,
      pullRequestRequests: pullRequestRequests,
    };

    return this.http.post<any>(environment.API_URL + '/releases', request);
  }
}
