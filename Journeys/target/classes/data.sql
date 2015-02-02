-- ----------------------------
-- Table structure for day
-- ----------------------------
DROP TABLE day IF EXISTS;
CREATE TABLE day (
  ID int NOT NULL,
  CONTENT varchar(10000) DEFAULT NULL,
  DATE datetime DEFAULT NULL,
  LATITUDE double DEFAULT NULL,
  LONGITUDE double DEFAULT NULL,
  PICTURE_URL varchar(255) DEFAULT NULL,
  TITLE varchar(255) DEFAULT NULL,
  JOURNEY_ID int DEFAULT NULL,
  ENABLED double DEFAULT NULL,
  PRIMARY KEY (ID)
);

-- ----------------------------
-- Records of day
-- ---------------------------
INSERT INTO day VALUES (26, null, '2015-01-23 00:00:00', 0, 0, null, null, 4, 0);
INSERT INTO day VALUES (27, null, '2015-01-24 00:00:00', 0, 0, null, null, 4, 0);
INSERT INTO day VALUES (28, null, '2015-01-25 00:00:00', 0, 0, null, null, 4, 0);
INSERT INTO day VALUES (29, null, '2015-01-26 00:00:00', 0, 0, null, null, 4, 0);
INSERT INTO day VALUES (30, null, '2015-01-27 00:00:00', 0, 0, null, null, 4, 0);
INSERT INTO day VALUES (31, null, '2015-01-28 00:00:00', 0, 0, null, null, 4, 0);
INSERT INTO day VALUES (32, null, '2015-01-29 00:00:00', 0, 0, null, null, 4, 0);
INSERT INTO day VALUES (25, null, '2015-01-30 00:00:00', 0, 0, null, null, 4, 0);
INSERT INTO day VALUES (33, null, '2015-06-20 00:00:00', 45.5016889, -73.56725599999999, 'http://www.veryworldtrip.com/wp-content/uploads/2014/12/sinstaller-au-quebec.jpg', 'Arrivée à Montréal', 5, 1);
INSERT INTO day VALUES (34, null, '2015-06-21 00:00:00', 44.6994873, -73.4529124, 'http://flyplattsburgh.com/data/images/gallery/Plattsburgh%20International%20Airport%20Terminal%201.jpg', 'Voiture à Plattsburgh', 5, 1);
INSERT INTO day VALUES (35, null, '2015-06-22 00:00:00', 46.8032826, -71.242796, 'http://pvtistes.net/wp-content/uploads/2012/11/quebec-ville1-800x535.jpg', 'En route vers Québec', 5, 1);
INSERT INTO day VALUES (36, null, '2015-06-23 00:00:00', 48.14597759999999, -69.71283950000003, 'http://ssdc-country.net/Bals_Sorties/Carnet%20de%20voyage/photos%20carnet%20de%20voyage/014%20Chute-montmorency.jpg', 'En route vers Tadoussac', 5, 1);
INSERT INTO day VALUES (37, null, '2015-06-24 00:00:00', 48.423604, -71.23954630000003, 'http://www.bonjourquebec.com/fileadmin/Image/decouvrez/activites/routes_circuits/route_fjord/tq_000186_g.jpg', 'Baleines et fjord', 5, 1);
INSERT INTO day VALUES (38, null, '2015-06-25 00:00:00', 46.8032826, -71.242796, 'http://pvtistes.net/wp-content/uploads/2012/11/quebec-ville1-800x535.jpg', 'Retour à Québec', 5, 1);
INSERT INTO day VALUES (39, null, '2015-06-26 00:00:00', 46.8032826, -71.242796, 'http://pvtistes.net/wp-content/uploads/2012/11/quebec-ville1-800x535.jpg', 'Visite de Québec', 5, 1);
INSERT INTO day VALUES (40, null, '2015-06-27 00:00:00', 45.5016889, -73.56725599999999, 'http://www.veryworldtrip.com/wp-content/uploads/2014/12/sinstaller-au-quebec.jpg', 'Retour à Montréal', 5, 1);
INSERT INTO day VALUES (41, null, '2015-06-28 00:00:00', 45.5016889, -73.56725599999999, 'http://www.veryworldtrip.com/wp-content/uploads/2014/12/sinstaller-au-quebec.jpg', 'Visite de Montréal', 5, 1);
INSERT INTO day VALUES (42, null, '2015-06-29 00:00:00', 45.4215296, -75.69719309999999, 'http://media-cdn.tripadvisor.com/media/photo-s/01/0c/cb/ef/ottawa.jpg', 'En route vers Ottawa', 5, 1);
INSERT INTO day VALUES (43, null, '2015-06-30 00:00:00', 44.2311717, -76.48595439999997, 'http://mw2.google.com/mw-panoramio/photos/medium/11716810.jpg', 'Mille îles', 5, 1);
INSERT INTO day VALUES (44, null, '2015-07-01 00:00:00', 43.653226, -79.38318429999998, 'http://knightsinntoronto-com.factorepreview.ca/system/images/images/2/original/toronto-012222.jpg?1358279327', 'Toronto', 5, 1);
INSERT INTO day VALUES (45, null, '2015-07-02 00:00:00', 43.08, -79.07100000000003, 'http://moonliteniagarafalls.com/photos/falls1.jpg', 'Chutes du Niagara', 5, 1);
INSERT INTO day VALUES (46, null, '2015-07-03 00:00:00', 38.9071923, -77.03687070000001, 'http://washington.org/sites/default/files/styles/guide_to_left/public/July4th-credit-WETA_3.jpg?itok=qtgNQ4Tc', 'En route vers Washington', 5, 1);
INSERT INTO day VALUES (47, null, '2015-07-04 00:00:00', 38.9071923, -77.03687070000001, 'http://washington.org/sites/default/files/styles/guide_to_left/public/July4th-credit-WETA_3.jpg?itok=qtgNQ4Tc', 'Independance Day à Washington', 5, 1);
INSERT INTO day VALUES (48, null, '2015-07-05 00:00:00', 39.9525839, -75.16522150000003, 'http://www.informationplanet.be/files/images/sejour_linguistique_philadelphie.jpg', 'En route vers Philadelphie', 5, 1);
INSERT INTO day VALUES (49, null, '2015-07-06 00:00:00', 40.7127837, -74.00594130000002, 'http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg', 'En route vers New York', 5, 1);
INSERT INTO day VALUES (50, null, '2015-07-07 00:00:00', 40.7127837, -74.00594130000002, 'http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg', 'New York', 5, 1);
INSERT INTO day VALUES (51, null, '2015-07-08 00:00:00', 40.7127837, -74.00594130000002, 'http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg', 'New York', 5, 1);
INSERT INTO day VALUES (52, null, '2015-07-09 00:00:00', 40.7127837, -74.00594130000002, 'http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg', 'New York', 5, 1);
INSERT INTO day VALUES (53, null, '2015-07-10 00:00:00', 40.7127837, -74.00594130000002, 'http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg', 'New York', 5, 1);
INSERT INTO day VALUES (54, null, '2015-07-11 00:00:00', 40.7127837, -74.00594130000002, 'http://www.sejoursvoyages.com/images/circuits/circuit43655_150168.jpg', 'New York', 5, 1);
INSERT INTO day VALUES (55, null, '2015-08-20 00:00:00', 0, 0, 'http://jre2014.fr/wp-content/uploads/2014/09/lasVegasBig.jpg', 'Las Vegas', 6, 1);
INSERT INTO day VALUES (56, null, '2015-08-21 00:00:00', 0, 0, 'http://jre2014.fr/wp-content/uploads/2014/09/lasVegasBig.jpg', 'Las Vegas', 6, 1);
INSERT INTO day VALUES (57, null, '2015-08-22 00:00:00', 36.1699412, -115.13982959999998, 'http://jre2014.fr/wp-content/uploads/2014/09/lasVegasBig.jpg', 'Las Vegas', 6, 1);

