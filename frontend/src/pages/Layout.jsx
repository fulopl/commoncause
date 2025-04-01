import {Link, Outlet} from "react-router-dom";
import {useUser} from "../context/UserProvider.jsx";

function Layout() {
    const {user} = useUser();

    return <>
        <nav>
            <ul>
                <li>
                    <Link to="/">Main</Link>
                </li>
                {user === null ?
                    <>
                        <li>
                            <Link to="/login">Login</Link>
                        </li>
                        <li>
                            <Link to="/register">Register</Link>
                        </li>
                    </>
                    :
                    <>
                        <li>
                            <Link to="/groups">Groups</Link>
                        </li>
                        <li>
                            <Link to="/logout">Logout</Link>
                        </li>
                    </>
                }
            </ul>
        </nav>
        <Outlet/>
    </>
}

export default Layout;