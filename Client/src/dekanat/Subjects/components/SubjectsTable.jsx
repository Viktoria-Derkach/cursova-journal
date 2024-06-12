import React, {useRef, useState} from "react";
import {DeleteSubject} from '../../../data/dekanatHelper';
import './componentsStyle.css';

function SubjectsTable(props){
    const checkBoxesRef = useRef([]);
    const mainCheckBoxRef = useRef();
    const [disabledButton, setDisabledButton]=useState(true);

    function selectAllSubject(){
        if(mainCheckBoxRef.current.checked){
            checkBoxesRef.current.forEach((Item) => {if(Item!==null) Item.checked=true;});
            setDisabledButton(false);
        }
        else{
            checkBoxesRef.current.forEach((Item) => {if(Item!==null) Item.checked=false;});
            setDisabledButton(true);
        }
    }

    function selectSubjects(){
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

    function deleteSubject(){
        let subjects= [];
        checkBoxesRef.current.forEach((Item) => {
         if(Item!=null && Item.checked) subjects.push(Item.id);
        });
        DeleteSubject(subjects).then(data =>{
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
        <div className="SubjectsTableDisplay" hidden={props.hidden}>
        <table className='Table'>
        <thead>
          <tr>
            <th className="checkBoxColumns TableHead">
              <input type="checkbox" id="allSubjects" onChange={selectAllSubject} ref={mainCheckBoxRef}/>
            </th>
            
            <th className="nameColumns TableHead">Предмет</th>
            <th className="facultyColumns TableHead">Факультет</th>
            <th className="hoursColumns TableHead">Кількість годин</th>
            <th className="teacherColumns TableHead">Викладач</th>
            <th className="directionColumns TableHead">Спеціальність</th>
            <th className="courseColumns TableHead">Курс</th>
            <th className="groupColumns TableHead">Група</th>
          </tr>
        </thead>

        <tbody>
          {props.subjects.map((item, index) => (
            <tr key={item.id}>
              <td className="checkBoxColumns TableCell">
                <input type="checkbox" className="subjectCheckbox" id={item.id} onChange={selectSubjects} ref={el => checkBoxesRef.current[index] = el}/>
              </td>
            
              <td className="nameColumns TableCell">{item.name}</td>
            <td className="facultyColumns TableCell">{item.faculty}</td>
            <td className="hoursColumns TableCell">{item.hours}</td>
            <td className="teacherColumns TableCell">{item.teacher.toString()}</td>
            <td className="directionColumns TableCell">{item.direction}</td>
            <td className="courseColumns TableCell">{item.course}</td>
            <td className="groupColumns TableCell">{item.group}</td>
            </tr>
              ))}
        </tbody>
      </table>

      <div className='form-controls'>
        <button className='buttonControl' id="deleteSubject" disabled={disabledButton} onClick={deleteSubject}>Видалити</button>
        <button className='buttonControl' id="addSubject" onClick={props.SetHidden}>Додати предмети</button>
      </div>
      </div>
    );
}

export default SubjectsTable;