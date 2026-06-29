public class Reserva{
    private Data d;
    private Horario inicio;
    private Horario fim;
    private Espaco esp;
    private Cliente cli;

     public Reserva(Data d, Horario inicio, Horario fim, Espaco esp, Cliente cli) {
        this.d = d;
        this.inicio = inicio;
        this.fim = fim;
        this.esp = esp;
        this.cli = cli;
    }


    public double preco(){
        return esp.preco(this.inicio, this.fim);
    }

    protected Data getD() {
        return d;
    }

    protected void setD(Data d) {
        this.d = d;
    }

    protected Horario getInicio() {
        return inicio;
    }

    protected void setInicio(Horario inicio) {
        this.inicio = inicio;
    }

    protected Horario getFim() {
        return fim;
    }

    protected void setFim(Horario fim) {
        this.fim = fim;
    }

    protected Espaco getEsp() {
        return esp;
    }

    protected void setEsp(Espaco esp) {
        this.esp = esp;
    }

    protected Cliente getCli() {
        return cli;
    }

    protected void setCli(Cliente cli) {
        this.cli = cli;
    }

    @Override
    public String toString() {
        //Data da reserva: x (y - z)
        return "* Local: " + this.esp.toString() + 
            "\n* Data: " + this.d.toString() +
            "\n* Horario: " + this.inicio.toString() +
            "\n* Cliente: " + this.cli.toString() +
            "\n* Valor: R$ " + String.format("%.2f", preco()).replace(".", ","); // troca . por ,

    }
}
