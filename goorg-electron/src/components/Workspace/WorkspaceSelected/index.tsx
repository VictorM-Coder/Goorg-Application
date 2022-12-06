import { Link, useParams } from 'react-router-dom';
import { useActivities, useWorkspaces } from '../../../hooks';
import { settingsSlider } from '../../../utils';
import { ActivityCard } from '../../Activity/ActivityCard';
import { Backpage } from '../../Backpage';
import { Header } from '../../Header';
import { Board } from '../../Kaban/Board';
import { Slide, Slider } from '../../Slider';
import { WorkspaceOptions } from './WorkspaceOptions';

export function WorkspaceSelected() {
   const { workspaces } = useWorkspaces();
   const { activitys } = useActivities();
   const { id } = useParams();

   const workSelected = workspaces.find(workspace => workspace.id.toString() === id);
   const activitiesFilter = activitys.filter(ac => ac.workspaceId.toString() === id)

   return (
      <div className="flex flex-1 flex-col rounded">
         <Header />
         
         <div className='m-5 mt-[86px]'>
            <Backpage title='inicio'/>

            <div className="flex flex-col gap-6">
               <div className="flex flex-col bg-white rounded overflow-hidden px-10 py-10 shadow-sm">
                  <span className={`text-blue-600 flex items-center gap-1 font-medium`}>
                     { workSelected?.name }
                  </span>
                  <span className="text-[13px] text-gray-500">
                     { workSelected?.description }
                  </span>
                  <span className="text-[13px] mt-3 py-0.5 px-3 self-start rounded-full font-medium bg-blue-200 text-blue-600">
                     { workSelected?.countActivities } Atividades
                  </span>
               </div>
               <Slider settings={settingsSlider}>
                  {  activitiesFilter.map(activity => (
                        <Slide key={activity.id} className="z-0 w-auto">
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
                              link={`${activity.workspaceId}/atividade/${activity.id}`}
                              nameVisible={true}
                              minWidth={true}
                           />
                        </Slide>
                     ))
                  }
               </Slider>
               <Board activitys={activitiesFilter} />
            </div>
            <WorkspaceOptions idWorkspace={id}/>
         </div>
      </div>
   )
}