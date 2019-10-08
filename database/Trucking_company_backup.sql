--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10
-- Dumped by pg_dump version 10.10

-- Started on 2019-09-16 20:15:28

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

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: trucking_company; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA trucking_company;


ALTER SCHEMA trucking_company OWNER TO postgres;

--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA trucking_company; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA trucking_company IS 'standard public schema';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 198 (class 1259 OID 24776)
-- Name: addresses; Type: TABLE; Schema: trucking_company; Owner: postgres
--

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

--
-- TOC entry 197 (class 1259 OID 24774)
-- Name: addresses_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.addresses_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.addresses_id_seq OWNER TO postgres;

--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 197
-- Name: addresses_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.addresses_id_seq OWNED BY trucking_company.addresses.id;


--
-- TOC entry 200 (class 1259 OID 24789)
-- Name: car_state; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.car_state (
    id integer NOT NULL,
    state character varying(20) NOT NULL
);


ALTER TABLE trucking_company.car_state OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 24787)
-- Name: car_state_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.car_state_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.car_state_id_seq OWNER TO postgres;

--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 199
-- Name: car_state_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.car_state_id_seq OWNED BY trucking_company.car_state.id;


--
-- TOC entry 202 (class 1259 OID 24797)
-- Name: car_status; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.car_status (
    id integer NOT NULL,
    status character varying(20) NOT NULL
);


ALTER TABLE trucking_company.car_status OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 24795)
-- Name: car_status_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.car_status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.car_status_id_seq OWNER TO postgres;

--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 201
-- Name: car_status_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.car_status_id_seq OWNED BY trucking_company.car_status.id;


--
-- TOC entry 204 (class 1259 OID 24805)
-- Name: car_type; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.car_type (
    id integer NOT NULL,
    type character varying(20) NOT NULL
);


ALTER TABLE trucking_company.car_type OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 24803)
-- Name: car_type_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.car_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.car_type_id_seq OWNER TO postgres;

--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 203
-- Name: car_type_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.car_type_id_seq OWNED BY trucking_company.car_type.id;


--
-- TOC entry 228 (class 1259 OID 25028)
-- Name: cars; Type: TABLE; Schema: trucking_company; Owner: postgres
--

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

--
-- TOC entry 227 (class 1259 OID 25026)
-- Name: cars_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.cars_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.cars_id_seq OWNER TO postgres;

--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 227
-- Name: cars_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.cars_id_seq OWNED BY trucking_company.cars.id;


--
-- TOC entry 230 (class 1259 OID 25065)
-- Name: cars_in_routes; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.cars_in_routes (
    id integer NOT NULL,
    route_id integer NOT NULL,
    car_id integer NOT NULL
);


ALTER TABLE trucking_company.cars_in_routes OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 25063)
-- Name: cars_in_routes_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.cars_in_routes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.cars_in_routes_id_seq OWNER TO postgres;

--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 229
-- Name: cars_in_routes_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.cars_in_routes_id_seq OWNED BY trucking_company.cars_in_routes.id;


--
-- TOC entry 206 (class 1259 OID 24830)
-- Name: customer_type; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.customer_type (
    id integer NOT NULL,
    type character varying(20) NOT NULL
);


ALTER TABLE trucking_company.customer_type OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 24828)
-- Name: customer_type_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.customer_type_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.customer_type_id_seq OWNER TO postgres;

--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 205
-- Name: customer_type_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.customer_type_id_seq OWNED BY trucking_company.customer_type.id;


--
-- TOC entry 218 (class 1259 OID 24935)
-- Name: customers; Type: TABLE; Schema: trucking_company; Owner: postgres
--

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

--
-- TOC entry 217 (class 1259 OID 24933)
-- Name: customers_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.customers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.customers_id_seq OWNER TO postgres;

--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 217
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.customers_id_seq OWNED BY trucking_company.customers.id;


--
-- TOC entry 220 (class 1259 OID 24954)
-- Name: drivers; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.drivers (
    id integer NOT NULL,
    surname character varying(20) NOT NULL,
    name character varying(20) NOT NULL,
    phone character varying(20) NOT NULL
);


