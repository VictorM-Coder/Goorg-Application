export interface ActivityReq {
   title: string;
   description: string;
   priorityTag: {
      id: number;
   },
   phase: string;
   endDate: string;
   workspace: {
      id: Number;
   } 
}