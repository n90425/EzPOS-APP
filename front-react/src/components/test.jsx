
import React, { useEffect, useState } from "react";

const Test = () => {
  const [message, setMessage] = useState("");


console.log("요청 주소:", `${import.meta.env.VITE_API_BASE_URL}/test`);

useEffect(() => {
  fetch(`${import.meta.env.VITE_API_BASE_URL}/test`)
    .then((res) => res.text())
    .then((data) => {
      console.log("백엔드 응답:", data); 
      setMessage(data);
    })
    .catch((err) => {
      console.error("API 요청 실패:", err);
    });
}, []);

  return (
    <div>
      백엔드 응답 메시지: {message}
    </div>
  );
};

export default Test;