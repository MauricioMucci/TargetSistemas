package br.com.task;

/**
 * @author mauriciomucci
 */
public enum Estado {
    SP("São Paulo (SP)"),
    RJ("Rio de Janeiro (RJ)"),
    MG("Minas Gerais (MG)"),
    ES("Espirito Santo (ES)"),
    OUTROS("Outros");

    private final String descricao;

    Estado(String descricao) {this.descricao = descricao;}

    public String getDescricao() {
        return descricao;
    }
}
