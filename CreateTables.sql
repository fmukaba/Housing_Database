CREATE SCHEMA HOUSING;
USE HOUSING;

CREATE TABLE USER (

ID_number           CHAR(9)            NOT NULL,
Username            VARCHAR(50),
Password	          VARCHAR(50),
Name	              VARCHAR(50),
Gender              CHAR(1)   		   NOT NULL        CHECK (Gender IN('F', 'M')),
Student_status      BOOLEAN,   
Marital_status      BOOLEAN,
Address             VARCHAR(100),
Phone_number        VARCHAR(50),    
College             VARCHAR(50),
Department          VARCHAR(50),
Major               VARCHAR(50),
Family_head_ID      CHAR(9),
                                                                      
PRIMARY KEY (ID_number)
);

ALTER TABLE USER ADD FOREIGN KEY(Family_head_ID) references USER(ID_number) on delete cascade;

CREATE TABLE ADMINISTRATOR (

Staff_ID              CHAR(9)            NOT NULL,
SSN                   CHAR(9),
Admin_Name            VARCHAR(50),
Gender                CHAR(1)            CHECK (Gender IN('F', 'M')),
Phone_number          VARCHAR(50),
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
Price_quarter		    INT,

PRIMARY KEY (Building_number,Apt_number)
);

CREATE TABLE RESIDENT (

ID_number               CHAR(9)         NOT NULL,
Admin_staff_ID          CHAR(9)         NOT NULL,
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

ID_number		CHAR(9)         NOT NULL,
Acceptance_status      		    BOOLEAN,

PRIMARY KEY (ID_number),
FOREIGN KEY (ID_number) REFERENCES USER (ID_number) on delete cascade,
FOREIGN KEY (Staff_ID) REFERENCES ADMINISTRATOR (Staff_ID) on delete cascade
);

CREATE TABLE HOUSING_PREFERENCE (
  
ID_number               CHAR(9)     NOT NULL,
Building_preference     TINYINT,
Housing_type_preference VARCHAR(50),
Bedroom_preference	    TINYINT,
Order_of_preference     TINYINT,
Roommate_preference     VARCHAR(150),

FOREIGN KEY (ID_number) REFERENCES APPLICANT(ID_number) on delete cascade
);

CREATE TABLE MAINTENANCE_REQUEST (
  
Request_number          INT          	NOT NULL,
Resident_ID             CHAR(9)   		NOT NULL,
Admin_staff_ID          CHAR(9)   	    NOT NULL,
Issue_desc              VARCHAR(150),
Submission_date         VARCHAR(15),
Date_completed          VARCHAR(15),
Status_request          VARCHAR(10) 	NOT NULL CHECK(Status IN("pending", "Completed")),
Comments                TEXT,

PRIMARY KEY (Request_number),
FOREIGN KEY (Resident_ID) REFERENCES RESIDENT(ID_number) on delete cascade,
FOREIGN KEY (Admin_staff_ID) REFERENCES ADMINISTRATOR(Staff_ID) on delete cascade
);

