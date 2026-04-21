# SGE — Sistema de Gestão Escolar

> API REST para gestão de dados do ensino básico no Sistema Nacional de Ensino de Moçambique.

---

## 📋 Sobre o Projecto

O **SGE** é uma API REST desenvolvida em Java com Spring Boot, desenhada para suportar as necessidades administrativas e pedagógicas do ensino básico. A aplicação permite armazenar e manipular dados de alunos, disciplinas, aproveitamentos e matrículas, com lógica integrada para cálculo de médias e definição da situação académica do aluno.
A API segue uma arquitectura em camadas com tratamento centralizado de excepções e mapeamento explícito entre entidades e DTOs
O projecto está em desenvolvimento activo, com novas entidades e funcionalidades a serem adicionadas progressivamente.

---

## ✅ Funcionalidades Actuais

- **Alunos** — Registar, visualizar, actualizar e remover alunos
- **Disciplinas** — Gestão completa de disciplinas
- **Aproveitamentos** — Registo de notas com cálculo automático de média e definição de situação (aprovado/reprovado)
- **Autenticação** — Login com Spring Security e JWT (retorno de token para acesso protegido)

---

## 🔜 Próximos Passos

- [ ] Documentação da API com Swagger / OpenAPI
- [ ] Entidade Turmas
- [ ] Entidade Professores
- [ ] Integração de Turmas e Professores com entidades existentes
- [ ] Migração para PostgreSQL (planeada)

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Função |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.5.7 | Framework base |
| Spring Web | — | Camada REST |
| Spring Security | 6.x | Segurança e controlo de acesso |
| JWT | — | Autenticação stateless |
| JPA / Hibernate | — | Persistência e ORM |
| MariaDB | 12.1.2 | Base de dados (migração para PostgreSQL planeada) |
| Git | — | Controlo de versões |

---

## 🚀 Como Executar Localmente

### Pré-requisitos

- Java 17+
- MariaDB instalado e a correr
- Maven

### Passos

```bash
# 1. Clonar o repositório
git clone https://github.com/aderitoandala/sge-spring-boot.git
cd sge-spring-boot

# 2. Configurar a base de dados
# Editar src/main/resources/application.properties com as tuas credenciais:
# spring.datasource.url=jdbc:mariadb://localhost:3306/sge
# spring.datasource.username=SEU_USUARIO
# spring.datasource.password=SUA_SENHA

# 3. Criar a base de dados
# No MariaDB: CREATE DATABASE sge;

# 4. Executar o projecto
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

---

## 🔐 Autenticação

A API utiliza autenticação via **JWT**. Para aceder aos endpoints protegidos:

1. Fazer login no endpoint `/auth/login` com as credenciais
2. Receber o token JWT na resposta
3. Incluir o token no header de cada requisição:

```
Authorization: Bearer <token>
```

---

## 📁 Estrutura do Projecto

```
src/
└── main/
    ├── java/com/mz/sge/
    │   ├── auth/           # Autenticação e JWT
    │   ├── controller/     # Endpoints REST
    │   ├── dto/            # Objectos de transferência de dados
    │   ├── entity/         # Entidades JPA
    │   ├── enums/          # Enumerações
    │   ├── exception/      # Tratamento de excepções
    │   ├── mapper/         # Mapeamento entre entidades e DTOs
    │   ├── repository/     # Acesso a dados (JPA)
    │   └── service/        # Lógica de negócio
    └── resources/
        └── application.properties
```

---

## 📌 Endpoints Disponíveis

> ⚠️ Documentação completa via Swagger em breve.

| Método | Endpoint | Descrição | Auth |
|---|---|---|---|
| POST | `/auth/login` | Autenticação e retorno de token | ❌ |
| GET | `/alunos` | Listar todos os alunos | ✅ |
| POST | `/alunos` | Registar novo aluno | ✅ |
| PUT | `/alunos/{id}` | Actualizar aluno | ✅ |
| DELETE | `/alunos/{id}` | Remover aluno | ✅ |
| GET | `/disciplinas` | Listar disciplinas | ✅ |
| POST | `/disciplinas` | Registar disciplina | ✅ |
| PUT | `/disciplinas/{id}` | Actualizar disciplina | ✅ |
| DELETE | `/disciplinas/{id}` | Remover disciplina | ✅ |
| GET | `/aproveitamentos` | Listar aproveitamentos | ✅ |
| POST | `/aproveitamentos` | Registar aproveitamento | ✅ |
| PUT | `/aproveitamentos/{id}` | Actualizar aproveitamento | ✅ |
| DELETE | `/aproveitamentos/{id}` | Remover aproveitamento | ✅ |



---
> *Os endpoints podem ser testados através do postman ou qualquer outro consumidor de APIs.*
## 👨‍💻 Autor

**Adérito Andala**
- GitHub: [@aderitoandala](https://github.com/aderitoandala)

---

## 📄 Licença

Este projecto está sob desenvolvimento. Licença a definir.
