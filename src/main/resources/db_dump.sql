--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10
-- Dumped by pg_dump version 10.10

-- Started on 2019-10-07 00:40:44

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

CREATE SCHEMA trucking_company;

ALTER SCHEMA trucking_company OWNER TO postgres;

COMMENT ON SCHEMA trucking_company IS 'standard public schema';

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE trucking_company.addresses (
    id integer NOT NULL,
    country character varying(15) DEFAULT 'Беларусь'::character varying NOT NULL,
    district character varying(45) DEFAULT 'Минский'::character varying NOT NULL,
    city character varying(20) NOT NULL,
    street character varying(40) DEFAULT '-'::character varying,
    housenum integer DEFAULT 0,
    building character varying(3) DEFAULT '-'::character varying
);

ALTER TABLE trucking_company.addresses OWNER TO postgres;

CREATE SEQUENCE trucking_company.addresses_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.addresses_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.addresses_id_seq OWNED BY trucking_company.addresses.id;

CREATE TABLE trucking_company.car_state (
    id integer NOT NULL,
    state character varying(20) NOT NULL
);

ALTER TABLE trucking_company.car_state OWNER TO postgres;

CREATE SEQUENCE trucking_company.car_state_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.car_state_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.car_state_id_seq OWNED BY trucking_company.car_state.id;

CREATE TABLE trucking_company.car_status (
    id integer NOT NULL,
    status character varying(20) NOT NULL
);

ALTER TABLE trucking_company.car_status OWNER TO postgres;

CREATE SEQUENCE trucking_company.car_status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.car_status_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.car_status_id_seq OWNED BY trucking_company.car_status.id;

CREATE TABLE trucking_company.car_type (
    id integer NOT NULL,
    type character varying(20) NOT NULL
);

ALTER TABLE trucking_company.car_type OWNER TO postgres;

CREATE SEQUENCE trucking_company.car_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.car_type_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.car_type_id_seq OWNED BY trucking_company.car_type.id;

CREATE TABLE trucking_company.cars (
    id integer NOT NULL,
    car_number character varying(15) NOT NULL,
    type_id integer NOT NULL,
    model character varying(30) NOT NULL,
    seatsnumber smallint,
    capacity_id integer,
    status_id integer DEFAULT 1 NOT NULL,
    state_id integer DEFAULT 1 NOT NULL,
    driver_id integer
);

ALTER TABLE trucking_company.cars OWNER TO postgres;

CREATE SEQUENCE trucking_company.cars_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.cars_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.cars_id_seq OWNED BY trucking_company.cars.id;

CREATE TABLE trucking_company.cars_in_routes (
    id integer NOT NULL,
    route_id integer NOT NULL,
    car_id integer NOT NULL
);

ALTER TABLE trucking_company.cars_in_routes OWNER TO postgres;

CREATE SEQUENCE trucking_company.cars_in_routes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.cars_in_routes_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.cars_in_routes_id_seq OWNED BY trucking_company.cars_in_routes.id;

CREATE TABLE trucking_company.customer_type (
    id integer NOT NULL,
    type character varying(20) NOT NULL
);

ALTER TABLE trucking_company.customer_type OWNER TO postgres;

CREATE SEQUENCE trucking_company.customer_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.customer_type_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.customer_type_id_seq OWNED BY trucking_company.customer_type.id;

CREATE TABLE trucking_company.customers (
    id integer NOT NULL,
    type_id integer NOT NULL,
    name character varying(25) NOT NULL,
    surname character varying(25) NOT NULL,
    phone character varying(20) NOT NULL,
    email character varying(25) NOT NULL,
    company_name character varying(45) DEFAULT '-'::character varying
);

ALTER TABLE trucking_company.customers OWNER TO postgres;

CREATE SEQUENCE trucking_company.customers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.customers_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.customers_id_seq OWNED BY trucking_company.customers.id;

