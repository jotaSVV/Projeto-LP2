package Projeto;
import java.util.ArrayList;

public class Edificio{

  public String nome;

    /**
   * 
   * @element-type Sala
   */
  private ArrayList<Sala> salas = new ArrayList<>();
  public Point point;

  private Faculdade faculdade;

  public Edificio(String nome, Faculdade faculdade) {
    this.nome = nome;
    this.faculdade = faculdade;
  }

  public String getNome() {
    return nome;
  }

  public ArrayList<Sala> getSalas() {
    return salas;
  }

  public Point getPoint() {
    return point;
  }

  public Faculdade getFaculdade() {
    return faculdade;
  }

  public void setFaculdade(Faculdade faculdade) {
    this.faculdade = faculdade;
  }
}