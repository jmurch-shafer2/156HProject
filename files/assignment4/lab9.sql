use joelm;

start transaction;

drop table if exists AlbumSong;
drop table if exists Song;
drop table if exists Album;
drop table if exists BandMember;
drop table if exists Band;
drop table if exists Musician;

create table Band (
  bandId int not null primary key auto_increment,
  name varchar(200) not null
)engine=InnoDB,collate=latin1_general_cs;

create table Musician (
  musicianId int not null primary key auto_increment,
  firstName varchar(50),
  lastName varchar(50),
  country varchar(100)
)engine=InnoDB,collate=latin1_general_cs;

create table BandMember (
  bandMemberId int not null primary key auto_increment,
  musicianId int not null,
  bandId int not null,
  yearJoined int,
  foreign key (musicianId) references Musician(musicianId),
  foreign key (bandId) references Band(bandId),
  #prevent duplicate entries
  constraint uniquePair unique index(musicianId,bandId)
)engine=InnoDB,collate=latin1_general_cs;

create table Album (
  albumId int not null primary key auto_increment,
  title varchar(100) not null,
  year int not null,
  number int not null,
  bandId int not null,
  foreign key (bandId) references Band(bandId)
)engine=InnoDB,collate=latin1_general_cs;

create table Song (
  songId int not null primary key auto_increment,
  title varchar(255) not null
)engine=InnoDB,collate=latin1_general_cs;

create table AlbumSong (
  albumSongId int not null primary key auto_increment,
  trackNumber int not null,
  trackLength int not null,
  albumId int not null,
  songId int not null,
  foreign key (albumId) references Album(albumId),
  foreign key (songId) references Song(songId),
  #prevent duplicate entries
  constraint uniquePair unique index(albumId,songId)
)engine=InnoDB,collate=latin1_general_cs;




insert into Musician (musicianId,firstName,lastName,country) values (1, 'Lou', 'Reed', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (2, 'Maureen', 'Tucker', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (3, 'Doug', 'Yule', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (4, 'Sterling', 'Morrison', 'USA');

insert into Musician (musicianId,firstName,lastName,country) values (5, 'Kurt', 'Cobain', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (6, 'Christ', 'Novoselic', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (7, 'Dave', 'Grohl', 'USA');

#dandies
insert into Musician (musicianId,firstName,lastName,country) values (8701, 'Courtney', 'Taylor-Taylor', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (8702, 'Peter', 'Holmstrom', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (8703, 'Zia', 'McCabe', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (8704, 'Brent', 'DeBoer', 'USA');

#bjm
insert into Musician (musicianId,firstName,lastName,country) values (8705, 'Anton', 'Newcombe', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (8706, 'Matt', 'Hollywood', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (8707, 'Rickey Rene', 'Maymi', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (8708, 'Patrick', 'Straczek', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (8709, 'Travis', 'Threlkel', 'USA');

#white stripes
insert into Musician (musicianId,firstName,lastName,country) values (8710, 'Jack', 'White', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (8711, 'Meg', 'White', 'USA');

#raconteurs
insert into Musician (musicianId,firstName,lastName,country) values (8712, 'Brendan', 'Benson', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (8713, 'Jack', 'Lawrence', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (8714, 'Patrick', 'Keeler', 'USA');

insert into Band (bandId,name) values (1,'Velvet Underground');
insert into Band (bandId,name) values (2,'Nirvana');
insert into Band (bandId,name) values (3,'Tom Waits');
insert into Band (bandId,name) values (8801,'The Dandy Warhols');
insert into Band (bandId,name) values (8802,'Brian Jonestown Massacre');
insert into Band (bandId,name) values (8803,'The White Stripes');
insert into Band (bandId,name) values (8804,'The Raconteurs');

# Velvet Underground
insert into BandMember (musicianId,bandId) values (1,1);
insert into BandMember (musicianId,bandId) values (2,1);
insert into BandMember (musicianId,bandId) values (3,1);
insert into BandMember (musicianId,bandId) values (4,1);

# Nirvana
insert into BandMember (musicianId,bandId) values (5,2);
insert into BandMember (musicianId,bandId) values (6,2);
insert into BandMember (musicianId,bandId) values (7,2);

# Dandies
insert into BandMember (musicianId,bandId) values (8701,8801);
insert into BandMember (musicianId,bandId) values (8702,8801);
insert into BandMember (musicianId,bandId) values (8703,8801);
insert into BandMember (musicianId,bandId) values (8704,8801);

#BJM
insert into BandMember (musicianId,bandId) values (8705,8802);
insert into BandMember (musicianId,bandId) values (8706,8802);
insert into BandMember (musicianId,bandId) values (8707,8802);
insert into BandMember (musicianId,bandId) values (8708,8802);
insert into BandMember (musicianId,bandId) values (8709,8802);

# White Stripes
insert into BandMember (musicianId,bandId) values (8710,8803);
insert into BandMember (musicianId,bandId) values (8711,8803);

# Raconteurs
insert into BandMember (musicianId,bandId) values (8710,8804);
insert into BandMember (musicianId,bandId) values (8712,8804);
insert into BandMember (musicianId,bandId) values (8713,8804);
insert into BandMember (musicianId,bandId) values (8714,8804);

### Velvet Underground & Nico

INSERT INTO Album (albumId,title,year,number,bandId) values (1,'The Velvet Underground & Nico',1967,1,1);

INSERT INTO Song (songId, title) VALUES (1, 'Sunday Morning');
INSERT INTO Song (songId, title) VALUES (2, 'I\'m Waiting for the Man');
INSERT INTO Song (songId, title) VALUES (3, 'Femme Fatale');
INSERT INTO Song (songId, title) VALUES (4, 'Venus in Furs');
INSERT INTO Song (songId, title) VALUES (5, 'Run Run Run');
INSERT INTO Song (songId, title) VALUES (6, 'All Tomorrow\'s Parties');
INSERT INTO Song (songId, title) VALUES (7, 'Heroin');
INSERT INTO Song (songId, title) VALUES (8, 'There She Goes Again');
INSERT INTO Song (songId, title) VALUES (9, 'I\'ll Be Your Mirror');
INSERT INTO Song (songId, title) VALUES (10, 'The Black Angel\'s Death Song');
INSERT INTO Song (songId, title) VALUES (11, 'European Son');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 174, 1,1);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 279, 1,2);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 158, 1,3);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 312, 1,4);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 262, 1,5);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 360, 1,6);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 432, 1,7);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 161, 1,8);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 134, 1,9);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 191, 1,10);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 466, 1,11);

### Nirvana (2) - Bleach

insert into Album (albumId,title,year,number,bandId) values (2,'Bleach',1989,1,2);

insert into Song (songId, title) values (12, 'Blew');
insert into Song (songId, title) values (13, 'Floyd the Barber');
insert into Song (songId, title) values (14, 'About a Girl');
insert into Song (songId, title) values (15, 'School');
insert into Song (songId, title) values (16, 'Love Buzz');
insert into Song (songId, title) values (17, 'Paper Cuts');
insert into Song (songId, title) values (18, 'Negative Creep');
insert into Song (songId, title) values (19, 'Scoff');
insert into Song (songId, title) values (20, 'Swap Meet');
insert into Song (songId, title) values (21, 'Mr. Moustache');
insert into Song (songId, title) values (22, 'Sifting');
insert into Song (songId, title) values (23, 'Big Cheese');
insert into Song (songId, title) values (24, 'Downer');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 175, 2, 12);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 138, 2, 13);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 168, 2, 14);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 162, 2, 15);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 215, 2, 16);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 246, 2, 17);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 176, 2, 18);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 250, 2, 19);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 183, 2, 20);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 204, 2, 21);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 322, 2, 22);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 222, 2, 23);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 103, 2, 24);

### Nirvana (2) - Nevermind

insert into Album (albumId,title,year,number,bandId) values (3,'Nevermind',1991,2,2);

insert into Song (songId, title) values (25, 'Smells Like Teen Spirit');
insert into Song (songId, title) values (26, 'In Bloom');
insert into Song (songId, title) values (27, 'Come as You Are');
insert into Song (songId, title) values (28, 'Breed');
insert into Song (songId, title) values (29, 'Lithium');
insert into Song (songId, title) values (30, 'Polly');
insert into Song (songId, title) values (31, 'Territorial Pissings');
insert into Song (songId, title) values (32, 'Drain You');
insert into Song (songId, title) values (33, 'Lounge Act');
insert into Song (songId, title) values (34, 'Stay Away');
insert into Song (songId, title) values (35, 'On a Plain');
insert into Song (songId, title) values (36, 'Something in the Way');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 301, 3, 25);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 254, 3, 26);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 219, 3, 27);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 183, 3, 28);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 257, 3, 29);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 177, 3, 30);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 142, 3, 31);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 223, 3, 32);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 156, 3, 33);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 212, 3, 34);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 196, 3, 35);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 231, 3, 36);

### Nirvana (2) - In Utero

insert into Album (albumId,title,year,number,bandId) values (4,'In Utero',1993,3,2);

insert into Song (songId, title) values (37, 'Serve the Servants');
insert into Song (songId, title) values (38, 'Scentless Apprentice');
insert into Song (songId, title) values (39, 'Heart-Shaped Box');
insert into Song (songId, title) values (41, 'Rape Me');
insert into Song (songId, title) values (42, 'Frances Farmer Will Have Her Revenge on Seattle');
insert into Song (songId, title) values (43, 'Dumb');
insert into Song (songId, title) values (44, 'Very Ape');
insert into Song (songId, title) values (45, 'Milk It');
insert into Song (songId, title) values (46, 'Pennyroyal Tea');
insert into Song (songId, title) values (47, 'Radio Friendly Unit Shifter');
insert into Song (songId, title) values (48, 'tourette\'s'); 
insert into Song (songId, title) values (49, 'All Apologies');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  216, 4, 37);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  228, 4, 38);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  281, 4, 39);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  170, 4, 41);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  249, 4, 42);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  152, 4, 43);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  116, 4, 44);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  235, 4, 45);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  217, 4, 46);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 291, 4, 47);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 155, 4, 48);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 231, 4, 49);

### Tom Waits (3) - Rain Dogs

insert into Album (albumId,title,year,number,bandId) values (5,'Rain Dogs',1985,8,3);

insert into Song (songId, title) values (51, 'Singapore');
insert into Song (songId, title) values (52, 'Clap Hands');
insert into Song (songId, title) values (53, 'Cemetery Polka');
insert into Song (songId, title) values (54, 'Jockey Full of Bourbon');
insert into Song (songId, title) values (55, 'Tango Till They\'re Sore');
insert into Song (songId, title) values (56, 'Big Black Mariah');
insert into Song (songId, title) values (57, 'Diamonds & Gold');
insert into Song (songId, title) values (58, 'Hang Down Your Head');
insert into Song (songId, title) values (59, 'Time');
insert into Song (songId, title) values (60, 'Rain Dogs');
insert into Song (songId, title) values (61, 'Midtown');
insert into Song (songId, title) values (62, '9th & Hennepin');
insert into Song (songId, title) values (63, 'Gun Street Girl');
insert into Song (songId, title) values (64, 'Union Square');
insert into Song (songId, title) values (65, 'Blind Love');
insert into Song (songId, title) values (66, 'Walking Spanish');
insert into Song (songId, title) values (67, 'Downtown Train');
insert into Song (songId, title) values (68, 'Bride of Rain Dog');
insert into Song (songId, title) values (69, 'Anywhere I Lay My Head');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  166, 5, 51);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  227, 5, 52);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  111, 5, 53);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  165, 5, 54);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  169, 5, 55);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  164, 5, 56);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  151, 5, 57);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  152, 5, 58);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  235, 5, 59);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 176, 5, 60);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 60, 5, 61);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 118, 5, 62);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 277, 5, 63);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 144, 5, 64);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (15, 258, 5, 65);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (16, 185, 5, 66);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (17, 233, 5, 67);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (18, 67, 5, 68);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (19, 168, 5, 69);


### Tom Waits (3) - Mule Variations

insert into Album (albumId,title,year,number,bandId) values (6,'Mule Variations',1999,12,3);

insert into Song (songId, title) values (71, 'Big in Japan');
insert into Song (songId, title) values (72, 'Lowside of the Road');
insert into Song (songId, title) values (73, 'Hold On');
insert into Song (songId, title) values (74, 'Get Behind the Mule');
insert into Song (songId, title) values (75, 'House Where Nobody Lives');
insert into Song (songId, title) values (76, 'Cold Water');
insert into Song (songId, title) values (77, 'Pony');
insert into Song (songId, title) values (78, 'What\'s He Building?');
insert into Song (songId, title) values (79, 'Black Market Baby');
insert into Song (songId, title) values (80, 'Eyeball Kid');
insert into Song (songId, title) values (81, 'Picture in a Frame');
insert into Song (songId, title) values (82, 'Chocolate Jesus');
insert into Song (songId, title) values (83, 'Georgia Lee');
insert into Song (songId, title) values (84, 'Filipino Box Spring Hog');
insert into Song (songId, title) values (85, 'Take It with Me');
insert into Song (songId, title) values (86, 'Come on Up to the House');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  245, 6, 71);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  179, 6, 72);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  333, 6, 73);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  412, 6, 74);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  254, 6, 75);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  323, 6, 76);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  272, 6, 77);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  200, 6, 78);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  302, 6, 79);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 265, 6, 80);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 219, 6, 81);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 235, 6, 82);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 264, 6, 83);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 189, 6, 84);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (15, 264, 6, 85);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (16, 276, 6, 86);

insert into Musician (musicianId,firstName,lastName,country) values (3478, 'Jens', 'Kidman', 'Sweden');
insert into Musician (musicianId,firstName,lastName,country) values (3479, 'Tomas', 'Haake', 'Sweden');
insert into Musician (musicianId,firstName,lastName,country) values (3480, 'Fredrik', 'Thordendal', 'Sweden');
insert into Musician (musicianId,firstName,lastName,country) values (3481, 'Marten', 'Hagstrom', 'Sweden');
insert into Musician (musicianId,firstName,lastName,country) values (3482, 'Dick', 'Lovgren', 'Sweden');

insert into Band (bandId,name) values (2478,'Meshuggah');

#Meshuggah

insert into BandMember (musicianId,bandId) values (3478,2478);
insert into BandMember (musicianId,bandId) values (3479,2478);
insert into BandMember (musicianId,bandId) values (3480,2478);
insert into BandMember (musicianId,bandId) values (3481,2478);
insert into BandMember (musicianId,bandId) values (3482,2478);

###Koloss

INSERT INTO Album (albumId,title,year,number,bandId) values (654,'Koloss',2012,7,2478);

INSERT INTO Song (songId, title) VALUES (1568, 'I Am Colossus');
INSERT INTO Song (songId, title) VALUES (1569, 'The Demons Name Is Surveillance');
INSERT INTO Song (songId, title) VALUES (1570, 'Do Not Look Down');
INSERT INTO Song (songId, title) VALUES (1571, 'Behind The Sun');
INSERT INTO Song (songId, title) VALUES (1572, 'The Hurt That Finds You First');
INSERT INTO Song (songId, title) VALUES (1573, 'Marrow');
INSERT INTO Song (songId, title) VALUES (1574, 'Break Those Bones Whose Sinews Gave It Motion');
INSERT INTO Song (songId, title) VALUES (1575, 'Swarm');
INSERT INTO Song (songId, title) VALUES (1576, 'Demiurge');
INSERT INTO Song (songId, title) VALUES (1577, 'The Last Vigil');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 283, 654,1568);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 281, 654,1569);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 284, 654,1570);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 374, 654,1571);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 334, 654,1572);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 337, 654,1573);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 417, 654,1574);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 328, 654,1575);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 376, 654,1576);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 273, 654,1577);

