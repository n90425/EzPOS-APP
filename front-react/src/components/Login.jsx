import React, {useState, useContext} from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../contexts/AuthContext";

const Login = () => {
    const { setIsAuthenticated } = useContext(AuthContext);
    const [id, setId] = useState("");
    const [pwd, setPwd] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const res = await fetch("http://localhost:8080/api/login", {
                method: "POST",
                headers: {
                "Content-Type": "application/json",
                },
                body: JSON.stringify({ businessId: id, businessPwd: pwd }),
            });

        if (res.ok) {
            const data = await res.json();
            localStorage.setItem("token", data.token); // JWT 저장
            setIsAuthenticated(true);
            navigate("/");
        } else {
            alert("아이디 또는 비밀번호가 잘못되었습니다.");
        }
        } catch (err) {
        console.error("로그인 오류:", err);
        alert("서버 오류가 발생했습니다.");
        }
    };

    const Signup = () => {
        navigate("/signup");
    };


    return (
        <div>
            <h2>로그인</h2>
            <input value={id} onChange={(e) => setId(e.target.value)} placeholder="아이디" />
            <input type="password" value={pwd} onChange={(e) => setPwd(e.target.value)} placeholder="비밀번호" />
            <button onClick={handleLogin}>로그인</button>
            <button onClick={Signup}>회원가입</button>
        </div>
    );
};

export default Login;