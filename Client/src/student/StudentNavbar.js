import '../styles/Navbar.css';
import { Outlet, Link, NavLink, Navigate} from "react-router-dom";

function StudentNavbar() {
  function exit(){
    window.sessionStorage.setItem("role", "");
    window.sessionStorage.setItem("id", "");
  }
  return (
    <>
      {
        (window.sessionStorage.getItem("role")!=="student") &&
        <Navigate to="/" replace={true}/>
      }
    <div id="container">
      <nav>
        <div id="logo">   
          <NavLink to="/student">
            Електронний журнал
          </NavLink>  
        </div>
        <ul>
          <li>
            <Link to="/student/marks">Оцінки</Link>
          </li>
          <li>
            <Link to="/student/subjects">Предмети</Link>
          </li>
          <li>
            <Link to="/student/ads">Оголошення</Link>
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

export default StudentNavbar;
