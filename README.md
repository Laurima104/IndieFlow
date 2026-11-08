# IndieFlow API

API REST para cadastro de projetos, membros, tarefas e bugs de jogos, usando Spring Boot, JPA e PostgreSQL.

## Requisitos e execução

- Java 17 ou superior.
- PostgreSQL acessível na URL configurada em `src/main/resources/application.properties`.
- Banco `indieflow` já criado no PostgreSQL.
- A senha do PostgreSQL é lida da variável de ambiente `pgAdmin`; não armazene a senha no repositório.

No PowerShell, antes de iniciar a aplicação:

```powershell
$env:pgAdmin = "sua_senha_do_postgres"
./gradlew.bat bootRun
```

O Hibernate está configurado com `ddl-auto=update` e criará/atualizará as tabelas ao iniciar. Faça backup antes de apontar a aplicação para uma base com dados importantes.

## Rotas CRUD

Cada recurso abaixo oferece `GET /{recurso}`, `GET /{recurso}/{id}`, `POST /{recurso}`, `PUT /{recurso}/{id}` e `DELETE /{recurso}/{id}`:

- `/projetos`
- `/membros`
- `/tarefas`
- `/bugs`

`POST` retorna `201 Created`; `DELETE` retorna `204 No Content`.

### Exemplos de corpo

Projeto (a data de início é preenchida automaticamente na criação):

```json
{
  "nome": "Meu jogo",
  "descricao": "Projeto de exemplo",
  "genero": "RPG",
  "status": "PLANEJAMENTO"
}
```

Membro:

```json
{
  "nome": "Ana Silva",
  "email": "ana@example.com",
  "especialidade": "Programação"
}
```

Tarefa:

```json
{
  "titulo": "Criar sistema de inventário",
  "descricao": "Implementar inventário inicial",
  "prioridade": "ALTA",
  "status": "PENDENTE",
  "prazo": "2026-11-12",
  "membroId": 1,
  "projetoId": 1
}
```

Bug:

```json
{
  "titulo": "Falha ao salvar",
  "descricao": "O progresso não é persistido",
  "gravidade": "ALTA",
  "status": "PENDENTE",
  "projetoId": 1
}
```

Os status aceitos para projeto são `PLANEJAMENTO`, `EM_DESENVOLVIMENTO`, `PAUSADO`, `CONCLUIDO` e `CANCELADO`. Prioridade: `BAIXA`, `MEDIA`, `ALTA`. Gravidade: `BAIXA`, `MEDIA`, `ALTA`, `CRITICA`. Status de tarefa/bug: `PENDENTE`, `EM_ANDAMENTO`, `CONCLUIDA`, `CANCELADA`.

## Regras de negócio

- E-mail de membro deve ser único (comparação sem diferenciar maiúsculas/minúsculas).
- Um projeto não pode ser excluído enquanto tiver tarefa ou bug associado.
- Tarefa e bug exigem projeto existente; tarefa pode ter um membro opcional, que também precisa existir.

## Respostas de erro

- `400 Bad Request`: corpo inválido ou campos que não passaram pela validação; a resposta lista os campos.
- `404 Not Found`: recurso relacionado ou solicitado não existe.
- `409 Conflict`: e-mail duplicado ou operação que viola uma regra/restrição de integridade.
