drop database if exists churchmealfinder_test;
DROP OWNED BY churchmealfinder_test;
drop user if exists churchmealfinder_test;

drop database if exists churchmealfinder_dev;
DROP OWNED BY churchmealfinder_dev;
drop user if exists churchmealfinder_dev;

create database churchmealfinder_test;
create user churchmealfinder_test;
\c churchmealfinder_test
GRANT ALL PRIVILEGES ON schema public to churchmealfinder_test;

create database churchmealfinder_dev;
create user churchmealfinder_dev;
\c churchmealfinder_dev
GRANT ALL PRIVILEGES ON schema public to churchmealfinder_dev;
