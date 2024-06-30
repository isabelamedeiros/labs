# labs

1- Modelagem do banco de dados seguindo as regras de normalização.
- Não existe mais de uma informação em uma célula.
- Os dados de cada tabela dependem da chave primaria.
- Os atributos das tabelas não dependem de atributos que não sejam a chave primaria.
- As tabelas estão relacionadas pelas chaves estrangeiras, criando o relacionamento das informações.
- A tabela venda_produtos recebe a chave estrangeira de produto_id e venda_id.
- A tabela vendas recebe a chave estrangeira usuario_id.
- Cada tabela possui um ID.
- A tabela venda_produtos para vendas e para produtos representa uma relação de muitos-para-muitos.
- A tabela usuarios para vendas, vendas para venda_produtos e produtos para venda_produtos representa uma relação de um-para-muitos.

**Diagrama entidade relacionamento**
![img.png](img.png)
