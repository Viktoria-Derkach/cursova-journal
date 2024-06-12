import React, {useRef, useState} from "react";
import {DeductStudent, TransferStudent} from '../../../data/dekanatHelper';
import './componentsStyle.css';

function StudentsTable(props){
    const checkBoxesRef = useRef([]);
    const mainCheckBoxRef = useRef();
    const [disabledButtons, setDisabledButtons]=useState(true);

    function selectAllStudent(){
        if(mainCheckBoxRef.current.checked){
            checkBoxesRef.current.forEach((Item) => {if(Item!==null) Item.checked=true;});
            setDisabledButtons(false);
        }
        else{
            checkBoxesRef.current.forEach((Item) => {if(Item!==null) Item.checked=false;});
            setDisabledButtons(true);
        }
    }

    function selectStudents(){
        let selectedOne=false;
        let selectedAll=true;
        checkBoxesRef.current.forEach((Item) => {
          if(Item===null) return;
          selectedOne = Item.checked || selectedOne;
          selectedAll = Item.checked && selectedAll;
        });
        if(selectedOne)
            setDisabledButtons(false);
        else
            setDisabledButtons(true);
        if(selectedAll)
          mainCheckBoxRef.current.checked=true;
        else
            mainCheckBoxRef.current.checked=false;
    }

    function deductStudent(){
        let students= [];
        checkBoxesRef.current.forEach((Item) => {
         if(Item!=null && Item.checked) students.push(Item.id);
        });        
        DeductStudent(students).then(data=> {
          props.reset();
          resetCheckBox();
        });
        
    }

    function resetCheckBox(){
      mainCheckBoxRef.current.checked=false;
      checkBoxesRef.current.forEach((Item) => {if(Item!==null) Item.checked=false;});
      setDisabledButtons(true);
    }

    function transferStudent(){
        let students= [];
        checkBoxesRef.current.forEach((Item) => {
         if(Item!=null && Item.checked) students.push(Item.id);
        });
        TransferStudent(students).then(data=> {
          props.reset();
          resetCheckBox();
        });
    
    }

    return(
        <div className="StudentsTableDisplay" hidden={props.hidden}>
        <table className='Table'>
        <thead>
          <tr>
            <th className="checkBoxColumns TableHead">
              <input type="checkbox" id="allStudents" onChange={selectAllStudent} ref={mainCheckBoxRef}/>
            </th>
            
            <th className="nameColumns TableHead">Прізвище ім'я по-батькові</th>
            <th className="facultyColumns TableHead">Факультет</th>
            <th className="groupColumns TableHead">Група</th>
            <th className="numberColumns TableHead">Номер залікової</th>
          </tr>
        </thead>

        <tbody>
          {props.students.map((item, index) => (
            <tr key={item.id}>
              <td className="checkBoxColumns TableCell">
                <input type="checkbox" className="studentCheckbox" id={item.id} onChange={selectStudents} ref={el => checkBoxesRef.current[index] = el}/>
              </td>
            
              <td className="nameColumns TableCell">{item.name}</td>
              <td className="facultyColumns TableCell">{item.faculty}</td>
              <td className="groupColumns TableCell">{item.direction}-{item.course}{item.group}</td>
              <td className="numberColumns TableCell">{item.number}</td>
            </tr>
              ))}
        </tbody>
      </table>

      <div className='form-controls'>
        <button className='buttonControl' id="deductStudent" disabled={disabledButtons} onClick={deductStudent}>Відрахувати</button>
        <button className='buttonControl' id="transferStudent" disabled={disabledButtons} onClick={transferStudent}>Перевести на наступний курс</button>
        <button className='buttonControl' id="addStudent" onClick={props.SetHidden}>Додати студентів</button>
      </div>
      </div>
    );
}

export default StudentsTable;