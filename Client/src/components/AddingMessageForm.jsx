import Filter from "./Filter"
import { useForm } from "react-hook-form";
import './style.css'
function AddingMessageForm(props){
    const { register, handleSubmit, reset} = useForm();

    function onSubmit(data){
      props.addMessage(data.theme, data.text);
      reset();
  }

    return(
        <form className="messageBox" onSubmit={handleSubmit(onSubmit)}>
            <div className="header">
              <h3><b>Додати оголошення</b></h3>
            </div>
            <input type="text" placeholder="Тема" required="required" id="themeInputSection" name="theme" {...register('theme')}/>
            <textarea rows={7} placeholder="Текст" required="required" id="textInputSection" name="text" {...register('text')}></textarea>
            <h6>Призначення:</h6>
            <div className="adsFlex" style={{justifyContent:"space-around"}}>
            <div className='form-controls recipients' >
              <Filter Name="Факультет" Array={props.faculties} Change={props.changeFaculty}/>
              <Filter Name="Курс" Array={props.courses} Change={props.changeCourse}/>
            </div>
            <div className='form-controls recipients' >              
              <Filter Name="Спеціальність" Array={props.directions} Change={props.changeDirections}/>
              <Filter Name="Група" Array={props.groups} Change={props.changeGroup}/>
            </div>
            </div>
            <div className='form-controls' style={{padding: 10+'px'}}>
              <button className='buttonControl' type="submit">Опублікувати</button>
            </div>
        </form>
    )
}

export default AddingMessageForm;