-- ----------------------------
-- Table structure for journey
-- ----------------------------
DROP TABLE journey IF EXISTS;
CREATE TABLE journey (
  ID int NOT NULL ,
  DISPLAY_WEEK_END bit DEFAULT NULL,
  END_DATE datetime DEFAULT NULL,
  PASSWORD varchar(255) DEFAULT NULL,
  PICTURE_URL varchar(255) DEFAULT NULL,
  START_DATE datetime DEFAULT NULL,
  TITLE varchar(255) DEFAULT NULL,
  USER_ID int DEFAULT NULL,
  PRIMARY KEY (ID)
);

-- ----------------------------
-- Records of journey
-- ----------------------------
INSERT INTO journey VALUES (4, 1, '2015-01-30 00:00:00', '', 'http://www.portesdusoleil.com/images/upload/diaporama/503_297/image_03_morzine_avoriaz_126.jpg', '2015-01-23 00:00:00', 'Ski dans les Alpes', 2);
INSERT INTO journey VALUES (5, 1, '2015-07-11 00:00:00', '$2a$10$PBX6/aRYc4TOg/vXHiUT2uYGCgbfSreHCtfSLh4HsDmKIXInk0oia', 'http://www.veryworldtrip.com/wp-content/uploads/2014/12/sinstaller-au-quebec.jpg', '2015-06-20 00:00:00', 'Nord Est américain', 1);
INSERT INTO journey VALUES (6, 1, '2015-08-22 00:00:00', '', 'http://www.las-vegas.travel/images/paris-hotel-las-vegas.jpg', '2015-08-20 00:00:00', 'Viva Las Vegas', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE role IF EXISTS;
CREATE TABLE role (
  ID int NOT NULL ,
  NAME varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID)
);

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO role VALUES (1, 'ROLE_ADMIN');
INSERT INTO role VALUES (2, 'ROLE_USER');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE user_role IF EXISTS;
CREATE TABLE user_role (
  USER_ID int NOT NULL,
  ROLE_ID int NOT NULL,
  PRIMARY KEY (USER_ID,ROLE_ID)
);

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO user_role VALUES (1, 1);
INSERT INTO user_role VALUES (2, 2);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE users IF EXISTS;
CREATE TABLE users (
  ID int NOT NULL ,
  EMAIL varchar(255) DEFAULT NULL,
  ENABLED bit DEFAULT NULL,
  FIRST_NAME varchar(255) DEFAULT NULL,
  LAST_NAME varchar(255) DEFAULT NULL,
  PASSWORD varchar(255) DEFAULT NULL,
  PRIMARY KEY (ID)
);

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO users VALUES (1, 'jeremie.ardiot@gmail.com', 1, 'Jérémie', 'Ardiot', '$2a$10$Y0VAfiPYUoNWyDYYDY6yuOM97O.GRN4Am9hoOaFsQtNkrag82muXG');
INSERT INTO users VALUES (2, 'j.ardiot@gmail.com', 1, 'Jonathan', 'Ardiot', '$2a$10$CV1AZ8ALToJszNfBpwrpwenJfzdxh6SXIW/yZn43JJOfJBUhgYLWG');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE comment IF EXISTS;
CREATE TABLE comment (
  ID int NOT NULL ,
  DATE datetime DEFAULT NULL,
  CONTENT varchar(5000) DEFAULT NULL,
  JOURNEY_ID int DEFAULT NULL,
  DAY_ID int DEFAULT NULL,
  USER_ID int DEFAULT NULL,
  PRIMARY KEY (ID)
);

