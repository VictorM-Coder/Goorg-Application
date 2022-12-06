import { Clock } from "phosphor-react";

import { Timer } from "./Timer/index";
import { TasksPomodoro } from "./TasksPomodoro/index";

interface CardTasksProps {
    tasks: {
       id: number;
       title: string;
       status: boolean;
    }[] | undefined;
}
 
export function Pomodoro({ tasks }: CardTasksProps) {
    const tasksNotCompleted = tasks?.filter(task => task.status == false)

    return (
        <div className="bg-white flex flex-col justify-center rounded shadow">
            <header className="flex items-center gap-2 px-8 py-5 pb-4">
                <span className="bg-green-300 p-2 rounded-full">
                    <Clock className=" text-green-500" size={20} />
                </span>
                <span className="text-sm text-gray-700 font-medium">Pomodoro</span> 
            </header>
            <hr className="mb-4"/>
            <Timer />
            <TasksPomodoro tasks={tasksNotCompleted}/>
        </div>
    )
}