export interface ActivityReq {
   title: string;
   description: string;
   priorityTag: {
      name: string;
   },
   phase: string;
   endDate: Date;
   workspace: {
      id: Number;
   } 
}