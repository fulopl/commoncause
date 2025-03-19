import {useState} from "react";

const UserForm = ({user, disabled, onSave}) => {

    const [email, setEmail] = useState(user?.username ?? "admin@example.com");
    const [password, setPassword] = useState(user?.password ?? "");

    const handleSubmit = (event) => {
        event.preventDefault();
        onSave({email: email, password: password});
    }

    return <>
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="email">Email address:</label>
                <input
                    type="email"
                    value={email}
                    name="email"
                    id="email"
                    placeholder="Enter your registered email address"
                    onChange={(e) => setEmail(e.target.value)}
                />
            </div>
            <div>
                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    value={password}
                    name="password"
                    id="password"
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>
            <div>
                <button type="submit" disabled={disabled}>
                    Log in
                </button>
            </div>
        </form>
    </>
}

export default UserForm;