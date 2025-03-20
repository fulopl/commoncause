import {useState} from "react";

const UserForm = ({user, disabled, onSave}) => {

    const [email, setEmail] = useState(user?.email ?? "");
    const [password, setPassword] = useState(user?.password ?? "");

    const handleSubmit = (event) => {
        event.preventDefault();
        onSave({email: email, password: password});
    }

    return <>
        <form autoComplete="off" onSubmit={handleSubmit}>
            <div>
                <label htmlFor="email">E-mail address:</label>
                <input
                    type="email"
                    autoComplete="off"
                    placeholder="enter e-mail address here"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    name="email"
                    id="email"
                />
            </div>
            <div>
                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    autoComplete="off"
                    placeholder="enter password here"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    name="password"
                    id="password"
                />
            </div>
            <div className="buttons">
                <button type="submit" disabled={disabled}>
                    {user.email ? "Update user data" : "Register"}
                </button>
            </div>
        </form>
    </>
}

export default UserForm;