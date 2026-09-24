package br.com.iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {

    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final DecimalFormat FORMATO_NUMERO;

    static {
        DecimalFormatSymbols simbolos =
                new DecimalFormatSymbols(Locale.forLanguageTag("pt-BR"));
        simbolos.setGroupingSeparator('.');
        simbolos.setDecimalSeparator(',');
        FORMATO_NUMERO = new DecimalFormat("#,##0.00", simbolos);
    }

    public static void main(String[] args) {

        // 3.1 - Inserir todos os funcionários na mesma ordem da tabela.
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario(
                "Maria", LocalDate.of(2000, 10, 18),
                new BigDecimal("2009.44"), "Operador"));

        funcionarios.add(new Funcionario(
                "João", LocalDate.of(1990, 5, 12),
                new BigDecimal("2284.38"), "Operador"));

        funcionarios.add(new Funcionario(
                "Caio", LocalDate.of(1961, 5, 2),
                new BigDecimal("9836.14"), "Coordenador"));

        funcionarios.add(new Funcionario(
                "Miguel", LocalDate.of(1988, 10, 14),
                new BigDecimal("19119.88"), "Diretor"));

        funcionarios.add(new Funcionario(
                "Alice", LocalDate.of(1995, 1, 5),
                new BigDecimal("2234.68"), "Recepcionista"));

        funcionarios.add(new Funcionario(
                "Heitor", LocalDate.of(1999, 11, 19),
                new BigDecimal("1582.72"), "Operador"));

        funcionarios.add(new Funcionario(
                "Arthur", LocalDate.of(1993, 3, 31),
                new BigDecimal("4071.84"), "Contador"));

        funcionarios.add(new Funcionario(
                "Laura", LocalDate.of(1994, 7, 8),
                new BigDecimal("3017.45"), "Gerente"));

        funcionarios.add(new Funcionario(
                "Heloísa", LocalDate.of(2002, 5, 24),
                new BigDecimal("1606.85"), "Eletricista"));

        funcionarios.add(new Funcionario(
                "Helena", LocalDate.of(1996, 9, 2),
                new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário João.
        funcionarios.removeIf(
                funcionario -> funcionario.getNome().equalsIgnoreCase("João"));

        // 3.3 - Imprimir todos os funcionários.
        System.out.println("============================================================");
        System.out.println("3.3 - FUNCIONÁRIOS");
        System.out.println("============================================================");
        imprimirFuncionarios(funcionarios);

        // 3.4 - Aplicar aumento de 10%.
        funcionarios.forEach(funcionario -> {
            BigDecimal novoSalario = funcionario.getSalario()
                    .multiply(new BigDecimal("1.10"))
                    .setScale(2, RoundingMode.HALF_UP);

            funcionario.setSalario(novoSalario);
        });

        System.out.println();
        System.out.println("============================================================");
        System.out.println("3.4 - FUNCIONÁRIOS APÓS AUMENTO DE 10%");
        System.out.println("============================================================");
        imprimirFuncionarios(funcionarios);

        // 3.5 - Agrupar funcionários por função.
        Map<String, List<Funcionario>> funcionariosPorFuncao =
                funcionarios.stream()
                        .collect(Collectors.groupingBy(
                                Funcionario::getFuncao,
                                LinkedHashMap::new,
                                Collectors.toList()));

        // 3.6 - Imprimir funcionários agrupados por função.
        System.out.println();
        System.out.println("============================================================");
        System.out.println("3.6 - FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO");
        System.out.println("============================================================");

        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(funcionario ->
                    System.out.println("  - " + funcionario.getNome()));
        });

        // 3.8 - Imprimir aniversariantes dos meses 10 e 12.
        System.out.println();
        System.out.println("============================================================");
        System.out.println("3.8 - ANIVERSARIANTES DE OUTUBRO E DEZEMBRO");
        System.out.println("============================================================");

        funcionarios.stream()
                .filter(funcionario -> {
                    int mes = funcionario.getDataNascimento().getMonthValue();
                    return mes == 10 || mes == 12;
                })
                .forEach(funcionario ->
                        System.out.println(
                                funcionario.getNome() + " - "
                                        + funcionario.getDataNascimento()
                                        .format(FORMATO_DATA)));

        // 3.9 - Imprimir funcionário com maior idade.
        System.out.println();
        System.out.println("============================================================");
        System.out.println("3.9 - FUNCIONÁRIO COM MAIOR IDADE");
        System.out.println("============================================================");

        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        if (funcionarioMaisVelho != null) {
            int idade = calcularIdade(
                    funcionarioMaisVelho.getDataNascimento());

            System.out.println("Nome: " + funcionarioMaisVelho.getNome());
            System.out.println("Idade: " + idade + " anos");
        }

        // 3.10 - Imprimir lista por ordem alfabética.
        System.out.println();
        System.out.println("============================================================");
        System.out.println("3.10 - FUNCIONÁRIOS EM ORDEM ALFABÉTICA");
        System.out.println("============================================================");

        funcionarios.stream()
                .sorted(Comparator.comparing(
                        Funcionario::getNome,
                        String.CASE_INSENSITIVE_ORDER))
                .forEach(funcionario ->
                        System.out.println(funcionario.getNome()));

        // 3.11 - Imprimir total dos salários.
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println();
        System.out.println("============================================================");
        System.out.println("3.11 - TOTAL DOS SALÁRIOS");
        System.out.println("============================================================");
        System.out.println("Total: R$ " + FORMATO_NUMERO.format(totalSalarios));

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println();
        System.out.println("============================================================");
        System.out.println("3.12 - SALÁRIOS MÍNIMOS POR FUNCIONÁRIO");
        System.out.println("============================================================");

        funcionarios.forEach(funcionario -> {
            BigDecimal quantidade = funcionario.getSalario()
                    .divide(salarioMinimo, 2, RoundingMode.HALF_UP);

            System.out.println(
                    funcionario.getNome() + " recebe "
                            + FORMATO_NUMERO.format(quantidade)
                            + " salários mínimos.");
        });
    }

    private static void imprimirFuncionarios(
            List<Funcionario> funcionarios) {

        funcionarios.forEach(funcionario -> {
            System.out.println("--------------------------------------------");
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println(
                    "Data de nascimento: "
                            + funcionario.getDataNascimento()
                            .format(FORMATO_DATA));
            System.out.println(
                    "Salário: R$ "
                            + FORMATO_NUMERO.format(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
        });
    }

    private static int calcularIdade(LocalDate dataNascimento) {
        return Period.between(
                dataNascimento,
                LocalDate.now()).getYears();
    }
}
