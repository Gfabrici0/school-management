CREATE TABLE student (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    registration VARCHAR(255) NOT NULL
);

CREATE TABLE student_course (
    id UUID PRIMARY KEY,
    id_course UUID NOT NULL,
    id_student UUID NOT NULL
);

CREATE TABLE course (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255)
);
