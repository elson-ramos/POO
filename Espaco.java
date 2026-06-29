import java.util.ArrayList;
public abstract class Espaco{

    protected String descricao;
    protected double valorHora;
    protected double taxaLimpeza;
    protected ArrayList<Reserva> reservas;


    Espaco(String d, double vH, double tL){
        this.descricao = d;
        this.valorHora = vH;
        this.taxaLimpeza = tL;
        this.reservas = new ArrayList<>();

    }



    public boolean disponivel(Data d, Horario inicio, Horario fim, boolean extra){
        if(extra && !this.possuiAdicionalExtra()){
            return false;
        }
        for(Reserva r : this.reservas){
            if(r.getD().equals(d)){
                // verifica se o fim de uma reserva não bate com o tempo de outra reserva
                if(inicio.compara(r.getFim()) < 0 && fim.compara(r.getInicio()) > 0){
                    return false;
                }
            }
        }
        return true;
    }


    public void adicionarReserva(Reserva r){
        reservas.add(r);
    }


    //calculo do preço
    public double preco(Horario inicio, Horario fim){
        int qtH;
        qtH = fim.getHora() - inicio.getHora();
        if(qtH < 1){
            return valorHora+taxaLimpeza;
        }
        else{
            if(fim.getMin() > 0){
                qtH = qtH+1;
                return (valorHora*qtH)+taxaLimpeza;
            }
            return (valorHora*qtH)+taxaLimpeza;
        }
    }


    public abstract boolean possuiAdicionalExtra();

    @Override
    public String toString() {
        return descricao;
    }

}