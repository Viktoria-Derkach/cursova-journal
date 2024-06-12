import React, {useEffect, useState} from "react";
import { useForm, useFieldArray} from "react-hook-form";
import {AddSubject} from '../../../data/dekanatHelper';
import './componentsStyle.css';

function AddingSubjectForm(props){

    const { register, control, handleSubmit, reset} = useForm({
        defaultValues: {
          subjects: [{ name: "", teacher: "", group: "", hours: ""}]
        }
      });
      const { fields, append, remove} = useFieldArray(
        {
          control,
          name: "subjects"
        }
      );
  
      const groups = [1, 2, 3, 4, 5, 6];
      

    function onSubmit(data){
        if(props.faculty==="All" || props.chair==="All" || props.direction==="All" || props.course==="All" || data.subjects.length===0){
            alert("Помилка введення");
            return;
        }
        let subjects=[];
        data.subjects.forEach((item) => {
            let subject={name: item.name, teacher: item.teacher, group: item.group, hours: item.hours, faculty: props.faculty, chair: props.chair, direction: props.direction, course: props.course};
            subjects.push(subject);
        });
        AddSubject(subjects).then(data=>{
          if(data){
            props.SetHidden();
            reset({
              subjects: [{ name: "", teacher: "", group: "", hours:""}]
            });        
            props.reset();
          }
        });
        
    }


    return (
      <form className='addingSubjectsForm' onSubmit={handleSubmit(onSubmit)} hidden={props.hidden}>
        <table className='Table'>
        <thead>
          <tr>
            <th className="deleteColumns">
            </th>
            
            <th className="nameColumnsInput TableHead">Предмет</th>
            <th className="teacherColumnsInput TableHead">Викладач</th>
            <th className="groupColumnsInput TableHead">Група</th>
            <th className="hoursColumnsInput TableHead">Кількість годин</th>
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
            <input type="text" id="nameSubjectInput" required="required"
                name={`subjects[${index}].name`}
                defaultValue={`${item.name}`}
                {...register(`subjects[${index}].name`)}/>
            </td>

            <td className="teacherColumnsInput TableCell" >
            <select id="teacherSubjectInput" required="required"
                name={`subjects[${index}].teacher`}
                defaultValue={`${item.teacher}`}
                {...register(`subjects[${index}].teacher`)}>
                {props.teachers.map((item, index) => (
                <option value={item} key={index}>{item}</option>
              ))}
            </select>
            </td>

            <td className="groupColumnsInput TableCell">
            <select id="groupSubjectInput" required="required"
                name={`subjects[${index}].group`}
                defaultValue={`${item.group}`}
                {...register(`subjects[${index}].group`)}>
                <option value="Всі">Всі</option>
                {groups.map((item, index) => (
                  <option value={item} key={index}>{item}</option>
                ))}
            </select>
            </td>

            <td className="hoursColumnsInput TableCell" >
            <input type="text" id="hoursSubjectInput" required="required"
                name={`subjects[${index}].hours`}
                defaultValue={`${item.hours}`}
                {...register(`subjects[${index}].hours`)}/>
            </td>
          </tr>
          );
          
        })}
        </tbody>
      </table>

      <div className='form-controls'>
        <input type="button" className="addButton" 
            onClick={() => {
                append({ name: "", teacher: "", group: "", hours: ""}); 
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

  export default AddingSubjectForm;