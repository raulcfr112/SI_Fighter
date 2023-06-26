package br.ufms.sifighter.simulador;

import br.ufms.sifighter.core.Acao;
import br.ufms.sifighter.core.lutador.Lutador;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Card {
    public static class DadosLuta {
        ArrayList<String> lutas;
        String[] lutadores;

        public DadosLuta(ArrayList<String> lutas, String[] lutadores) {
            this.lutas = lutas;
            this.lutadores = lutadores;
        }
    }

    public static class DadosVencedor {
        Lutador vencedor;
        String resultado;

        public DadosVencedor(Lutador vencedor, String resultado) {
            this.vencedor = vencedor;
            this.resultado = resultado;
        }
    }

    public DadosLuta leituraCard(Path card) {
        try {
            Scanner nLutas = new Scanner(card.toFile());

            int lutas = nLutas.nextInt();
            int contadorAux = 0;
            String[] vetorLutadores = new String[lutas * 2];
            ArrayList<String> listaLutas = new ArrayList<>();
            nLutas.nextLine();

            for (int i = 0; i < lutas; i++) {
                String[] lutadores = nLutas.nextLine().split("vs");
                vetorLutadores[contadorAux++] = lutadores[0].substring(0, lutadores[0].length() - 1);
                vetorLutadores[contadorAux++] = lutadores[1].substring(1);
            }
            while (nLutas.hasNextLine()) {
                listaLutas.add(nLutas.nextLine());
            }
            return new DadosLuta(listaLutas, vetorLutadores);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public DadosVencedor leituraLuta(Lutador lutador0, Lutador lutador1, Path luta) {
        Lutador[] lutadores = new Lutador[2];
        int auxContadorRounds = 0;

        if (lutador0.getAgilidade() >= lutador1.getAgilidade()) {
            lutadores[0] = lutador0;
            lutadores[1] = lutador1;
        } else {
            lutadores[1] = lutador0;
            lutadores[0] = lutador1;
        }

        ArrayList<Acao> acoesLutadorA = new ArrayList<>();
        ArrayList<Acao> acoesLutadorB = new ArrayList<>();

        try {
            Scanner acoes = new Scanner(luta.toFile());
            String[] comandos;
            String[] comandosA;
            String[] comandosB;
            ArrayList<Acao> prioridadesAcoes = new ArrayList<>();
            prioridadesAcoes.add(Acao.AVANCAR_DIREITA);
            prioridadesAcoes.add(Acao.AVANCAR_ESQUERDA);
            prioridadesAcoes.add(Acao.PULAR);
            prioridadesAcoes.add(Acao.ABAIXAR);
            prioridadesAcoes.add(Acao.LANCAR_ADVERSARIO);
            prioridadesAcoes.add(Acao.DEFENDER);
            prioridadesAcoes.add(Acao.CHUTAR_ALTO);
            prioridadesAcoes.add(Acao.CHUTAR_BAIXO);
            prioridadesAcoes.add(Acao.SOCAR_ALTO);
            prioridadesAcoes.add(Acao.SOCAR_BAIXO);
            prioridadesAcoes.add(Acao.GOLPEAR_ESPECIAL);
            prioridadesAcoes.add(Acao.DISPARAR_PODER);

            lutadores[0].lutar(lutadores[1], -10);
            lutadores[1].lutar(lutadores[0], 10);

            while (acoes.hasNextLine()) {
                if (auxContadorRounds > 0) {
                    lutador0.recuperacaoPorRound();
                    lutador1.recuperacaoPorRound();
                }
                comandos = acoes.nextLine().split("\\|");
                comandosA = comandos[0].split(" ");
                comandosB = comandos[1].substring(1).split(" ");

                leituraAcoes(acoesLutadorA, comandosA);
                leituraAcoes(acoesLutadorB, comandosB);

                int acoesPorRound;
                if (acoesLutadorA.size() > acoesLutadorB.size()) {
                    acoesPorRound = acoesLutadorA.size();
                } else {
                    acoesPorRound = acoesLutadorB.size();
                }
                for (int i = 0; i < acoesPorRound; i++) {
                    if (!acoesLutadorA.isEmpty() && !acoesLutadorB.isEmpty()) {
                        Acao acaoA = acoesLutadorA.remove(0);
                        Acao acaoB = acoesLutadorB.remove(0);

                        if (prioridadesAcoes.indexOf(acaoA) <= 3) {
//                            if (prioridadesAcoes.indexOf(acaoA) == 0){
//                                if (lutadores[1].getPosX() == lutadores[0].getPosX() + 5){
//                                    lutadores[0].executar(acaoA);
//                                    if ()
//                                }
//                            }
                            lutadores[0].executar(acaoA);
                        }
                        if (prioridadesAcoes.indexOf(acaoB) <= 3) {
                            lutadores[1].executar(acaoB);
                        }
                        if (prioridadesAcoes.indexOf(acaoA) == 4) {
                            lutadores[0].executar(acaoA);
                            if (lutadorPerdeu(lutador1)) {
                                return resultado(lutador0, lutador1);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoB) == 4) {
                            lutadores[1].executar(acaoB);
                            if (lutadorPerdeu(lutador0)) {
                                return resultado(lutador1, lutador0);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoA) == 5) {
                            lutadores[0].executar(acaoA);
                        }
                        if (prioridadesAcoes.indexOf(acaoB) == 5) {
                            lutadores[1].executar(acaoB);
                        }
                        if (prioridadesAcoes.indexOf(acaoA) >= 6 && prioridadesAcoes.indexOf(acaoA) <= 9) {
                            lutadores[0].executar(acaoA);
                            if (lutadorPerdeu(lutador1)) {
                                return resultado(lutador0, lutador1);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoB) >= 6 && prioridadesAcoes.indexOf(acaoB) <= 9) {
                            lutadores[1].executar(acaoB);
                            if (lutadorPerdeu(lutador0)) {
                                return resultado(lutador1, lutador0);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoA) == 10) {
                            lutadores[0].executar(acaoA);
                            if (lutadorPerdeu(lutador1)) {
                                return resultado(lutador0, lutador1);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoB) == 10) {
                            lutadores[1].executar(acaoB);
                            if (lutadorPerdeu(lutador0)) {
                                return resultado(lutador1, lutador0);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoA) == 11) {
                            lutadores[0].executar(acaoA);
                            if (lutadorPerdeu(lutador1)) {
                                return resultado(lutador0, lutador1);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoB) == 11) {
                            lutadores[1].executar(acaoB);
                            if (lutadorPerdeu(lutador0)) {
                                return resultado(lutador1, lutador0);
                            }
                        }
                    } else if (acoesLutadorA.isEmpty()) {
                        Acao acaoB = acoesLutadorB.remove(0);

                        if (prioridadesAcoes.indexOf(acaoB) <= 3) {
                            lutadores[1].executar(acaoB);
                        }
                        if (prioridadesAcoes.indexOf(acaoB) == 4) {
                            lutadores[1].executar(acaoB);
                            if (lutadorPerdeu(lutador0)) {
                                return resultado(lutador1, lutador0);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoB) == 5) {
                            lutadores[1].executar(acaoB);
                        }
                        if (prioridadesAcoes.indexOf(acaoB) >= 6 && prioridadesAcoes.indexOf(acaoB) <= 9) {
                            lutadores[1].executar(acaoB);
                            if (lutadorPerdeu(lutador0)) {
                                return resultado(lutador1, lutador0);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoB) == 10) {
                            lutadores[1].executar(acaoB);
                            if (lutadorPerdeu(lutador0)) {
                                return resultado(lutador1, lutador0);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoB) == 11) {
                            lutadores[1].executar(acaoB);
                            if (lutadorPerdeu(lutador0)) {
                                return resultado(lutador1, lutador0);
                            }
                        }
                    } else {
                        Acao acaoA = acoesLutadorA.remove(0);

                        if (prioridadesAcoes.indexOf(acaoA) <= 3) {
                            lutadores[0].executar(acaoA);
                        }
                        if (prioridadesAcoes.indexOf(acaoA) == 4) {
                            lutadores[0].executar(acaoA);
                            if (lutadorPerdeu(lutador1)) {
                                return resultado(lutador0, lutador1);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoA) == 5) {
                            lutadores[0].executar(acaoA);
                        }
                        if (prioridadesAcoes.indexOf(acaoA) >= 6 && prioridadesAcoes.indexOf(acaoA) <= 9) {
                            lutadores[0].executar(acaoA);
                            if (lutadorPerdeu(lutador1)) {
                                return resultado(lutador0, lutador1);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoA) == 10) {
                            lutadores[0].executar(acaoA);
                            if (lutadorPerdeu(lutador1)) {
                                return resultado(lutador0, lutador1);
                            }
                        }
                        if (prioridadesAcoes.indexOf(acaoA) == 11) {
                            lutadores[0].executar(acaoA);
                            if (lutadorPerdeu(lutador1)) {
                                return resultado(lutador0, lutador1);
                            }
                        }
                    }
                }
//            final Map<Botao, Acao> botoes = new HashMap<>();
                //tentei implementar com hashmap mas o modulo nao permitia!
                auxContadorRounds++;
            }
            if (lutadores[0].getVida() >= lutadores[1].getVida()) {
                return resultado(lutadores[0], lutadores[1]);
            } else {
                return resultado(lutadores[1], lutadores[0]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void leituraAcoes(ArrayList<Acao> acoes, String[] acoesArray) {
        for (String s : acoesArray) {
            if (s.equals("Right") || s.equals("Left") || s.equals("Down") || s.equals("Up")) {
                if (s.equals("Right")) {
                    acoes.add(Acao.AVANCAR_DIREITA);
                }
                if (s.equals("Left")) {
                    acoes.add(Acao.AVANCAR_ESQUERDA);
                }
                if (s.equals("Up")) {
                    acoes.add(Acao.PULAR);
                }
                if (s.equals("Down")) {
                    acoes.add(Acao.ABAIXAR);
                }
            } else {
                switch (s) {
                    case "A" -> acoes.add(Acao.SOCAR_BAIXO);
                    case "B" -> acoes.add(Acao.CHUTAR_BAIXO);
                    case "X" -> acoes.add(Acao.SOCAR_ALTO);
                    case "Y" -> acoes.add(Acao.CHUTAR_ALTO);
                    case "LB" -> acoes.add(Acao.LANCAR_ADVERSARIO);
                    case "LT" -> acoes.add(Acao.DEFENDER);
                    case "RB" -> acoes.add(Acao.GOLPEAR_ESPECIAL);
                    case "RT" -> acoes.add(Acao.DISPARAR_PODER);
                }
            }
        }
    }

    private boolean lutadorPerdeu(Lutador lutador) {
        return lutador.getVida() <= 0;
    }

    private DadosVencedor resultado(Lutador vencedor, Lutador perdedor) {
        String resultado = vencedor.getNome() + " vs " + perdedor.getNome() + " | Vencedor: " + vencedor.getNome() + " | Vida: " + vencedor.getVida() +
                " vs " + perdedor.getVida();
        return new DadosVencedor(vencedor, resultado);
    }

}