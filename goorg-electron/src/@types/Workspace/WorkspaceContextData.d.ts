import { Workspace } from "./Workspace";
import { WorkspaceReq } from "./WorkspaceReq";

export interface WorkspaceContextData {
   workspaces: Workspace[];
   addNewWorkspace: (data: WorkspaceReq) => Promise<void>;
   updateWorkspace: (data: WorkspaceReq) => Promise<void>;
   deleteWorkspaceById: (id: Number) => Promise<void>;
}