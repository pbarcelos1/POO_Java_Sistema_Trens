import Excecoes.CapacidadeAtingida;

public class Inicializador {
    public static void iniciarGaragemVagoes(Garagem<Vagao> garagemVagoes){
        for(int i=1;i<501;i++){
            Vagao v = new Vagao( null,i, 500);
            try{garagemVagoes.estaciona(v);}
            catch(CapacidadeAtingida e){
                System.out.println("ultimo vagao estacionado: "+v + e);
                break;
            }
        }
    }
    public static void iniciarGaragemLocomotivas(Garagem<Locomotiva> garagemLoc){
        for(int i=1;i<510;i++){
            Locomotiva l = new Locomotiva(i,null,1000,50);
            try{garagemLoc.estaciona(l);
            }
            catch(CapacidadeAtingida e){
                System.out.println("ultima locomotiva estacionada: "+ l + e);
                break;
            }
        }
    }
}
