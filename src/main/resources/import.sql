insert into users (id,name,password,email,telephone) values(1,'Admin', '$2a$10$y3nKDWcXPJE5pXm5xfeZP.osdkokTeh9Fkc0TKl5pAy6X0tXQmCrK','system@localhost','+38(099)1458962');
insert into users (id,name,password,email,telephone) values(2,'User', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','system@localhost2','+38(099)8645738');

insert into roles (name) values ('ROLE_ADMIN');
insert into roles (name) values ('ROLE_USER');

insert into users_roles (user_id, roles_name) values (1,'ROLE_ADMIN')
insert into users_roles (user_id, roles_name) values (2,'ROLE_USER');

insert into month_budget (id, local_date, users_id) values (11, '2019-03-28', 1);
insert into month_budget (id, local_date, users_id) values (12, '2019-04-28', 2);
insert into month_budget (id, local_date, users_id) values (13, '2019-03-28', 1);
insert into month_budget (id, local_date, users_id) values (14, '2019-04-28', 2);

insert into income (id, description, amount, month_budget_id ) values (21, 'paymant', 10000, 11);
insert into income (id, description, amount, month_budget_id ) values (22, 'paymant', 10000, 12);
insert into income (id, description, amount, month_budget_id ) values (23, 'paymant', 10000, 13);
insert into income (id, description, amount, month_budget_id ) values (24, 'paymant', 10000, 14);
insert into income (id, description, amount, month_budget_id ) values (25, 'paymant', 10000, 11);

insert into expense (id, description, amount, month_budget_id) values (31, 'pay', 3000, 11);
insert into expense (id, description, amount, month_budget_id) values (32, 'pay', 3000, 12);
insert into expense (id, description, amount, month_budget_id) values (33, 'pay', 3000, 13);
insert into expense (id, description, amount, month_budget_id) values (34, 'pay', 3000, 14);
insert into expense (id, description, amount, month_budget_id) values (35, 'pay', 3000, 11);
