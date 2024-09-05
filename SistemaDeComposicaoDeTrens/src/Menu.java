public class Menu {
    public static void imprimeMenuPrincipal(){
        System.out.println("DIGITE A OPÇAO DESEJADA\n"+
        "1- CRIAR UM TREM, PASSE O ID DO TREM E A ID DE SUA PRIMEIRA LOCOMOTIVA QUE ESTÁ DISPONIVEL NA GARAGEM\n"+
        "2- EDITAR UM TREM EXISTENTE(PASSE A ID DO TREM QUE DESEJA EDITAR E ESTA NO PATIO)\n"+
        "3- LISTAR TODOS OS TRENS JA CRIADOS(TODOS QUE ESTAO NO PATIO)\n"+
        "4- DESFAZER UM TREM(PASSE A ID DO TREM)\n"+
        "5- ENCERRAR PROGRAMA");
    }
    public static void imprimeMenuEdicaoTrem(int idTrem){
        System.out.println("VOCE ESTA EDITANDO O TREM ID: "+ idTrem +
        "\nDIGITE O QUE DESEJA REALIZAR\n"+
        "1- INSERIR LOCOMOTIVA\n"+ "2- INSERIR VAGAO\n"+ 
        "3- REMOVER ULTIMO ELEMENTO DO TREM\n" + 
        "4- LISTAR LOCOMOTIVAS LIVRES NA GARAGEM\n"+
        "5- LISTAR VAGOES LIVRES NA GARAGEM\n" +
        "6- ENCERRAR EDICAO DO TREM");
    }

}
