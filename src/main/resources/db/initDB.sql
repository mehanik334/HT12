CREATE TABLE accounts (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    status VARCHAR(25) NOT NULL
);

CREATE TABLE skills(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25) UNIQUE

);

CREATE TABLE developers(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(25) NOT NULL ,
    lastName VARCHAR(25) NOT NULL ,
    accounts_id INT,
    FOREIGN KEY (accounts_id) REFERENCES accounts(id)
);

CREATE TABLE developer_skills(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    developers_id INT NOT NULL,
    skills_id INT NOT NULL,
    FOREIGN KEY (developers_id) REFERENCES developers(id),
    FOREIGN KEY (skills_id) REFERENCES skills(id)
)

