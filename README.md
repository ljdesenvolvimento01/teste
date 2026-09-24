# Teste Prático - Iniflex

Projeto desenvolvido em Java para o teste prático de programação da Iniflex.

## Requisitos

- Java JDK 17 ou superior
- Visual Studio Code
- Extensão Extension Pack for Java (Microsoft)

## Estrutura

```text
teste-pratico-iniflex/
├── .vscode/
│   └── settings.json
├── src/
│   └── br/
│       └── com/
│           └── iniflex/
│               ├── Pessoa.java
│               ├── Funcionario.java
│               └── Principal.java
└── README.md
```

## Como executar no VS Code

1. Instale o JDK 17 ou superior.
2. Instale o Visual Studio Code.
3. Instale a extensão **Extension Pack for Java**, da Microsoft.
4. Extraia este projeto.
5. Abra a pasta `teste-pratico-iniflex` no VS Code.
6. Abra `src/br/com/iniflex/Principal.java`.
7. Clique em **Run** acima do método `main`.

Também é possível executar pelo terminal:

```bash
javac -encoding UTF-8 -d bin src/br/com/iniflex/*.java
java -cp bin br.com.iniflex.Principal
```

## Requisitos implementados

- 3.1 Inserção dos funcionários na ordem da tabela
- 3.2 Remoção de João
- 3.3 Impressão com data `dd/MM/yyyy` e valores no padrão brasileiro
- 3.4 Aumento de 10%
- 3.5 Agrupamento por função usando `Map`
- 3.6 Impressão agrupada por função
- 3.8 Aniversariantes dos meses 10 e 12
- 3.9 Funcionário com maior idade
- 3.10 Ordem alfabética
- 3.11 Total dos salários
- 3.12 Quantidade de salários mínimos

## Observação

O requisito 3.7 não aparece no enunciado fornecido; por isso, a implementação segue diretamente do 3.6 para o 3.8.

## Autor

Leandro Juan de Sousa Silva
