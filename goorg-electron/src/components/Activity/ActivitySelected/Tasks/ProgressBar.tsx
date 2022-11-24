import { LinearProgress } from "@mui/material";
import { useEffect, useState } from "react";

interface ProgressBarProps {
   valueCurrent: number | undefined;
   valueMax: number | undefined;
}

export function ProgressBar({ valueCurrent = 0, valueMax = 0 }: ProgressBarProps) {
   const [valueOfProgress, setValueOfProgress] = useState(0);

   useEffect(() => {
      if (valueCurrent >= valueMax) {
         valueCurrent = valueMax;
      }

      const value = 100 / valueMax;
      setValueOfProgress(value);

   }, [valueCurrent, valueMax])

   return (
      <>
        <LinearProgress 
            value={(valueCurrent > valueMax) ? valueOfProgress * valueMax : valueOfProgress * valueCurrent}
            variant="determinate"
            className="rounded-full py-[10px] w-60"
            color="primary"
         />
         <span className="text-xs font-medium text-gray-300">{valueCurrent} / {valueMax}</span>
      </>
   )
}