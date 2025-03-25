import LoginForm from "../components/LoginForm.jsx";
import {Link, useNavigate} from "react-router-dom";
import {useState} from "react";
import {useUser} from "../context/UserProvider";
import Loading from "../components/loading/Loading.jsx";
import MessageBox from "../components/MessageBox.jsx";

function Login() {
    const navigate = useNavigate();
    const [isDisabled, setDisabled] = useState(false);
    const {message, reSetMessage, login} = useUser();
    const [isLoading, setLoading] = useState(false);

    const handleSignIn = async (userCredentials) => {
        setDisabled(true);
        setLoading(true);
        await login(userCredentials);
        setLoading(false);
    }

    if (isLoading) return <Loading/>;

    if (!message) {
        return <>
            <h2>Sign in</h2>
            <LoginForm
                disabled={isDisabled}
                onSave={handleSignIn}
            />
            <h2>...or create a new account</h2>
            <Link to="/register">
                <button type="button">Register</button>
            </Link>
        </>
    }

    if (message === "OK") {
        return <MessageBox
            text="You have successfully signed in"
            onOk={()=>{
                reSetMessage();
                navigate("/");
            }}
        />
    }

    return <MessageBox
        text={message}
        onOk={() => {
            reSetMessage();
            setDisabled(false);
        }}
    />
}

export default Login;