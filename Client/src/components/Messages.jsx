import './style.css'
function Messages(props){
    return (
        <>
        {props.messages.map((item, index) =>(
            <div className="messageBox" key={index}>
            <div className="header">
              <h4 className="nameSection"><b>{item.from}&nbsp;</b></h4>
              <h4 className="dateSection">{"– " + item.postDate}</h4>
            </div>
            <h4 className="themeSection">{item.title}</h4>
            <p className="textSection">{item.text}</p>
          </div>
        )
        )}
        </>
    )
}

export default Messages;