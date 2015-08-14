package pt.ei2015.ementasipleiria;

/**
 * Created by alves on 22/07/2015.
 */
public class Refeicao {
    String sopa;
    String carne;
    String peixe;
    String sobremesa;

    public Refeicao() {
    }

    public Refeicao(String sopa, String carne, String peixe, String sobremesa) {
        this.sopa = sopa;
        this.carne = carne;
        this.peixe = peixe;
        this.sobremesa = sobremesa;
    }

    public String getSopa() {

        return sopa;
    }

    public void setSopa(String sopa) {
        this.sopa = sopa;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getPeixe() {
        return peixe;
    }

    public void setPeixe(String peixe) {
        this.peixe = peixe;
    }

    public String getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(String sobremesa) {
        this.sobremesa = sobremesa;
    }
}
