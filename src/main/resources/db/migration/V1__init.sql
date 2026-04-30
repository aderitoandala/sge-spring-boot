
---========== Sequences==================---
CREATE SEQUENCE aluno_seq START 1 INCREMENT 1;
CREATE SEQUENCE disciplina_seq START 1 INCREMENT 1;
CREATE SEQUENCE aproveitamento_seq START 1 INCREMENT 1;

---========== Tabelas independentes =========---
--- ==== custom_user ===---
CREATE TABLE custom_user (
    id       UUID         NOT NULL DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(20)  NOT NULL,

    CONSTRAINT pk_custom_user PRIMARY KEY (id),
    CONSTRAINT uk_custom_user_username UNIQUE (username)
);

--- ==== aluno ===---
CREATE TABLE aluno (
    id    BIGINT       NOT NULL DEFAULT nextval('aluno_seq'),
    nome  VARCHAR(20)  NOT NULL,
    email VARCHAR(100) NOT NULL,
    sexo  VARCHAR(20)  NOT NULL,

    CONSTRAINT pk_aluno PRIMARY KEY (id),
    CONSTRAINT uk_aluno_email UNIQUE (email)
);

--- ==== disciplina ===---
CREATE TABLE disciplina (
    id        BIGINT       NOT NULL DEFAULT nextval('disciplina_seq'),
    nome      VARCHAR(20)  NOT NULL,
    descricao VARCHAR(100),

    CONSTRAINT pk_disciplina PRIMARY KEY (id),
    CONSTRAINT uk_disciplina_nome UNIQUE (nome)
);

---======= Tabelas com dependências ==========---
--- ==== aproveitamento ===---
CREATE TABLE aproveitamento (
    id           BIGINT  NOT NULL DEFAULT nextval('aproveitamento_seq'),
    aluno_id     BIGINT  NOT NULL,
    disciplina_id BIGINT NOT NULL,
    semestre     INTEGER NOT NULL,
    nota1        DOUBLE PRECISION NOT NULL,
    nota2        DOUBLE PRECISION NOT NULL,

    CONSTRAINT pk_aproveitamento PRIMARY KEY (id),
    CONSTRAINT fk_aproveitamento_aluno
        FOREIGN KEY (aluno_id) REFERENCES aluno (id),
    CONSTRAINT fk_aproveitamento_disciplina
        FOREIGN KEY (disciplina_id) REFERENCES disciplina (id),
    CONSTRAINT uk_aluno_disciplina_semestre
        UNIQUE (aluno_id, disciplina_id, semestre)
);
