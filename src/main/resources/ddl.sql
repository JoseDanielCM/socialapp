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


-- ---------------------------------------------------------------
INSERT INTO public.users (bio,birthdate,email,first_name,last_name,"password",phone,profile_picture,registered_at,"role",username) VALUES
	 ('No bio available...','2002-02-02','jodacarmon@gmail.com','Jose Daniel','Carvajal','$2a$10$jsHOKWupC0sodyXBwewwAeuRehCysV1IHvHTvWYkW.zwWTVIfqxrG','No phone number avaliable','https://i.pinimg.com/736x/ca/24/48/ca2448be61e1d106a8706ed3e5f30dcb.jpg','2025-02-14 14:22:02.549531','USER','danycar'),
	 ('No bio available...','1800-02-20','johan@gmail.com','Johan Sebastian','Ruiz Angarita','$2a$10$LgB3eYjNAq6oKYauH1WJg.fBy6AOqfGPv4FRRxbCmilly8/kHXP1y','No phone number avaliable','https://cdna.artstation.com/p/assets/images/images/015/423/152/medium/dominik-koscinski-closeupfront.jpg?1548270414','2025-02-14 17:25:30.153284','USER','johan'),
	 ('No bio available...','2000-02-24','laura@gmail.com','laura','laura','$2a$10$xP2I60yGLEJPfRr5qGgADe.xed7/FKeJ/5Ly/g3XQSmfas1l6rSUa','No phone number avaliable','https://i.pinimg.com/736x/74/2c/70/742c70a8e0708cdb228748ca4921e301.jpg','2025-02-14 14:43:56.816528','USER','laura'),
	 ('In the howl house','2000-02-24','lilith@gmail.com','Lilith','Mairi','$2a$10$ojZuyVRysowqaLegVzMTzOSwZfeFCdQfDgDdo.Izgfm5YsPlWiHZq','No phone number avaliable','https://i.pinimg.com/736x/4f/9f/3b/4f9f3b2be7b1dbbc4d6ee270be65cd86.jpg','2025-02-18 16:25:40.965359','USER','Lilith'),
	 ('Gamer... 💚','2000-02-24','juan@gmail.com','Juan Jose','Torres Becerra','$2a$10$Tgn7EIYOJsgmxuoWuxNUoempmaiP4E7iayDd1w7OtLXe6FF4vId0O','3153416598','https://i.pinimg.com/236x/d0/81/1a/d0811ae6d7f05db9a219c252d277c193.jpg','2025-02-14 14:22:31.435032','USER','juan');

   INSERT INTO public.tags (name) VALUES
	 ('disney'),
	 ('series'),
	 ('animation'),
	 ('spiderman'),
	 ('multiverse'),
	 ('movies'),
	 ('dreamworks'),
	 ('movie'),
	 ('cinema'),
	 ('filmLovers');
INSERT INTO public.tags (name) VALUES
	 ('hobbies'),
	 ('VideoGames'),
	 ('gamer'),
	 ('platformer'),
	 ('RPG'),
	 ('turn-based-battle-system'),
	 ('Difficulty'),
	 ('ARPG');

   INSERT INTO public.posts ("content",created_at,img_url,title,updated_at,user_id) VALUES
	 ('an American animated fantasy television series ','2025-02-18 16:33:08.036527','https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/978AB840710181C646FBAB5CFA8FEBDA198FB889409984264B5072CA5C810E78/scale?width=1200&aspectRatio=1.78&format=webp','Welcome to the owl house',NULL,4),
	 ('a dimension-hopping story in which different versions of Spider-Man from different universes are forced to team up to battle a common threat.','2025-02-18 16:39:00.389109','https://images.cdn1.buscalibre.com/fit-in/360x360/64/29/642967099924cfc31a41277ac8270995.jpg','Spiderman into the spiderverse',NULL,4),
	 ('The film follows Roz (Nyong''o), a service robot shipwrecked on an uninhabited island who must adapt to its surroundings,','2025-02-18 16:42:20.994613','https://deadline.com/wp-content/uploads/2024/11/Roz-and-Brightbill-Painting.jpg','Wild robot',NULL,4),
	 ('How often do you guys watch movies?','2025-02-18 16:44:17.658833','','Cinephiles Assemble!',NULL,4),
	 ('An epic action adventure through a vast ruined kingdom of insects and heroes. Explore twisting caverns, battle tainted creatures and befriend bizarre bugs.','2025-02-18 16:57:06.135555','https://store-images.s-microsoft.com/image/apps.24270.13847644057609868.a4a91f76-8d1c-4e19-aa78-f4d27d2818fb.d96146d7-d00a-4db9-ad68-197b2f962a17?q=90&w=480&h=270','Hollow Knight',NULL,5),
	 ('Is a story-rich, party-based RPG set in the universe of Dungeons & Dragons, where your choices shape a tale of fellowship and betrayal.','2025-02-18 17:04:21.902408','https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/media/image/2023/08/opinion-baldurs-gate-3-pc-3101716.jpg?tf=3840x','Baldurs Gate 3',NULL,5),
	 ('Dark Souls is a dark fantasy action role-playing game series developed by FromSoftware and published by Bandai Namco Entertainment.','2025-02-18 17:06:32.110528','https://img.redbull.com/images/c_fill,g_auto,w_1200,h_630/f_auto,q_auto/redbullcom/2014/04/22/1331646783043_2/dark-souls','Dark Souls',NULL,5);

INSERT INTO public."comments" ("content",created_at,updated_at,user_id,post_id) VALUES
	 ('@johan @danycar @laura','2025-02-18 16:34:16.128838',NULL,5,1),
	 ('I usually watch one per week @juan and you','2025-02-18 16:45:57.651898',NULL,4,4),
	 ('If the movie were about ur life, every single day bitch','2025-02-18 16:51:42.485782',NULL,5,4);


