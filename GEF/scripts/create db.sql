DROP TABLE GEFDATABASE.CLASS_TIME;
DROP TABLE GEFDATABASE.STUDENTS_CLASS;
DROP TABLE GEFDATABASE.COURSE;
DROP TABLE GEFDATABASE.ROOM;


CREATE TABLE GEFDATABASE.Course (ID_COURSE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
CODE_COURSE VARCHAR(5) NOT NULL UNIQUE,
CREDIT INTEGER NOT NULL,
NAME VARCHAR(20) NOT NULL,
MODULE INTEGER NOT NULL,
PRIMARY KEY (ID_COURSE));

CREATE TABLE GEFDATABASE.Room ( ID_ROOM INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
ROOM_NAME VARCHAR(10) NOT NULL,
BUILDING VARCHAR(10) NOT NULL,
CAPACITY INTEGER NOT NULL,
PRIMARY KEY (ID_ROOM),
CONSTRAINT UK_ROOM UNIQUE (ROOM_NAME, BUILDING));

CREATE TABLE GEFDATABASE.Students_Class ( ID_STUDENTS_CLASS INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
NUMBER_STUDENTS_CLASS VARCHAR(5) NOT NULL, 
ID_COURSE INTEGER NOT NULL,
PRIMARY KEY(ID_STUDENTS_CLASS),
FOREIGN KEY(ID_COURSE) REFERENCES GEFDATABASE.Course,
CONSTRAINT UK_STUDENTS_CLASS UNIQUE (NUMBER_STUDENTS_CLASS, ID_COURSE));

CREATE TABLE GEFDATABASE.Class_Time( ID_CLASS_TIME INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
ID_STUDENTS_CLASS INTEGER NOT NULL,
ID_ROOM INTEGER,
DAY_OF_WEEK VARCHAR(10) NOT NULL,
CLASS_TIME VARCHAR(1) NOT NULL,
PRIMARY KEY (ID_CLASS_TIME),
FOREIGN KEY(ID_STUDENTS_CLASS) REFERENCES GEFDATABASE.Students_Class,
CONSTRAINT UK_CLASS_TIME UNIQUE(ID_STUDENTS_CLASS, DAY_OF_WEEK, CLASS_TIME));