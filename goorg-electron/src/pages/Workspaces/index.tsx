import { Plus, ShareNetwork, User } from "phosphor-react";
import { useState } from "react";
import { Header } from "../../components/Header";
import { WorkspaceAddCard, WorkspaceCard, WorkspaceCreate } from "../../components/Workspace";
import { useWorkspaces } from "../../hooks";
import { getColor } from "../../utils/colorsRadom";

export function Workspaces() {
   const { workspaces } = useWorkspaces();
   const [isOpenWorkspaceModal, setIsOpenWorkspaceModal] = useState(false);

   return (
      <div >
         <Header />
      
         <div className="m-5 flex flex-col gap-6 mt-20">
            <div className="bg-white rounded flex overflow-hidden px-10 py-10 ">
               <div>
                  <h2 className="text-md font-semibold text-gray-700 mb-[2px] flex items-center gap-1">
                     <ShareNetwork size={20} />
                     <span>Workspaces</span>
                  </h2>
                  <span className="bg-violet-300 text-[13px] py-[1px] px-3 rounded-full font-medium text-violet-800">
                     {workspaces.length} Workspaces
                  </span>
                  <p className="text-sm text-gray-300 mt-2">
                     Relação de todas os workspaces criados.
                  </p>
               </div>
            </div>

            <div className="bg-white w-full rounded">
               <header className="px-8 py-5 flex items-center gap-1">
                  <span className="text-blue-700 bg-blue-200 p-2 rounded-full">
                     <User size={20} />
                  </span>
                  <span className="text-sm text-gray-700 font-medium">Seus workspaces</span>
               </header>

               <div className="w-full h-[1px] bg-gray-200"></div>

               <div className="px-8 py-5 flex flex-wrap gap-3 overflow-hidden">
                  <WorkspaceAddCard />
                  { workspaces.map(workspace => (
                     <WorkspaceCard
                        id={workspace.id}
                        name={workspace.name}
                        countActivities={workspace.countActivities}
                        description={workspace.description}
                        color={getColor()}
                     />
                  )) }
               </div>
            </div>
         </div>

         <button 
            onClick={() => setIsOpenWorkspaceModal(true)}
            className='bg-blue-primary text-white p-3 rounded-full fixed bottom-10 right-10 hover:bg-indigo-600 transition-colors'
         >
            <Plus size={24} weight="bold"/>
         </button>

         <WorkspaceCreate 
            isOpenModal={isOpenWorkspaceModal}
            onCloseModal={() => setIsOpenWorkspaceModal(false)}
         />
      </div>
   )
}