import { useContext, useEffect } from "react";
import useDining from "../../hooks/useDining";
import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;

function Dining() {
    const {tables, fetchTables} = useDining();

    useEffect(() => {
        fetchTables("BIZ001");
    }, []);

    return (
        <div className="dining-container">

            
        </div>
    )
}

export default Dining;