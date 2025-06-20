# Feirinhas UFRN - API

![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.2-brightgreen)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-blue)

API para a plataforma **Feirinhas UFRN**, uma vitrine digital criada para fortalecer e dar visibilidade contínua aos produtos vendidos nas feiras da Universidade Federal do Rio Grande do Norte.

## 🎯 Visão do Produto

[cite_start]O projeto "Feirinhas UFRN" nasceu para resolver uma dificuldade comum entre vendedores e compradores das feiras universitárias: a falta de um canal de divulgação contínuo.  [cite_start]O alcance dos produtos vendidos fica, muitas vezes, limitado aos dias e locais específicos dos eventos. 

[cite_start]Nossa plataforma busca ser o ponto de encontro digital entre os empreendedores da UFRN e a comunidade acadêmica, facilitando a descoberta de produtos, ampliando o alcance dos vendedores e fortalecendo a cultura empreendedora local. 

## 👥 Usuários

* **Vendedores:** Estudantes e pequenos empreendedores que vendem produtos artesanais, livros, roupas, itens da cultura geek, entre outros, nas feiras da universidade. 
* **Compradores:** Alunos, servidores e frequentadores da UFRN interessados em descobrir e comprar os produtos únicos oferecidos. 

## ✨ Funcionalidades Principais

A plataforma foi pensada para ser simples, gratuita e focada na comunidade da UFRN. 

### Para Vendedores:
* **Publicação de Produtos:** Crie um perfil e publique seus produtos com fotos, descrições, preços e formas de contato (ex: WhatsApp). 
* **Agenda:** Informe à comunidade quando e em qual feirinha da UFRN você estará presente. 

### Para Compradores:
* **Vitrine Digital:** Explore os produtos disponíveis nas feiras em um único lugar. 
* **Filtros Inteligentes:** Encontre o que procura com filtros por local, data do evento ou tipo de produto. 
* **Planejamento:** Descubra produtos interessantes e programe-se para visitar os vendedores nas feiras. 

## 🚀 Tecnologias Utilizadas

Este projeto é a API backend do sistema, construída com tecnologias modernas e robustas do ecossistema Java.

* **Linguagem:** Java 17
* **Framework:** Spring Boot 3.5.2
* **Módulos Spring:**
    * `Spring Web`: Para a criação de endpoints RESTful.
    * `Spring Data JPA`: Para persistência de dados de forma simplificada.
    * `Spring Security`: Para controle de autenticação e autorização.
    * `Spring Validation`: Para validação dos dados de entrada.
* **Banco de Dados:** PostgreSQL
* **Build Tool:** Maven
* **Utilitários:** Lombok

## 🛠️ Como Começar (Setup Local)

Siga os passos abaixo para configurar e executar o projeto em seu ambiente de desenvolvimento.

**Pré-requisitos:**
* JDK 17 ou superior
* Maven 3.x
* PostgreSQL instalado e em execução

**1. Clone o repositório:**
```bash
git clone https://github.com/hugofsantos/Feirinhas-UFRN.git
cd feirinhas-ufrn
```

**2. Configure o Banco de Dados:**
Abra o arquivo `src/main/resources/application.properties` e configure as credenciais de acesso ao seu banco de dados PostgreSQL.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/feirinhas_ufrn_db
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
spring.jpa.hibernate.ddl-auto=update
```
*Observação: Crie um banco de dados com o nome `feirinhas_ufrn_db` ou altere a URL para um de sua preferência.*

**3. Instale as dependências:**
O Maven cuidará disso para você. Execute o comando na raiz do projeto:
```bash
mvn clean install
```

**4. Execute a aplicação:**
```bash
mvn spring-boot:run
```

Pronto! A API estará rodando em `http://localhost:8080`.

## 📈 Futuro do Projeto

A longo prazo, a plataforma poderá evoluir para incluir novas funcionalidades, como: 
* Agendamento de participação em feirinhas. 
* Sistema de avaliação de vendedores. 
* Vendas diretas pela plataforma. 
* Expansão para outros eventos ou instituições de ensino. 

---