ALTER TABLE trucking_company.drivers OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24952)
-- Name: drivers_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.drivers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.drivers_id_seq OWNER TO postgres;

--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 219
-- Name: drivers_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.drivers_id_seq OWNED BY trucking_company.drivers.id;


--
-- TOC entry 208 (class 1259 OID 24846)
-- Name: request_status; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.request_status (
    id integer NOT NULL,
    status character varying(20) NOT NULL
);


ALTER TABLE trucking_company.request_status OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 24844)
-- Name: request_status_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.request_status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.request_status_id_seq OWNER TO postgres;

--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 207
-- Name: request_status_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.request_status_id_seq OWNED BY trucking_company.request_status.id;


--
-- TOC entry 222 (class 1259 OID 24967)
-- Name: requests; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.requests (
    id integer NOT NULL,
    customer_id integer NOT NULL,
    status_id integer NOT NULL,
    request_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    comment character varying(1000) DEFAULT NULL::character varying
);


ALTER TABLE trucking_company.requests OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24965)
-- Name: requests_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.requests_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.requests_id_seq OWNER TO postgres;

--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 221
-- Name: requests_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.requests_id_seq OWNED BY trucking_company.requests.id;


--
-- TOC entry 210 (class 1259 OID 24854)
-- Name: role; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.role (
    id integer NOT NULL,
    role_name character varying(10) NOT NULL
);


ALTER TABLE trucking_company.role OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 24852)
-- Name: role_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.role_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.role_id_seq OWNER TO postgres;

--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 209
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.role_id_seq OWNED BY trucking_company.role.id;


--
-- TOC entry 212 (class 1259 OID 24862)
-- Name: route_status; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.route_status (
    id integer NOT NULL,
    status character varying(15) NOT NULL
);


ALTER TABLE trucking_company.route_status OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 24860)
-- Name: route_status_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.route_status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.route_status_id_seq OWNER TO postgres;

--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 211
-- Name: route_status_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.route_status_id_seq OWNED BY trucking_company.route_status.id;


--
-- TOC entry 224 (class 1259 OID 24991)
-- Name: routes; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.routes (
    id integer NOT NULL,
    name character varying(45) NOT NULL,
    status_id integer DEFAULT 1 NOT NULL
);


ALTER TABLE trucking_company.routes OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24989)
-- Name: routes_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.routes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.routes_id_seq OWNER TO postgres;

--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 223
-- Name: routes_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.routes_id_seq OWNED BY trucking_company.routes.id;


--
-- TOC entry 226 (class 1259 OID 25010)
-- Name: tasks; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.tasks (
    id integer NOT NULL,
    route_id integer,
    address_id integer,
    "time" timestamp without time zone NOT NULL,
    details character varying(100) NOT NULL
);


ALTER TABLE trucking_company.tasks OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 25008)
-- Name: tasks_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.tasks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.tasks_id_seq OWNER TO postgres;

--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 225
-- Name: tasks_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.tasks_id_seq OWNED BY trucking_company.tasks.id;


--
-- TOC entry 214 (class 1259 OID 24879)
-- Name: truck_capacity; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.truck_capacity (
    id integer NOT NULL,
    "capacity, ton" integer NOT NULL
);


ALTER TABLE trucking_company.truck_capacity OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 24877)
-- Name: truck_capacity_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.truck_capacity_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.truck_capacity_id_seq OWNER TO postgres;

--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 213
-- Name: truck_capacity_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.truck_capacity_id_seq OWNED BY trucking_company.truck_capacity.id;


--
-- TOC entry 216 (class 1259 OID 24887)
-- Name: users; Type: TABLE; Schema: trucking_company; Owner: postgres
--

CREATE TABLE trucking_company.users (
    id integer NOT NULL,
    login character varying(20) NOT NULL,
    role_id integer NOT NULL,
    password bytea
);


ALTER TABLE trucking_company.users OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 24885)
-- Name: users_id_seq; Type: SEQUENCE; Schema: trucking_company; Owner: postgres
--

