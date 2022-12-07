import classNames from 'classnames';
import { File, Plus } from 'phosphor-react';
import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { useParams } from 'react-router-dom';
import { TasksComplete } from '../../../../../@types/Tasks/TasksComplete';

import { useActivities } from '../../../../../hooks';
import { CompleteTasks } from '../CompleteTasks';
import { TaskCreate } from '../TaskCrud/TaskCreate';
import { Task } from './Task';

interface CardTasksProps {
   tasks: {
      id: number;
      title: string;
      complete: boolean;
   }[] | undefined;
}

export function CardTasks({ tasks }: CardTasksProps) {
   const { idAc } = useParams();
   const { completeTasks } = useActivities();
   const [countTasksComplete, setCountTasksComplete] = useState<number | undefined>(0);

   useEffect(() => {
      let count = 0;
      tasks?.filter(item => item.complete === true).forEach(item => count++)
      setCountTasksComplete(count);
   }, [tasks])

   const { register, getValues } = useForm();
   const [isOpenTaskModal, setIsOpenTaskModal] = useState(false);
   const [isOpenTasksCompleteModal, setIsOpenTasksCompleteModal] = useState(false);

   const handleOpenTaskModal = () => setIsOpenTaskModal(true);
   const handleCloseTaskModal = () => setIsOpenTaskModal(false);

   const handleOpenTaskCompleteModal = () => setIsOpenTasksCompleteModal(true);
   const handleCloseTaskCompleteModal = () => setIsOpenTasksCompleteModal(false);

   function completeScheduledTasks() {
      const tasks: TasksComplete[] = Object.entries(getValues())
      .filter(item => item[1] === true)
      .map(item => { return { id: Number(item[0]), complete: item[1] } });

      if (tasks.length) {
         completeTasks(tasks).then(() => {
            handleCloseTaskCompleteModal();
            setCountTasksComplete(tasks.length);
         });
      } 
   }

   return (
      <div className="bg-white rounded shadow overflow-hidden">    
         <header className="flex items-center justify-between px-8 py-5 pb-4">
            <div className="flex items-center gap-2">
               <span className="bg-cyan-200 p-2 inline-block rounded-full text-cyan-500">
                  <File size={20} weight="fill" />
               </span>
               <span className="text-sm text-gray-700 font-medium">Tarefas</span>
            </div>
            <button 
               onClick={handleOpenTaskCompleteModal}
               className={classNames("px-6 py-2 flex items-center gap-2 rounded-full", {
                  'bg-gray-100 text-gray-300': countTasksComplete === tasks?.length,
                  'bg-blue-100 text-blue-primary': countTasksComplete != tasks?.length
               })}
               disabled={countTasksComplete === tasks?.length}
            >
               <span className="w-4 h-4 border-[1.5px] border-blue-primary rounded-full block bg-gray-50"></span>
               <span className="text-sm font-medium">Concluir tarefas marcadas</span>
            </button>
         </header>

         <form>
            <div className="w-full h-[2px] bg-cyan-200"></div>

            <div className="flex flex-col px-8 py-5 gap-4">
               { tasks?.map(task => 
                  <Task key={task.id} id={task.id} title={task.title} status={task.complete} register={register} />
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

         <TaskCreate 
            idActivity={idAc as string}
            isOpenTaskModal={isOpenTaskModal}
            onCloseTaskModal={handleCloseTaskModal}
         />

         <CompleteTasks 
            isOpenModal={isOpenTasksCompleteModal}
            onCloseModal={handleCloseTaskCompleteModal}
            onConfirmComplete={completeScheduledTasks}
         />
      </div>
   )
}