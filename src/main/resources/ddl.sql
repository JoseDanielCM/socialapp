INSERT INTO follows(user_id,followed_user_id) VALUES(1,2);
INSERT INTO follows(user_id,followed_user_id) VALUES(4,2);

INSERT INTO likes(user_id,post_id) VALUES(3 ,1);
INSERT into posts(content,title,user_id,created_at)
VALUES ('contentOld','titleee',5,'2021-02-24 00:00:00');
INSERT into comments(user_id,post_id,content) VALUES(2,1,'lloro 😢');

insert into notifications(notification_type,is_read,reference_id,user_id) VALUES('COMMENT',false,1,2);
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));

SELECT currval('users_id_seq');

INSERT INTO users(bio,birthdate,email,first_name,last_name,password,phone,profile_picture,role,username)
VALUES ('coding...','2007-02-24','jodacarmon@gmail.com','Jose Daniel','Carvajal Montañez','$2a$10$6MZP16mpRCu1CPHOZojwMuWjbadqCX6g.SQ.aNmfh7Vwz5xb74dl.','No phone number avaliable','','USER','danycar');

-- ------------------------------------------ TRIGGERS ------------------------------------------
CREATE OR REPLACE FUNCTION create_follow_notification()
RETURNS TRIGGER AS $$
BEGIN
  INSERT INTO notifications (created_at, is_read, notification_type, reference_id, user_id)
  VALUES (NOW(), false, 'FOLLOW', NEW.id, NEW.followed_user_id);
  
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_follow_notification
AFTER INSERT ON follows
FOR EACH ROW
EXECUTE FUNCTION create_follow_notification();

-- ---------------------------
CREATE OR REPLACE FUNCTION create_like_notification()
RETURNS TRIGGER AS $$
DECLARE 
  post_author_id INT;
BEGIN
  -- Obtener el autor del post
  SELECT user_id INTO post_author_id 
  FROM posts 
  WHERE id = NEW.post_id;

  -- Insertar la notificación solo si el autor del post no es el mismo que da like
  IF post_author_id IS NOT NULL AND post_author_id != NEW.user_id THEN
    INSERT INTO notifications (created_at, is_read, notification_type, reference_id, user_id)
    VALUES (NOW(), false, 'LIKE', NEW.id, post_author_id);
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;




CREATE TRIGGER trigger_like_notification
AFTER INSERT ON likes
FOR EACH ROW
EXECUTE FUNCTION create_like_notification();

-- -------------------------------------------------------------------

CREATE OR REPLACE FUNCTION create_comment_notification()
RETURNS TRIGGER AS $$
DECLARE 
  post_author_id INT;
BEGIN
  -- Obtener el autor del post
  SELECT user_id INTO post_author_id 
  FROM posts 
  WHERE id = NEW.post_id;

  -- Insertar la notificación solo si el autor del post no es el mismo que comenta
  IF post_author_id IS NOT NULL AND post_author_id != NEW.user_id THEN
    INSERT INTO notifications (created_at, is_read, notification_type, reference_id, user_id)
    VALUES (NOW(), false, 'COMMENT', NEW.id, post_author_id);
  END IF;

  RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_comment_notification
AFTER INSERT ON comments
FOR EACH ROW
EXECUTE FUNCTION create_comment_notification();


