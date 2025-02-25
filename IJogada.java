public interface IJogada {
    // Método que realiza a jogada, podendo lançar uma exceção se a entrada for inválida.
    int fazerJogada() throws JogadaInvalidaException;
}
