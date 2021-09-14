CREATE TABLE communication.scheduling_order (
	id int8 NOT NULL,
	date_scheduling varchar(255) NOT NULL,
	receiver varchar(255) NOT NULL,
    mensage varchar(255) NOT NULL,
    type_communication varchar(255) NOT NULL,
	CONSTRAINT scheduling_order_pkey PRIMARY KEY (id)
);