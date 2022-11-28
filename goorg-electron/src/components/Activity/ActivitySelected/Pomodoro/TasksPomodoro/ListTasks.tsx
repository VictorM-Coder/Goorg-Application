import { Check } from "phosphor-react";
import { useEffect, useState } from "react";

import { Task } from "../../Tasks/TasksCard/Task";
import { ProgressBarTasks } from "./ProgressBarTasks";

interface CardTasksProps {
    tasks: {
       id: number;
       title: string;
       status: boolean;
    }[] | undefined;
}

export function ListTasks({ tasks }: CardTasksProps) {
    const [totalTasksDone, setTotalTasksDone] = useState(0);
    const totalTasks = tasks? tasks.length : 0

    //Register aleatorio para a renderização das Tasks não dar erro
    let register = function(): any {

    }

    function setTasksFinished(list:{id: number, title: string, status: boolean}[] | undefined) {
        //Aqui vai mudar a quantidade de tasks feitas
        //setTotalTasksDone(totalTasksDone => totalTasksDone + *total que foram marcadas*)
    }

    if (totalTasksDone === totalTasks) {
        return (
        <div>
            <div className="flex flex-col gap-3 mx-8 mb-2">
                <ProgressBarTasks totalTasks={totalTasks} totalTasksDone={totalTasksDone}/>
                <div className="flex flex-col gap-3">
                    {tasks?.map(task => {
                            if (!task.status) return (<Task key={task.id} id={task.id} title={task.title} status={task.status} register={register} />)   
                        }  
                    )}                
                </div>     
            </div>
        </div>
        )
    } else {
        return (
            <div className="mx-8 mb-3">
                <div className="flex flex-col gap-3 mb-3">
                    <ProgressBarTasks totalTasks={totalTasks} totalTasksDone={totalTasksDone}/>
                    <div className="flex flex-col gap-3">
                        {tasks?.map(task => {
                                if (!task.status) return (<Task key={task.id} id={task.id} title={task.title} status={task.status} register={register} />)   
                            }  
                        )}                
                    </div>     
                </div>
                <div className="flex rounded py-3 border-solid border-2  border-green-300">
                    <button className="w-full flex justify-center items-center font-semibold" onClick={() => setTasksFinished(tasks)}>
                        <Check className="text-green-500" size={24} />
                        <span className="ml-1">
                            Concluir tarefas marcadas   
                        </span>
                    </button>
                </div>
            </div>
        )        
    }


}