###ObZen

INSERT INTO Album (albumId,title,year,number,bandId) values (655,'Obzen',2008,6,2478);

INSERT INTO Song (songId, title) VALUES (1579, 'Combustion');
INSERT INTO Song (songId, title) VALUES (1580, 'Electric Red');
INSERT INTO Song (songId, title) VALUES (1581, 'Bleed');
INSERT INTO Song (songId, title) VALUES (1582, 'Lethargica');
INSERT INTO Song (songId, title) VALUES (1583, 'obZen');
INSERT INTO Song (songId, title) VALUES (1584, 'This Spiteful Snake');
INSERT INTO Song (songId, title) VALUES (1585, 'Pineal Gland Optics');
INSERT INTO Song (songId, title) VALUES (1586, 'Pravus');
INSERT INTO Song (songId, title) VALUES (1587, 'Dancers to a Discordant System');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 248, 655,1579);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 351, 655,1580);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 442, 655,1581);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 347, 655,1582);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 264, 655,1583);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 292, 655,1584);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 312, 655,1585);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 310, 655,1586);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 576, 655,1587);

###I

INSERT INTO Album (albumId,title,year,number,bandId) values (656,'I',2004,4,2478);


INSERT INTO Song (songId, title) VALUES (1588, 'I');


insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 1260, 656,1588);


###Taylor

INSERT INTO Band (bandID, name) values (4, 'Taylor Swift');


### Taylor Swift - Taylor Swift
INSERT INTO Album (albumId, title, year, number, bandId) values (7, 'Taylor Swift', 2006, 1, 4);

INSERT INTO Song (songId, title) VALUES (87, 'Tim McGraw');
INSERT INTO Song (songId, title) VALUES (88, 'Picture to Burn');
INSERT INTO Song (songId, title) VALUES (89, 'Teardrops on My Guitar');
INSERT INTO Song (songId, title) VALUES (90, 'A Place in This World');
INSERT INTO Song (songId, title) VALUES (91, 'Cold as You');
INSERT INTO Song (songId, title) VALUES (92, 'The Outside');
INSERT INTO Song (songId, title) VALUES (93, 'Tied Together with a Smile');
INSERT INTO Song (songId, title) VALUES (94, 'Stay Beautiful');
INSERT INTO Song (songId, title) VALUES (95, 'Should\'ve Said No');
INSERT INTO Song (songId, title) VALUES (96, 'Mary\'s Song (Oh My My My)');
INSERT INTO Song (songId, title) VALUES (97, 'Our Song');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  334, 7, 87);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  175, 7, 88);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  215, 7, 89);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  202, 7, 90);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  241, 7, 91);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  159, 7, 92);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  251, 7, 93);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  238, 7, 94);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  244, 7, 95);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10,  215, 7, 96);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11,  204, 7, 97);


### Taylor Swift - Fearless
INSERT INTO Album (albumId, title, year, number, bandId) values (8, 'Fearless', 2008, 2, 4);
INSERT INTO Song (songId, title) VALUES (98, 'Fearless');
INSERT INTO Song (songId, title) VALUES (99, 'Fifteen');
INSERT INTO Song (songId, title) VALUES (100, 'Love Story');
INSERT INTO Song (songId, title) VALUES (101, 'Hey Stephen');
INSERT INTO Song (songId, title) VALUES (102, 'White Horse');
INSERT INTO Song (songId, title) VALUES (103, 'You Belong With Me');
INSERT INTO Song (songId, title) VALUES (104, 'Breathe');
INSERT INTO Song (songId, title) VALUES (105, 'Tell Me Why');
INSERT INTO Song (songId, title) VALUES (106, 'You\'re Not Sorry');
INSERT INTO Song (songId, title) VALUES (107, 'The Way I Loved You');
INSERT INTO Song (songId, title) VALUES (108, 'Forever And Always');
INSERT INTO Song (songId, title) VALUES (109, 'The Best Day');
INSERT INTO Song (songId, title) VALUES (110, 'Change');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  241, 8, 98);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  294, 8, 99);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  235, 8, 100);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  254, 8, 101);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  234, 8, 102);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  231, 8, 103);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  263, 8, 104);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  200, 8, 105);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  261, 8, 106);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10,  244, 8, 107);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11,  225, 8, 108);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12,  245, 8, 109);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13,  280, 8, 110);

### Taylor Swift - Speak Now
INSERT INTO Album (albumId, title, year, number, bandId) values (9, 'Speak Now', 2010, 3, 4);
INSERT INTO Song (songId, title) VALUES (111, 'Mine');
INSERT INTO Song (songId, title) VALUES (112, 'Sparks Fly');
INSERT INTO Song (songId, title) VALUES (113, 'Back to December');
INSERT INTO Song (songId, title) VALUES (114, 'Speak Now');
INSERT INTO Song (songId, title) VALUES (115, 'Dear John');
INSERT INTO Song (songId, title) VALUES (116, 'Mean');
INSERT INTO Song (songId, title) VALUES (117, 'The Story of Us');
INSERT INTO Song (songId, title) VALUES (118, 'Never Grow Up');
INSERT INTO Song (songId, title) VALUES (119, 'Enchanted');
INSERT INTO Song (songId, title) VALUES (120, 'Better Than Revenge');
INSERT INTO Song (songId, title) VALUES (121, 'Innocent');
INSERT INTO Song (songId, title) VALUES (122, 'Haunted');
INSERT INTO Song (songId, title) VALUES (123, 'Last Kiss');
INSERT INTO Song (songID, title) VALUES (124, 'Long Live');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  230, 9, 111);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  260, 9, 112);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  293, 9, 113);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  240, 9, 114);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  403, 9, 115);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  237, 9, 116);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  265, 9, 117);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  290, 9, 118);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  352, 9, 119);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10,  217, 9, 120);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11,  302, 9, 121);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12,  242, 9, 122);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13,  367, 9, 123);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14,  317, 9, 124);

### Taylor Swift - Red
INSERT INTO Album (albumId, title, year, number, bandId) values (10, 'Red', 2012, 4, 4);
INSERT INTO Song (songId, title) VALUES (125, 'State of Grace');
INSERT INTO Song (songId, title) VALUES (126, 'Red');
INSERT INTO Song (songId, title) VALUES (127, 'Treacherous');
INSERT INTO Song (songId, title) VALUES (128, 'I Knew You Were Trouble');
INSERT INTO Song (songId, title) VALUES (129, 'All Too Well');
INSERT INTO Song (songId, title) VALUES (130, '22');
INSERT INTO Song (songId, title) VALUES (131, 'I Almost Do');
INSERT INTO Song (songId, title) VALUES (132, 'We Are Never Ever Getting Back Together');
INSERT INTO Song (songId, title) VALUES (133, 'Stay Stay Stay');
INSERT INTO Song (songId, title) VALUES (134, 'The Last Time');
INSERT INTO Song (songId, title) VALUES (135, 'Holy Ground');
INSERT INTO Song (songId, title) VALUES (136, 'Sad Beautiful Tragic');
INSERT INTO Song (songId, title) VALUES (137, 'The Lucky One');
INSERT INTO Song (songID, title) VALUES (138, 'Everything Has Changed');
INSERT INTO Song (songID, title) VALUES (139, 'Starlight');
INSERT INTO Song (songID, title) VALUES (140, 'Begin Again');


insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  295, 10, 125);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  223, 10, 126);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  182, 10, 127);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  219, 10, 128);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  329, 10, 129);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  232, 10, 130);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  244, 10, 131);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  193, 10, 132);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  205, 10, 133);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10,  299, 10, 134);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11,  202, 10, 135);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12,  284, 10, 136);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13,  240, 10, 137);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14,  245, 10, 138);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (15,  220, 10, 139);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (16,  237, 10, 140);

### Taylor Swift - 1989
INSERT INTO Album (albumId, title, year, number, bandId) values (11, '1989', 2014, 5, 4);
INSERT INTO Song (songId, title) VALUES (141, 'Welcome to New York');
INSERT INTO Song (songId, title) VALUES (142, 'Blank Space');
INSERT INTO Song (songId, title) VALUES (143, 'Style');
INSERT INTO Song (songId, title) VALUES (144, 'Out of The Woods');
INSERT INTO Song (songId, title) VALUES (145, 'All You Had to Do Was Stay');
INSERT INTO Song (songId, title) VALUES (146, 'Shake It Off');
INSERT INTO Song (songId, title) VALUES (147, 'I Wish You Would');
INSERT INTO Song (songId, title) VALUES (148, 'Bad Blood');
INSERT INTO Song (songId, title) VALUES (149, 'Wildest Dreams');
INSERT INTO Song (songId, title) VALUES (150, 'How You Get the Girl');
INSERT INTO Song (songId, title) VALUES (151, 'This Love');
INSERT INTO Song (songId, title) VALUES (152, 'I Know Places');
INSERT INTO Song (songId, title) VALUES (153, 'Clean');


insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  212, 11, 141);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  231, 11, 142);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  231, 11, 143);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  235, 11, 144);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  193, 11, 145);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  219, 11, 146);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  207, 11, 147);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  211, 11, 148);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  220, 11, 149);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10,  247, 11, 150);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11,  250, 11, 151);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12,  195, 11, 152);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13,  270, 11, 153);

insert into Musician (musicianId,firstName,lastName,country) values (1001, 'John', 'Lennon', 'UK');
insert into Musician (musicianId,firstName,lastName,country) values (1002, 'Paul', 'McCartney', 'UK');
insert into Musician (musicianId,firstName,lastName,country) values (1003, 'George', 'Harrison', 'UK');
insert into Musician (musicianId,firstName,lastName,country) values (1004, 'Ringo', 'Starr', 'UK');
insert into Musician (musicianId,firstName,lastName,country) values (1005, 'P.', 'Best', 'UK');

insert into Band (bandId,name) values (2001,'The Beatles');

insert into BandMember (musicianId,bandId) values (1001,2001);
insert into BandMember (musicianId,bandId) values (1002,2001);
insert into BandMember (musicianId,bandId) values (1003,2001);
insert into BandMember (musicianId,bandId) values (1004,2001);
insert into BandMember (musicianId,bandId) values (1005,2001);

#The Beatles - Sgt.Pepper's Lonely Hearts Club Band

insert into Album (albumId,title,year,number,bandId) values (101,'Sgt. Pepper\'s Lonely Hearts Club Band',1967,8,2001);

insert into Song (songId, title) values (301, 'Sgt. Pepper\'s Lonely Hearts Club Band');
insert into Song (songId, title) values (302, 'With a Little Help from My Friends');
insert into Song (songId, title) values (303, 'Lucy in the Sky with Diamonds');
insert into Song (songId, title) values (304, 'Getting Better');
insert into Song (songId, title) values (305, 'Fixing a Hole');
insert into Song (songId, title) values (306, 'She\'s Leaving Home');
insert into Song (songId, title) values (307, 'Being for the Benefit of Mr. Kite!');
insert into Song (songId, title) values (308, 'Within You Without You');
insert into Song (songId, title) values (309, 'When I\'m Sixty-Four');
insert into Song (songId, title) values (310, 'Lovely Rita');
insert into Song (songId, title) values (311, 'Good Morning Good Morning');
insert into Song (songId, title) values (312, 'Sgt. Pepper\'s Lonely Hearts Club Band (Reprise)');
insert into Song (songId, title) values (313, 'A Day in the Life');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 122, 101,301);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 164, 101,302);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 208, 101,303);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 168, 101,304);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 156, 101,305);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 215, 101,306);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 157, 101,307);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 304, 101,308);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 157, 101,309);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 162, 101,310);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 161, 101,311);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 79, 101, 312);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 339, 101, 313);

#The Beatles - A Hard Day's Night

insert into Album (albumId,title,year,number,bandId) values (102,'A Hard Day\'s Night',1964,3,2001);

insert into Song (songId, title) values (314, 'A Hard Day\'s Night');
insert into Song (songId, title) values (315, 'I Should Have Known Better');
insert into Song (songId, title) values (316, 'If I Fell');
insert into Song (songId, title) values (317, 'I\'m Happy Just to Dance with You');
insert into Song (songId, title) values (318, 'And I Love Her');
insert into Song (songId, title) values (319, 'Tell Me Why');
insert into Song (songId, title) values (320, 'Can\'t Buy Me Love');
insert into Song (songId, title) values (321, 'Any Time at All');
insert into Song (songId, title) values (322, 'I\'ll Cry Instead');
insert into Song (songId, title) values (323, 'Things We Said Today');
insert into Song (songId, title) values (324, 'When I Get Home');
insert into Song (songId, title) values (325, 'You Can\'t Do That');
insert into Song (songId, title) values (326, 'I\'ll Be Back');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 153, 102,314);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 163, 102,315);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 139, 102,316);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 116, 102,317);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 149, 102,318);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 128, 102,319);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 131, 102,320);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 131, 102,321);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 105, 102,322);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 155, 102,323);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 136, 102,324);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 154, 102,325);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 144, 102,326);

#The Beatles - Help!

insert into Album (albumId,title,year,number,bandId) values (103,'Help!',1965,5,2001);

insert into Song (songId, title) values (327, 'Help!');
insert into Song (songId, title) values (328, 'The Night Before');
insert into Song (songId, title) values (329, 'You\'ve Got to Hide Your Love Away');
insert into Song (songId, title) values (330, 'I Need You');
insert into Song (songId, title) values (331, 'Another Girl');
insert into Song (songId, title) values (332, 'You\'re Going to Lose That Girl');
insert into Song (songId, title) values (333, 'Ticket to Ride');
insert into Song (songId, title) values (334, 'Act Naturally');
insert into Song (songId, title) values (335, 'It\'s Only Love');
insert into Song (songId, title) values (336, 'You Like Me Too Much');
insert into Song (songId, title) values (337, 'Tell Me What You See');
insert into Song (songId, title) values (338, 'I\'ve Just Seen a Face');
insert into Song (songId, title) values (339, 'Yesterday');
insert into Song (songId, title) values (340, 'Dizzy Miss Lizzy');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 138, 103,327);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 154, 103,328);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 129, 103,329);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 148, 103,330);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 125, 103,331);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 138, 103,332);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 189, 103,333);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 150, 103,334);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 116, 103,335);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 156, 103,336);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 157, 103,337);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 125, 103,338);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 125, 103,339);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 174, 103,340);

#The Beatles - Abbey Road

insert into Album (albumId,title,year,number,bandId) values (104,'Abbey Road',1969,12,2001);

insert into Song (songId, title) values (341, 'Come Together');
insert into Song (songId, title) values (342, 'Something');
insert into Song (songId, title) values (343, 'Maxwell\'s Silver Hammer');
insert into Song (songId, title) values (344, 'Oh! Darling');
insert into Song (songId, title) values (345, 'Octopus\'s Garden');
insert into Song (songId, title) values (346, 'I Want You (She\'s So Heavy)');
insert into Song (songId, title) values (347, 'Here Comes the Sun');
insert into Song (songId, title) values (348, 'Because');
insert into Song (songId, title) values (349, 'You Never Give Me Your Money');
insert into Song (songId, title) values (350, 'Sun King');
insert into Song (songId, title) values (351, 'Mean Mr. Mustard');
insert into Song (songId, title) values (352, 'Polythene Pam');
insert into Song (songId, title) values (353, 'She Came in Through the Bathroom Window');
insert into Song (songId, title) values (354, 'Golden Slumbers');
insert into Song (songId, title) values (355, 'Carry That Weight');
insert into Song (songId, title) values (356, 'The End');
insert into Song (songId, title) values (357, 'Her Majesty');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 260, 104,341);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 183, 104,342);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 207, 104,343);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 206, 104,344);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 171, 104,345);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 467, 104,346);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 185, 104,347);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 165, 104,348);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 242, 104,349);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 146,104,350);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 66, 104,351);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 72, 104,352);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 117,104,353);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 91, 104,354);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (15, 96, 104,355);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (16, 125,104,356);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (17, 23, 104,357);

