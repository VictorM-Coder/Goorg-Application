import { Plus, ShareNetwork } from 'phosphor-react';
import { useState } from 'react';
import { Link } from 'react-router-dom';

import { NewWorkspaceModal } from '../../components/NewWorkspaceModal';
import { Slide, Slider, SliderProps } from '../../components/Slider';
import { WorkspaceCard } from '../../components/Workspace/WorkspaceCard';
import { useWorkspaces } from '../../hooks/useWorkspaces';

export function WorkspacesRecents() {
   const [onNewWorkspaceModal, setOnNewWorkspaceModal] = useState(false);
   const { workspaces } = useWorkspaces();
   const [searchWorkspaces, setSearchWorkspaces] = useState('');

   function handleOpenNewWorkspaceModal() {
      setOnNewWorkspaceModal(true);
   }

   function handleCloseNewWorkspaceModal() {
      setOnNewWorkspaceModal(false);
   }

   const settings: SliderProps = { spaceBetween: 10, slidesPerView: 'auto', resizeObserver: true }

   const workspacesFilter = (searchWorkspaces.length > 0) 
   ? workspaces.filter(workspace =>workspace.name.includes(searchWorkspaces))
   : workspaces;

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
            { 
               workspaces.length === 0
               ?  <button 
                     onClick={() => handleOpenNewWorkspaceModal()}
                     className="block w-full p-4 rounded border border-dashed border-gray-200 hover:bg-white transition-colors"
                  >
                     <span className="text-sm font-medium flex items-center justify-center gap-1 text-blue-primary">
                        <Plus size={16} weight="bold"/>
                        Adicionar Workspace
                     </span>
                   </button>
               :  <Slider settings={settings}>
                  {
                     workspacesFilter.length > 0 
                     ?  workspacesFilter.map(workspace => (
                           <Slide key={workspace.name} className="z-0 w-auto">
                              <Link to={`/workspace/${workspace.name}`} className="flex-none hover:scale-105 transition-all">
                                 <WorkspaceCard
                                    name={workspace.name}
                                    countActivitys={workspace.countActivitys}
                                    description={workspace.description}
                                    color={workspace.color}
                                 />
                              </Link>   
                           </Slide>
                        ))
                     : <li className='text-xs text-center text-gray-300 mx-auto list-none'>Nenhum workspace encontrado</li>
                  }
                  </Slider>
            }
         </div>

         <NewWorkspaceModal 
            onNewWorkspaceModal={onNewWorkspaceModal} 
            handleCloseNewWorkspaceModal={handleCloseNewWorkspaceModal}
         />
      </div>
   )
}