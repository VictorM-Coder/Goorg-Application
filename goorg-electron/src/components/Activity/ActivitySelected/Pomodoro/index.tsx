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
    const tasksNotCompleted = tasks?.filter(task => {
        if (!task.status) return task
    })
    return (
        <div className="bg-white flex flex-col justify-center rounded shadow">
            <header className="flex items-center gap-2 p-3 px-6">
                <Clock className="bg-green-300 text-gray-500 p-2 rounded-full" size={36} />
                <h1 className="font-medium text-base">Pomodoro</h1> 
            </header>
            <hr className="mb-4"/>
            <Timer />
            <TasksPomodoro tasks={tasksNotCompleted}/>
        </div>
    )
}