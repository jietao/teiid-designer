--  Create script for the Books database on Oracle
--  Creates schema and inserts rows
--  Prior to executing this script, it is recommended that a user named "books" be created
--  It is also recommended that a "FEDERATE_DEVELOPER" role be created and assigned to the books user.
--  An example of the create scripts for the user and role is shown below.
--  To use these, remove the comment cards (that is, /* .. */).
--  Connect to Oracle with SQL*Plus as the books user and execute the script.
--  A table drop section is also included in case the script is being used to refresh the database.

-- Create Federate_Developer role and BOOKS USER

/*

CREATE ROLE FEDERATE_DEVELOPER NOT IDENTIFIED;

GRANT ALTER SYSTEM TO FEDERATE_DEVELOPER;

GRANT CREATE ANY CLUSTER TO FEDERATE_DEVELOPER;

GRANT CREATE DATABASE LINK TO FEDERATE_DEVELOPER;

GRANT CREATE LIBRARY TO FEDERATE_DEVELOPER;

GRANT CREATE MATERIALIZED VIEW TO FEDERATE_DEVELOPER;

GRANT CREATE PROCEDURE TO FEDERATE_DEVELOPER;

GRANT CREATE PUBLIC SYNONYM TO FEDERATE_DEVELOPER;

GRANT CREATE SEQUENCE TO FEDERATE_DEVELOPER;

GRANT CREATE SESSION TO FEDERATE_DEVELOPER;

GRANT CREATE SYNONYM TO FEDERATE_DEVELOPER;

GRANT CREATE TABLE TO FEDERATE_DEVELOPER;

GRANT CREATE TRIGGER TO FEDERATE_DEVELOPER;

GRANT CREATE TYPE TO FEDERATE_DEVELOPER;

GRANT CREATE VIEW TO FEDERATE_DEVELOPER;


CREATE USER books IDENTIFIED BY "mm"
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP
    QUOTA 100M ON INDX
    QUOTA 100M ON USERS
    PROFILE DEFAULT
    ACCOUNT UNLOCK;

GRANT "CONNECT" TO books;

GRANT FEDERATE_DEVELOPER TO books;

ALTER USER books DEFAULT ROLE "CONNECT",
                            FEDERATE_DEVELOPER;

GRANT CREATE PUBLIC SYNONYM TO books;

commit;

connect books/mm;

*/

--  Uncomment the following section to DROP tables before creating them.

/*

DROP TABLE BOOK_AUTHORS;

DROP TABLE BOOKS;

DROP TABLE AUTHORS;

DROP TABLE PUBLISHERS;

commit;

*/

-- Create tables

CREATE TABLE AUTHORS (
	AUTHOR_ID number(10) NOT NULL,
	FIRSTNAME varchar2 (255),
	LASTNAME varchar2 (255),
	MIDDLEINIT varchar2 (255) 
); 

CREATE TABLE BOOKS (
	ISBN varchar2 (255) NOT NULL,
	TITLE varchar2 (255) ,
	SUBTITLE varchar2 (255),
	PUBLISHER number(10),
	PUBLISH_YEAR number(10),
	EDITION number(10),
	TYPE varchar2 (255) 
);
 
CREATE TABLE BOOK_AUTHORS (
	ISBN varchar2 (255) NOT NULL,
	AUTHOR_ID number(10) NOT NULL 
);

CREATE TABLE PUBLISHERS (
	PUBLISHER_ID number(10) NOT NULL,
	NAME varchar2 (255),
	LOCATION varchar2 (255) 
);

commit;

-- Add table and column comments

COMMENT ON TABLE AUTHORS IS 'Author Table.';

COMMENT ON COLUMN AUTHORS.FIRSTNAME IS 'Author first name';

COMMENT ON COLUMN AUTHORS.LASTNAME IS 'Author last name';

COMMENT ON COLUMN AUTHORS.MIDDLEINIT IS 'Author middle initial';

COMMENT ON TABLE BOOKS IS 'Books Table.';

COMMENT ON COLUMN BOOKS.ISBN IS 'International Standard Book Number';

COMMENT ON COLUMN BOOKS.TITLE IS 'Book title';

COMMENT ON COLUMN BOOKS.SUBTITLE IS 'Book subtitle';

COMMENT ON COLUMN BOOKS.PUBLISHER IS 'Publisher identification number';

COMMENT ON COLUMN BOOKS.PUBLISH_YEAR IS 'Year book was published';

COMMENT ON COLUMN BOOKS.TYPE IS 'Book type: Hardback, Softback, or Audio';

COMMENT ON TABLE BOOK_AUTHORS IS 'Table relating Books and their Authors. Each Book may have multiple Authors.';

COMMENT ON COLUMN BOOK_AUTHORS.ISBN IS 'International Standard Book Number', N'user', N'dbo', N'table', N'BOOK_AUTHORS', N'column', N'ISBN'

COMMENT ON TABLE PUBLISHERS IS 'Table of book publishers.';

