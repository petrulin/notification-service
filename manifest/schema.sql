CREATE ROLE notification_service WITH
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    LOGIN
    NOREPLICATION
    NOBYPASSRLS
    CONNECTION LIMIT -1
    PASSWORD 'gISsOUNlUW';


CREATE SCHEMA notification_service AUTHORIZATION notification_service;


-- Permissions

GRANT ALL ON SCHEMA notification_service TO notification_service;

CREATE TABLE notification_service.message (
	uuid varchar(100) NOT NULL,
	CONSTRAINT message_pkey PRIMARY KEY (uuid)
);

ALTER TABLE notification_service.message OWNER TO notification_service;
GRANT ALL ON TABLE notification_service.message TO notification_service;


CREATE TABLE notification_service.mail_message (
   id bigserial NOT NULL,
   created timestamp(6) NOT NULL,
   message varchar(4000) NOT NULL,
   email varchar(200) NOT NULL,
   user_name varchar(50) NOT NULL,
   status varchar(50) NOT NULL,
   CONSTRAINT notification_pkey PRIMARY KEY (id)
);

ALTER TABLE notification_service.mail_message OWNER TO notification_service;
GRANT ALL ON TABLE notification_service.mail_message TO notification_service;
