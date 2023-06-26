package br.ufms.sifighter.core.lutador;

import br.ufms.sifighter.core.Especial;
import br.ufms.sifighter.core.Incansavel;
import br.ufms.sifighter.core.Poderoso;

public class CaixeiroViajante extends Lutador implements Especial, Poderoso, Incansavel {

    public CaixeiroViajante() {
        super("Caixeiro Viajante");
    }

    @Override
    public int getAgilidade() {
        return 75;
    }

    @Override
    public float getForca() {
        return 1.6f;
    }

    @Override
    public float getResistencia() {
        return 1.1f;
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
