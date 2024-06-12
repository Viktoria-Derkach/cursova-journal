import '../styles/Navbar.css';
import { Outlet, Link, NavLink, Navigate } from "react-router-dom";

function TeacherNavbar() {
  function exit(){
    window.sessionStorage.setItem("role", "");
    window.sessionStorage.setItem("id", "");
  }
  return (
    <>
      {
        (window.sessionStorage.getItem("role")!=="teacher") &&
        <Navigate to="/" replace={true}/>
      }
    <div id="container">
      <nav>
        <div id="logo">
        <NavLink to="/teacher">
            Електронний журнал
          </NavLink>  
        </div>
        <ul>
          <li>
            <Link to="/teacher/marks">Оцінки</Link>
          </li>
          <li>
            <Link to="/teacher/subjects">Предмети</Link>
          </li>
          <li>
            <Link to="/teacher/ads">Оголошення</Link>
          </li>
          <li>
            <Link to="/" onClick={exit}>Вихід</Link>
          </li>
        </ul>
      </nav>
    </div>
      <Outlet />
    </>
  )
};

export default TeacherNavbar;