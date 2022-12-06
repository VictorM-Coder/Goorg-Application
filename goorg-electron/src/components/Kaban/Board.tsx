
import { useState } from "react";
import { Activity } from "../../@types/Activity/Activity";
import { ActivityCreate } from "../Activity";
import { Column } from "./Collumn";

interface BoardProps {
  activitys: Activity[];
}

export function Board({ activitys }: BoardProps) {
   const [isOpenActivityModal, setIsOpenActivityModal] = useState(false);
   const [phaseActivity, setPhaseActivity] = useState("TO_DO");
   const handleOpenActivityModal = (phase: string) => {
      setPhaseActivity(phase) 
      setIsOpenActivityModal(true) 
   };
   const handleCloseActivityModal = () => setIsOpenActivityModal(false);
   const urlCurrent = window.location.href.split("/");

   return (
      <div className="bg-zinc-50 px-10 py-6 rounded shadow-sm w-full">
         <div className="grid grid-cols-3 gap-6 xlmax:grid-cols-1">
            <Column 
               name="To do"
               refPhase="TO_DO" 
               activitys={activitys.filter(activity => activity.phase == 'TO_DO')}
               onOpenActivityModal={handleOpenActivityModal}
            />
            <Column 
               name='Doing'
               refPhase="DOING" 
               activitys={activitys.filter(activity => activity.phase == 'DOING')}
               onOpenActivityModal={handleOpenActivityModal}
            />
            <Column 
               name='Done'
               refPhase="DONE" 
               activitys={activitys.filter(activity => activity.phase == 'DONE')}
               onOpenActivityModal={handleOpenActivityModal}
            />
         </div>

         <ActivityCreate 
            isOpenModal={isOpenActivityModal}
            onCloseModal={handleCloseActivityModal}
            phase={phaseActivity}
            isSelectWorkspace={urlCurrent.includes('atividades') ? true : false}
         />
      </div>
   )
}