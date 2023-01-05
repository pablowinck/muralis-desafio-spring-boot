# Etapa Técnica - Muralis
### Problema
Criar um projeto Spring Boot para permitir:
- Cadastrar um cliente
- Consultar um cliente
- Alterar um cliente
- Excluir um cliente
- Listar clientes
- Pesquisar clientes

### Regras
- Ao receber o CEP do cliente, deve-se consultar a API do **viaecep** ou similar, para adquirir os dados do cep e registrar no banco de dados adquiridos atraves da API.

---

### Solução proposta
- Para consulta do CEP não atrapalhar o fluxo de cadastro ou atualização do cliente, foi aplicado event sourcing para posterior de criar ou editar o usuário, lançar um evento, o qual a classe que consome, pesquisa de fato o CEP e atualiza o usuário de forma assíncrona.
- Foi adicionado resiliência a classe do CEP, para caso a API externa esteja com algum problema, não atrapalhar o fluxo de cadastro ou atualização do cliente. Além de garantir por um maior tempo, a consistência dos dados de endereço.

### Resiliência
Foi adicionado o retry do resiliencie4j, com a seguinte configuração:
- maxAttempts: 5
- waitDuration: 4s
- exponentialBackoffMultiplier: 2

Logo, a cada tentativa de falha, o tempo de espera para a próxima tentativa será o dobro do anterior, e o número máximo de tentativas será de 5.

Como as tentativas são assíncronas, o usuário não será afetado pelo tempo de espera.

## Como rodar aplicacao
- Para rodar a aplicação, basta executar o comando abaixo:
```bash
./mvnw spring-boot:run
```
- Para rodar os testes, basta executar o comando abaixo:
```bash
./mvnw test
```