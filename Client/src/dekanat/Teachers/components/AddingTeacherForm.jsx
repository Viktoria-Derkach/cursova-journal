import React from "react";
import { useForm, useFieldArray} from "react-hook-form";
import {AddTeacher} from '../../../data/dekanatHelper';
import './componentsStyle.css';

function AddingTeacherForm(props){

    const { register, control, handleSubmit, reset} = useForm({
        defaultValues: {
          teachers: [{ name: "", email: ""}]
        }
      });
      const { fields, append, remove} = useFieldArray(
        {
          control,
          name: "teachers"
        }
      );

    function onSubmit(data){
        if(props.faculty==="All" || props.chair==="All" || data.teachers.length===0){
            alert("Помилка введення");
            return;
        }
        let teachers=[];
        data.teachers.forEach((item) => {
            let teacher={name: item.name, email: item.email, faculty: props.faculty, chair: props.chair};
            teachers.push(teacher);
        });
        AddTeacher(teachers).then(data =>{
          if(data){
            props.SetHidden();
            reset({
              teachers: [{ name: "", email: ""}]
            });
            props.reset();
          }
        })
        
    }


    return (
      <form className='addingTeachersForm' onSubmit={handleSubmit(onSubmit)} hidden={props.hidden}>
        <table className='Table'>
        <thead>
          <tr>
            <th className="deleteColumns">
            </th>
            
            <th className="nameColumnsInput TableHead">Прізвище ім'я по-батькові</th>
            <th className="mailColumnsInput TableHead">E-mail</th>

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
            <input type="text" id="nameTeacherInput" required="required"
                name={`teachers[${index}].name`}
                defaultValue={`${item.name}`}
                {...register(`teachers[${index}].name`)}/>
            </td>

            <td className="mailColumnsInput TableCell" >
            <input type="email" id="mailTeacherInput" required="required"
                name={`teachers[${index}].email`}
                defaultValue={`${item.email}`}
                {...register(`teachers[${index}].email`)}/>
            </td>
          </tr>
          );
          
        })}
        </tbody>
      </table>

      <div className='form-controls'>
        <input type="button" className="addButton" 
            onClick={() => {
                append({ name: "", email: ""}); 
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

  export default AddingTeacherForm;