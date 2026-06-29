

public class Data{

    private int dia, mes, ano;

    Data(int d, int m, int a){
        this.dia = d;
        this.mes = m;
        this.ano = a;
    }

    public boolean equals(Data d2){
        if(this.dia == d2.dia && this.mes == d2.mes && this.ano == d2.ano ){
            return true;
        }
        else{
            return false;
        }
    }

        public int getDia() {
            return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        // preenche com zero à esquerda, se necessário
        return String.format("%02d/%02d/%d", this.dia, this.mes, this.ano);
    }
}