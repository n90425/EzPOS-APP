import React, { useState } from "react";

const Signup = () => {
    const [form, setForm] = useState({
        businessId: "",
        businessPwd: "",
        businessNum: "",
        businessName: "",
        ownerName: "",
        businessAdd: "",
        businessPostCode: "",
        businessPhone: "",
        businessEmail: "",
    });

    const handleChange = (e) => {
        setForm({
        ...form,
        [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
        const res = await fetch("http://localhost:8080/api/signup", {
            method: "POST",
            headers: {
            "Content-Type": "application/json",
            },
            body: JSON.stringify(form),
        });

        if (res.ok) {
            alert("회원가입 성공!");
            window.location.href = "/login";
            // navigate("/login"); // 로그인 페이지로 이동하고 싶을 경우
        } else {
            const msg = await res.text();
            alert("회원가입 실패: " + msg);
        }
        } catch (error) {
            console.error("에러 발생:", error);
            alert("서버 오류로 회원가입 실패");
        }
    };

    return (
        <div style={{ maxWidth: "600px", margin: "0 auto" }}>
        <h2>사업자 회원가입</h2>
        <form onSubmit={handleSubmit}>
            <input name="businessId" value={form.businessId} onChange={handleChange} placeholder="아이디" required /><br />
            <input name="businessPwd" type="password" value={form.businessPwd} onChange={handleChange} placeholder="비밀번호" required /><br />
            <input name="businessNum" value={form.businessNum} onChange={handleChange} placeholder="사업자등록번호" /><br />
            <input name="businessName" value={form.businessName} onChange={handleChange} placeholder="상점명" /><br />
            <input name="ownerName" value={form.ownerName} onChange={handleChange} placeholder="대표자명" /><br />
            <input name="businessAdd" value={form.businessAdd} onChange={handleChange} placeholder="주소" /><br />
            <input name="businessPostCode" value={form.businessPostCode} onChange={handleChange} placeholder="우편번호" /><br />
            <input name="businessPhone" value={form.businessPhone} onChange={handleChange} placeholder="전화번호" /><br />
            <input name="businessEmail" value={form.businessEmail} onChange={handleChange} placeholder="이메일" /><br />
            <button type="submit">회원가입</button>
        </form>
        </div>
    );
};

export default Signup;
