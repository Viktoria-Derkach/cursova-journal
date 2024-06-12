const getEntityUrl = "http://localhost:8000/get/entity/";
const insertUrl = "http://localhost:8000/insert/";
const getNameUrl = "http://localhost:8000/get/name/";
const pointUrl = "http://localhost:8000/point/";
const getInfoUrl = "http://localhost:8000/get/info/";
const adUrl = "http://localhost:8000/ad/";

export async function addMarks(marks){
    let result =false;
    await Promise.all(marks.student.map(async student => {
       await fetch(pointUrl + "insert", {
         method: 'POST',
         headers: {
           'Accept': 'application/json, text/plain, */*',
           'Content-Type': 'application/json'
         },
         body: JSON.stringify({
           date: marks.date,
           subject: marks.subject,
           studentId: student.studentId,
           point: student.point
         })
       })
       .then((response) => response.json())
       .then((data) => {
         result=result||data;
       });
     }));
    return result;

}

export async function GetStudents(id, subject, group){
  let index=group.indexOf('-');
  let groupObject={direction: group.slice(0, index), course: group.slice(index+1, index+2), group: group.slice(index+2)}
  console.log(groupObject);
  let students=[{}];
   await fetch(
     pointUrl + "get/teacher?" +
       new URLSearchParams({
         id: id,
         subject: subject,
         direction: groupObject.direction,
         course: groupObject.course,
         group: groupObject.group
       })
     )
     .then((response) => response.json())
     .then((data) => {
       students=data;
     });
     console.log(students);
  return students;
}

export async function GetGroupsBySubject(subject){
  let groups = [];
    await fetch(
      getEntityUrl + "subject/groups?" +
        new URLSearchParams({
          name: subject,
        })
    )
      .then((response) => response.json())
      .then((data) => {
        data.forEach(el => {
          groups.push((el.direction + '-' + el.course + el.group));
        });
      });
    return groups;
  }

export async function GetSubjectsByTeacher(id){
  let subjects
  await fetch(
      getNameUrl + "teacher/subject?" +
        new URLSearchParams({
          id: id
        })
    )
      .then((response) => response.json())
      .then((data) => {
        subjects = data;
      });
      return subjects;
  }

export async function GetInfo(id){
  let info;
      await fetch(
        getInfoUrl + "teacher?" +
          new URLSearchParams({
            id: id
          })
      )
      .then((response) => response.json())
      .then((data) => {
       info=data;
      });
      return info;
  }

  export async function GetSubjects(id){
    let subjects;
   await fetch(
     getEntityUrl + "teacher/subject?" +
       new URLSearchParams({
         id: id,
       })
   )
     .then((response) => response.json())
     .then((data) => {
       subjects=data;
     });
     return subjects;
  }

  export async  function GetMessages(id){
    let messages;
    await fetch(
       adUrl + "get/teacher?" +
       new URLSearchParams({
         id: id
       }))
     .then((response) => response.json())
     .then((data) => {
       messages = data;
     });
    return messages;
  }

  export async function AddMessage(message){
    let result;
    await fetch(adUrl + "add", {
      method: 'POST',
      headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(message)
    })
    .then((response) => response.json())
    .then((data) => {
      result=data;
    });
    return result;
  }

  export async function GetFaculties(){
    let faculties;
    await fetch(
      getNameUrl + "faculties"
    )
      .then((response) => response.json())
      .then((data) => {
        faculties=data;
      });
    return faculties;
  }

  export async function GetDirections(faculty="All"){
    let directions;
      await fetch(
        getNameUrl + "directions?" +
          new URLSearchParams({
            faculty: faculty,
          })
      )
        .then((response) => response.json())
        .then((data) => {
          directions = data;
        });
    return directions;
    }