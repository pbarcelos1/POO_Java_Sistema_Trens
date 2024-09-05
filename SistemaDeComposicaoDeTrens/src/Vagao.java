public class Vagao extends CarroFerroviario{
    public Vagao(Trem trem, int id, int capacidadeCarga){
        super(trem,id,capacidadeCarga);
    }
    @Override
    public String toString() {
        return "Vagao: " + super.toString();
    }
}