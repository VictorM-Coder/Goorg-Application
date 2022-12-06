import { HashRouter } from 'react-router-dom';
import { SidebarProvider } from './contexts/SidebarContext';
import { ActivityProvider } from './hooks/useActivities';
import { WorkspacesProvider } from './hooks/useWorkspaces';
import { Router } from './routers/Router'

export function App() {
  return (
    <HashRouter>
     <WorkspacesProvider>
      <ActivityProvider>
        <SidebarProvider>
          <Router />
        </SidebarProvider>
      </ActivityProvider>
     </WorkspacesProvider>
    </HashRouter>
  )
}