CREATE SEQUENCE trucking_company.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE trucking_company.users_id_seq OWNER TO postgres;

--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: trucking_company; Owner: postgres
--

ALTER SEQUENCE trucking_company.users_id_seq OWNED BY trucking_company.users.id;


--
-- TOC entry 2769 (class 2604 OID 24779)
-- Name: addresses id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.addresses ALTER COLUMN id SET DEFAULT nextval('trucking_company.addresses_id_seq'::regclass);


--
-- TOC entry 2775 (class 2604 OID 24792)
-- Name: car_state id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.car_state ALTER COLUMN id SET DEFAULT nextval('trucking_company.car_state_id_seq'::regclass);


--
-- TOC entry 2776 (class 2604 OID 24800)
-- Name: car_status id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.car_status ALTER COLUMN id SET DEFAULT nextval('trucking_company.car_status_id_seq'::regclass);


--
-- TOC entry 2777 (class 2604 OID 24808)
-- Name: car_type id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.car_type ALTER COLUMN id SET DEFAULT nextval('trucking_company.car_type_id_seq'::regclass);


--
-- TOC entry 2793 (class 2604 OID 25031)
-- Name: cars id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars ALTER COLUMN id SET DEFAULT nextval('trucking_company.cars_id_seq'::regclass);


--
-- TOC entry 2796 (class 2604 OID 25068)
-- Name: cars_in_routes id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars_in_routes ALTER COLUMN id SET DEFAULT nextval('trucking_company.cars_in_routes_id_seq'::regclass);


--
-- TOC entry 2778 (class 2604 OID 24833)
-- Name: customer_type id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.customer_type ALTER COLUMN id SET DEFAULT nextval('trucking_company.customer_type_id_seq'::regclass);


--
-- TOC entry 2784 (class 2604 OID 24938)
-- Name: customers id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.customers ALTER COLUMN id SET DEFAULT nextval('trucking_company.customers_id_seq'::regclass);


--
-- TOC entry 2786 (class 2604 OID 24957)
-- Name: drivers id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.drivers ALTER COLUMN id SET DEFAULT nextval('trucking_company.drivers_id_seq'::regclass);


--
-- TOC entry 2779 (class 2604 OID 24849)
-- Name: request_status id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.request_status ALTER COLUMN id SET DEFAULT nextval('trucking_company.request_status_id_seq'::regclass);


--
-- TOC entry 2787 (class 2604 OID 24970)
-- Name: requests id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.requests ALTER COLUMN id SET DEFAULT nextval('trucking_company.requests_id_seq'::regclass);


--
-- TOC entry 2780 (class 2604 OID 24857)
-- Name: role id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.role ALTER COLUMN id SET DEFAULT nextval('trucking_company.role_id_seq'::regclass);


--
-- TOC entry 2781 (class 2604 OID 24865)
-- Name: route_status id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.route_status ALTER COLUMN id SET DEFAULT nextval('trucking_company.route_status_id_seq'::regclass);


--
-- TOC entry 2790 (class 2604 OID 24994)
-- Name: routes id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.routes ALTER COLUMN id SET DEFAULT nextval('trucking_company.routes_id_seq'::regclass);


--
-- TOC entry 2792 (class 2604 OID 25013)
-- Name: tasks id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.tasks ALTER COLUMN id SET DEFAULT nextval('trucking_company.tasks_id_seq'::regclass);


--
-- TOC entry 2782 (class 2604 OID 24882)
-- Name: truck_capacity id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.truck_capacity ALTER COLUMN id SET DEFAULT nextval('trucking_company.truck_capacity_id_seq'::regclass);


--
-- TOC entry 2783 (class 2604 OID 24890)
-- Name: users id; Type: DEFAULT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.users ALTER COLUMN id SET DEFAULT nextval('trucking_company.users_id_seq'::regclass);


