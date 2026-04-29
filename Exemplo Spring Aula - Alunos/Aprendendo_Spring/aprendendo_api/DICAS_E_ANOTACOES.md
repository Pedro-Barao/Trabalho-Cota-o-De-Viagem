-CAMINHO:

=CONTROLLER -> SERVICE
=SERVICE -> REPOSITORY
=REPOSITORY -> SERVICE
=SERVICE -> CONTROLLER


-CONCEITOS:

=CONTROLLER: Direciona as rotas, ou seja, recebe as chamadas e parametros, assim repassando para o service (nele não terá regra de negócios)

=SERVICE: Responsável pela regra de negócios e mediador para chamada dos banco de dados (Repository)

=REPOSITORY: Aquele quem aplica as Querys e mexe diretamente no banco de dados