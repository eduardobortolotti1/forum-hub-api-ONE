# FórumHub - Challenge Back End

Este projeto foi desenvolvido como projeto final do curso da ONE (Oracle Next Education). Nele, implementamos uma API
RESTful em Java com Spring Boot, que simula o funcionamento de um fórum de discussão, semelhante ao utilizado na
plataforma Alura. O principal objetivo é permitir que os usuários possam criar, visualizar, atualizar e excluir tópicos,
bem como gerenciar respostas, garantindo que somente o dono de cada conteúdo possa modificá-lo.

## Sobre o Projeto

O FórumHub é uma API que oferece as funcionalidades básicas de um fórum:

- **Criar Tópicos:** Permite que usuários autenticados criem novos tópicos de discussão.
- **Listar Tópicos:** Exibe todos os tópicos criados.
- **Visualizar Tópico Específico:** Mostra os detalhes de um tópico, incluindo suas respostas.
- **Atualizar Tópicos:** Permite a alteração de tópicos, porém somente pelo usuário que o criou.
- **Excluir Tópicos:** Possibilita a remoção de tópicos, também restrito ao dono do conteúdo.
- **Gerenciar Respostas:** Inclui operações de CRUD para respostas, com a mesma lógica de validação de propriedade.

Além disso, a API implementa autenticação e autorização com Spring Security e utiliza tokens JWT para garantir a
segurança das operações.

## Tecnologias Aplicadas

- **Java** - Linguagem de programação utilizada no desenvolvimento.
- **Spring Boot** - Framework principal para criação da API RESTful.
- **Spring Security** - Gerenciamento de autenticação e autorização.
- **JWT (JSON Web Token)** - Para geração e validação dos tokens de acesso.
- **Spring Data JPA** - Facilita a interação com a base de dados.
- **MySQL** - Banco de dados relacional utilizado.
- **Maven** - Gerenciamento de dependências e build do projeto.

## Deploy

Você pode executar este projeto localmente ou implantá-lo em um servidor de sua preferência. Para desenvolvimento e
testes, recomenda-se utilizar o perfil de desenvolvimento com o banco H2.

## Instalação

### Pré-requisitos

Certifique-se de ter instalados:

- [Java 11 ou superior](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/)
- [Git](https://git-scm.com/)

### Configuração do Projeto Local

1. **Clonar o repositório**
   ```bash
   git clone https://github.com/eduardobortolotti1/forum-hub-api-ONE
   cd forum-hub-api-ONE

2. **Configurar as Variáveis de Ambiente**

### Configurações do Banco de Dados MySQL (application.properties)

```bash
   spring.datasource.url=jdbc:mysql://localhost/forum-hub-api
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.username=
   spring.datasource.password=
   ```

### Build e Execução do Projeto

```bash
   mvn clean install
   mvn spring-boot:run
```

### Estrutura de Endpoints
Você pode acessar todos os endpoints importando o arquivo JSON no Postman por este [Link](https://api.postman.com/collections/33658122-f5e4cf69-a457-49dc-98ab-badd4dedf0a2?access_key=PMAT-01JK9BJQ6D4P7CYD2EWG2VYTQ0)

![Endpoints screenshot](https://i.imgur.com/gThozQM.png)

Demonstração de Uso
Exemplo: Criação de um Novo Tópico

Requisição:

POST /topicos  
Content-Type: application/json  
Authorization: Bearer <seu_token_jwt>  

{  
"titulo": "Como implementar JWT no Spring Boot?",  
"mensagem": "Gostaria de entender melhor como gerar e validar tokens JWT no Spring Boot."  
"idCurso": 1  
}

Resposta:

201 Created  
Location: /topicos/20  
{  
"titulo": "Como implementar JWT no Spring Boot?",  
"mensagem": "Gostaria de entender melhor como gerar e validar tokens JWT no Spring Boot.",  
"dataCriacao": "2025-02-04T18:06:56.1433309",  
"status": "NAO_RESPONDIDO",  
"autor": "Eduardo Bortolotti",  
"curso": "Administração de redes"  
}

Histórico e Contexto

Este desafio, denominado FórumHub, tem como objetivo demonstrar os processos internos de um fórum: a criação e
manipulação de tópicos e respostas, com toda a lógica de negócio envolvida. O projeto foi desenvolvido utilizando
conceitos de CRUD, autenticação/validação de dados e integração com uma base de dados relacional.

A ideia é replicar, no nível de back end, como os dados são armazenados, relacionados e protegidos em um ambiente real,
onde a segurança e a propriedade das informações são fundamentais. Todas as alterações em tópicos e respostas são
validadas para garantir que apenas o dono do conteúdo possa modificá-lo, evitando alterações indevidas por outros
usuários.
