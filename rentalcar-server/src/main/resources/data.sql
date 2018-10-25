--USER
insert into user  values(1, sysdate(), 'jTailor@tailor.com', 'James', 'Tailor');
insert into user  values(1, sysdate(), 'jTailor@tailor.com', 'James', 'Tailor');

--CITY
insert into city values (1,'Berlin');

--LOCATION
insert into location values(1,'Auguststrasse 89, 12443', 'Branch1',1);
insert into location values(2,'Herderstrasse 9, 12443', 'Branch2',1);

--CAR
insert into car values (1, 'opel', 'grey','HH243AB', 12000, 'corsa',1);
insert into car values (2,'mercedes', 'white', 'HH433AB', 2000, 'Class A',2);
--EQUIPMENT
insert into equipment values(1,'GPS');
--CAR_EQUIPMENT
insert into car_equipment values(1,1);
--CATEGORY
insert into category values (1,'van');

--RENTAL
insert into rental values(1,' ',PARSEDATETIME('26 Jul 2018, 05:00:58 PM','dd MMM yyyy, hh:mm:ss a','en'),
PARSEDATETIME('26 Jul 2018, 09:00:00 AM','dd MMM yyyy, hh:mm:ss a','en'),1, 1, 1,1);

insert into rental  (id, description, start_date, end_date , drop_off_location_id , pick_up_location_id , car_id , user_id )
values(2,' ',PARSEDATETIME('25 Oct 2018, 5:30:00 PM','dd MMM yyyy, hh:mm:ss a','en'),
PARSEDATETIME('25 Oct 2018, 6:30:58 PM','dd MMM yyyy, hh:mm:ss a','en'),2, 2, 1,1);

insert into rental  (id, description, start_date, end_date , drop_off_location_id , pick_up_location_id , car_id , user_id )
values(5,' ',PARSEDATETIME('25 Oct 2018, 9:30:00 AM','dd MMM yyyy, hh:mm:ss a','en'),
PARSEDATETIME('25 Oct 2018, 2:30:58 PM','dd MMM yyyy, hh:mm:ss a','en'),2, 2, 1,1);




