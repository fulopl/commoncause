import './App.css'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import MainPage from "./pages/MainPage.jsx";
import Layout from "./pages/Layout.jsx";
import ErrorPage from "./pages/ErrorPage.jsx";
import Groups from "./pages/Groups.jsx";
import Registration from "./pages/Registration.jsx";
import Logout from "./pages/Logout.jsx";
import Login from "./pages/Login.jsx";

function App() {
    const router = createBrowserRouter([
        {
            path: "/",
            element: <Layout/>,
            errorElement: <ErrorPage/>,
            children: [
                {
                    path: "/main",
                    element: <MainPage/>,
                    errorElement: <ErrorPage/>,
                },
                {
                    path: "/groups",
                    element: <Groups/>,
                    errorElement: <ErrorPage/>,  //TODO remove this line if redundant
                },
                {
                    path: "/login",
                    element: <Login/>,
                    errorElement: <ErrorPage/>,
                },
                {
                    path: "/logout",
                    element: <Logout/>,
                    errorElement: <ErrorPage/>,
                },
                {
                    path: "/register",
                    element: <Registration/>,
                    errorElement: <ErrorPage/>,
                },
            ]
        }])

    return <RouterProvider router={router}/>

}

export default App
