export interface ActivityEdit {
   title?: string;
   description?: string;
   priorityTag?: {
      id: number;
   },
   endDate?: Date;
   phase?: string;
}