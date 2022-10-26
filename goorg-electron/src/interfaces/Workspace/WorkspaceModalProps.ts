export interface WorkspaceModalProps {
   workspaceId?: string;
   isEditWorkspace: boolean;
   isOpenWorkspaceModal: boolean;
   onCloseWorkspaceModal: () => void;
}