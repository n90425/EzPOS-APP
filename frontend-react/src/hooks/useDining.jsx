import axios from "axios";
import { useState } from "react";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;

const useDining = () => {
    const [tables, setTables] = useState([]);

    const fetchTables = async(businessId) => {
        try {
            const res = await axios.post(`${BASE_URL}/dining`, {
                businessId: "BIZ001"
            });
            console.log("응답 데이터: ", res.data);
            setTables(res.data);
        } catch(error){
            console.error("테이블 불러오는중 오류발생 fetchTables: ", error);
            setTables([]); // 에러 시 빈배열로 초기화
        }
    };

    return { tables, setTables, fetchTables };
};

export default useDining;