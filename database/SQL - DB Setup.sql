-- CREATE DATABASE gym;
CREATE TABLE gym (
gym_ID INT NOT NULL AUTO_INCREMENT,
gym_Name VARCHAR(100) NOT NULL,
phone_Number VARCHAR(100) NOT NULL,
address VARCHAR(100),
city VARCHAR(100),
state VARCHAR(2),
zip_Code INT,
PRIMARY KEY (gym_ID)
);

CREATE TABLE trainer (
trainer_ID INT NOT NULL AUTO_INCREMENT,
first_Name VARCHAR(100) NOT NULL,
last_Name VARCHAR(100) NOT NULL,
gym_ID INT,
PRIMARY KEY (trainer_ID),
FOREIGN KEY (gym_ID) REFERENCES gym(gym_ID)
);

CREATE TABLE classes (
class_ID INT NOT NULL AUTO_INCREMENT,
class_Name VARCHAR(100) NOT NULL,
date_Time DATETIME,
gym_ID INT,
trainer_ID INT,
PRIMARY KEY (class_ID),
FOREIGN KEY (gym_ID) REFERENCES gym(gym_ID),
FOREIGN KEY (trainer_ID) REFERENCES trainer(trainer_ID)
);

CREATE TABLE membership (
member_ID INT NOT NULL AUTO_INCREMENT,
first_Name VARCHAR(100) NOT NULL,
last_Name VARCHAR(100) NOT NULL,
phone_Number VARCHAR(14),
birth_Date DATE,
gym_ID INT NOT NULL,
PRIMARY KEY (member_ID),
FOREIGN KEY (gym_ID) REFERENCES gym(gym_ID)
);

CREATE TABLE classes_Scheduled (
schedule_ID INT NOT NULL AUTO_INCREMENT,
member_ID INT NOT NULL,
class_ID INT NOT NULL,
PRIMARY KEY (schedule_ID),
FOREIGN KEY (member_ID) REFERENCES membership(member_ID),
FOREIGN KEY (class_ID) REFERENCES classes(class_ID)
);

--  /*/**/*/**  DROP DATABASE gym;
-- DROP TABLE gym;
-- DROP TABLE membership;
-- DROP TABLE trainer;
-- DROP TABLE classes;
-- DROP TABLE classes_Scheduled

/*
INSERT INTO gym (gym_Name, phone_Number, address, city, state, zip_Code)
VALUES ('Slytherin', '480-444-1212', '123 E My Street', 'Mesa', 'AZ', 85204);
*/
/*
INSERT INTO membership (first_Name, last_Name, phone_Number, birth_Date, gym_ID)
VALUES ('Kim', 'Smith', '480-555-1515', '1985-01-30',1);
INSERT INTO membership (first_Name, last_Name, phone_Number, birth_Date, gym_ID)
VALUES ('Tom', 'Baker', '480-333-1515', '1980-01-30',1);
*/
/*
INSERT INTO trainer (first_Name, last_Name, gym_ID)
VALUES ('Ashley', 'Clay',1);
INSERT INTO trainer (first_Name, last_Name, gym_ID)
VALUES ('Brian', 'Taps',1);
*/
/*
INSERT INTO classes (class_Name, date_Time, gym_ID, trainer_ID)
VALUES ('Zumba', '2020-07-04 17:30:00',1,1);
INSERT INTO classes (class_Name, date_Time, gym_ID, trainer_ID)
VALUES ('Boxing 101', '2020-07-05 15:00:00',1,2);
*/
/*
INSERT INTO classes_Scheduled (member_ID, class_ID)
VALUES (1, 2);
INSERT INTO classes_Scheduled (member_ID, class_ID)
VALUES (2, 1);
*/

SELECT * 
FROM classes_scheduled;

-- Update trainer SET first_Name = "Hermione", last_Name = "Granger" WHERE trainer_ID = 2;
