import '../../styles/WindowBox.css';
import React, {useState, useEffect} from 'react';
import {GetInfo} from '../../data/dekanatHelper';
function InfoDekanat() {

  const [info, setInfo] = useState([]);
  
  useEffect(() => {    
    GetInfo().then(data => setInfo(data));
  }, []);

    return (
      <div className='windowBox'>
        <h2>Загальна інформація</h2>
        <div className="Info">
          Кількість факультетів: <b>{info.faculty}</b><br/>
          Кількість спеціальностей: <b>{info.direction}</b><br/>
          Кількість груп: <b>{info.group}</b><br/>
          Кількість студентів: <b>{info.student}</b><br/>
          Кількість викладачів: <b>{info.teacher}</b><br/>
        </div>
      </div>
    );
  }
  
  export default InfoDekanat;