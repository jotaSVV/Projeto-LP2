package Projeto;

public class PontosDePassagem extends Point{
    private Edificio e;
    private int cod;
    public PontosDePassagem(int cod,double x, double y, int z, String name) {
        super(x, y, z, name);
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public Edificio getE() {
        return e;
    }

    public void setE(Edificio e) {
        this.e = e;
    }

    public void addEdificio(Edificio e){
        this.edificio = e;
    }
}
