/* INSERE CATEGORIAS */
INSERT INTO 
           categoria(nome) 
VALUES
		('Limpeza'),
		('Eletro domesticos'),
		('Eletronicos'),
		('Frios'),
		('Cama mesa e banho'),
		('Moda Masculina'),
		('Moda Feminina'),
		('Infato Juvenil'),
		('Escolar'),
		('Outros');
/* INSERE ESTADOS */
insert 
	into 
		estado(nome) 
	values
		('Rio de Janeiro'),
		('Sao Paulo'),
		('Minas Gerais'),
		('Roraima'),
		('Acre'),
		('Rondonia'),
		('Bahia'),
		('Paraiba'),
		('Ceara'),
		('Sergipe'),
		('Mato Grosso do Sul');
/* INSERE CIDADES */
insert 
	into 
		cidade(nome,estado_id) 
	values
		('Rio de Janeiro',1),
		('Sao Paulo',2),
		('Juiz de Fora',3),
		('Belo Horizonte',3),
		('Uberlandia',3),
		('Boa Vista',4),
		('Caracarai',4),
		('Amajari',4),
		('Bonfin',4),
		('Joao Pessoa',8),
		('Patos',8);
		
/* INSERE CLIENTES */
INSERT INTO 
			cliente(cpf_cnpj,email,nome,tipo) 
		VALUES
	     	 ('21604979500','maria98@gmail.com','Maria Candida Oliveira',1),
	   	     ('21604979501','carla@gmail.com','Carla de Jesus',1),
			 ('21604979502','juliana@gmail.com','Juliana Rodrigues',1),
			 ('21604979503','paulo@gmail.com','Paulo Ferreira',1),
			 ('21604979504','simao@gmail.com','Simao Pedro de Campos',1),
			 ('21604979505','andre@gmail.com','Andre Mauro',1),
			 ('21604979506','sandra@gmail.com','Sandra Cristina Carneiro',1),
			 ('21604979507','idelia@gmail.com','Idelia Candida Oliveira',1),
			 ('21604979508','Jeniffer@gmail.com','Jeniffer Palloma',1),
			 ('21604979509','itia@gmail.com','Itia Alagy Sarnivchetiev',1);
/* INSERE ENDERES VINCULADOS COM CLIENTES */
INSERT INTO 
           endereco (bairro,cep,complemento,logradouro,numero,cidade_id,cliente_id) 
       VALUES
		 ('Jardim Guanabara','16256-698','casa','Rua Tamburiu','99',1,1),
		 ('Mote Leste','16956-698','Apto 22','Av Macunaima','42',2,2),
		 ('Pedra Branca','16256-691','casa','Rua Timbau','22',3,3),
		 ('Jardim Am´erica','13956-698','Apto 22','Av Bertioga','42',2,4) ;
/* CADASTRA NOVOS PRODUTOS */
INSERT 
     INTO produto (nome,preco) 
values
    ('Caderno Capa dura 1M',10.00),
    ('Microcomputador Apple',10000.00),
    ('Smarth Phone ',1350.00),
    ('Geladeira Dupla Face',350.00),
    ('Kit Teclado e Mouse',80.00),
    ('Impressora HP',800.00),
    ('Refrigerante Refrigereco Laranja',2.65),
    ('Detergente Ype',8.00),
    ('Arroz Tio Joao',12.00),
    ('Veneno para baratas',12.00),
    ('Telefone sem fio',320.55);
/* VINCULA OS PRODUTOS A UMA CATEGORIA  */  
INSERT INTO 
          produto_categoria(produto_id,categoria_id)
values (1,9),(2,3),(3,3),(4,2),(5,3),(6,3),(7,10),(8,10),(9,10);
/* CRIA ALGUNS PEDIDOS DE EXEMPLO*/
INSERT INTO 
     pedido(instante,cliente_id,enderece_entrega_id)
values
     ('2018-01-01 22:22:22',1,1),
     ('2018-01-01 22:22:23',2,2),
     ('2018-01-01 22:22:23',3,3),
     ('2018-01-01 22:22:23',2,4);
     
INSERT INTO 
          item_pedido (desconto,preco,quantidade,pedido_id,produto_id) 
VALUES
      (0.00,1.56,10.00,1,1),
		(0.00,5.56,5.00,1,2),
		(0.00,3.56,3.00,1,5),
		(0.00,4.56,24.00,1,11);
		
INSERT INTO 
          item_pedido (desconto,preco,quantidade,pedido_id,produto_id) 
VALUES
      (0.00,1.56,10.00,2,1),
		(0.00,5.56,5.00,2,8),
		(0.00,3.56,3.00,2,3),
		(0.00,4.56,24.00,2,7);
INSERT INTO 
          item_pedido (desconto,preco,quantidade,pedido_id,produto_id) 
VALUES
      (0.00,1.56,10.00,3,1),
		(0.00,5.56,5.00,3,2),
		(0.00,3.56,3.00,3,3),
		(0.00,4.56,24.00,3,4);
		
INSERT INTO 
          item_pedido (desconto,preco,quantidade,pedido_id,produto_id) 
VALUES
      (0.00,1.56,10.00,4,1),
		(0.00,5.56,5.00,4,2),
		(0.00,3.56,3.00,4,3),
		(0.00,4.56,24.00,4,4);

/* INSERE UM NOVO PAGAMENTO*/	 
INSERT INTO pagamento(pedido_id,estado) values(1,1),(2,1),(3,1),(4,1);

/* INSERE PAGAMENTOS COM BOLETO */
INSERT INTO 
          pagamento_com_boleto(data_pagamento,data_vencimento,pedido_id) 
VALUES
     ('2018-01-01','2018-01-10',1),
     ('2018-01-01','2018-01-10',2),
     ('2018-01-01','2018-01-10',3);

/* INSERE PAGAMENTOS COM CARTAO */
INSERT INTO pagamento_com_cartao(numero_parcelas,pedido_id) VALUES(3,4);
