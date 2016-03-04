DELETE FROM moviegenres;
DELETE FROM genre;
DELETE FROM moviecountries;
DELETE FROM movieactors;
DELETE FROM moviedirectors;
DELETE FROM movie;
DELETE FROM actor;
DELETE FROM director;
DELETE FROM country;

INSERT INTO country VALUES 
(1,'USA'), 
(2,'Dominican Republic'), 
(3,'Japan'), 
(4, 'Australia'), 
(5, 'UK'), 
(6, 'New Zeland'), 
(7, 'Norway'), 
(8, 'St Vincent and the Grenadines'), 
(9, 'Denmark'), 
(10, 'Germany'),
(11, 'Sweden');

INSERT INTO actor --(name, dob, pic, countryid) 
	VALUES 
	(1, 'Orlando Bloom', '13.01.1977', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/Orlando_Bloom_2014_Comic_Con_%28cropped%29.jpg/800px-Orlando_Bloom_2014_Comic_Con_%28cropped%29.jpg', 5), 
	(2, 'Geoffrey Rush', '06.07.1951', 'https://upload.wikimedia.org/wikipedia/commons/8/8b/Geoffrey_Rush_Cannes_2011.jpg', 4), 
	(3, 'Johnny Depp', '09.06.1963', 'http://st.kp.yandex.net/images/actor_iphone/iphone360_6245.jpg', 1), 
	(4, 'Elijah Wood', '28.01.1981', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/08/Elijah_Wood_FF_2014.jpg/800px-Elijah_Wood_FF_2014.jpg', 1), 
	(5, 'Cate Blanchett', '14.05.1969', 'https://upload.wikimedia.org/wikipedia/commons/6/64/Cate_Blanchett_Cannes_2015.jpg', 4);

INSERT INTO director --(name, dob, pic, countryid) 
	VALUES 
	(1, 'James Bobin', null, 'https://upload.wikimedia.org/wikipedia/commons/1/10/James_Bobin.jpg', 5), 
	(2, 'Tim Burton', '25.08.1958', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Tim_Burton_by_Gage_Skidmore.jpg/800px-Tim_Burton_by_Gage_Skidmore.jpg', 1), 
	(3, 'Peter Jackson', '31.10.1961', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Peter_Jackson_SDCC_2014.jpg/800px-Peter_Jackson_SDCC_2014.jpg', 6), 
	(4, 'Gore Verbinski', '16.03.1964', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b8/Gore_Verbinski_1.JPG/1024px-Gore_Verbinski_1.JPG', 1), 
	(5, 'Joachim Rønning', null, 'http://33.media.tumblr.com/tumblr_m9jcqb8mPj1rqtzyi.jpg', 7),
	(6, 'Espen Sandberg', '17.06.1971', 'http://www1.pictures.zimbio.com/gi/85th+Annual+Academy+Awards+Arrivals+gJ3DSU0Cz3kl.jpg', 7);

INSERT INTO movie VALUES 
	(1, 'Pirates of the Caribbean: The Curse of the Black Pearl', '28.06.2003', 
		'https://upload.wikimedia.org/wikipedia/en/0/0e/Pirates_of_the_Caribbean_movie.jpg', 'Prepare to be blown out of the water.', 
		$$Blacksmith Will Turner teams up with eccentric pirate "Captain" Jack Sparrow to save his love, the governor's daughter, from Jack's former pirate allies, who are now undead.$$, 
		8.1, 13, 143),
	(2, 'Pirates of the Caribbean: Dead Men Tell No Tales', '26.05.2017', 
		'https://upload.wikimedia.org/wikipedia/en/2/21/Pirates_of_the_Caribbean%2C_Dead_Men_Tell_No_Tales.jpg', null, 
		$$Captain Jack Sparrow searches for the trident of Poseidon.$$, 
		null, null, null),
	(3, 'Alice in Wonderland', '25.02.2010', 
		'https://upload.wikimedia.org/wikipedia/en/f/ff/Alice-In-Wonderland-Theatrical-Poster.jpg', 'Fantastic fun for the whole family', 
		$$Nineteen-year-old Alice returns to the magical world from her childhood adventure, where she reunites with her old friends and learns of her true destiny: to end the Red Queen's reign of terror.$$, 
		6.5, null, 108),
	(4, 'Alice Through the Looking Glass', '27.05.2016', 
		'http://cdn3-www.comingsoon.net/assets/uploads/2015/08/AliceThroughTheLookingGlass2.jpg', null, 
		$$Alice returns to the whimsical world of Wonderland and travels back in time to save the Mad Hatter.$$, 
		null, null, null),
	(5, 'Kon-Tiki', '24.08.2012', 
		'https://upload.wikimedia.org/wikipedia/en/0/0b/Kon-tiki_2012_Poster.jpg', 'Real adventure has no limits.', 
		$$Legendary explorer Thor Heyerdal's epic 4,300-mile crossing of the Pacific on a balsawood raft in 1947, in an effort to prove that it was possible for South Americans to settle in Polynesia in pre-Columbian times.$$, 
		7.2, 13, 114),
	(6, 'The Lord of the Rings: The Fellowship of the Ring', '01.03.2002', 
		'http://ia.media-imdb.com/images/M/MV5BNTEyMjAwMDU1OV5BMl5BanBnXkFtZTcwNDQyNTkxMw@@._V1__SX1537_SY723_.jpg', 'One Ring To Rule Them All.', 
		$$A meek Hobbit and eight companions set out on a journey to destroy the One Ring and the Dark Lord Sauron.$$, 
		8.8, 13, 178),
	(7, 'The Lord of the Rings: The Two Towers', '23.01.2003', 
		'http://ia.media-imdb.com/images/M/MV5BMTAyNDU0NjY4NTheQTJeQWpwZ15BbWU2MDk4MTY2Nw@@._V1__SX1537_SY723_.jpg', 'A New Power Is Rising.', 
		$$While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron's new ally, Saruman, and his hordes of Isengard.$$, 
		8.7, 13, 179);

INSERT INTO moviedirectors VALUES (1, 4), (2, 5), (2, 6), (5, 5), (5, 6), (3, 2), (4, 1), (6, 3), (7, 3);

INSERT INTO movieactors VALUES (1, 1), (2, 1), (6, 1), (7, 1), (1, 2), (1, 3), (2, 3), (3, 3), (4, 3), (6, 4), (7, 4), (6, 5), (7, 5);

INSERT INTO moviecountries VALUES (1, 1), (1, 2), (1, 8), (2, 1), (3, 1), (3, 5), (4, 1), (4, 5), (5, 5), (5, 7), (5, 9), (5, 10), (5, 11), (6, 1), (6, 6), (7, 1), (7, 6);

INSERT INTO genre VALUES (1, 'Fantasy'), (2, 'Action'), (3, 'Adventure'), (4, 'Comedy'), (5, 'Family'), (6, 'History'), (7, 'Drama');

INSERT INTO moviegenres VALUES (1, 1), (1, 2), (1, 3), (2, 2), (2, 3), (2, 4), (3, 1), (3, 3), (3, 5), (4, 1), (4, 3), (4, 5), (5, 3), (5, 6), (6, 1), (6, 7), (6, 3), (7, 1), (7, 7), (7, 3);

SELECT name FROM actor WHERE actorid IN 
(SELECT "a".actorid FROM movieactors "a" JOIN movie "b" 
ON "a".movieid = "b".movieid 
WHERE "b".title LIKE '%Pirates of the Caribbean%');

SELECT DISTINCT "a".title 
FROM movie "a" JOIN moviecountries "b" ON "a".movieid = "b".movieid 
WHERE "b".movieid IN (SELECT countryid FROM country WHERE country = 'Norway');

