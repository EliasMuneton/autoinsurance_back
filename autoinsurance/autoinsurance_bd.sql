create table user_roles (
	user_role_id int primary key,
	description varchar(200) not null
);

INSERT INTO public.user_roles
(user_role_id, description)
VALUES(1, 'Admin');

INSERT INTO public.user_roles
(user_role_id, description)
VALUES(2, 'Driver');

create table user_status (
	user_status_id int primary key,
	description varchar(200) not null
);

INSERT INTO public.user_status
(user_status_id, description)
VALUES(1, 'Available');

INSERT INTO public.user_status
(user_status_id, description)
VALUES(2, 'Not Available');


create table users (
	user_id serial primary key,
	user_role_id int not null default 2,
	email varchar(200) not null,
	pass varchar(500) not null,
	first_name varchar(200),
	last_name varchar(200),
	user_status_id int not null default 1,
	birthdate date,
	created_at timestamp,
	created_by int,
	updated_at timestamp,
	updated_by int,
	deleted_at timestamp,
	deleted_by int,
	foreign key (user_role_id) references user_roles (user_role_id),
	foreign key (user_status_id) references user_status (user_status_id)
);


create table brands (
	brand_id serial primary key,
	brand_name varchar(200) not null,
	created_at timestamp,
	created_by int,
	updated_at timestamp,
	updated_by int,
	deleted_at timestamp,
	deleted_by int
);

INSERT INTO public.brands
(brand_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES('Ford', null, null, null, null, null, null);

INSERT INTO public.brands
(brand_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES('Audi', null, null, null, null, null, null);

INSERT INTO public.brands
(brand_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES('Honda', null, null, null, null, null, null);

INSERT INTO public.brands
(brand_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES('Toyota', null, null, null, null, null, null);



create table models (
	model_id serial primary key,
	brand_id int not null,
	model_name varchar(200) not null,
	created_at timestamp,
	created_by int,
	updated_at timestamp,
	updated_by int,
	deleted_at timestamp,
	deleted_by int,
	foreign key (brand_id) references brands (brand_id)
);

INSERT INTO public.models
(brand_id, model_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES(1, 'Lobo', null, null, null, null, null, null);

INSERT INTO public.models
(brand_id, model_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES(1, 'Focus', null, null, null, null, null, null);

INSERT INTO public.models
(brand_id, model_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES(2, 'A3', null, null, null, null, null, null);

INSERT INTO public.models
(brand_id, model_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES(2, 'A4', null, null, null, null, null, null);

INSERT INTO public.models
(brand_id, model_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES(3, 'Civic', null, null, null, null, null, null);

INSERT INTO public.models
(brand_id, model_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES(3, 'CR-V', null, null, null, null, null, null);


INSERT INTO public.models
(brand_id, model_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES(4, 'Corolla', null, null, null, null, null, null);

INSERT INTO public.models
(brand_id, model_name, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES(4, 'Camry', null, null, null, null, null, null);

create table colors (
	color_id serial primary key,
	color_name varchar(200) not null,
	created_at timestamp,
	created_by int,
	updated_at timestamp,
	updated_by int,
	deleted_at timestamp,
	deleted_by int
);


INSERT INTO public.colors
(color_name)
VALUES('Blue');

INSERT INTO public.colors
(color_name)
VALUES('Red');

INSERT INTO public.colors
(color_name)
VALUES('Yellow');

INSERT INTO public.colors
(color_name)
VALUES('Black');

INSERT INTO public.colors
(color_name)
VALUES('White');

INSERT INTO public.colors
(color_name)
VALUES('Green');


create table vehicles (
	vehicle_id serial primary key,
	user_id int not null,
	model_id int not null,
	color_id int not null,
	vehicle_year int,
	licence_plate varchar(10) not null,
	description varchar(1000),
	created_at timestamp,
	created_by int,
	updated_at timestamp,
	updated_by int,
	deleted_at timestamp,
	deleted_by int,
	foreign key (user_id) references users (user_id),
	foreign key (model_id) references models (model_id),
	foreign key (color_id) references colors (color_id)
);

create table claim_status (
	claim_status_id int primary key,
	description varchar(200) not null,
	created_at timestamp,
	created_by int,
	updated_at timestamp,
	updated_by int,
	deleted_at timestamp,
	deleted_by int
);

INSERT INTO public.claim_status
(claim_status_id, description)
VALUES(1, 'Pending');


INSERT INTO public.claim_status
(claim_status_id, description)
VALUES(2, 'Attended');

create table claim_subjects (
	claim_subject_id serial primary key,
	description varchar(500) not null,
	created_at timestamp,
	created_by int,
	updated_at timestamp,
	updated_by int,
	deleted_at timestamp,
	deleted_by int
);


INSERT INTO public.claim_subjects
(description)
VALUES('Minor accidents');

INSERT INTO public.claim_subjects
(description)
VALUES('Damage to a windshield');

INSERT INTO public.claim_subjects
(description)
VALUES('Acts of vandalism');


INSERT INTO public.claim_subjects
(description)
VALUES(' Theft of a vehicle');


INSERT INTO public.claim_subjects
(description)
VALUES('Other');



create table claims (
	claim_id serial primary key,
	claim_subject_id int not null,
	claim_status_id int not null default 1,
	user_id int not null,
	description text not null,
	created_at timestamp,
	created_by int,
	updated_at timestamp,
	updated_by int,
	deleted_at timestamp,
	deleted_by int,
	foreign key (user_id) references users (user_id),
	foreign key (claim_subject_id) references claim_subjects (claim_subject_id),
	foreign key (claim_status_id) references claim_status (claim_status_id)
);


INSERT INTO public.users
(user_role_id, email, pass, first_name, last_name, user_status_id, birthdate, created_at, created_by, updated_at, updated_by, deleted_at, deleted_by)
VALUES(1, 'eliasmunetong@gmail.com', '$2a$10$dpvc.LcT/oh.G5m3mG/5QeBLm0xAlf0QD2aeyHkTbxvax1NkPUPjK', 'Elias', 'Muneton', 1, '1995-04-18', null, null, null, null, null, null);



