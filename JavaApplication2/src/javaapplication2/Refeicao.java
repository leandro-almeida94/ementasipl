/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

/**
 *
 * @author alves
 */
public class Refeicao {
    String sopa=null;
    String carne=null;
    String peixe=null;
    String sobremesa=null;

    public Refeicao(String sopa, String carne, String peixe, String sobremesa) {
        this.sopa = sopa;
        this.carne = carne;
        this.peixe = peixe;
        this.sobremesa = sobremesa;
    }

    public Refeicao() {
    }

    public String getSopa() {
        if(sopa !=null)
            return sopa;
        return "Não Definido";
    }

    public void setSopa(String sopa) {
        this.sopa = sopa;
    }

    public String getCarne() {
        if(carne!=null)
            return carne;
        return "Não Definido";
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getPeixe() {
        if(peixe!=null)
            return peixe;
        return "Não Definido";
    }

    public void setPeixe(String peixe) {
        this.peixe = peixe;
    }

    public String getSobremesa() {
        if(sobremesa != null)
            return sobremesa;
        return "Não Definido";
    }

    public void setSobremesa(String sobremesa) {
        this.sobremesa = sobremesa;
    }
    
    
}
