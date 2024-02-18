export interface Repository {
  name: string;
  pullRequestsCount: number;
  commitsCount: number;
  url: string;
  branches: string[];
}
