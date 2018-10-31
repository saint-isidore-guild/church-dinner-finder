databaseChangeLog = {

    changeSet(author: "mike (generated)", id: "1542866375835-1") {
        createSequence(sequenceName: "hibernate_sequence")
    }

    changeSet(author: "mike (generated)", id: "1542866375835-2") {
        createSequence(sequenceName: "seq_event")
    }

    changeSet(author: "mike (generated)", id: "1542866375835-3") {
        createSequence(sequenceName: "seq_venue")
    }

    changeSet(author: "mike (generated)", id: "1542866375835-3-1") {
        createSequence(sequenceName: "seq_category")
    }

    changeSet(author: "mike (generated)", id: "1542866375835-4") {
        createTable(tableName: "category") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "categoryPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "mike (generated)", id: "1542866375835-5") {
        createTable(tableName: "event") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "eventPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "phone", type: "VARCHAR(255)")

            column(name: "venue_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "cost_description", type: "VARCHAR(255)")

            column(name: "end_time", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "has_cost", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "organizer", type: "VARCHAR(255)")

            column(name: "start_time", type: "TIMESTAMP WITH TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "image_url", type: "VARCHAR(1000)")

            column(name: "description", type: "VARCHAR(255)")

            column(name: "email", type: "VARCHAR(255)")

            column(name: "website", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "mike (generated)", id: "1542866375835-6") {
        createTable(tableName: "event_category") {
            column(name: "event_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "category_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "mike (generated)", id: "1542866375835-7") {
        createTable(tableName: "venue") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "venuePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "address1", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "longitude", type: "FLOAT8")

            column(name: "address2", type: "VARCHAR(255)")

            column(name: "zip", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "city", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "VARCHAR(255)")

            column(name: "state", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "latitude", type: "FLOAT8")
        }
    }

    changeSet(author: "mike (generated)", id: "1542866375835-8") {
        addPrimaryKey(columnNames: "event_id, category_id", constraintName: "event_categoryPK", tableName: "event_category")
    }

    changeSet(author: "mike (generated)", id: "1542866375835-9") {
        addForeignKeyConstraint(baseColumnNames: "event_id", baseTableName: "event_category", constraintName: "FK24ud2uucu4h8ga1ois1mnalo8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "event")
    }

    changeSet(author: "mike (generated)", id: "1542866375835-10") {
        addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "event_category", constraintName: "FKpwl2b1ylc09urqr0c4n18io", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "category")
    }

    changeSet(author: "mike (generated)", id: "1542866375835-11") {
        addForeignKeyConstraint(baseColumnNames: "venue_id", baseTableName: "event", constraintName: "FKthgbmd6s6hp4l47kx1sh4cf9n", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "venue")
    }
}
