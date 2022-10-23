import { NewWorkspace } from "./NewWorkspace";
import { Workspace } from "./Workspace";

export interface WorkspaceContextData {
   workspaces: Workspace[];
   addNewWorkspace: (data: NewWorkspace) => void;
   deleteWorkspaceById: (id: Number) => void;
}