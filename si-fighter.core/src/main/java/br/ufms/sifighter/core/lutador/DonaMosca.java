package br.ufms.sifighter.core.lutador;

import br.ufms.sifighter.core.Incansavel;
import br.ufms.sifighter.core.Poderoso;

public class DonaMosca extends Lutador implements Poderoso, Incansavel {

    public DonaMosca() {
        super("Dona Mosca");
    }

    @Override
    public int getAgilidade() {
        return 85;
    }

    @Override
    public float getForca() {
        return 1.0f;
    }

    @Override
    public float getResistencia() {
        return 1.0f;
    }

    @Override
    public int recuperarEnergia() {
        return 1;
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
