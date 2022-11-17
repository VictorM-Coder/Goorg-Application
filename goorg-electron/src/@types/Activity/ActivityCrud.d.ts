export interface ActivityCrud {
   id?: number;
   title: string;
   description: string;
   priorityTag: {
      id: number;
   },
   phase?: string;
   endDate: string;
   workspace: {
      id: Number;
   } 
}