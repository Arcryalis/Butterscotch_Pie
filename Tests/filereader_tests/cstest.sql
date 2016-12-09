-- reset everything
-- drop the cstest database
drop database if exists cstest;

-- and to create a new one
create database if not exists cstest;

-- use cstest database
use cstest;

-- create account table
create table account(
	username char(20),
	password char(20),
	firstname char(20),
	lastname char(20),	
	ukphonenum char(20),
	dob char(20),
	city char(20),
	profilefilepath char(50),
	lastlogin char(20),
	primary key (username)
);

-- add some test data into account
insert into account values ('u1', 'p1', 'f.name 1', 's.name 1', '01792', '2016-11-30 00:00:00', 'city 1', 'png.png', '28/11/16 20:45:00');
insert into account values ('u2', 'p2', 'f.name 2', 's.name 2', '02803', '1919-08-02 00:00:00', 'city 2', 'that.gif', '08/12/15 21:05:00');
insert into account values ('u3', 'p3', 'f.name 3', 's.name 3', '03914', '2006-06-04 00:00:00', 'city 3', ':c/totally/a/pic','15/3/13 16:29:00');
insert into account values ('Sev', 'p2', 'Sevan', 's.name 4', '59648', '2014-07-23 00:00:00', 'Megacity 4', 'picture4', '09/12/16 10:54:00');


-- create contacts table
create table contacts(
	sender char(20),
	reciever char(20),
	approved char(1),
	primary key (sender,reciever)
);

-- add some test data into contacts
insert into contacts values ('u1', 'u2', 'c');
insert into contacts values ('u1', 'u3', 'c');
insert into contacts values ('u2', 'u3', 'r');
insert into contacts values ('Sev', 'Tim', 'c');
insert into contacts values ('Sev', 'John', 'r');
insert into contacts values ('Prometheus', 'Michael', 'c');
insert into contacts values ('Michael', 'Anna', 'c');
insert into contacts values ('Arthur Dent', 'George', 'c');

-- create chat table
create table chat1(
	messagenum int,
	sender char(20),
	timesent char(20),
	messagetype char(1),	
	message char(100),
	description char(30),
	primary key (messagenum)
);

insert into chat1 values ('1', 'u1', '2016-11-29 23:59:00', 't', 'Hi all!', '');
insert into chat1 values ('2', 'u1', '2016-11-30 00:01:00', 't', 'Great to hear from you.', '');
insert into chat1 values ('3', 'u2', '2016-11-30 00:01:30', 'm', 'memes.wav', 'Listen to this.');
insert into chat1 values ('4', 'u3', '2016-11-30 00:25:55', 't', 'Lali-Ho!', '');
insert into chat1 values ('5', 'u1', '2016-11-30 01:00:00', 'm', 'totallyRealRadio.mp3', 'This is better');

-- create room table
create table room(
	roomname char(20),
	roomtype char(1),
	primary key (roomname)
);
	
insert into room values ('room1', 'c');
insert into room values ('room2', 'd');	
insert into room values ('room3', 'c');	

-- create table for room <-> account relations 
create table roommembers(
	roomname char(20),
	username char(20),
	primary key (roomname, username),
	foreign key (roomname) references room (roomname)
);

insert into roommembers values ('room1', 'u1');
insert into roommembers values ('room1', 'Sev');
insert into roommembers values ('room2', 'Sev');
insert into roommembers values ('room2', 'u2');
insert into roommembers values ('room2', 'u3');







