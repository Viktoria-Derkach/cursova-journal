const insertUrl = "http://localhost:8000/insert/";
const getEntityUrl = "http://localhost:8000/get/entity/";
const getNameUrl = "http://localhost:8000/get/name/";
const deleteUrl = "http://localhost:8000/delete/";
const updateUrl = "http://localhost:8000/update/";
const getInfoUrl = "http://localhost:8000/get/info/";
const adUrl = "http://localhost:8000/ad/"

    export async function GetChairs(faculty="All"){
      let chairs;
    await fetch(
      getNameUrl + "chairs?" +
        new URLSearchParams({
          faculty: faculty,
        })
    )
      .then((response) => response.json())
      .then((data) => {
        chairs = data;
      });
      return chairs;
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
    
      export async function GetStudents(faculty, direction, course, group){
        let students;
        await fetch(
          getEntityUrl + "students?" +
            new URLSearchParams({
              faculty: faculty,
              direction: direction,
              course: course,
              group: group
            })
        )
          .then((response) => response.json())
          .then((data) => {
            students = data;
          });
        return students;
      }

      export async function GetTeachers(faculty="All", chair="All", subject="All"){
        let teachers;
        await fetch(
          getEntityUrl + "teachers?" +
            new URLSearchParams({
              faculty: faculty,
              chair: chair,
              subject: subject
            })
        )
          .then((response) => response.json())
          .then((data) => {
            teachers = data;
            console.log(data);
          });
        return teachers;
      }
    
      export async function GetSubjectsName(faculty="All", chair="All"){
        let subject;
        await fetch(
          getNameUrl + "subject/name?" +
            new URLSearchParams({
              faculty: faculty,
              chair: chair
            })
        )
          .then((response) => response.json())
          .then((data) => {
            subject = data;
          });
          return subject;
      }

      export async function AddStudent(students){
        let result;
        await fetch(insertUrl + "student", {
          method: 'POST',
          headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(students)
        })
        .then((response) => response.json())
        .then((data) => {
          result=data;
        });
        return result;
      }

      export async function DeductStudent(students){
        let result=false;;
        await Promise.all(students.map(async (student) => {
           await fetch(
            deleteUrl + "student?" +
              new URLSearchParams({
                id: student
              }), {method: "DELETE"}
          )
            .then((response) => response.json())
            .then((data) => {
              result = result || data;
          });
        }));
        return result;
      }

      export async function TransferStudent(students){
        let result;
        await fetch(updateUrl + "transfer/student", {
          method: 'POST',
          headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(students)
        })
        .then((response) => response.json())
        .then((data) => {
          console.log(data);
          result=data;
        });
        return result;
      }

      export async function AddTeacher(teachers){
        let result;
        await fetch(insertUrl + "teacher", {
          method: 'POST',
          headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(teachers)
        })
        .then((response) => response.json())
        .then((data) => {
          result=data;
        });
        return result;
      }

      export async function DeleteTeacher(teachers){
        let result=false;
        await Promise.all(teachers.map(async teacher => {
          await fetch(
            deleteUrl + "teacher?" +
              new URLSearchParams({
                id: teacher
              }), {method: "DELETE"}
          )
            .then((response) => response.json())
            .then((data) => {
              result = result || data;
          });
        }));
        return result;
      }
      
      export async function GetSubjects(faculty="All", chair="All", direction="All", course="All"){
      let subjects;
      await fetch(
        getEntityUrl + "subjects?" +
          new URLSearchParams({
            faculty: faculty,
            chair: chair,
            direction: direction,
            course: course,
          })
      )
        .then((response) => response.json())
        .then((data) => {
          subjects = data;
          console.log(data);
        });
        return subjects;
      }

      export async function GetTeachersName(faculty="All"){
        let teachers;
        await fetch(
          getNameUrl + "teacher/name?" +
            new URLSearchParams({
              faculty: faculty,
            })
        )
          .then((response) => response.json())
          .then((data) => {
            teachers = data;
          });
          return teachers;
      }

      export async function AddSubject(subjects, setSuccessAdding){
        let result;
        await fetch(insertUrl + "subject", {
          method: 'POST',
          headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(subjects)
        })
        .then((response) => response.json())
        .then((data) => {
          result=data;
        });
        return result;
      }

      export async function DeleteSubject(subjects){
        let result=false;
        await Promise.all(subjects.map(async subject => {
          await fetch(
            deleteUrl + "subject?" +
              new URLSearchParams({
                id: subject
              }), {method: "DELETE"}
          )
            .then((response) => response.json())
            .then((data) => {
              result=result||data;
          });
        }));
        return result;
      }

      export async function GetInfo() {
        let info;
        await fetch(
          getInfoUrl + "all")
        .then((response) => response.json())
        .then((data) => {
          info = data;
        });
        return info;
      }

      export async function GetMessages(){
        let messages;
        await fetch(
          adUrl + "get/all")
        .then((response) => response.json())
        .then((data) => {
          messages=data;
          console.log(data);
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
          result=result||data;
        });   
        return result;     
      }