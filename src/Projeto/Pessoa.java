package Projeto;
public abstract class Pessoa {

  private String nome;

  private String apelido;

  private Data dataNascimento;

  private String email;

  private Point point;

  public Point getPoint() {
    return point;
  }

  public void setPoint(Point point) {
    this.point = point;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setApelido(String apelido) {
    this.apelido = apelido;
  }

  public Data getDataNascimento() {
    return dataNascimento;
  }

  public String getEmail() {
    return email;
  }

  public String getApelido() {
    return apelido;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Pessoa(String nome, String apelido, Data dataNascimento) {
    this.nome = nome;
    this.apelido = apelido;
    this.dataNascimento = dataNascimento;
  }

  public int age(){
    Data d = new Data(); // data de hoje
    if(d.getMonth() >= this.getDataNascimento().getMonth()){
      return d.getYear() - this.getDataNascimento().getYear();
    }else if(d.getMonth() == this.getDataNascimento().getMonth() && d.getDay() >= this.getDataNascimento().getDay()){
      return d.getYear() - this.getDataNascimento().getYear();
    }
    else{
      return d.getYear() - this.getDataNascimento().getYear()-1;
    }
  }

  @Override
  public String toString() {
    return "Pessoa{" +
            "nome='" + nome + '\'' +
            ", apelido='" + apelido + '\'' +
            ", dataNascimento=" + dataNascimento +
            ", email='" + email + '\'' +
            '}';
  }
}