package br.ufms.sifighter.core.lutador;

import br.ufms.sifighter.core.Poderoso;
import br.ufms.sifighter.core.Regenerativo;

public class MonicaAssassina extends Lutador implements Poderoso, Regenerativo {

    public MonicaAssassina() {
        super("Mônica Assassina");
    }

    @Override
    public int getAgilidade() {
        return 80;
    }

    @Override
    public float getForca() {
        return 1.6f;
    }

    @Override
    public float getResistencia() {
        return 1.4f;
    }

    @Override
    public int getEnergiaParaPoder() {
        return 10;
    }

    @Override
    public int getDanoPoder() {
        return 10;
    }

    @Override
    public int recuperarVida() {
        return 0;
    }
}