#The Beatles - Let It Be

insert into Album (albumId,title,year,number,bandId) values (105,'Let It Be',1970,13,2001);

insert into Song (songId, title) values (358, 'Two of Us');
insert into Song (songId, title) values (359, 'Dig a Pony');
insert into Song (songId, title) values (360, 'Across the Universe');
insert into Song (songId, title) values (361, 'I Me Mine');
insert into Song (songId, title) values (362, 'Dig It');
insert into Song (songId, title) values (363, 'Let It Be');
insert into Song (songId, title) values (364, 'Maggie Mae');
insert into Song (songId, title) values (365, 'I\'ve Got a Feeling');
insert into Song (songId, title) values (366, 'One After 909');
insert into Song (songId, title) values (367, 'The Long and Winding Road');
insert into Song (songId, title) values (368, 'For You Blue');
insert into Song (songId, title) values (369, 'Get Back');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 217, 105,358);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 235, 105,359);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 228, 105,360);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 146, 105,361);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 50, 105,362);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 243, 105,363);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 40, 105,364);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 218, 105,365);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 174, 105,366);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 218, 105,367);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 152, 105,368);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 189, 105,369);

# Vanessa Carlton
insert into Band (name) values ('Vanessa Carlton');

# Vanessa Carlton - Be Not Nobody

insert into Album (title, year, number, bandId) values ('Be Not Nobody', 2002, 1, (select b.bandId from Band b where b.name = 'Vanessa Carlton'));

insert into Song (songID, title) values (10200, 'Ordinary Day');
insert into Song (songID, title) values (10201, 'Unsung');
insert into Song (songID, title) values (10202, 'A Thousand Miles');
insert into Song (songID, title) values (10203, 'Pretty Baby');
insert into Song (songID, title) values (10204, 'Rinse');
insert into Song (songID, title) values (10205, 'Sway');
insert into Song (songID, title) values (10206, 'Paradise');
insert into Song (songID, title) values (10207, 'Prince');
insert into Song (songID, title) values (10208, 'Paint It Black');
insert into Song (songID, title) values (10209, 'Wanted');
insert into Song (songID, title) values (10210, 'Twilight');

insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (09, 270, (select a.albumId from Album a where a.title = 'Be Not Nobody'), 10208);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (8, 249, (select a.albumId from Album a where a.title = 'Be Not Nobody'), 10207);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (7, 290, (select a.albumId from Album a where a.title = 'Be Not Nobody'), 10206);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (6, 237, (select a.albumId from Album a where a.title = 'Be Not Nobody'), 10205);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (5, 271, (select a.albumId from Album a where a.title = 'Be Not Nobody'), 10204);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (4, 235, (select a.albumId from Album a where a.title = 'Be Not Nobody'), 10203);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (3, 237, (select a.albumId from Album a where a.title = 'Be Not Nobody'), 10202);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (2, 260, (select a.albumId from Album a where a.title = 'Be Not Nobody'), 10201);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (11, 289, (select a.albumId from Album a where a.title = 'Be Not Nobody'), 10210);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (10, 235, (select a.albumId from Album a where a.title = 'Be Not Nobody'), 10209);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (1, 238, (select a.albumId from Album a where a.title = 'Be Not Nobody'), 10200);

# Vanessa Carlton - Harmonium

insert into Album (title, year, number, bandId) values ('Harmonium', 2002, 2, (select b.bandId from Band b where b.name = 'Vanessa Carlton'));

insert into Song (songID, title) values (10211, 'White Houses');
insert into Song (songID, title) values (10212, 'Whos to Say');
insert into Song (songID, title) values (10213, 'Annie');
insert into Song (songID, title) values (10214, 'San Francisco');
insert into Song (songID, title) values (10215, 'Afterglow');
insert into Song (songID, title) values (10216, 'Private Radio');
insert into Song (songID, title) values (10217, 'Half a Week Before the Winter');
insert into Song (songID, title) values (10218, 'Cest La Vie');
insert into Song (songID, title) values (10219, 'Papa');
insert into Song (songID, title) values (10220, 'She Floats');

insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (9, 159, (select a.albumId from Album a where a.title = 'Harmonium'), 10219);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (8, 154, (select a.albumId from Album a where a.title = 'Harmonium'), 10218);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (7, 207, (select a.albumId from Album a where a.title = 'Harmonium'), 10217);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (6, 179, (select a.albumId from Album a where a.title = 'Harmonium'), 10216);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (5, 236, (select a.albumId from Album a where a.title = 'Harmonium'), 10215);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (4, 252, (select a.albumId from Album a where a.title = 'Harmonium'), 10214);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (3, 288, (select a.albumId from Album a where a.title = 'Harmonium'), 10213);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (2, 289, (select a.albumId from Album a where a.title = 'Harmonium'), 10212);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (10, 300, (select a.albumId from Album a where a.title = 'Harmonium'), 10220);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (1, 225, (select a.albumId from Album a where a.title = 'Harmonium'), 10211);

# Vanessa Carlton - Heroes & Thieves
insert into Album (title, year, number, bandId) values ('Heroes & Thieves', 2007, 3, (select b.bandId from Band b where b.name = 'Vanessa Carlton'));

insert into Song (songId, title) values (10240, 'Nolita Fairytale');
insert into Song (songId, title) values (10241, 'Hands on Me');
insert into Song (songId, title) values (10242, 'Spring Street');
insert into Song (songId, title) values (10243, 'My Best');
insert into Song (songId, title) values (10244, 'Come Undone');
insert into Song (songId, title) values (10245, 'The One');
insert into Song (songId, title) values (10246, 'Heroes & Thieves');
insert into Song (songId, title) values (10247, 'This Time');
insert into Song (songId, title) values (10248, 'Fools Like Me');
insert into Song (songId, title) values (10249, 'Home');
insert into Song (songId, title) values (10250, 'More than This');

insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (1, 208, (select a.albumId from Album a where a.title = 'Heroes & Thieves'), 10240);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (2, 181, (select a.albumId from Album a where a.title = 'Heroes & Thieves'), 10241);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (3, 250, (select a.albumId from Album a where a.title = 'Heroes & Thieves'), 10242);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (4, 180, (select a.albumId from Album a where a.title = 'Heroes & Thieves'), 10243);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (5, 275, (select a.albumId from Album a where a.title = 'Heroes & Thieves'), 10244);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (6, 245, (select a.albumId from Album a where a.title = 'Heroes & Thieves'), 10245);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (7, 227, (select a.albumId from Album a where a.title = 'Heroes & Thieves'), 10246);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (8, 229, (select a.albumId from Album a where a.title = 'Heroes & Thieves'), 10247);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (9, 190, (select a.albumId from Album a where a.title = 'Heroes & Thieves'), 10248);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (10, 338, (select a.albumId from Album a where a.title = 'Heroes & Thieves'), 10249);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (11, 288, (select a.albumId from Album a where a.title = 'Heroes & Thieves'), 10250);

# Vanessa Carlton - Rabbits on the Run

insert into Album (title, year, number, bandId) values ('Rabbits on the Run', 2011, 4, (select b.bandId from Band b where b.name = 'Vanessa Carlton'));

insert into Song (songID, title) values (10221, 'Carousel');
insert into Song (songID, title) values (10222, 'I Dont Want to Be a Bride');
insert into Song (songID, title) values (10223, 'London');
insert into Song (songID, title) values (10224, 'Fairweather Friend');
insert into Song (songID, title) values (10225, 'Hear the Bells');
insert into Song (songID, title) values (10226, 'Dear California');
insert into Song (songID, title) values (10227, 'Tall Tales for Spring');
insert into Song (songID, title) values (10228, 'Get Good');
insert into Song (songID, title) values (10229, 'The Marching Line');
insert into Song (songID, title) values (10230, 'In the End');

insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (9, 270, (select a.albumId from Album a where a.title = 'Rabbits on the Run'), 10229);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (8, 234, (select a.albumId from Album a where a.title = 'Rabbits on the Run'), 10228);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (7, 268, (select a.albumId from Album a where a.title = 'Rabbits on the Run'), 10227);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (6, 199, (select a.albumId from Album a where a.title = 'Rabbits on the Run'), 10226);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (5, 224, (select a.albumId from Album a where a.title = 'Rabbits on the Run'), 10225);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (4, 235, (select a.albumId from Album a where a.title = 'Rabbits on the Run'), 10224);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (3, 259, (select a.albumId from Album a where a.title = 'Rabbits on the Run'), 10223);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (2, 241, (select a.albumId from Album a where a.title = 'Rabbits on the Run'), 10222);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (10, 173, (select a.albumId from Album a where a.title = 'Rabbits on the Run'), 10230);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (1, 196, (select a.albumId from Album a where a.title = 'Rabbits on the Run'), 10221);

# Vanessa Carlton - Liberman

insert into Album (title, year, number, bandId) values ('Liberman', 2015, 5, (select b.bandId from Band b where b.name = 'Vanessa Carlton'));

insert into Song (songID, title) values (10231, 'Take It Easy');
insert into Song (songID, title) values (10232, 'Willows');
insert into Song (songID, title) values (10233, 'House of Seven Swords');
insert into Song (songID, title) values (10234, 'Operator');
insert into Song (songID, title) values (10235, 'Blue Pool');
insert into Song (songID, title) values (10236, 'Nothing Where Something Used to Be');
insert into Song (songID, title) values (10237, 'Matter of Time');
insert into Song (songID, title) values (10238, 'Unlock the Lock');
insert into Song (songID, title) values (10239, 'River');
insert into Song (songID, title) values (10251, 'Ascension');

insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (9, 213, (select a.albumId from Album a where a.title = 'Liberman'), 10239);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (8, 191, (select a.albumId from Album a where a.title = 'Liberman'), 10238);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (7, 196, (select a.albumId from Album a where a.title = 'Liberman'), 10237);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (6, 241, (select a.albumId from Album a where a.title = 'Liberman'), 10236);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (5, 196, (select a.albumId from Album a where a.title = 'Liberman'), 10235);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (4, 196, (select a.albumId from Album a where a.title = 'Liberman'), 10234);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (3, 223, (select a.albumId from Album a where a.title = 'Liberman'), 10233);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (2, 173, (select a.albumId from Album a where a.title = 'Liberman'), 10232);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (10, 157, (select a.albumId from Album a where a.title = 'Liberman'), 10240);
insert into AlbumSong (trackNumber, trackLength, albumId, songId) values (1, 332, (select a.albumId from Album a where a.title = 'Liberman'), 10231);


# Coldplay

insert into Musician (musicianId,firstName,lastName,country) values (1301, 'Chris', 'Martin', 'England');
insert into Musician (musicianId,firstName,lastName,country) values (1302, 'Jonny', 'Buckland', 'England');
insert into Musician (musicianId,firstName,lastName,country) values (1303, 'Guy', 'Berryman', 'Scotland');
insert into Musician (musicianId,firstName,lastName,country) values (1304, 'Will', 'Champion', 'England');

insert into Band (bandId,name) values (1301,'Coldplay');

insert into BandMember (musicianId,bandId) values (1301, 1301);
insert into BandMember (musicianId,bandId) values (1302, 1301);
insert into BandMember (musicianId,bandId) values (1303, 1301);
insert into BandMember (musicianId,bandId) values (1304, 1301);

# Coldplay - Parachutes

insert into Album (albumId,title,year,number,bandId) values (1301,'Parachutes', 2000, 1, 1301);

insert into Song (songId, title) values (1301, 'Don\'t Panic');
insert into Song (songId, title) values (1302, 'Shiver');
insert into Song (songId, title) values (1303, 'Spies');
insert into Song (songId, title) values (1304, 'Sparks');
insert into Song (songId, title) values (1305, 'Yellow');
insert into Song (songId, title) values (1306, 'Trouble');
insert into Song (songId, title) values (1307, 'Parachutes');
insert into Song (songId, title) values (1308, 'High Speed');
insert into Song (songId, title) values (1309, 'We Never Change');
insert into Song (songId, title) values (1310, 'Everything\'s Not Lost');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 137, 1301, 1301);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 300, 1301, 1302);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 319, 1301, 1303);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 227, 1301, 1304);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 269, 1301, 1305);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 271, 1301, 1306);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 46, 1301, 1307);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 254, 1301, 1308);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 249, 1301, 1309);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 437, 1301, 1310);

# Coldplay - A Rush of Blood to the Head

insert into Album (albumId,title,year,number,bandId) values (1302,'A Rush of Blood to the Head', 2002, 2, 1301);

insert into Song (songId, title) values (1311, 'Politik');
insert into Song (songId, title) values (1312, 'In My Place');
insert into Song (songId, title) values (1313, 'God Put a Smile upon Your Face');
insert into Song (songId, title) values (1314, 'The Scientist');
insert into Song (songId, title) values (1315, 'Clocks');
insert into Song (songId, title) values (1316, 'Daylight');
insert into Song (songId, title) values (1317, 'Green Eyes');
insert into Song (songId, title) values (1318, 'Warning Sign');
insert into Song (songId, title) values (1319, 'A Whisper');
insert into Song (songId, title) values (1320, 'A Rush of Blood to the Head');
insert into Song (songId, title) values (1321, 'Amsterdam');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 318, 1302, 1311);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 228, 1302, 1312);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 297, 1302, 1313);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 309, 1302, 1314);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 307, 1302, 1315);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 327, 1302, 1316);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 223, 1302, 1317);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 331, 1302, 1318);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 238, 1302, 1319);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 351, 1302, 1320);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 319, 1302, 1321);

# Coldplay - X&Y

insert into Album (albumId,title,year,number,bandId) values (1303,'X&Y', 2005, 3, 1301);

insert into Song (songId, title) values (1322, 'Square One');
insert into Song (songId, title) values (1323, 'What If');
insert into Song (songId, title) values (1324, 'White Shadows');
insert into Song (songId, title) values (1325, 'Fix You');
insert into Song (songId, title) values (1326, 'Talk');
insert into Song (songId, title) values (1327, 'X&Y');
insert into Song (songId, title) values (1328, 'Speed of Sound');
insert into Song (songId, title) values (1329, 'A Message');
insert into Song (songId, title) values (1330, 'Low');
insert into Song (songId, title) values (1331, 'The Hardest Part');
insert into Song (songId, title) values (1332, 'Swallowed in the Sea');
insert into Song (songId, title) values (1333, 'Twisted Logic');
insert into Song (songId, title) values (1334, 'Til Kingdom Come');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 287, 1303, 1322);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 297, 1303, 1323);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 328, 1303, 1324);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 294, 1303, 1325);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 311, 1303, 1326);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 274, 1303, 1327);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 288, 1303, 1328);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 285, 1303, 1329);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 332, 1303, 1330);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 265, 1303, 1331);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 238, 1303, 1332);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 301, 1303, 1333);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 250, 1303, 1334);

# Coldplay - Viva la Vida or Death and All His Friends

insert into Album (albumId,title,year,number,bandId) values (1304,'Viva la Vida or Death and All His Friends', 2008, 4, 1301);

