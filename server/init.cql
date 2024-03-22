CREATE KEYSPACE your_keyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE your_keyspace;

CREATE TABLE IF NOT EXISTS currencies (currency_code TEXT PRIMARY KEY,name TEXT,description TEXT);

CREATE TABLE IF NOT EXISTS currency_rates (id UUID PRIMARY KEY,source_currency TEXT,target_currency TEXT,rate DOUBLE,timestamp BIGINT);
