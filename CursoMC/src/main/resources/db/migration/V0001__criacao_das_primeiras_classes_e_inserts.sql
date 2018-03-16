CREATE TABLE IF NOT EXISTS `categoria` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `estado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `cidade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `estado_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkworrwk40xj58kevvh3evi500` (`estado_id`),
  CONSTRAINT `FKkworrwk40xj58kevvh3evi500` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE IF NOT EXISTS `cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf_cnpj` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKdijsw0oltnegm4l7km32p5wiq` (`email`,`cpf_cnpj`),
  UNIQUE KEY `UK_d9fm2y08ggvsys7gxchljvrgw` (`cpf_cnpj`),
  UNIQUE KEY `UK_cmxo70m08n43599l3h0h07cc6` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE IF NOT EXISTS `endereco` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `cidade_id` bigint(20) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8b1kcb3wucapb8dejshyn5fsx` (`cidade_id`),
  KEY `FK8s7ivtl4foyhrfam9xqom73n9` (`cliente_id`),
  CONSTRAINT `FK8b1kcb3wucapb8dejshyn5fsx` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`),
  CONSTRAINT `FK8s7ivtl4foyhrfam9xqom73n9` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `preco` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `pedido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `instante` datetime(6) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `enderece_entrega_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK30s8j2ktpay6of18lbyqn3632` (`cliente_id`),
  KEY `FKmgi9t4arxsm9dqf3advmdhneb` (`enderece_entrega_id`),
  CONSTRAINT `FK30s8j2ktpay6of18lbyqn3632` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKmgi9t4arxsm9dqf3advmdhneb` FOREIGN KEY (`enderece_entrega_id`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `item_pedido` (
  `desconto` decimal(19,2) DEFAULT NULL,
  `preco` decimal(19,2) DEFAULT NULL,
  `quantidade` decimal(19,2) DEFAULT NULL,
  `pedido_id` bigint(20) NOT NULL,
  `produto_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pedido_id`,`produto_id`),
  KEY `FKtk55mn6d6bvl5h0no5uagi3sf` (`produto_id`),
  CONSTRAINT `FK60ym08cfoysa17wrn1swyiuda` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FKtk55mn6d6bvl5h0no5uagi3sf` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `pagamento` (
  `pedido_id` bigint(20) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FKthad9tkw4188hb3qo1lm5ueb0` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `pagamento_com_boleto` (
  `data_pagamento` datetime(6) DEFAULT NULL,
  `data_vencimento` datetime(6) DEFAULT NULL,
  `pedido_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FKcr74vrxf8nfph0knq2bho8doo` FOREIGN KEY (`pedido_id`) REFERENCES `pagamento` (`pedido_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `pagamento_com_cartao` (
  `numero_parcelas` int(11) DEFAULT NULL,
  `pedido_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FKta3cdnuuxclwfh52t4qi432ow` FOREIGN KEY (`pedido_id`) REFERENCES `pagamento` (`pedido_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `produto_categoria` (
  `produto_id` bigint(20) NOT NULL,
  `categoria_id` bigint(20) NOT NULL,
  KEY `FKq3g33tp7xk2juh53fbw6y4y57` (`categoria_id`),
  KEY `FK1c0y58d3n6x3m6euv2j3h64vt` (`produto_id`),
  CONSTRAINT `FK1c0y58d3n6x3m6euv2j3h64vt` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`),
  CONSTRAINT `FKq3g33tp7xk2juh53fbw6y4y57` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE IF NOT EXISTS `telefone` (
  `cliente_id` bigint(20) NOT NULL,
  `telefones` varchar(255) DEFAULT NULL,
  KEY `FK8aafha0njkoyoe3kvrwsy3g8u` (`cliente_id`),
  CONSTRAINT `FK8aafha0njkoyoe3kvrwsy3g8u` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


