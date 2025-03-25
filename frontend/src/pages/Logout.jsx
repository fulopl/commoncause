import {useNavigate} from "react-router-dom";
import {useUser} from "../context/UserProvider";
import MessageBox from "../components/MessageBox.jsx";

function Logout() {
    const navigate = useNavigate();
    const {reSetMessage, logout} = useUser();

    logout();
    reSetMessage();

    return <MessageBox
        text="You have been signed out"
        onOk={() => navigate("/")}
    />
}

export default Logout;