public abstract class CarroFerroviario{
    private Trem trem;
    private int id;
    private int capacidadeCarga;

    public CarroFerroviario(Trem trem, int id, int capacidadeCarga){
        if(id < 0 || capacidadeCarga<0){
            throw new IllegalArgumentException();
        }
        this.trem = trem;
        this.id = id;
        this.capacidadeCarga = capacidadeCarga;
    }
    public Trem getTrem() {
        return this.trem;
    }
    public void setTrem(Trem trem) {
        this.trem = trem;
    }
    public int getId() {
        return this.id;
    }
    public int getCapacidadeCarga() {
        return this.capacidadeCarga;
    }
    @Override
    public String toString() {
        return 
            "trem='" + getTrem() + "'" +
            ", id='" + getId() + "'" +
            ", capacidadeCarga='" + getCapacidadeCarga() + "' " ;
    }

}