import { Link, useParams } from "react-router-dom";

export function Home() {
   return (
      <div className="flex flex-col items-center justify-center h-[90vh]">      
         <h1>Home</h1>
         <Link to="/" className="text-sm text-blue-400 block">Home</Link>
      </div>
   )
}