import { Check } from "phosphor-react";
import { useForm } from "react-hook-form";
import { Task } from "../../Tasks/TasksCard/Task";
import { ProgressBarTasks } from "./ProgressBarTasks";

interface CardTasksProps {
    tasks: {
       id: number;
       title: string;
    complete: boolean;
    }[] | undefined;
 }

export function TasksPomodoro({ tasks }: CardTasksProps) {
    const { register, getValues } = useForm();

    return (
        <div className="px-8 py-5 pb-4">
            <div className="flex flex-col gap-3 mb-3">
                <ProgressBarTasks totalTasks={tasks?.length} totalTasksDone={0}/> 
            </div>
            
            <form>
                <div className="flex flex-col gap-4">
                    { tasks?.map(task => 
                        <Task key={task.id} id={task.id} title={task.title} complete={task.complete} register={register} />
                    )}

                    <div
                        className="block w-full p-4 mt-1 rounded border bg-green-300 border-gray-200
                        hover:bg-green-400 transition-colors"
                        role="button"
                    >
                        <span className="text-[13px] font-medium flex items-center justify-center gap-1 text-gray-900">
                            <Check size={22} weight="fill" />
                            <span>Concluir tarefas marcadas</span>
                        </span>
                    </div>
                </div>     
            </form>
        </div>
    )
}