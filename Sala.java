public class Sala extends Espaco{
    private boolean proj;
    private double precoProj;

    Sala(String descricao, double valorHora, double taxaLimpeza, boolean projetor, double precoProjetor){
        super(descricao, valorHora, taxaLimpeza);
        this.proj = projetor;
        this.precoProj = precoProjetor;
    }


    @Override
    public String toString(){
        if(possuiAdicionalExtra()){
           return super.toString() + " (Sala com Projetor)";
        } else {
            return super.toString() + " (Sala sem Projetor)";   
        }
    }

    @Override
    public double preco(Horario inicio, Horario fim) {
        // calcula a diferença bruta usando apenas a hora
        int qtH = fim.getHora() - inicio.getHora();
        
        // garante o mínimo de 1 hora de cobrança
        if (qtH < 1) {
            qtH = 1;
        }
        
        // valor da hora multiplicado por 4
        double valorFinal = ((this.valorHora * 4) * qtH) + this.taxaLimpeza;
        
        // soma o projetor se a sala possuir
        if (this.possuiAdicionalExtra()) {
            valorFinal += this.precoProj;
        }
        
        return valorFinal;
    }

    @Override
    public boolean possuiAdicionalExtra() {
        return this.proj;
    }


}