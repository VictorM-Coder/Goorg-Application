import { NotePencil } from "phosphor-react";
import { ActivityCardProps } from "../../../@types/Activity";
import { settingsSlider } from "../../../utils";
import { Slide, Slider } from "../../Slider";
import { ActivityCard } from "../ActivityCard";
import { ActivityAddCard } from "../ActivityCardAdd";

interface ActivitiesRecentsProps {
   activities: ActivityCardProps[];
}

export function ActivitiesRecents({ activities }: ActivitiesRecentsProps) {
   return (
      <div className="bg-white overflow-hidden rounded">
         <header className="flex items-center justify-between px-8 py-5">
            <div className="flex items-center gap-2">
               <span className="bg-orange-200 p-2 inline-block rounded-full text-orange-500">
                  <NotePencil weight="fill" size={22} />
               </span>
               <span className="text-sm text-gray-700 font-medium">Atividades mais Recentes</span>
            </div>
         </header>

         <div className="w-full h-[1px] bg-gray-200"></div>

         <div className="mt-3 p-8 gap-4">
            <Slider settings={settingsSlider}>
               <Slide className="w-auto z-0">
                  <ActivityAddCard />
               </Slide>
               { activities.map(activity => (
                  <Slide className="w-auto z-0" key={activity.id}>
                     <ActivityCard
                        id={activity.id}
                        title={activity.title}
                        description={activity.description}
                        priorityTag={activity.priorityTag}
                        workspaceId={activity.workspaceId}
                        startDate={activity.startDate}
                        endDate={activity.endDate}
                        phase={activity.phase}
                        tasks={activity.tasks}
                        link={`/atividades/workspace/${activity.id}/atividade/${activity.id}`}
                        nameVisible={true}
                        minWidth={true}
                     />  
                  </Slide>
               ))}
            </Slider>
         </div>
      </div>
   )
}