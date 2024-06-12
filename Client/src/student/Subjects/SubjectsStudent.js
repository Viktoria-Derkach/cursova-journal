import {GetSubjects} from '../../data/studentHelper';
import React, {useState, useEffect} from 'react';


function SubjectsStudent() {
  const [subjects, setSubjects] = useState([]);
  const id=window.sessionStorage.getItem("id");
  useEffect(() => {    
    GetSubjects(id).then(data=>setSubjects(data));
  }, []);
  return (
    <div className='windowBox'>
      <div className="SubjectTable">
      <table className='Table'>
        <thead>
      <tr>           
          <th className="nameColumns TableHead">Предмет</th>
          <th className="chairColumns TableHead">Кафедра</th>
          <th className="teacherColumns TableHead">Викладач</th>
          <th className="hoursColumns TableHead">Кількість годин</th>
        </tr>
      </thead>

      <tbody>
        {subjects.map((item, index) => (
          <tr key={index}>
          <td className="nameColumns TableCell">{item.name}</td>
          <td className="chairColumns TableCell">{item.chair}</td>
          <td className="teacherColumns TableCell">{item.teacher }</td>
          <td className="hoursColumns TableCell">{item.hours}</td>
          </tr>
            ))}
      </tbody>
      </table>
      </div>
    </div>
  )
  }
  
  export default SubjectsStudent;