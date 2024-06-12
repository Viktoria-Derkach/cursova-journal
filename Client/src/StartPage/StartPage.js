import './StartPage.css';
import MenuButton from './components/MenuButton.jsx';
import { useNavigate } from "react-router-dom";
import React, {useState} from 'react';
import { useForm } from 'react-hook-form'
import InputBox from './components/InputBox';
 

function StartPage() {

  const url = "http://localhost:8000/login?";

  const [styleDisplay, setStyleDisplay] = useState({menu: "none", openButton: "block", closeButton: "none", signBox: "none"});
  const [role, setRole] = useState("")
  const { register, handleSubmit, reset } = useForm({defaultValues: {
    email: "",
    password: ""
  }});
  const navigate = useNavigate()

  function openMenu() {
    setStyleDisplay({menu: "flex", openButton: "none", closeButton: "block", signBox: "none"})
  }

  function closeMenu(){
    setStyleDisplay({menu: "none", openButton: "block", closeButton: "none", signBox: "none"})
  }

  function showSignIn(){
    setStyleDisplay({menu: "none", openButton: "none", closeButton: "none", signBox: "flex"})
  }

  function signIn(data) {
    fetch(
      url +
        new URLSearchParams({
          role: role,
          email: data.email,
          password: data.password,
        })
    )
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        if(data.status===500){
          alert("Неправильний пароль");
          return;
        } 
        reset();
        window.sessionStorage.setItem("role", role);
        window.sessionStorage.setItem("id", data.id);
        switch (role) {
          case "teacher":
            navigate("/teacher");
            break;
          case "dekanat":
            navigate("/dekanat");
            break;
          case "student":
            navigate("/student");
            break;
          default:
            break;
        };
      });
  }

  return (
    <div className="window">
      <h1>Електронний журнал</h1>
      <div className="container">
        <MenuButton id="open" content="Перегляд" display={styleDisplay.openButton} onClick={openMenu}/>
        <MenuButton id="close" content="Закрити" display={styleDisplay.closeButton} onClick={closeMenu}/>
        <div className="menu" style={{display: styleDisplay.menu}}>
            <MenuButton content="Деканат" left={-120} onClick={()=>{setRole("dekanat"); showSignIn();}}/>
            <MenuButton content="Студент" right={-120} onClick={()=>{setRole("student"); showSignIn();}}/>
            <MenuButton content="Викладач" bottom={-120} onClick={()=>{setRole("teacher"); showSignIn();}}/>
            <MenuButton content="Розклад" top={-120} onClick={()=>window.location.replace("http://elct.lnu.edu.ua/rozk/")}/>
        </div>
      </div>

      <form className="box" style={{display: styleDisplay.signBox}} onSubmit={handleSubmit(signIn)}>
        <h2>Авторизація</h2>
        <InputBox Label="Логін" Type="email" Name="email" Register={register('email')}/>
        <InputBox Label="Пароль" Type="password" Name="password" Register={register('password')}/>
        <a href="#">Забули пароль?</a>
        <button id="signIn" type="submit">Увійти</button>
        <button id="back" onClick={openMenu}>Назад до меню</button>
      </form>
    </div>
  );
}

export default StartPage;
