import { BrowserRouter, HashRouter } from 'react-router-dom';
import { SidebarProvider } from './contexts/SidebarContext';
import { WorkspacesProvider } from './hooks/useWorkspaces';
import { Router } from './routers/Router'

export function App() {
  return (
    <HashRouter>
     <WorkspacesProvider>
        <SidebarProvider>
          <Router />
        </SidebarProvider>
     </WorkspacesProvider>
    </HashRouter>
  )
}
