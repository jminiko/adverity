create user adverity with password 'adverity';
alter role adverity with SUPERUSER ;
drop database adverity;
create database adverity;
grant ALL on DATABASE adverity to adverity;

create schema adv_schema;
grant ALL on schema adv_schema to adverity;


------------------------  for datasource  -------------------------
CREATE TABLE adv_schema.analytics_datasource(
"id" INTEGER NOT NULL,
"value" TEXT NOT NULL
);
ALTER TABLE
    adv_schema.analytics_datasource ADD PRIMARY KEY("id");
ALTER TABLE
    adv_schema.analytics_datasource ADD CONSTRAINT "analytics_datasource_unique" UNIQUE("value");

CREATE SEQUENCE IF NOT EXISTS adv_schema.datasource_seq;

SELECT SETVAL('adv_schema.datasource_seq', (
    SELECT max(id) FROM adv_schema.analytics_datasource)
           );

ALTER TABLE adv_schema.analytics_datasource
    ALTER COLUMN id
        SET DEFAULT nextval('adv_schema.datasource_seq'::regclass);

ALTER SEQUENCE adv_schema.datasource_seq
    OWNED BY adv_schema.analytics_datasource.id;
------------------------    for campaigns  ------------------------
CREATE TABLE adv_schema.analytics_campaign(
"id" INTEGER NOT NULL,
"value" TEXT NOT NULL
);
grant ALL on TABLE adv_schema.analytics_campaign to adverity;
ALTER TABLE
    adv_schema.analytics_campaign ADD CONSTRAINT "analytics_campaign_unique" UNIQUE("value");

CREATE SEQUENCE IF NOT EXISTS adv_schema.campaign_seq;

SELECT SETVAL('adv_schema.campaign_seq', (
    SELECT max(id) FROM adv_schema.analytics_campaign)
);

ALTER TABLE adv_schema.analytics_campaign
    ALTER COLUMN id
        SET DEFAULT nextval('adv_schema.campaign_seq'::regclass);

ALTER SEQUENCE adv_schema.campaign_seq
    OWNED BY adv_schema.analytics_campaign.id;
------------------------for analytics stuff------------------------
CREATE TABLE adv_schema.analytics_main(
"id" INTEGER NOT NULL,
"datasource_id" INTEGER NOT NULL,
"campaign_id" INTEGER NOT NULL,
"daily" DATE NOT NULL,
"clicks" INTEGER NOT NULL,
"impressions" INTEGER NOT NULL
);
grant ALL on TABLE adv_schema.analytics_main to adverity;
ALTER TABLE
    adv_schema.analytics_main ADD PRIMARY KEY("id");
--ALTER TABLE
--    adv_schema.analytics_main ADD CONSTRAINT "analytics_unique" UNIQUE("datasource","campaign","daily");
CREATE INDEX "analytics_daily_index" ON
    adv_schema.analytics_main("daily");

CREATE SEQUENCE IF NOT EXISTS adv_schema.analytics_main_seq;

SELECT SETVAL('adv_schema.analytics_main_seq', (
    SELECT max(id) FROM adv_schema.analytics_main)
           );

ALTER TABLE adv_schema.analytics_main
    ALTER COLUMN id
        SET DEFAULT nextval('adv_schema.analytics_main_seq'::regclass);

ALTER SEQUENCE adv_schema.analytics_main_seq
    OWNED BY adv_schema.analytics_main.id;
