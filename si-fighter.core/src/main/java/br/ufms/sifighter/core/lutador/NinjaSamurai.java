package br.ufms.sifighter.core.lutador;

import br.ufms.sifighter.core.Especial;
import br.ufms.sifighter.core.Incansavel;
import br.ufms.sifighter.core.Poderoso;

public class NinjaSamurai extends Lutador implements Especial, Poderoso, Incansavel {

    public NinjaSamurai() {
        super("Ninja Samurai");
    }

    @Override
    public int getAgilidade() {
        return 82;
    }

    @Override
    public float getForca() {
        return 1.5f;
    }

    @Override
    public float getResistencia() {
        return 1.3f;
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
        return 0;
    }

    @Override
    public int recuperarEnergia() {
        return 0;
    }

    @Override
    public int getEnergiaParaPoder() {
        return 0;
    }

    @Override
    public int getDanoPoder() {
        return 0;
    }
}
