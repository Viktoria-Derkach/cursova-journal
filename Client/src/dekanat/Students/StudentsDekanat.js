import '../../styles/WindowBox.css';
import {GetStudents, GetDirections, GetFaculties} from '../../data/dekanatHelper';
import React, {useState, useEffect} from 'react';
import AddingStudentForm from './components/AddingStudentForm';
import StudentsTable from './components/StudentsTable';
import Filter from '../../components/Filter'

function StudentsDekanat() {
  const [students, setStudents] = useState([]);
  const [hiddenTable, setHiddenTable]=useState(false);
  const [filter, setFilter] = useState({faculty:"All", direction:"All", course:"All", group:"All"});
  const [directions, setDirections]=useState([]);
  const [faculties, setFaculties]=useState([]);
  const courses = [1, 2, 3, 4, 5, 6];
  const groups = [1, 2, 3, 4, 5, 6];

  useEffect(() => {
    GetFaculties().then(data => setFaculties(data));
    GetDirections("All").then(data => setDirections(data));
    GetStudents("All", "All", "All", "All").then(data => setStudents(data));
  }, []);

  function changeFaculty(e){
    GetDirections(e.target.value).then(data=>setDirections(data));
    GetStudents(e.target.value, "All", filter.course, filter.group).then(data => setStudents(data));
    setFilter({faculty: e.target.value, direction: "All", course: filter.course, group: filter.group});
    
  }

  function changeDirection(e){
    setFilter({faculty: filter.faculty, direction: e.target.value, course: filter.course, group: filter.group});
    GetStudents(filter.faculty, e.target.value, filter.course, filter.group).then(data => setStudents(data));
  }

  function changeCourse(e){
    setFilter({faculty: filter.faculty, direction: filter.direction, course: e.target.value, group: filter.group});
    GetStudents(filter.faculty, filter.direction, e.target.value, filter.group).then(data => setStudents(data));
  }

  function changeGroup(e){
    setFilter({faculty: filter.faculty, direction: filter.direction, course: e.target.value, group: e.target.value});
    GetStudents(filter.faculty, filter.direction, filter.course, e.target.value).then(data => setStudents(data));
  }

    return (
      <div className='windowBox'>
        <form className='form-controls'>
          <Filter Name="Факультет" Array={faculties} Change={changeFaculty}/>
          <Filter Name="Спеціальність" Array={directions} Change={changeDirection}/>
          <Filter Name="Курс" Array={courses} Change={changeCourse}/>
          <Filter Name="Група" Array={groups} Change={changeGroup}/>
        </form>

      <StudentsTable students={students} hidden={hiddenTable} SetHidden={()=>setHiddenTable(true)} reset={()=>GetStudents(filter.faculty, filter.direction, filter.course, filter.group).then(data => setStudents(data))}/>
      <AddingStudentForm hidden={!hiddenTable} SetHidden={()=>setHiddenTable(false)} 
                          faculty={filter.faculty} direction={filter.direction} course={filter.course} group={filter.group}
                          reset={()=>GetStudents(filter.faculty, filter.direction, filter.course, filter.group).then(data => setStudents(data))}/>
        

      </div>
    );
  }
  
  export default StudentsDekanat;