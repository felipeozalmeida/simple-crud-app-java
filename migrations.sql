CREATE DATABASE users_crud_app;

CREATE TABLE users_crud_app.usuarios (
  id BIGINT NOT NULL AUTO_INCREMENT,
  login VARCHAR(255),
  senha VARCHAR(255),
  status VARCHAR(255),
  tipo VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE users_crud_app.pessoas (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255),
  cargo VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE users_crud_app.usuarios_pessoas (
  id BIGINT NOT NULL AUTO_INCREMENT,
  idUsuario BIGINT,
  idPessoa BIGINT,
  PRIMARY KEY (id)
);

ALTER TABLE users_crud_app.usuarios_pessoas
    ADD CONSTRAINT FK_PES_USU_ID FOREIGN KEY (idPessoa) REFERENCES users_crud_app.pessoas (id);
ALTER TABLE users_crud_app.usuarios_pessoas
    ADD CONSTRAINT FK_USU_PES_ID FOREIGN KEY (idUsuario) REFERENCES users_crud_app.usuarios (id);
