package br.ufms.sifighter.simulador;

import br.ufms.sifighter.core.lutador.Lutador;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SimuladorApp {

    public static void main(String[] args) {
        iniciarLuta();
    }

    public static void iniciarLuta() {
        Path card = Paths.get("C:\\Users\\Raul\\Desktop\\si-fighter-main\\si-fighter.simulador\\src\\main\\java\\br\\ufms\\sifighter\\simulador\\arquivos\\card.txt");
        FileWriter arq;
        try {
            arq = new FileWriter("C:\\Users\\Raul\\Desktop\\si-fighter-main\\si-fighter.simulador\\src\\main\\java\\br\\ufms\\sifighter\\simulador\\arquivos\\resultados");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PrintWriter gravarArq = new PrintWriter(arq);
        Card card1 = new Card();
        String[] lutadores = card1.leituraCard(card).lutadores;
        String[] vencedores = new String[lutadores.length / 2];
        int luta = 1;
        Path caminhoLuta;

        for (int i = 0; i < lutadores.length; i += 2) {
            caminhoLuta = Paths.get("C:\\Users\\Raul\\Desktop\\si-fighter-main\\si-fighter.simulador\\src\\main\\java\\br\\ufms\\sifighter\\simulador\\arquivos\\luta_" + luta + ".txt");
            vencedores[luta - 1] = card1.leituraLuta(Lutador.get(lutadores[i]), Lutador.get(lutadores[i + 1]), caminhoLuta).vencedor.getNome();
            luta++;
            gravarArq.println(card1.leituraLuta(Lutador.get(lutadores[i]), Lutador.get(lutadores[i + 1]), caminhoLuta).resultado);
        }
        while (vencedores.length > 1) {
            lutadores = vencedores;
            vencedores = new String[vencedores.length / 2];
            int contagem = 0;
            for (int i = 0; i < lutadores.length; i += 2) {
                caminhoLuta = Paths.get("C:\\Users\\Raul\\Desktop\\si-fighter-main\\si-fighter.simulador\\src\\main\\java\\br\\ufms\\sifighter\\simulador\\arquivos\\luta_" + luta + ".txt");
                vencedores[contagem] = card1.leituraLuta(Lutador.get(lutadores[i]), Lutador.get(lutadores[i + 1]), caminhoLuta).vencedor.getNome();
                luta++;
                contagem++;
                gravarArq.println(card1.leituraLuta(Lutador.get(lutadores[i]), Lutador.get(lutadores[i + 1]), caminhoLuta).resultado);
            }
        }
        gravarArq.println("Campeão: " + vencedores[0]);
        gravarArq.close();
    }
}