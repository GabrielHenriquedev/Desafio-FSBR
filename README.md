
# Desafio FSBR
## Aplicação Web de Cadastro de Clientes

Este é um projeto desenvolvido em Java 17 com Spring Boot, Thymeleaf e JPA, que permite o cadastro, visualização, edição e remoção de clientes. A aplicação também se integra ao serviço ViaCEP para consulta automática de endereços a partir do CEP informado pelo usuário.

## Tecnologias Usadas
- Java 17: Linguagem de programação utilizada para o desenvolvimento da aplicação.
- Spring Boot: Framework utilizado para criar a aplicação web.
- Thymeleaf: Motor de templates utilizado para renderizar as páginas HTML.
- JPA (Java Persistence API): Framework para persistência de dados, utilizado para interação com o banco de dados.
- Banco de Dados H2: Banco de dados embutido utilizado para armazenar os dados dos clientes durante o desenvolvimento.
- API ViaCEP: Serviço externo utilizado para buscar informações de endereço (bairro, cidade, estado) com base no CEP informado pelo usuário.


## Como Rodar a aplicação
Para rodar a aplicação em um ambiente Docker, siga os passos abaixo:

1. Clonar o Repositório
Primeiro, clone o repositório em sua máquina local:

` git clone https://github.com/seu-usuario/seu-repositorio.git`

` cd seu-repositorio`

2. Compilar a Aplicação
Certifique-se de ter o Maven instalado em sua máquina e compile o projeto com o seguinte comando:

` mvn clean package `

Isso irá gerar um arquivo JAR no diretório target.

4. Executar o Container Docker
Após construir a imagem, execute o container com o seguinte comando:

` docker run -p 8080:8080 clientmanager:latest `

- Nota: A porta 8080 é a porta exposta pela aplicação Spring Boot. Certifique-se de que a porta 8080 esteja livre em sua máquina.

5. Acessar a API
Após executar o container, você pode acessar a API em:


` http://localhost:8080/`


## API de Clientes

Esta API permite gerenciar uma lista de clientes, incluindo operações para listar, adicionar, atualizar e remover clientes. A API foi desenvolvida utilizando Spring Boot e segue uma arquitetura RESTful.

## Endpoints

1. Listar todos os clientes

- GET /clientes

2. Adicionar um novo cliente

- POST /clientes

3. Atualizar um cliente existente

- PUT /clientes/{email}

Descrição: Atualiza as informações de um cliente existente com base no email fornecido.

4. Deletar um cliente

- DELETE /clientes/{email}


Exemplo de Requisições HTTP

{
  "nome": "Carlos Souza",
  "email": "carlos@example.com",
  "telefone": "1122334455",
  "cep": 53550040
}


## Atenção

Este projeto não utiliza JSF, pois enfrentei dificuldades para configurá-lo corretamente. Apesar de entender sua sintaxe, não consegui integrá-lo à minha aplicação Spring. Tentei criar uma aplicação usando Maven, mas ainda assim encontrei problemas com o JSF.

O Thymeleaf, por outro lado, não oferece suporte nativo para os métodos HTTP PUT e DELETE em formulários HTML. Apesar de ter tentado contornar essa limitação, ainda não consegui uma solução ideal. Por isso, algumas funcionalidades na classe *ClientController* podem parecer um pouco improvisadas.

Reconheço que essa não é a melhor abordagem, mas minha principal barreira foi a configuração do JSF. Se for admitido, me dedicarei ao máximo para aprender a ferramenta.
