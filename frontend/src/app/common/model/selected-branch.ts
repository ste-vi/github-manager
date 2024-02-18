import { Repository } from './repository';

export interface SelectedBranch {
  toBranch: string;
  fromBranch: string;
  repository: Repository;
}
