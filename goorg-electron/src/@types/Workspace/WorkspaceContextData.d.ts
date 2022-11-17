import { Workspace } from "./Workspace";
import { WorkspaceReq } from "./WorkspaceCrud";

export interface WorkspaceContextData {
   workspaces: Workspace[];
   reateWorkspace: (data: WorkspaceCrud) => Promise<void>;
   updateWorkspace: (data: WorkspaceCrud) => Promise<void>;
   deleteWorkspace: (id: Number) => Promise<void>;
}