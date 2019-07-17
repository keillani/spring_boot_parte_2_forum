package br.com.alura.forum.config.validacao;

public class ErroDeFormularioDto {

    private String campo;
    private String erro;

    public ErroDeFormularioDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    //só receberá os parametros do construtor por este motivo gerar somente os getters
    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
