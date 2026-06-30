
public class Estacao extends Espaco{
    private boolean monitorExtra;
    private double precoMonitor;


    Estacao(String d, double vH, double tL, boolean mE, double pM){
        super(d, vH, tL);
        this.monitorExtra = mE;
        this.precoMonitor = pM;
    }

    public double preco(Horario inicio, Horario fim){
        double valor = super.preco(inicio, fim);
        if(monitorExtra){
            valor += precoMonitor;
        }
        return valor;
    }


    public boolean possuiAdicionalExtra(){
        return this.monitorExtra;
    }

    @Override
    public String toString(){
        if(possuiAdicionalExtra()){
           return super.toString() + " (Estacao de trabalho com Monitor Extra)";
        } else {
            return super.toString() + " (Estacao de Trabalho sem Monitor Extra)";   
        }
    }
}