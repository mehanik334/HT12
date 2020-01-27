INSERT INTO accounts(status) VALUES('ACTIVE');
INSERT INTO accounts(status) VALUES ('BANNED');
INSERT INTO accounts(status) VALUES ('DELETED');

INSERT INTO skills(name) VALUES ('Java Core');
INSERT INTO skills(name) VALUES ('Spring framework');
INSERT INTO skills(name) VALUES ('Hibernate');
INSERT INTO skills(name) VALUES ('Java Collection');

INSERT INTO developers(firstName,lastName,accounts_id) VALUES ('Alex','Denisenko',1);
INSERT INTO developers(firstName,lastName,accounts_id) VALUES ('Ivan','Ivanov',1);
INSERT INTO developers(firstName,lastName,accounts_id) VALUES ('Petr','Petrov',2);
INSERT INTO developers(firstName,lastName,accounts_id) VALUES ('Semen','Sidorov',3);

INSERT INTO developer_skills(developers_id,skills_id) VALUES (1,1);
INSERT INTO developer_skills(developers_id,skills_id) VALUES (2,2);
INSERT INTO developer_skills(developers_id,skills_id) VALUES (3,3);
INSERT INTO developer_skills(developers_id,skills_id) VALUES (4,4);
INSERT INTO developer_skills(developers_id,skills_id) VALUES (1,4);