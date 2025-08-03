import React from 'react';
import '@fortawesome/fontawesome-free/css/all.min.css'; 
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import { AuthProvider, AuthContext } from './contexts/AuthContext.jsx';
import { useContext } from 'react';
import Header from './components/Header.jsx';
import Signup from './components/Signup/Signup.jsx'; 
import Login from './components/Login.jsx'; //로그인 페이지
import Main from './components/Main.jsx';  // 메인 페이지
import { TableProvider } from './components/Dining/TableContext.jsx';
import Logout from './components/Logout.jsx';  // 메인 페이지

// import ProductManagement from './components/Product/ProductManagement';
// import Order from './components/Order/Order.js';  // 주문 페이지
// import OrderHistory from './components/Order/OrderHistory.js';  // 주문 페이지
// import Dining from "./components/Dining/Dining.js"; // 테이블
// import EditDining from "./components/Dining/EditDining.js" // 테이블수정
// import { SaleSummary } from './components/SaleSummary/SaleSummary.js'; //매출 요약
// import Payment from "./components/Pay/PaymentPage.js";
// import PaymentHistory from "./components/Pay/PaymentHistory"; // 결제 내역 페이지
// import TossPayResult from './components/Pay/TossPayResult.js';
// import KitchenSocket from './components/Kitchen/KitchenSocket.js';

// import Test from './components/test.jsx';  //백이랑 연결 test용


const ProtectedRoute = ({ children }) => {
  const { isAuthenticated, loading } = useContext(AuthContext);
  if (loading) return null;
  return isAuthenticated ? children : <Navigate to="/login" />;
}


function App() {
  return (
    <AuthProvider>
      <TableProvider>
        <Router>
          <Header />
            <Routes>
              <Route path="/login" element={<Login />} />
              <Route path="/signup" element={<Signup />} />
              <Route path="/" element={<ProtectedRoute><Main /></ProtectedRoute>} /> {/* 메인 페이지 */}
              {/* <Route path="/sales-summary" element={<SaleSummary/>}/> 매출 요약 */}
              {/* <Route path="/payment-history" element={<PaymentHistory/>}/> 판매 내역역 */}
              {/* <Route path="/order-history" element={<OrderHistory />} /> 테이블 ID에 따른 주문 페이지 */}
              {/* <Route path="/order/:tableNo" element={<Order/>}/> 주문내역 */}
              {/* <Route path="/product-management" element={<ProductManagement />} /> */}
              {/* <Route path="/dining" element={<Dining/>}/> {/* 테이블가져오기 */}
              {/* <Route path="/editDining" element={<EditDining/>}/> 테이블수정하기 */}
              {/* <Route path="/payment-history" element={<PaymentHistory/>}/> 판매 내역 */}
              {/* <Route path="/pay" element={<Payment/>}/> 결제 */}
              {/* <Route path="/success" element={<TossPayResult/>}/> 결제 */}
              {/* <Route path="/kitchen" element={<KitchenSocket/>}/>주방 */}
              <Route path="/logout" element={<Logout />} />

              {/* <Route path="/test" element={<Test />} /> test용 */}
            </Routes>
        </Router>
      </TableProvider>
    </AuthProvider>  
  );
}

export default App