--
-- TOC entry 2972 (class 0 OID 24776)
-- Dependencies: 198
-- Data for Name: addresses; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.addresses (id, country, district, city, street, housenum, building) FROM stdin;
1	Беларусь	Минский	Минск	Скорины	18	-
2	Беларусь	Минский	пос.Солнечный	-	\N	-
3	Беларусь	Минский	Заславль	Уваровой	4	-
4	Беларусь	Минский	Минск	Орловская	39	2
5	Беларусь	Минский	Жодино	Землестроителей	18	-
6	Беларусь	Минский	Борисов	Победы пр.	34	1
7	Беларусь	Минский	Минск	Газеты Звезда пр.	41	-
8	Беларусь	Минский	Минск	Оранжерейная	10	-
9	Беларусь	Минский	Минск	Аэропорт Минск-2	\N	-
11	Беларусь	Минский	г.п.Раков	Минская	12	-
12	Беларусь	Минский	Минск	Руссиянова	18	3
13	Беларусь	Минский	Минск	Карбышева	1	3
14	Беларусь	Минский	Минск	Толбухина	2	-
33	Беларусь	Минский	Минск	пр.Независимости	0	
34	Беларусь	Минский	Минск	пр.Независимости	164	
35	Беларусь	Гомельский	Гомель	Гагарина	0	
36	Беларусь	Минский	Минск	ДС \\"Дружная\\" 1	0	
37	Беларусь	Минский	Минск	ДС \\"Дружная\\" 1	0	
39	Беларусь	Минский	Минск	ДС \\"Дружная\\"	0	
40	Беларусь	Минский	Минск	ДС \\"Дружная\\"	0	
41	Беларусь	Минский	Минск	ДС \\"Дружная\\"	0	
42	Беларусь	Минский	Минск	ДС \\"Дружная\\"	0	
43	Беларусь	Минский	Минск	пр.Независимости	164	
44	Беларусь	Минский	Минск	пр.Независимости	164	
45	Беларусь	Минский	Минск	пр.Независимости	164	
46	Беларусь	Минский	Минск	ДС \\"Дружная\\"	0	
47	Беларусь	Минский	Минск	пр.Независимости	164	2
48	Беларусь	Минский	Минск	пр.Независимости	164	2
49	Беларусь	Минский	Минск	пр.Независимости	164	2
50	Беларусь	Минский	Минск	пр.Независимости	164	2
51	Беларусь	Минский	Минск	ДС \\"Дружная\\" 1	0	
52	Беларусь	Минский	Минск	пр.Независимости	164	
\.


--
-- TOC entry 2974 (class 0 OID 24789)
-- Dependencies: 200
-- Data for Name: car_state; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.car_state (id, state) FROM stdin;
1	нормальное
2	требуется осмотр
3	требуется ремонт
4	аварийное
\.


--
-- TOC entry 2976 (class 0 OID 24797)
-- Dependencies: 202
-- Data for Name: car_status; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.car_status (id, status) FROM stdin;
1	готов к отправке
2	в рейсе
3	в ремонте
\.


--
-- TOC entry 2978 (class 0 OID 24805)
-- Dependencies: 204
-- Data for Name: car_type; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.car_type (id, type) FROM stdin;
1	автобус
2	грузовик
\.


--
-- TOC entry 3002 (class 0 OID 25028)
-- Dependencies: 228
-- Data for Name: cars; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.cars (id, car_number, type_id, model, seatsnumber, capacity_id, status_id, state_id, driver_id) FROM stdin;
1	145 DD	1	МАЗ-241	22	\N	1	1	3
2	874 HH	1	МАЗ-251	47	\N	1	1	2
3	665 OG	1	МАЗ-251	47	\N	3	3	6
4	996 TY	2	ЗИЛ-130	\N	1	1	2	4
5	455 WW	2	КАМАЗ 65206	\N	4	1	1	5
\.


--
-- TOC entry 3004 (class 0 OID 25065)
-- Dependencies: 230
-- Data for Name: cars_in_routes; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.cars_in_routes (id, route_id, car_id) FROM stdin;
1	1	2
2	2	5
3	3	4
4	4	4
5	5	1
7	7	2
8	8	2
25	10	1
26	10	3
29	6	3
\.