insert into Song (songId, title) values (1335, 'Life In Technicolor');
insert into Song (songId, title) values (1336, 'Cemetaries of London');
insert into Song (songId, title) values (1337, 'Lost!');
insert into Song (songId, title) values (1338, '42');
insert into Song (songId, title) values (1339, 'Lovers in Japan / Reign of Love');
insert into Song (songId, title) values (1340, 'Yes');
insert into Song (songId, title) values (1341, 'Viva la Vida');
insert into Song (songId, title) values (1342, 'Violet Hill');
insert into Song (songId, title) values (1343, 'Strawberry Swing');
insert into Song (songId, title) values (1344, 'Death and All His Friends');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 150, 1304, 1335);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 201, 1304, 1336);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 235, 1304, 1337);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 237, 1304, 1338);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 411, 1304, 1339);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 426, 1304, 1340);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 241, 1304, 1341);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 222, 1304, 1342);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 249, 1304, 1343);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 378, 1304, 1344);

# Coldplay - Mylo Xyloto

insert into Album (albumId,title,year,number,bandId) values (1305,'Mylo Xyloto', 2011, 5, 1301);

insert into Song (songId, title) values (1345, 'Mylo Xyloto');
insert into Song (songId, title) values (1346, 'Hurts Like Heaven');
insert into Song (songId, title) values (1347, 'Paradise');
insert into Song (songId, title) values (1348, 'Charlie Brown');
insert into Song (songId, title) values (1349, 'Us Against the World');
insert into Song (songId, title) values (1350, 'M.M.I.X');
insert into Song (songId, title) values (1351, 'Every Teardrop is a Waterfall');
insert into Song (songId, title) values (1352, 'Major Minus');
insert into Song (songId, title) values (1353, 'U.F.O');
insert into Song (songId, title) values (1354, 'Princess of China');
insert into Song (songId, title) values (1355, 'Up in Flames');
insert into Song (songId, title) values (1356, 'A Hopeful Transmission');
insert into Song (songId, title) values (1357, 'Don\'t Let It Break Your Heart');
insert into Song (songId, title) values (1358, 'Up with the Birds');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 43, 1305, 1345);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 242, 1305, 1346);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 277, 1305, 1347);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 285, 1305, 1348);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 239, 1305, 1349);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 49, 1305, 1350);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 240, 1305, 1351);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 210, 1305, 1352);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 137, 1305, 1353);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 239, 1305, 1354);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 193, 1305, 1355);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 33, 1305, 1356);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 234, 1305, 1357);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 225, 1305, 1358);

insert into Musician (musicianId,firstName,lastName,country) values (506, 'Brendan', 'Urie', 'USA');

insert into Musician (musicianId,firstName,lastName,country) values (507, 'Joe', 'Trohman', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (508, 'Pete', 'Wentz', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (509, 'Patrick', 'Stump', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (510, 'Andy', 'Hurley', 'USA');

insert into Musician (musicianId,firstName,lastName,country) values (511, 'Daniel', 'Smith', 'UK');
insert into Musician (musicianId,firstName,lastName,country) values (512, 'Kyle', 'Simmons', 'UK');
insert into Musician (musicianId,firstName,lastName,country) values (513, 'Will', 'Farquarson', 'UK');
insert into Musician (musicianId,firstName,lastName,country) values (514, 'Chris', 'Wood', 'UK');


insert into Band (bandId,name) values (309,'Panic! at the Disco');
insert into Band (bandID,name) values (310,'Fall Out Boy');
insert into Band (bandID,name) values (311,'Bastille');

#Panic! at the Disco
insert into BandMember (musicianId,bandId) values (506,309);

#Fall Out Boy
insert into BandMember (musicianId,bandId) values (507,310);
insert into BandMember (musicianId,bandId) values (508,310);
insert into BandMember (musicianId,bandId) values (509,310);
insert into BandMember (musicianId,bandId) values (510,310);

#Bastille
insert into BandMember (musicianId,bandId) values (511,311);
insert into BandMember (musicianId,bandId) values (512,311);
insert into BandMember (musicianId,bandId) values (513,311);
insert into BandMember (musicianId,bandId) values (514,311);

###Panic! at the Disco (5) - Death of a Bachelor

insert into Album (albumId,title,year,number,bandId) values (254,'Death of a Bachelor',2016,5,309);

insert into Song (songId, title) values (400, 'Victorious');
insert into Song (songId, title) values (401, 'Don''t Threaten Me with a Good Time');
insert into Song (songId, title) values (402, 'Hallelujah');
insert into Song (songId, title) values (403, 'Emperor''s New Clothes');
insert into Song (songId, title) values (404, 'Death of a Bachelor');
insert into Song (songId, title) values (405, 'Crazy=Genius');
insert into Song (songId, title) values (406, 'LA Devotee');
insert into Song (songId, title) values (407, 'Golden Days');
insert into Song (songId, title) values (408, 'The Good, the Bad and the Dirty');
insert into Song (songId, title) values (409, 'House of Memories');
insert into Song (songId, title) values (410, 'Impossible Year');


insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  179, 254, 400);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  213, 254, 401);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  181, 254, 402);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  159, 254, 403);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  204, 254, 404);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  198, 254, 405);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  197, 254, 406);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  254, 254, 407);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  172, 254, 408);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 209, 254, 409);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 203, 254, 410);

###Panic! at the Disco (3) - Vices & Virtues

insert into Album (albumId,title,year,number,bandId) values (255,'Vices & Virtues',2011,3,309);

insert into Song (songId, title) values (411, 'The Balad of Mona Lisa');
insert into Song (songId, title) values (412, 'Let''s Kill Tonight');
insert into Song (songId, title) values (413, 'Hurricane');
insert into Song (songId, title) values (414, 'Memories');
insert into Song (songId, title) values (415, 'Trade Mistakes');
insert into Song (songId, title) values (416, 'Ready to Go (Get Me Out of My Mind');
insert into Song (songId, title) values (417, 'Always');
insert into Song (songId, title) values (418, 'The Calendar');
insert into Song (songId, title) values (419, 'Sarah Smiles');
insert into Song (songId, title) values (420, 'Nearly Witches (Ever Since We Met...)');
insert into Song (songId, title) values (421, 'Stall Me');
insert into Song (songId, title) values (422, 'Oh Glory(Demo)');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  227, 255, 411);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  214, 255, 412);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  265, 255, 413);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  206, 255, 414);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  216, 255, 415);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  217, 255, 416);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  154, 255, 417);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  283, 255, 418);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  213, 255, 419);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 256, 255, 420);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 190, 255, 421);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 183, 255, 422);

###Fall Out Boy (5) - Save Rock and Roll

insert into Album (albumId,title,year,number,bandId) values (256,'Save Rock and Roll',2013,5,310);

insert into Song (songId, title) values (423, 'The Phoenix');
insert into Song (songId, title) values (424, 'My Song Know What You Did in the Dark (Light Em Up)');
insert into Song (songId, title) values (425, 'Alone Together');
insert into Song (songId, title) values (426, 'Where Did the Party Go');
insert into Song (songId, title) values (427, 'Just One Yesterday (feat. Foxes)');
insert into Song (songId, title) values (428, 'The Mighty Fall (feat. Big Sean)');
insert into Song (songId, title) values (429, 'Miss Missing You');
insert into Song (songId, title) values (430, 'Death Valley');
insert into Song (songId, title) values (431, 'Young Volcanoes');
insert into Song (songId, title) values (432, 'Rat a Tat (feat. Courtney Love');
insert into Song (songId, title) values (433, 'Save Rock and Roll (feat. Elton John)');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  245, 256, 423);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  187, 256, 424);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  203, 256, 425);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  243, 256, 426);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  245, 256, 427);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  213, 256, 428);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  211, 256, 429);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  227, 256, 430);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  205, 256, 431);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 243, 256, 432);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 281, 256, 433);

###Fall Out Boy (6) - American Beauty/American Psycho

insert into Album (albumId,title,year,number,bandId) values (257,'American Beauty/American Psycho',2015,6,310);

insert into Song (songId, title) values (434, 'Irresistible');
insert into Song (songId, title) values (435, 'American Beauty/American Psycho');
insert into Song (songId, title) values (436, 'Centuries');
insert into Song (songId, title) values (437, 'The Kids Aren''t Right');
insert into Song (songId, title) values (438, 'Uma Thurman');
insert into Song (songId, title) values (439, 'Jet Pack Blues');
insert into Song (songId, title) values (440, 'Novocaine');
insert into Song (songId, title) values (441, 'Fourth of July');
insert into Song (songId, title) values (442, 'Favorite Record');
insert into Song (songId, title) values (443, 'Immortals');
insert into Song (songId, title) values (444, 'Twin Skeleton''s (Hotel in NYC)');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  207, 257, 434);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  196, 257, 435);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  228, 257, 436);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  261, 257, 437);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  212, 257, 438);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  179, 257, 439);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  229, 257, 440);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  224, 257, 441);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  203, 257, 442);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 189, 257, 443);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 220, 257, 444);

###Bastille (1) - Bad Blood

insert into Album (albumId,title,year,number,bandId) values (258,'Bad Blood',2013,1,311);

insert into Song (songId, title) values (445, 'Pompeii');
insert into Song (songId, title) values (446, 'Things We Lost in the Fire');
insert into Song (songId, title) values (447, 'Bad Blood');
insert into Song (songId, title) values (448, 'Overjoyed');
insert into Song (songId, title) values (449, 'These Streets');
insert into Song (songId, title) values (450, 'Weight of Living, Pt. 2');
insert into Song (songId, title) values (451, 'Icarus');
insert into Song (songId, title) values (452, 'Oblivion');
insert into Song (songId, title) values (453, 'Flaws');
insert into Song (songId, title) values (454, 'Daniel in the Den');
insert into Song (songId, title) values (455, 'Laura Palmer');
insert into Song (songId, title) values (456, 'Get Home');
insert into Song (songId, title) values (457, 'The Silence');
insert into Song (songId, title) values (458, 'Weight of Living, Pt. 1');
insert into Song (songId, title) values (459, 'Laughter Lines');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  214, 258, 445);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  241, 258, 446);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  213, 258, 447);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  206, 258, 448);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  175, 258, 449);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  175, 258, 450);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  225, 258, 451);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  196, 258, 452);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  219, 258, 453);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 189, 258, 454);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 186, 258, 455);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 191, 258, 456);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 231, 258, 457);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 206, 258, 458);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (15, 244, 258, 459);

insert into Musician (musicianId,firstName,lastName,country) values (803, 'Neil', 'Peart', 'CA');
insert into Musician (musicianId,firstName,lastName,country) values (804, 'Geddy', 'Lee', 'CA');
insert into Musician (musicianId,firstName,lastName,country) values (805, 'Alex ', 'Lifeson', 'CA');
insert into Musician (musicianId,firstName,lastName,country) values (806, 'Micheal', 'Jackson', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (807, 'Randy', 'Jackson', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (808, 'Jermaine', 'Jackson ', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (809, 'Damon ', 'Albarn', 'UK');
insert into Musician (musicianId,firstName,lastName,country) values (810, 'Graham  ', 'Coxon', 'UK');
insert into Musician (musicianId,firstName,lastName,country) values (811, 'Alex ', 'James', 'UK');
insert into Musician (musicianId,firstName,lastName,country) values (812, 'Adam ', 'Levine', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (813, 'James  ', 'Valentine', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (814, 'Jesse ', 'Carmichael', 'USA');


insert into Band (bandId,name) values (51,'Rush');
insert into Band (bandId,name) values (52,'Jackson 5');
insert into Band (bandId,name) values (53,'blur');
insert into Band (bandId,name) values (54,'Maroon 5');


#Rush
insert into BandMember (musicianId,bandId) values (803,51);
insert into BandMember (musicianId,bandId) values (804,51);
insert into BandMember (musicianId,bandId) values (805,51);
#Jackson5
insert into BandMember (musicianId,bandId) values (806,52);
insert into BandMember (musicianId,bandId) values (807,52);
insert into BandMember (musicianId,bandId) values (808,52);
#blur 
insert into BandMember (musicianId,bandId) values (809,53);
insert into BandMember (musicianId,bandId) values (810,53);
insert into BandMember (musicianId,bandId) values (811,53);
#Maroon 5
insert into BandMember (musicianId,bandId) values (812,54);
insert into BandMember (musicianId,bandId) values (813,54);
insert into BandMember (musicianId,bandId) values (814,54);



###Rush band -  Hemispheres Album

insert into Album (albumId,title,year,number,bandId) values (301,'Hemispheres',1978,1,51);

insert into Song (songId, title) values (511, 'Cygnus X-1');
insert into Song (songId, title) values (512, 'Circumstances');
insert into Song (songId, title) values (513, 'The Trees');
insert into Song (songId, title) values (514, 'La villa strangiato');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 1808, 301, 511);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  341, 301, 512);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  444, 301, 513);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  936, 301, 514);


###Jackson 5 -Third Album & Maybe Tomorrow

insert into Album (albumId,title,year,number,bandId) values (302,'Third Album & Maybe Tomorrow',2001,2,52);

insert into Song (songId, title) values (515, 'Third Album: I\'ll Be There');
insert into Song (songId, title) values (516, 'Third Album: Ready or Not (Here I Come)');
insert into Song (songId, title) values (517, 'Third Album: oh How Happy');
insert into Song (songId, title) values (518, 'Third Album: Bridge Over Troubled Water');
insert into Song (songId, title) values (519, 'Third Album: Can I see you in the morning');
insert into Song (songId, title) values (520, 'Third Album: Goin\' Back to Indiana');
insert into Song (songId, title) values (521, 'Third Album: How Funky is Your Chicken?');
insert into Song (songId, title) values (522, 'Third Album: Mama\'s Pearl');
insert into Song (songId, title) values (523, 'Third Album: Reach In');
insert into Song (songId, title) values (524, 'Third Album: The Love I Saw in You Was Just a Mirage');
insert into Song (songId, title) values (525, 'Third Album: Darling Dear');
insert into Song (songId, title) values (526, 'Maybe Tomorrow: Maybe Tomorrow');
insert into Song (songId, title) values (527, 'Maybe Tomorrow: She\'s Good ');
insert into Song (songId, title) values (528, 'Maybe Tomorrow: Never Can Say Goodbye');
insert into Song (songId, title) values (529, 'Maybe Tomorrow: The Wall');
insert into Song (songId, title) values (530, 'Maybe Tomorrow: Petals');
insert into Song (songId, title) values (531, 'Maybe Tomorrow: 16 Candles');
insert into Song (songId, title) values (532, 'Maybe Tomorrow: (We\'ve Got) Blue Skies');
insert into Song (songId, title) values (533, 'Maybe Tomorrow: My Little Baby');
insert into Song (songId, title) values (534, 'Maybe Tomorrow: It\'s Great to be Here');
insert into Song (songId, title) values (535, 'Maybe Tomorrow: Honey Chile');
insert into Song (songId, title) values (536, 'Maybe Tomorrow: I Will Find a Way');
insert into Song (songId, title) values (537, 'Bonus Track: Sugar Daddy');
insert into Song (songId, title) values (538, 'Bonus Track: I\'m So Happy');


insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  345, 302, 515);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  287, 302, 516);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  203, 302, 517);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  543, 302, 518);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  345, 302, 519);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  313, 302, 520);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  211, 302, 521);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  327, 302, 522);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  305, 302, 523);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 443, 302, 524);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 281, 302, 525);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 445, 302, 526);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 387, 302, 527);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 303, 302, 528);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (15, 343, 302, 529);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (16, 245, 302, 530);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (17, 213, 302, 531);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (18, 311, 302, 532);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (19, 227, 302, 533);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (20, 305, 302, 534);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (21, 243, 302, 535);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (22, 281, 302, 536);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (23, 227, 302, 537);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (24, 205, 302, 538);

