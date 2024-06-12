
import '../styles/Navbar.css';
import { Outlet, Link, NavLink, Navigate } from "react-router-dom";

function DekanatNavbar() {
  function exit(){
    window.sessionStorage.setItem("role", "");
    window.sessionStorage.setItem("id", "");
  }
  return (
    <>
     {
        (window.sessionStorage.getItem("role")!=="dekanat") &&
        <Navigate to="/" replace={true}/>
      }
    <div id="container">
      <nav>
      <div id="logo">   
          <NavLink to="/dekanat">
            Електронний журнал
          </NavLink>  
        </div>
        <ul>
          <li>
            <Link to="/dekanat/students">Студенти</Link>
          </li>
          <li>
            <Link to="/dekanat/teachers">Вчителі</Link>
          </li>
          <li>
            <Link to="/dekanat/subjects">Предмети</Link>
          </li>
          <li>
            <Link to="/dekanat/ads">Оголошення</Link>
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

export default DekanatNavbar;