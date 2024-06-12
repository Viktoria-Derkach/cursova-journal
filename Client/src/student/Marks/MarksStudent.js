import '../../styles/WindowBox.css';
import {GetSubjectsName, GetMarks} from '../../data/studentHelper';
import React, {useState, useEffect} from 'react';
import Filter from '../../components/Filter'

function MarksStudent() {
  const id = window.sessionStorage.getItem("id");
  const [marks, setMarks] = useState({date:[], studentsArray:[]});
  const [filter, setFilter] = useState("");
  const [subjects, setSubjects] = useState([]);
  useEffect(() => {    
    GetSubjectsName(id).then(data=>{
      setSubjects(data);
      setFilter(data[0])
      GetMarks(data[0], id).then(marks=>setMarks(marks));
    });
  }, []);

  function displayOnlyMyMarks(e){
    if(e.target.checked){
      let onlyMyMarks={date:marks.date, studentsArray:[marks.studentsArray[0]]};
      setMarks(onlyMyMarks);      
    }
    else{
      GetMarks(filter, id).then(marks=>setMarks(marks));
    }
  }

  function changeSubject(e){
    setFilter(e.target.value);
    GetMarks(e.target.value, id).then(marks=>setMarks(marks));
  }

    return (
      <div  className='windowBox'>
        <form className='form-controls'>
         <Filter Name="Предмет" notHaveAll={true} Array={subjects} Change={changeSubject}/>
        <div className='form-control' style={{marginTop: 30+'px'}}>
            <label htmlFor="onlyMyMarks">Тільки мої оцінки</label>
            <input type="checkbox" id="onlyMyMarks" style={{marginLeft: 20+'px'}} onChange={displayOnlyMyMarks}/>
        </div>
        </form>
        <div className="MarksTableDisplay">
        <table className='Table'>
        <thead>
          <tr>            
            <th className="nameColumns TableHead">Прізвище ім'я по-батькові</th>
            {marks.date.map((item, index)=>(
                <th className="pointColumns TableHead" key={index}>{item}</th>
              ))}
          </tr>
        </thead>
        <tbody>
          {
            marks.studentsArray.map((item, index)=>(
              <tr key={index}>
                <td className="nameColumns TableCell">{item.name}</td>
                {item.points.map((point, pointIndex)=>(
                <td className="pointColumns TableCell" key={pointIndex}>{point.value}</td>
                ))}
              </tr> 
            ))
          }
        </tbody>
      </table>
      </div>
      </div>
    );
  }
  
  export default MarksStudent;