###Blur - The Magic Whip

insert into Album (albumId,title,year,number,bandId) values (303,'The Magic Whip',2015,1,53);

insert into Song (songId, title) values (539, 'Lonesome Street');
insert into Song (songId, title) values (540, 'New World Towers');
insert into Song (songId, title) values (541, 'Go Out');
insert into Song (songId, title) values (542, 'Ice Cream Man');
insert into Song (songId, title) values (543, 'Thought I Was A Spaceman');
insert into Song (songId, title) values (544, 'I broadcast');
insert into Song (songId, title) values (545, 'My Terracotta Heart');
insert into Song (songId, title) values (546, 'There are too many of us');
insert into Song (songId, title) values (547, 'Ghost ship');
insert into Song (songId, title) values (548, 'Pyongyang');
insert into Song (songId, title) values (549, 'Ong ong');
insert into Song (songId, title) values (550, 'Mirrorball');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  407, 303, 539);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  496, 303, 540);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  428, 303, 541);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  361, 303, 542);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  612, 303, 543);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  379, 303, 544);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  429, 303, 545);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  424, 303, 546);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  403, 303, 547);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 589, 303, 548);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 320, 303, 549);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 320, 303, 550);

###Maroon 5 - Live from SoHo

insert into Album (albumId,title,year,number,bandId) values (304,'Live from SoHo',2008,1,54);

insert into Song (songId, title) values (551, 'If I Never See Your Face Again');
insert into Song (songId, title) values (552, 'Makes Me Wonder');
insert into Song (songId, title) values (553, 'Little of Your Time');
insert into Song (songId, title) values (554, 'Won\'t Go Home Without You');
insert into Song (songId, title) values (555, 'Wake Up Call');
insert into Song (songId, title) values (556, 'Nothing Lasts Forever');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  414, 304, 551);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  441, 304, 552);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  213, 304, 553);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  406, 304, 554);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  375, 304, 555);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  475, 304, 556);

insert into Musician (musicianId,firstName,lastName,country) values (150, 'Edward', 'Ma', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (151, 'Justin', 'Boreta', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (152, 'Josh', 'Mayer', 'USA');

insert into Musician (musicianId,firstName,lastName,country) values (153, 'Harrison', 'Mills', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (154, 'Clayton', 'Knight', 'USA');

insert into Band (bandId,name) values (150,'The Glitch Mob');
insert into Band (bandId,name) values (151,'ODESZA');

# The Glitch Mob
insert into BandMember (musicianId,bandId) values (150,150);
insert into BandMember (musicianId,bandId) values (151,150);
insert into BandMember (musicianId,bandId) values (152,150);

# ODESZA
insert into BandMember (musicianId,bandId) values (153,151);
insert into BandMember (musicianId,bandId) values (154,151);

### The Glitch Mob - Drink the Sea

INSERT INTO Album (albumId,title,year,number,bandId) values (150,'Drink the Sea',2010,1,150);

INSERT INTO Song (songId, title) VALUES (3150, 'Animus Vox');
INSERT INTO Song (songId, title) VALUES (3151, 'Bad Wings');
INSERT INTO Song (songId, title) VALUES (3152, 'How to Be Eaten by a Woman');
INSERT INTO Song (songId, title) VALUES (3153, 'A Dream Within a Dream');
INSERT INTO Song (songId, title) VALUES (3154, 'Fistful of Silence');
INSERT INTO Song (songId, title) VALUES (3155, 'Between Two Points');
INSERT INTO Song (songId, title) VALUES (3156, 'We Swarm');
INSERT INTO Song (songId, title) VALUES (3157, 'Drive It Like You Stole It');
INSERT INTO Song (songId, title) VALUES (3158, 'Fortune Days');
INSERT INTO Song (songId, title) VALUES (3159, 'Starve the Ego, Feed the Soul');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 645, 150,3150);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 640, 150,3151);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 600, 150,3152);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 524, 150,3153);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 511, 150,3154);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 537, 150,3155);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 554, 150,3156);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 555, 150,3157);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 623, 150,3158);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 549, 150,3159);

### The Glitch Mob - Love Death Immortality

insert into Album (albumId,title,year,number,bandId) values (151,'Love Death Immortality',2014,3,150);

insert into Song (songId, title) values (160, 'Mind of a Beast');
insert into Song (songId, title) values (161, 'Our Demons');
insert into Song (songId, title) values (162, 'Skullclub');
insert into Song (songId, title) values (163, 'Becoming Harmonious');
insert into Song (songId, title) values (164, 'Can\'t Kill Us');
insert into Song (songId, title) values (165, 'I Need My Memory Back');
insert into Song (songId, title) values (166, 'Skytoucher');
insert into Song (songId, title) values (167, 'Fly by Night Only');
insert into Song (songId, title) values (168, 'Carry the Sun');
insert into Song (songId, title) values (169, 'Beauty of the Unhidden Heart');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 416, 151, 160);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 516, 151, 161);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 548, 151, 162);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 440, 151, 163);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 440, 151, 164);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 547, 151, 165);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 608, 151, 166);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 446, 151, 167);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 456, 151, 168);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 429, 151, 169);


### ODESZA - Summer's Gone

insert into Album (albumId,title,year,number,bandId) values (152,'Summer\'s Gone',2012,1,151);

insert into Song (songId, title) values (170, 'Intro');
insert into Song (songId, title) values (171, 'How Did I Get Here');
insert into Song (songId, title) values (172, 'I Want You');
insert into Song (songId, title) values (173, 'Above the Middle');
insert into Song (songId, title) values (174, 'We Were Young');
insert into Song (songId, title) values (175, 'Today');
insert into Song (songId, title) values (176, 'Tuytus');
insert into Song (songId, title) values (177, 'Rely');
insert into Song (songId, title) values (178, 'Hey Now');
insert into Song (songId, title) values (179, 'If You Don\'t (feat. Cumulus)');
insert into Song (songId, title) values (180, 'Don\'t Stop');
insert into Song (songId, title) values (181, 'IPlayYouListen');
insert into Song (songId, title) values (182, 'Nomadic Firs - Cover Bombs (ODESZA Edit)');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 34, 152, 170);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 211, 152, 171);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 242, 152, 172);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 303, 152, 173);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 312, 152, 174);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 415, 152, 175);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 316, 152, 176);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 312, 152, 177);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 228, 152, 178);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 307, 152, 179);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 248, 152, 180);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 453, 152, 181);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 330, 152, 182);

### ODESZA - My Friends Never Die

insert into Album (albumId,title,year,number,bandId) values (153,'My Friends Never Die',2013,2,151);

insert into Song (songId, title) values (183, 'My Friends Never Die');
insert into Song (songId, title) values (184, 'If there\'s Time');
insert into Song (songId, title) values (185, 'Without You');
insert into Song (songId, title) values (186, 'Home');
insert into Song (songId, title) values (187, 'Keep Her Close');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  252, 152, 183);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  316, 152, 184);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  304, 152, 185);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  228, 152, 186);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  334, 152, 187);

### ODESZA - In Return

insert into Album (albumId,title,year,number,bandId) values (154,'In Return',2014,3,151);

insert into Song (songId, title) values (188, 'Always This Late');
insert into Song (songId, title) values (189, 'Say My Name (feat. Zyra)');
insert into Song (songId, title) values (190, 'Bloom');
insert into Song (songId, title) values (191, 'All We Need (feat. Shy Girls)');
insert into Song (songId, title) values (192, 'Sundara');
insert into Song (songId, title) values (193, 'White Lies (feat. Jenni Potts)');
insert into Song (songId, title) values (194, 'Kusanagi');
insert into Song (songId, title) values (195, 'Echoes (feat. Py)');
insert into Song (songId, title) values (196, 'It\'s Only');
insert into Song (songId, title) values (197, 'Koto');
insert into Song (songId, title) values (198, 'Memories That You Call');
insert into Song (songId, title) values (199, 'Sun Models (feat. Madelyn Grant)');
insert into Song (songId, title) values (200, 'For Us (feat. Brianna Marela)');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  241, 154, 188);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  423, 154, 189);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  316, 154, 190);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  331, 154, 191);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  216, 154, 192);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  437, 154, 193);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  328, 154, 194);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  424, 154, 195);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  428, 154, 196);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 314, 154, 197);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 408, 154, 198);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 240, 154, 199);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 549, 154, 200);


#needtobreathe band members
insert into Musician (musicianId,firstName,lastName,country) values (1050, 'Bear', 'Rinehart', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (1051, 'Bo', 'Rinehart', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (1052, 'Seth', 'Bolt', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (1053, 'Joe', 'Stillwell', 'USA');

#mother mother band members
insert into Musician (musicianId,firstName,lastName,country) values (1054, 'Ryan', 'Guldemond', 'Canada');
insert into Musician (musicianId,firstName,lastName,country) values (1055, 'Molly', 'Guldemond', 'Canada');
insert into Musician (musicianId,firstName,lastName,country) values (1056, 'Jasmin', 'Parker', 'Canada');
insert into Musician (musicianId,firstName,lastName,country) values (1057, 'Ali', 'Siadat', 'Canada');
insert into Musician (musicianId,firstName,lastName,country) values (1058, 'Jeremy', 'Page', 'Canada');

insert into Band (bandId,name) values (850,'Needtobreathe');
insert into Band (bandId,name) values (851,'Mother Mother');

# Needtobreathe
insert into BandMember (musicianId,bandId) values (1050,850);
insert into BandMember (musicianId,bandId) values (1051,850);
insert into BandMember (musicianId,bandId) values (1052,850);
insert into BandMember (musicianId,bandId) values (1053,850);

# Mother Mother
insert into BandMember (musicianId,bandId) values (1054,851);
insert into BandMember (musicianId,bandId) values (1055,851);
insert into BandMember (musicianId,bandId) values (1056,851);
insert into BandMember (musicianId,bandId) values (1057,851);
insert into BandMember (musicianId,bandId) values (1058,851);

### The outsiders - Needtobreathe, complete

INSERT INTO Album (albumId,title,year,number,bandId) values (9000,'The Outsiders',2009,3,850);

INSERT INTO Song (songId, title) VALUES (6000, 'The Outsiders');
INSERT INTO Song (songId, title) VALUES (6001, 'Valley of Tomorrow');
INSERT INTO Song (songId, title) VALUES (6002, 'Through Smoke');
INSERT INTO Song (songId, title) VALUES (6003, 'Lay \'em Down');
INSERT INTO Song (songId, title) VALUES (6004, 'What You\'ve Done to Me');
INSERT INTO Song (songId, title) VALUES (6005, 'Hurricane');
INSERT INTO Song (songId, title) VALUES (6006, 'These Hard Times');
INSERT INTO Song (songId, title) VALUES (6007, 'Stones Under Rushing Water');
INSERT INTO Song (songId, title) VALUES (6008, 'Prisoner');
INSERT INTO Song (songId, title) VALUES (6009, 'Won\'t Turn Back');
INSERT INTO Song (songId, title) VALUES (6010, 'Girl Named Tennessee');
INSERT INTO Song (songId, title) VALUES (6011, 'Something Beautiful');
INSERT INTO Song (songId, title) VALUES (6012, 'Garden');
INSERT INTO Song (songId, title) VALUES (6013, 'Let Us Love');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 269, 9000,6000);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 242, 9000,6001);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 192, 9000,6002);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 191, 9000,6003);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 201, 9000,6004);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 247, 9000,6005);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 219, 9000,6006);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 204, 9000,6007);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 195, 9000,6008);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 189, 9000,6009);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 178, 9000,6010);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 222, 9000,6011);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 221, 9000,6012);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 222, 9000,6013);

### The Reckoning - Needtobreathe(2) complete

insert into Album (albumId,title,year,number,bandId) values (9001,'The Reckoning',2011,4,850);

insert into Song (songId, title) values (6014, 'Oohs and Ahhs');
insert into Song (songId, title) values (6015, 'White Fences');
insert into Song (songId, title) values (6016, 'Drive All Night');
insert into Song (songId, title) values (6017, 'A Place Only You Can Go');
insert into Song (songId, title) values (6018, 'Slumber');
insert into Song (songId, title) values (6019, 'The Reckoning');
insert into Song (songId, title) values (6020, 'Able');
insert into Song (songId, title) values (6021, 'Maybe They\'re On to Us');
insert into Song (songId, title) values (6022, 'Wanted Man');
insert into Song (songId, title) values (6023, 'Keep Your Eyes Open');
insert into Song (songId, title) values (6024, 'Tyrant Kings');
insert into Song (songId, title) values (6025, 'Devil\'s Been Talkin\'');
insert into Song (songId, title) values (6026, 'Angel At My Door');
insert into Song (songId, title) values (6027, 'Learn To Love');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 277, 9001, 6014);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 225, 9001, 6015);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 253, 9001, 6016);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 192, 9001, 6017);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 215, 9001, 6018);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 224, 9001, 6019);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 261, 9001, 6020);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 190, 9001, 6021);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 246, 9001, 6022);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 250, 9001, 6023);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 249, 9001, 6024);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 215, 9001, 6025);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 272, 9001, 6026);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 261, 9001, 6027);

### Rivers in the Wasteland - Needtobreathe complete

insert into Album (albumId,title,year,number,bandId) values (9002,'Rivers in the Wasteland',2014,5,850);

insert into Song (songId, title) values (6028, 'Wasteland');
insert into Song (songId, title) values (6029, 'State I\'m In');
insert into Song (songId, title) values (6030, 'Feet, Don\'t Fail Me Now');
insert into Song (songId, title) values (6031, 'Oh, Carolina');
insert into Song (songId, title) values (6032, 'Difference Maker');
insert into Song (songId, title) values (6033, 'Rise Again');
insert into Song (songId, title) values (6034, 'The Heart');
insert into Song (songId, title) values (6035, 'Where the Money Is');
insert into Song (songId, title) values (6036, 'Multiplied');
insert into Song (songId, title) values (6037, 'Brother');
insert into Song (songId, title) values (6038, 'More Heart, Less Attack');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 270, 9002, 6028);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 199, 9002, 6029);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 225, 9002, 6030);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 205, 9002, 6031);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 340, 9002, 6032);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 197, 9002, 6033);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 224, 9002, 6034);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 202, 9002, 6035);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 275, 9002, 6036);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 289, 9002, 6037);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 303, 9002, 6038);

### The Sticks - Mother Mother complete

insert into Album (albumId,title,year,number,bandId) values (9003,'The Sticks',2012,4,851);

insert into Song (songId, title) values (6039, 'Omen');
insert into Song (songId, title) values (6040, 'The Sticks');
insert into Song (songId, title) values (6041, 'Let\'s Fall in Love');
insert into Song (songId, title) values (6042, 'Business Man');
insert into Song (songId, title) values (6043, 'Dread in My Heart');
insert into Song (songId, title) values (6044, 'Infinitesimal');
insert into Song (songId, title) values (6045, 'Happy');
insert into Song (songId, title) values (6046, 'Bit by Bit');
insert into Song (songId, title) values (6047, 'Latter Days');
insert into Song (songId, title) values (6048, 'Little Pistols');
insert into Song (songId, title) values (6049, 'Love it Dissipates'); 
insert into Song (songId, title) values (6050, 'Cry Forum');
insert into Song (songId, title) values (6051, 'Waiting For the World to End'); 
insert into Song (songId, title) values (6052, 'To the Wild');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  67, 9003, 6039);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  243, 9003, 6040);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  188, 9003, 6041);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  201, 9003, 6042);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  152, 9003, 6043);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  177, 9003, 6044);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  238, 9003, 6045);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  198, 9003, 6046);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  210, 9003, 6047);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 269, 9003, 6048);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 180, 9003, 6049);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 243, 9003, 6050);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 260, 9003, 6051);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 231, 9003, 6052);

