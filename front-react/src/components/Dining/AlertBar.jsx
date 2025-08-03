import "./AlertBar.css";

function AlertBar({message, isVisible}) {
    return (
        <div className={`alert-bar ${isVisible ? 'isVisible': ''}`}>
            {message}
        </div>
    )
}

export default AlertBar