import java.util.ArrayList;
import java.util.Scanner;
import Excecoes.*;

public class PatioCentral{
    private ArrayList<Trem> trens = new ArrayList<>(50);
    public PatioCentral(){
    }
    public void addTrem(Trem trem){
        this.trens.add(trem);
    }
    public Trem getTremPorId(int id){
        for(Trem trem:this.trens){
            if(trem.getId()==id){
                return trem;
            }
        }
        throw new IllegalArgumentException("TREM INEXISTENTE");
    }

    public void criarTrem(int id,Garagem<Locomotiva> garagemLocomotivas, Garagem<Vagao> garagemVagoes) throws IdUtilizada{
        for(Trem trem:this.trens){
            if(trem.getId()==id){
                throw new IdUtilizada("ID JA ESTA SENDO UTILIZADA!");
            }
        }
        this.addTrem(new Trem(id,garagemLocomotivas.getUltimo()));
    }

    public void listarTrens(){
        if(this.trens.isEmpty()){
            System.out.println("NAO HA TRENS DISPONIVEIS");
        }
        for(Trem trem:this.trens){
            System.out.println(trem);
        }
    }

    public void editarTrem(int idTrem,Garagem<Locomotiva> garagemLocomotivas, Garagem<Vagao> garagemVagoes){
        boolean continua;
        do{
            Trem t = getTremPorId(idTrem);
            Menu.imprimeMenuEdicaoTrem(idTrem);
            Scanner scanner = new Scanner(System.in);
            int escolha = scanner.nextInt();
            scanner.nextLine();
            continua = tomarDecisaoDeEdicao(t,garagemLocomotivas,garagemVagoes,escolha);          
        }while(continua);
    }
    public boolean tomarDecisaoDeEdicao(Trem trem,Garagem<Locomotiva> garagemLocomotivas, Garagem<Vagao> garagemVagoes,int escolha){
        Scanner scanner = new Scanner(System.in);
        switch (escolha) {
            case 1:
                System.out.println("DIGITE A ID DA LOCOMOTIVA A SER ADICIONADA");
                escolha = scanner.nextInt();
                try {
                    Locomotiva auxiliar = garagemLocomotivas.retiraPorId(escolha); 
                    trem.engata(auxiliar);
                    System.out.println("LOCOMOTIVA: "+ auxiliar.getId()+ " ADICIONADA COM SUCESSO!");
                } catch (GaragemVazia e) {
                    System.out.println("NAO FOI POSSIVEL RETIRAR A LOCOMOTIVA: "+e);
                } catch(IllegalArgumentException e){
                    System.out.println("NAO FOI POSSIVEL RETIRAR A LOCOMOTIVA: "+e);
                } catch (VagoesJaAdicionados e){
                    System.out.println("NAO FOI POSSIVEL ENGATAR A LOCOMOTIVA: "+ e);
                }
                break;
            case 2:
                System.out.println("DIGITE A ID DO VAGAO A SER ADICIONADO");
                escolha = scanner.nextInt();                  
                try {
                    Vagao aux = garagemVagoes.retiraPorId(escolha);
                    trem.engata(aux);
                    System.out.println("VAGAO: "+ aux.getId()+ " ADICIONADO COM SUCESSO!");
                } catch (GaragemVazia e) {
                    System.out.println("NAO FOI POSSIVEL RETIRAR O VAGAO: "+ e);
                } catch (IllegalArgumentException e){
                    System.out.println("NAO FOI POSSIVEL RETIRAR O VAGAO: "+ e);
                } catch (CapacidadeAtingida e){
                    System.out.println("NAO FOI POSSIVEL ENGATAR O VAGAO: "+ e);
                }
                break;
            case 3:
                trem.removerUltimoElemento(this,garagemVagoes,garagemLocomotivas);
                if(trem.getQtdLoc() == 0){
                    System.out.println("TREM: "+ trem.getId()+" FOI DESFEITO.");
                    this.trens.remove(trem);
                    return false;
                }
                break;
            case 4:
                garagemLocomotivas.listarElementos();   
                break;
            case 5:
                garagemVagoes.listarElementos();
                break;          
            case 6:
                return false;
            default:
                break;
        }
        return true;
    }

    public void desfazerTrem(Trem t,Garagem<Locomotiva> gl, Garagem<Vagao> gv){
        while(t.removerUltimoElemento(this, gv, gl)){}
        System.out.println("TREM DESFEITO COM SUCESSO, VAGOES E LOCOMOTIVAS DEVOLVIDOS AS SUAS GARAGENS");
    }
    public void remove(Trem trem){
        this.trens.remove(trem);
    }
}