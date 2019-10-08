# ModuloAuditoria
Trabalho em desenvolvimento para Projeto de IC-IFSP Caraguatatuba

Para exportar o banco:

A conexão gerada pelo NetBeans não é Embedded, mas usa um servidor Derby.
A conexão ficou assim certo?
"jdbc:derby://localhost:1527/myDB;create=true;user=me;password=mine"
Você precisa trocar para algo assim:
"jdbc:derby:myDB;create=true;user=me;password=mine"

E a classe assim:
Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
Para assim:
Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

Faça um Clean/Build no seu projeto pelo NetBeans.
Ele vai gerar uma pasta Dist
Nela há o seu Jar, o Banco de Dados e a Lib/Derby.jar
Execute seu jar com esse comando: java -jar AddressBook.jar
Se der erro por que ele não encontrou as tabelas, é por que a base de dados é outra
você precisa ir na pasta do Derby do NetBeans que fica em:

Linux:
/home/seuusuario/.netbeans-derby

Se é Windows, deve estar na pasta do seu usuário
Lá você encontra uma pasta com o nome do seu banco de dados, por exemplo nesse caso é o myDB

Copie a pasta inteira para o diretório do seu projeto.
E sobreescreva a pasta existente.
##################################################################################################

Comando criar jks:
keytool -import -alias {{alias aqui}} -keystore {{ destino = C:\\NFE\\KEYSTORE\\nomedokeystore.jks }} -file {{certificado = C:\\certificado.cer }}
