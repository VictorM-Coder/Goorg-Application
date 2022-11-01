export interface ActivityReq {
   name: string;
   description: string;
   priorityTag: {
      name: string;
   },
   phase: string;
   endDate: Date;
   workspace: {
      id: number;
   },
}