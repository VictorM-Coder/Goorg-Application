import { File } from "phosphor-react";
import { useState } from "react";
import { useForm } from "react-hook-form";
import { Task } from "./Task";

interface CardTasks {
   checkbox: {
      id: boolean;
   }[]
}

interface CardTasksProps {
   tasks: {
      id: number;
      title: string;
      status: boolean;
   }[] | undefined;
}

export function CardTasks({ tasks }: CardTasksProps) {
   const { register, handleSubmit, formState } = useForm<CardTasks>();
   const [countActivitysMark, setCountActivitysMark] = useState(0);
 
   function handleChangeCountActivitysMarks(complete: boolean) {
      (complete) 
      ? setCountActivitysMark(countActivitysMark + 1) 
      : setCountActivitysMark(countActivitysMark - 1)
   }

   function data(checkbox: any) {
      console.log(checkbox)
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
                     id={task.id} 
                     title={task.title}
                     register={register} 
                     handleMarkTask={handleChangeCountActivitysMarks}
                  />
               )}
            </div>    
         </form>
      </div>
   )
}