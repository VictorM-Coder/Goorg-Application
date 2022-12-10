import classNames from 'classnames';
import { Clock, Plus } from 'phosphor-react';
import { useEffect, useState } from 'react';
import { useForm } from 'react-hook-form';
import { useParams } from 'react-router-dom';
import { TasksComplete } from '../../../../@types/Tasks/TasksComplete';
import { useActivities } from '../../../../hooks';
import { CompleteTasks, Task, TaskCreate } from '../Tasks';

import { ProgressBarTasks } from './ProgressBarTasks';
import { Timer } from './Timer';

interface CardTasksProps {
    tasks: {
       id: number;
       title: string;
       complete: boolean;
    }[] | undefined;
}
 
export function Pomodoro({ tasks }: CardTasksProps) {
    const { register, getValues } = useForm();
    const { completeTasks } = useActivities();
    const { idAc } = useParams();
 
    const [clockIsActived, setClockIsActived] = useState(false);
    const [countTasksComplete, setCountTasksComplete] = useState(0);
    const [isOpenTaskModal, setIsOpenTaskModal] = useState(false);
    const [isOpenTasksCompleteModal, setIsOpenTasksCompleteModal] = useState(false);

    const handleOpenTaskModal = () => setIsOpenTaskModal(true);
    const handleCloseTaskModal = () => setIsOpenTaskModal(false);

    const handleOpenTaskCompleteModal = () => setIsOpenTasksCompleteModal(true);
    const handleCloseTaskCompleteModal = () => setIsOpenTasksCompleteModal(false);
    

    function completeScheduledTasks() {
        const tasksChecked: TasksComplete[] = Object.entries(getValues())
        .filter(item => item[1] === true)
        .map(item => { return { id: Number(item[0]), complete: item[1] } });
  
        if (tasksChecked.length) {
            completeTasks(tasksChecked).then(() => {
              handleCloseTaskCompleteModal();
            });
        } 
    }
    
    useEffect(() => {
        let count = 0;
        tasks?.filter(item => item.complete === true).forEach(item => count++)
        setCountTasksComplete(count);
    }, [tasks])

    return (
        <div className="bg-white flex flex-col justify-center rounded shadow">
            <header className="flex items-center justify-between px-8 py-5 pb-4">
                <div className="flex items-center gap-2">
                    <span className="bg-green-300 p-2 inline-block rounded-full text-green-600">
                        <Clock size={20} />
                    </span>
                    <span className="text-sm text-gray-700 font-medium">Pomodoro</span>
                </div>
                <button 
                    onClick={handleOpenTaskCompleteModal}
                    className={classNames("px-6 py-2 flex items-center gap-2 rounded-full", {
                        'bg-gray-100 text-gray-300': countTasksComplete === tasks?.length || !clockIsActived,
                        'bg-blue-100 text-blue-primary': countTasksComplete != tasks?.length && clockIsActived,
                    })}
                    disabled={countTasksComplete === tasks?.length || !clockIsActived}
                >
                    <span className="w-4 h-4 border-[1.5px] border-blue-primary rounded-full block bg-gray-50"></span>
                    <span className="text-sm font-medium">Concluir tarefas marcadas</span>
                </button>
            </header>

            <div className="w-full h-[1px] bg-gray-200 mb-10"></div>

            <Timer onActivedPomodoro={() => setClockIsActived(!clockIsActived)}/>

            <div className="px-8 py-5 pb-4">
                <div className="flex flex-col gap-3 mb-3">
                    <ProgressBarTasks totalTasks={tasks?.length} totalTasksDone={tasks?.filter(t => t.complete === true).length}/> 
                </div>
                
                <form>
                    <div className="flex flex-col gap-4">
                        { tasks?.map(task => 
                            <Task 
                                key={task.id} 
                                id={task.id} 
                                title={task.title} 
                                status={task.complete} 
                                register={register} 
                            />
                        )}   
                        
                        <div className="block w-full p-4 mt-1 rounded border border-dashed border-blue-500
                            hover:bg-white transition-colors"
                            role="button"
                            onClick={handleOpenTaskModal}
                        >
                            <span className="text-sm font-medium flex items-center justify-center gap-1 text-blue-primary">
                                <Plus size={16} weight="bold"/>
                                Adicionar Tarefa
                            </span>
                        </div>
                    </div>     
                </form>

                <TaskCreate 
                    idActivity={idAc as string}
                    isOpenTaskModal={isOpenTaskModal}
                    onCloseTaskModal={handleCloseTaskModal}
                    isFromPomodoro={true}
                />

                <CompleteTasks 
                    isOpenModal={isOpenTasksCompleteModal}
                    onCloseModal={handleCloseTaskCompleteModal}
                    onConfirmComplete={completeScheduledTasks}
                />
            </div>
        </div>
    )
}