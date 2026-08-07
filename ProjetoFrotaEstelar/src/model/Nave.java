package model;

public record Nave(String registro, String modelo, int autonomiaAnosLuz, StatusNave status) {
    public String obterStatusSimplificado(){
        switch (status){
            case OPERACIONAL -> {
                return "Pronta para salto";
            }
            case EM_REPARO -> {
                return "Doca seca";
            }
            case DESATIVADA -> {
                return "Nave indisponivel";
            }
            case EM_MISSAO -> {
                return "Nave em uso";
            }
            case TESTE_DE_VOO -> {
                return "Nave sendo testada";
            }default -> {
                return null;
            }
        }
    }
}
