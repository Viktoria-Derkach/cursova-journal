import {useForm} from "react-hook-form";
import {addMarks} from '../../../data/teacherHelper';
import React from 'react';
import "./componentsStyle.css"



function AddingMarksTable(props){

    const { register, handleSubmit, reset} = useForm({
        defaultValues: {
          points: [],
          date: undefined
        }
      });
      let id =[];
    
      function onSubmit(data){
        let date=new Date(data.date);
        let points={
          date: date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate(),
          subject: props.subject,
          student: []

        };
        data.points.forEach((item, index)=>{
          points.student.push({
            studentId: id[index],
            point: item
          });
        });
        addMarks(points).then(data=>{
          if(data){
            props.setHidden();
            props.reset();
            reset();
          }
        });
        console.log(points);
    }

    function PointsTable(){
      id=[];
      return props.students.map((item, index)=>{
        id.push(item.id);
        return(
        <tr key={index}> 
          <td className="nameColumns TableCell">{item.name}</td>
          <td className="pointColumns TableCell"> 
            <input type="text" required="required" name={`points[${index}]`} {...register(`points[${index}]`)}/>
            
          </td>
        </tr> 
      )});
    }

    return(
        <form className="AddingMarks" hidden={props.hidden} onSubmit={handleSubmit(onSubmit)}>
        <table className='Table'>
        <thead>
          <tr>            
            <th className="nameColumns TableHead">Прізвище ім'я по-батькові</th>
            <th className="pointColumns TableHead" >
              <input type="date" required="required" name="date" {...register(`date`)}/>
              </th>
            
          </tr>
        </thead>
        <tbody>
          <PointsTable/>
        </tbody>
      </table>
      <div className='form-controls'>
        <input type="button" className='buttonControl' id="cancelAddingButton" value="Скасувати" onClick={props.setHidden}/>
        <button className='buttonControl' type="submit" id="confirmAddingButton">Зберегти</button>
      </div>
      </form>
    )
}

export default AddingMarksTable;