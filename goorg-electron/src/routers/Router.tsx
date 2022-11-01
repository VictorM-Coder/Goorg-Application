import { Route, Routes } from "react-router-dom";

import { WorkspaceSelected } from "../components/Workspace/WorkspaceSelected";
import { MainLayout } from "../Layouts/MainLayout";
import { Home } from "../pages/Home";
import { Login } from "../pages/Login";

export function Router() {

   return (
      <Routes>
         <Route path="/" element={ <Login /> } />
         <Route path="/inicio" element={ <MainLayout page={<Home />} /> } />
         <Route path="/inicio/workspace/:id" element={<MainLayout page={<WorkspaceSelected />} />} />
      </Routes>
   )
}