import './App.css'
import {createBrowserRouter, RouterProvider} from "react-router-dom";

const router = createBrowserRouter([]);

function App() {
    return (
        <div className={"App"}>
            <RouterProvider router={router}/>
        </div>
    );
}

export default App
