;             
CREATE USER IF NOT EXISTS SA SALT '9b50b71bfb3d9db8' HASH 'aca1a1089afb626fea40796a2c04bf5edb851db98a151423a229e2cd2844abdc' ADMIN;           
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_B879FFD0_34E4_4D57_8FDD_831637E56853 START WITH 1 BELONGS_TO_TABLE;    
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_51478B26_5746_467C_A922_D469A7451646 START WITH 1 BELONGS_TO_TABLE;    
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_603545F8_8E41_4F05_B366_48407A2F2630 START WITH 1 BELONGS_TO_TABLE;    
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_E37A9910_CD82_4BB7_96B1_2F9BBAF5F8AB START WITH 1 BELONGS_TO_TABLE;    
CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_B2F8BACD_68B7_4534_ACF5_F7BF50D891C1 START WITH 1 BELONGS_TO_TABLE;    
CREATE CACHED TABLE PUBLIC.CATEGORY(
    CATEGORY_ID INT NOT NULL,
    NAME VARCHAR(45) NOT NULL,
    COLOR SMALLINT NOT NULL,
    ICON VARCHAR(45) DEFAULT NULL
);      
ALTER TABLE PUBLIC.CATEGORY ADD CONSTRAINT PUBLIC.CONSTRAINT_3 PRIMARY KEY(CATEGORY_ID);      
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.CATEGORY;
INSERT INTO PUBLIC.CATEGORY(CATEGORY_ID, NAME, COLOR, ICON) VALUES
(1, 'Privat', 1, 'Icon-Privat'),
(2, 'Uni', 2, 'Icon-Uni'),
(3, 'Familie', 3, 'Icon-Familie');          
CREATE CACHED TABLE PUBLIC.TERMIN(
    TERMIN_ID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    START DATE NOT NULL,
    STARTZEIT TIME NOT NULL,
    ENDE DATE NOT NULL,
    ENDEZEIT TIME NOT NULL,
    ALLDAY TINYINT NOT NULL,
    ORT VARCHAR(100) DEFAULT NULL,
    REPEATBOOL TINYINT NOT NULL,
    REPEATTIME VARCHAR(12) DEFAULT NULL,
    CANCEL TINYINT DEFAULT NULL,
    ATTACHEMENT BLOB,
    NOTE VARCHAR(200) DEFAULT NULL,
    PRIORITY INT DEFAULT NULL,
    REMINDER TINYINT DEFAULT NULL,
    VCARD INT DEFAULT NULL,
    CANCELMSG VARCHAR(50) DEFAULT NULL,
    REMINDERDATE DATE DEFAULT NULL,
    REMINDERTIME TIME DEFAULT NULL
);             
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.TERMIN;  
CREATE CACHED TABLE PUBLIC.VCARD(
    VCARD_ID INT NOT NULL,
    FIRSTNAME VARCHAR(45) NOT NULL,
    LASTNAME VARCHAR(45) NOT NULL,
    TELNR VARCHAR(45) NOT NULL,
    OFFICE VARCHAR(45) NOT NULL,
    TITLE VARCHAR(45) NOT NULL,
    EMAIL VARCHAR(45) NOT NULL,
    NOTE VARCHAR(200) NOT NULL
);               
ALTER TABLE PUBLIC.VCARD ADD CONSTRAINT PUBLIC.CONSTRAINT_4 PRIMARY KEY(VCARD_ID);            
-- 3 +/- SELECT COUNT(*) FROM PUBLIC.VCARD;   
INSERT INTO PUBLIC.VCARD(VCARD_ID, FIRSTNAME, LASTNAME, TELNR, OFFICE, TITLE, EMAIL, NOTE) VALUES
(1, 'Malte', 'Rusko', '0451 4094801', 'Office 1', 'Dr.', 'malte.rusko@stud.fh-luebeck.de', 'Erste VCard!'),
(2, 'Philipp', 'Drath', '0451 4094802', 'Office 2', 'Prof', 'philipp.drath@stud.fh-luebck.de', 'Zweite VCard!'),
(3, 'Sergej', 'Makschanow', '0451 4094803', 'Office 3', 'Prof. rer. nat.', 'sergej.makschanow@stud.fh-luebeck.de', 'Die dritte VCard!');    
CREATE CACHED TABLE PUBLIC.CALENDAR(
    CALENDAR_ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_E37A9910_CD82_4BB7_96B1_2F9BBAF5F8AB) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_E37A9910_CD82_4BB7_96B1_2F9BBAF5F8AB,
    USER_ID INT NOT NULL
);   
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CALENDAR;
CREATE CACHED TABLE PUBLIC.CALENDAR_TERMIN(
    CALENDAR_TERMIN_ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_51478B26_5746_467C_A922_D469A7451646) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_51478B26_5746_467C_A922_D469A7451646,
    TERMIN_ID INT NOT NULL,
    CALENDAR_ID INT NOT NULL
);    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CALENDAR_TERMIN;         
CREATE CACHED TABLE PUBLIC.CATEGORY_TERMIN(
    CATEGORY_TERMIN_ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_B2F8BACD_68B7_4534_ACF5_F7BF50D891C1) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_B2F8BACD_68B7_4534_ACF5_F7BF50D891C1,
    TERMIN_ID INT NOT NULL,
    CATEGORY_ID INT NOT NULL
);    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.CATEGORY_TERMIN;         
CREATE CACHED TABLE PUBLIC.USER(
    USER_ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_B879FFD0_34E4_4D57_8FDD_831637E56853) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_B879FFD0_34E4_4D57_8FDD_831637E56853,
    USERNAME VARCHAR(45) NOT NULL,
    PASSWORD VARCHAR(45) NOT NULL,
    EMAIL VARCHAR(45) NOT NULL,
    FIRSTNAME VARCHAR(45) NOT NULL,
    LASTNAME VARCHAR(45) NOT NULL,
    CONFIRMEDUSER TINYINT DEFAULT NULL,
    USERPREFERENCES INT DEFAULT NULL,
    WEATHERREPORT BLOB,
    CALENDAR_ID INT NOT NULL
);            
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USER;    
CREATE CACHED TABLE PUBLIC.USERPREFERENCES(
    USER_PREFERENCES_ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_603545F8_8E41_4F05_B366_48407A2F2630) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_603545F8_8E41_4F05_B366_48407A2F2630,
    PROVINCE VARCHAR(45) DEFAULT NULL,
    UNIVERSITY VARCHAR(45) DEFAULT NULL,
    COURSE VARCHAR(45) DEFAULT NULL,
    SEMESTER VARCHAR(45) DEFAULT NULL,
    MUSIC VARCHAR(45) DEFAULT NULL,
    GENDER VARCHAR(45) DEFAULT NULL,
    AGE INT DEFAULT NULL,
    USER_ID INT NOT NULL
);              
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.USERPREFERENCES;         
ALTER TABLE PUBLIC.TERMIN ADD CONSTRAINT PUBLIC.TERMIN_IBFK_1 FOREIGN KEY(VCARD) REFERENCES PUBLIC.VCARD(VCARD_ID) NOCHECK;   
ALTER TABLE PUBLIC.USERPREFERENCES ADD CONSTRAINT PUBLIC.USERPREFERENCES_IBFK_1 FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USER(USER_ID) NOCHECK; 
ALTER TABLE PUBLIC.CALENDAR ADD CONSTRAINT PUBLIC.CALENDAR_IBFK_1 FOREIGN KEY(USER_ID) REFERENCES PUBLIC.USER(USER_ID) NOCHECK;               
