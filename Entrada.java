import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.Scanner;

/**
 * Classe com as rotinas de entrada e saída do projeto
 * @author Caio Sperandio e Elson Ramos
 */
public class Entrada {
    private Scanner input;

    /**
     * Construtor da classe Entrada
     * Se houver um arquivo input.txt na pasta em que o projeto está sendo executado,
     * define que o Scanner vai ler deste arquivo e não do teclado.
     * Se o arquivo não existir, define que o Scanner vai ler da entrada padrão (teclado)
     * NÃO ALTERE O CODIGO DESTE CONSTRUTOR!!!!
     */
    public Entrada() {
        try {
            // Se houver um arquivo input.txt, o Scanner vai ler dele.
            this.input = new Scanner(new FileInputStream("input2.txt")).useLocale(Locale.US);
        } catch (FileNotFoundException e) {
            // Caso contrário, vai ler do teclado.
            // Locale.US para que o Java sempre leia double com "pontos" ao invés de "vírgulas"
            this.input = new Scanner(System.in).useLocale(Locale.US);
        }
    }
    
    public void fechar() {
        System.exit(0);
    }    


    /**
     * Faz a leitura de uma linha inteira
     * Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
     * @param msg: Mensagem que será exibida ao usuário
     * @return Uma String contendo a linha que foi lida
     */
    public String lerLinha(String msg) {
        while (true) {
            try {
            // Imprime uma mensagem ao usuário, lê uma e retorna esta linha
            System.out.print(msg);
            String linha = this.input.nextLine();


            // Ignora linhas começando com #, que vão indicar comentários no arquivo de entrada:
            // Agora também verifica se linha não está vazio
            while (!linha.isEmpty() && linha.charAt(0) == '#') linha = this.input.nextLine();
            
            if (linha.trim().isEmpty()) throw new EmptyException();
            
            return linha;
            } catch (EmptyException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Faz a leitura de um número inteiro
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para int
     */
    public int lerInteiro(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um inteiro e retorna este inteiro
        while (true) {
            try {  // Repete até que o usuário digite um número
                String linha = this.lerLinha(msg).trim();
                return Integer.parseInt(linha);
            } catch (Exception e) {
                System.err.println("erro: digite apenas números!");
            }
        }
    }

    /**
     * Faz a leitura de um double
     * @param msg: Mensagem que será exibida ao usuário
     * @return O número digitado pelo usuário convertido para double
     */
    private double lerDouble(String msg) {
        // Imprime uma mensagem ao usuário, lê uma linha contendo um double e retorna este double
        while (true) {
            try {  // Repete até que o usuário digite um número
                String linha = this.lerLinha(msg).trim();
                return Double.parseDouble(linha);
            } catch (Exception e) {
                System.err.println("erro: digite apenas números!");
            }
        }

    }

    /**
     * Faz a leitura de sim/não para adicionais
     * @param msg: Mensagem que será exibida ao usuário
     * @return A opção digitada pelo usuário convertido para true ou false
     */
    private boolean lerAdicional(String msg) {
        String linha = this.lerLinha(msg).trim(); // le a linha original

        // enquanto for diferente de s ou n, repita
        while (!linha.equalsIgnoreCase("s") && !linha.equalsIgnoreCase("n")) {
            System.out.println("erro: responda apenas com 's' ou 'n'");
            linha = this.lerLinha(msg).trim();
        }
        
        // return linha.equalsIgnoreCase("s");
        // não funcionou assim (por que?)

        if (linha.equalsIgnoreCase("s")) {
            return true;
        } else {
            return false;
        }

    }

    private String lerAlfanumerico(String msg) {
        while (true) {
            String linha = this.lerLinha(msg); // leitura da linha original

            // verifica se possui apenas:
            // 1. letras de a até z
            // 2. letras de A até Z
            // 3. números de 0 até 9
            // 4. espaços
            if (linha.matches("^[a-zA-Z0-9 ]+$")) {
                return linha.trim();
            }

            System.out.println("erro: digite apenas letras, números e espaços");
        }
    }

    private String lerApenasLetras(String msg) {
        while (true) {
            String linha = this.lerLinha(msg); // leitura da linha original

            // verifica se possui apenas:
            // 1. letras de a até z
            // 2. letras de A até Z
            // 3. números de 0 até 9
            // 4. espaços
            if (linha.matches("^[a-zA-Z0-9 ]+$")) {
                return linha.trim();
            }

            System.out.println("erro: digite apenas letras, números e espaços");
        }
    }

    /**
     * Imprime o menu principal, lê a opção escolhida pelo usuário e retorna a opção selecionada.
     * @return Inteiro contendo a opção escolhida pelo usuário
     */
    public int menu() {
        String msg = "*********************\n" +
                "Escolha uma opção:\n" +
                "1) Cadastros\n" +
                "2) Reservas\n" +
                "0) Sair\n";

        int op = this.lerInteiro(msg);

        while (op < 0 || op > 2) {
            System.out.println("Opcao invalida. Tente novamente: ");
            op = this.lerInteiro(msg);
        }

        return op;
    }

    /**
     * Imprime o menu de cadastros, lê a opção escolhida pelo usuário e chama o metodo apropriado.
     */
    public void menuCadastro(Sistema s) {
        String msg = "*********************\n" +
                "Escolha uma opcao:\n" +
                "1) Ver clientes\n" +
                "2) Ver salas\n" +
                "3) Ver estacoes de trabalho\n" +
                "4) Cadastrar cliente\n" +
                "5) Cadastrar sala\n" +
                "6) Cadastrar estacao de trabalho\n" +
                "0) Voltar\n";

        int op = this.lerInteiro(msg);

        while (op < 0 || op > 6) {
            System.out.println("Opcao invalida. Tente novamente: ");
            op = this.lerInteiro(msg);
        }

        switch (op) {
            case 1:
                this.listarClientes(s);
                break;
            case 2:
                this.listarSalas(s);
                break;
            case 3:
                this.listarEstacoes(s);
                break;
            case 4:
                this.cadastrarCliente(s);
                break;
            case 5:
                this.cadastrarSala(s);
                break;
            case 6:
                this.cadastrarEstacao(s);
                break;
        }
    }

    public void menuReservas(Sistema s) {
        String msg = "*********************\n" +
                "Escolha uma opcao:\n" +
                "1) Ver reservas\n" +
                "2) Ver reservas por data\n" +
                "3) Ver reservas por cliente\n" +
                "4) Fazer reserva (dia inteiro)\n" +
                "5) Fazer reserva (turno inteiro)\n" +
                "6) Fazer reserva (horario específico)\n" +
                "0) Voltar\n";

        int op = this.lerInteiro(msg);
        
        while (op < 0 || op > 6) {
            System.out.println("Opcao invalida. Tente novamente: ");
            op = this.lerInteiro(msg);
        }

        switch (op) {
            case 1:
                this.listarReservas(s);
                break;
            case 2:
                this.listarReservasData(s);
                break;
            case 3:
                this.listarReservasCliente(s);
                break;
            case 4:
                this.reservarData(s);;
                break;
            case 5:
                this.reservarTurno(s);
                break;
            case 6:
                this.reservarHorario(s);
                break;
        }
    }



    /**
     * Faz a leitura dos dados basicos do sistema, crie um objeto Sistema com os dados lidos
     * e retorna este objeto.
     * @return Novo objeto Sistema com os dados lidos
     */
    public Sistema criarSistema() {
        System.out.println("Iniciando o sistema");
        System.out.println("Carregando dados...");
        try {
            FileReader f = new FileReader("clientes.txt");
            BufferedReader buff = new BufferedReader(f);
            String dados = buff.readLine();
            

            buff.close();
        } catch (IOException e) {
            System.out.println("erro: nao foi possivel salvar no arquivo");
        }
    
        double valorHora = this.lerDouble("Digite o valor por hora para usar um espaco: R$ ");
        double taxaLimpeza = this.lerDouble("Digite a taxa de limpeza: R$ ");
        double precoProjetor = this.lerDouble("Digite o valor extra para usar o projetor: R$ ");
        double precoMonitor = this.lerDouble("Digite o valor para usar o monitor extra: R$ ");

        return new Sistema(valorHora, taxaLimpeza, precoProjetor, precoMonitor);
    }

    /**
     * Lista todos os clientes cadastrados
     * @param s: Objeto da classe Sistema
     */
    public void listarClientes(Sistema s) {
        System.out.println("*********************************");
        ArrayList<Cliente> clientes = s.getClientes();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        }
        else {
            System.out.println("Clientes cadastrados:");
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }

    public void cadastrarCliente(Sistema s) {
        this.listarClientes(s);

        System.out.println("Cadastrando cliente.");
        String nome = this.lerLinha("Digite o nome do cliente: ");
        String cpf = this.lerLinha("Digite o CPF do cliente: ");
        String email = this.lerLinha("Digite o email do cliente: ");
        String senha = this.lerLinha("Digite a senha do cliente: ");

        if (s.getCliente(cpf) == null) {
            Cliente cli = new Cliente(nome, cpf, email, senha);
            s.cadastrar(cli);
        }
        else {
            System.out.println("CPF ja cadastrado. Cliente nao inserido.");
        }
    }

    public void cadastrarSala(Sistema s) {
        this.listarSalas(s);

        System.out.println("Cadastrando Sala.");
        String descricao = this.lerAlfanumerico("Digite o nome da sala: ");
        boolean projetor = this.lerAdicional("Possui projetor? (s/n): ");

        ArrayList<Sala> salas = s.getSalas();
        boolean existe = false;
        for (Sala sa : salas) {
            if (descricao.equals(sa.descricao)) existe = true;
        }

        if (!existe) {
             System.out.println("A sala foi cadastrada com sucesso.");
            Sala s1 = new Sala(descricao, s.getValorHora(), s.getTaxaLimpeza(), projetor, s.getPrecoProjetor());
            s.cadastrar(s1);
        }
        else {
            System.out.println("Sala ja cadastrada.");
        }
    }


    public void cadastrarEstacao(Sistema s){
        this.listarEstacoes(s);

        System.out.println("Cadastrando Estacao.");
        String descricao = this.lerAlfanumerico("Digite o nome da estação: ");
        boolean monitorExtra = this.lerAdicional("A estacao possui monitor extra: (s/n): ");
        

        ArrayList<Estacao> estacoes = s.getEstacoes();
        boolean existe = false;
        for (Estacao est : estacoes) {
            if (descricao.equals(est.descricao)) existe = true;
        }

        if (!existe) {
            System.out.println("A estacao foi cadastrada com sucesso.");
            Estacao e1 = new Estacao(descricao, s.getValorHora(), s.getTaxaLimpeza(), monitorExtra, s.getPrecoProjetor());
            s.cadastrar(e1);
        }
        else {
            System.out.println("Estacao ja cadastrada.");
        }
    }

    public Cliente lerCliente(Sistema s){
        String nome = lerLinha("Digite o nome do cliente: ");
        String cpf = lerLinha("Digite o CPF do cliente: ");
        String email = lerLinha("Digite o email do cliente: ");
        String senha = lerLinha("Digite a senha do cliente: ");
        
        return new Cliente(nome, cpf, email, senha);
    }
        


    public Data lerData(Sistema s){
        System.out.println("Escolha uma data (dd/mm/aaaa): ");
        int dia = lerInteiro("Dia: ");
        int mes = lerInteiro("Mes: ");
        int ano = lerInteiro("Ano: ");

        return new Data(dia, mes, ano);
    }


    public Horario lerHorario(Sistema s){
        System.out.println("Escolha um horario (hh:mm): ");
        int hora = lerInteiro("Hora: ");
        int minuto = lerInteiro("Minuto: ");

        return new Horario(hora, minuto);
    }

    public String lerTipo(Sistema s){
        String tipo = lerLinha("Deseja reservar uma sala ou estacao de trabalho? (s/e): ");
        return tipo;
    }


    public boolean lerExtra(Sistema s, Espaco e){
        if(e instanceof Sala) {
            String l = lerLinha("Deseja reservar sala com projetor? (s/n): ");
            if(l.equalsIgnoreCase("s")){
                return true;
            }
            return false;
        } else{
            String l = lerLinha("Deseja reservar estacao de trabalho com monitor extra (s/n): ");
            if(l.equalsIgnoreCase("s")){
                return true;
            }
            return false;
        }
    }
    
    public void listarSalas(Sistema s){
        System.out.println("*********************************");
        ArrayList<Sala> salas = s.getSalas();

        if (salas.isEmpty()) {
            System.out.println("Nenhuma sala encontrada.");
        }
        else {
            System.out.println("Salas cadastradas:");
            for (Sala c : salas) {
                System.out.println(c);
            }
        }

    }

    public void listarEstacoes(Sistema s){
        System.out.println("*********************************");
        ArrayList<Estacao> estacoes = s.getEstacoes();

        if (estacoes.isEmpty()) {
            System.out.println("Nenhuma estacao encontrada.");
        }
        else {
            System.out.println("Estacoes cadastradas:");
            for (Estacao c : estacoes) {
                System.out.println(c);
            }
        }
    }


    public void listarReservas(Sistema s){
        System.out.println("*********************************");
        //ArrayList<Reserva> reservas = s.getReservas();
        
        Collections.sort(s.getReservas(), new Comparator<Reserva>(){

            public int compare(Reserva r1, Reserva r2){
                // 1 critério: nome do cliente
                int resultado = r1.getCli().getNome().compareTo(r2.getCli().getNome());
            
                //se nomes iguais
                if(resultado == 0){
                    // 2 criterio: preco maior primeiro
                    if(r1.getEsp().preco(r1.getInicio(), r1.getFim()) > 
                        r2.getEsp().preco(r2.getInicio(), r2.getFim())){
                        return -1;
                    }
                    else if(r1.getEsp().preco(r1.getInicio(), r1.getFim()) < 
                        r2.getEsp().preco(r2.getInicio(), r2.getFim())){
                            return 1;
                        }

                        //3 critério: data mais recente
                        resultado = r2.getD().compara(r1.getD());
                }
                
                return resultado;
            }
        });

        for(Reserva r : s.getReservas()){
            System.out.println(r);
            System.out.println();
        }
        //if (reservas.isEmpty()) {
          //  System.out.println("Nenhuma reserva cadastrada.");}
        //else {
           // System.out.println("Clientes cadastrados:");
            //for (Reserva r : reservas) {
              //  System.out.println(r);
           // }
       // }
    }

    public void listarReservasData(Sistema s){
        Data d = this.lerData(s);

        ArrayList<Reserva> reservas = s.getReservas(d);

        System.out.println("Reservas cadastradas nesta data: ");

        if(reservas.isEmpty()){
            System.out.println("Nenhuma reserva cadastrada nesta data.");
        }

        else{
            for(Reserva r : reservas){
                System.out.println(r);
            }
        }
    }


    public void listarReservasCliente(Sistema s){
        this.listarClientes(s);
        
        String cpf = this.lerLinha("Digite o CPF do cliente: ");
        Cliente c = s.getCliente(cpf);

        if(c == null){
            System.out.println("Cliente nao encontrado.");
            return;
        }
        
        ArrayList<Reserva> reservas = s.getReservas(c);
        System.out.println("Reservas cadastradas para este cliente: ");
        if(reservas.isEmpty()){
            System.out.println("Nenhuma reserva cadastrada.");
        }
        else{
            for(Reserva r: reservas){
                System.out.println(r);
            }
        }
    }


    
    public void reservarData(Sistema s) {
        System.out.println("Reservando espaco para o dia inteiro.");
        String tipo = this.lerTipo(s);

        boolean extra;
        if(tipo.equalsIgnoreCase("s")){
            String resp = lerLinha("Deseja reservar sala com projetor? (s/n): ");

            extra = resp.equalsIgnoreCase("s");
        }
        else{
            String resp = this.lerLinha("Deseja reservar estacao com monitor extra? (s/n): ");

            extra = resp.equalsIgnoreCase("s");
        }

        Data d = this.lerData(s);

        this.listarClientes(s);
        String cpf = this.lerLinha("Digite o CPF do cliente: ");

        Cliente c = s.getCliente(cpf);

        if(c == null){
            System.out.println("Cliente nao encontrado.");
            return;
        }

        boolean reserva = s.reservar(tipo, d, c, extra);

        if(reserva){
            System.out.println("Reserva realizada com sucesso.");
        }
        else{
            System.out.println("Nao foi possivel realizar a reserva.");
        }

    }

    public void reservarTurno(Sistema s){
        System.out.println("Reservando espaco para um turno.");
        String tipo = this.lerTipo(s);

        boolean extra;
        if(tipo.equalsIgnoreCase("s")){
            String resp = lerLinha("Deseja reservar sala com projetor? (s/n): ");

            extra = resp.equalsIgnoreCase("s");
        }
        else{
            String resp = this.lerLinha("Deseja reservar estacao com monitor extra? (s/n): ");

            extra = resp.equalsIgnoreCase("s");
        }

        Data d = this.lerData(s);

        String turno = this.lerLinha("Digite o turno (m/v/n): ");

        this.listarClientes(s);
        String cpf = this.lerLinha("Digite o CPF do cliente: ");

        Cliente c = s.getCliente(cpf);

        if(c == null){
            System.out.println("Cliente nao encontrado.");
            return;
        }

        boolean reserva = s.reservar(tipo, d, turno, c, extra);

        if(reserva){
            System.out.println("Reserva realizada com sucesso.");
        }
        else{
            System.out.println("Nao foi possivel realizar a reserva.");
        }
    }





    public void reservarHorario(Sistema s){
        System.out.println("Reservando espaco para um horario.");
        String tipo = this.lerTipo(s);

        boolean extra;
        if(tipo.equalsIgnoreCase("s")){
            String resp = lerLinha("Deseja reservar sala com projetor? (s/n): ");

            extra = resp.equalsIgnoreCase("s");
        }
        else{
            String resp = this.lerLinha("Deseja reservar estacao com monitor extra? (s/n): ");

            extra = resp.equalsIgnoreCase("s");
        }
        
        Data d = this.lerData(s);

        System.out.println("Digite o horario inicial:");
        Horario inicio = this.lerHorario(s);

        System.out.println("Digite o horario final:");
        Horario fim = this.lerHorario(s);

        this.listarClientes(s);
        String cpf = this.lerLinha("Digite o CPF do cliente: ");

        Cliente c = s.getCliente(cpf);

        if(c == null){
            System.out.println("Cliente nao encontrado.");
            return;
        }

        boolean reserva = s.reservar(tipo, d, inicio, fim, c, extra);

        if(reserva){
            System.out.println("Reserva realizada com sucesso.");
        }
        else{
            System.out.println("Nao foi possivel realizar a reserva.");
        }
    }





}