--
-- TOC entry 2980 (class 0 OID 24830)
-- Dependencies: 206
-- Data for Name: customer_type; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.customer_type (id, type) FROM stdin;
1	юр.лицо
2	физ.лицо
\.


--
-- TOC entry 2992 (class 0 OID 24935)
-- Dependencies: 218
-- Data for Name: customers; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.customers (id, type_id, name, surname, phone, email, company_name) FROM stdin;
7	1	Андрей	Баденков	1234567	a.badenkov_1@gmail.com	ОАО \\"Сантехгарант\\"
8	1	Алексей	Дерюгин	6587421	alex_der@yahoo.com	ЗАО \\"Внешдомстрой\\"
9	2	Борис	Сергейчук	8745961	sergey_W_sergey@yandex.ru	-
10	1	Татьяна	Андреенко	(29)697-45-12	smalltalk39@gmail.com	ОАО \\"Белмедпрепараты\\"
11	2	Елена	Авдеенко	(29)111-11-22	avdei_lena@gmail.com	
\.


--
-- TOC entry 2994 (class 0 OID 24954)
-- Dependencies: 220
-- Data for Name: drivers; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.drivers (id, surname, name, phone) FROM stdin;
2	Михайлов	Денис	2547964
3	Петров	Сергей	4258963
4	Врубель	Евгений	1257852
5	Панасюк	Михаил	2571541
6	Мерешко	Дмитрий	2475488
\.


--
-- TOC entry 2982 (class 0 OID 24846)
-- Dependencies: 208
-- Data for Name: request_status; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.request_status (id, status) FROM stdin;
1	рассматривается
2	принята
3	отклонена
\.


--
-- TOC entry 2996 (class 0 OID 24967)
-- Dependencies: 222
-- Data for Name: requests; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.requests (id, customer_id, status_id, request_date, comment) FROM stdin;
1	7	2	2018-09-04 21:00:00	-
2	8	2	2018-10-08 21:00:00	-
3	7	2	2018-10-24 21:00:00	-
4	9	2	2018-12-19 21:00:00	-
5	10	2	2019-01-11 21:00:00	\N
6	11	2	2019-07-02 21:00:00	\N
7	10	2	2019-07-07 21:00:00	\N
8	9	2	2019-07-31 21:00:00	\N
9	10	1	2019-08-12 21:00:00	Привет! Привет! Как дела?
10	10	2	2019-08-12 21:00:00	Hello!
12	10	3	2019-08-12 21:00:00	еще одна заявка!!!!
13	10	1	2019-08-12 21:00:00	Заявка, заявка, заявка.....
14	10	3	2019-08-12 21:00:00	
15	9	1	2019-08-13 21:00:00	1234567 хороший год
16	7	3	2019-08-12 21:00:00	заявка отклоненная
\.


--
-- TOC entry 2984 (class 0 OID 24854)
-- Dependencies: 210
-- Data for Name: role; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.role (id, role_name) FROM stdin;
1	admin
2	driver
3	customer
\.


--
-- TOC entry 2986 (class 0 OID 24862)
-- Dependencies: 212
-- Data for Name: route_status; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.route_status (id, status) FROM stdin;
1	запланирован
2	на выполнении
3	выполнен
\.


--
-- TOC entry 2998 (class 0 OID 24991)
-- Dependencies: 224
-- Data for Name: routes; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.routes (id, name, status_id) FROM stdin;
1	Пассажироперевозки БО Солнечный	3
2	Грузоперевозки стройматериалы	3
3	Грузоперевозки сантехника	3
4	Перевозка мебели	3
5	Перевозка пассажиров	3
6	Пассажироперевозки г.п.Раков	2
7	Пассажироперевозки Аэропорт Минск-2	1
8	Пассажироперевозки Минск	1
10	New route	1
\.


