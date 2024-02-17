import { Branch } from './branch';
import { Repository } from './repository';

export interface SelectedBranch {
  toBranch: Branch;
  fromBranch: Branch;
  repository: Repository;
}