### Very Good Bad Thing - Mother Mother complete

insert into Album (albumId,title,year,number,bandId) values (9004,'Very Good Bad Thing',2014,5,851);

insert into Song (songId, title) values (6053, 'Get Out the Way');
insert into Song (songId, title) values (6054, 'Monkey Tree');
insert into Song (songId, title) values (6055, 'Modern Love');
insert into Song (songId, title) values (6056, 'Reaper Man');
insert into Song (songId, title) values (6057, 'I Go Hungry');
insert into Song (songId, title) values (6058, 'Have It Our');
insert into Song (songId, title) values (6059, 'Very Good Bad Thing');
insert into Song (songId, title) values (6060, 'Kept Down');
insert into Song (songId, title) values (6061, 'Shout if You Know');
insert into Song (songId, title) values (6062, 'Alone and Sublime');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1,  165, 9004, 6053);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2,  207, 9004, 6054);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3,  191, 9004, 6055);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4,  280, 9004, 6056);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5,  217, 9004, 6057);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6,  219, 9004, 6058);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7,  245, 9004, 6059);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8,  259, 9004, 6060);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9,  187, 9004, 6061);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 344, 9004, 6062);



insert into Musician (musicianId,firstName,lastName,country) values (250, 'Florence', 'Welch', 'Great Britain');
insert into Musician (musicianId,firstName,lastName,country) values (251, 'Isabella', 'Summers', 'Great Britain');
insert into Musician (musicianId,firstName,lastName,country) values (800, 'Jack', 'Antonoff', 'USA');

insert into Band (bandId,name) values (340,'Florence and the Machine');
insert into Band (bandId,name) values (90,'Childish Gambino');
insert into Band (bandId,name) values (800,'Bleachers');
insert into Band (bandId,name) values (950,'Paul Simon');

insert into BandMember (musicianId,bandId) values (250,340);
insert into BandMember (musicianId,bandId) values (251,340);
insert into BandMember (musicianId,bandId) values (800,800);

#Florence and the Machine (1) Lungs
INSERT INTO Album (albumId,title,year,number,bandId) values (320,'Lungs',2009,1,340);

INSERT INTO Song (songId, title) VALUES (2220, 'Dog Days Are Over');
INSERT INTO Song (songId, title) VALUES (2221, 'Rabbit Heart (Raise It Up)');
INSERT INTO Song (songId, title) VALUES (2222, 'I\'m Not Calling You A Liar');
INSERT INTO Song (songId, title) VALUES (2223, 'Howl');
INSERT INTO Song (songId, title) VALUES (2224, 'Kiss With a Fist');
INSERT INTO Song (songId, title) VALUES (2225, 'Girl With One Eye');
INSERT INTO Song (songId, title) VALUES (2226, 'Drumming Song');
INSERT INTO Song (songId, title) VALUES (2227, 'Between Two Lungs');
INSERT INTO Song (songId, title) VALUES (2228, 'Cosmic Love');
INSERT INTO Song (songId, title) VALUES (2229, 'My Boy Builds Coffins');
INSERT INTO Song (songId, title) VALUES (2230, 'Hurricane Drunk');
INSERT INTO Song (songId, title) VALUES (2231, 'Blinding');
INSERT INTO Song (songId, title) VALUES (2232, 'You\'ve Got The Love');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 253, 320, 2220);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 232, 320, 2221);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 185, 320, 2222);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 214, 320, 2223);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 124, 320, 2224);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 219, 320, 2225);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 224, 320, 2226);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 249, 320, 2227);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 256, 320, 2228);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 177, 320, 2229);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 193, 320, 2230);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 280, 320, 2231);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 169, 320, 2232);

#Camp

INSERT INTO Album (albumId,title,year,number,bandId) values (94,'Camp',2011,1,90);

INSERT INTO Song (songId, title) VALUES (800, 'Outside');
INSERT INTO Song (songId, title) VALUES (801, 'Fire Fly');
INSERT INTO Song (songId, title) VALUES (802, 'Bonfire');
INSERT INTO Song (songId, title) VALUES (803, 'All the Shine');
INSERT INTO Song (songId, title) VALUES (804, 'Letter Home');
INSERT INTO Song (songId, title) VALUES (805, 'Heartbeat');
INSERT INTO Song (songId, title) VALUES (806, 'Backpackers');
INSERT INTO Song (songId, title) VALUES (807, 'Les');
INSERT INTO Song (songId, title) VALUES (808, 'Hold You Down');
INSERT INTO Song (songId, title) VALUES (809, 'Kids');
INSERT INTO Song (songId, title) VALUES (810, 'You See Me');
INSERT INTO Song (songId, title) VALUES (811, 'Sunrise');
INSERT INTO Song (songId, title) VALUES (812, 'That Power');


insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 270, 94,800);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 204, 94,801);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 193, 94,802);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 346, 94,803);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 104, 94,804);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 271, 94,805);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 196, 94,806);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 319, 94,807);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 293, 94,808);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 298, 94,809);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 195, 94,810);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 220, 94,811);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 462, 94,812);

#Because the Internet

insert into Album (albumId,title,year,number,bandId) values (95,'Because The Internet',2013,2,90);

insert into Song (songId, title) values (813, 'The Library');
insert into Song (songId, title) values (814, 'Crawl');
insert into Song (songId, title) values (815, 'Worldstar');
insert into Song (songId, title) values (816, 'Dial Up');
insert into Song (songId, title) values (817, 'The Worst Guys');
insert into Song (songId, title) values (818, 'Shadows');
insert into Song (songId, title) values (819, 'Telegraph Ave.');
insert into Song (songId, title) values (820, 'Sweatpants');
insert into Song (songId, title) values (821, '3005');
insert into Song (songId, title) values (822, 'Playing Around Before the Party Starts');
insert into Song (songId, title) values (823, 'The Party');
insert into Song (songId, title) values (824, 'No Exit');
insert into Song (songId, title) values (825, 'Death By Numbers');
insert into Song (songId, title) values (826, 'Flight of the Navigator');
insert into Song (songId, title) values (827, 'Zealots of Stockholm');
insert into Song (songId, title) values (828, 'Urn');
insert into Song (songId, title) values (829, 'Pink Toes');
insert into Song (songId, title) values (830, 'Earth: The Oldest Computer');
insert into Song (songId, title) values (831, 'Life: The Biggest Troll');


insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 5, 95, 813);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 389, 95, 814);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 245, 95, 815);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 45, 95, 816);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 330, 95, 817);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 232, 95, 818);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 211, 95, 819);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 181, 95, 820);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 234, 95, 821);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 54, 95,822);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 91, 95, 823);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 172, 95, 824);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 44, 95, 825);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 344, 95, 826);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (15, 291, 95, 827);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (16, 73, 95, 828);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (17, 207, 95, 829);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (18, 282, 95, 830);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (19, 342, 95, 831);

# Bleachers "Strange Desire"
INSERT INTO Album (albumId,title,year,number,bandId) values (800,'Strange Desire',2014,1,800);

INSERT INTO Song (songId, title) VALUES (4801, 'Wild Heart');
INSERT INTO Song (songId, title) VALUES (4802, 'Rollercoaster');
INSERT INTO Song (songId, title) VALUES (4803, 'Shadow');
INSERT INTO Song (songId, title) VALUES (4804, 'I Wanna Get Better');
INSERT INTO Song (songId, title) VALUES (4805, 'Wake Me');
INSERT INTO Song (songId, title) VALUES (4806, 'Reckless Love');
INSERT INTO Song (songId, title) VALUES (4807, 'Take Me Away');
INSERT INTO Song (songId, title) VALUES (4808, 'Like a River Runs');
INSERT INTO Song (songId, title) VALUES (4809, 'You\'re Still A Mystery');
INSERT INTO Song (songId, title) VALUES (4810, 'I\'m Ready to Move On / Wild Heart Reprise');
INSERT INTO Song (songId, title) VALUES (4811, 'Who I Want You to Love');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 200, 800,4801);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 188, 800,4802);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 231, 800,4803);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 204, 800,4804);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 163, 800,4805);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 181, 800,4806);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 150, 800,4807);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 213, 800,4808);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 264, 800,4809);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 251, 800,4810);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 290, 800,4811);


# Paul Simon Graceland
INSERT INTO Album (albumId,title,year,number,bandId) values (850,'Graceland',1986,8,950);

INSERT INTO Song (songId, title) VALUES (851, 'The Boy in the Bubble');
INSERT INTO Song (songId, title) VALUES (852, 'Graceland');
INSERT INTO Song (songId, title) VALUES (853, 'I Know What I Know');
INSERT INTO Song (songId, title) VALUES (854, 'Gumboots');
INSERT INTO Song (songId, title) VALUES (855, 'Diamonds on the Soles of Her Shoes');
INSERT INTO Song (songId, title) VALUES (856, 'You Can Call Me Al');
INSERT INTO Song (songId, title) VALUES (857, 'Under African Skies');
INSERT INTO Song (songId, title) VALUES (858, 'Homeless');
INSERT INTO Song (songId, title) VALUES (859, 'Crazy Love, Vol. II');
INSERT INTO Song (songId, title) VALUES (860, 'That Was Your Mother');
INSERT INTO Song (songId, title) VALUES (861, 'All Around the World or the Myth of Fingerprints');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 239, 850,851);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 288, 850,852);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 193, 850,853);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 164, 850,854);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 345, 850,855);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 279, 850,856);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 217, 850,857);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 228, 850,858);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 258, 850,859);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 172, 850,860);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 195, 850,861);

insert into Musician (musicianId,firstName,lastName,country) values (67101, 'Chris', 'Phillips', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (67102, 'Stacey', 'Guess', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (67103, 'Don', 'Raleigh', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (67104, 'Katharine', 'Whalen', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (67105, 'Ken', 'Mosher', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (67106, 'Jimbo', 'Mathus', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (67107, 'Tom', 'Maxwell', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (67108, 'Clay', 'Walker', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (67109, 'Stuart', 'Cole', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (67110, 'Je', 'Widenhouse', 'USA');

insert into Band (bandId,name) values (6751, 'Squirrel Nut Zipper');

insert into BandMember (musicianId,bandId) values (67101, 6751);
insert into BandMember (musicianId,bandId) values (67102, 6751);
insert into BandMember (musicianId,bandId) values (67103, 6751);
insert into BandMember (musicianId,bandId) values (67104, 6751);
insert into BandMember (musicianId,bandId) values (67105, 6751);
insert into BandMember (musicianId,bandId) values (67106, 6751);
insert into BandMember (musicianId,bandId) values (67107, 6751);
insert into BandMember (musicianId,bandId) values (67108, 6751);
insert into BandMember (musicianId,bandId) values (67109, 6751);
insert into BandMember (musicianId,bandId) values (67110, 6751);

insert into Album (albumId,title,year,number,bandId) values (67101, 'The Inevitable', 1995, 1, 6751);

insert into Song (songId, title) values (67101, 'Lover\'s Lane');
insert into Song (songId, title) values (67102, 'Danny Diamond');
insert into Song (songId, title) values (67103, 'I\'ve Found a New Baby');
insert into Song (songId, title) values (67104, 'Anything But Love');
insert into Song (songId, title) values (67105, 'Good Enough for Granddad');
insert into Song (songId, title) values (67106, 'Wished for You');
insert into Song (songId, title) values (67107, 'La Grippe');
insert into Song (songId, title) values (67108, 'Lugubrious Whing Whang');
insert into Song (songId, title) values (67109, 'Club Limbo');
insert into Song (songId, title) values (67110, 'Wash Jones');
insert into Song (songId, title) values (67111, 'You\'re Driving Me Crazy');
insert into Song (songId, title) values (67112, 'Plenty More');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 183, 67101, 67101);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 229, 67101, 67102);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 162, 67101, 67103);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 158, 67101, 67104);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 137, 67101, 67105);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 134, 67101, 67106);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 190, 67101, 67107);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 158, 67101, 67108);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 176, 67101, 67109);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 184, 67101, 67110);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 166, 67101, 67111);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 207, 67101, 67112);


insert into Album (albumId,title,year,number,bandId) values (67102, 'Hot', 1996, 2, 6751);

insert into Song (songId, title) values (67113, 'Got My Own Thing Now');
insert into Song (songId, title) values (67114, 'Put a Lid on It');
insert into Song (songId, title) values (67115, 'Memphis Exorcism');
insert into Song (songId, title) values (67116, 'Twilight');
insert into Song (songId, title) values (67117, 'It Ain\'t You');
insert into Song (songId, title) values (67118, 'Prince Nez');
insert into Song (songId, title) values (67119, 'Hell');
insert into Song (songId, title) values (67120, 'Meant to Be');
insert into Song (songId, title) values (67121, 'Bad Businessman');
insert into Song (songId, title) values (67122, 'Flight of the Passing Fancy');
insert into Song (songId, title) values (67123, 'Blue Angel');
insert into Song (songId, title) values (67124, 'The Interlocutor');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 150, 67102, 67113);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 159, 67102, 67114);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 144, 67102, 67115);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 214, 67102, 67116);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 188, 67102, 67117);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 173, 67102, 67118);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 192, 67102, 67119);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 191, 67102, 67120);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 228, 67102, 67121);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 238, 67102, 67122);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 275, 67102, 67123);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 167, 67102, 67124);


insert into Album (albumId,title,year,number,bandId) values (67103, 'Perennial Favorites', 1998, 5, 6751);

insert into Song (songId, title) values (67125, 'Suits Are Picking up the Bill');
insert into Song (songId, title) values (67126, 'Low Down Man');
insert into Song (songId, title) values (67127, 'Ghost of Stephan Foster');
insert into Song (songId, title) values (67128, 'Pallin\' with Al');
insert into Song (songId, title) values (67129, 'Fat Cat Keeps Getting Fatter');
insert into Song (songId, title) values (67130, 'Trou Macacq');
insert into Song (songId, title) values (67131, 'My Drag');
insert into Song (songId, title) values (67132, 'Soon');
insert into Song (songId, title) values (67133, 'Evening at Laftte\'s');
insert into Song (songId, title) values (67134, 'The Kraken');
insert into Song (songId, title) values (67135, 'That Fascinating Thing');
insert into Song (songId, title) values (67136, 'It\'s Over');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 184, 67103, 67125);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 254, 67103, 67126);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 212, 67103, 67127);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 161, 67103, 67128);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 167, 67103, 67129);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 197, 67103, 67130);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 207, 67103, 67131);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 182, 67103, 67132);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 168, 67103, 67133);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 220, 67103, 67134);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 163, 67103, 67135);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 109, 67103, 67136);


insert into Album (albumId,title,year,number,bandId) values (67104, 'Christmas Caravan', 1998, 6, 6751);

insert into Song (songId, title) values (67137, 'Winter Weather');
insert into Song (songId, title) values (67138, 'Indian Giver');
insert into Song (songId, title) values (67139, 'A Johnny Ace Christmas');
insert into Song (songId, title) values (67140, 'My Evergreen');
insert into Song (songId, title) values (67141, 'Sleigh Ride');
insert into Song (songId, title) values (67142, 'I\'m Coming Home for Christmas');
insert into Song (songId, title) values (67143, 'Carolina Christmas');
insert into Song (songId, title) values (67144, 'Gift of the Magi');
insert into Song (songId, title) values (67145, 'Hot Christmas');
insert into Song (songId, title) values (67146, 'Hanging Up My Stockings');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 144, 67104, 67137);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 217, 67104, 67138);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 223, 67104, 67139);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 155, 67104, 67140);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 189, 67104, 67141);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 226, 67104, 67142);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 111, 67104, 67143);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 199, 67104, 67144);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 155, 67104, 67145);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 431, 67104, 67146);


