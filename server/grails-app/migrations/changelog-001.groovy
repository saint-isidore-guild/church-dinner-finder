databaseChangeLog = {

    changeSet(author: "mike", id: "db_default_timezone_utc") {
        sql("SET TIME ZONE 'UTC';")
    }

}