import { useEffect, useState } from 'react';
import LinearProgress from '@mui/material/LinearProgress';
import { Checks, ClockAfternoon } from 'phosphor-react';

interface ProgressBarTasksProps {
    totalTasks: number | undefined;
    totalTasksDone: number | undefined;
} 

export function ProgressBarTasks({totalTasks, totalTasksDone = 0}: ProgressBarTasksProps) {
    const [progress, setProgress] = useState(0);
    const [totalTasksPending, setTotalTasksPending] = useState(() => {
        if (totalTasks) return totalTasks - totalTasksDone;
        else return 0;
    })

    useEffect(() => {
        let progress = 0;

        if (totalTasks) {
            setTotalTasksPending(totalTasks - totalTasksDone);
            progress = (totalTasksDone / totalTasks) * 100 ;            
        }

        setProgress(progress);              
    }, [totalTasksDone, totalTasks]);

    const absoluteTotalTasks = totalTasksPending + totalTasksDone;

    return (
        <div className='flex flex-col my-6 text-xs gap-5'>
            <div className='flex justify-center items-center gap-2 font-semibold'>
                <div>0</div>
                <LinearProgress className="w-96 p-1 rounded-md" variant="determinate" color="primary" value={progress} />
                <div>{absoluteTotalTasks}</div>
            </div>
            <div className='text-gray-600 flex justify-center gap-8 font-semibold'>
                <div className='flex items-center gap-1'>
                    <ClockAfternoon className='text-amber-500' size={24} />
                    <h2>{totalTasksPending} Pendentes</h2>
                </div>
                <div className='flex items-center gap-1'> 
                    <Checks className='text-green-600' size={24} />
                    <h2>{totalTasksDone} Concluidas</h2>
                </div>
            </div>
        </div>
    )
}