# https://github.com/amigoscode/microservices/issues/2
services:
postgres:
container_name: postgres
image: postgres
environment:
POSTGRES_USER: ${POSTGRES_USER:-amigoscode}
POSTGRES_PASSWORD: ${POSTGRES_PASSWORD:-password}
PGDATA: /data/postgres
volumes:
- type: volume
source: postgres
target: /data/postgres
ports:
- "5431:5432"
networks:
- postgres
restart: unless-stopped
pgadmin:
container_name: pgadmin
image: dpage/pgadmin4
environment:
PGADMIN_DEFAULT_EMAIL: ${PGADMIN_DEFAULT_EMAIL:-pgadmin4@pgadmin.org}
PGADMIN_DEFAULT_PASSWORD: ${PGADMIN_DEFAULT_PASSWORD:-admin}
PGADMIN_CONFIG_SERVER_MODE: 'False'
volumes:
- type: volume
source: pgadmin
target: /var/lib/pgadmin
ports:
- "${PGADMIN_PORT:-5050}:80"
networks:
- postgres
restart: unless-stopped

networks:
postgres:
driver: bridge

volumes:
postgres:
pgadmin:

----------------------------------------------------

# Title: Run PostgreSQL and pgAdmin in docker for local development using docker compose
# Link: https://belowthemalt.com/2021/06/09/run-postgresql-and-pgadmin-in-docker-for-local-development-using-docker-compose/
# Steps:
# 1. run the below command to run postgreSQL and pgAdmin4 in a detached mode
# > docker compose up -d
# 2. To view the logs , use command
# > docker logs -f local_pgdb
# 3. To configure pgadmin – open a browser and go to
# browser : http://localhost:5050/
# 4. In the connection details for hostname give the container name of postgreSQL
# Pgadmin4 :
#   Create server :
#     Connection :
#       Host name/address: local_pgdb # postgres
#       Port: 5432 # 5432
#       User name: user # neo
#       Password: admin # password
#       Save password: checked.