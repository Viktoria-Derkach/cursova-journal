
function MarksTableDisplay(props){
    return(
        <div hidden={props.hidden}>
        <div className="MarksTableDisplay">
        <table className='Table'>
        <thead>
          <tr>            
            <th className="nameColumns TableHead">Прізвище ім'я по-батькові</th>
            {props.students.date.map((item, index)=>(
                <th className="pointColumns TableHead" key={index}>{item}</th>
              ))}
          </tr>
        </thead>
        <tbody>
          {
            props.students.studentsArray.map((item, index)=>(
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
      <div className='form-controls'>
        <input type="button" className="addButton" onClick={props.setHidden}/>
      </div>
      </div>
    )
}

export default MarksTableDisplay;