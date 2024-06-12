import '../../styles/WindowBox.css';
import {GetDirections, GetSubjects, GetTeachersName, GetFaculties, GetChairs} from '../../data/dekanatHelper';
import React, {useState, useEffect} from 'react';

import AddingSubjectForm from './components/AddingSubjectForm';
import SubjectsTable from './components/SubjectsTable';
import Filter from '../../components/Filter'

function SubjectsDekanat() {
  const [subjects, setSubjects] = useState([]);
  const [teachers, setTeachers] = useState([]);

  const [hiddenTable, setHiddenTable]=useState(false);
  const [filter, setFilter] = useState({faculty:"All", chair:"All", direction:"All", course:"All"});
  const [directions, setDirections]=useState([]);

  const [chairs, setChairs]=useState([]);
  const [faculties, setFaculties]=useState([]);
  const courses = [1, 2, 3, 4, 5, 6];


  useEffect(() => {
    GetFaculties().then(data => setFaculties(data));
    GetDirections("All").then(data => setDirections(data));
    GetChairs("All").then(data=>setChairs(data));
    GetTeachersName("All").then(data=>setTeachers(data));
    GetSubjects("All", "All", "All", "All").then(data=>setSubjects(data));
  }, []);


  function changeFaculty(e){
    setFilter({faculty: e.target.value, chair: filter.chair, direction: filter.direction, course: filter.course})
    GetChairs(e.target.value).then(data=>setChairs(data));
    GetDirections(e.target.value).then(data => setDirections(data));
    GetTeachersName(e.target.value).then(data=>setTeachers(data));
    GetSubjects(e.target.value, "All", "All", filter.course).then(data=>setSubjects(data));
  }

  function changeChair(e){
    setFilter({faculty: filter.faculty, chair: e.target.value, direction: filter.direction, course: filter.course});
    GetSubjects(filter.faculty, e.target.value, filter.direction, filter.course).then(data=>setSubjects(data));
  }

  function changeDirection(e){
    setFilter({faculty: filter.faculty, chair: filter.chair, direction: e.target.value, course: filter.course});
    GetSubjects(filter.faculty, filter.chair, e.target.value, filter.course).then(data=>setSubjects(data));
  }

  function changeCourse(e){
    setFilter({faculty: filter.faculty, chair: filter.chair, direction: filter.direction, course: e.target.value});
    GetSubjects(filter.faculty, filter.chair, filter.direction, e.target.value).then(data=>setSubjects(data));
  }

  function changeFilter(faculty, chair, direction, course){
    if(faculty===null) faculty=filter.faculty;
    else {
      GetDirections(faculty, setDirections);
      GetTeachersName(faculty, setTeachers);
      GetChairs(faculty, setChairs);
    }
    if(chair===null) chair=filter.chair;
    if(direction===null) direction=filter.direction;
    if(course===null) course=filter.course;
    GetSubjects(faculty, chair, direction, course, setSubjects);
    setFilter({faculty: faculty, chair:chair, direction: direction, course: course});
  }

    return (
      <div className='windowBox'>
        <form className='form-controls'>
          <Filter Name="Факультет" Array={faculties} Change={changeFaculty}/>
          <Filter Name="Кафедра" Array={chairs} Change={changeChair}/>
          <Filter Name="Спеціальність" Array={directions} Change={changeDirection}/>
          <Filter Name="Курс" Array={courses} Change={changeCourse}/>
        </form>

      <SubjectsTable subjects={subjects} hidden={hiddenTable} SetHidden={()=>setHiddenTable(true)} reset={()=>GetSubjects(filter.faculty, filter.chair, filter.direction, filter.course).then(data=>setSubjects(data))}/>
      <AddingSubjectForm hidden={!hiddenTable} SetHidden={()=>setHiddenTable(false)} 
                          faculty={filter.faculty} chair={filter.chair} direction={filter.direction} course={filter.course} teachers={teachers} reset={()=>GetSubjects(filter.faculty, filter.chair, filter.direction, filter.course).then(data=>setSubjects(data))}/>
        

      </div>
    );
  }
  
  export default SubjectsDekanat;