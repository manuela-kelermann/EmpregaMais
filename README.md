# EMPREGA+
### Projeto colaborativo de alunos da Residência Full-Stack.

### Stack: Java,Spring,SupaBase,TS e React

Desenvolvimento de soluções tech e culturais a partir de atividades de Design Thinking

#### O problema: 
"Muitas pessoas têm dificuldade para encontrar e acessar oportunidades de trabalho compatíveis com o seu perfil"

#### A hipótese: 
Aplicação web que beneficie contratante e candidato, com fácil navegação para ambos os lados.
Fácil acesso aos perfis de candidatos e objetividade de informações. 
Transparência e clareza de requisitos e expectativas de cada vaga. 

INFORMAÇÕES DOS RELATÓRIOS
EMPREGADOR
- Questionario de inserção de vagas com tópicos e requisitos.
	- Modalidade da vaga;
	- Sobre a vaga, localidade (minímo e maximo de caracteres);
	- Escolaridade;
	- Responsabilidade e atribuições;
	- Requisitos e qualificações;
	- Redirecionamento para candidatar-se;
	- Beneficios.

EMPREGADO
- Perfil 
	- Foto;
	- Sobre;
	- Stacks e habilidades;
	- Escolaridade/formações;
	- Experiências;
	- Links de github e portfólio;
	- Curriculo.
    - 
# Conexão com SupaBase:

crie um arquivo .env para criar a variavel de ambiente com:
DB_UR=Url do supaBase
DB_USERNAME=Usuario do supaBase
DB_PASSWORD=Senha do supaBase

Documentação auxiliar:
https://medium.com/@luizfelper/criando-uma-api-usando-o-supabase-com-java-spring-boot-38512ebdb2b


## Qual status usar em cada operação da sua API?

### Endpoints — Vaga

| Método | Caminho        | Descrição                  | Sucesso        | Erros             |
|--------|----------------|----------------------------|----------------|-------------------|
| GET    | `/vagas`       | Lista todas as vagas       | 200 - OK       | —                 |
| GET    | `/vagas/{id}`  | Busca uma vaga pelo id     | 200 - OK       | 404 - NOT_FOUND   |
| POST   | `/vagas`      | Cadastra uma nova vaga     | 201  - CREATED | 400 - BAD_REQUEST |
| PUT    | `/vagas/{id}` | Atualiza uma vaga existente | 200 - OK       | 404 - NOT_FOUND   |
| DELETE | `/vagas/{id}` | Remove uma vagaa           | 204 - NO_CONTENT| 404 - NOT_FOUND            |
