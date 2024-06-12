import '../../styles/WindowBox.css';
import {GetInfo} from '../../data/studentHelper';
import React, {useState, useEffect} from 'react';


function InfoStudent() {
  const [info, setInfo] = useState({});
  const id=window.sessionStorage.getItem("id");
  useEffect(() => {    
    GetInfo(id).then(data => setInfo(data));
  }, []);

    return (
      <div className='windowBox'>
        <h2>Загальна інформація</h2>
        <div className="Info">
          Прізвище ім'я: <b>{info.name}</b><br/>
          Факультет: <b>{info.faculty}</b><br/>
          Спеціальність: <b>{info.direction}</b><br/>
          Курс: <b>{info.course}</b><br/>
          Група: <b>{info.group}</b><br/>
          Номер залікової: <b>{info.number}</b><br/>
          Кількість предметів: <b>{info.subjects}</b><br/>

        </div>
      </div>
    );
  }
  
  export default InfoStudent;