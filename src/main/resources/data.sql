-- insert into users (user_name, first_name, last_name, email, role, age, ssn) values ('stoneberg', 'Lee', 'YuPyeong', 'stoneberg@gmail.com', 'admin', '47', 'ssn-1134-3214');
-- insert into users (user_name, first_name, last_name, email, role, age, ssn) values ('president03', 'Kim', 'YoungSam', 'president03@gmail.com', 'user', '88', 'ssn-5214-8521');
-- insert into users (user_name, first_name, last_name, email, role, age, ssn) values ('hong2020', 'Hong', 'ManHee', 'hong2020@gmail.com', 'admin', '16', 'ssn-3652-6354');
-- insert into users (user_name, first_name, last_name, email, role, age, ssn) values ('zetlee', 'Yoon', 'JaeHo', 'zetlee@gmail.com', 'admin', '35', 'ssn-6321-5421');


-- add adress column
insert into users (user_name, first_name, last_name, email, role, age, ssn, address, created_at, modified_at) 
values ('stoneberg', 'Lee', 'YuPyeong', 'stoneberg@gmail.com', 'admin', '47', 'ssn-1134-3214', '711-2880 Nulla St. Mankato Mississippi 96522 (257) 563-7401', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into users (user_name, first_name, last_name, email, role, age, ssn, address, created_at, modified_at)  
values ('president03', 'Kim', 'YoungSam', 'president03@gmail.com', 'user', '88', 'ssn-5214-8521', 'Ap #867-859 Sit Rd. Azusa New York 39531 (793) 151-6230', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into users (user_name, first_name, last_name, email, role, age, ssn, address, created_at, modified_at)  
values ('hong2020', 'Hong', 'ManHee', 'hong2020@gmail.com', 'admin', '16', 'ssn-3652-6354', '191-103 Integer Rd. Corona New Mexico 08219 (404) 960-3807', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into users (user_name, first_name, last_name, email, role, age, ssn, address, created_at, modified_at)  
values ('zetlee', 'Yoon', 'JaeHo', 'zetlee@gmail.com', 'admin', '35', 'ssn-6321-5421', 'Ap #285-7193 Ullamcorper Avenue Amesbury HI 93373 (302) 259-2375', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


insert into orders (order_name, description, user_id, created_at, modified_at)  values ('AAA', 'Cosmetics', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into orders (order_name, description, user_id, created_at, modified_at)  values ('BBB', 'Noodles', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into orders (order_name, description, user_id, created_at, modified_at)  values ('CCC', 'Milks', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into orders (order_name, description, user_id, created_at, modified_at)  values ('AAA', 'Cosmetics', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into orders (order_name, description, user_id, created_at, modified_at)  values ('AAA', 'Cosmetics', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into orders (order_name, description, user_id, created_at, modified_at)  values ('BBB', 'Noodles', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into orders (order_name, description, user_id, created_at, modified_at)  values ('AAA', 'Cosmetics', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into orders (order_name, description, user_id, created_at, modified_at)  values ('BBB', 'Noodles', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into orders (order_name, description, user_id, created_at, modified_at)  values ('CCC', 'Milks', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into orders (order_name, description, user_id, created_at, modified_at)  values ('DDD', 'Tires', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

