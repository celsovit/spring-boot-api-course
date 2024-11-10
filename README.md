# API com Spring Boot


## 🐳 Criar container Docker do PostgreSQL 

```bash
docker pull postgres
docker run -p 5432:5432 -v ~/database:/var/lib/postgresql/data -e POSTGRES_PASSWORD=1234 -d postgres
```

### 🌱 Dicas

#### Para reiniciar um container parado
```bash
docker ps -a
docker restart <id_container_parado>
```

#### Obter automaticamente o ID do container
```bash
# Cria container com um nome fixo
docker run --name my_postgres -p 5432:5432 -v ~/database:/var/lib/postgresql/data -e POSTGRES_PASSWORD=1234 -d postgres

# Guarda o ID do container em variável de ambiente
container_id=$(docker ps -q -f "name=my_postgres")

# Usa variável para rodar o docker exec
docker exec -it $container_id psql -U postgres
```

## 🐘 Criar banco de dados PostgreSQL

#### Abrir o terminal psql: 
```bash
docker ps
docker exec -it <container_id> psql -U postgres
```

#### Comandos no terminal psql:
```bash
# cria o banco de dados
CREATE DATABASE springboot_course

# confirmar se aparece na lista
\l 

# conectar-se ao banco de dados criado
\c springboot_course

# listar as tabelas existentes
\dt

# listar as tabelas com comando SQL
SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';

# encerrar o terminal psql
\q
```

### ⚡ Gerar script do banco de dados PostgreSQL
```bash
docker ps

docker exec -t <container_id> pg_dump -U postgres -d springboot_course \
  --schema-only \
  --no-owner \
  --no-comments \
  --no-tablespaces \
  --no-privileges \
  --no-unlogged-table-data \
  --no-blobs \
  --file=/tmp/schema_dump.sql

docker cp <container_id>:/tmp/schema_dump.sql ~/schema_dump.sql
```

## 💡 Links Úteis
- [Discord Grupo Professor Nelio Alves](https://discord.gg/SbjpsFv)
- [Free Hosting Bliss: Deploying Your Spring Boot App on Render](https://medium.com/spring-boot/free-hosting-bliss-deploying-your-spring-boot-app-on-render-d0ebd9713b9d)
- [Youtube: Deploy Spring Boot no Render](https://youtu.be/fwWvgk_SW2g)