CREATE TABLE trucking_company.drivers (
    id integer NOT NULL,
    surname character varying(20) NOT NULL,
    name character varying(20) NOT NULL,
    phone character varying(20) NOT NULL
);

ALTER TABLE trucking_company.drivers OWNER TO postgres;

CREATE SEQUENCE trucking_company.drivers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.drivers_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.drivers_id_seq OWNED BY trucking_company.drivers.id;

CREATE TABLE trucking_company.request_status (
    id integer NOT NULL,
    status character varying(20) NOT NULL
);

ALTER TABLE trucking_company.request_status OWNER TO postgres;

CREATE SEQUENCE trucking_company.request_status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.request_status_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.request_status_id_seq OWNED BY trucking_company.request_status.id;

CREATE TABLE trucking_company.requests (
    id integer NOT NULL,
    customer_id integer NOT NULL,
    status_id integer NOT NULL,
    request_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    comment character varying(1000) DEFAULT NULL::character varying
);

ALTER TABLE trucking_company.requests OWNER TO postgres;

CREATE SEQUENCE trucking_company.requests_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.requests_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.requests_id_seq OWNED BY trucking_company.requests.id;

CREATE TABLE trucking_company.role (
    id integer NOT NULL,
    role_name character varying(10) NOT NULL
);

ALTER TABLE trucking_company.role OWNER TO postgres;

CREATE SEQUENCE trucking_company.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.role_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.role_id_seq OWNED BY trucking_company.role.id;

CREATE TABLE trucking_company.route_status (
    id integer NOT NULL,
    status character varying(15) NOT NULL
);

ALTER TABLE trucking_company.route_status OWNER TO postgres;

CREATE SEQUENCE trucking_company.route_status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.route_status_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.route_status_id_seq OWNED BY trucking_company.route_status.id;

CREATE TABLE trucking_company.routes (
    id integer NOT NULL,
    name character varying(45) NOT NULL,
    status_id integer DEFAULT 1 NOT NULL
);

ALTER TABLE trucking_company.routes OWNER TO postgres;

CREATE SEQUENCE trucking_company.routes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.routes_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.routes_id_seq OWNED BY trucking_company.routes.id;

CREATE TABLE trucking_company.tasks (
    id integer NOT NULL,
    route_id integer,
    address_id integer,
    "time" timestamp without time zone NOT NULL,
    details character varying(100) NOT NULL
);

ALTER TABLE trucking_company.tasks OWNER TO postgres;

CREATE SEQUENCE trucking_company.tasks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.tasks_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.tasks_id_seq OWNED BY trucking_company.tasks.id;

CREATE TABLE trucking_company.truck_capacity (
    id integer NOT NULL,
    "capacity, ton" integer NOT NULL
);

ALTER TABLE trucking_company.truck_capacity OWNER TO postgres;

CREATE SEQUENCE trucking_company.truck_capacity_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.truck_capacity_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.truck_capacity_id_seq OWNED BY trucking_company.truck_capacity.id;

CREATE TABLE trucking_company.users (
    id integer NOT NULL,
    login character varying(20) NOT NULL,
    role_id integer NOT NULL,
    password bytea
);

ALTER TABLE trucking_company.users OWNER TO postgres;

CREATE SEQUENCE trucking_company.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE trucking_company.users_id_seq OWNER TO postgres;

ALTER SEQUENCE trucking_company.users_id_seq OWNED BY trucking_company.users.id;

