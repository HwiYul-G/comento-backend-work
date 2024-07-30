CREATE TABLE statistic9.requestInfo (
    requestID numeric NOT NULL PRIMARY KEY,
    requestCode varchar(5) NOT NULL,
    userID varchar(5),
    createDate varchar(10)
);

CREATE TABLE statistic9.requestCode(
    requestCode varchar(5) NOT NULL PRIMARY KEY,
    code_explain varchar(5) NOT NULL
);

CREATE TABLE statistic9.user(
    userID varchar(5) NOT NULL PRIMARY KEY,
    HR_ORGAN varchar(5) NOT NULL,
    USERNAME varchar(5) NOT NULL
);

ALTER TABLE statistic9.requestInfo
    ADD FOREIGN KEY(requestCode) REFERENCES statistic9.requestCode(requestCode);
ALTER TABLE statistic9.requestInfo
    ADD FOREIGN KEY(userID) REFERENCES statistic9.user(userID);

CREATE TABLE statistic9.holiday(
   localDate varchar(10) NOT NULL PRIMARY KEY,
   description varchar(15)
);