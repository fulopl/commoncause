import {useNavigate} from "react-router-dom";
import {useUser} from "../context/UserProvider";

function Logout() {
    const navigate = useNavigate();
    const {reSetMessage, logout} = useUser();

    logout();
    reSetMessage();

    return <>
        <h2>You have been signed out.</h2>
        <button type="button" onClick={() => navigate("/")}>
            Go to main page!
        </button>
    </>
}

export default Logout;