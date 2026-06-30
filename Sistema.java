import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Sistema {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Estacao> estacoes = new ArrayList<>();
    private ArrayList<Sala> salas = new ArrayList<>();
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private double valorH;
    private double taxaL;
    private double precoP;
    private double precoM;

    public Sistema(double valorHora, double taxaLimpeza, double precoProjetor, double precoMonitor) {
        this.valorH = valorHora;
        this.taxaL = taxaLimpeza;
        this.precoP = precoProjetor;
        this.precoM = precoMonitor;



        // salvar informacoes sobre o valor do espaco por hora
        // salvar as informacoes sobre o valor da taxa de limpeza

        // salvar as informacoes sobre o preco do projetor
        // salvar as informacoes sobre o preco do monitor extra
    }

    public <T> void escreveArquivo(String nome_arq, T objeto) {
        // observação: achamos pertinente usar o tipo genérico nesse caso,
        // ainda que não seja uma boa prática. julgamos ser melhor do que
        // replicar esse trecho de código 4 vezes.
        try {
            FileWriter f = new FileWriter (nome_arq, true);
            BufferedWriter buff = new BufferedWriter(f);
            buff.write(objeto.toString());
            buff.write("\n");
            buff.close();
        } catch (IOException e) {
            System.out.println("erro: nao foi possivel salvar no arquivo");
        }
    }


    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public Cliente getCliente(String cpf) {
        for (Cliente c : this.clientes) {
            if (c.getCpf().equals(cpf)) return c;
        }
        return null;
    }

    public void cadastrar(Cliente cli) {
        this.clientes.add(cli);
        this.escreveArquivo("clientes.txt", cli);
    }

    public void cadastrar(Estacao est) {
        this.estacoes.add(est);
        this.escreveArquivo("estacoes.txt", est);
    }

    public void cadastrar(Sala sala) {
        this.salas.add(sala);
        this.escreveArquivo("salas.txt", sala);
    }    


    public double getValorHora(){
    return valorH;
    }

    public double getTaxaLimpeza(){
        return taxaL;
    }

    public double getPrecoProjetor(){
        return precoP;
    }

    public double getPrecoMonitor(){
        return precoM;
    }




    public boolean reservar(String tipo, Data d, Horario inicio, Horario fim, Cliente c, boolean extra){
        //se cliente for null
        if(c == null){
            return false;
        }
        //se for sala
        if(tipo.equalsIgnoreCase("s")){
            for(Sala s : salas){
                if(s.disponivel(d, inicio, fim, extra)){

                    //cria a reserva
                    Reserva r = new Reserva(d, inicio, fim, s, c);

                    //add no espaço
                    s.adicionarReserva(r);

                    //add na lista geral
                    reservas.add(r);

                    return true;
                }
            }
        }

        //se for estação
        else if(tipo.equalsIgnoreCase("e")){
            for(Estacao e : estacoes){
                if(e.disponivel(d, inicio, fim, extra)){
                    
                    //cria reserva
                    Reserva r = new Reserva(d, inicio, fim, e, c);

                    //add no espaço
                    e.adicionarReserva(r);

                    //add na lista geral
                    reservas.add(r);

                    return true;
                }
            }
        }

        //caso não tenha encontrado espaço disponivel
        return false;

    }


    public boolean reservar(String tipo, Data d, Cliente c, boolean extra){
        Horario inicio = new Horario(8, 0);
        Horario fim = new Horario(22, 0);

        return reservar(tipo, d, inicio, fim, c, extra);
    }


    public boolean reservar(String tipo, Data d, String turno, Cliente c, boolean extra){
        Horario inicio = null;
        Horario fim = null;
        
        //caso matutino
        if(turno.equalsIgnoreCase("m")){
            inicio = new Horario(8, 0);
            fim = new Horario(12, 0);
        }

        //caso vespertino
        else if(turno.equalsIgnoreCase("v")){
            inicio = new Horario(13, 0);
           fim = new Horario(17, 0);
        }

        //caso noturno
        else if(turno.equalsIgnoreCase("n")){
            inicio = new Horario(18, 0);
            fim = new Horario(22, 0);
        }

         if(inicio == null || fim == null){
            return false;
        }

        return reservar(tipo, d, inicio, fim, c, extra);
    }



    public ArrayList<Reserva> getReservas(){
        return reservas;
    }

    //retorna as reservas da data
    public ArrayList<Reserva> getReservas(Data d){
        ArrayList<Reserva> reservas_filtradas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getD().equals(d)) {
                reservas_filtradas.add(r);
            }
        }
        return reservas_filtradas;        
    }


    //retorna as reservas do cliente
    public ArrayList<Reserva> getReservas(Cliente c){
        ArrayList<Reserva> reservas_filtradas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getCli().equals(c)) {
                reservas_filtradas.add(r);
            }
        }
        return reservas_filtradas;
    }    

    public ArrayList<Sala> getSalas(){
        return salas;
    }


    public ArrayList<Estacao> getEstacoes(){
        return estacoes;
    }
}
