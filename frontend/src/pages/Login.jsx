import SignInForm from "../components/LoginForm.jsx";
import {Link, useNavigate} from "react-router-dom";
import {useState} from "react";
import {useUser} from "../context/UserProvider";

function Login() {
    const navigate = useNavigate();
    const [isDisabled, setDisabled] = useState(false);
    const {message, reSetMessage, login} = useUser();

    const handleSignIn = (userCredentials) => {
        setDisabled(true);
        login(userCredentials);
    }

    if (!message) {
        return <>
            <h2>Sign in</h2>
            <SignInForm
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
        return <>
            <h2>You have successfully signed in.</h2>
            <button type="button" onClick={() => navigate("/")}>
                Go to main page!
            </button>
        </>;
    }

    return <>
        <h2>{message}</h2>
        <button type="button" onClick={() => {
            reSetMessage();
            setDisabled(false);
        }}>
            OK
        </button>
    </>
}

export default Login;