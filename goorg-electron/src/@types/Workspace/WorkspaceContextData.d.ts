import { NewWorkspace } from "./NewWorkspace";
import { Workspace } from "./Workspace";

export interface WorkspaceContextData {
   workspaces: Workspace[];
   addNewWorkspace: (data: NewWorkspace) => Promise<void>;
   editWorkspaceById: (id: Number, data: NewWorkspace) => Promise<void>;
   deleteWorkspaceById: (id: Number) => Promise<void>;
}