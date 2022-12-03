
import { useState } from "react";
import { Activity } from "../../@types/Activity/Activity";
import { ActivityCreate } from "../Activity";
import { Column } from "./Collumn";

interface BoardProps {
  activitys: Activity[];
  linkRedirectActivity: string;
}

export function Board({ activitys, linkRedirectActivity }: BoardProps) {
   const [isOpenActivityModal, setIsOpenActivityModal] = useState(false);
   const [phaseActivity, setPhaseActivity] = useState("TO_DO");
   const handleOpenActivityModal = (phase: string) => {
      setPhaseActivity(phase) 
      setIsOpenActivityModal(true) 
   };
   const handleCloseActivityModal = () => setIsOpenActivityModal(false);
   
   return (
      <div className="bg-zinc-50 px-10 py-6 rounded shadow-sm w-full">
         <div className="grid grid-cols-3 gap-6 xlmax:grid-cols-1">
            <Column 
               name="To do"
               refPhase="TO_DO" 
               activitys={activitys.filter(activity => activity.phase == 'TO_DO')}
               linkRedirectActivity={linkRedirectActivity}
               onOpenActivityModal={handleOpenActivityModal}
            />
            <Column 
               name='Doing'
               refPhase="DOING" 
               activitys={activitys.filter(activity => activity.phase == 'DOING')}
               linkRedirectActivity={linkRedirectActivity}
               onOpenActivityModal={handleOpenActivityModal}
            />
            <Column 
               name='Done'
               refPhase="DONE" 
               activitys={activitys.filter(activity => activity.phase == 'DONE')}
               linkRedirectActivity={linkRedirectActivity}
               onOpenActivityModal={handleOpenActivityModal}
            />
         </div>

         <ActivityCreate 
            isOpenModal={isOpenActivityModal}
            onCloseModal={handleCloseActivityModal}
            phase={phaseActivity}
            isSelectWorkspace={linkRedirectActivity.includes('atividades') ? true : false}
         />
      </div>
   )
}