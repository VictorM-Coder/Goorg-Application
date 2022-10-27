import { Box, Modal } from "@mui/material";
import { ShareNetwork, X } from "phosphor-react";
import { useState, useEffect, useRef } from "react";
import { useWorkspaces } from "../../hooks/useWorkspaces";
import { WorkspaceModalProps } from "../../interfaces/Workspace";
import { api } from "../../services/api";
import { styleModal } from "../../utils";

export function WorkspaceModal({ 
   isEditWorkspace, 
   workspaceId, 
   isOpenWorkspaceModal, 
   onCloseWorkspaceModal
} : WorkspaceModalProps) {
   
   const [name, setName] = useState('');
   const [description, setDescription] = useState('');
   const { addNewWorkspace, editWorkspaceById } = useWorkspaces();

   const isFirst = useRef(false);

   function handleSubmit(event: any) {
      event.preventDefault();
      const workspace = { name, description };
      
      if (!isEditWorkspace) {
         addNewWorkspace(workspace);
         setName('');
         setDescription('');
      } else {
         editWorkspaceById(Number(workspaceId), workspace).then(() => {
            onCloseWorkspaceModal();
         });
      } 
   }

   function fetchDataWorkspace() {
      if (isEditWorkspace) {
         api.get(`workspace/${workspaceId}`)
         .then(res => {
            setName(res.data.name);
            setDescription(res.data.description);
         });
      }
   }

   useEffect(() => {
      if (isFirst.current) {
         fetchDataWorkspace();
      } 

      return () => { isFirst.current = true } 
   }, [])
   
   return (
      <Modal 
         open={isOpenWorkspaceModal}
         onClose={onCloseWorkspaceModal}
         aria-labelledby="modal-modal-title"
         aria-describedby="modal-modal-description"
      >
         <Box sx={styleModal}>
            <div className="p-8 flex flex-col gap-8 w-[500px]">
               <header className="text-blue-primary flex items-center justify-between">
                  <span className="flex items-center gap-1 font-semibold text-[15px]">
                     <ShareNetwork size={20} />
                     Novo Worskpace
                  </span>
                  <button onClick={onCloseWorkspaceModal}>
                     <X size={22} weight="bold"/>
                  </button>
               </header>

               <form className="flex flex-col gap-3" onSubmit={handleSubmit}>
                  <div className="flex flex-col gap-1 text-gray-600">
                     <label className="text-[13px] font-medium">Nome</label>
                     <input 
                        type="text"
                        value={name}
                        onChange={event => setName(event.target.value)}
                        className="w-full px-3 py-2 rounded text-[13px] text-gray-500 bg-gray-200 border border-gray-200 focus:outline-gray-300 focus:bg-transparent"
                     />
                  </div>

                  <div className="flex flex-col gap-1 text-gray-600">
                     <label htmlFor="description" className="text-[13px] font-medium">Descrição</label>
                     <textarea 
                        value={description}
                        className="w-full px-3 py-2 rounded text-[13px] text-gray-500 bg-gray-200 border border-gray-200 focus:outline-gray-300 focus:bg-transparent"
                        onChange={event => setDescription(event.target.value)}
                     >
                     </textarea>
                  </div>
      
                  <button 
                     type="submit"
                     className="w-full mt-2 bg-blue-primary text-[13px] font-medium text-white p-3 rounded">
                     Criar Workspace
                  </button>
               </form>
            </div>
         </Box>
      </Modal>
   )
}