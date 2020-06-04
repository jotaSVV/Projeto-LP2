package Projeto;
import java.util.ArrayList;

public class Sala{

  private int codigo;

  private int nrCadeiras;

  public void setNrCadeiras(int nrCadeiras) {
    this.nrCadeiras = nrCadeiras;
  }

  public void setPiso(int piso) {
    this.piso = piso;
  }

  public void setNrTomadas(int nrTomadas) {
    this.nrTomadas = nrTomadas;
  }

  private int piso;

  private int nrTomadas;

  private ArrayList<Turma> turmas = new ArrayList<>();

  private Edificio edificio;

  ArrayList<Horario>  Horarios = new ArrayList<>();

  public Point point;


  public ArrayList<Turma> getTurmas() {
    return turmas;
  }

  public Edificio getEdificio() {
    return edificio;
  }

  public void setEdificio(Edificio edificio) {
    this.edificio = edificio;
  }

  public Point getPoint() {
    return point;
  }

  public int getCodigo() {
    return codigo;
  }

  public int getNrCadeiras() {
    return nrCadeiras;
  }

  public int getNrTomadas() {
    return nrTomadas;
  }

  public ArrayList<Horario> getHorarios() {
    return Horarios;
  }

  public Sala(int codigo, int nrCadeiras) {
    this.codigo = codigo;
    this.nrCadeiras = nrCadeiras;
    this.piso = codigo / 100;
    this.nrTomadas = nrCadeiras;
  }

  public int getPiso() {
    return piso;
  }

  public void addEdificio(Edificio e){
    this.edificio = e;
  }

}