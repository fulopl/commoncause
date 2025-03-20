import {createContext, useCallback, useContext, useEffect, useState} from "react";

const UserContext = createContext({});

const setToken = (token) => window.localStorage.setItem("token", token);
const getToken = () => window.localStorage.getItem("token");

const UserProvider = ({children}) => {
    const [user, setUser] = useState();
    const [message, setMessage] = useState("");
    const [isLoading, setLoading] = useState(true);


    const getMe = useCallback(async () => {
        try {
            const res = await fetch("/api/user/me", {
                headers: {
                    authorization: `Bearer ${getToken()}`
                }
            });
            const response = await res.json();
            if (response.error) setUser(null);
            else setUser(response);
        } finally {
            setLoading(false)
        }
    }, []);

    useEffect(() => {
        getMe();
    }, []);

    const login = async (credentials) => {
        console.log(credentials)
        try {
            const res = await fetch("/api/user/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(credentials),
            });
            const response = await res.json();
            console.log(response);
            if (response.jwt) {
                setToken(response.jwt);
              //  await getMe();
                setMessage("OK");
            } else if (response.error === "Bad credentials") setMessage("Incorrect username or password. Please try again!");
            else setMessage(`An error occurred while processing your request.\n${response.error}\nPlease try again later!`);
        } catch (error) {
            setMessage("Server/network unavailable. Please try again later!");
        }
    };

    const logout = () => {
        setUser(null);
        setToken("");
    }

    const reSetMessage = () => {
        setMessage("");
    }

    return (
        <UserContext.Provider value={{user, message, reSetMessage, login, logout}}>
            {!isLoading && children}
        </UserContext.Provider>
    );
};

export const useUser = () => useContext(UserContext);

export default UserProvider;