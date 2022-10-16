import { Route, Routes } from 'react-router-dom';

import { Activitys } from '../pages/Activitys';
import { Home } from '../pages/Home';
import { Login } from '../pages/Login';
import { Workspaces } from '../pages/Workspaces';

export function Router() {
   return (
      <Routes>
         <Route path="/" element={<Login />} />
         <Route path="/inicio" element={<Home />} />
         <Route path="/atividades" element={<Activitys />} />
         <Route path="/workspaces" element={<Workspaces />} />
      </Routes>
   )
}