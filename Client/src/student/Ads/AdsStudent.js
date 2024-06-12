import {GetMessages} from '../../data/studentHelper';
import Messages from '../../components/Messages';
import React, {useState, useEffect} from 'react';


function AdsStudent() {

  
  const [messages, setMessages] = useState([]);
  const id=window.sessionStorage.getItem("id");
  useEffect(() => {    
    GetMessages(id).then(data=>setMessages(data));
  }, []);
  
    return (
      <div className='windowBox'>
        <div className="adsFlex">
          <div className='adsStudents'>
          <Messages messages={messages}/>
          </div>
        </div>
        
      </div>
    );
  }
  
  export default AdsStudent;