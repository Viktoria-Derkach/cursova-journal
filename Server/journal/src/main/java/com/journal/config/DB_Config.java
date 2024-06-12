//package com.journal.config;
//
//import com.journal.entities.*;
//import com.journal.entities.Direction;
//import com.journal.repositories.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//@Configuration
//public class DB_Config {
//
//    @Bean
//    CommandLineRunner commandLineRunner(
//            AdRepository adRepository,
//            AdminRepository adminRepository,
//            ChairRepository chairRepository,
//            DirectionRepository directionRepository,
//            FacultyRepository facultyRepository,
//            PointRepository pointRepository,
//            StudentRepository studentRepository,
//            SubjectRepository subjectRepository,
//            TeacherRepository teacherRepository) {
//        return args -> {
//            Faculty electronics = new Faculty("Факультет електроніки та комп'ютерних технологій");
//            Faculty fiz_math = new Faculty("Фізико-математичний факультет");
//
//            facultyRepository.saveAll(List.of(electronics, fiz_math));
//
//            Chair oit = new Chair("Кафедра системного проектування");
//            Chair pbe = new Chair("Кафедра радіоелектронних та комп'ютрених систем");
//            Chair m = new Chair("Кафедра механіки");
//            Chair hm = new Chair("Кафедра вищої математики");
//
//
//            oit.setFaculty(electronics);
//            pbe.setFaculty(electronics);
//            m.setFaculty(fiz_math);
//            hm.setFaculty(fiz_math);
//
//            chairRepository.saveAll(List.of(oit, pbe, m, hm));
//
//            Direction fei = new Direction("ФеІ");
//            Direction fem = new Direction("ФеМ");
//            Direction mtk = new Direction("МТК");
//            Direction mto = new Direction("МТО");
//
//            fei.setFaculty(electronics);
//            fem.setFaculty(electronics);
//            mtk.setFaculty(fiz_math);
//            mto.setFaculty(fiz_math);
//
//            directionRepository.saveAll(List.of(fei, fem, mtk, mto));
//
//            //Password = admin1password
//            Admin af = new Admin("admin.mail@gmail.com",
//                    "e2f13fb5bc47424d6b27b3ac1c605d33f1f598c1db269b044c3f59338d1e583f");
//            //Password = second admin
//            Admin as = new Admin("second.admin.mail@gmail.com",
//                    "73ec83bd440bab097b9046945e697b2697ba781c80a878ce673ea893048b99b6");
//
//            adminRepository.saveAll(List.of(af, as));
//
//            //Password = demkivpassword
//            Teacher demkiv = new Teacher("demkiv.l@lnu.edu.ua",
//                    "305dcef7f23cb48af053fe4fea7dacbdab9c4e2340bf6e1e9cada04e190a51a6",
//                    "Демків Лідія Степанівна", electronics, oit);
//            //Password = XIOpassword
//            Teacher xio = new Teacher("xiocompan@gmail.com",
//                    "30e8663a8cf3c0ca5f59c4837ea05fc76bba97f8bc25c18b066a94e72407c12a",
//                    "Хвищун Іван Олександрович", electronics, pbe);
//
//            //Password = banakhpassword
//            Teacher banakh = new Teacher("banakh.t@lnu.edu.ua",
//                    "9384659568770759be0458ab6d6eb74fcfc07f8247e89aafe9ab2487d7920013",
//                    "Банах Тарас Онуфрійович", fiz_math, hm);
//            //Password = guranpassword
//            Teacher guran = new Teacher("guran.i@mail.com",
//                    "59ce14c415beacb6a89ed5a9acee29b88490d2ee8ba58389d30b29ac7ac10c15",
//                    "Гуран Ігор Йосипович", fiz_math, m);
//
//            teacherRepository.saveAll(List.of(demkiv, xio, banakh, guran));
//
//            //Password = sidorpassword
//            Student sidor = new Student("sidor.o@mail.com",
//                    "06ad0e56560945a4153a0f974b4a667be48a21be5d49d5bcca3f5db0c82d87ec",
//                    "Сідор О. О.", 1, electronics, fem, 1, 1);
//            //Password = fedorpassword
//            Student fedor = new Student("fedor.o@mail.com",
//                    "470d8bdbbf8c812c828b60967c6675e02b91d1d7f59a83adafa65ce7e8a9349e",
//                    "Федор О. В.", 2, electronics, fei, 3, 2);
//
//            //Password = morozpassword
//            Student moroz = new Student("moroz.kh@mail.com",
//                    "c900f186b13f8e23fd81a1864b1d2b9bb7a7979a5faf8f5e6cd55f5cfbd0ad7d",
//                    "Мороз Х. В.", 3, fiz_math, mtk, 2, 3);
//            //Password = fedakpassword
//            Student fedak = new Student("fedak.m@mail.com",
//                    "a4a9b83121a33034dab0845837b17209b958ebbe0935ab060e5b6c08b1da9d72",
//                    "Федак М. О.", 4, fiz_math, mto, 4, 4);
//
//            studentRepository.saveAll(List.of(sidor, fedor, moroz, fedak));
//
//            Ad first = new Ad("Формат проведення зимової заліково-екзаменаційної сесії 2022 — змішаний.", "1,2,3,4 курси ОР бакалавр:\n" +
//                    "Заліки можуть проводитися дистанційно; іспити — в аудиторному форматі\nОР Магістр — змішаний (очно-дистанційний) формат. Якщо магістри навчались дистанційно, ймовірно і сесія буде дистанційна (це опція для магістрів, в яких є практичні завдання на іспитах і потрібно перебувати в лабораторії).", LocalDate.of(2022, Month.NOVEMBER, 18));
//            Ad second = new Ad("Перенесення 'Що? Де? Коли?'", "На жаль, через ситуацію з електроенергією у Львові гра переноситься на 30 листопада (середа). Час, локація та зареєстровані команди зберігаються.", LocalDate.of(2022, Month.NOVEMBER, 16));
//            Ad third = new Ad("Перехід на дистанційний формат навчання до 15 жовтня.", "ЛНУ переходить на дистанційне навчання до 15 жовтня включно.\nУ суботу навчання відбуватиметься також у дистанційному форматі. \nБережіть себе!", LocalDate.of(2022, Month.OCTOBER, 10));
//            Ad fourth = new Ad("ПРОЄКТ РЕЙТИНГУ ПЕРШОГО КУРСУ БАКАЛАВРАТУ", "Просимо ознайомитися із рейтинговими балами за посиланням http://studviddil.lnu.edu.ua/http-studviddil-lnu-edu-ua-reytynh-uspishnosti-studentiv-proekt-reytynh-uspishnosti-studentiv-za-rezultatamy-1-semestrovoho-kontroliu-2022-2023-n-r/ і у випадку, якщо студент претендує на академічну та соціальну стипендії одночасно (окрім студентів зі статусом сироти/дитини-сироти/дитини, позбавленої батьківського піклування, які можуть отримувати дві стипендії одночасно) і бажає відмовитися від академічної на користь соціальної, має звернутись у деканат свого факультету до 15:00 20 вересня 2022 р.\n" +
//                    "\n" +
//                    "Стипендія буде виплачуватися до наступного семестрового контролю.", LocalDate.of(2022, Month.SEPTEMBER, 19));
//
//            first.setPostedBy(demkiv);
//            second.setPostedBy(banakh);
//            third.setPostedBy(xio);
//            fourth.setPostedBy(guran);
//
//            first.addStudent(sidor);
//            first.addStudent(fedor);
//
//            second.addStudent(moroz);
//
//            third.addStudent(sidor);
//
//            fourth.addStudent(fedak);
//
//            adRepository.saveAll(List.of(first, second, third, fourth));
//
//            Subject sub1 = new Subject("Програмування та підтримка веб-застосувань", electronics, oit, fei, 3, "2", 200);
//            Subject sub2 = new Subject("Алгоритмізація та прогр", electronics, pbe, fem, 1, "1", 150);
//            Subject sub3 = new Subject("Основи математики", fiz_math, m, mto, 2, "3", 100);
//            Subject sub4 = new Subject("Математичний аналіз", fiz_math, hm, mtk, 4, "4", 75);
//
//
//            sub1.addTeacher(demkiv);
//            sub2.addTeacher(xio);
//            sub3.addTeacher(banakh);
//            sub4.addTeacher(guran);
//
//            sub1.addStudent(sidor);
//            sub2.addStudent(sidor);
//            sub2.addStudent(fedor);
//            sub3.addStudent(fedak);
//
//            subjectRepository.saveAll(List.of(sub1, sub2, sub3, sub4));
//
//            Point p1s = new Point(8, LocalDate.of(2022, Month.NOVEMBER, 19), sidor, sub1);
//            Point p2s = new Point(3, LocalDate.of(2022, Month.NOVEMBER, 12), sidor, sub1);
//            Point p3s = new Point(15, LocalDate.of(2022, Month.NOVEMBER, 5), sidor, sub2);
//            Point p1fr = new Point(4, LocalDate.of(2022, Month.OCTOBER, 21), fedor, sub2);
//            Point p2fr = new Point(3, LocalDate.of(2022, Month.OCTOBER, 7), fedor, sub2);
//            Point p3fr = new Point(9, LocalDate.of(2022, Month.NOVEMBER, 19), fedor, sub2);
//            Point p1fd = new Point(5, LocalDate.of(2022, Month.SEPTEMBER, 23), fedak, sub3);
//            Point p2fd = new Point(5, LocalDate.of(2022, Month.SEPTEMBER, 30), fedak, sub3);
//            Point p3fd = new Point(5, LocalDate.of(2022, Month.OCTOBER, 7), fedak, sub3);
//
//            pointRepository.saveAll(List.of(p1s, p2s, p3s, p1fr, p2fr, p3fr, p1fd, p2fd, p3fd));
//        };
//    }
//}
