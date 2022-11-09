import { Notepad } from "phosphor-react";
import { useParams } from "react-router-dom";
import { useActivities } from "../../../hooks";
import { Backpage } from "../../Backpage";
import { Header } from "../../Header";
import { CardTasks } from "./CardTasks";
import { HeaderActivitySelected } from "./Header";

export function ActivitySelected() {
   const { activitys } = useActivities();
   const { id } = useParams();

   const activitySelected = activitys.find(ac => ac.id.toString() === id);
   
   return (
      <div className="flex flex-1 flex-col rounded">
         <Header />

         <div className="m-5 mt-[86px]">
            <Backpage title='Workspace'/>

            <div className="flex flex-col gap-6">
               <HeaderActivitySelected 
                 title={activitySelected?.title}
                 description={activitySelected?.description}
                 priorityTagName={activitySelected?.priorityTag.name}
                 phaseName={activitySelected?.phase}
                 startDate={activitySelected?.startDate}
                 endDate={activitySelected?.endDate}
               />
               <CardTasks tasks={activitySelected?.tasks}/>
            </div>

         </div>
      </div>
   )
}