insert into Album (albumId,title,year,number,bandId) values (67105, 'Bedlam Ballroom', 2000, 7, 6751);

insert into Song (songId, title) values (67147, 'Bedbugs');
insert into Song (songId, title) values (67148, 'Baby Wants a Diamond Ring');
insert into Song (songId, title) values (67149, 'Do What?');
insert into Song (songId, title) values (67150, 'Bent Out of Shape');
insert into Song (songId, title) values (67151, 'Stop Drop and Roll');
insert into Song (songId, title) values (67152, 'Hush');
insert into Song (songId, title) values (67153, 'It All Depends');
insert into Song (songId, title) values (67154, 'Bedlam Ballroom');
insert into Song (songId, title) values (67155, 'Just This Side of Blue');
insert into Song (songId, title) values (67156, 'Don\'t Fix It');
insert into Song (songId, title) values (67157, 'Missing Link');
insert into Song (songId, title) values (67158, 'Bedlam Reprise');
insert into Song (songId, title) values (67159, 'Do It This a Way');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 192, 67105, 67147);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 205, 67105, 67148);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 162, 67105, 67149);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 167, 67105, 67150);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 178, 67105, 67151);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 232, 67105, 67152);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 227, 67105, 67153);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 135, 67105, 67154);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 188, 67105, 67155);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 213, 67105, 67156);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 201, 67105, 67157);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 21, 67105, 67158);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 153, 67105, 67159);

# Jonas Brothers + Hannah Montana

insert into Musician (musicianId,firstName,lastName,country) values (89101, 'Kevin', 'Jonas', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (89102, 'Joe', 'Jonas', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (89103, 'Nick', 'Jonas', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (89104, 'Miley', 'Cyrus', 'USA');

insert into Band (bandId,name) values (8951, 'Jonas Brothers');
insert into Band (bandId,name) values (8952, 'Hannah Montana');

insert into BandMember (musicianId,bandId) values (89101, 8951);
insert into BandMember (musicianId,bandId) values (89102, 8951);
insert into BandMember (musicianId,bandId) values (89103, 8951);
insert into BandMember (musicianId,bandId) values (89104, 8952);

# Jonas Brothers - It's About Time

insert into Album (albumId,title,year,number,bandId) values (89101, 'It\'s About Time', 2006, 1, 8951);

insert into Song (songId, title) values (89101, 'What I Go to School For');
insert into Song (songId, title) values (89102, 'Time for Me to Fly');
insert into Song (songId, title) values (89103, 'Year 3000');
insert into Song (songId, title) values (89104, 'One Day at a Time');
insert into Song (songId, title) values (89105, '6 Minutes');
insert into Song (songId, title) values (89106, 'Mandy');
insert into Song (songId, title) values (89107, 'You Just Don\'t Know It');
insert into Song (songId, title) values (89108, 'I Am What I Am');
insert into Song (songId, title) values (89109, 'Underdog');
insert into Song (songId, title) values (89110, '7:05');
insert into Song (songId, title) values (89111, 'Please Be Mine');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 209, 89101, 89101);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 183, 89101, 89102);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 199, 89101, 89103);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 232, 89101, 89104);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 182, 89101, 89105);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 165, 89101, 89106);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 211, 89101, 89107);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 127, 89101, 89108);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 194, 89101, 89109);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 223, 89101, 89110);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 192, 89101, 89111);

# Jonas Brothers - Jonas Brothers (Album)

insert into Album (albumId,title,year,number,bandId) values (89102, 'Jonas Brothers', 2007, 2, 8951);

insert into Song (songId, title) values (89112, 'S.O.S.');
insert into Song (songId, title) values (89113, 'Hold On');
insert into Song (songId, title) values (89114, 'Goodnight and Goodbye');
insert into Song (songId, title) values (89115, 'That\'s Just the Way we Roll');
insert into Song (songId, title) values (89116, 'Hello Beautiful');
insert into Song (songId, title) values (89117, 'Still in Love with You');
insert into Song (songId, title) values (89118, 'Australia');
insert into Song (songId, title) values (89119, 'Games');
insert into Song (songId, title) values (89120, 'When You Look Me in the Eyes');
insert into Song (songId, title) values (89121, 'Inseparable');
insert into Song (songId, title) values (89122, 'Just Friends');
insert into Song (songId, title) values (89123, 'Hollywood');
insert into Song (songId, title) values (89124, 'Year 3000');
insert into Song (songId, title) values (89125, 'Kids of the Future');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 153, 89102, 89112);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 165, 89102, 89113);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 151, 89102, 89114);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 173, 89102, 89115);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 149, 89102, 89116);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 190, 89102, 89117);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 213, 89102, 89118);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 201, 89102, 89119);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 249, 89102, 89120);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 170, 89102, 89121);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 187, 89102, 89122);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 169, 89102, 89123);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 202, 89102, 89124);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 200, 89102, 89125);

# Jonas Brothers - A Little Bit Longer

insert into Album (albumId,title,year,number,bandId) values (89103, 'A Little Bit Longer', 2008, 3, 8951);

insert into Song (songId, title) values (89126, 'BB Good');
insert into Song (songId, title) values (89127, 'Burnin\' Up');
insert into Song (songId, title) values (89128, 'Shelf');
insert into Song (songId, title) values (89129, 'One Man Show');
insert into Song (songId, title) values (89130, 'Lovebug');
insert into Song (songId, title) values (89131, 'Tonight');
insert into Song (songId, title) values (89132, 'Can\'t Have You');
insert into Song (songId, title) values (89133, 'Video Girl');
insert into Song (songId, title) values (89134, 'Pushin\' Me Away');
insert into Song (songId, title) values (89135, 'Sorry');
insert into Song (songId, title) values (89136, 'Got Me Going Crazy');
insert into Song (songId, title) values (89137, 'A Little Bit Longer');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 176, 89103, 89126);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 174, 89103, 89127);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 228, 89103, 89128);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 188, 89103, 89129);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 220, 89103, 89130);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 209, 89103, 89131);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 268, 89103, 89132);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 173, 89103, 89133);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 183, 89103, 89134);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 192, 89103, 89135);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 155, 89103, 89136);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 205, 89103, 89137);

# Jonas Brothers - Lines, Vines and Trying Times

insert into Album (albumId,title,year,number,bandId) values (89104, 'Lines, Vines and Trying Times', 2009, 4, 8951);

insert into Song (songId, title) values (89138, 'World War III');
insert into Song (songId, title) values (89139, 'Paranoid');
insert into Song (songId, title) values (89140, 'Fly with Me');
insert into Song (songId, title) values (89141, 'Poison Ivy');
insert into Song (songId, title) values (89142, 'Hey Baby');
insert into Song (songId, title) values (89143, 'Before the Storm');
insert into Song (songId, title) values (89144, 'What Did I Do to Your Heart');
insert into Song (songId, title) values (89145, 'Much Better');
insert into Song (songId, title) values (89146, 'Black Keys');
insert into Song (songId, title) values (89147, 'Don\'t Charge Me for the Crime');
insert into Song (songId, title) values (89148, 'Turn Right');
insert into Song (songId, title) values (89149, 'Don\'t Speak');
insert into Song (songId, title) values (89150, 'Keep It Real');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 193, 89104, 89138);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 219, 89104, 89139);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 234, 89104, 89140);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 249, 89104, 89141);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 199, 89104, 89142);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 266, 89104, 89143);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 197, 89104, 89144);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 276, 89104, 89145);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 228, 89104, 89146);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 239, 89104, 89147);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 168, 89104, 89148);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 235, 89104, 89149);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 171, 89104, 89150);

# Hannah Montana - Hanna Montana 2

insert into Album (albumId,title,year,number,bandId) values (89105, 'Hannah Montana 2', 2007, 5, 8952);

insert into Song (songId, title) values (89151, 'We Got the Party');
insert into Song (songId, title) values (89152, 'Nobody\'s Perfect');
insert into Song (songId, title) values (89153, 'Make Some Noise');
insert into Song (songId, title) values (89154, 'Rock Star');
insert into Song (songId, title) values (89155, 'Old Blue Jeans');
insert into Song (songId, title) values (89156, 'Life\'s What You Make It');
insert into Song (songId, title) values (89157, 'One in a Million');
insert into Song (songId, title) values (89158, 'Bigger Than Us');
insert into Song (songId, title) values (89159, 'You and Me Together');
insert into Song (songId, title) values (89160, 'True Friend');
insert into Song (songId, title) values (89161, 'See You Again');
insert into Song (songId, title) values (89162, 'East Northumberland High');
insert into Song (songId, title) values (89163, 'Let\'s Dance');
insert into Song (songId, title) values (89164, 'G.N.O. (Girl\'s Night Out)');
insert into Song (songId, title) values (89165, 'Right Here');
insert into Song (songId, title) values (89166, 'As I Am');
insert into Song (songId, title) values (89167, 'Start All Over');
insert into Song (songId, title) values (89168, 'Clear');
insert into Song (songId, title) values (89169, 'Good and Broken');
insert into Song (songId, title) values (89170, 'I Miss You');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 216, 89105, 89151);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 200, 89105, 89152);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 267, 89105, 89153);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 178, 89105, 89154);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 202, 89105, 89155);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 191, 89105, 89156);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 235, 89105, 89157);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 178, 89105, 89158);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 228, 89105, 89159);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 191, 89105, 89160);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 190, 89105, 89161);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 204, 89105, 89162);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 183, 89105, 89163);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 218, 89105, 89164);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (15, 164, 89105, 89165);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (16, 225, 89105, 89166);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (17, 207, 89105, 89167);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (18, 183, 89105, 89168);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (19, 176, 89105, 89169);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (20, 238, 89105, 89170);


insert into Musician (musicianId,firstName,lastName,country) values (300, 'Harry', 'Styles', 'England');
insert into Musician (musicianId,firstName,lastName,country) values (301, 'Louis', 'Tomlinson', 'England');
insert into Musician (musicianId,firstName,lastName,country) values (302, 'Niall', 'Horan', 'England');
insert into Musician (musicianId,firstName,lastName,country) values (303, 'Liam', 'Payne', 'England');
insert into Musician (musicianId,firstName,lastName,country) values (304, 'Nate', 'Reuss', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (305, 'Jack', 'Antonoff', 'USA');
insert into Musician (musicianId,firstName,lastName,country) values (306, 'Andrew', 'Dost', 'USA');

insert into Band (bandId,name) values (300,'One Direction');
insert into Band (bandId,name) values (301,'Fun');

##One Direction
insert into BandMember (musicianId,bandId) values (300,300);
insert into BandMember (musicianId,bandId) values (301,300);
insert into BandMember (musicianId,bandId) values (302,300);
insert into BandMember (musicianId,bandId) values (303,300);

##Fun
insert into BandMember (musicianId,bandId) values (304,301);
insert into BandMember (musicianId,bandId) values (305,301);
insert into BandMember (musicianId,bandId) values (306,301);

##ALBUM 1 : One Direction - Four
INSERT INTO Album (albumId,title,year,number,bandId) values (300,'Four',2014,1,300);

INSERT INTO Song (songId, title) VALUES (7300, 'Steal My Girl');
INSERT INTO Song (songId, title) VALUES (7301, 'Ready To Run');
INSERT INTO Song (songId, title) VALUES (7302, 'Where do Broken Hearts go');
INSERT INTO Song (songId, title) VALUES (7303, '18');
INSERT INTO Song (songId, title) VALUES (7304, 'Girl Almighty');
INSERT INTO Song (songId, title) VALUES (7305, 'Fools Gold');
INSERT INTO Song (songId, title) VALUES (7306, 'Night Changes');
INSERT INTO Song (songId, title) VALUES (7307, 'No Control');
INSERT INTO Song (songId, title) VALUES (7308, 'Fireproof');
INSERT INTO Song (songId, title) VALUES (7309, 'Spaces');
INSERT INTO Song (songId, title) VALUES (7310, 'Stockholm Syndrome');
INSERT INTO Song (songId, title) VALUES (7311, 'Clouds');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 348, 300,7300);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 316, 300,7301);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 349, 300,7302);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 408, 300,7303);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 322, 300,7304);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 331, 300,7305);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 347, 300,7306);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 320, 300,7307);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 254, 300,7308);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 417, 300,7309);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 335, 300,7310);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 352, 300,7311);

##ALBUM 2 : One Direction - Up All Night
INSERT INTO Album (albumId,title,year,number,bandId) values (5301,'Up All Night',2011,2,300);

INSERT INTO Song (songId, title) VALUES (7312, 'What makes you Beautiful');
INSERT INTO Song (songId, title) VALUES (7313, 'Gotta be you');
INSERT INTO Song (songId, title) VALUES (7314, 'One Thing');
INSERT INTO Song (songId, title) VALUES (7315, 'More than this');
INSERT INTO Song (songId, title) VALUES (7316, 'Up all Night');
INSERT INTO Song (songId, title) VALUES (7317, 'I Wish');
INSERT INTO Song (songId, title) VALUES (7318, 'Tell me a Lie');
INSERT INTO Song (songId, title) VALUES (7319, 'Taken');
INSERT INTO Song (songId, title) VALUES (7320, 'I want');
INSERT INTO Song (songId, title) VALUES (7321, 'Everything about you');
INSERT INTO Song (songId, title) VALUES (7322, 'Same Mistakes');
INSERT INTO Song (songId, title) VALUES (7323, 'Save you tonight');
INSERT INTO Song (songId, title) VALUES (7324, 'Stole my Heart');
INSERT INTO Song (songId, title) VALUES (7325, 'Stand Up');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 317, 5301,7312);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 401, 5301,7313);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 314, 5301,7314);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 404, 5301,7315);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 312, 5301,7316);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 335, 5301,7317);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 315, 5301,7318);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 355, 5301,7319);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 251, 5301,7320);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 335, 5301,7321);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 337, 5301,7322);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 324, 5301,7323);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 325, 5301,7324);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 324, 5301,7325);

##ALBUM 3 : Fun - Aim and Ignite
INSERT INTO Album (albumId,title,year,number,bandId) values (5302,'Aim and Ignite',2009,1,301);

INSERT INTO Song (songId, title) VALUES (7326, 'Be Calm');
INSERT INTO Song (songId, title) VALUES (7327, 'Benson Hedges');
INSERT INTO Song (songId, title) VALUES (7328, 'All the Pretty Girls');
INSERT INTO Song (songId, title) VALUES (7329, 'I wanna be the one');
INSERT INTO Song (songId, title) VALUES (7330, 'At least I am not as Sad');
INSERT INTO Song (songId, title) VALUES (7331, 'Light a Roman Candle with me');
INSERT INTO Song (songId, title) VALUES (7332, 'Walking the dog');
INSERT INTO Song (songId, title) VALUES (7333, 'Barlights');
INSERT INTO Song (songId, title) VALUES (7334, 'The Gambler');
INSERT INTO Song (songId, title) VALUES (7335, 'Take your Time(Coming Home)');
INSERT INTO Song (songId, title) VALUES (7336, 'Walking the dog II');
INSERT INTO Song (songId, title) VALUES (7337, 'Take your Time(Acoustic)');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 410, 5302,7326);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 400, 5302,7327);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 321, 5302,7328);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 336, 5302,7329);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 406, 5302,7330);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 305, 5302,7331);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 324, 5302,7332);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 417, 5302,7333);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 411, 5302,7334);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 708, 5302,7335);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 430, 5302,7336);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 357, 5302,7337);

