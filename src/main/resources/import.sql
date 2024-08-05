insert into cargos (descricao) values ('Engenheiro Tec. Responsável')
insert into cargos (descricao) values ('Engenheiro Agrimensor')
insert into cargos (descricao) values ('Engenheiro Civil')
insert into cargos (descricao) values ('Engenheiro Ambiental')


insert into empresas (cnpj, nome, tel1, email, rua, numero, complemento, bairro, cep, cidade, uf, status_empresa) values ( '00.209.792/0001-09', 'ERG Engenharia', '(31) 2138-4700', 'erg@ergbh.com.br', 'R. Rio Grande do Sul', '1066', '1º andar', 'Santo Agostinho', '30170-111', 'Belo Horizonte', 'MG', 'ATIVA');
insert into empresas (cnpj, nome, tel1, email, rua, numero, complemento, bairro, cep, cidade, uf, status_empresa) values ( '00.209.792/0001-09', 'Morais', '(31) 2138-4700', 'erg@ergbh.com.br', 'R. Rio Grande do Sul', '1066', '1º andar', 'Santo Agostinho', '30170-111', 'Belo Horizonte', 'MG', 'ATIVA');


insert into centrocusto (numero, descricao, datainicio, datafim, status) values ('510','Tecnologia da Informação','16/04/2024','16/05/2024','FINALIZADO')
insert into centrocusto (numero, descricao, datainicio, datafim, status) values ('81','Samarco Rotina','16/04/2024','16/05/2024','ATIVO')
insert into centrocusto (numero, descricao, datainicio, datafim, status) values ('83','Mineração Usiminas S/A','16/04/2024','16/05/2024','ATIVO')

insert into funcionarios (nome, cpf, admissao, matricula, nascimento, genero, estado_civil, grau, tel1, email, cidade, uf, id_empresas, id_centrocusto, id_cargos) values ('Bruna', '099.259.606-81', '02/08/2023', '0052', '03/02/1994', 'FEMININO', 'SOLTEIRO', 'SUPERIOR_COMPLETO' , '(31) 9 9477-7720', 'bruna.blm13@gmail.com', 'Contagem', 'MG', 1, 1, 2);
insert into funcionarios (nome, cpf, admissao, matricula, nascimento, genero, estado_civil, grau, tel1, email, cidade, uf, id_empresas, id_centrocusto, id_cargos) values ('Carlos', '088.888.888-88', '02/08/2024', '0062', '03/02/1990', 'MASCULINO', 'SOLTEIRO', 'SUPERIOR_COMPLETO' , '(31) 9 9999-9999', 'calos.ti@gmail.com', 'Belo Horizonte', 'MG', 1, 1, 2);


insert into clientes (cnpj, nome, razaosocial, tel1, email, rua, numero, complemento, bairro, cep, cidade, uf, id_empresas) values ( '33.592.510/0001-54', 'Vale','Vale', '0800 285 7000', 'vale@vale.com', 'Rodovia MG 040', 'KM 49', 'n/a', 'Santos Anjos', 'Brumadinho', '35.460.000', 'MG', 1);
insert into clientes (cnpj, nome, razaosocial, tel1, email, rua, numero, complemento, bairro, cep, cidade, uf, id_empresas) values ( '33.131.541/0001-08', 'CBMM', 'CBMM','(31) 3226-2120', 'cbmm@cbmm.com', 'R. Brasil', '604', 'n/a', 'Jardim Leblon', 'Belo Horizonte', '30180-910', 'MG', 2);

insert into tiposervicos (descricao) values ('Geoprocessamento')
insert into tiposervicos (descricao) values ('Servico de Campo')
insert into tiposervicos (descricao) values ('Visita Tecnica')

