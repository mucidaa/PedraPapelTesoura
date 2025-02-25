import java.util.Random;
import javax.swing.JOptionPane;

public class JogadorComputador extends Jogador {
    public JogadorComputador(String nome) {
        super(nome);
    }

    // Construtor com nome padrão
    public JogadorComputador() {
        super("Computador");
    }

    @Override
    public int fazerJogada() {
        Random rand = new Random();
        int escolha = rand.nextInt(3) + 1; // gera número entre 1 e 3
        String jogada = (escolha == 1 ? "Pedra" : (escolha == 2 ? "Papel" : "Tesoura"));
        JOptionPane.showMessageDialog(null, getNome() + " escolheu: " + jogada,
                "Jogada do Computador", JOptionPane.INFORMATION_MESSAGE);
        return escolha;
    }
}
