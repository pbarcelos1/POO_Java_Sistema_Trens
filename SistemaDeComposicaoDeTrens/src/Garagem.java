import java.util.ArrayList;
import java.util.List;

import Excecoes.CapacidadeAtingida;
import Excecoes.GaragemVazia;

public class Garagem<E extends CarroFerroviario> {
    private List<E> estacionamento;
    private int capacidade;
    private int count;
    public Garagem(int capacidade){
        if(capacidade<0){
            throw new IllegalArgumentException("CAPACIDADE DA GARAGEM NAO PODE SER NEGATIVA!");
        }
        this.estacionamento = new ArrayList<E>();
        this.capacidade = capacidade;
        this.count = 0;
    }
    public void estaciona(E object)throws CapacidadeAtingida{
        if(this.estaLotado()){
            System.out.println(""+this.size());
            throw new CapacidadeAtingida("GARAGEM ESTA LOTADA");
        }
        this.estacionamento.add(object);
        object.setTrem(null);
        count++;
        System.out.println(object+" ESTACIONADO COM SUCESSO! ");
    }
    public E retiraPorId(int id) throws GaragemVazia{
        if(this.estaVazio()){
            throw new GaragemVazia("A GARAGEM ESTA VAZIA, NAO HA O QUE RETIRAR");
        }
        for(int i = 0;i<this.estacionamento.size();i++){
            if(this.estacionamento.get(i).getId()==id){
                E object = this.estacionamento.get(i);
                this.estacionamento.remove(i);
                count--;
                return object;
            }
        }
        throw new IllegalArgumentException("OBJETO NAO EXISTE NA GARAGEM");
    }
    public E getUltimo(){
        if(this.estaVazio()){
            throw new Error("A GARAGEM ESTA VAZIA, NAO HA O QUE RETIRAR");
        }
        count--;
        return this.estacionamento.remove(count);
    }
    public int size(){
        return this.count;
    }
    public boolean estaVazio(){
        return this.size() == 0;
    }
    public boolean estaLotado(){
        return this.size() == this.capacidade;
    }
    public void listarElementos(){
        for(int i=0;i<this.size();i++){
            System.out.println(this.estacionamento.get(i));
        }
    }
}
