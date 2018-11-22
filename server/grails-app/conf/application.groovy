import static java.sql.Connection.TRANSACTION_READ_COMMITTED
import grails.util.Environment

String envShortName = Environment.envNameMappings.entrySet().find { it.value == Environment.current.name }.key

dataSource {
    dbCreate = 'none'
    dialect = 'org.hibernate.dialect.PostgreSQL94Dialect'
    driverClassName = 'org.postgresql.Driver'
    jmxExport = true
    password = ''
    pooled = true
    url = 'jdbc:postgresql://localhost/churchmealfinder_' + envShortName
    username = 'churchmealfinder_' + envShortName
}

grails.plugin.databasemigration.updateOnStart = true

environments {
    development {

    }

    test {

    }

    production {
        dataSource {
            properties {
                defaultTransactionIsolation = TRANSACTION_READ_COMMITTED
                initialSize = 5
                jdbcInterceptors = 'ConnectionState'
                jmxEnabled = true
                maxActive = 50
                maxAge = 600_000
                maxIdle = 25
                maxWait = 10000
                minEvictableIdleTimeMillis = 60000
                minIdle = 5
                testOnBorrow = true
                testOnReturn = false
                testWhileIdle = true
                timeBetweenEvictionRunsMillis = 5000
                validationInterval = 15000
                validationQuery = 'SELECT 1'
                validationQueryTimeout = 3
            }
        }
    }
}