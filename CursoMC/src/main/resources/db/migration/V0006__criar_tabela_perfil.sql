CREATE TABLE perfis(
     cliente_id BIGINT NOT NULL,
     perfis VARCHAR(30),
     CONSTRAINT `FK_PERFIL_USER` FOREIGN KEY (cliente_id) REFERENCES cliente (id)
)ENGINE=InnoDB CHARSET=utf8;