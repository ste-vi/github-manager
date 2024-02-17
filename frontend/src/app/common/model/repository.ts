import {Branch} from "./branch";

export interface Repository {
  name: string;
  pullRequestsCount: number;
  commitsCount: number;
  url: string;
  branches: Branch[];
}