--
-- TOC entry 3000 (class 0 OID 25010)
-- Dependencies: 226
-- Data for Name: tasks; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.tasks (id, route_id, address_id, "time", details) FROM stdin;
1	1	1	2018-09-18 10:30:00	Посадка пассажиров, ожидание
2	1	2	2018-09-18 12:00:00	Прибытие на БО, высадка пассажиров
3	1	2	2018-09-18 22:00:00	Посадка пассажиров
4	1	1	2018-09-18 23:00:00	Прибытие в место отправления, высадка пассажиров
7	2	3	2018-10-15 10:00:00	Погрузка товара, регистрация
8	2	4	2018-10-15 13:00:00	Выгрузка товара, регистрация
9	3	5	2018-11-08 10:00:00	Погрузка товара, получение накладной
10	3	1	2018-11-08 13:00:00	Передача товара, передача накладной
11	4	6	2019-01-12 10:00:00	Погрузка мебели, ожидание
12	4	7	2019-01-12 14:00:00	Выгрузка мебели
17	5	8	2019-02-03 10:00:00	Посадка пассажиров
18	5	9	2019-02-03 12:00:00	Прибытие в аэропорт, выгрузка пассажиров
19	5	9	2019-02-03 16:00:00	Посадка пассажиров
20	5	8	2019-02-03 18:00:00	Прибытие в место отправления
21	6	42	2019-08-13 12:00:00	Посадка пассажиров
22	6	11	2019-08-13 13:00:00	Высадка пассажиров
23	6	11	2019-08-13 22:00:00	Посадка пассажиров
25	7	8	2019-09-02 11:00:00	Посадка пассажиров
26	7	9	2019-09-02 13:00:00	Прибытие в аэропорт, выгрузка пассажиров
27	7	9	2019-09-02 17:00:00	Посадка пассажиров
28	7	8	2019-09-02 19:00:00	Прибытие в место отправления
29	8	12	2019-10-05 12:00:00	Забрать пассажиров
30	8	13	2019-10-05 13:00:00	Высадка пассажиров
31	8	13	2019-10-05 14:00:00	Забрать пассажиров
32	8	14	2019-10-05 16:00:00	Высадка пассажиров
57	10	34	2019-09-01 00:00:00	Забрать пассажиров
58	10	35	2019-09-01 12:00:00	Высадка пассажиров
62	6	46	2019-08-13 23:00:00	Высадка пассажиров
\.


--
-- TOC entry 2988 (class 0 OID 24879)
-- Dependencies: 214
-- Data for Name: truck_capacity; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.truck_capacity (id, "capacity, ton") FROM stdin;
1	6
2	10
3	15
4	0
\.


--
-- TOC entry 2990 (class 0 OID 24887)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: trucking_company; Owner: postgres
--

COPY trucking_company.users (id, login, role_id, password) FROM stdin;
1	admin	1	\\x1d5c62404bac08a100f7ad0a676fb2ed
2	d.mihailov	2	\\x5ab64efa2db2411799849c13e12182ac
3	s.petrov	2	\\x9e385edf8dd12fc7b86efb3d7cac1d1d
4	e.vrubel	2	\\xfe4cb71e6ab056695da3c0c672a1ae7e
5	m.panasiuk	2	\\x5450c11f7b7a3b0bab119169b2ba7562
6	d.mereshko	2	\\xc8daa35b04c02a60ef7bbef83b2684a2
7	santexgarant	3	\\x5dc73be87f793f43b3bc12d68f97c7b1
8	outerHouse	3	\\x41dc7728b020316ca8537b321bb99065
9	sergey009	3	\\xca0cc4d33faa985d99bbd1d82b088596
10	tatianaA	3	\\x8cce5219ccf978dd270d77677fa6b3ad
11	AvdeenkoE	3	\\x416e5dd03e90f7cb722784802f1d1f48
\.


--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 197
-- Name: addresses_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.addresses_id_seq', 1, false);


--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 199
-- Name: car_state_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.car_state_id_seq', 1, false);


--
-- TOC entry 3030 (class 0 OID 0)
-- Dependencies: 201
-- Name: car_status_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.car_status_id_seq', 1, false);


--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 203
-- Name: car_type_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.car_type_id_seq', 1, false);


