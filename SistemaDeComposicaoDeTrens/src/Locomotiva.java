public class Locomotiva extends CarroFerroviario{
    private int nroMaxVagoes;
    public Locomotiva(int id, Trem trem, int capacidadeCarga, int nroMaxVagoes){
        super(trem, id, capacidadeCarga);
        if(nroMaxVagoes < 0){
            throw new IllegalArgumentException("Nro Maximo de Vagoes invalido!");
        }
        this.nroMaxVagoes = nroMaxVagoes;
    }
    public int getNroMaxVagoes(){
        return this.nroMaxVagoes;
    }
    @Override
    public String toString() {
        return "Locomotiva: " + super.toString() +
            " nroMaxVagoes='" + getNroMaxVagoes() + "'";
    }
}
