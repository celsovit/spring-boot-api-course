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

#### Obter o IP do gateway da rede Docker
```bash
docker network inspect bridge | grep Gateway
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

## Construir Imagem e Container Docker
A partir do arquivo Dockerfile presente no projeto, é possível criar uma imagem Docker e, 
a partir dela, iniciar contêineres para executar a aplicação. Para isso, siga o passo a passo
a seguir:

```bash
# cria a imagem Docker
docker build -t app-generator .

# inicia um novo contêiner
docker run -p 8080:8080 --name my-app app-generator  

# para o contêiner
docker stop my-app

# remove o contêiner parado
docker rm my-app

# logs do contêiner
docker logs my-app

# acessa contêiner via shell
docker exec -it my-app bash
```

## 💡 Links Úteis
- [Discord Grupo Professor Nelio Alves](https://discord.gg/SbjpsFv)
- [Multi stage build docker: uma abordagem para otimizar o processo de criação da imagem docker](https://thiagolopessilva.medium.com/multi-stage-build-docker-uma-abordagem-para-otimizar-o-processo-de-cria%C3%A7%C3%A3o-da-imagem-docker-2e579ecd830e)
- [Build a Docker Image using Maven and Spring boot](https://medium.com/@ramanamuttana/build-a-docker-image-using-maven-and-spring-boot-418e24c00776)
- [Free Hosting Bliss: Deploying Your Spring Boot App on Render](https://medium.com/spring-boot/free-hosting-bliss-deploying-your-spring-boot-app-on-render-d0ebd9713b9d)
- [Youtube: Deploy Spring Boot no Render](https://youtu.be/fwWvgk_SW2g)
