import '../../styles/WindowBox.css';
import {GetStudents, GetGroupsBySubject, GetSubjectsByTeacher} from '../../data/teacherHelper';
import React, {useState, useEffect} from 'react';
import MarksTableDisplay from './components/MarksTableDisplay';
import AddingMarksTable from './components/AddingMarksTable';
import Filter from '../../components/Filter'

function MarksTeacher() {
  const id=window.sessionStorage.getItem("id");
  const [subjects, setSubjects] = useState([]);
  const [groups, setGroups] = useState([]);
  const [filter, setFilter] = useState({subject:"", group: ""});
  const [students, setStudents] = useState({date:[], studentsArray:[]}); 
  const [hiddenTable, setHiddenTable] = useState(false);

  useEffect(() => {    
    GetSubjectsByTeacher(id).then(data => {
      setSubjects(data);
      GetGroupsBySubject(data[0]).then(groups => {
        setGroups(groups);
        GetStudents(id, data[0], groups[0]).then(data => setStudents(data));
        setFilter({subject:data[0], group: groups[0]});
      });
    });
  }, []);
  
  function changeSubject(e){
    GetGroupsBySubject(e.target.value).then(groups => {
      setGroups(groups);
      GetStudents(id, e.target.value, groups[0]).then(data => setStudents(data));
      setFilter({subject:e.target.value, group: groups[0]});
    });
  }

  function changeGroups(e){
    GetStudents(id, filter.subject, e.target.value).then(data => setStudents(data));
    setFilter({subject:filter.subject, group: e.target.value});
  }

    return (
      
      <div  className='windowBox'>
        <form className='form-controls' hidden={hiddenTable}>
         <Filter Name="Предмет" Array={subjects} notHaveAll={true} Change={changeSubject}/>
         <Filter Name="Група" Array={groups} notHaveAll={true} Change={changeGroups}/>
        </form>
        <MarksTableDisplay hidden={hiddenTable} students={students} setHidden={()=>setHiddenTable(true)}/>
        <AddingMarksTable hidden={!hiddenTable} students={students.studentsArray} setHidden={()=>setHiddenTable(false)} subject={filter.subject} reset={()=>GetStudents(id, filter.subject, filter.group).then(data => setStudents(data))}/>
        
      
      </div>
    );
  }
  
  export default MarksTeacher;