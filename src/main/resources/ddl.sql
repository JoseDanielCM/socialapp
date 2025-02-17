INSERT INTO follows(user_id,followed_user_id) VALUES(1,2);
INSERT INTO follows(user_id,followed_user_id) VALUES(4,2);

INSERT INTO likes(user_id,post_id) VALUES(3 ,4);
INSERT into posts(content,title,user_id,created_at)
VALUES ('contentOld','titleee',5,'2021-02-24 00:00:00');
INSERT into comments(user_id,post_id,content) VALUES(2,1,'lloro 😢');
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));

SELECT currval('users_id_seq');

INSERT INTO users(bio,birthdate,email,first_name,last_name,password,phone,profile_picture,role,username)
VALUES ('coding...','2007-02-24','jodacarmon@gmail.com','Jose Daniel','Carvajal Montañez','$2a$10$6MZP16mpRCu1CPHOZojwMuWjbadqCX6g.SQ.aNmfh7Vwz5xb74dl.','No phone number avaliable','','USER','danycar');