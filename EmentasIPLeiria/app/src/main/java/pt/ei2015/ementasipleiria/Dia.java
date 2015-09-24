/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ei2015.ementasipleiria;

import java.util.Date;

/**
 *
 * @author alves
 */
public class Dia {
    Date dia;
    Refeicao almoco;
    Refeicao jantar;
    
    String prato;

    public String getPrato() {
        return prato.trim();
    }

    public void setPrato(String prato) {
        this.prato = prato.replace("Sopa:", "").replace("Sopa :", "");
    }

    public Dia(Date dia, Refeicao almoco, Refeicao jantar) {
        this.dia = dia;
        this.almoco = almoco;
        this.jantar = jantar;
    }

    public Dia() {
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Refeicao getAlmoco() {
        return almoco;
    }

    public void setAlmoco(Refeicao almoco) {
        this.almoco = almoco;
    }

    public Refeicao getJantar() {
        return jantar;
    }

    public void setJantar(Refeicao jantar) {
        this.jantar = jantar;
    }

    
}
