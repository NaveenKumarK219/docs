
---------------Users table contains all users data-----------------

CREATE TABLE users
(
    user_id serial,
    email character varying(50) NOT NULL,
    active boolean,
    last_name character varying(30),
    name character varying(30) NOT NULL,
    password character varying(255),
    role character varying(20),
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
);

INSERT INTO users (email,active,last_name,name,password,role) values('admin',true,'Kumar','Navin','$2a$10$pNOQHgXFKcfrqzdgsPI2oOBJlXaGiAWQqh8fy2sAV9cOtpzfiyBf.','ADMIN');

--------------Table Of Contents for storing filenames and titles----------

CREATE TABLE table_of_contents
(
    id serial,
    title character varying(30) NOT NULL,
    file_name character varying(200) NOT NULL,
    author character varying(50),
    edited_by character varying(50),
    created_dtm timestamp with time zone default now(),
    edited_dtm timestamp with time zone default now(),
    active boolean
);

create table docs_data(
      id serial,
      file_name varchar(200) not null ,
      content text
);

insert into  table_of_contents(title, file_name, author, edited_by, active) VALUES ('home', 'home', 'Navin', 'Navin', true);
insert into docs_data(file_name, content) values ('home', '# Docs App - Document Application

### Welcome To Docs App - Document Application
* This app is all about documentation, useful for any project to maintain their docs.
* Any project can easily use this app to create their own documentation application.
* We will release more features in each release.');

insert into  table_of_contents(title, file_name, author, edited_by, active) VALUES ('Welcome Page', 'welcomePage', 'Navin', 'Navin', true);

insert into docs_data(file_name, content) values ('welcomePage', '# Docs App - Documentation Application

## Welcome To Docs App
* Create your projects documentation application with docs app.
* Its simple to deploy and use.

### Project Setup Requirements
* Create a folder "docs" in your home path. Example /home/pc-user-name/docs
* Application provides you a default admin role with username as "admin@doc.com" and password as "123456",so that you can start the things.
* Before running the application please run the DataBase Script File (docs-app-DB-Script.sql) in your database.

### Start your Documentation App [Get-Started](/admin/app-settings)');
