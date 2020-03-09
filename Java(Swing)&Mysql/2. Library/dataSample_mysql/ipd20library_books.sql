CREATE DATABASE  IF NOT EXISTS `ipd20library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ipd20library`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ipd20library
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(13) NOT NULL,
  `authors` varchar(200) NOT NULL,
  `title` varchar(200) NOT NULL,
  `copiesTotal` int(11) NOT NULL,
  `genre` enum('Fiction','Novel','Mystery','Fantasy','Self_Help') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1202 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'096796931X','Godiva','Knife in the Water (Nóz w wodzie)',6,'Fantasy'),(2,'9793903209007','Remington','Alien Trespass',2,'Mystery'),(3,'2028030283','Augustin','Darktown Strutters (Get Down and Boogie)',2,'Fiction'),(4,'038965082X','Faunie','Death in Venice (Morte a Venezia)',10,'Novel'),(5,'3648556533','Valerie','Fiddlededee',4,'Fantasy'),(6,'0588196428','Jo','Deadline',8,'Fantasy'),(7,'0668187492','Fancie','Journey of Natty Gann, The',2,'Novel'),(8,'9786741610324','Quintin','Emperor\'s New Groove 2: Kronk\'s New Groove, The',5,'Fiction'),(9,'9797435845566','Steffie','Crippled Avengers (Can que) (Return of the 5 Deadly Venoms)',10,'Fantasy'),(10,'9787023475796','Amara','Carne',7,'Fantasy'),(11,'9581595422','Car','Kirikou and the Wild Beast (Kirikou et les bêtes sauvages)',2,'Mystery'),(12,'0120098520','Edlin','America\'s Heart and Soul',5,'Fantasy'),(13,'8773208833','Lynna','Crawlspace',3,'Fiction'),(14,'9783099756822','Ogdan','Town Called Panic, A (Panique au village)',8,'Mystery'),(15,'9791289023913','Sargent','Nightmare in Las Cruces, A',9,'Fiction'),(16,'1932461035','Abra','South Park: Imaginationland',9,'Self_Help'),(17,'9781606597329','Scott','Ghost Dog: The Way of the Samurai',10,'Mystery'),(18,'6092768941','Marcella','Ghost Machine',9,'Self_Help'),(19,'9791624906761','Eada','Leprechaun 3',9,'Fiction'),(20,'6525648599','Giacinta','Snowriders',9,'Fantasy'),(21,'9784359960271','Happy','Brother (Hermano)',4,'Mystery'),(22,'9256365014','Dorry','Nude Nuns with Big Guns',10,'Mystery'),(23,'5668792765','Shayla','Here Comes Peter Cottontail ',1,'Fantasy'),(24,'9781854463470','Marybeth','Battle Royale (Batoru rowaiaru)',2,'Fantasy'),(25,'9780241216249','Carmencita','You Don\'t Mess with the Zohan',8,'Fantasy'),(26,'9795353392183','Nealson','Mike Bassett: England Manager',5,'Fiction'),(27,'9787685728567','Johnath','No habrá paz para los malvados',5,'Fantasy'),(28,'7115742219','Clementina','In Your Dreams (Dans tes rêves)',6,'Mystery'),(29,'6059166660','Marcos','Brand Upon the Brain!',2,'Novel'),(30,'1923825003','Laurena','Skin Too Few: The Days of Nick Drake, A',7,'Mystery'),(31,'4520623693','Barnaby','Man Who Left His Will on Film, The (Tôkyô sensô sengo hiwa)',7,'Novel'),(32,'9781244667536','Pate','Dresser, The',2,'Mystery'),(33,'7918970225','Katrine','Cold Turkey',10,'Mystery'),(34,'8444475912','Mikel','Of Human Bondage',1,'Novel'),(35,'5346987495','Lodovico','See You Tomorrow, Everyone',8,'Fiction'),(36,'9798285432297','Emmett','Squid and the Whale, The',10,'Fantasy'),(37,'9795343800769','Mac','ByeBye Bin Laden',9,'Fantasy'),(38,'3780007819','Jaimie','Let\'s Get Those English Girls',9,'Fantasy'),(39,'9786799832168','Darla','Gitarrmongot',6,'Fiction'),(40,'9670273803','Mireielle','Tesseract, The',3,'Fantasy'),(41,'1898440689','Dacey','Cage aux Folles, La',2,'Fantasy'),(42,'635795909X','Celestia','Assassination (Ansatsu) (Assassination, The) (Assassin, The)',9,'Novel'),(43,'9804485133','Philip','The Town that Dreaded Sundown',2,'Self_Help'),(44,'9790676804411','Wynnie','Prisoner, The (Island of Fire) (Huo shao dao)',3,'Novel'),(45,'4343580571','Spencer','The Hobbit: The Battle of the Five Armies',9,'Novel'),(46,'9781797871986','Corinne','Dragon Age: Redemption',1,'Fantasy'),(47,'5621712277','Aimee','After Five in the Jungle (Nach Fünf im Urwald)',2,'Fiction'),(48,'9784840319820','Haleigh','Snow White',3,'Novel'),(49,'9798234581635','Anett','Cass',3,'Mystery'),(50,'714001227X','Caesar','American Reunion (American Pie 4)',7,'Fiction'),(51,'4830004959','Konstanze','Time (Shi gan)',7,'Fantasy'),(52,'3868423826','Kain','Easy Virtue',7,'Mystery'),(53,'9790180427733','Burk','7 Women (a.k.a. Seven Women)',4,'Mystery'),(54,'9016621504','Palm','Juche Idea, The',7,'Novel'),(55,'9793350694624','Juana','Love in Another Language (Baska Dilde Ask)',3,'Novel'),(56,'8027496055','Cari','I Heart Monster Movies',10,'Mystery'),(57,'9785915934447','Cordie','52 Tuesdays',3,'Fiction'),(58,'8972929506','Marieann','A Life in Dirty Movies',8,'Fiction'),(59,'4537280530','Bernete','Captain America: The First Avenger',9,'Fiction'),(60,'4886101712','Orrin','Fly, The',2,'Self_Help'),(61,'9799961157834','Vale','Art and Craft',8,'Fiction'),(62,'2895044279','Miles','Finding Bliss',1,'Fantasy'),(63,'462085493X','Page','Black Coal, Thin Ice (Bai ri yan huo)',6,'Fiction'),(64,'9704072287','Shawna','Welcome, or No Trespassing',4,'Fantasy'),(65,'9786201331557','Ekaterina','Stone Angel, The',5,'Self_Help'),(66,'9782335287060','Salome','Phone Booth',2,'Mystery'),(67,'9425131597','Crystie','Hunted City',7,'Fantasy'),(68,'6565442983','Fraze','Hitler\'s Stealth Fighter',4,'Mystery'),(69,'9799496611451','Fredia','Drained (O cheiro do Ralo)',3,'Fiction'),(70,'5825923829','Hildegarde','U.S. vs. John Lennon, The',4,'Fantasy'),(71,'9792809105634','Hugo','Watch Out for the Automobile (Beregis avtomobilya)',7,'Fiction'),(72,'4062421585','Mikel','Stuck Between Stations',3,'Novel'),(73,'1587951231','Inga','Mega Shark vs. Giant Octopus',1,'Fantasy'),(74,'9797964629859','Lemmy','Double Take',1,'Mystery'),(75,'3770095863','Dolley','Legend of the Red Dragon (a.k.a. New Legend of Shaolin, The) (Hong Xi Guan: Zhi Shao Lin wu zu)',5,'Fiction'),(76,'9787779682315','Kalil','The Learning Tree',8,'Self_Help'),(77,'9780338048960','Kris','Hound of the Baskervilles, The',2,'Mystery'),(78,'7530840339','Yevette','Flesh and Bone',2,'Novel'),(79,'9795138017225','Kevon','Mothlight',6,'Fantasy'),(80,'978792752836X','Jessa','Today\'s Special',2,'Fiction'),(81,'5168452183','Mahmoud','Way, The',3,'Fiction'),(82,'9796025961980','Hardy','Tarzan\'s New York Adventure',4,'Fiction'),(83,'9788374055200','Gabey','Message in a Bottle',8,'Fantasy'),(84,'9786237214453','Brooks','Paradise Alley',4,'Fiction'),(85,'9781298901235','Ginny','Saw VII 3D  The Final Chapter',3,'Self_Help'),(86,'9787373787908','Margoo','Hilary and Jackie',3,'Fiction'),(87,'9790626669839','Tedman','Reform School Girls',9,'Fantasy'),(88,'5557084029','Corilla','Drumline',5,'Fantasy'),(89,'979215567189X','Travus','Adventures of Tintin, The',7,'Fantasy'),(90,'0297378384','Mildred','Mysterious Skin',10,'Fiction'),(91,'4921103364','Catie','Willow',10,'Self_Help'),(92,'1839122838','Ranee','Shinobi No Mono 4: Siege',10,'Novel'),(93,'6336978915','Mattie','Sun Alley (Sonnenallee)',6,'Mystery'),(94,'6035860400','Arney','Sarah Silverman:  We Are Miracles',2,'Novel'),(95,'2187638294','Mignon','M*A*S*H (a.k.a. MASH)',2,'Mystery'),(96,'978989845637X','Laurie','Honeymoon Killers, The',3,'Fiction'),(97,'2290399523','Vasily','Grey Gardens',7,'Fiction'),(98,'3369854112','Mead','Radio Rebel',2,'Self_Help'),(99,'6484079021','Say','Truly Human (Et rigtigt menneske)',3,'Mystery'),(100,'9789846408374','Blythe','Tenchi: The Samurai Astronomer',10,'Fiction'),(101,'2268347478','Katey','Soulless',4,'Fiction'),(102,'7663238281','Sarge','Three Steps Above Heaven (Tres metros sobre el cielo)',3,'Novel'),(103,'5320918151','Paul','Creature',4,'Fantasy'),(104,'1355691273','Jolie','Midnight\'s Children',7,'Self_Help'),(105,'8298235592','Bertie','Mummy, The',2,'Self_Help'),(106,'2021392546','Shaw','Almost You',1,'Self_Help'),(107,'8722096159','Kenneth','Needle, The (Igla)',7,'Self_Help'),(108,'9790007792158','Mariette','For Neda',8,'Fantasy'),(109,'1328895998','Lion','Turks & Caicos',9,'Novel'),(110,'9782954852917','Jemie','Northwest Passage',7,'Mystery'),(111,'9795094259423','Nikolaos','Saw II',10,'Fiction'),(112,'5346557806','Ricki','Black Snake Moan',10,'Self_Help'),(113,'9783922937993','Gizela','Marquis',4,'Fantasy'),(114,'9781520365608','Wit','Jay and Silent Bob Strike Back',4,'Novel'),(115,'9793894979925','Casi','If You Could See What I Hear',1,'Fantasy'),(116,'978446977197X','Netti','Treasure Island',2,'Novel'),(117,'5520365598','Bowie','Arsenic and Old Lace',10,'Fiction'),(118,'8686702139','Dasya','Exit Smiling',3,'Fiction'),(119,'9785451623805','Orbadiah','Step Into Liquid',7,'Fantasy'),(120,'479988008X','Ania','Falling in Love',9,'Mystery'),(121,'9785858358500','Lacie','Bride Comes Home, The',1,'Mystery'),(122,'2594617067','Irvine','All Together, The',1,'Mystery'),(123,'4383529534','Antonietta','3 Ring Circus',1,'Mystery'),(124,'0677136447','Ronna','Well Digger\'s Daughter, The (La fille du puisatier)',4,'Fantasy'),(125,'9786583285525','Blinnie','Pleasure of Being Robbed, The',7,'Fantasy'),(126,'979638998829X','Lurette','The Private Life of Deer',6,'Fiction'),(127,'5611200265','Yul','Corvette Summer',3,'Novel'),(128,'9794828197273','Kerrie','My Mother (Ma mère)',2,'Fiction'),(129,'2632586445','Corilla','Larceny, Inc.',5,'Novel'),(130,'9788632494724','Persis','Solomon Kane',8,'Mystery'),(131,'978415106849X','Mead','Love Exposure (Ai No Mukidashi)',6,'Fiction'),(132,'9797531096595','Colas','Just Married',9,'Mystery'),(133,'9788523331492','Latisha','TokyoGa',10,'Mystery'),(134,'9797637725960','Peg','Doctor Takes a Wife, The',9,'Self_Help'),(135,'9787714180099','Gaby','Manakamana',7,'Fiction'),(136,'2936388899','Guenevere','Air Force One',7,'Fantasy'),(137,'9784715458071','Bess','Boat That Rocked, The (a.k.a. Pirate Radio)',7,'Novel'),(138,'4672136051','Constantino','Desperate Living',2,'Fiction'),(139,'9796663750453','Flemming','Our Paradise (Notre paradis)',9,'Self_Help'),(140,'9787082266944','Randie','Numb',6,'Self_Help'),(141,'7113996485','June','Pride & Prejudice',2,'Self_Help'),(142,'9789030878142','Mallissa','Cannonball Run, The',8,'Self_Help'),(143,'9787952594451','Sherwin','Man of the West',1,'Fantasy'),(144,'8883775503','Marty','Katt Williams: Priceless: Afterlife',6,'Novel'),(145,'9788035947532','Dalia','Weekend',3,'Fiction'),(146,'6145226870','Alice','Rebel, The (Le Rebelle)',7,'Novel'),(147,'3367605700','Annora','November Man, The',9,'Fantasy'),(148,'9799567460418','Feodor','Kidulthood',7,'Self_Help'),(149,'3186727790','Emlyn','Henry\'s Crime',7,'Fiction'),(150,'978480800108X','Bald','Sons of Katie Elder',7,'Mystery'),(151,'9781744057680','Jilleen','Sundays and Cybele (Les dimanches de Ville d\'Avray)',9,'Self_Help'),(152,'4614501079','Brooks','Punishment Park',3,'Self_Help'),(153,'9785920637749','Winnah','Details, The',6,'Fantasy'),(154,'978976215228X','Mace','Three Marias, The (Três Marias, As)',4,'Mystery'),(155,'6030240560','Bertha','Fireworks (Hanabi)',6,'Novel'),(156,'9744713232','Clive','Mall',4,'Self_Help'),(157,'9799947699463','Bil','13 Fighting Men',5,'Fantasy'),(158,'9798700101176','Ode','Trip to Italy, The',9,'Fiction'),(159,'4093445516','Clemence','Trotsky, The',5,'Novel'),(160,'9786964963448','Duncan','Sleepy Hollow',9,'Fantasy'),(161,'4817915757','Eldridge','Hellgate',9,'Novel'),(162,'9783519417189','Abramo','Dry White Season, A',3,'Fiction'),(163,'9785176956373','Flemming','Boys, The',8,'Fantasy'),(164,'9784994902356','Norman','Rapture (Arrebato)',2,'Novel'),(165,'3072170398','Loralie','Goal! The Dream Begins (Goal!)',10,'Mystery'),(166,'9785949576977','Giraldo','Kiss of the Vampire, The',1,'Self_Help'),(167,'9796271823394','Emmy','Remains of the Day, The',8,'Fantasy'),(168,'9797117698527','Kinny','I\'ll Cry Tomorrow',10,'Novel'),(169,'7260397357','Charmian','Diggers',10,'Novel'),(170,'9780092523676','Luca','Port of Shadows (Quai des brumes)',10,'Fantasy'),(171,'9789266066575','Anastasia','Candyman 3: Day of the Dead',7,'Fiction'),(172,'9786825796550','Dionis','I\'m Here',4,'Fantasy'),(173,'9787228306368','Berte','ABBA: The Movie',5,'Mystery'),(174,'9799714693497','Harbert','3 dev adam (Three Giant Men) ',5,'Fiction'),(175,'4796818340','Inge','Under the Bridges (Unter den Brücken)',4,'Fiction'),(176,'3398114563','Rubina','Space Movie, The',9,'Mystery'),(177,'9799853966522','Oralle','American Affair, An',8,'Mystery'),(178,'9783092161084','Karol','Secret Society',3,'Self_Help'),(179,'3021768873','Kalie','Concert, Le',7,'Fantasy'),(180,'7034242593','Erminia','Bad Men of Missouri',9,'Novel'),(181,'8545059264','Melantha','Snarveien (Detour)',6,'Fantasy'),(182,'6912977863','Olag','Hunting and Gathering (Ensemble, c\'est tout)',1,'Mystery'),(183,'2881833632','Damaris','Beast of Yucca Flats, The',9,'Fiction'),(184,'2958065502','Gerhard','Longtime Companion',5,'Mystery'),(185,'9796083710316','Tisha','Navy Seals',3,'Novel'),(186,'9786096095410','Umeko','House on Carroll Street, The',3,'Self_Help'),(187,'9798365326884','Base','Léon Morin, Priest (Léon Morin, prêtre)',1,'Self_Help'),(188,'978437478201X','Dew','Normal Heart, The',3,'Fiction'),(189,'4461827070','Lorette','Crawlspace',4,'Fantasy'),(190,'9912212037','Fianna','Free Samples',3,'Novel'),(191,'9785510386541','Daphene','General\'s Daughter, The',4,'Mystery'),(192,'8925355086','Jayme','Desire Under the Elms',4,'Fantasy'),(193,'9780711499373','Alessandra','Tough Guise: Violence, Media & the Crisis in Masculinity',8,'Fiction'),(194,'8168828003','Brock','Forklift Driver Klaus: The First Day on the Job',1,'Novel'),(195,'5767050554','Bengt','SimpleMinded Murder, The (Enfaldige mördaren, Den)',4,'Fantasy'),(196,'9782063168415','Tracy','Sweet Evil (L\'enfance du mal)',4,'Novel'),(197,'9799471124158','Darlleen','Apartment, The',1,'Mystery'),(198,'9790095456007','Margie','61*',4,'Self_Help'),(199,'979910221606X','Freida','Fantomas Unleashed',6,'Fiction'),(200,'6952644288','Avrit','Black Death',9,'Mystery'),(201,'9784520485290','Teri','Bomb the System',7,'Mystery'),(202,'9788987090299','Gabey','Money Train',1,'Self_Help'),(203,'9790119324482','Lamar','Remote Control',3,'Mystery'),(204,'9787370472132','Mil','Monument Ave.',7,'Mystery'),(205,'979926496293X','Zorina','Funny Games U.S.',5,'Novel'),(206,'7687405412','Toiboid','Anne Frank Remembered',1,'Fantasy'),(207,'9787911464596','Atlante','Pluto\'s Christmas Tree',9,'Novel'),(208,'5963872774','Constancia','Alvin and the Chipmunks',6,'Novel'),(209,'9783334885933','Onofredo','Jackass: The Movie',6,'Self_Help'),(210,'9794038185834','Hercule','Party Girl',2,'Self_Help'),(211,'9781190049120','Banky','Passchendaele',10,'Novel'),(212,'0953447766','Jaimie','Our Blushing Brides',6,'Novel'),(213,'9798078257627','Tomaso','Monty Python\'s Life of Brian',4,'Novel'),(214,'9792132324034','Jodi','El asombroso mundo de Borjamari y Pocholo',4,'Novel'),(215,'9798966473180','Aila','Batman Begins',1,'Fantasy'),(216,'9583975699','Levy','Autómata (Automata)',8,'Fantasy'),(217,'7233958432','Heriberto','Royal Wedding',3,'Novel'),(218,'9796773808359','Faun','Detropia',9,'Novel'),(219,'1887389024','Kari','Attack of the 50 Ft. Woman',9,'Novel'),(220,'9787441108284','Howie','Kink',2,'Self_Help'),(221,'9863173150','Rabi','Dot and the Whale',8,'Self_Help'),(222,'2794460412','Levy','Prelude to a Kiss',2,'Novel'),(223,'9788423383059','Kaila','In Tranzit',9,'Mystery'),(224,'9796152321239','Bernadene','Woman in White, The',7,'Fantasy'),(225,'9733068047','Jayme','Bounty Killer',8,'Self_Help'),(226,'4297896494','Dorie','Bloodbrothers',5,'Novel'),(227,'9783258233640','Nigel','Bad Country',9,'Self_Help'),(228,'9789098491057','Sancho','Calamari Union',6,'Self_Help'),(229,'9791763666247','Collin','Riot in Cell Block 11',3,'Mystery'),(230,'9789649851992','Janie','Viva Knievel!',9,'Self_Help'),(231,'978224186515X','Sharyl','Steel Dawn',7,'Novel'),(232,'7853520336','Maisey','Nadja',2,'Mystery'),(233,'9789610703976','Merell','Without Love',4,'Mystery'),(234,'9796118047685','Janette','Soldiers of Fortune',5,'Mystery'),(235,'049088735X','Ax','Sarah Silverman:  We Are Miracles',5,'Fantasy'),(236,'3407541724','Krisha','Holiday Engagement',6,'Mystery'),(237,'3792566664','Brandon','George Lopez: America\'s Mexican',9,'Novel'),(238,'9780204222974','Elladine','Twentieth Century',5,'Fiction'),(239,'978016391947X','Lazarus','Goodbye World',4,'Self_Help'),(240,'2172554855','Jodi','Allegro',4,'Fiction'),(241,'9796322106473','Elissa','Three Way',10,'Novel'),(242,'978838135594X','Obed','Fox and the Hound, The',9,'Fiction'),(243,'9797694205695','Ondrea','Good Boy!',2,'Mystery'),(244,'6698007978','Horace','Bob\'s Birthday',10,'Self_Help'),(245,'4048497634','Ford','Mad Masters, The (Les maîtres fous)',2,'Self_Help'),(246,'5761085968','Emmalee','Silent Sonata',3,'Mystery'),(247,'0663482003','Katlin','Out of Reach',5,'Mystery'),(248,'9794727181746','Isaac','Parking (Ting che)',2,'Mystery'),(249,'9780150517726','Cathrine','Shiver of the Vampires, The (Frisson des vampires, Le)',5,'Mystery'),(250,'9434843375','Mavis','Mysterious Lady, The',2,'Self_Help'),(251,'9786722789918','Augy','Simon & the Oaks',5,'Fiction'),(252,'9781666277894','Sharline','Blood Beast Terror, The',6,'Self_Help'),(253,'8331660536','Margaux','Crimson Rivers, The (Rivières pourpres, Les)',10,'Mystery'),(254,'9796042019694','Barbie','MacGyver: Lost Treasure of Atlantis',3,'Self_Help'),(255,'9797544923053','Charin','Presidentintekijät',10,'Self_Help'),(256,'9780230456901','Shaughn','Now You See Me',4,'Novel'),(257,'9795615953810','Jewell','Fools\' Parade',6,'Mystery'),(258,'9797494648860','Hurlee','Rosetta',4,'Fantasy'),(259,'9780795511159','Magdalen','Little Prince, The',9,'Fiction'),(260,'9784514172731','Paton','America the Beautiful ',2,'Self_Help'),(261,'0371039444','Yorke','Astronaut\'s Wife, The',5,'Fiction'),(262,'9787979303164','Franklyn','Wrestling with Alligators',2,'Novel'),(263,'1087364779','Sean','Serendipity',8,'Fantasy'),(264,'9784339093602','Lilllie','Panic in the Streets',3,'Novel'),(265,'9793527082956','Jarrod','Dracula 2000',4,'Self_Help'),(266,'9790220671753','Forester','Diary of a Mad Housewife',6,'Fantasy'),(267,'9799786008669','Dayle','Whispering Corridors (Yeogo Goedam)',1,'Self_Help'),(268,'9798890219351','Lorrayne','Carry on Cabby',4,'Fantasy'),(269,'5599459039','Breena','Singapore Sling (Singapore sling: O anthropos pou agapise ena ptoma)',1,'Self_Help'),(270,'9785230656425','Dela','Sailor of the King',10,'Fantasy'),(271,'9795621594800','Fredericka','Sorcerer',9,'Fantasy'),(272,'7765601547','Rochella','Bill Burr: You People Are All the Same',6,'Novel'),(273,'9788508401728','Marisa','Legend, The (Legend of Fong SaiYuk, The) (Fong Sai Yuk)',4,'Self_Help'),(274,'8670932555','Vicki','Dishonored',8,'Novel'),(275,'9791833057848','Lethia','Bachelor Party, The',8,'Fantasy'),(276,'9782902331002','Blythe','Storm Warriors, The (Fung wan II)',9,'Fantasy'),(277,'7975986763','Larry','Ulysses\' Gaze (To Vlemma tou Odyssea)',10,'Mystery'),(278,'3666391907','Bronny','Dentist, The',10,'Novel'),(279,'0608296139','Archibold','Century',1,'Novel'),(280,'1169581544','Sheree','Meet the Deedles',7,'Novel'),(281,'979736998804X','Carleton','Electra Glide in Blue',4,'Self_Help'),(282,'9790052383571','Miquela','Mission to Mir',8,'Mystery'),(283,'4599813435','Glenine','Alexandra\'s Project',4,'Novel'),(284,'9792613085606','Cornell','Shout, The',4,'Fiction'),(285,'9783038135267','Galina','Stealth',1,'Mystery'),(286,'9784837310850','Rhona','Thousand Words, A',5,'Mystery'),(287,'9792177967120','Alane','21 Grams',8,'Self_Help'),(288,'9789275099537','Dorian','Flawless',3,'Fantasy'),(289,'9795425181418','Andrus','Eva',10,'Fiction'),(290,'7432979235','Glori','Ronin',10,'Mystery'),(291,'9786887180804','George','Confessions of a Superhero',10,'Fantasy'),(292,'3556549107','Elston','Streetcar Named Desire, A',4,'Fiction'),(293,'9794981414684','Veda','Into the Storm',2,'Fiction'),(294,'978693117925X','Kalinda','Bounty Killer',9,'Fantasy'),(295,'1288956886','Woodman','Pressure Point ',2,'Self_Help'),(296,'9789713494725','Jefferson','Legends of Oz: Dorothy\'s Return',1,'Mystery'),(297,'7647464884','Sophey','Eight Below',3,'Fantasy'),(298,'9788781226845','Rahel','Kivski Freski',2,'Mystery'),(299,'9786193274235','Malia','Hotel Chevalier (Part 1 of \'The Darjeeling Limited\')',5,'Mystery'),(300,'7375812454','Alister','Air Guitar Nation',4,'Self_Help');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-24 16:26:09