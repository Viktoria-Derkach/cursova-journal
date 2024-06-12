
function InputBox(props){

    return(
        <div className="inputBox">
            <input className="input" placeholder="" required="required" type={props.Type} name={props.Name} {... props.Register}/>
            <label>{props.Label}</label>
        </div>  
    )
}

export default InputBox;