insert into ordemservico (descricao, datainicio, datafim, id_centrocusto, id_tiposervicos) values ('Servico de Campo', '16/04/2024', '16/05/2024', 1, 2)
insert into ordemservico (descricao, datainicio, datafim, id_centrocusto, id_tiposervicos) values ('Palestras', '16/04/2024', '16/05/2024', 2, 1)
insert into ordemservico (descricao, datainicio, datafim, id_centrocusto, id_tiposervicos) values ('Visita Tecnica', '16/04/2024', '16/05/2024', 3, 1)
insert into ordemservico (descricao, datainicio, datafim, id_centrocusto, id_tiposervicos) values ('Treinamento', '16/04/2024', '16/05/2024', 1, 2)


insert into atividades (descricao) values ('Acompanhamento Atividades em Campo')
insert into atividades (descricao) values ('Analise de Documentos')
insert into atividades (descricao) values ('Analise Propostas')
insert into atividades (descricao) values ('CheckList')

insert into apontamentos  (local, data, minutos, minutosextra, obs, id_atividades, id_funcionarios, id_ordemservico) values ( 'Brumadinho', '16/04/2024', '30', '50', 'teste observacao', 3, 2, 2);
insert into apontamentos  (local, data, minutos, minutosextra, obs, id_atividades, id_funcionarios, id_ordemservico) values ( 'Belo Horizonte', '01/08/2024', '8', '40', 'teste observacao', 3, 1, 3);

INSERT INTO tiposervicoatividade (id_tiposervico, id_atividade) VALUES (1, 1);
INSERT INTO tiposervicoatividade (id_tiposervico, id_atividade) VALUES (1, 2);

INSERT INTO centrocustotiposervico (id_centrocusto, id_tiposervico) VALUES (2, 1);
INSERT INTO centrocustotiposervico (id_centrocusto, id_tiposervico) VALUES (1, 2);

INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/auth/login', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/auth/register', 'LIVRE_ACESSO');

INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/cargos/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/centrocusto/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/ordemservico/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/tiposervico/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/atividades/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/empresas/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/clientes/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/funcionarios/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/apontamentos/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/permissoes/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/usuarios/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/tiposervico-atividade/salvar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('POST', '/centrocusto-tiposervico/salvar', 'LIVRE_ACESSO');

INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/cargos/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/centrocusto/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/ordemservico/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/tiposervico/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/atividades/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/empresas/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/clientes/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/funcionarios/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/apontamentos/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/permissoes/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/usuarios/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/tiposervico-atividade/listar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/centrocusto-tiposervico/listar', 'LIVRE_ACESSO');

INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/cargos/buscar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/centrocusto/buscar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/ordemservico/buscar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/tiposervico/buscar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/atividades/buscar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/empresas/buscar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/clientes/buscar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/funcionarios/buscar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/apontamentos/buscar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/permissoes/buscar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/usuarios/buscar/{id}', 'LIVRE_ACESSO');

INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/cargos/atualizar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/centrocusto/atualizar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/ordemservico/atualizar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/tiposervico/atualizar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/atividades/atualizar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/empresas/atualizar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/clientes/atualizar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/funcionarios/atualizar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/apontamentos/atualizar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/permissoes/atualizar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/usuarios/atualizar', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('PUT', '/tiposervico-atividade/atualizar', 'LIVRE_ACESSO');

INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/cargos/deletar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/centrocusto/deletar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/ordemservico/deletar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/tiposervico/deletar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/atividades/deletar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/empresas/deletar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/clientes/deletar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/funcionarios/deletar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/apontamentos/deletar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/permissoes/deletar/{id}', 'ADMIN');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/usuarios/deletar/{id}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('DELETE', '/tipo-servico-atividade/deletar/{id}', 'LIVRE_ACESSO');

INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/empresas/nomes', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/centrocusto/numeros', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/cargos/nomes', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/funcionarios/nomes', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/tiposervico/descricao', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/atividades/descricao', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/ordemservico/descricao', 'LIVRE_ACESSO');

INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/centrocusto-tiposervico/tiposervico/{idCentroCusto}', 'LIVRE_ACESSO');
INSERT INTO permissao (httpmethod, urlpattern, role) VALUES ('GET', '/tiposervico-atividade/atividade/{idTipoServico}', 'LIVRE_ACESSO');