--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 227
-- Name: cars_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.cars_id_seq', 1, false);


--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 229
-- Name: cars_in_routes_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.cars_in_routes_id_seq', 1, false);


--
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 205
-- Name: customer_type_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.customer_type_id_seq', 1, false);


--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 217
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.customers_id_seq', 1, false);


--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 219
-- Name: drivers_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.drivers_id_seq', 1, false);


--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 207
-- Name: request_status_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.request_status_id_seq', 1, false);


--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 221
-- Name: requests_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.requests_id_seq', 1, false);


--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 209
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.role_id_seq', 1, false);


--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 211
-- Name: route_status_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.route_status_id_seq', 1, false);


--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 223
-- Name: routes_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.routes_id_seq', 1, false);


--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 225
-- Name: tasks_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.tasks_id_seq', 1, false);


--
-- TOC entry 3043 (class 0 OID 0)
-- Dependencies: 213
-- Name: truck_capacity_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.truck_capacity_id_seq', 1, false);


--
-- TOC entry 3044 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: trucking_company; Owner: postgres
--

SELECT pg_catalog.setval('trucking_company.users_id_seq', 1, true);


--
-- TOC entry 2798 (class 2606 OID 24786)
-- Name: addresses addresses_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.addresses
    ADD CONSTRAINT addresses_pkey PRIMARY KEY (id);


--
-- TOC entry 2800 (class 2606 OID 24794)
-- Name: car_state car_state_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.car_state
    ADD CONSTRAINT car_state_pkey PRIMARY KEY (id);


--
-- TOC entry 2802 (class 2606 OID 24802)
-- Name: car_status car_status_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.car_status
    ADD CONSTRAINT car_status_pkey PRIMARY KEY (id);


--
-- TOC entry 2804 (class 2606 OID 24810)
-- Name: car_type car_type_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.car_type
    ADD CONSTRAINT car_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2828 (class 2606 OID 25037)
-- Name: cars cars_car_number_key; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_car_number_key UNIQUE (car_number);


--
-- TOC entry 2832 (class 2606 OID 25070)
-- Name: cars_in_routes cars_in_routes_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars_in_routes
    ADD CONSTRAINT cars_in_routes_pkey PRIMARY KEY (id);


--
-- TOC entry 2830 (class 2606 OID 25035)
-- Name: cars cars_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_pkey PRIMARY KEY (id);


--
-- TOC entry 2806 (class 2606 OID 24835)
-- Name: customer_type customer_type_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.customer_type
    ADD CONSTRAINT customer_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2818 (class 2606 OID 24941)
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- TOC entry 2820 (class 2606 OID 24959)
-- Name: drivers drivers_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.drivers
    ADD CONSTRAINT drivers_pkey PRIMARY KEY (id);


--
-- TOC entry 2808 (class 2606 OID 24851)
-- Name: request_status request_status_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.request_status
    ADD CONSTRAINT request_status_pkey PRIMARY KEY (id);


--
-- TOC entry 2822 (class 2606 OID 24977)
-- Name: requests requests_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.requests
    ADD CONSTRAINT requests_pkey PRIMARY KEY (id);


--
-- TOC entry 2810 (class 2606 OID 24859)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 2812 (class 2606 OID 24867)
-- Name: route_status route_status_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.route_status
    ADD CONSTRAINT route_status_pkey PRIMARY KEY (id);


--
-- TOC entry 2824 (class 2606 OID 24997)
-- Name: routes routes_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.routes
    ADD CONSTRAINT routes_pkey PRIMARY KEY (id);


--
-- TOC entry 2826 (class 2606 OID 25015)
-- Name: tasks tasks_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- TOC entry 2814 (class 2606 OID 24884)
-- Name: truck_capacity truck_capacity_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.truck_capacity
    ADD CONSTRAINT truck_capacity_pkey PRIMARY KEY (id);


--
-- TOC entry 2816 (class 2606 OID 24895)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 2844 (class 2606 OID 25043)
-- Name: cars cars_capacity_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_capacity_id_fkey FOREIGN KEY (capacity_id) REFERENCES trucking_company.truck_capacity(id);


