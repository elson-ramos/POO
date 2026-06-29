public class Horario {
    private int hora;
    private int min;

    Horario(int h, int m) {
        this.hora = h;
        this.min = m;
    }

    public int compara(Horario h2) {
        if (this.hora > h2.hora) return 1;
        if (this.hora < h2.hora) return -1;
        if (this.min > h2.min) return 1;
        if (this.min < h2.min) return -1;
        else return 0;
    }


    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }


    @Override
    public String toString() {
        // preenche com zero à esquerda, se necessário
        return String.format("%02d:%02d", this.hora, this.min);
    }
}
