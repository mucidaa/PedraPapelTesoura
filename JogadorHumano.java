import javax.swing.JOptionPane;

public class JogadorHumano extends Jogador {
    public JogadorHumano(String nome) {
        super(nome);
    }

    // Construtor com nome padrão
    public JogadorHumano() {
        super("Jogador Humano");
    }

    @Override
    public int fazerJogada() throws JogadaInvalidaException {
        String input = JOptionPane.showInputDialog(null,
                getNome() + ", escolha sua jogada:\n1 - Pedra\n2 - Papel\n3 - Tesoura",
                "Escolha de Jogada", JOptionPane.QUESTION_MESSAGE);
        if(input == null) {
            throw new JogadaInvalidaException("Nenhuma entrada fornecida!");
        }
        int escolha;
        try {
            escolha = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            throw new JogadaInvalidaException("Entrada inválida! Por favor, insira um número.");
        }
        if (escolha < 1 || escolha > 3) {
            throw new JogadaInvalidaException("Jogada inválida! Escolha 1 para Pedra, 2 para Papel ou 3 para Tesoura.");
        }
        return escolha;
    }
}
