mariadb -u ${MARIADB_ROOT_USER} -p${MARIADB_ROOT_PASSWORD} --execute \
"CREATE DATABASE IF NOT EXISTS ${MARIADB_DATABASE};
USE ${MARIADB_DATABASE};
GRANT ALL PRIVILEGES ON ${MARIADB_DATABASE}.* TO '${MARIADB_USER}'@'%' IDENTIFIED BY '${MARIADB_PASSWORD}';
FLUSH PRIVILEGES;"