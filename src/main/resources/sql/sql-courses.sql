CREATE TABLE IF NOT EXISTS `courses` (
    `course_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(100) NOT NULL,
    `fees` varchar(10) NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `created_by` varchar(50) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `person_courses` (
    `person_id` int NOT NULL,
    `course_id` int NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person(person_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id),
    PRIMARY KEY (`person_id`, `course_id`)

);