##ALBUM 4 : Fun - Some Nights
INSERT INTO Album (albumId,title,year,number,bandId) values (5303,'Some Nights',2012,2,301);

INSERT INTO Song (songId, title) VALUES (7338, 'Some nights');
INSERT INTO Song (songId, title) VALUES (7339, 'We are young');
INSERT INTO Song (songId, title) VALUES (7340, 'Carry On');
INSERT INTO Song (songId, title) VALUES (7341, 'It gets better');
INSERT INTO Song (songId, title) VALUES (7342, 'Why am I the one');
INSERT INTO Song (songId, title) VALUES (7343, 'All Alone');
INSERT INTO Song (songId, title) VALUES (7344, 'All Alright');
INSERT INTO Song (songId, title) VALUES (7345, 'One foot');
INSERT INTO Song (songId, title) VALUES (7346, 'Stars');
INSERT INTO Song (songId, title) VALUES (7347, 'Out on the Town');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 437, 5303,7338);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 410, 5303,7339);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 417, 5303,7340);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 331, 5303,7341);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 446, 5303,7342);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 303, 5303,7343);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 357, 5303,7344);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 332, 5303,7345);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 653, 5303,7346);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 422, 5303,7347);

##ALBUM 5 : One Direction - Midnight Memories
INSERT INTO Album (albumId,title,year,number,bandId) values (5304,'Midnight Memories',2013,3,300);

INSERT INTO Song (songId, title) VALUES (7348, 'Best Song Ever');
INSERT INTO Song (songId, title) VALUES (7349, 'Story of my life');
INSERT INTO Song (songId, title) VALUES (7350, 'Diana');
INSERT INTO Song (songId, title) VALUES (7351, 'Midnight memories');
INSERT INTO Song (songId, title) VALUES (7352, 'You and I');
INSERT INTO Song (songId, title) VALUES (7353, 'Dont forget where you belong');
INSERT INTO Song (songId, title) VALUES (7354, 'Strong');
INSERT INTO Song (songId, title) VALUES (7355, 'Happily');
INSERT INTO Song (songId, title) VALUES (7356, 'Right Now');
INSERT INTO Song (songId, title) VALUES (7357, 'Little Black Dress');
INSERT INTO Song (songId, title) VALUES (7358, 'Through the Dark');
INSERT INTO Song (songId, title) VALUES (7359, 'Somthing Great');
INSERT INTO Song (songId, title) VALUES (7360, 'Little White Lies');
INSERT INTO Song (songId, title) VALUES (7361, 'Better than Words');

insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (1, 321, 5304,7348);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (2, 404, 5304,7349);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (3, 405, 5304,7350);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (4, 254, 5304,7351);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (5, 358, 5304,7352);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (6, 401, 5304,7353);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (7, 304, 5304,7354);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (8, 255, 5304,7355);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (9, 320, 5304,7356);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (10, 238, 5304,7357);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (11, 342, 5304,7358);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (12, 357, 5304,7359);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (13, 318, 5304,7360);
insert into AlbumSong (trackNumber,trackLength,albumId,songId) values (14, 327, 5304,7361);


INSERT INTO Song (`title`) VALUES 
 ( "Wouldn't It Be Nice" ) , 
 ( "You Still Believe in Me" ) , 
 ( "That's Not Me" ) , 
 ( "Don't Talk (Put Your Head on My Shoulder)" ) , 
 ( "I'm Waiting for the Day" ) , 
 ( "Let's Go Away for Awhile" ) , 
 ( "Sloop John B" ) , 
 ( "God Only Knows" ) , 
 ( "I Know There's an Answer" ) , 
 ( "Here Today" ) , 
 ( "I Just Wasn't Made for These Times" ) , 
 ( "Pet Sounds" ) , 
 ( "Caroline No" ) , 
 ( "Heroes and Villains" ) , 
 ( "Vegetables" ) , 
 ( "Fall Breaks and Back to Winter" ) , 
 ( "She's Goin' Bald" ) , 
 ( "Little Pad" ) , 
 ( "Good Vibrations" ) , 
 ( "With Me Tonight" ) , 
 ( "Wind Chimes" ) , 
 ( "Gettin' Hungry" ) , 
 ( "Wonderful" ) , 
 ( "Whistle In" ) , 
 ( "It's Working" ) , 
 ( "Song for Dan Treacy" ) , 
 ( "Someone's Missing" ) , 
 ( "Flash Delirium" ) , 
 ( "I Found a Whistle" ) , 
 ( "Siberian Breaks" ) , 
 ( "Brian Eno" ) , 
 ( "Lady Dada's Nightmare" ) , 
 ( "Congratulations" ) , 
 ( "Emoji" ) , 
 ( "Pink Cloud (feat. Max Collins)" ) , 
 ( "Just Like That (feat. Johnny Graves)" ) , 
 ( "Downhearted (feat. Jonny Rose)" ) , 
 ( "End Is Near (Fire In The Hole VIP)" ) , 
 ( "Open Your Eyes" ) , 
 ( "Someone Like Me" ) , 
 ( "Zero" ) , 
 ( "You Found Me (feat. Replacer)" ) , 
 ( "Angels and Demons (feat. Feather)" ) , 
 ( "Ashes" ) , 
 ( "Momentum" ) , 
 ( "Mirrors (feat. Princewhateverer)" ) , 
 ( "Rock the World" ) , 
 ( "Where I Belong" );
 
 INSERT INTO Musician (`lastName`, `firstName`, `country`) VALUES 
 ( "Wilson", "Brian", "USA"  ), 
 ( "Love", "Mike", "USA"  ), 
 ( "Jardine", "Al", "USA"  ), 
 ( "Marks", "David", "USA"  ), 
 ( "Johnston", "Bruce", "USA"  ), 
 ( "VanWyngarden", "Andrew", "USA"  ), 
 ( "Goldwasser", "Ben", "USA"  ), 
 ( "Shaw", "Tyler", "USA"  ), 
 ( "Parsberg", "Michael", "Denmark"  ), 
 ( "Odden", "Alexander", "Norway"  );

INSERT INTO Band (`name`) Values
 ('The Beach Boys'),
 ('MGMT'),
 ('Aviators'),
 ('Pegboard Nerds');

INSERT INTO Album (`title`, `year`, `number`, `bandId`) VALUES 
 ( "Pet Sounds", 1966, 11, (select bandId from Band where name = "The Beach Boys" ) ), 
 ( "Smiley Smile", 1967, 12, (select bandId from Band where name = "The Beach Boys" ) ), 
 ( "Congratulations", 2010, 2, (select bandId from Band where name = "MGMT" ) ), 
 ( "Mirrors", 2013, 8, (select bandId from Band where name = "Aviators" ) ), 
 ( "Pink Cloud", 2015, 8, (select bandId from Band where name = "Pegboard Nerds" ) );
 
 
 
 INSERT INTO AlbumSong (`TrackNumber`, `TrackLength`, `albumId`, `songId`) VALUES 
 ( 1, 145, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "Wouldn't It Be Nice" )  ), 
 ( 2, 151, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "You Still Believe in Me" )  ), 
 ( 3, 148, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "That's Not Me" )  ), 
 ( 4, 173, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "Don't Talk (Put Your Head on My Shoulder)" )  ), 
 ( 5, 185, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "I'm Waiting for the Day" )  ), 
 ( 6, 138, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "Let's Go Away for Awhile" )  ), 
 ( 7, 178, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "Sloop John B" )  ), 
 ( 8, 171, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "God Only Knows" )  ), 
 ( 9, 189, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "I Know There's an Answer" )  ), 
 ( 10, 174, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "Here Today" )  ), 
 ( 11, 192, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "I Just Wasn't Made for These Times" )  ), 
 ( 12, 142, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "Pet Sounds" )  ), 
 ( 13, 171, (select albumId from Album where title = "Pet Sounds" ), (select songId from Song where title = "Caroline No" )  ), 
 ( 1, 217, (select albumId from Album where title = "Smiley Smile" ), (select songId from Song where title = "Heroes and Villains" )  ), 
 ( 2, 127, (select albumId from Album where title = "Smiley Smile" ), (select songId from Song where title = "Vegetables" )  ), 
 ( 3, 135, (select albumId from Album where title = "Smiley Smile" ), (select songId from Song where title = "Fall Breaks and Back to Winter" )  ), 
 ( 4, 135, (select albumId from Album where title = "Smiley Smile" ), (select songId from Song where title = "She's Goin' Bald" )  ), 
 ( 5, 150, (select albumId from Album where title = "Smiley Smile" ), (select songId from Song where title = "Little Pad" )  ), 
 ( 6, 216, (select albumId from Album where title = "Smiley Smile" ), (select songId from Song where title = "Good Vibrations" )  ), 
 ( 7, 137, (select albumId from Album where title = "Smiley Smile" ), (select songId from Song where title = "With Me Tonight" )  ), 
 ( 8, 156, (select albumId from Album where title = "Smiley Smile" ), (select songId from Song where title = "Wind Chimes" )  ), 
 ( 9, 147, (select albumId from Album where title = "Smiley Smile" ), (select songId from Song where title = "Gettin' Hungry" )  ), 
 ( 10, 141, (select albumId from Album where title = "Smiley Smile" ), (select songId from Song where title = "Wonderful" )  ), 
 ( 11, 64, (select albumId from Album where title = "Smiley Smile" ), (select songId from Song where title = "Whistle In" )  ), 
 ( 1, 246, (select albumId from Album where title = "Congratulations" ), (select songId from Song where title = "It's Working" )  ), 
 ( 2, 249, (select albumId from Album where title = "Congratulations" ), (select songId from Song where title = "Song for Dan Treacy" )  ), 
 ( 3, 149, (select albumId from Album where title = "Congratulations" ), (select songId from Song where title = "Someone's Missing" )  ), 
 ( 4, 255, (select albumId from Album where title = "Congratulations" ), (select songId from Song where title = "Flash Delirium" )  ), 
 ( 5, 220, (select albumId from Album where title = "Congratulations" ), (select songId from Song where title = "I Found a Whistle" )  ), 
 ( 6, 729, (select albumId from Album where title = "Congratulations" ), (select songId from Song where title = "Siberian Breaks" )  ), 
 ( 7, 271, (select albumId from Album where title = "Congratulations" ), (select songId from Song where title = "Brian Eno" )  ), 
 ( 8, 271, (select albumId from Album where title = "Congratulations" ), (select songId from Song where title = "Lady Dada's Nightmare" )  ), 
 ( 9, 235, (select albumId from Album where title = "Congratulations" ), (select songId from Song where title = "Congratulations" )  ), 
 ( 1, 219, (select albumId from Album where title = "Pink Cloud" ), (select songId from Song where title = "Emoji" )  ), 
 ( 2, 215, (select albumId from Album where title = "Pink Cloud" ), (select songId from Song where title = "Pink Cloud (feat. Max Collins)" )  ), 
 ( 3, 203, (select albumId from Album where title = "Pink Cloud" ), (select songId from Song where title = "Just Like That (feat. Johnny Graves)" )  ), 
 ( 4, 210, (select albumId from Album where title = "Pink Cloud" ), (select songId from Song where title = "Downhearted (feat. Jonny Rose)" )  ), 
 ( 5, 243, (select albumId from Album where title = "Pink Cloud" ), (select songId from Song where title = "End Is Near (Fire In The Hole VIP)" )  ), 
 ( 1, 300, (select albumId from Album where title = "Mirrors" ), (select songId from Song where title = "Open Your Eyes" )  ), 
 ( 2, 310, (select albumId from Album where title = "Mirrors" ), (select songId from Song where title = "Someone Like Me" )  ), 
 ( 3, 241, (select albumId from Album where title = "Mirrors" ), (select songId from Song where title = "Zero" )  ), 
 ( 4, 260, (select albumId from Album where title = "Mirrors" ), (select songId from Song where title = "You Found Me (feat. Replacer)" )  ), 
 ( 5, 272, (select albumId from Album where title = "Mirrors" ), (select songId from Song where title = "Angels and Demons (feat. Feather)" )  ), 
 ( 6, 428, (select albumId from Album where title = "Mirrors" ), (select songId from Song where title = "Ashes" )  ), 
 ( 7, 300, (select albumId from Album where title = "Mirrors" ), (select songId from Song where title = "Momentum" )  ), 
 ( 8, 270, (select albumId from Album where title = "Mirrors" ), (select songId from Song where title = "Mirrors (feat. Princewhateverer)" )  ), 
 ( 9, 370, (select albumId from Album where title = "Mirrors" ), (select songId from Song where title = "Rock the World" )  ), 
 ( 10, 307, (select albumId from Album where title = "Mirrors" ), (select songId from Song where title = "Where I Belong" )  ) ;

INSERT INTO BandMember (`musicianId`, `bandId`) VALUES 
 ( (select musicianId from Musician where lastName = "Wilson" and firstName = "Brian" ), (select bandId from Band where name = "The Beach Boys" )  ),
 ( (select musicianId from Musician where lastName = "Love" and firstName = "Mike" ), (select bandId from Band where name = "The Beach Boys" )  ),
 ( (select musicianId from Musician where lastName = "Jardine" and firstName = "Al" ), (select bandId from Band where name = "The Beach Boys" )  ),
 ( (select musicianId from Musician where lastName = "Marks" and firstName = "David" ), (select bandId from Band where name = "The Beach Boys" )  ),
 ( (select musicianId from Musician where lastName = "Johnston" and firstName = "Bruce" ), (select bandId from Band where name = "The Beach Boys" )  ),
 ( (select musicianId from Musician where lastName = "VanWyngarden" and firstName = "Andrew" ), (select bandId from Band where name = "MGMT" )  ),
 ( (select musicianId from Musician where lastName = "Goldwasser" and firstName = "Ben" ), (select bandId from Band where name = "MGMT" )  ),
 ( (select musicianId from Musician where lastName = "Shaw" and firstName = "Tyler" ), (select bandId from Band where name = "Aviators" )  ),
 ( (select musicianId from Musician where lastName = "Parsberg" and firstName = "Michael" ), (select bandId from Band where name = "Pegboard Nerds" )  ),
 ( (select musicianId from Musician where lastName = "Odden" and firstName = "Alexander" ), (select bandId from Band where name = "Pegboard Nerds" )  );

## Adding musicians not in any band so that one of the queries gives 
insert into Musician (firstName, lastName, country) values ('Edgar', 'Froese', 'Germany');
insert into Musician (firstName, lastName, country) values ('Christopher', 'Franke', 'Germany');
insert into Musician (firstName, lastName, country) values ('Brett', 'Sparks', 'United States');

commit;





























































-- W

SELECT a.albumId as "Album ID", a.title as "Album Title", a.number as "Album Number", a.year as "Album Year", b.bandId as "Band ID", b.name as "Band Name" from Band b
	join Album a on a.bandId = b.bandId;
	
    
-- finds everything but band members
select a.albumId as "Album ID", a.title as "Album Title", a.year as "Album Year",albs.trackNumber,s.songId,s.title from Band b
	join Album a on a.bandId = b.bandId
    left join AlbumSong albs on a.albumId = albs.albumId
    left join Song s on albs.songId = s.songId
    where a.albumId = 1;
-- finds band members 
select a.albumId as "Album ID", b.bandId as "Band ID",a.year as "Album Year",a.number as "Album Number", a.title as "Album Title", b.name as "Band Name", m.firstName, m.lastName from Band b
	join Album a on a.bandId = b.bandId
    join BandMember bm on bm.bandId = b.bandId
    join Musician m on m.musicianId = bm.musicianId
    where a.albumId = 1;