--
-- TOC entry 2847 (class 2606 OID 25058)
-- Name: cars cars_driver_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_driver_id_fkey FOREIGN KEY (driver_id) REFERENCES trucking_company.drivers(id) ON DELETE SET NULL;


--
-- TOC entry 2849 (class 2606 OID 25076)
-- Name: cars_in_routes cars_in_routes_car_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars_in_routes
    ADD CONSTRAINT cars_in_routes_car_id_fkey FOREIGN KEY (car_id) REFERENCES trucking_company.cars(id);


--
-- TOC entry 2848 (class 2606 OID 25071)
-- Name: cars_in_routes cars_in_routes_route_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars_in_routes
    ADD CONSTRAINT cars_in_routes_route_id_fkey FOREIGN KEY (route_id) REFERENCES trucking_company.routes(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2846 (class 2606 OID 25053)
-- Name: cars cars_state_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_state_id_fkey FOREIGN KEY (state_id) REFERENCES trucking_company.car_state(id);


--
-- TOC entry 2845 (class 2606 OID 25048)
-- Name: cars cars_status_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_status_id_fkey FOREIGN KEY (status_id) REFERENCES trucking_company.car_status(id);


--
-- TOC entry 2843 (class 2606 OID 25038)
-- Name: cars cars_type_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.cars
    ADD CONSTRAINT cars_type_id_fkey FOREIGN KEY (type_id) REFERENCES trucking_company.car_type(id);


--
-- TOC entry 2834 (class 2606 OID 24942)
-- Name: customers customers_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.customers
    ADD CONSTRAINT customers_id_fkey FOREIGN KEY (id) REFERENCES trucking_company.users(id) ON UPDATE CASCADE;


--
-- TOC entry 2835 (class 2606 OID 24947)
-- Name: customers customers_type_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.customers
    ADD CONSTRAINT customers_type_id_fkey FOREIGN KEY (type_id) REFERENCES trucking_company.customer_type(id);


--
-- TOC entry 2836 (class 2606 OID 24960)
-- Name: drivers drivers_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.drivers
    ADD CONSTRAINT drivers_id_fkey FOREIGN KEY (id) REFERENCES trucking_company.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2837 (class 2606 OID 24978)
-- Name: requests requests_customer_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.requests
    ADD CONSTRAINT requests_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES trucking_company.customers(id);


--
-- TOC entry 2838 (class 2606 OID 24983)
-- Name: requests requests_status_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.requests
    ADD CONSTRAINT requests_status_id_fkey FOREIGN KEY (status_id) REFERENCES trucking_company.request_status(id);


--
-- TOC entry 2839 (class 2606 OID 24998)
-- Name: routes routes_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.routes
    ADD CONSTRAINT routes_id_fkey FOREIGN KEY (id) REFERENCES trucking_company.requests(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2840 (class 2606 OID 25003)
-- Name: routes routes_status_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.routes
    ADD CONSTRAINT routes_status_id_fkey FOREIGN KEY (status_id) REFERENCES trucking_company.route_status(id) ON UPDATE CASCADE;


--
-- TOC entry 2842 (class 2606 OID 25021)
-- Name: tasks tasks_address_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.tasks
    ADD CONSTRAINT tasks_address_id_fkey FOREIGN KEY (address_id) REFERENCES trucking_company.addresses(id) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 2841 (class 2606 OID 25016)
-- Name: tasks tasks_route_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.tasks
    ADD CONSTRAINT tasks_route_id_fkey FOREIGN KEY (route_id) REFERENCES trucking_company.routes(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2833 (class 2606 OID 24896)
-- Name: users users_role_id_fkey; Type: FK CONSTRAINT; Schema: trucking_company; Owner: postgres
--

ALTER TABLE ONLY trucking_company.users
    ADD CONSTRAINT users_role_id_fkey FOREIGN KEY (role_id) REFERENCES trucking_company.role(id);


-- Completed on 2019-09-16 20:15:28

--
-- PostgreSQL database dump complete
--