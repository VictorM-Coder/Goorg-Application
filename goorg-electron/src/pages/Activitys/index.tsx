import { Kanban, NotePencil } from "phosphor-react";
import { ActivitiesRecents } from "../../components/Activity/ActivitiesRecents";
import { Header } from "../../components/Header";
import { Board } from "../../components/Kaban/Board";
import { useActivities } from "../../hooks"
import { ActivityOptions } from "./ActivityOptions";

export function Activitys() {
   const { activitys } = useActivities();

   return (
      <div >
         <Header />
         
         <div className="m-5 flex flex-col gap-6 mt-20">
            <div className="bg-white rounded flex overflow-hidden px-10 py-10 ">
               <div>
                  <h2 className="text-lg font-semibold text-gray-700 mb-[2px] flex items-center gap-1">
                     <NotePencil weight="fill" size={22} />
                     <span>Atividades</span>
                  </h2>
                  <span className="bg-violet-300 text-[13px] py-[1px] px-3 rounded-full font-medium text-violet-800">
                     {activitys.length} Atividades
                  </span>
                  <p className="text-sm text-gray-300 mt-2">
                     Relação de todas as atividades criadas.
                  </p>
               </div>
            </div>
            
            <ActivitiesRecents activities={activitys}/>
            <Board activitys={activitys}/>
         </div>

         <ActivityOptions />
      </div>
   )
}