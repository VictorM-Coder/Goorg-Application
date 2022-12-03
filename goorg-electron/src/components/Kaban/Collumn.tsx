import classNames from "classnames";
import { Plus } from "phosphor-react";
import { Activity } from "../../@types/Activity/Activity";
import { ActivityCard } from "../Activity/ActivityCard";

interface CollumProps {
   name: string;
   refPhase: string;
   activitys: Activity[];
   linkRedirectActivity: string;
   onOpenActivityModal: (phase: string) => void;
}

export function Column({ name, refPhase, activitys, linkRedirectActivity, onOpenActivityModal }: CollumProps) {
   return (
      <div>
         <span className={classNames('border-b-2  text-sm font-medium block', {
            'border-b-blue-600': name === 'To do',
            'border-b-orange-400': name === 'Doing',
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
                  startDate={activity.startDate}
                  endDate={activity.endDate}
                  workspaceId={activity.workspaceId}
                  tasks={activity.tasks}
                  nameVisible={false}
                  link={`${linkRedirectActivity}/workspace/${activity.workspaceId}/atividade/${activity.id}`}
               />
            ))}                 
         </div>
         <button 
            onClick={() => onOpenActivityModal(refPhase)}
            className="block w-full p-4 mt-6 rounded border border-dashed border-gray-200 hover:bg-white transition-colors">
            <span className="text-sm font-medium flex items-center justify-center gap-1 text-blue-primary">
               <Plus size={16} weight="bold"/>
               Adicionar Atividade
            </span>
         </button>
      </div>
   )
}