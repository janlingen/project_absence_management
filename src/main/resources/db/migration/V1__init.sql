CREATE TABLE IF NOT EXISTS STUDENT
(
    ID                       INT AUTO_INCREMENT,
    GITHUB_NAME              VARCHAR(30) NOT NULL,
    GITHUB_ID                INT NOT NULL UNIQUE,
    LEFTOVER_VACATION_TIME   INT NOT NULL,
    CONSTRAINT STUDENT_PK
        PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS EXAM
(
    ID                       INT AUTO_INCREMENT,
    NAME                     VARCHAR(50) NOT NULL,
    EXEMPTION_OFFSET         INT NOT NULL,
    DATE                     DATE NOT NULL,
    START                    TIME NOT NULL,
    END                      TIME NOT NULL,
    ONLINE                   BOOLEAN NOT NULL,
    CONSTRAINT EXAM_PK
        PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS VACATION
(
    ID                     INT AUTO_INCREMENT,
    STUDENT_ID             INT,
    DATE                   DATE NOT NULL,
    START                  TIME NOT NULL,
    END                    TIME NOT NULL,
    REASON                 VARCHAR(255) NULL,
    CONSTRAINT VACATION_PK
        PRIMARY KEY (ID, STUDENT_ID)
);

CREATE TABLE IF NOT EXISTS EXAM_STUDENT
(
    STUDENT_ID             INT NOT NULL,
    EXAM_ID                INT NOT NULL
);
