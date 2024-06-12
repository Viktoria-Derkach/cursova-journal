import {GetDirections, GetFaculties, GetMessages, AddMessage} from '../../data/teacherHelper';
import React, {useState, useEffect} from 'react';
import Messages from '../../components/Messages';
import AddingMessageForm from '../../components/AddingMessageForm';

function AdsTeacher() {
  const [filter, setFilter] = useState({faculty:"All", direction:"All", course:"All", group:"All"});
  const [directions, setDirections]=useState([]);
  const [faculties, setFaculties]=useState([]);
  const [messages, setMessages]=useState([]);
  const courses = [1, 2, 3, 4, 5, 6];
  const groups = [1, 2, 3, 4, 5, 6];
  const id=window.sessionStorage.getItem("id");

  useEffect(() => {
    GetFaculties().then(data => setFaculties(data));
    GetDirections("All").then(data => setDirections(data));
    GetMessages(id).then(data=>setMessages(data));
  }, []);

  function changeFaculty(e){
    GetDirections(e.target.value).then(data=>setDirections(data));
    setFilter({faculty: e.target.value, direction: "All", course: filter.course, group: filter.group});
    
  }

  function changeDirection(e){
    setFilter({faculty: filter.faculty, direction: e.target.value, course: filter.course, group: filter.group});
  }

  function changeCourse(e){
    setFilter({faculty: filter.faculty, direction: filter.direction, course: e.target.value, group: filter.group});
  }

  function changeGroup(e){
    setFilter({faculty: filter.faculty, direction: filter.direction, course: e.target.value, group: e.target.value});
  }

  function addMessage(theme, text){
    const options = {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
    };
    
    let date = new Date();
    let message ={
      title: theme,
      text: text,
      id: id,
      date: (date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()),
      faculty: filter.faculty, 
      direction: filter.direction, 
      course: filter.course, 
      group: filter.group
    }
    AddMessage(message).then(result=>GetMessages(id).then(data=>setMessages(data)));
  }

    return (
      <div className='windowBox'>
        <div className="adsFlex">
          <div className="messagesSection">
          <Messages messages={messages}/>
          </div>

          <div className="messagesSection">
            <AddingMessageForm faculties={faculties} directions={directions} courses={courses}
                                groups={groups} addMessage={addMessage}
                                changeFaculty={changeFaculty} changeDirection={changeDirection} changeCourse={changeCourse} changeGroup={changeGroup}/>
          </div>
        </div>
        
      </div>
    );
  }
  
  export default AdsTeacher;