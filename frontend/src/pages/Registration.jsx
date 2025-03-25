import UserForm from "../components/UserForm";
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import Loading from "../components/loading";
import MessageBox from "../components/MessageBox.jsx";

const registerUser = (user) => {
    return fetch("/api/user/register",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user)
        })
        .then(res => {
            if (!res.ok) return res.json();
            return {message: "ok"}
        })
        .then(body => body.message ?? "")
}

function Registration() {
    const navigate = useNavigate();
    const [isLoading, setLoading] = useState(false);
    const [message, setMessage] = useState("");

    const handleRegister = (user) => {
        setLoading(true);
        registerUser(user).then(response => {
                setLoading(false);
                setMessage(response);
            }
        );
    }

    if (isLoading) {
        return <Loading/>;
    }

    if (message === "ok") {
        return (<MessageBox
                text="Account created. Please sign in!"
                onOk={() => navigate("/login")}
            />
        );
    }

    if (message) {
        return <MessageBox
            text={message}
            onOk={() => setMessage("")}
        />
    }

    return <>
        <h2>Registration</h2>
        <UserForm
            user={{username: ""}}
            disabled={isLoading}
            onSave={handleRegister}
        />
    </>
}

export default Registration;