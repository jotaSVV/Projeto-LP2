package Projeto;

public class Point {

  public Integer x;

  public Integer y;

  public Integer z;

    public Sala sala;
    public Edificio edificio;

  public Point(Integer x, Integer y, Integer z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }


  public Integer getX() {
    return x;
  }

  public void setX(Integer x) {
    this.x = x;
  }

  public Integer getY() {
    return y;
  }

  public void setY(Integer y) {
    this.y = y;
  }

  public Integer getZ() {
    return z;
  }

  public void setZ(Integer z) {
    this.z = z;
  }

  public Sala getSala() {
    return sala;
  }

  public void setSala(Sala sala) {
    this.sala = sala;
  }

  public Edificio getEdificio() {
    return edificio;
  }

  public void setEdificio(Edificio edificio) {
    this.edificio = edificio;
  }
}