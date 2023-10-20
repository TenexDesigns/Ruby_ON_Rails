default: &default
  adapter: postgresql
  encoding: unicode
  pool: <%= ENV.fetch("RAILS_MAX_THREADS") { 5 } %>
  host: localhost
  username: postgres
  password: GacauEliud
  port: 5433



development:
  adapter: postgresql
  encoding: unicode
  username: postgres
  password: GacauEliud
  host: localhost
  port: 5433
  database: railsbook

test:
  adapter: postgresql
  encoding: unicode
  username: my_database_username
  password: my_database_password
  host: localhost
  port: 5432
  database: my_test_database

production:
  adapter: postgresql
  encoding: unicode
  username: my_database_username
  password: my_database_password
  host: localhost
  port: 5432
  database: my_production_database




  ....
