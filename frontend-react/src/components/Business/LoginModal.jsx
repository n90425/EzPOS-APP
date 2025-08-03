import React from "react";
import "./LoginModal.css";

function LoginModal({onClose}) {
    return (
        <div className="modal-overlay">
            <div className="modal-content">
                <button className="close-btn" onClick={onClose}>X</button>
                <h2>로그인</h2>
                <input type="text" placeholder="사업자번호" />
                <input type="text" placeholder="비밀번호" />
                <button className="login-btn">로그인</button>
                <div className="links">
                    <a href="#">회원가입</a> | <a href="#">아이디</a> | <a href="#">비밀번호 찾기</a> 
                </div>
            </div>
        </div>
    )
}

export default LoginModal;