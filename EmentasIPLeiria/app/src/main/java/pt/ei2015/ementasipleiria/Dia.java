package pt.ei2015.ementasipleiria;

import java.util.Date;

/**
 * Created by alves on 22/07/2015.
 */
public class Dia {
    Date dia;
    Refeicao almoco;
    Refeicao jantar;

    public Dia() {
    }

    public Dia(Date dia, Refeicao almoco, Refeicao jantar) {
        this.dia = dia;
        this.almoco = almoco;
        this.jantar = jantar;
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
