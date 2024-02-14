import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ReleaseWorkflowBuilderComponent } from './components/release-workflow-builder/release-workflow-builder.component';

export const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'release-builder', component: ReleaseWorkflowBuilderComponent },
];
