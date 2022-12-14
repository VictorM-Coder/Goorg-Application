import { ShareNetwork } from 'phosphor-react';
import { useState } from 'react';
import { Slide, Slider } from '../../components/Slider';
import { WorkspaceAddCard } from '../../components/Workspace';
import { WorkspaceCard } from '../../components/Workspace/WorkspaceCard';
import { useWorkspaces } from '../../hooks/useWorkspaces';
import { settingsSlider } from '../../utils';
import { getColor } from '../../utils/colorsRadom';

export function WorkspacesRecents() {
   const { workspaces } = useWorkspaces();
   const [searchWorkspaces, setSearchWorkspaces] = useState('');

   const workspacesFilter = (searchWorkspaces.length > 0) 
   ? workspaces.filter(workspace =>workspace.name.includes(searchWorkspaces)): workspaces;

   return (
      <div className="bg-white overflow-hidden rounded">    
         <header className="flex items-center justify-between px-8 py-5">
            <div className="flex items-center gap-2">
               <span className="bg-blue-200 p-2 inline-block rounded-full text-blue-primary">
                  <ShareNetwork size={20} />
               </span>
               <span className="text-sm text-gray-700 font-medium">Workspaces</span>
            </div>
            
            <input 
               type="search" 
               name="workspaces" 
               id="search-workspaces"
               placeholder="Busque por um workspace"
               className="bg-gray-200 px-4 py-2.5 rounded w-64 text-xs text-gray-500 placeholder:text-xs 
               placeholder:text-gray-300 focus:bg-transparent focus:outline-blue-primary"
               onChange={ event => setSearchWorkspaces(event.target.value) }
            />
         </header>

         <div className="w-full h-[2px] bg-blue-100"></div>

         <div className="mt-3 p-8 gap-4">
            <Slider settings={settingsSlider}>
               <Slide className='z-0 w-auto'>
                  <WorkspaceAddCard />
               </Slide>
               {
                  workspacesFilter.length > 0 
                  ?  workspacesFilter.map(workspace => (
                        <Slide className='z-0 w-auto' key={workspace.id}>
                           <WorkspaceCard
                              id={workspace.id}
                              name={workspace.name}
                              countActivities={workspace.countActivities}
                              description={workspace.description}
                              color={getColor()}
                           />
                        </Slide>  
                     ))
                  : <span></span>
               }
            </Slider>
         </div>
      </div>
   )
}