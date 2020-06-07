package Projeto;

  public class Point {
    public double x;
    public double y;
    public int z;
    private String name;
    public Sala sala;
    public Edificio edificio;

    public Point(double x, double y, int z, String name) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.name = name;
    }

    public Point() {
    }

    public double getX() {
      return x;
    }

    public void setX(double x) {
      this.x = x;
    }

    public double getY() {
      return y;
    }

    public void setY(double y) {
      this.y = y;
    }

    public int getZ() {
      return z;
    }

    public void setZ(int z) {
      this.z = z;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
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

    @Override
    public String toString() {
      return "Point{" +
              "x=" + x +
              ", y=" + y +
              ", z=" + z +
              ", name='" + name + '\'' +
              '}';
    }
}