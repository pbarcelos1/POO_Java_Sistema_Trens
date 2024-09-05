import java.util.ArrayList;
import Excecoes.*;

public class Trem{
    private int id;
    private ArrayList<Vagao> vagoes = new ArrayList<>();
    private ArrayList<Locomotiva> locomotivas = new ArrayList<>();
    private int capTotalVagoes = 0;
    private int capTotalCarga = 0;
    private int cargaCarregada = 0;

    public Trem(int id, Locomotiva locomotiva){
        this.id = id;
        try {
            this.engata(locomotiva);
        } catch (IllegalArgumentException e){
            System.out.println(e);
        } catch(VagoesJaAdicionados e){
            System.out.println("impossivel"+e);
        }
    }
    public int getId() {
        return this.id;
    }
    public ArrayList<Vagao> getVagoes() {
        return this.vagoes;
    }
    public void setVagoes(ArrayList<Vagao> vagoes) {
        this.vagoes = vagoes;
    }

    public ArrayList<Locomotiva> getLocomotivas() {
        return this.locomotivas;
    }
    public void setLocomotivas(ArrayList<Locomotiva> locomotivas) {
        this.locomotivas = locomotivas;
    }
    public int getCapTotalVagoes() {
        return this.capTotalVagoes;
    }
    public void setCapTotalVagoes(int capTotalVagoes) {
        this.capTotalVagoes = capTotalVagoes;
    }
    public int getCapTotalCarga() {
        return this.capTotalCarga;
    }
    public void setCapTotalCarga(int capTotalCarga) {
        this.capTotalCarga = capTotalCarga;
    }
    
    public int getQtdLoc(){
        return this.locomotivas.size();
    }
    public int getQtdVag(){
        return this.vagoes.size();
    }

    public void aumentarCapCarga(Locomotiva l){
        capTotalCarga += l.getCapacidadeCarga();
    }

    public void engata(Vagao vagao)throws CapacidadeAtingida{
        if(this.vagoes.size()==this.capTotalVagoes){
            throw new CapacidadeAtingida("CAPACIDADE DE VAGOES ATINGIDA");
        }
        if(this.cargaCarregada + vagao.getCapacidadeCarga() > this.capTotalCarga){
            throw new CapacidadeAtingida("CAPACIDADE TOTAL DE CARGA EXCEDIDA");
            
        }
        vagoes.add(vagao);
        this.cargaCarregada += vagao.getCapacidadeCarga();
        vagao.setTrem(this);
    }
    public void engata(Locomotiva locomotiva) throws VagoesJaAdicionados{
        if(this.vagoes.size()>0){
            throw new VagoesJaAdicionados("VAGOES JA FORAM ADICIONADOS");
        }
        if(locomotiva == null){
            throw new IllegalArgumentException("LOCOMOTIVA INEXISTENTE");
        }
        this.locomotivas.add(locomotiva);
        this.capTotalVagoes += locomotiva.getNroMaxVagoes();
        this.capTotalCarga += locomotiva.getCapacidadeCarga();
        this.atualizaCapVagoes();
        this.atualizaCapCarga();
        locomotiva.setTrem(this);
    }


    public boolean desengataVagao(Garagem<Vagao> garagem, Vagao vagao){
        for(Vagao v : this.vagoes){
            if(v == vagao){
                try{
                    garagem.estaciona(vagao);
                    this.vagoes.remove(vagao);
                    this.cargaCarregada -= vagao.getCapacidadeCarga();
                    return true;
                }catch(CapacidadeAtingida e){
                    System.out.println("NAO FOI POSSIVEL DESENGATAR O VAGAO " + e);
                }
            }
        }
        return false;
    }

    public boolean desengataLoc(Garagem<Locomotiva> garagemLoc, Locomotiva loc){
        for(Locomotiva l : this.locomotivas){
            if(l==loc){
                try{
                    garagemLoc.estaciona(loc);
                    this.locomotivas.remove(loc);
                    if(this.locomotivas.size()>0){    
                        this.atualizaCapVagoes();
                        this.atualizaCapCarga();
                    }
                    return true;
                }catch(CapacidadeAtingida e){
                    System.out.println("NAO FOI POSSIVEL DESENGATAR A LOCOMOTIVA " + e);
                }
            }
        }
        return false;
    }
    private void atualizaCapVagoes(){
        int capVagoes = 0;
        if(this.locomotivas.size()>1){
            for(Locomotiva l:this.locomotivas){
                capVagoes += l.getNroMaxVagoes();
            }
            capVagoes -= capVagoes * (10*(this.locomotivas.size()-1))/100;
            if(capVagoes<0){
                this.capTotalVagoes = 0;
            }
            else{
                this.capTotalVagoes = capVagoes;
            }
        }
        else{
            this.capTotalVagoes=this.locomotivas.get(0).getNroMaxVagoes();
        }
    }
    private void atualizaCapCarga(){
        int capCarga = 0;
        if(this.locomotivas.size()>1){
            for(Locomotiva l:this.locomotivas){
                capCarga += l.getCapacidadeCarga();
            }
            capCarga -= capCarga * (10*(locomotivas.size()-1))/100;
            if(capCarga<0){
                this.capTotalCarga = 0;
            }
            else{
                this.capTotalCarga = capCarga;
            }
        }
        else{
            this.capTotalCarga=this.locomotivas.get(0).getCapacidadeCarga();
        }
        
    }

    public boolean removerUltimoElemento(PatioCentral patio,Garagem<Vagao> garagemVag, Garagem<Locomotiva> garagemLoc){
        if(this.vagoes.size()>0){
            desengataVagao(garagemVag, this.vagoes.get(this.vagoes.size()-1));
        }
        else if(this.locomotivas.size()>=1){    
            desengataLoc(garagemLoc, this.locomotivas.get(this.locomotivas.size()-1));
            if(this.locomotivas.size()==0){
                patio.remove(this);
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return 
            " id='" + this.id + "'" +
            ", vagoes='" + this.vagoes.size() + "'" +
            ", locomotivas='" + this.locomotivas.size() + "'" +
            ", capacidade total de vagoes='" + this.capTotalVagoes + "'" +
            ", capacidade total de carga='" + this.capTotalCarga + "'" +
            ", carga carregada='" + this.cargaCarregada + "'" ;
    }

}