ALTER TABLE ONLY trucking_company.addresses ALTER COLUMN id SET DEFAULT nextval('trucking_company.addresses_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.car_state ALTER COLUMN id SET DEFAULT nextval('trucking_company.car_state_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.car_status ALTER COLUMN id SET DEFAULT nextval('trucking_company.car_status_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.car_type ALTER COLUMN id SET DEFAULT nextval('trucking_company.car_type_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.cars ALTER COLUMN id SET DEFAULT nextval('trucking_company.cars_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.cars_in_routes ALTER COLUMN id SET DEFAULT nextval('trucking_company.cars_in_routes_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.customer_type ALTER COLUMN id SET DEFAULT nextval('trucking_company.customer_type_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.customers ALTER COLUMN id SET DEFAULT nextval('trucking_company.customers_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.drivers ALTER COLUMN id SET DEFAULT nextval('trucking_company.drivers_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.request_status ALTER COLUMN id SET DEFAULT nextval('trucking_company.request_status_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.requests ALTER COLUMN id SET DEFAULT nextval('trucking_company.requests_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.role ALTER COLUMN id SET DEFAULT nextval('trucking_company.role_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.route_status ALTER COLUMN id SET DEFAULT nextval('trucking_company.route_status_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.routes ALTER COLUMN id SET DEFAULT nextval('trucking_company.routes_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.tasks ALTER COLUMN id SET DEFAULT nextval('trucking_company.tasks_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.truck_capacity ALTER COLUMN id SET DEFAULT nextval('trucking_company.truck_capacity_id_seq'::regclass);

ALTER TABLE ONLY trucking_company.users ALTER COLUMN id SET DEFAULT nextval('trucking_company.users_id_seq'::regclass);

INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (1, 'Беларусь', 'Минский', 'Минск', 'Скорины', 18, '-');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (2, 'Беларусь', 'Минский', 'пос.Солнечный', '-', NULL, '-');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (3, 'Беларусь', 'Минский', 'Заславль', 'Уваровой', 4, '-');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (4, 'Беларусь', 'Минский', 'Минск', 'Орловская', 39, '2');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (5, 'Беларусь', 'Минский', 'Жодино', 'Землестроителей', 18, '-');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (6, 'Беларусь', 'Минский', 'Борисов', 'Победы пр.', 34, '1');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (7, 'Беларусь', 'Минский', 'Минск', 'Газеты Звезда пр.', 41, '-');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (8, 'Беларусь', 'Минский', 'Минск', 'Оранжерейная', 10, '-');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (9, 'Беларусь', 'Минский', 'Минск', 'Аэропорт Минск-2', NULL, '-');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (11, 'Беларусь', 'Минский', 'г.п.Раков', 'Минская', 12, '-');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (12, 'Беларусь', 'Минский', 'Минск', 'Руссиянова', 18, '3');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (13, 'Беларусь', 'Минский', 'Минск', 'Карбышева', 1, '3');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (14, 'Беларусь', 'Минский', 'Минск', 'Толбухина', 2, '-');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (33, 'Беларусь', 'Минский', 'Минск', 'пр.Независимости', 0, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (34, 'Беларусь', 'Минский', 'Минск', 'пр.Независимости', 164, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (35, 'Беларусь', 'Гомельский', 'Гомель', 'Гагарина', 0, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (36, 'Беларусь', 'Минский', 'Минск', 'ДС \"Дружная\" 1', 0, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (37, 'Беларусь', 'Минский', 'Минск', 'ДС \"Дружная\" 1', 0, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (39, 'Беларусь', 'Минский', 'Минск', 'ДС \"Дружная\"', 0, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (40, 'Беларусь', 'Минский', 'Минск', 'ДС \"Дружная\"', 0, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (41, 'Беларусь', 'Минский', 'Минск', 'ДС \"Дружная\"', 0, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (42, 'Беларусь', 'Минский', 'Минск', 'ДС \"Дружная\"', 0, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (43, 'Беларусь', 'Минский', 'Минск', 'пр.Независимости', 164, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (44, 'Беларусь', 'Минский', 'Минск', 'пр.Независимости', 164, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (45, 'Беларусь', 'Минский', 'Минск', 'пр.Независимости', 164, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (46, 'Беларусь', 'Минский', 'Минск', 'ДС \"Дружная\"', 0, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (47, 'Беларусь', 'Минский', 'Минск', 'пр.Независимости', 164, '2');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (48, 'Беларусь', 'Минский', 'Минск', 'пр.Независимости', 164, '2');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (49, 'Беларусь', 'Минский', 'Минск', 'пр.Независимости', 164, '2');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (50, 'Беларусь', 'Минский', 'Минск', 'пр.Независимости', 164, '2');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (51, 'Беларусь', 'Минский', 'Минск', 'ДС \"Дружная\" 1', 0, '');
INSERT INTO trucking_company.addresses (id, country, district, city, street, housenum, building) VALUES (52, 'Беларусь', 'Минский', 'Минск', 'пр.Независимости', 164, '');


INSERT INTO trucking_company.car_state (id, state) VALUES (1, 'нормальное');
INSERT INTO trucking_company.car_state (id, state) VALUES (2, 'требуется осмотр');
INSERT INTO trucking_company.car_state (id, state) VALUES (3, 'требуется ремонт');
INSERT INTO trucking_company.car_state (id, state) VALUES (4, 'аварийное');


INSERT INTO trucking_company.car_status (id, status) VALUES (1, 'готов к отправке');
INSERT INTO trucking_company.car_status (id, status) VALUES (2, 'в рейсе');
INSERT INTO trucking_company.car_status (id, status) VALUES (3, 'в ремонте');


INSERT INTO trucking_company.car_type (id, type) VALUES (1, 'автобус');
INSERT INTO trucking_company.car_type (id, type) VALUES (2, 'грузовик');


INSERT INTO trucking_company.cars (id, car_number, type_id, model, seatsnumber, capacity_id, status_id, state_id, driver_id) VALUES (1, '145 DD', 1, 'МАЗ-241', 22, NULL, 1, 1, 3);
INSERT INTO trucking_company.cars (id, car_number, type_id, model, seatsnumber, capacity_id, status_id, state_id, driver_id) VALUES (2, '874 HH', 1, 'МАЗ-251', 47, NULL, 1, 1, 2);
INSERT INTO trucking_company.cars (id, car_number, type_id, model, seatsnumber, capacity_id, status_id, state_id, driver_id) VALUES (3, '665 OG', 1, 'МАЗ-251', 47, NULL, 3, 3, 6);
INSERT INTO trucking_company.cars (id, car_number, type_id, model, seatsnumber, capacity_id, status_id, state_id, driver_id) VALUES (4, '996 TY', 2, 'ЗИЛ-130', NULL, 1, 1, 2, 4);
INSERT INTO trucking_company.cars (id, car_number, type_id, model, seatsnumber, capacity_id, status_id, state_id, driver_id) VALUES (5, '455 WW', 2, 'КАМАЗ 65206', NULL, 4, 1, 1, 5);


INSERT INTO trucking_company.cars_in_routes (id, route_id, car_id) VALUES (1, 1, 2);
INSERT INTO trucking_company.cars_in_routes (id, route_id, car_id) VALUES (2, 2, 5);
INSERT INTO trucking_company.cars_in_routes (id, route_id, car_id) VALUES (3, 3, 4);
INSERT INTO trucking_company.cars_in_routes (id, route_id, car_id) VALUES (4, 4, 4);
INSERT INTO trucking_company.cars_in_routes (id, route_id, car_id) VALUES (5, 5, 1);
INSERT INTO trucking_company.cars_in_routes (id, route_id, car_id) VALUES (7, 7, 2);
INSERT INTO trucking_company.cars_in_routes (id, route_id, car_id) VALUES (8, 8, 2);
INSERT INTO trucking_company.cars_in_routes (id, route_id, car_id) VALUES (25, 10, 1);
INSERT INTO trucking_company.cars_in_routes (id, route_id, car_id) VALUES (26, 10, 3);
INSERT INTO trucking_company.cars_in_routes (id, route_id, car_id) VALUES (29, 6, 3);


INSERT INTO trucking_company.customer_type (id, type) VALUES (1, 'юр.лицо');
INSERT INTO trucking_company.customer_type (id, type) VALUES (2, 'физ.лицо');


INSERT INTO trucking_company.customers (id, type_id, name, surname, phone, email, company_name) VALUES (9, 2, 'Борис', 'Сергейчук', '8745961', 'sergey_W_sergey@yandex.ru', '-');
INSERT INTO trucking_company.customers (id, type_id, name, surname, phone, email, company_name) VALUES (11, 2, 'Елена', 'Авдеенко', '(29)111-11-22', 'avdei_lena@gmail.com', '');
INSERT INTO trucking_company.customers (id, type_id, name, surname, phone, email, company_name) VALUES (7, 1, 'Андрей', 'Баденков', '1234567', 'a.badenkov_1@gmail.com', 'ОАО "Сантехгарант"');
INSERT INTO trucking_company.customers (id, type_id, name, surname, phone, email, company_name) VALUES (8, 1, 'Алексей', 'Дерюгин', '6587421', 'alex_der@yahoo.com', 'ЗАО "Внешдомстрой"');
INSERT INTO trucking_company.customers (id, type_id, name, surname, phone, email, company_name) VALUES (10, 1, 'Татьяна', 'Андреенко', '(29)697-45-12', 'smalltalk39@gmail.com', 'ОАО "Белмедпрепараты"');


INSERT INTO trucking_company.drivers (id, surname, name, phone) VALUES (2, 'Михайлов', 'Денис', '2547964');
INSERT INTO trucking_company.drivers (id, surname, name, phone) VALUES (3, 'Петров', 'Сергей', '4258963');
INSERT INTO trucking_company.drivers (id, surname, name, phone) VALUES (4, 'Врубель', 'Евгений', '1257852');
INSERT INTO trucking_company.drivers (id, surname, name, phone) VALUES (5, 'Панасюк', 'Михаил', '2571541');
INSERT INTO trucking_company.drivers (id, surname, name, phone) VALUES (6, 'Мерешко', 'Дмитрий', '2475488');


INSERT INTO trucking_company.request_status (id, status) VALUES (1, 'рассматривается');
INSERT INTO trucking_company.request_status (id, status) VALUES (2, 'принята');
INSERT INTO trucking_company.request_status (id, status) VALUES (3, 'отклонена');


INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (1, 7, 2, '2018-09-04 21:00:00', '-');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (2, 8, 2, '2018-10-08 21:00:00', '-');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (3, 7, 2, '2018-10-24 21:00:00', '-');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (4, 9, 2, '2018-12-19 21:00:00', '-');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (5, 10, 2, '2019-01-11 21:00:00', NULL);
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (6, 11, 2, '2019-07-02 21:00:00', NULL);
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (7, 10, 2, '2019-07-07 21:00:00', NULL);
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (8, 9, 2, '2019-07-31 21:00:00', NULL);
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (9, 10, 1, '2019-08-12 21:00:00', 'Привет! Привет! Как дела?');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (10, 10, 2, '2019-08-12 21:00:00', 'Hello!');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (12, 10, 3, '2019-08-12 21:00:00', 'еще одна заявка!!!!');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (13, 10, 1, '2019-08-12 21:00:00', 'Заявка, заявка, заявка.....');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (14, 10, 3, '2019-08-12 21:00:00', '');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (15, 9, 1, '2019-08-13 21:00:00', '1234567 хороший год');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (16, 7, 3, '2019-08-12 21:00:00', 'заявка отклоненная');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (17, 10, 1, '2019-09-17 21:00:00', '29.09 нужна машина для переезда с пр.Машерова на ул.Энгельса');
INSERT INTO trucking_company.requests (id, customer_id, status_id, request_date, comment) VALUES (18, 11, 1, '2019-09-17 21:00:00', '29.09 нужна машина для переезда с пр.Машерова на ул.Бядули');

INSERT INTO trucking_company.role (id, role_name) VALUES (1, 'admin');
INSERT INTO trucking_company.role (id, role_name) VALUES (2, 'driver');
INSERT INTO trucking_company.role (id, role_name) VALUES (3, 'customer');


INSERT INTO trucking_company.route_status (id, status) VALUES (1, 'запланирован');
INSERT INTO trucking_company.route_status (id, status) VALUES (2, 'на выполнении');
INSERT INTO trucking_company.route_status (id, status) VALUES (3, 'выполнен');


INSERT INTO trucking_company.routes (id, name, status_id) VALUES (1, 'Пассажироперевозки БО Солнечный', 3);
INSERT INTO trucking_company.routes (id, name, status_id) VALUES (2, 'Грузоперевозки стройматериалы', 3);
INSERT INTO trucking_company.routes (id, name, status_id) VALUES (3, 'Грузоперевозки сантехника', 3);
INSERT INTO trucking_company.routes (id, name, status_id) VALUES (4, 'Перевозка мебели', 3);
INSERT INTO trucking_company.routes (id, name, status_id) VALUES (5, 'Перевозка пассажиров', 3);
INSERT INTO trucking_company.routes (id, name, status_id) VALUES (6, 'Пассажироперевозки г.п.Раков', 2);
INSERT INTO trucking_company.routes (id, name, status_id) VALUES (7, 'Пассажироперевозки Аэропорт Минск-2', 1);
INSERT INTO trucking_company.routes (id, name, status_id) VALUES (8, 'Пассажироперевозки Минск', 1);
INSERT INTO trucking_company.routes (id, name, status_id) VALUES (10, 'New route', 1);


INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (1, 1, 1, '2018-09-18 10:30:00', 'Посадка пассажиров, ожидание');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (2, 1, 2, '2018-09-18 12:00:00', 'Прибытие на БО, высадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (3, 1, 2, '2018-09-18 22:00:00', 'Посадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (4, 1, 1, '2018-09-18 23:00:00', 'Прибытие в место отправления, высадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (7, 2, 3, '2018-10-15 10:00:00', 'Погрузка товара, регистрация');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (8, 2, 4, '2018-10-15 13:00:00', 'Выгрузка товара, регистрация');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (9, 3, 5, '2018-11-08 10:00:00', 'Погрузка товара, получение накладной');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (10, 3, 1, '2018-11-08 13:00:00', 'Передача товара, передача накладной');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (11, 4, 6, '2019-01-12 10:00:00', 'Погрузка мебели, ожидание');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (12, 4, 7, '2019-01-12 14:00:00', 'Выгрузка мебели');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (17, 5, 8, '2019-02-03 10:00:00', 'Посадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (18, 5, 9, '2019-02-03 12:00:00', 'Прибытие в аэропорт, выгрузка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (19, 5, 9, '2019-02-03 16:00:00', 'Посадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (20, 5, 8, '2019-02-03 18:00:00', 'Прибытие в место отправления');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (21, 6, 42, '2019-08-13 12:00:00', 'Посадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (22, 6, 11, '2019-08-13 13:00:00', 'Высадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (23, 6, 11, '2019-08-13 22:00:00', 'Посадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (25, 7, 8, '2019-09-02 11:00:00', 'Посадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (26, 7, 9, '2019-09-02 13:00:00', 'Прибытие в аэропорт, выгрузка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (27, 7, 9, '2019-09-02 17:00:00', 'Посадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (28, 7, 8, '2019-09-02 19:00:00', 'Прибытие в место отправления');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (29, 8, 12, '2019-10-05 12:00:00', 'Забрать пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (30, 8, 13, '2019-10-05 13:00:00', 'Высадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (31, 8, 13, '2019-10-05 14:00:00', 'Забрать пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (32, 8, 14, '2019-10-05 16:00:00', 'Высадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (57, 10, 34, '2019-09-01 00:00:00', 'Забрать пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (58, 10, 35, '2019-09-01 12:00:00', 'Высадка пассажиров');
INSERT INTO trucking_company.tasks (id, route_id, address_id, "time", details) VALUES (62, 6, 46, '2019-08-13 23:00:00', 'Высадка пассажиров');


INSERT INTO trucking_company.truck_capacity (id, "capacity, ton") VALUES (1, 6);
INSERT INTO trucking_company.truck_capacity (id, "capacity, ton") VALUES (2, 10);
INSERT INTO trucking_company.truck_capacity (id, "capacity, ton") VALUES (3, 15);
INSERT INTO trucking_company.truck_capacity (id, "capacity, ton") VALUES (4, 0);


INSERT INTO trucking_company.users (id, login, role_id, password) VALUES (1, 'admin', 1, '\x1d5c62404bac08a100f7ad0a676fb2ed');
INSERT INTO trucking_company.users (id, login, role_id, password) VALUES (2, 'd.mihailov', 2, '\x5ab64efa2db2411799849c13e12182ac');
INSERT INTO trucking_company.users (id, login, role_id, password) VALUES (3, 's.petrov', 2, '\x9e385edf8dd12fc7b86efb3d7cac1d1d');
INSERT INTO trucking_company.users (id, login, role_id, password) VALUES (4, 'e.vrubel', 2, '\xfe4cb71e6ab056695da3c0c672a1ae7e');
INSERT INTO trucking_company.users (id, login, role_id, password) VALUES (5, 'm.panasiuk', 2, '\x5450c11f7b7a3b0bab119169b2ba7562');
INSERT INTO trucking_company.users (id, login, role_id, password) VALUES (6, 'd.mereshko', 2, '\xc8daa35b04c02a60ef7bbef83b2684a2');
INSERT INTO trucking_company.users (id, login, role_id, password) VALUES (7, 'santexgarant', 3, '\x5dc73be87f793f43b3bc12d68f97c7b1');
INSERT INTO trucking_company.users (id, login, role_id, password) VALUES (8, 'outerHouse', 3, '\x41dc7728b020316ca8537b321bb99065');
INSERT INTO trucking_company.users (id, login, role_id, password) VALUES (9, 'sergey009', 3, '\xca0cc4d33faa985d99bbd1d82b088596');
INSERT INTO trucking_company.users (id, login, role_id, password) VALUES (10, 'tatianaA', 3, '\x8cce5219ccf978dd270d77677fa6b3ad');
INSERT INTO trucking_company.users (id, login, role_id, password) VALUES (11, 'AvdeenkoE', 3, '\x416e5dd03e90f7cb722784802f1d1f48');


SELECT pg_catalog.setval('trucking_company.addresses_id_seq', 52, true);


SELECT pg_catalog.setval('trucking_company.car_state_id_seq', 4, true);


SELECT pg_catalog.setval('trucking_company.car_status_id_seq', 3, true);


SELECT pg_catalog.setval('trucking_company.car_type_id_seq', 2, true);


SELECT pg_catalog.setval('trucking_company.cars_id_seq', 5, true);


SELECT pg_catalog.setval('trucking_company.cars_in_routes_id_seq', 29, true);


SELECT pg_catalog.setval('trucking_company.customer_type_id_seq', 2, true);


SELECT pg_catalog.setval('trucking_company.customers_id_seq', 11, true);


SELECT pg_catalog.setval('trucking_company.drivers_id_seq', 6, true);


SELECT pg_catalog.setval('trucking_company.request_status_id_seq', 3, true);


SELECT pg_catalog.setval('trucking_company.requests_id_seq', 18, true);


SELECT pg_catalog.setval('trucking_company.role_id_seq', 3, true);


SELECT pg_catalog.setval('trucking_company.route_status_id_seq', 3, true);


SELECT pg_catalog.setval('trucking_company.routes_id_seq', 10, true);


SELECT pg_catalog.setval('trucking_company.tasks_id_seq', 62, true);


SELECT pg_catalog.setval('trucking_company.truck_capacity_id_seq', 4, true);


SELECT pg_catalog.setval('trucking_company.users_id_seq', 11, true);


ALTER TABLE ONLY trucking_company.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.car_state
    ADD CONSTRAINT car_state_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.car_status
    ADD CONSTRAINT car_status_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.car_type
    ADD CONSTRAINT car_type_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_car_number_key UNIQUE (car_number);


ALTER TABLE ONLY trucking_company.cars_in_routes
    ADD CONSTRAINT cars_in_routes_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.customer_type
    ADD CONSTRAINT customer_type_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.drivers
    ADD CONSTRAINT drivers_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.request_status
    ADD CONSTRAINT request_status_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.requests
    ADD CONSTRAINT requests_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.route_status
    ADD CONSTRAINT route_status_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.routes
    ADD CONSTRAINT routes_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.truck_capacity
    ADD CONSTRAINT truck_capacity_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_capacity_id_fkey FOREIGN KEY (capacity_id) REFERENCES trucking_company.truck_capacity(id);


ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_driver_id_fkey FOREIGN KEY (driver_id) REFERENCES trucking_company.drivers(id) ON DELETE SET NULL;


ALTER TABLE ONLY trucking_company.cars_in_routes
    ADD CONSTRAINT cars_in_routes_car_id_fkey FOREIGN KEY (car_id) REFERENCES trucking_company.cars(id);


ALTER TABLE ONLY trucking_company.cars_in_routes
    ADD CONSTRAINT cars_in_routes_route_id_fkey FOREIGN KEY (route_id) REFERENCES trucking_company.routes(id) ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_state_id_fkey FOREIGN KEY (state_id) REFERENCES trucking_company.car_state(id);


ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_status_id_fkey FOREIGN KEY (status_id) REFERENCES trucking_company.car_status(id);


ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_type_id_fkey FOREIGN KEY (type_id) REFERENCES trucking_company.car_type(id);


ALTER TABLE ONLY trucking_company.customers
    ADD CONSTRAINT customers_id_fkey FOREIGN KEY (id) REFERENCES trucking_company.users(id) ON UPDATE CASCADE;


ALTER TABLE ONLY trucking_company.customers
    ADD CONSTRAINT customers_type_id_fkey FOREIGN KEY (type_id) REFERENCES trucking_company.customer_type(id);


ALTER TABLE ONLY trucking_company.drivers
    ADD CONSTRAINT drivers_id_fkey FOREIGN KEY (id) REFERENCES trucking_company.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE ONLY trucking_company.requests
    ADD CONSTRAINT requests_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES trucking_company.customers(id);


ALTER TABLE ONLY trucking_company.requests
    ADD CONSTRAINT requests_status_id_fkey FOREIGN KEY (status_id) REFERENCES trucking_company.request_status(id);


ALTER TABLE ONLY trucking_company.routes
    ADD CONSTRAINT routes_id_fkey FOREIGN KEY (id) REFERENCES trucking_company.requests(id) ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE ONLY trucking_company.routes
    ADD CONSTRAINT routes_status_id_fkey FOREIGN KEY (status_id) REFERENCES trucking_company.route_status(id) ON UPDATE CASCADE;


ALTER TABLE ONLY trucking_company.tasks
    ADD CONSTRAINT tasks_address_id_fkey FOREIGN KEY (address_id) REFERENCES trucking_company.addresses(id) ON UPDATE CASCADE ON DELETE SET NULL;


ALTER TABLE ONLY trucking_company.tasks
    ADD CONSTRAINT tasks_route_id_fkey FOREIGN KEY (route_id) REFERENCES trucking_company.routes(id) ON UPDATE CASCADE ON DELETE CASCADE;


ALTER TABLE ONLY trucking_company.users
    ADD CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES trucking_company.role(id);


-- Completed on 2019-10-07 00:40:44

--
-- PostgreSQL database dump complete
--

