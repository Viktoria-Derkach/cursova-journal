const pointUrl = "http://localhost:8000/point/";
const getNameUrl = "http://localhost:8000/get/name/";
const getEntityUrl = "http://localhost:8000/get/entity/";
const getInfoUrl = "http://localhost:8000/get/info/";
const adUrl = "http://localhost:8000/ad/";


export async function GetInfo(id){
    let info;
     await fetch(
       getInfoUrl + "student?" + 
         new URLSearchParams({
           id: id
         }))
     .then((response) => response.json())
     .then((data) => {
       info=data;
     });    
    return info;
  }

  export async function GetSubjectsName(id){
    let subjects;
    await fetch(
      getNameUrl + "student/subject?" +
        new URLSearchParams({
          id: id
        })
    )
      .then((response) => response.json())
      .then((data) => {
        subjects =data;
      });
    return subjects;
  }

  export async function GetMarks(subject, id){
    let marks;
    await fetch(
      pointUrl + "get/student?" +
        new URLSearchParams({
          id: id,
          subject: subject
        })
    )
      .then((response) => response.json())
      .then((data) => {
        marks=data;
        console.log(data);
      });    
    return marks;
  }

  export async function GetSubjects(id){
    let subjects;
   await fetch(
     getEntityUrl + "student/subject?" +
       new URLSearchParams({
         id: id,
       })
   )
     .then((response) => response.json())
     .then((data) => {
       subjects = data;
     }); 
  return subjects;
  }

  export async function GetMessages(id){
    let messages;
    await fetch(
       adUrl + "get/student?" + 
       new URLSearchParams({
         id: id
       }))
     .then((response) => response.json())
     .then((data) => {
       messages=data;
     });    
    return messages;
  }