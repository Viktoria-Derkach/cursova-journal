
function Filter(props) {
  return (
    <div className='form-control' hidden={props.hidden}>
            <label htmlFor="Select">{props.Name}:</label>
            <select id="Select" defaultValue={"All"} onChange={props.Change}>
              {!props.notHaveAll &&
                <option value="All">Всі</option>
              }
              
              {props.Array.map((item, index) => (
                <option value={item} key={index}>{item}</option>
              ))}
            </select>
    </div>
  )
  }
  
  export default Filter;