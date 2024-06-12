import { useForm, useFieldArray} from "react-hook-form";
import React, {useState, useEffect} from 'react';
import {AddStudent} from '../../../data/dekanatHelper';
import './componentsStyle.css';

function AddingStudentForm(props){

    const { register, control, handleSubmit, reset} = useForm({
        defaultValues: {
          students: [{ name: "", email: "", number: ""}]
        }
      });
      const { fields, append, remove} = useFieldArray(
        {
          control,
          name: "students"
        }
      );
      const [successAdding, setSuccessAdding] = useState(false);

      useEffect(() => {
        if(successAdding){
          props.SetHidden();
          reset({
            students: [{ name: "", email: "", number: ""}]
          });
          props.reset();
        }
      }, [successAdding]);

    function onSubmit(data){
        if(props.faculty==="All" || props.direction==="All" || props.course==="All" || props.group==="All" || data.students.length===0){
            alert("Помилка введення");
            return;
        }
        let students=[];
        data.students.forEach((item) => {
            let student={name: item.name, email: item.email, number: item.number, faculty: props.faculty, direction: props.direction, course: props.course, studentGroup: props.group};
            students.push(student);
        });
        AddStudent(students).then(data=>setSuccessAdding(data));        
    }


    return (
      <form className='addingStudentsForm' onSubmit={handleSubmit(onSubmit)} hidden={props.hidden}>
        <table className='Table'>
        <thead>
          <tr>
            <th className="deleteColumns">
            </th>
            
            <th className="nameColumnsInput TableHead">Прізвище ім'я по-батькові</th>
            <th className="mailColumnsInput TableHead">E-mail</th>
            <th className="numberColumnsInput TableHead">Номер залікової</th>

          </tr>
        </thead>

        <tbody>
        {fields.map((item, index) => {
          return (

            <tr key={item.id}>
            <td className="deleteColumns">
                <button className="deleteButton" onClick={() => remove(index)}/>
            </td>
            
            <td className="nameColumnsInput TableCell" >
            <input type="text" id="nameStudentInput" required="required"
                name={`students[${index}].name`}
                defaultValue={`${item.name}`}
                {...register(`students[${index}].name`)}/>
            </td>

            <td className="mailColumnsInput TableCell" >
            <input type="email" id="mailStudentInput" required="required"
                name={`students[${index}].email`}
                defaultValue={`${item.email}`}
                {...register(`students[${index}].email`)}/>
            </td>

            <td className="numberColumnsInput TableCell">
            <input type="text" id="numberStudentInput" required="required"
                name={`students[${index}].number`}
                defaultValue={`${item.number}`}
                {...register(`students[${index}].number`)}/>
            </td>
          </tr>
          );
          
        })}
        </tbody>
      </table>

      <div className='form-controls'>
        <input type="button" className="addButton" 
            onClick={() => {
                append({ name: "", email: "", number: ""}); 
            }}
        />
      </div>
      <div className='form-controls'>
        <input type="button" className='buttonControl' id="cancelAddingButton" value="Скасувати" onClick={props.SetHidden}/>
        <button className='buttonControl' type="submit" id="confirmAddingButton">Додати</button>
      </div>
        </form>
    )
  }

  export default AddingStudentForm;