**Prova de conceito**

Aplicação REST para consumo de mensagens.

Tecnologias:

_SpringBoot_
_Maven_
_Java Mail_
_AWS EC2 and SQS_
_Hibernate_
_PostgreSQL_
_log4j_
_Junit with Power Mockito_

Através de requisições rest é possível adicionar, remover, atualizar e listar templetes de email. Esses templetes são armazenadas numa base de dados e ada mensagem é salva juntamente com as informações de remetente, destinatário, quem receberá uma cópia dessa mensagem, além de um identificador único e uma chave. Para disparar o email a aplicação irá consumir mensagens de um fila onde o conteudo, dessa mensagem, é a chave salva no templete. O envio da msg para a fila está implementado no repositório client-test.

