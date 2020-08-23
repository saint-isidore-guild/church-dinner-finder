## Built with

- Grails 4
- VueJs
- Vuetify

## Setup
Install Java via [sdkman](https://sdkman.io/)
```
sdk install java 8u151-oracle
```
Install PostgreSQL via [Homebrew](https://brew.sh/)
```
brew install postgresql
```

Create the dev and test databases
```
psql -f server/sql/setup.sql
```

## Usage
Server
```
./gradlew server:bootRun
```

Client
```
./gradlew client:bootRun 
```
