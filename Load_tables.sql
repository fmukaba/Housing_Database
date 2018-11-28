LOAD DATA LOCAL INFILE "/Users/simone/Downloads/Housing-Database/Housing_Database - User.csv" 
INTO TABLE USER
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
;

LOAD DATA LOCAL INFILE "/Users/simone/Downloads/Housing-Database/Housing_Database - Admin.csv" 
INTO TABLE ADMINISTRATOR
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
;

LOAD DATA LOCAL INFILE "/Users/simone/Downloads/Housing-Database/Housing_Database - Housing_unit.csv" 
INTO TABLE HOUSING_UNIT
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
;

LOAD DATA LOCAL INFILE "/Users/simone/Downloads/Housing-Database/Housing_Database - Resident.csv" 
INTO TABLE RESIDENT
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
;

LOAD DATA LOCAL INFILE "//Users/simone/Downloads/Housing-Database/Housing_Database - Applicant.csv" 
INTO TABLE APPLICANT
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
;

LOAD DATA LOCAL INFILE "/Users/simone/Downloads/Housing-Database/Housing_Database - Housing_preference.csv" 
INTO TABLE HOUSING_PREFERENCE
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
;

LOAD DATA LOCAL INFILE "/Users/simone/Downloads/Housing-Database/Housing_Database - Maintenance_request.csv" 
INTO TABLE MAINTENANCE_REQUEST
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\r\n'
IGNORE 1 LINES;
;
