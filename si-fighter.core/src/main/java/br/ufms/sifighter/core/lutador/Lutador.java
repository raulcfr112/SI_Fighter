package br.ufms.sifighter.core.lutador;

import br.ufms.sifighter.core.*;

import java.util.HashMap;
import java.util.Map;

public abstract class Lutador implements MovimentosBasicos {

    private static final Map<String, Lutador> LUTADORES = new HashMap<>();

    static {
        LUTADORES.put("Arlequina", new Arlequina());
        LUTADORES.put("Barba Azul", new BarbaAzul());
        LUTADORES.put("Caixeiro Viajante", new CaixeiroViajante());
        LUTADORES.put("Dona Mosca", new DonaMosca());
        LUTADORES.put("Freddy Kruger", new FreddyKruger());
        LUTADORES.put("Mônica Assassina", new MonicaAssassina());
        LUTADORES.put("Ninja Samurai", new NinjaSamurai());
        LUTADORES.put("Sanfoneiro Fantasma", new SanfoneiroFantasma());
    }

    private final Map<Acao, Runnable> acoes = new HashMap<>();

    protected final String nome;
    protected int vida;
    protected int energia;
    protected int posX;
    protected EstadoVertical posY;
    protected boolean defendendo;

    protected Lutador oponente;

    protected Lutador(String nome) {
        this.nome = nome;

        acoes.put(Acao.AVANCAR_ESQUERDA, this::avancarEsquerda);
        acoes.put(Acao.AVANCAR_DIREITA, this::avancarDireita);
        acoes.put(Acao.ABAIXAR, this::abaixar);
        acoes.put(Acao.PULAR, this::pular);

        acoes.put(Acao.SOCAR_BAIXO, this::socarBaixo);
        acoes.put(Acao.SOCAR_ALTO, this::socarAlto);
        acoes.put(Acao.CHUTAR_BAIXO, this::chutarBaixo);
        acoes.put(Acao.CHUTAR_ALTO, this::chutarAlto);
        acoes.put(Acao.LANCAR_ADVERSARIO, this::lancarAdversario);
        acoes.put(Acao.DEFENDER, this::defender);

        if (this instanceof Especial) {
            acoes.put(Acao.GOLPEAR_ESPECIAL, this::avancarDireita);
        }
        if (this instanceof Poderoso) {
            acoes.put(Acao.DISPARAR_PODER, this::avancarDireita);
        }
    }

    public void lutar(Lutador oponente, int posXInicial) {
        this.oponente = oponente;
        this.posX = posXInicial;
        this.vida = 100;
        this.energia = 100;
    }

    public void executar(Acao acao) {
        acoes.get(acao).run();
    }

    protected void movimentar(Acao acao, int energia) {
        if (this.energia >= energia) {
            switch (acao) {
                case AVANCAR_ESQUERDA:
                    if (this.posX > 20) {
                        this.posX -= 5;
                    }
                    break;
                case AVANCAR_DIREITA:
                    if (this.posX < 20) {
                        this.posX += 5;
                    }
                    break;
                case ABAIXAR:
                    this.posY = EstadoVertical.ABAIXADO;
                    break;
                case PULAR:
                    this.posY = EstadoVertical.PULANDO;
                    break;
            }
            this.energia -= energia;
        }
    }

    protected void atacar(int energia, int dano) {
        if (this.energia >= energia) {
            oponente.receberDano((int) (dano * getForca()));
            this.energia -= energia;
        }
    }

    public void receberDano(int dano) {
        vida -= isDefendendo() ? (dano / getResistencia() / 3) : (dano / getResistencia());
    }

    public void avancarEsquerda() {
        movimentar(Acao.AVANCAR_ESQUERDA, 1);
    }

    public void avancarDireita() {
        movimentar(Acao.AVANCAR_DIREITA, 1);
    }

    public void abaixar() {
        movimentar(Acao.ABAIXAR, 2);
    }

    public void pular() {
        movimentar(Acao.PULAR, 3);
    }

    public void socarBaixo() {
        atacar(5, 10);
    }

    public void socarAlto() {
        atacar(7, 20);
    }

    public void chutarBaixo() {
        atacar(7, 15);
    }

    public void chutarAlto() {
        atacar(10, 30);
    }

    public void lancarAdversario() {
        atacar(20, 30);
    }

    public void defender() {
        this.defendendo = true;
    }

    public final String getNome() {
        return nome;
    }

    public final int getVida() {
        return vida;
    }

    public final int getEnergia() {
        return energia;
    }

    public void recuperacaoPorRound() {
        if (this instanceof Incansavel) {
            this.energia += 4;
        }
        if (this instanceof Regenerativo) {
            this.vida += 5;
        }
        this.energia += 1;
        this.defendendo = false;
    }

    public final int getPosX() {
        return posX;
    }

    public EstadoVertical getPosY() {
        return posY;
    }

    public void setPosY(EstadoVertical posY) {
        this.posY = posY;
    }

    public boolean isDefendendo() {
        return defendendo;
    }

    public void setDefendendo(boolean defendendo) {
        this.defendendo = defendendo;
    }

    public abstract int getAgilidade();

    public abstract float getForca();

    public abstract float getResistencia();

    public static final Lutador get(String nome) {
        return LUTADORES.get(nome);
    }
}
