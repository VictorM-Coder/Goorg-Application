import { useContext } from "react";
import { LatestDatasContext } from "../contexts/LatestDatas";

export function useLatestDatas() {
   const context = useContext(LatestDatasContext);
   return context;
}