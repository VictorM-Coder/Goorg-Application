import classNames from "classnames";
import { Plus } from "phosphor-react";
import { Activity } from "../../@types/Activity/Activity";
import { ActivityCard } from "../Activity/ActivityCard";

interface CollumProps {
   name: string;
   activitys: Activity[];
}

export function Column({ name, activitys }: CollumProps) {
   return (
      <div>
         <span className={classNames('border-b-2  text-sm font-medium block', {
            'border-b-blue-600': name === 'To do',
            'border-b-orange-400': name === 'In progress',
            'border-b-green-500': name === 'Done',
         })}>
            { name }
         </span>
         <div className="flex justify-center flex-wrap gap-4 mt-4 max-h-[400px] overflow-y-auto scrollbar 
            transition-all pr-2"
         >
            { activitys.map(activity => (
               <ActivityCard
                  key={activity.id}
                  id={activity.id}
                  title={activity.title} 
                  description={activity.description} 
                  priorityTag={activity.priorityTag}
                  phase={activity.phase}
                  endDate={activity.endDate}
                  workspaceId={activity.workspaceId}
                  nameVisible={false}
               />
            ))}                 
         </div>
         <button className="block w-full p-4 mt-6 rounded border border-dashed border-gray-200 hover:bg-white transition-colors">
            <span className="text-sm font-medium flex items-center justify-center gap-1 text-blue-primary">
               <Plus size={16} weight="bold"/>
               Adicionar Atividade
            </span>
         </button>
      </div>
   )
}