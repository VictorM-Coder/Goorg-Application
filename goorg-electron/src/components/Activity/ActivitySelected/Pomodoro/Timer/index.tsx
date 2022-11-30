import { Clock, Play, Stop } from "phosphor-react";
import { useState, useEffect } from "react";

const checkIfIsSmallerThan10 = (unidade: number) => {
    if (unidade < 10) return `0${unidade}`;
    return unidade;
}

export function Timer() {
    const [minutes, setMinutes] = useState<number>(0);
    const [seconds, setSeconds] = useState<number>(-1);
    
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
                console.log("Acabou");
            }            
        }
    }, [seconds])

    function startTimer() {
        setTimeout(() => {
          setSeconds(1);
        }, 1000);
    } 

    function stopTimer() {
        setMinutes(0);
        setSeconds(-2);
    }

    if (seconds < 0) {
        return (
            <div className="text-gray-600 flex flex-col items-center gap-2 font-semibold">
                <div className=" bg-gray-100 flex flex-col items-center g-1 rounded-full p-7 py-16">
                    <div className="text-2xl">00:00</div>
                    <div className="flex gap-0.5">
                        <Play size={16} weight="fill"/>
                        <button className="text-xs" onClick={() => startTimer()}>Iniciar pomodoro</button> 
                    </div>        
                </div>
                <div className="flex items-center gap-0.5 text-base">
                    <Clock size={24}/>
                    <div>25:00</div>
                </div>
            </div>
        )         
    } else {
        return (
            <div className="text-gray-600 flex flex-col items-center gap-2 font-semibold">
                <div className="bg-gray-100  flex flex-col items-center g-1 rounded-full p-8 py-16">
                    <div className="text-2xl">
                        {checkIfIsSmallerThan10(minutes)}:{checkIfIsSmallerThan10(seconds)} 
                    </div>
                    <div className="flex gap-0.5">
                        <Stop size={18} weight="fill" />
                        <button className="text-xs" onClick={() => stopTimer()}>Parar contador</button> 
                    </div>                      
                </div>
                <div className="flex items-center gap-0.5 text-base">
                    <Clock size={24}/>
                    <div>25:00</div>
                </div>
            </div>
        )          
    }
}