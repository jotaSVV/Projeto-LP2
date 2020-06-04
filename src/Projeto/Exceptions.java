package Projeto;
public class Exceptions  extends Exception {
    public Exceptions(String message) { // Exeção -> Algumas açoes podem dar erro, e as exeçoes o que faz é detetar o erro, diz qual é o erro e conotinua o programa (caso nao fizessemos execao o programa partia mal deteta-se um erro)
        super(message); // imprime a mensagem de erro
    }
}