import { useState, useEffect } from 'react';
import {GetSubjects} from '../../data/teacherHelper';
import "./subjectsTeacher.css"


function SubjectsTeacher() {
  const id=window.sessionStorage.getItem("id");
  const [subjects, setSubjects] = useState([]);
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
            <th className="hoursColumns TableHead">Кількість годин</th>
            <th className="chairColumns TableHead">Кафедра</th>
            <th className="directionColumns TableHead">Спеціальність</th>
            <th className="courseColumns TableHead">Курс</th>
            <th className="groupColumns TableHead">Група</th>
          </tr>
        </thead>

        <tbody>
          {subjects.map((item, index) => (
            <tr key={index}>
            <td className="nameColumns TableCell">{item.name}</td>
            <td className="hoursColumns TableCell">{item.hours}</td>
            <td className="chairColumns TableCell">{item.chair}</td>
            <td className="directionColumns TableCell">{item.direction}</td>
            <td className="courseColumns TableCell">{item.course}</td>
            <td className="groupColumns TableCell">{item.group}</td>
            </tr>
              ))}
        </tbody>
        </table>
        </div>
      </div>
    );
  }
  
  export default SubjectsTeacher;