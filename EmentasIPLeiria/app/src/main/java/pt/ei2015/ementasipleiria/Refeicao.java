/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ei2015.ementasipleiria;

/**
 *
 * @author alves
 */
public class Refeicao {
    String sopa=null;
    String carne=null;
    String peixe=null;
    String sobremesa=null;
    String vegetariano=null;

    public Refeicao(String sopa, String carne, String peixe, String sobremesa, String vegetariano) {
        this.sopa = sopa;
        this.carne = carne;
        this.peixe = peixe;
        this.sobremesa = sobremesa;
        this.vegetariano=vegetariano;
    }

    public Refeicao() {
    }

    public String getSopa() {
        if(sopa !=null)
            return sopa.trim();
        return "Não Definido";
    }

    public void setSopa(String sopa) {
        this.sopa = sopa.replace("Sopa:", "").replace("Sopa :", "");
    }

    public String getCarne() {
        if(carne!=null)
            return carne.trim();
        return "Não Definido";
    }

    public void setCarne(String carne) {
        this.carne = carne.replace("Carne:", "").replace("Carne :", "");
    }

    public String getPeixe() {
        if(peixe!=null)
            return peixe.trim();
        return "Não Definido";
    }

    public void setPeixe(String peixe) {
        this.peixe = peixe.replace("Peixe:", "").replace("Peixe :", "");
    }

    public String getSobremesa() {
        if(sobremesa != null)
            return sobremesa.trim();
        return "Não Definido";
    }

    public void setSobremesa(String sobremesa) {
        this.sobremesa = sobremesa.replace("Sobremesa:", "").replace("Sobremesa :", "");
    }
    
        public String getVegetariano() {
            if(vegetariano != null)
            return vegetariano.trim();
        return "Não Definido";
    }

    public void setVegetariano(String vegetariano) {
        this.vegetariano = vegetariano.replace("Vegetariano:", "").replace("Vegetariano :", "");
    }   
}
