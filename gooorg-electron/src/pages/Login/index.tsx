import { Link } from "react-router-dom";

export function Login() {
   return (
      <div className="flex flex-col items-center justify-center h-[90vh]">      
         <h1>Login</h1>
         <Link to="/home" className="text-sm text-blue-400 block">Home</Link>
      </div>
   )
}