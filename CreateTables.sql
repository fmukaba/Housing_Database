CREATE SCHEMA HOUSING;
USE HOUSING;

CREATE TABLE USER (

ID_number           VARCHAR(9)            NOT NULL,
Username            VARCHAR(50),
Password            VARCHAR(50),
Name                VARCHAR(50),
Gender              VARCHAR(8),
Student_status      BOOLEAN,   
Marital_status      BOOLEAN,
Address             VARCHAR(50),
Phone_number        INT,    
College             VARCHAR(50),
Department          VARCHAR(50),
Major               VARCHAR(50),
Family_head_ID      VARCHAR(9),
PRIMARY KEY (ID_number)
);

ALTER TABLE USER ADD FOREIGN KEY(Family_head_ID) references USER(ID_number) on delete cascade;

CREATE TABLE ADMINISTRATOR (

Staff_ID              VARCHAR(9)            NOT NULL,
SSN                   INT,
Name                  VARCHAR(50),
Gender                VARCHAR(8),
Phone_number          INT,
Dept_name             VARCHAR(50),
Job_title             VARCHAR(50),
ResidentAdmin_flag    BOOLEAN,
MaintenanceAdmin_flag BOOLEAN,
AdmissionsAdmin_flag  BOOLEAN,

PRIMARY KEY (Staff_ID)
);

CREATE TABLE HOUSING_UNIT (

Building_number         INT     NOT NULL,
Apt_number              INT     NOT NULL,
Bedroom_number          INT,
Max_people              INT,
Married_couples_allowed BOOLEAN,
Bed_number              INT,
Housing_type            VARCHAR(50),
Occupation_status       BOOLEAN,
Price_quarter		INT,

PRIMARY KEY (Building_number,Apt_number)
);

CREATE TABLE RESIDENT (

ID_number               VARCHAR(9)         NOT NULL,
Admin_staff_ID          VARCHAR(9)         NOT NULL,
Move_in_date            DATE,
Check_out_date          DATE,
Building_number         INT         NOT NULL,
Apt_number              INT         NOT NULL,
Rent_till_date          INT,

FOREIGN KEY (ID_number) REFERENCES USER (ID_number) on delete cascade,
primary key (ID_number),
FOREIGN KEY (Building_number,Apt_number) REFERENCES HOUSING_UNIT(Building_number,Apt_number) on delete cascade
);

CREATE TABLE APPLICANT (

ID_number		VARCHAR(9)         NOT NULL,
Acceptance_status       BOOLEAN,

PRIMARY KEY (ID_number),
FOREIGN KEY (ID_number) REFERENCES USER (ID_number) on delete cascade
);

CREATE TABLE APPLICATION (

Application_number      INT    	      NOT NULL,
Admin_staff_ID          VARCHAR(9)    NOT NULL,
User_ID_number          VARCHAR(9)    NOT NULL,
Fee_paid                BOOLEAN,
Admission_status        BOOLEAN,

PRIMARY KEY (Application_number),
FOREIGN KEY  (Admin_staff_ID) REFERENCES ADMINISTRATOR(Staff_ID) on delete cascade,
FOREIGN KEY (ID_number) REFERENCES USER(ID_number) on delete cascade
);

CREATE TABLE HOUSING_PREFERENCE (
Application_number      INT     NOT NULL,
Building_preference     TINYINT,
Housing_type_preference VARCHAR(50),
Bedroom_preference	TINYINT,
Order_of_preference     TINYINT,
Roommate_preference     VARCHAR(150),

FOREIGN KEY (Application_number) REFERENCES APPLICATION(Application_number) on delete cascade
);

CREATE TABLE MAINTENANCE_REQUEST (
Request_number          INT           NOT NULL,
Resident_ID             VARCHAR(9)    NOT NULL,
Admin_staff_ID          VARCHAR(9)    NOT NULL,
Issue_desc              VARCHAR(150),
Submission_date         DATE,
Date_completed          DATE,
Status                  VARCHAR(150),
Comments                TEXT,

PRIMARY KEY (Request_number),
FOREIGN KEY (Resident_ID) REFERENCES RESIDENT(ID_number) on delete cascade,
FOREIGN KEY (Admin_staff_ID) REFERENCES ADMINISTRATOR(Staff_ID) on delete cascade

);
