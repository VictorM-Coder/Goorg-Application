import { File } from "phosphor-react";
import { useForm } from "react-hook-form";
import { Task } from "./Task";

interface CardTasks {
   chechbox: {
      id: boolean;
   }[]
}

export function CardTasks() {
   const { register, handleSubmit } = useForm<CardTasks>();

   function data(checkbox: any) {
      console.log(checkbox)
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
         </header>

         <div className="w-full h-[2px] bg-cyan-200"></div>

         <form className="px-8 py-6 gap-4" onSubmit={handleSubmit(data)}>
            <div className="flex flex-col gap-4">
               <Task register={register} id="1"/>
               <Task register={register} id="2"/>
            </div>    
         </form>
      </div>
   )
}