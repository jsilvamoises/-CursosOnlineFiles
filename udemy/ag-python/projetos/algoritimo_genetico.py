#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Thu Mar 15 12:45:00 2018

@author: moises
"""
from random import random
import matplotlib.pyplot as plt
import pymysql as mariadb
class Produto():
    def __init__(self,nome,espaco,valor):
        self.nome = nome
        self.espaco = espaco
        self.valor = valor

class Individuo():
    def __init__(self,espacos,valores,limite_espacos,geracao=0):
        self.espacos = espacos
        self.valores = valores
        self.limite_espacos  = limite_espacos
        self.espaco_usado = 0
        self.nota_avaliacao = 0
        self.geracao = geracao        
        self.cromossomo = []
        
        for i in range(len(espacos)):
            if random() < 0.5:
                self.cromossomo.append("0")
            else:
                self.cromossomo.append("1")
    def avaliacao(self):
        nota=0
        soma_espacos = 0
        for i in range(len(self.cromossomo)):
            if self.cromossomo[i] == "1":
                nota+=self.valores[i]
                soma_espacos+= self.espacos[i]
        if soma_espacos > self.limite_espacos:
            nota = 1
        self.nota_avaliacao = nota
        self.espaco_usado = soma_espacos
        
    def crossover(self, outro_individuo):
        corte = int(round(random() * len(self.cromossomo)))
        #print("Corte %s " % corte)
        
        filho1 = outro_individuo.cromossomo[0:corte]+self.cromossomo[corte::]
        #print(filho1)
        
        filho2 = self.cromossomo[0:corte]+outro_individuo.cromossomo[corte::]
        #print(filho2)
         
        filhos = [
                Individuo(self.espacos,self.valores,self.limite_espacos,self.geracao+1),
                Individuo(self.espacos,self.valores,self.limite_espacos,self.geracao+1)
                ]
        filhos[0].cromossomo = filho1
        filhos[1].cromossomo = filho2
        
        return filhos
    
    def mutacao(self,taxa_mutacao):
        #print("Antes %s" % taxa_mutacao)
        for i in range (len(self.cromossomo)):
            if random() < taxa_mutacao:
                if self.cromossomo[i] == "1":
                    self.cromossomo[i] = "0"
                else:
                    self.cromossomo[i] = "1"
        #print("Depois %s" % self.cromossomo)            
        return self
                
        
class AlgoritimoGenetico():
    def __init__(self,tamanho_populacao):
        self.tamanho_populacao = tamanho_populacao
        self.populacao = []
        self.geracao = 0
        self.melhor_solucao = 0
        self.lista_solucoes = []
    def inicializa_populacao(self,espacos,valores,limite_espacos):
        for i in range(self.tamanho_populacao):
            self.populacao.append(Individuo(espacos,valores,limite_espacos))
        self.melhor_solucao = self.populacao[0]
    def ordenaPopulacao(self):
        self.populacao = sorted(self.populacao,key=lambda populacao: populacao.nota_avaliacao,
                            reverse=True)
    def melhor_individuo(self,individuo):
        if individuo.nota_avaliacao > self.melhor_solucao.nota_avaliacao:
            self.melhor_solucao = individuo
    def soma_avalicao(self):
        soma = 0
        for individuo in self.populacao:
            soma += individuo.nota_avaliacao
        return soma
    def seleciona_pai(self,soma_avaliacao):
        pai = -1
        valor_sorteado = random() * soma_avaliacao
        soma = 0
        i = 0
        while i < len(self.populacao) and soma < valor_sorteado:
            soma += self.populacao[i].nota_avaliacao
            pai += 1;
            i +=1
        return pai
    def visaualiza_geracao(self):
        melhor = self.populacao[0]
        print("Geracao: %s -> Valor: %s Espaço: %s Cromossomo: %s " % (self.populacao[0].geracao, melhor.nota_avaliacao, melhor.espaco_usado, melhor.cromossomo))
       # print("Geração: %s -> Valor: %s Espaco: %s Cromossomo: %s" % (self.populacao[0].geracao, melhor.nota_avaliacao,melhor.espacao_usado,melhor.cromossomo))

    def resolver(self,taxa_mutacao, numero_geracoes,espacos,valores,limite_espacos):
        self.inicializa_populacao(espacos,valores,limite_espacos)
        
        for individuo in self.populacao:
            individuo.avaliacao()
            
        self.ordenaPopulacao()
        self.melhor_solucao = self.populacao[0]
        self.lista_solucoes.append(self.melhor_solucao.nota_avaliacao)
        self.visaualiza_geracao
        
        for geracao in range(numero_geracoes):
            soma_avaliacao = self.soma_avalicao()
            nova_populacao = []
            
            for individuos_gerados in range(0, self.tamanho_populacao, 2):
                pai1 = self.seleciona_pai(soma_avaliacao)
                pai2 = self.seleciona_pai(soma_avaliacao)
                
                filhos = self.populacao[pai1].crossover(self.populacao[pai2])
                
                nova_populacao.append(filhos[0].mutacao(taxa_mutacao))
                nova_populacao.append(filhos[1].mutacao(taxa_mutacao))
            
            self.populacao = list(nova_populacao)
            
            for individuo in self.populacao:
                individuo.avaliacao()
                
            self.ordenaPopulacao()
            
            self.visaualiza_geracao()
            
            melhor = self.populacao[0]
            self.lista_solucoes.append(melhor.nota_avaliacao)
            self.melhor_individuo(melhor)
        print
        print("Melhor Solução : Geração: %s -> Valor: %s Espaço: %s Cromossomo: %s" % (self.melhor_solucao.geracao,
              self.melhor_solucao.nota_avaliacao,
              self.melhor_solucao.espaco_usado,
              self.melhor_solucao.cromossomo))
        
        return self.melhor_solucao.cromossomo
        
        
if __name__ == '__main__':
    lista_produtos = []
    conexao = mariadb.connect(host='localhost',user='root',password='1982',db='ia')
    cursor = conexao.cursor()
    cursor.execute('select nome,espaco,valor,quantidade from produtos')
    
    for produto in cursor:
        for i in range(produto[3]):
            lista_produtos.append(Produto(produto[0],produto[1],produto[2]))
    
    
    cursor.close()
    conexao.close()
    
    
    #p1 = Produto('iphone6',0.0000899,2199.12)
    '''
    lista_produtos =[]
    lista_produtos.append(Produto("Geladeira Dako", 0.751, 999.90))
    lista_produtos.append(Produto("Iphone 6", 0.0000899, 2911.12))
    lista_produtos.append(Produto("TV 55' ", 0.400, 4346.99))
    lista_produtos.append(Produto("TV 50' ", 0.290, 3999.90))
    lista_produtos.append(Produto("TV 42' ", 0.200, 2999.00))
    lista_produtos.append(Produto("Notebook Dell", 0.00350, 2499.90))
    lista_produtos.append(Produto("Ventilador Panasonic", 0.496, 199.90))
    lista_produtos.append(Produto("Microondas Electrolux", 0.0424, 308.66))
    lista_produtos.append(Produto("Microondas LG", 0.0544, 429.90))
    lista_produtos.append(Produto("Microondas Panasonic", 0.0319, 299.29))
    lista_produtos.append(Produto("Geladeira Brastemp", 0.635, 849.00))
    lista_produtos.append(Produto("Geladeira Consul", 0.870, 1199.89))
    lista_produtos.append(Produto("Notebook Lenovo", 0.498, 1999.90))
    lista_produtos.append(Produto("Notebook Asus", 0.527, 3999.00))'''
    
    #for produto in lista_produtos:
        #print(produto.nome)
    espacos = []
    valores = []
    nomes = []
    for produto in lista_produtos:
        espacos.append(produto.espaco)
        valores.append(produto.valor)
        nomes.append(produto.nome)
        
    limite = 3.14*len(lista_produtos)
    tamanho_populacao = 20
    taxa_mutacao = 0.01
    numero_geracoes = 200
    
    ag = AlgoritimoGenetico(tamanho_populacao)
    
    
    resultado = ag.resolver(taxa_mutacao,numero_geracoes,espacos,valores,limite)
    
    for i in range(len(lista_produtos)):
        if resultado[i] == "1":
            print("Nome: %s RS %s " % (lista_produtos[i].nome,lista_produtos[i].valor))
    
    
    #for valor in ag.lista_solucoes:
    #    print(valor)
    
    plt.plot(ag.lista_solucoes)
    plt.title("Acompanhamento dos valore")
    plt.show
    '''
    ag = AlgoritimoGenetico(tamanho_populacao)
    ag.inicializa_populacao(espacos,valores,limite)
    
    for individuo in ag.populacao:
        individuo.avaliacao()
        
    ag.ordenaPopulacao()
    ag.melhor_individuo(ag.populacao[0])
    
    soma = ag.soma_avalicao()
    #print("Soma Avaliacoes %s" % soma)
    
    nova_populacao = []
    
    probabilidade_mutacao = 0.01
    
    for individuos_gerados in range(0, ag.tamanho_populacao, 2):
        pai1 = ag.seleciona_pai(soma)
        pai2 = ag.seleciona_pai(soma)
        
        filhos = ag.populacao[pai1].crossover(ag.populacao[pai2])
        nova_populacao.append(filhos[0].mutacao(probabilidade_mutacao))
        nova_populacao.append(filhos[1].mutacao(probabilidade_mutacao))
   
    ag.populacao = list(nova_populacao)
    
    for individuo in ag.populacao:
        individuo.avaliacao()
    ag.ordenaPopulacao()
    ag.melhor_individuo(ag.populacao[0])
    
    soma = ag.soma_avalicao()
    
    print("Melhor solucao para o problema %s " % ag.melhor_solucao.cromossomo)
    print("Melhor Note %s " % ag.melhor_solucao.nota_avaliacao)
    
    
    
    #for i in range( ag.tamanho_populacao):
    #    print('**** individuo %s **** ' % i)
    #    print("Espacos utilizados %s " % str(ag.populacao[i].espacos))
    #    print("Valores %s" % str(ag.populacao[i].valores))
    #    print("Cromossomo %s " % str(ag.populacao[i].cromossomo))
    #    print("Nota = %s " % ag.populacao[i].nota_avaliacao)
    
    #individuo1 = Individuo(espacos,valores,limite)
    #print("indivíduo1")
    
    #print("Espaco %s"%str(individuo1.espacos))
    #print("Valores %s"%str(individuo1.valores))
    #print("Cromossomo %s"%str(individuo1.cromossomo))
    
    #print("\n#### Componentes da carga ####")
    #for i in range(len(lista_produtos)):
       # if individuo1.cromossomo[i] == "1":
         #  print("Nome %s R$ %s "%(lista_produtos[i].nome,lista_produtos[i].valor))
           
    #individuo1.avaliacao()
    #print("Nota %s"% individuo1.nota_avaliacao)
    #print("Espaco Usado %s" % individuo1.espaco_usado)
    
    #individuo2 = Individuo(espacos,valores,limite)
    #print("indivíduo2")
    #individuo2.avaliacao()
    #print("Nota %s"% individuo2.nota_avaliacao)
    #print("Espaco Usado %s" % individuo2.espaco_usado)

    #individuo1.crossover(individuo2)
    #individuo1.mutacacao(0.05)
    #individuo2.mutacacao(0.05)'''