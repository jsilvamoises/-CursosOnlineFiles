CREATE TABLE departamento(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sigla VARCHAR(3) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO departamento(nome,sigla) values('DEFAULT','DFT');

CREATE TABLE email(
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   host VARCHAR(100) NOT NULL,
   port INTEGER NOT NULL DEFAULT 587,
   username VARCHAR(120) NOT NULL,
   password VARCHAR(255) NOT NULL,
   transport_protocol VARCHAR(60) NOT NULL DEFAULT "smtp",
   smtp_auth BOOL NOT NULL DEFAULT TRUE,
   smtp_starttls_enable BOOL NOT NULL DEFAULT TRUE,
   debug BOOL NOT NULL DEFAULT TRUE,
   departamento_id BIGINT NOT NULL,
   CONSTRAINT `FK_departamento_id` FOREIGN KEY (departamento_id) REFERENCES  departamento (id)
   
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO email(host,port,username,password,departamento_id)
VALUES('smtp.gmail.com',587,'usuario@gmail.com','@senha',1);
