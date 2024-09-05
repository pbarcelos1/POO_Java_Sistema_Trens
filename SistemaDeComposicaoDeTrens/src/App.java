import java.util.Scanner;
import Excecoes.*;

public class App {
    public static void main(String[] args) throws Exception {
        // CRIANDO GARAGENS E PATIO
        Garagem<Locomotiva> garagemLoc = new Garagem<Locomotiva>(1000);
        Garagem<Vagao> garagemVagoes = new Garagem<Vagao>(1000);
        Inicializador.iniciarGaragemVagoes(garagemVagoes);
        Inicializador.iniciarGaragemLocomotivas(garagemLoc);
        PatioCentral patio = new PatioCentral();
        // CRIANDO VAGOES E INICIANDO GARAGENS
        // while(Menu.exibirMenuTrens(patio,garagemLoc,garagemVag));
        exibeMenu(patio, garagemVagoes, garagemLoc);
        System.out.println("FIM DO PROGRAMA");
    }
    public static void exibeMenu(PatioCentral patio, Garagem<Vagao> garagemVagoes, Garagem<Locomotiva> garagemLoc){
        boolean continua = true;
            do{
                Menu.imprimeMenuPrincipal();
                Scanner in = new Scanner(System.in);
                int entrada = in.nextInt();
                in.nextLine();
                int id;
                switch (entrada){
                    case 1:
                        try {
                            System.out.println("DIGITE O ID DO TREM(PRECISA SER UMA ID UNICA)");
                            id = in.nextInt();
                            in.nextLine();
                            patio.criarTrem(id,garagemLoc,garagemVagoes);
                            System.out.println("TREM: "+id+" CRIADO COM SUCESSO");
                        } catch (IdUtilizada e) {
                            System.out.println("NAO FOI POSSIVEL CRIAR O TREM: "+e);
                        }
                        break;
                    case 2:         
                        System.out.println("DIGITE O ID DO TREM QUE DESEJA EDITAR");
                        id = in.nextInt();
                        in.nextLine();
                        try{
                            patio.editarTrem(id, garagemLoc, garagemVagoes);
                        }catch (IllegalArgumentException e) {
                            System.out.println("NAO FOI POSSIVEL EDITAR O TREM POIS: "+e);
                        }
                        break;
                    case 3:
                        patio.listarTrens();
                        break;
                    case 4:
                        System.out.println("DIGITE A ID DO TREM QUE VOCE DESEJA DESCONSTRUIR");
                        id = in.nextInt();
                        in.nextLine();
                        try{
                            Trem auxi = patio.getTremPorId(id);
                            patio.desfazerTrem(auxi, garagemLoc, garagemVagoes);
                        }catch (IllegalArgumentException e) {
                            System.out.println(e);
                        }
                        break;
                    case 5:
                        continua = false;
                    default:
                        System.out.println("ENTRADA INVALIDA");
                        break;
                }
            }while(continua);
        }
    }
