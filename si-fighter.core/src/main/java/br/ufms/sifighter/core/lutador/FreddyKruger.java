package br.ufms.sifighter.core.lutador;

import br.ufms.sifighter.core.Especial;
import br.ufms.sifighter.core.Incansavel;
import br.ufms.sifighter.core.Poderoso;
import br.ufms.sifighter.core.Regenerativo;

public class FreddyKruger extends Lutador implements Especial, Poderoso, Incansavel, Regenerativo {

    public FreddyKruger() {
        super("Freddy Kruger");
    }

    @Override
    public int getAgilidade() {
        return 95;
    }

    @Override
    public float getForca() {
        return 1.8f;
    }

    @Override
    public float getResistencia() {
        return 1.5f;
    }

    @Override
    public int getEnergiaParaEspecial() {
        return 20;
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
        return 5;
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
        return 2;
    }
}
