function MessageBox({text, onOk}) {
    return <>
        <h2>{text || "An unexpected error occurred. Please try again later!"}</h2>
        <button type="button" onClick={onOk}>
            OK
        </button>
    </>
}

export default MessageBox;