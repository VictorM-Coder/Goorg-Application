import { Task } from "../../Tasks/TasksCard/Task";
import { ListTasks } from "./ListTasks";
import { ProgressBarTasks } from "./ProgressBarTasks";

interface CardTasksProps {
    tasks: {
       id: number;
       title: string;
       status: boolean;
    }[] | undefined;
 }

export function TasksPomodoro({ tasks }: CardTasksProps) {
    return (
        <div>
            {/* <ProgressBarTasks tasks={tasks}/> */}
            <ListTasks tasks={tasks} />
        </div>
    )
}