DROP TABLE category_geo IF EXISTS;
CREATE TABLE category_geo (
  ID int NOT NULL ,
  NAME_EN varchar(200) DEFAULT NULL,
  NAME_FR varchar(200) DEFAULT NULL,
  CONTINENT_NAME_EN varchar(100) DEFAULT NULL,
  CONTINENT_NAME_FR varchar(100) DEFAULT NULL,
  TYPE int DEFAULT NULL,
  JOURNEY_ID int DEFAULT NULL,
  PRIMARY KEY (ID)
);


-- WORLD
INSERT INTO CATEGORY_GEO VALUES(1000,'Trip around the world','Tour du monde','','',4,null);

-- CONTINENT
INSERT INTO CATEGORY_GEO VALUES(1001,'Trip in North America','Circuit en Amérique du Nord','North America','Amérique du Nord',3,null);
INSERT INTO CATEGORY_GEO VALUES(1002,'Trip in South America','Circuit en Amérique du Sud','South America','Amérique du Sud',3,null);
INSERT INTO CATEGORY_GEO VALUES(1003,'Trip in Europe','Circuit en Europe','Europe','Europe',3,null);
INSERT INTO CATEGORY_GEO VALUES(1004,'Trip in Asia','Circuit en Asie','Asia','Asie',3,null);
INSERT INTO CATEGORY_GEO VALUES(1005,'Trip in Africa','Circuit en Afrique','Africa','Afrique',3,null);
INSERT INTO CATEGORY_GEO VALUES(1006,'Trip in Oceania','Circuit en Océanie','Oceania','Océanie',3,null);
INSERT INTO CATEGORY_GEO VALUES(1007,'Trip in Antarctica','Circuit en Antartique','Antarctica','Antarctique',3,null);

