#!/usr/bin/env bash

until printf "" 2>>/dev/null >>/dev/tcp/cassandra/9042; do
    sleep 5;
    echo "Waiting for cassandra...";
done

echo "Creating keyspace and table..."
cqlsh cassandra -u cassandra_user -p cassandra_password -e "CREATE KEYSPACE your_keyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};"

