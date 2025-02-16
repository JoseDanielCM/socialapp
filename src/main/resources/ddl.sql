INSERT INTO follows(user_id,followed_user_id) VALUES(1,2);
INSERT INTO follows(user_id,followed_user_id) VALUES(4,2);

INSERT INTO likes(user_id,post_id) VALUES(2 ,1);

INSERT into comments(user_id,post_id,content) VALUES(2,1,'lloro 😢');


INSERT INTO users(bio,birthdate,email,first_name,last_name,password,phone,profile_picture,role,username)
VALUES ('coding...','2007-02-24','jodacarmon@gmail.com','Jose Daniel','Carvajal Montañez','$2a$10$6MZP16mpRCu1CPHOZojwMuWjbadqCX6g.SQ.aNmfh7Vwz5xb74dl.','No phone number avaliable','','USER','danycar');