COMMENT ON COLUMN PUBLISHERS.PUBLISHER_ID IS 'Publisher identification number';

COMMENT ON COLUMN PUBLISHERS.LOCATION IS 'Publisher location';

commit;

SET DEFINE OFF;

-- INSERT rows to Authors table

INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (2,'Jeff','Rashka',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (3,'John','Paul',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (4,'David','Marco',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (5,'Alfred','Aho','V.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (6,'Ravi','Sethi',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (7,'Jeffrey','Ullman','D.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (8,'Ron','Black',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (9,'Magee','Kramer',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (10,'Douglas','Lea',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (11,'Karl','Wiegers','E.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (12,'Robert','Muller','J.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (13,'Hector','Garcia-Molina',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (14,'Fergus','O''Connell',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (15,'Li','Gong',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (16,'Brett','McLaughlin',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (17,'Mike','Loukides',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (18,'David','Flanagan',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (19,'Jamie','Jaworski',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (20,'Paul','Perrone','J.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (21,'Rob','Weltman',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (22,'Tony','Dahbura',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (23,'Tom','DeMarco',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (24,'Timothy','Lister',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (25,'Judith','Bowman','S.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (26,'Philip','Bernstein','A');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (27,'Eric','Newcomer',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (28,'Danny','Ayers',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (29,'Michael','Stonebraker',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (30,'Dwayne','Phillips',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (31,'Steve','McConnell',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (33,'Ron','Patton',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (34,'Edward','Kit',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (35,'Michael','Hernandez','J.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (36,'John','Viescas','L.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (37,'Mathew','Robinson',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (38,'Paul','Vorobiev',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (39,'Alan','Holub',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (40,'Bruce','Eckel',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (41,'Jim','Gray',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (42,'Timothy','Howes','A.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (43,'Mark','Smith','C.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (44,'Gordon','Good','S.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (45,'James','Rumbaugh',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (46,'Ivar','Jacobson',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (47,'Grady','Booch',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (48,'Alistair','Cockburn',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (49,'Charles','Goldfarb','F.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (50,'Paul','Prescod',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (51,'Michael','Kay',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (52,'Martin','Gruber',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (53,'Jennifer','Widom',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (54,'Joseph','Hellerstein','M.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (55,'Sandra','Emerson','L.');
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (56,'Marcy','Darnovsky',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (57,'Jeff','Magee',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (59,'Susannah','Finzi',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (61,'Hans','Bergsten',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (62,'Jason','Diamond',null);
INSERT INTO AUTHORS (AUTHOR_ID,FIRSTNAME,LASTNAME,MIDDLEINIT)
VALUES (1,'Elfriede','Dustin',null);

commit;

-- INSERT rows to Book_Authors table.

INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-13-014714-1',49);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-13-014714-1',50);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-13-239856-7',14);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-10088-6',5);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-10088-6',6);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-10088-6',7);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-31000-7',15);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-31009-0',10);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-43287-0',1);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-43287-0',2);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-43287-0',3);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-43336-2',35);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-43336-2',36);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-44787-8',25);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-44787-8',55);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-44787-8',56);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-65758-9',21);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-65758-9',22);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-201-70225-8',48);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-471-98710-7',9);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-471-98710-7',57);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-471355232',4);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-596-00016-2',16);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-596-00016-2',17);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-672-31602-1',19);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-672-31602-1',20);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-672-31983-7',33);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-7356-0631-5',11);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-7356-0877-6',31);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-7821-1148-3',52);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-7897-2271-2',8);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-818-68300-7',30);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-932633-33-1',11);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-932633-43-9',23);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0-932633-43-9',24);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0130273635',40);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('020130998X',45);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('020130998X',46);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('020130998X',47);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0201877562',34);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('0201877562',59);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-556-15900-5',31);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-55615-484-4',31);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-558-60190-2',41);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-55860-415-4',26);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-55860-415-4',27);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-56592-483-5',18);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-56592-487-8',18);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-57231-621-7',31);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-57870-070-1',42);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-57870-070-1',43);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-57870-070-1',44);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-861-00312-9',51);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-861-00506-7',51);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-861002-77-7',28);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-861002-77-7',61);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-861002-77-7',62);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-884777-84-8',37);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-884777-84-8',38);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1-893115-10-0',39);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('130402648',7);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('130402648',13);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('130402648',53);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1558605150',12);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1558605231',29);
INSERT INTO BOOK_AUTHORS (ISBN,AUTHOR_ID) VALUES ('1558605231',54);

commit;

-- INSERT rows to Books table.

INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-7356-0877-6','After the Gold Rush','Creating a True Profession of Software Engineering',5,1999,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-201-43287-0','Automated Software Testing','Introduction, Management, and Performance',6,1999,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-55615-484-4','Code Complete','A Practical Handbook of Software Construction',5,1993,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-201-10088-6','Compilers','Principles, Techniques, and Tools',6,1985,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-7897-2271-2','Complete Idiot''s Guide to Project Management with Microsoft Project 2000',null,10,2000,1,'Audio');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-471-98710-7','Concurrency State Models & Java Programs',null,7,1999,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-201-31009-0','Concurrent Programming in Java','Design Principles and Patterns',6,2000,2,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-932633-33-1','Creating a Software Engineering Culture',null,13,1996,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1558605150','Database Design for Smarties','Using UML for Data Modeling',14,1999,1,'Bogus');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-13-239856-7','How to Run Successful Projects II',null,16,1996,2,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-201-31000-7','Inside Java 2 Security',null,6,1999,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-596-00016-2','Java and XML',null,18,2000,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-56592-487-8','Java in a Nutshell',null,18,1999,3,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-56592-483-5','Java Enterprise in a Nutshell',null,18,1999,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-672-31602-1','Java Security Handbook',null,21,2000,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-201-65758-9','LDAP Programming with Java',null,6,2000,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-932633-43-9','Peopleware','Productive Projects and Teams',13,1999,2,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-201-44787-8','The Practical SQL Handbook','Using Structured Query Language',6,1996,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-55860-415-4','Principles of Transaction Processing',null,14,1997,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-861002-77-7','Professional Java Server Programming',null,26,1999,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-556-15900-5','Rapid Development','Taming Wild Software Schedules',5,1996,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-818-68300-7','The Software Project Manager''s Handbook','Principles that Work',29,1998,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-57231-621-7','Software Project Survival Guide',null,5,1998,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-7356-0631-5','Software Requirements',null,5,1999,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-672-31983-7','Software Testing',null,21,2001,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-201-43336-2','SQL Queries for Mere Mortals','A Hands-on Guide to Data Manipulation in SQL',6,2000,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-884777-84-8','Swing',null,35,2000,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-893115-10-0','Taming Java Threads',null,36,2000,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-558-60190-2','Transaction Processing','Concepts and Techniques',14,1992,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-57870-070-1','Understanding and Deploying LDAP Directory Services',null,39,1990,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('020130998X','The Unified Modeling Language Reference Manual',null,6,1998,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-201-70225-8','Writing Effective Use Cases',null,6,2001,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-13-014714-1','The XML Handbook',null,16,2000,2,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-861-00312-9','XSLT Programmer''s Reference',null,26,2000,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-7821-1148-3','SQL Instant Reference',null,44,1993,1,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('130402648','Database System Implementation',null,16,1999,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1558605231','Readings in Database Systems',null,14,1998,3,'Hardback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0-471355232','Building and Managing the Meta Data Repository','A Full Life-Cycle Guide',7,2000,1,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0201877562','Software Testing in the Real World','Improving the Process',6,1995,1,'HardBack');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('0130273635','Thinking in Java',null,16,2000,2,'Softback');
INSERT INTO BOOKS (ISBN,TITLE,SUBTITLE,PUBLISHER,PUBLISH_YEAR,EDITION,TYPE)
VALUES ('1-861-00506-7','XSLT Programmer''s Reference',null,26,2001,2,'Hardback');

commit;

-- INSERT rows to Publishers table.

INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (3,'Publisher',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (5,'Microsoft Press',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (6,'Addison-Wesley',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (7,'Wiley',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (10,'Que',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (13,'Dorset House Publishing',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (14,'Morgan Kaufman',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (16,'Prentice Hall',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (18,'O''Reilly',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (21,'Sams',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (26,'Wrox',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (29,'IEEE Computer Society',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (35,'Manning',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (36,'Apress',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (39,'Macmillan',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (44,'Sybex',null);
INSERT INTO PUBLISHERS (PUBLISHER_ID,NAME,LOCATION) VALUES (2,'Publishing Information',null);

commit;

SET DEFINE ON;

-- Add Primary Keys

ALTER TABLE AUTHORS 
ADD CONSTRAINT PK_AUTHORS PRIMARY KEY (AUTHOR_ID);

ALTER TABLE BOOKS 
ADD CONSTRAINT PK_BOOKS PRIMARY KEY (ISBN);

ALTER TABLE BOOK_AUTHORS 
ADD CONSTRAINT PK_BOOK_AUTHORS PRIMARY KEY (ISBN, AUTHOR_ID); 

ALTER TABLE PUBLISHERS 
ADD CONSTRAINT PK_PUBLISHERS PRIMARY KEY (PUBLISHER_ID);

commit;

-- Add Foreign Keys

ALTER TABLE BOOKS 
ADD CONSTRAINT FK_PUBLISHER FOREIGN KEY (PUBLISHER) 
REFERENCES PUBLISHERS (PUBLISHER_ID);

ALTER TABLE BOOK_AUTHORS 
ADD CONSTRAINT FK_AUTHOR FOREIGN KEY (AUTHOR_ID) 
REFERENCES AUTHORS (AUTHOR_ID);

ALTER TABLE BOOK_AUTHORS
ADD CONSTRAINT FK_ISBN FOREIGN KEY (ISBN) 
REFERENCES BOOKS (ISBN);

commit;
