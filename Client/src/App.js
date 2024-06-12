import {BrowserRouter, Routes, Route, Outlet, Navigate } from "react-router-dom";

import StartPage from "./StartPage/StartPage";

import StudentNavbar from "./student/StudentNavbar";
import InfoStudent from "./student/Info/InfoStudent";
import MarksStudent from "./student/Marks/MarksStudent";
import SubjectsStudent from "./student/Subjects/SubjectsStudent";
import AdsStudent from "./student/Ads/AdsStudent";

import TeacherNavbar from "./teacher/TeacherNavbar";
import InfoTeacher from "./teacher/Info/InfoTeacher";
import MarksTeacher from "./teacher/Marks/MarksTeacher";
import SubjectsTeacher from "./teacher/Subjects/SubjectsTeacher";
import AdsTeacher from "./teacher/Ads/AdsTeacher";

import DekanatNavbar from "./dekanat/DekanatNavbar";
import InfoDekanat from "./dekanat/Info/InfoDekanat";
import StudentsDekanat from "./dekanat/Students/StudentsDekanat";
import TeachersDekanat from "./dekanat/Teachers/TeachersDekanat";
import SubjectsDekanat from "./dekanat/Subjects/SubjectsDekanat";
import AdsDekanat from "./dekanat/Ads/AdsDekanat";

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<Outlet/>}>
          <Route index element={<StartPage />} />
        </Route>

        <Route path="student/" element={<StudentNavbar />}>        
          <Route index element={<InfoStudent />} />
          <Route path="marks" element={<MarksStudent />} />
          <Route path="subjects" element={<SubjectsStudent />} />
          <Route path="ads" element={<AdsStudent />} />
        </Route>

        <Route path="teacher/" element={<TeacherNavbar />}>
          <Route index element={<InfoTeacher />} />
          <Route path="marks" element={<MarksTeacher />} />
          <Route path="subjects" element={<SubjectsTeacher />} />
          <Route path="ads" element={<AdsTeacher />} />
        </Route>

        <Route path="dekanat/" element={<DekanatNavbar />}>
          <Route index element={<InfoDekanat />} />
          <Route path="students" element={<StudentsDekanat />} />
          <Route path="teachers" element={<TeachersDekanat />} />
          <Route path="subjects" element={<SubjectsDekanat />} />
          <Route path="ads" element={<AdsDekanat />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
