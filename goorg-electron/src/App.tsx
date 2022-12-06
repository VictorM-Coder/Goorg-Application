import { HashRouter } from 'react-router-dom';
import { LatestDatasContext, LatestDatasProvider } from './contexts/LatestDatas';
import { SidebarProvider } from './contexts/SidebarContext';
import { ActivityProvider } from './hooks/useActivities';
import { WorkspacesProvider } from './hooks/useWorkspaces';
import { Router } from './routers/Router'

export function App() {
  return (
    <HashRouter>
     <WorkspacesProvider>
      <ActivityProvider>
        <LatestDatasProvider>
          <SidebarProvider>
            <Router />
          </SidebarProvider>
        </LatestDatasProvider>
      </ActivityProvider>
     </WorkspacesProvider>
    </HashRouter>
  )
}
