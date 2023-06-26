package br.ufms.sifighter.core.lutador;

import br.ufms.sifighter.core.Especial;
import br.ufms.sifighter.core.Poderoso;

public class BarbaAzul extends Lutador implements Especial, Poderoso {

    public BarbaAzul() {
        super("Barba Azul");
    }

    @Override
    public int getAgilidade() {
        return 70;
    }

    @Override
    public float getForca() {
        return 1.7f;
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
        return 20;
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
