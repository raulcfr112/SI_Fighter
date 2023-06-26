package br.ufms.sifighter.core.lutador;

import br.ufms.sifighter.core.Especial;
import br.ufms.sifighter.core.Incansavel;
import br.ufms.sifighter.core.Poderoso;

public class Arlequina extends Lutador implements Especial, Poderoso, Incansavel {

    public Arlequina() {
        super("Arlequina");
    }

    @Override
    public int getAgilidade() {
        return 90;
    }

    @Override
    public float getForca() {
        return 1.2f;
    }

    @Override
    public float getResistencia() {
        return 1.0f;
    }

    @Override
    public int getEnergiaParaEspecial() {
        return 25;
    }

    @Override
    public int getDanoEspecial() {
        return 50;
    }

    @Override
    public int getAlcanceEspecial() {
        return 20;
    }

    @Override
    public int recuperarEnergia() {
        return 2;
    }

    @Override
    public int getEnergiaParaPoder() {
        return 10;
    }

    @Override
    public int getDanoPoder() {
        return 10;
    }
}
