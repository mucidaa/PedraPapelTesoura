import javax.swing.*;

public class Jogo {
    // Constantes para as jogadas
    public static final int pedra = 1;
    public static final int papel = 2;
    public static final int tesoura = 3;

    private Jogador jogador1;
    private Jogador jogador2;

    // Método iniciarJogo() sem parâmetros: solicita o modo de jogo por meio de uma interface gráfica.
    public void iniciarJogo() {
        Object[] opcoes = {"Jogador vs Computador", "Jogador vs Jogador"};
        int modo = JOptionPane.showOptionDialog(null, "Escolha o modo de jogo:", "Modo de Jogo",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);


        if (modo == 0) { // Jogador vs Computador
            String nome = JOptionPane.showInputDialog(null, "Digite o nome do jogador:",
                    "Nome do Jogador", JOptionPane.QUESTION_MESSAGE);
            if (nome == null || nome.trim().isEmpty()) {
                nome = "Jogador Humano";
            }
            jogador1 = new JogadorHumano(nome);
            jogador2 = new JogadorComputador();
        } else if (modo == 1) { // Jogador vs Jogador
            String nome1 = JOptionPane.showInputDialog(null, "Digite o nome do primeiro jogador:",
                    "Nome do Jogador 1", JOptionPane.QUESTION_MESSAGE);
            if (nome1 == null || nome1.trim().isEmpty()) {
                nome1 = "Jogador 1";
            }
            String nome2 = JOptionPane.showInputDialog(null, "Digite o nome do segundo jogador:",
                    "Nome do Jogador 2", JOptionPane.QUESTION_MESSAGE);
            if (nome2 == null || nome2.trim().isEmpty()) {
                nome2 = "Jogador 2";
            }
            jogador1 = new JogadorHumano(nome1);
            jogador2 = new JogadorHumano(nome2);
        } else {
            JOptionPane.showMessageDialog(null, "Modo de jogo inválido ou cancelado. Encerrando o jogo.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }

        jogarPartida();
    }

    // Gerencia as rodadas da partida (melhor de 3 rodadas, por exemplo)
    private void jogarPartida() {
        int rodadas = 3;
        jogador1.resetPontuacao();
        jogador2.resetPontuacao();

        for (int i = 1; i <= rodadas; i++) {
            String roundInfo = "\n--- Rodada " + i + " ---\n";
            int jogada1 = 0, jogada2 = 0;

            try {
                jogada1 = jogador1.fazerJogada();
            } catch (JogadaInvalidaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro na Jogada", JOptionPane.ERROR_MESSAGE);
                i--; // repete a rodada em caso de jogada inválida
                continue;
            }

            try {
                jogada2 = jogador2.fazerJogada();
            } catch (JogadaInvalidaException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro na Jogada", JOptionPane.ERROR_MESSAGE);
                i--; // repete a rodada se a jogada do segundo jogador for inválida
                continue;
            }

            int resultado = definirVencedor(jogada1, jogada2);
            if (resultado == 1) {
                roundInfo += jogador1.getNome() + " venceu a rodada!\n";
                jogador1.incrementarPontuacao();
            } else if (resultado == 2) {
                roundInfo += jogador2.getNome() + " venceu a rodada!\n";
                jogador2.incrementarPontuacao();
            } else {
                roundInfo += "Rodada empatada!\n";
            }
            roundInfo += "Placar: " + jogador1.getNome() + " " + jogador1.getPontuacao() +
                    " x " + jogador2.getPontuacao() + " " + jogador2.getNome();

            JOptionPane.showMessageDialog(null, roundInfo, "Resultado da Rodada", JOptionPane.INFORMATION_MESSAGE);
        }

        // Exibe o vencedor final
        String finalResult = "\n=== Resultado Final ===\n";
        if (jogador1.getPontuacao() > jogador2.getPontuacao()) {
            finalResult += "Parabéns, " + jogador1.getNome() + "! Você venceu o jogo!";
        } else if (jogador2.getPontuacao() > jogador1.getPontuacao()) {
            finalResult += "Parabéns, " + jogador2.getNome() + "! Você venceu o jogo!";
        } else {
            finalResult += "O jogo terminou empatado!";
        }
        JOptionPane.showMessageDialog(null, finalResult, "Resultado Final", JOptionPane.INFORMATION_MESSAGE);
    }

    // Retorna 1 se o jogador1 vencer, 2 se o jogador2 vencer ou 0 para empate.
    private int definirVencedor(int jogada1, int jogada2) {
        if (jogada1 == jogada2) {
            return 0;
        }
        if ((jogada1 == pedra && jogada2 == tesoura) ||
                (jogada1 == papel && jogada2 == pedra) ||
                (jogada1 == tesoura && jogada2 == papel)) {
            return 1;
        } else {
            return 2;
        }
    }
}
