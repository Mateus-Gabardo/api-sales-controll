# api-sales-controll

API Rest utilzando Spring de cadastro (Create/Read/Update/Delete/List) de produtos e pedidos.

### Tecnologias utilizadas:

*   Java 11

*   Spring,

*   JPA,

*   Bean Validation

*   QueryDSL

*   REST com JSON

*   PostgreSQL

*   MockMVC

*   JUnit

### Como utilizar

Importe o projeto, e configure o acesso ao banco de dados disponível no arquivo `application.properties`

    sales-controll\src\main\resources\application.properties

O arquivo terá o seguinte conteúdo:

    spring.datasource.url= jdbc:postgresql://localhost:5432/sales-control-db
    spring.datasource.username=postgres
    spring.datasource.password=1234
    spring.jpa.hibernate.ddl-auto=update

    spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true

Navegue até `sales-controll\src\main\java\com\api\salescontroll` e rode o arquivo **SalesControllApplication**

No PostMan importe a collection disponível dentro do próprio projeto chamado: \*\*Sales-controll - API-postman\_colletion.json. \*\*

Nesse arquivo estarão as principais requisições que poderão ser feitas para acessar os endpoint bem como seus respectivos métodos GET, POST, PUT e DELETE, tendo a possibilidade de aplicar filtros e paginação.

Para respeitar a regra definida no relacionamento entre as entidades, gere primeiro um produto, em seguida crie um pedido (sale) para por fim linkar os dois criando um item-pedido (salesitem)

Alguns exemplos de URL:

End-point de produto

`http://localhost:8080/sale`

End-point de pedido

`http://localhost:8080/sale`

End-point para adicionar um item no pedido

`http://localhost:8080/saleitem`

End-point para aplicação de taxa sobre o pedido

`http://localhost:8080/saleitem/apply-discount`
