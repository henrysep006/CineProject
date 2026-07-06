# 🎬 CineProject

Este é um sistema desenvolvido em Java e Spring Boot para gerenciamento de cinema com persistência em banco de dados relacional MySQL.

Para rodar a aplicação em sua máquina local, primeiro crie um banco de dados no seu MySQL local. Em seguida, acesse o arquivo de configuração em `src/main/resources/application.properties` e insira as credenciais de acesso (URL, usuário e senha) do seu banco de dados recém-criado. Com o banco configurado, abra o projeto em sua IDE de preferência e execute o comando "Clean and Build" para baixar as dependências Maven e compilar os arquivos limpos. Após o build, execute a classe principal do sistema localizada no caminho `src/main/java/com/cinema/CineProject/CineProjectApplication.java` para iniciar o servidor interno. Assim que o terminal indicar que o sistema inicializou com sucesso, basta abrir o seu navegador de preferência e acessar o endereço http://localhost:8080 para visualizar e testar a aplicação de forma interativa.

Desenvolvido por Henry Pinhatti.
