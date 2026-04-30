# SGE — Sistema de Gestão Escolar

> API REST para gestão de dados no ensino básico do Sistema Nacional de Ensino de Moçambique.

---

## 🌐 Demo

- **API:** https://sge-spring-boot.onrender.com
- **Swagger:** https://sge-spring-boot.onrender.com/swagger-ui/index.html

> ⚠️ Alojado no plano gratuito do Render — pode demorar até 50 segundos a responder após período de inactividade.

---

## 📋 Sobre o Projecto

O **SGE** é uma API REST desenvolvida em Java com Spring Boot, desenhada para suportar as necessidades administrativas e pedagógicas do ensino básico. A aplicação permite armazenar e manipular dados de alunos, disciplinas, aproveitamentos e matrículas, com lógica integrada para cálculo de médias e definição da situação académica do aluno.

A API segue uma arquitectura em camadas com tratamento centralizado de excepções e mapeamento explícito entre entidades e DTOs.

O projecto está em desenvolvimento activo, com novas entidades e funcionalidades a serem adicionadas progressivamente.

---

## ✅ Funcionalidades Actuais

- **Alunos** — Registar, visualizar, actualizar e remover alunos
- **Disciplinas** — Gestão completa de disciplinas
- **Aproveitamentos** — Registo de notas com cálculo automático de média e definição de situação (aprovado/reprovado)
- **Autenticação** — Registo e login com Spring Security e JWT (retorno de token para acesso protegido)

---

## 🔜 Próximos Passos

- [ ] Entidade Turmas
- [ ] Entidade Professores
- [ ] Integração de Turmas e Professores com entidades existentes

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Função |
|---|---|---|
| Java | 17 | Linguagem principal |
| Spring Boot | 3.5.x | Framework base |
| Spring Web | — | Camada REST |
| Spring Security | 6.x | Segurança e controlo de acesso |
| JWT | — | Autenticação stateless |
| JPA / Hibernate | — | Persistência e ORM |
| PostgreSQL | 18.2 | Base de dados |
| Flyway | 11.20.3 | Migrações da base de dados |
| SpringDoc OpenAPI | — | Documentação da API (Swagger) |
| Docker | — | Containerização |
| Git | — | Controlo de versões |

---

## 🚀 Como Executar Localmente

### Pré-requisitos

- Java 17+
- PostgreSQL instalado e a correr
- Maven

### Variáveis de Ambiente

Define as seguintes variáveis de ambiente antes de executar:

```bash
export DATABASE_URL=jdbc:postgresql://localhost:5432/db_sge
export DATABASE_USERNAME=SEU_USUARIO
export DATABASE_PASSWORD=SUA_SENHA
export JWT_SECRET=SEU_SECRET
```

### Passos

```bash
# 1. Clonar o repositório
git clone https://github.com/aderitoandala/sge-spring-boot.git
cd sge-spring-boot

# 2. Criar a base de dados
# No PostgreSQL: CREATE DATABASE db_sge;

# 3. Executar o projecto
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

> O Flyway aplica automaticamente as migrações ao arrancar.

---

## 🔐 Autenticação

A API utiliza autenticação via **JWT**. Para aceder aos endpoints protegidos:

1. Criar conta no endpoint `/auth/register`
2. Fazer login no endpoint `/auth/login` com as credenciais
3. Receber o token JWT na resposta
4. Incluir o token no header de cada requisição:

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
    │   ├── config/         # Configurações (Swagger)
    │   ├── controller/     # Endpoints REST
    │   ├── dto/            # Objectos de transferência de dados
    │   ├── entity/         # Entidades JPA
    │   ├── enums/          # Enumerações
    │   ├── exception/      # Tratamento de excepções
    │   ├── mapper/         # Mapeamento entre entidades e DTOs
    │   ├── repository/     # Acesso a dados (JPA)
    │   └── service/        # Lógica de negócio
    └── resources/
        ├── db/migration/   # Scripts Flyway
        └── application.properties
```

---

## 📌 Endpoints Disponíveis

> 📄 Documentação completa disponível via [Swagger](https://sge-spring-boot.onrender.com/swagger-ui/index.html).

| Método | Endpoint | Descrição | Auth |
|---|---|---|---|
| POST | `/auth/register` | Registar novo utilizador | ❌ |
| POST | `/auth/login` | Autenticação e retorno de token | ❌ |
| GET | `/alunos` | Listar todos os alunos | ✅ |
| GET | `/alunos/id/{id}` | Buscar aluno por ID | ✅ |
| GET | `/alunos/nome/{nome}` | Buscar aluno por nome | ✅ |
| POST | `/alunos/criar` | Registar novo aluno | ✅ |
| PUT | `/alunos/actualizar/{id}` | Actualizar aluno | ✅ |
| DELETE | `/alunos/apagar/{id}` | Remover aluno | ✅ |
| GET | `/disciplinas` | Listar todas as disciplinas | ✅ |
| GET | `/disciplinas/id/{id}` | Buscar disciplina por ID | ✅ |
| GET | `/disciplinas/nome/{nome}` | Buscar disciplina por nome | ✅ |
| POST | `/disciplinas/criar` | Registar nova disciplina | ✅ |
| PUT | `/disciplinas/actualizar/{id}` | Actualizar disciplina | ✅ |
| DELETE | `/disciplinas/apagar/{id}` | Remover disciplina | ✅ |
| GET | `/aproveitamentos` | Listar todos os aproveitamentos | ✅ |
| GET | `/aproveitamentos/id/{id}` | Buscar aproveitamento por ID | ✅ |
| GET | `/aproveitamentos/aluno/{alunoId}/semestre/{semestre}` | Buscar por aluno e semestre | ✅ |
| POST | `/aproveitamentos/criar` | Registar aproveitamento | ✅ |
| PUT | `/aproveitamentos/actualizar/{id}` | Actualizar aproveitamento | ✅ |
| DELETE | `/aproveitamentos/apagar/{id}` | Remover aproveitamento | ✅ |

---

## 👨‍💻 Autor

**Adérito Andala**
- GitHub: [@aderitoandala](https://github.com/aderitoandala)

---

## 📄 Licença

Este projecto está sob desenvolvimento. Licença a definir.
