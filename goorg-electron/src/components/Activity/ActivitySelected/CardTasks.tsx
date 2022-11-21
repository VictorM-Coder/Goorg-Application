import { File, Plus } from "phosphor-react";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { useParams } from "react-router-dom";
import { api } from "../../../services/api";
import { TaskModal } from "../../Modal/TaskModal";
import { Task } from "./Task";

interface CardTasksProps {
   tasks: {
      id: number;
      title: string;
      status: boolean;
   }[] | undefined;
}

export function CardTasks({ tasks }: CardTasksProps) {
   const { idAc } = useParams();

   const { register, handleSubmit } = useForm();
   const [isOpenTaskModal, setIsOpenTaskModal] = useState(false);
   
   const handleOpenTaskModal = () => setIsOpenTaskModal(true);
   const handleCloseTaskModal = () => setIsOpenTaskModal(false);

   function data(checkbox: any) {
      const checkboxValues = Object.entries(checkbox);
      const tasks = checkboxValues.map(task => {
         return {
            id: Number(task[0]),
            complete: task[1]
         }
      });
 
      api.put('task/all', tasks)
      .then(() => console.log(''));
   }

   return (
      <div className="bg-white rounded shadow overflow-hidden">
         <form onSubmit={handleSubmit(data)}>
            <header className="flex items-center justify-between px-8 py-5 pb-4">
               <div className="flex items-center gap-2">
                  <span className="bg-cyan-200 p-2 inline-block rounded-full text-cyan-500">
                     <File size={20} weight="fill" />
                  </span>
                  <span className="text-sm text-gray-700 font-medium">Tarefas</span>
               </div>
               <button className="bg-blue-100 text-blue-primary px-6 py-2 flex items-center gap-2 rounded-full">
                  <span className="w-4 h-4 border-[1.5px] border-blue-primary rounded-full block bg-gray-50"></span>
                  <span className="text-sm font-medium">Concluir tarefas marcadas</span>
               </button>
            </header>

            <div className="w-full h-[2px] bg-cyan-200"></div>

            <div className="flex flex-col px-8 py-5 gap-4">
               { tasks?.map(task => 
                  <Task 
                     key={task.id}
                     id={task.id} 
                     title={task.title}
                     status={task.status}
                     register={register} 
                  />
               )}

               <div
                  className="block w-full p-4 mt-1 rounded border border-dashed border-blue-500
                  hover:bg-white transition-colors"
                  role="button"
                  onClick={handleOpenTaskModal}
               >
                  <span className="text-sm font-medium flex items-center justify-center gap-1 text-blue-primary">
                     <Plus size={16} weight="bold"/>
                     Adicionar Tarefa
                  </span>
               </div>
            </div>     
         </form>

         <TaskModal 
            idActivity={idAc as string}
            isOpenTaskModal={isOpenTaskModal}
            onCloseTaskModal={handleCloseTaskModal}
         />
      </div>
   )
}