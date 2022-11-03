import { Workspace } from "./Workspace";
import { WorkspaceReq } from "./WorkspaceReq";

export interface WorkspaceContextData {
   workspaces: Workspace[];
   addNewWorkspace: (data: WorkspaceReq) => Promise<void>;
   editWorkspaceById: (id: Number, data: WorkspaceReq) => Promise<void>;
   deleteWorkspaceById: (id: Number) => Promise<void>;
}