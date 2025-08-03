import { useEffect, useRef, useState } from "react";
import "./SettingDropDown.css";
import { useNavigate } from "react-router-dom";

function SettingDropDown({showAlert, onTableMove, onTableMerge}) {
    const [isOpen, setIsOpen] = useState(false); // dropDown 상태추가
    const dropdownRef = useRef(null); // 드롭다운 밖을 마우스클릭할경우 드롭다운 닫힘
    const toggleDropdown = () => setIsOpen(!isOpen);

    // 드롭다운 밖을선택할때 상태를 false로변경
    const handleClickOutside = (event) => {
        if(dropdownRef.current && !dropdownRef.current.contains(event.target)) {
            setIsOpen(false);
        }
    }

    // 드롭다운 밖을 선택할때 이벤트 추가,삭제
    useEffect(() => {
        document.addEventListener("mousedown", handleClickOutside);
        return() => {
            document.removeEventListener("mousedown", handleClickOutside);
        }
    }, []);

    const tableNavi = useNavigate();

    // 테이블 편집을 눌렀을때 이동
    const handleEditTable = () => {
        tableNavi("/dining");
    }

    // 매출요약을 눌렀을때 이동
    const handleSaleSummary = () => {
        tableNavi("/summary")
    }

    return (
        <div className="top-bar">
            <button className="top-bar-button" onClick={handleSaleSummary}>매출요약</button>
            <button className="top-bar-button" onClick={onTableMove}>자리이동</button>
            <button className="top-bar-button" onClick={onTableMerge}>합석</button>
            <div className="settings-container" ref={dropdownRef}>
                <button className="top-bar-button settings" onClick={toggleDropdown}>
                    <i className="fas fa-cog"></i>
                </button>
                {isOpen && (
                    <div className="dropdown-menu">
                        <div className="dropdown-item" onClick={handleEditTable}>테이블편집</div>
                        <div className="dropdown-item">공간편집(미구현)</div>
                    </div>
                )}
            </div>
        </div>
    )
}

export default SettingDropDown;