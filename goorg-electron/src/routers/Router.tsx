import { Route, Routes } from "react-router-dom";
import { ActivitySelected } from "../components/Activity/ActivitySelected";

import { WorkspaceSelected } from "../components/Workspace/WorkspaceSelected";
import { MainLayout } from "../Layouts/MainLayout";
import { Activitys } from "../pages/Activitys";
import { Home } from "../pages/Home";
import { Login } from "../pages/Login";

export function Router() {
   return (
      <Routes>
         <Route path="/" element={ <Login /> } />
         <Route path="/inicio" element={ <MainLayout page={ <Home /> } /> } />
         <Route path="/inicio/workspace/:id" element={ <MainLayout page={ <WorkspaceSelected /> } />} />
         <Route path="/inicio/workspace/:id/atividade/:id" element={ <MainLayout page={ <ActivitySelected /> } />} />
         <Route path="/atividades" element={ <MainLayout page={ <Activitys /> } />}/>
      </Routes>
   )
}