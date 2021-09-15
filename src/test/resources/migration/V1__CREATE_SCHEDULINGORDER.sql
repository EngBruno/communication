CREATE TABLE communication.scheduling_order (
	id bigint NOT NULL,
	date_scheduling timestamp NOT NULL,
	cell_phone varchar(50) NOT NULL,
	email varchar(100) NOT NULL,
    message varchar(255) NOT NULL,
    status BOOLEAN NOT NULL,
    type_communication varchar(20) NOT NULL,
	CONSTRAINT scheduling_order_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE communication.scheduling_order_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
ALTER TABLE communication.scheduling_order ALTER COLUMN id SET DEFAULT NEXTVAL('communication.scheduling_order_id_seq'::regclass);