-- AREA
INSERT INTO CATEGORY_GEO VALUES(2000,'Sahara','Sahara','Africa','Afrique',2,null);
INSERT INTO CATEGORY_GEO VALUES(2001,'Patagonia','Patagonie','South America','Amérique du Sud',2,null);

-- COUNTRIES AND ISLES
INSERT INTO CATEGORY_GEO VALUES(2,'Antigua and Barbuda','Antigua-et-Barbuda','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(3,'Anguilla','Anguilla', 'North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(4,'Albania','Albanie', 'Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(5,'Armenia','Arménie','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(6,'Angola','Angola', 'Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(8,'Argentina','Argentine','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(9,'American Samoa', 'Samoa américaines','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(10,'Austria','Autriche','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(11,'Australia','Australie','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(12,'Aruba','Aruba','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(14,'Azerbaijan','Azerbaïdjan','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(15,'Bosnia and Herzegovina','Bosnie-Herzégovine','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(16,'Barbados','Barbade','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(17,'Bangladesh','Bangladesh','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(18,'Belgium','Belgique','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(19,'Burkina Faso','Burkina Faso','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(20,'Bulgaria','Bulgarie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(21,'Bahrain','Bahreïn','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(22,'Burundi','Burundi','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(23,'Benin','Bénin','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(24,'Saint Barthélemy','Saint Barthélemy','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(25,'Bermuda','Bermudes','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(26,'Brunei','Brunei','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(27,'Bolivia','Bolivie','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(28,'Bonaire','Bonaire','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(29,'Brazil','Brésil','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(30,'Bahamas','Bahamas','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(31,'Bhutan','Bhoutan','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(32,'Bouvet Island','Île Bouvet','Antarctica','Antarctique',1,null);
INSERT INTO CATEGORY_GEO VALUES(33,'Botswana','Botswana','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(34,'Belarus','Biélorussie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(35,'Belize','Belize','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(36,'Canada','Canada','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(37,'Cocos Islands','Îles Cocos','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(38,'Democratic Republic of the Congo','République démocratique du Congo','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(39,'Central African Republic','République centrafricaine','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(40,'Republic of the Congo','République du Congo','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(41,'Switzerland','Suisse','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(42,'Ivory Coast','Côte d''Ivoire','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(43,'Cook Islands','Îles Cook','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(44,'Chile','Chili','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(45,'Cameroon','Cameroun','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(46,'China','Chine','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(47,'Colombia','Colombie','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(48,'Costa Rica','Costa Rica','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(49,'Cuba','Cuba','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(50,'Cape Verde','Cap Vert','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(51,'Curacao','Curaçao','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(52,'Christmas Island','Île Christmas','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(53,'Cyprus','Chypre','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(54,'Czech Republic','République tchèque','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(55,'Germany','Allemagne','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(56,'Djibouti','Djibouti','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(57,'Denmark','Danemark','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(58,'Dominica','Dominique','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(59,'Dominican Republic','République dominicaine','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(60,'Algeria','Algérie','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(61,'Ecuador','Equateur','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(62,'Estonia','Estonie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(63,'Egypt','Egypte','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(64,'Western Sahara','Sahara occidental','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(65,'Eritrea','Érythrée','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(66,'Spain','Espagne','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(67,'Ethiopia','Ethiopie','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(68,'Finland','Finlande','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(69,'Fiji','Fidji','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(70,'Falkland Islands','Îles Malouines','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(71,'Micronesia','Micronésie','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(72,'Faroe Islands','Îles Féroé','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(73,'France','France','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(74,'Gabon','Gabon','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(75,'United Kingdom','Royaume-Uni','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(76,'Grenada','Grenade','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(77,'Georgia','Géorgie','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(78,'French Guiana','Guyane française','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(79,'Guernsey','Guernesey','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(80,'Ghana','Ghana','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(81,'Gibraltar','Gibraltar','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(82,'Greenland','Groenland','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(83,'Gambia','Gambie','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(84,'Guinea','Guinée','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(85,'Guadeloupe','Guadeloupe','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(86,'Equatorial Guinea','Guinée équatoriale','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(87,'Greece','Grèce','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(88,'South Georgia and the South Sandwich Islands','Géorgie du Sud-et-les Îles Sandwich du Sud','Antarctica','Antarctique',1,null);
INSERT INTO CATEGORY_GEO VALUES(89,'Guatemala','Guatemala','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(90,'Guam','Guam','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(91,'Guinea-Bissau','Guinée-Bissau','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(92,'Guyana','Guyane','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(93,'Hong Kong','Hong Kong','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(94,'Heard Island and McDonald Islands','Îles Heard-et-MacDonald','Antarctica','Antarctique',1,null);
INSERT INTO CATEGORY_GEO VALUES(95,'Honduras','Honduras','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(96,'Croatia','Croatie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(97,'Haiti','Haïti','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(98,'Hungary','Hongrie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(99,'Indonesia','Indonésie','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(100,'Ireland','Irlande','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(101,'Israel','Israël','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(102,'Isle of Man','Île de Man','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(103,'India','Inde','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(104,'British Indian Ocean Territory','Territoire britannique de l''océan Indien','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(105,'Iraq','Iraq','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(106,'Iran','Iran','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(107,'Iceland','Islande','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(108,'Italy','Italie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(109,'Jersey','Jersey','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(110,'Jamaica','Jamaïque','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(111,'Jordan','Jordanie','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(112,'Japan','Japon','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(113,'Kenya','Kenya','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(114,'Kyrgyzstan','Kirghizistan','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(115,'Cambodia','Cambodge','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(116,'Kiribati','Kiribati','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(117,'Comoros','Comores','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(118,'Saint Kitts and Nevis','Saint-Christophe-et-Niévès','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(119,'North Korea','Corée du Nord','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(120,'South Korea','Corée du Sud','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(121,'Kuwait','Koweït','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(122,'Cayman Islands','Îles Caïmans','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(123,'Kazakhstan','Kazakhstan','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(124,'Laos','Laos','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(125,'Lebanon','liban','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(126,'Saint Lucia','Sainte-Lucie','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(127,'Liechtenstein','Liechtenstein','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(128,'Sri Lanka','Sri Lanka','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(129,'Liberia','Liberia','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(130,'Lesotho','Lesotho','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(131,'Lithuania','Lituanie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(132,'Luxembourg','Luxembourg','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(133,'Latvia','Lettonie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(134,'Libya','Libye','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(135,'Morocco','Maroc','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(136,'Monaco','Monaco','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(137,'Moldova','Moldavie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(138,'Montenegro','Montenegro','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(139,'Saint Martin','Saint-Martin','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(140,'Madagascar','Madagascar','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(141,'Marshall Islands','Îles Marshall','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(142,'Macedonia','Macédoine','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(143,'Mali','Mali','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(144,'Myanmar [Burma]','Birmanie','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(145,'Mongolia','Mongolie','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(146,'Macao','Macao','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(147,'Northern Mariana Islands','Îles Mariannes du Nord','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(148,'Martinique','Martinique','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(149,'Mauritania','Mauritanie','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(150,'Montserrat','Montserrat','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(151,'Malta','Malte','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(152,'Mauritius','Maurice','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(153,'Maldives','Maldives','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(154,'Malawi','Malawi','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(155,'Mexico','Mexique','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(156,'Malaysia','Malaisie','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(157,'Mozambique','Mozambique','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(158,'Namibia','Namibie','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(159,'New Caledonia','Nouvelle-Calédonie','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(160,'Niger','Niger','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(161,'Norfolk Island','Île Norfolk','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(162,'Nigeria','Nigeria','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(163,'Nicaragua','Nicaragua','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(164,'Netherlands','Pays-Bas','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(165,'Norway','Norvège','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(166,'Nepal','Népal','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(167,'Nauru','Nauru','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(168,'Niue','Niue','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(169,'New Zealand','Nouvelle-Zélande','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(170,'Oman','Oman','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(171,'Panama','Panama','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(172,'Peru','Pérou','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(173,'French Polynesia','Polynésie française','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(174,'Papua New Guinea','Papouasie-Nouvelle-Guinée','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(175,'Philippines','Philippines','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(176,'Pakistan','Pakistan','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(177,'Poland','Pologne','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(178,'Saint Pierre and Miquelon','Saint Pierre et Miquelon','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(179,'Pitcairn Islands','Îles Pitcairn','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(180,'Puerto Rico','Porto Rico','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(181,'Palestine','Palestine','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(182,'Portugal','Portugal','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(183,'Palau','Palaos','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(184,'Paraguay','Paraguay','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(185,'Qatar','Qatar','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(186,'Réunion','Réunion','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(187,'Romania','Roumanie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(188,'Serbia','Serbie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(189,'Russia','Russie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(190,'Rwanda','Rwanda','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(191,'Saudi Arabia','Arabie Saoudite','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(192,'Solomon Islands','Salomon','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(193,'Seychelles','Seychelles','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(194,'Sudan','Soudan','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(195,'Sweden','Suède','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(196,'Singapore','Singapour','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(197,'Saint Helena','Sainte-Hélène','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(198,'Slovenia','Slovénie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(200,'Slovakia','Slovaquie','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(201,'Sierra Leone','Sierra Leone','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(202,'San Marino','Saint-Marin','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(203,'Senegal','Senegal','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(204,'Somalia','Somalie','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(205,'Suriname','Suriname','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(206,'South Sudan','Soudan du Sud','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(207,'São Tomé and Príncipe','Sao Tomé-et-Principe','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(208,'El Salvador','Salvador','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(209,'Sint Maarten','Saint-Martin (Pays-Bas)','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(210,'Syria','Syrie','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(211,'Swaziland','Swaziland','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(212,'Turks and Caicos Islands','Îles Turques-et-Caïques','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(213,'Chad','Tchad','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(214,'French Southern Territories','Terres australes et antarctiques françaises','Antarctica','Antarctique',1,null);
INSERT INTO CATEGORY_GEO VALUES(215,'Togo','Togo','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(216,'Thailand','Thaïlande','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(217,'Tajikistan','Tadjikistan','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(218,'Tokelau','Tokelau','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(219,'East Timor','Timor oriental','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(220,'Turkmenistan','Turkménistan','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(221,'Tunisia','Tunisie','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(222,'Tonga','Tonga','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(223,'Turkey','Turquie','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(224,'Trinidad and Tobago','Trinité-et-Tobago','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(225,'Tuvalu','Tuvalu','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(226,'Taiwan','Taïwan','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(227,'Tanzania','Tanzanie','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(228,'Ukraine','Ukraine','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(229,'Uganda','Ouganda','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(230,'U.S. Minor Outlying Islands','Îles mineures éloignées des États-Unis','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(231,'United States','Etats-Unis','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(232,'Uruguay','Uruguay','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(233,'Uzbekistan','Ouzbékistan','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(234,'Vatican City','Vatican','Europe','Europe',1,null);
INSERT INTO CATEGORY_GEO VALUES(235,'Saint Vincent and the Grenadines','Saint-Vincent-et-les-Grenadines','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(236,'Venezuela','Venezuela','South America','Amérique du Sud',1,null);
INSERT INTO CATEGORY_GEO VALUES(237,'British Virgin Islands','Îles Vierges britanniques','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(238,'U.S. Virgin Islands','Îles Vierges des États-Unis','North America','Amérique du Nord',1,null);
INSERT INTO CATEGORY_GEO VALUES(239,'Vietnam','Vietnam','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(240,'Vanuatu','Vanuatu','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(241,'Wallis and Futuna','Wallis et Futuna','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(242,'Samoa','Samoa','Oceania','Océanie',1,null);
INSERT INTO CATEGORY_GEO VALUES(244,'Yemen','Yémen','Asia','Asie',1,null);
INSERT INTO CATEGORY_GEO VALUES(245,'Mayotte','Mayotte','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(246,'South Africa','Afrique du Sud','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(247,'Zambia','Zambie','Africa','Afrique',1,null);
INSERT INTO CATEGORY_GEO VALUES(248,'Zimbabwe','Zimbabwe','Africa','Afrique',1,null);

-- CATEGORY TRIP

DROP TABLE category_trip IF EXISTS;
CREATE TABLE category_trip (
  ID int NOT NULL ,
  NAME_EN varchar(200) DEFAULT NULL,
  NAME_FR varchar(200) DEFAULT NULL,
  JOURNEY_ID int DEFAULT NULL,
  PRIMARY KEY (ID)
);

INSERT INTO CATEGORY_TRIP VALUES(1,'Stay','Séjour',null);
INSERT INTO CATEGORY_TRIP VALUES(2,'Trip','Circuit',null);
INSERT INTO CATEGORY_TRIP VALUES(3,'Trek','Randonnée',null);
INSERT INTO CATEGORY_TRIP VALUES(5,'Sports','Sports',null);
INSERT INTO CATEGORY_TRIP VALUES(6,'Winter sports','Sports d''hiver',null);
INSERT INTO CATEGORY_TRIP VALUES(7,'Working','Travail',null);
