create database cs209;

create table frameworks(
                           framework varchar(20),
                           frame_id integer primary key auto_increment
);

insert into frameworks (framework) values('Spring');
insert into frameworks (framework) values('Structs');
insert into frameworks (framework) values('Spark');
insert into frameworks (framework) values('GWT');
insert into frameworks (framework) values('DropWizard');
insert into frameworks (framework) values('Blade');
insert into frameworks (framework) values('Vaadin');
insert into frameworks (framework) values('JHipster');
insert into frameworks  values('Tapestry', 9);
insert into frameworks  values('Wicket', 10);
insert into frameworks  values('Hibernate', 11);
insert into frameworks  values('MyBatis', 12);

delete from frameworks where frame_id in (14, 15, 16, 17);

create table github_repos(
                             frame_id integer,
                             repo_name varchar(80),
                             full_name varchar(100) unique,
                             url varchar(100),
                             stars integer,
                             forks integer,
                             create_year integer,
                             create_month integer,
                             updated_date varchar(12),
                             description varchar(120),
                             gh_id integer primary key auto_increment,

                             foreign key(frame_id) references frameworks(frame_id)
);

create index year_index on github_repos(create_year);
create index frame_index on github_repos(frame_id);

truncate table github_repos;
drop table github_repos;
drop table commits;

create table commits(
                        gh_id integer,
                        num integer,
                        season integer,
                        cm_id integer primary key auto_increment,

                        foreign key(gh_id) references github_repos(gh_id)
);

# create table stackOverFlow_data(
#
# );

insert into github_repos (repo_name, url, stars, forks, create_date, updated_date, description) values ();

select * from github_repos where frame_id = 5;