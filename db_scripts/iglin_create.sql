DROP TABLE moviecountries CASCADE;
DROP TABLE movieactors CASCADE;
DROP TABLE moviedirectors CASCADE;
DROP TABLE director CASCADE;
DROP TABLE actor CASCADE;
DROP TABLE country CASCADE;
DROP TABLE moviegenres CASCADE;
DROP TABLE movie CASCADE;
DROP TABLE genre CASCADE;

CREATE TABLE genre (
	genreid serial PRIMARY KEY, 
	genre text NOT NULL);

CREATE TABLE movie (
	movieid serial PRIMARY KEY, 
	title text NOT NULL,
	releasedate date,
	poster text, 
	tagline text,
	details text,
	rating real,
	agerating int, 
	duration int);

CREATE TABLE moviegenres (
	movieid int REFERENCES genre,
	genreid int REFERENCES movie,
	PRIMARY KEY (movieid, genreid));

CREATE TABLE country (
	countryid serial PRIMARY KEY,
	country text NOT NULL);

CREATE TABLE actor (
	actorid serial PRIMARY KEY, 
	name text NOT NULL, 
	dob date,
	pic text, 
	countryid int REFERENCES country);

CREATE TABLE director (
	directorid serial PRIMARY KEY, 
	name text NOT NULL, 
	dob date,
	pic text, 
	countryid int REFERENCES country); 

CREATE TABLE moviedirectors (
	movieid int REFERENCES movie,
	directorid int REFERENCES director,
	PRIMARY KEY (movieid, directorid)
	); 

CREATE TABLE movieactors (
	movieid int REFERENCES movie,
	actorid int REFERENCES actor,
	PRIMARY KEY (movieid, actorid)
	); 

CREATE TABLE moviecountries (
	movieid int REFERENCES movie,
	countryid int REFERENCES country,
	PRIMARY KEY (movieid, countryid)
	); 
