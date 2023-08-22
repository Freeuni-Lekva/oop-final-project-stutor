INSERT INTO users(username, hashedPassword, firstname, lastname, email ) VALUES
    ("admin", "$2a$10$3n/kMAcUp98f2Yh5fNsBQeoSnTsbBpt42zRoZhasnMR.tMhvwDk4S", "irakli", "khutshishvili", "admin"),
    ("dvalidvali", "$2a$10$3n/kMAcUp98f2Yh5fNsBQeoSnTsbBpt42zRoZhasnMR.tMhvwDk4S", "nikoloz", "dvali", "ndval@freeuni.edu.ge"),
    ("BIGJOY", "$2a$10$3n/kMAcUp98f2Yh5fNsBQeoSnTsbBpt42zRoZhasnMR.tMhvwDk4S", "giorgi", "chalagashvili", "gchal@freeuni.edu.ge"),
    ("wry", "$2a$10$3n/kMAcUp98f2Yh5fNsBQeoSnTsbBpt42zRoZhasnMR.tMhvwDk4S", "data", "shanidze", "dshan@freeuni.edu.ge"),
    ("dushki", "$2a$10$3n/kMAcUp98f2Yh5fNsBQeoSnTsbBpt42zRoZhasnMR.tMhvwDk4S", "demetre", "gelashvili", "dgela@freeuni.edu.ge"),
    ("ruska", "$2a$10$3n/kMAcUp98f2Yh5fNsBQeoSnTsbBpt42zRoZhasnMR.tMhvwDk4S", "ruska", "keldishvili", "rkeld@freeuni.edu.ge"),
    ("GLUNCHO", "$2a$10$3n/kMAcUp98f2Yh5fNsBQeoSnTsbBpt42zRoZhasnMR.tMhvwDk4S", "nika", "glunchadze", "nglun@freeuni.edu.ge"),
    ("KBG", "$2a$10$3n/kMAcUp98f2Yh5fNsBQeoSnTsbBpt42zRoZhasnMR.tMhvwDk4S", "keti", "begiashvili", "kbegi@freeuni.edu.ge"),
    ("DZADZO", "$2a$10$3n/kMAcUp98f2Yh5fNsBQeoSnTsbBpt42zRoZhasnMR.tMhvwDk4S", "giorgi", "dzadzamia", "gdzad@freeuni.edu.ge"),
    ("broxlgar", "$2a$10$3n/kMAcUp98f2Yh5fNsBQeoSnTsbBpt42zRoZhasnMR.tMhvwDk4S", "dato", "chechelashvili", "dchec@freeuni.edu.ge");

INSERT INTO subjects (subject_name) VALUES
    ('Any subject'),
    ('Mathematics'),
    ('Science'),
    ('English Language'),
    ('Physics'),
    ('Chemistry'),
    ('Biology'),
    ('History'),
    ('Geography'),
    ('Computer Science'),
    ('Economics'),
    ('Literature'),
    ('Social Studies'),
    ('Languages'),
    ('Music'),
    ('Art'),
    ('Physical Education'),
    ('Psychology'),
    ('Environmental Science'),
    ('Political Science'),
    ('Business Studies');

INSERT INTO admins (adminName) VALUES
    ('admin'),
    ('dvalidvali'),
    ('BIGJOY');

INSERT INTO posts (username, subject_name, type, text) VALUES
    ('admin', 'Physics', 'teach', 'saolimpiado fizikis swavlis msurvelebu damikavshirdit'),
    ('BIGJOY', 'Computer Science', 'teach', 'htmls vaswavli umaghles doneze'),
    ('dvalidvali', 'Literature', 'teach', 'litkhelshi myavs A'),
    ('wry', 'Literature', 'learn', 'litxelshi maqvs fx, batonma zaalma ar damiwera kargi qula finalurze da mondomebuli bichi var da darwmunebuli var rom 81 procents avigheb fxze'),
    ('broxlgar', 'Economics', 'learn', 'zaza gelashvilis leqciebis mosmenis shemdeg mivkhvdi, rom saintereso sagania'),
    ('dushki', 'Social Studies', 'learn', 'qalbaton ana khutshiviltan saubris shemdeg mivkhvdi, rom libri dzalian magari sagania da minda, rom bevri msgavsi sagani gaviaro'),
    ('gluncho', 'Mathematics', 'teach', 'mravali olimpiadis medalosani gaswavlit saolimpiado matematika');

INSERT INTO chat (sender, receiver, message) VALUES
    ('dvalidvali', 'BIGJOY', 'bigjoy rogor khar?'),
    ('BIGJOY', 'dvalidvali', 'kargad shen rogor khar? rom gwer pasuxs ratom ar mcem?'),
    ('dvalidvali', 'BIGJOY', 'medzina'),
    ('wry', 'dvalidvali', 'gamarjoba, litkhelshi minda momzadeba da gavige A gyoliat litkhelshi. rogor shedzelit da ranairad imecadinet shegidzliat mitkhrat?'),
    ('dvalidvali', 'wry', 'mtavaria kacoba da literatura da xelovneba mxolod smedegia, shen tu shedzleb da kaci gakhdebi, litkhelshi ki ara... ar vici rashi magram kargia ra'),
    ('wry', 'dvalidvali', 'ra brdznuli sityvebia mefeo, gtkhovt, momecit ufleba dagekhmarot'),
    ('dvalidvali', 'wry', 'shen ra ici rom mefe var'),
    ('wry', 'dvalidvali', 'tqveni mosmenistanave mivkhvdi am yvelafers, martlac rom filosofiashi 107 qula gekutvnodat');
