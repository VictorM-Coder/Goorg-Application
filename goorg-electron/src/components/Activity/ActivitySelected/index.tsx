import { useParams } from "react-router-dom";
import { useActivities } from "../../../hooks";
import { Header } from "../../Header";
import { TagPhase, TagPriority } from "../Badges";
import { CardNotes } from "./CardNotes";
import { CardTasks } from "./CardTasks";

export function ActivitySelected() {
   const { activitys } = useActivities();
   const { id } = useParams();

   const activitySelected = activitys.find(ac => ac.id.toString() === id);
   
   return (
      <div className="flex flex-1 flex-col rounded">
         <Header />

         <div className="m-5 flex flex-col gap-6 mt-24">
            <div className="flex justify-between gap-4">
               <div className="bg-white rounded overflow-hidden px-10 py-10 h-full 
                  shadow-sm flex items-center w-full">
                  <div>
                     <div className="flex flex-col">
                        <span className="text-blue-primary font-medium">
                           {activitySelected?.title}
                        </span>
                        <span className="text-sm text-gray-500">
                           {activitySelected?.description}
                        </span>
                     </div>
                     <div className="flex items-center gap-2 pt-4 pb-1">
                        <TagPriority name={activitySelected?.priorityTag.name} size='sm'/>
                        <TagPhase name={activitySelected?.phase} size='sm'/>
                     </div>
                  </div>
               </div>

               {/* <CardNotes /> */}
            </div>

            <CardTasks />
         </div>
      </div>
   )
}