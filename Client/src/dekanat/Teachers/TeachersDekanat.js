import '../../styles/WindowBox.css';
import TeachersTable from './components/TeachersTable';
import AddingTeacherForm from './components/AddingTeacherForm';
import {GetSubjectsName, GetTeachers, GetChairs, GetFaculties} from '../../data/dekanatHelper';
import React, {useState, useEffect} from 'react';
import Filter from '../../components/Filter'


function TeachersDekanat() {
  
  const [teachers, setTeachers] = useState([]);
  const [hiddenTable, setHiddenTable]=useState(false);
  const [filter, setFilter] = useState({faculty:"All", chair:"All", subject: "All"});
  const [subjects, setSubjects]=useState([]);
  const [chairs, setChairs]=useState([]);
  const [faculties, setFaculties]=useState([]);

  useEffect(() => {
    GetFaculties().then(data => setFaculties(data));
    GetChairs("All").then(data=>setChairs(data));
    GetSubjectsName("All", "All").then(data=>setSubjects(data));
    GetTeachers("All", "All", "All").then(data=>setTeachers(data));
  }, []);

  function changeFaculty(e){
    setFilter({faculty: e.target.value, chair: "All", subject: "All"})
    GetChairs(e.target.value).then(data=>setChairs(data));
    GetSubjectsName(e.target.value, "All").then(data=>setSubjects(data));
    GetTeachers(e.target.value, "All", "All").then(data=>setTeachers(data));
  }

  function changeChair(e){
    setFilter({faculty: filter.faculty, chair: e.target.value, subject: "All"});
    GetSubjectsName(filter.faculty, e.target.value).then(data=>setSubjects(data));
    GetTeachers(filter.faculty, e.target.value, "All").then(data=>setTeachers(data));

  }

  function changeSubject(e){
    setFilter({faculty: filter.faculty, chair: filter.chair, subject: e.target.value})
    GetTeachers(filter.faculty, filter.chair, e.target.value).then(data=>setTeachers(data));
  }
  
    return (
      <div className='windowBox'>
         <form className='form-controls'>
         <Filter Name="Факультет" Array={faculties} Change={changeFaculty}/>
         <Filter Name="Кафедра" Array={chairs} Change={changeChair}/>
         <Filter Name="Предмет" Array={subjects} Change={changeSubject} hidden={hiddenTable}/>
        </form>

      <TeachersTable teachers={teachers} hidden={hiddenTable} SetHidden={()=>setHiddenTable(true)}
                      reset={()=>GetTeachers(filter.faculty, filter.chair, filter.subject).then(data=>setTeachers(data))}/>
      <AddingTeacherForm hidden={!hiddenTable} SetHidden={()=>setHiddenTable(false)} 
                          faculty={filter.faculty} chair={filter.chair} reset={()=>GetTeachers(filter.faculty, filter.chair, filter.subject).then(data=>setTeachers(data))}/>
        
      </div>
    );
  }
  
  export default TeachersDekanat;