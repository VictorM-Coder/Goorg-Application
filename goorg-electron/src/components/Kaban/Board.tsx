
import { Activity } from "../../@types/Activity/Activity";
import { Column } from "./Collumn";

interface BoardProps {
  activitys: Activity[];
}

export function Board({ activitys }: BoardProps) {
   return (
      <div className="bg-zinc-50 px-10 py-6 rounded shadow-sm w-full">
         <div className="grid grid-cols-3 gap-6 xlmax:grid-cols-1">
            <Column 
               name='To do' 
               activitys={activitys.filter(activity => activity.phase == 'TO_DO')}
            />
            <Column 
               name='In progress'
               activitys={activitys.filter(activity => activity.phase == 'IN_PROGRESS')}
            />
            <Column 
               name='Done' 
               activitys={activitys.filter(activity => activity.phase == 'DONE')}
            />
         </div>
      </div>
   )
}