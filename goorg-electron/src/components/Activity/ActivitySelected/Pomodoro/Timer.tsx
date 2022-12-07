import { Clock, Play, Stop } from "phosphor-react";
import { useState, useEffect } from "react";

interface TimerProps {
   onActivedPomodoro: () => void;
}

export function Timer({ onActivedPomodoro }: TimerProps) {
    const [minutes, setMinutes] = useState<number>(0);
    const [seconds, setSeconds] = useState<number>(-1);

    const checkIfIsSmallerThan10 = (unidade: number) => {
        if (unidade < 10) return `0${unidade}`;
        return unidade;
    }
    
    useEffect(() => {
        if (seconds >= 1) {
            if (minutes !== 25) {
                setTimeout(() => {
                    setSeconds(seconds => seconds + 1);
                }, 1000)
                if (seconds === 60) {
                    setSeconds(0);
                    setMinutes(minutes => minutes + 1);
                }
            } else {
                setSeconds(-1);
                setMinutes(0);
                onActivedPomodoro();
            }            
        }
    }, [seconds])

    function startTimer() {
        setTimeout(() => {
          setSeconds(1);
        }, 1000);
        onActivedPomodoro();
    } 

    function stopTimer() {
        setMinutes(0);
        setSeconds(-2);
        onActivedPomodoro();
    }

    return (
        <div className="text-gray-600 flex flex-col items-center gap-3 font-semibold">
            <div className=" bg-gray-100 rounded-full w-48 h-48 flex justify-center items-center">
                {
                    seconds < 0 ?
                    <div className="flex flex-col items-center">
                        <div className="text-2xl">00:00</div>
                        <button className="flex gap-1 items-center" onClick={() => startTimer()}>
                            <Play size={14} weight="fill"/>
                            <span className="text-xs">Iniciar pomodoro</span>
                        </button>
                    </div>          
                    : <div className="flex flex-col items-center">
                        <div className="text-2xl">
                            {checkIfIsSmallerThan10(minutes)}:{checkIfIsSmallerThan10(seconds)} 
                        </div>
                        <button className="flex gap-1 items-center" onClick={() => stopTimer()}>
                            <Stop size={18} weight="fill" />
                            <span className="text-xs">Parar contador</span> 
                        </button>                      
                    </div> 
                }             
            </div>
            <div className="flex items-center gap-0.5 text-base">
                <Clock size={24}/>
                <div>25:00</div>
            </div>
        </div>
    )         
}