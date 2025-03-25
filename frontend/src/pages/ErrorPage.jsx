import {useRouteError} from "react-router-dom";

function ErrorPage() {

    const error = useRouteError();
    console.error(error);
    return (
        <div style={{padding: "20px", textAlign: "center"}}>
            <h1>Oops! Something went wrong.</h1>
            <p>{error ? error.message : "An unexpected error occurred. Please try again later!"}</p>
            <a href="/">Go back to Main Page</a>
        </div>
    );
}

export default ErrorPage;