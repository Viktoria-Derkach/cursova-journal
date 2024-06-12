import React, {useRef, useState} from "react";
import {DeleteTeacher} from '../../../data/dekanatHelper';
import './componentsStyle.css';

function TeachersTable(props){
    const checkBoxesRef = useRef([]);
    const mainCheckBoxRef = useRef();
    const [disabledButton, setDisabledButton]=useState(true);

    function selectAllTeacher(){
        if(mainCheckBoxRef.current.checked){
            checkBoxesRef.current.forEach((Item) => {if(Item!==null) Item.checked=true;});
            setDisabledButton(false);
        }
        else{
            checkBoxesRef.current.forEach((Item) => {if(Item!==null) Item.checked=false;});
            setDisabledButton(true);
        }
    }

    function selectTeachers(){
        let selectedOne=false;
        let selectedAll=true;
        checkBoxesRef.current.forEach((Item) => {
          if(Item===null) return;
          selectedOne = Item.checked || selectedOne;
          selectedAll = Item.checked && selectedAll;
        });
        if(selectedOne)
            setDisabledButton(false);
        else
            setDisabledButton(true);
        if(selectedAll)
          mainCheckBoxRef.current.checked=true;
        else
            mainCheckBoxRef.current.checked=false;
    }

    function deleteTeacher(){
        let teachers= [];
        checkBoxesRef.current.forEach((Item) => {
         if(Item!=null && Item.checked) teachers.push(Item.id);
        });
        DeleteTeacher(teachers).then(data=>{
          props.reset();
          resetCheckBox();
        });
        
    }

    function resetCheckBox(){
      mainCheckBoxRef.current.checked=false;
      checkBoxesRef.current.forEach((Item) => {if(Item!==null) Item.checked=false;});
      setDisabledButton(true);
    }

    return(
        <div className="TeachersTableDisplay" hidden={props.hidden}>
        <table className='Table'>
        <thead>
          <tr>
            <th className="checkBoxColumns TableHead">
              <input type="checkbox" id="allTeachers" onChange={selectAllTeacher} ref={mainCheckBoxRef}/>
            </th>
            
            <th className="nameColumns TableHead">Прізвище ім'я по-батькові</th>
            <th className="facultyColumns TableHead">Факультет</th>
            <th className="chairColumns TableHead">Кафедра</th>
            <th className="subjectsColumns TableHead">Предмети</th>
          </tr>
        </thead>

        <tbody>
          {props.teachers.map((item, index) => (
            <tr key={item.id}>
              <td className="checkBoxColumns TableCell">
                <input type="checkbox" className="teacherCheckbox" id={item.id} onChange={selectTeachers} ref={el => checkBoxesRef.current[index] = el}/>
              </td>
            
              <td className="nameColumns TableCell">{item.name}</td>
            <td className="facultyColumns TableCell">{item.faculty}</td>
            <td className="chairColumns TableCell">{item.chair}</td>
            <td className="subjectsColumns TableCell">{item.subjects.map(item=>(item + ', '))}</td>
            </tr>
              ))}
        </tbody>
      </table>

      <div className='form-controls'>
        <button className='buttonControl' id="deleteTeacher" disabled={disabledButton} onClick={deleteTeacher}>Видалити</button>
        <button className='buttonControl' id="addTeacher" onClick={props.SetHidden}>Додати вчителів</button>
      </div>
      </div>
    );
}

export default TeachersTable;