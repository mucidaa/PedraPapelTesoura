public abstract class Jogador implements IJogada {
    private String nome;
    private int pontuacao;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void incrementarPontuacao() {
        this.pontuacao++;
    }

    public void resetPontuacao() {
        this.pontuacao = 0;
    }

    // Método abstrato a ser implementado pelas subclasses
    public abstract int fazerJogada() throws JogadaInvalidaException;
}
