import '../../styles/WindowBox.css';
import {GetInfo} from '../../data/teacherHelper';
import React, {useState, useEffect} from 'react';


function InfoTeacher() {
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
          Кафедра: <b>{info.chair}</b><br/>
          Предмети: <b>{info.subjects}</b><br/>
          Кількість годин: <b>{info.hours}</b><br/>
          Кількість студентів: <b>{info.students}</b><br/>

        </div>
      </div>
    );
  }
  
  export default InfoTeacher;