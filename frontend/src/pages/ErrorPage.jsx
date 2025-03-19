import {useRouteError} from "react-router-dom";

function ErrorPage() {

    const error = useRouteError();
    console.error(error);
    return (
        <div style={{padding: "20px", textAlign: "center"}}>
            <h1>Oops! Something went wrong.</h1>
            <p>{error.statusText || error.message}</p>
            <a href="/">Go back to Home</a>
        </div>
    );
}

export default ErrorPage;