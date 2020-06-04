package Projeto;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;

public class Turma {

  private int ano;
  private String codigo;
  private Curso curso;
  private RedBlackBST<Integer,Aluno> alunos = new RedBlackBST<>();
  private Professor professor;
  private Disciplina disciplina;
  private Horario horario;
  private Sala sala;

  public Turma(int ano, String codigo) {
    this.ano = ano;
    this.codigo = codigo;
  }


  public Curso getCurso() {
    return curso;
  }

  public void setCurso(Curso curso) {
    this.curso = curso;
  }

  public int getAno() {
    return ano;
  }

  public String getCodigo() {
    return codigo;
  }

  public Professor getProfessor() {
    return professor;
  }

  public Disciplina getDisciplina() {
    return disciplina;
  }

  public void setDisciplina(Disciplina disciplina) {
    this.disciplina = disciplina;
  }

  public Horario getHorario() {
    if(horario == null){
      return null;
    }
    else {
      return horario;
    }
  }

  public void setHorario(Horario horario) {
    this.horario = horario;
  }

  public Sala getSala() {
    return sala;
  }

  public void setSala(Sala sala) {
    this.sala = sala;
  }

  public RedBlackBST<Integer, Aluno> getAlunos() {
    return alunos;
  }

  public void setProfessor(Professor professor) {
    this.professor = professor;
  }


  public void horario_aulas(Horario h ,Sala s){
    this.setHorario(h); // associamos a turma ao horario e vice-versa
    h.getTurmas().put(this.getCodigo(),this); // associamos o horario à turma
    this.sala = s; // associamos a sala à turma e vice-versa
    this.sala.getTurmas().add(this); // associamos a turma á sala
    s.Horarios.add(h); // associamos a sala ao horario
  }

  @Override
  public String toString() {
    return "Turma{" +
            "ano=" + ano +
            ", codigo='" + codigo + '\'' +
            ", professor=" + professor +
            ", disciplina=" + disciplina +
            ", sala=" + sala +
            '}';
  }

  public void imprimeTodosAlunos (){
    System.out.println("Turma:" + this.getAno() + this.getCodigo());
    for (int cod: this.alunos.keys()
    ) {
      Aluno a = this.alunos.get(cod);
      System.out.println(a.getNome());
    }
  }

  /**
   * Método que indica em que sala uma determinada turma está a ter aulas
   * @param turmas base de dados de turmas
   * @param t turma enviada
   * Verifica se a turma existe na bd e caso exista imprime a sala em que uma turma esta a ter aulas
   */
  public void salaEmAula(SeparateChainingHashST<String, Turma> turmas, Turma t) {
    if (turmas.contains(t.getCodigo())) {
      System.out.println("A turma" + t.getCodigo() + "tem aulas na sala" + t.getSala());
    } else {
      System.out.println("A turma que procurou não existe na Separate Channing!");
    }

  }


  /**
   * Função que indica se um determinado aluno , pertence a uma determinada turma
   * @param alunos base de dados de alunos
   * @param turmas base de dados de turmas
   * @param t turmas
   * @param a alunos
   * verifica se a e t estao na bd
   */
  public void alunoPertenceATurma (RedBlackBST<Integer, Aluno> alunos,SeparateChainingHashST<String, Turma> turmas,Turma t,Aluno a){
    if(turmas.contains(t.getCodigo())){
      if(alunos.contains(a.getNumeroAluno())){
        if(t.getAlunos().contains(a.getNumeroAluno())){
          System.out.println("O aluno " + a.getNome()+" " + a.getApelido() + ", numero" + a.getNumeroAluno() + " pertence à turma\n" + this.toString());
        }else{
          System.out.println("O aluno " + a.getNome() + a.getApelido() + " não pertence à turma!");
        }
      }else{
        System.out.println("O aluno que tentou procurar, não é valido!");
      }
    }else{
      System.out.println("A turma que pretende procurar, não é valida!");
    }
  }

  /**
   * Função que indica se um determinado professor , pertence a uma determinada turma
   * @param professores bd de alunos
   * @param turmas bd de turmas
   * @param t
   * @param p
   */
  public void professorPertenceATurma (RedBlackBST<String, Aluno> professores,SeparateChainingHashST<String, Turma> turmas,Turma t,Professor p){
    if(turmas.contains(t.getCodigo())){
      if(professores.contains(p.getEmail())){
        if(t.getProfessor() == p){
          System.out.println("O Professor " + p.getNome() + " "+p.getApelido()+" pertence à turma\n" + this.toString());
        }else{
          System.out.println("O aluno " + p.getNome() + p.getApelido() + " não pertence à turma!");
        }
      }else{
        System.out.println("O professor que tentou procurar, não é valido!");
        return;
      }
    }else{
      System.out.println("A turma que pretende procurar, não é valida!");
      return;
    }
  }

  /**
   *
   * @param turmas st de turmas
   * @param t turma
   *  Percorre os alunos da turma e soma as idades para depois fazer a media
   */
  public void mediaIdadesTurma (SeparateChainingHashST<String, Turma> turmas,Turma t){
    int soma=0, media=0,contador=0;
    //se a turma que queremos saber as idades existir na st turmas, entao podemos calcular a media de idades
    if(turmas.contains(t.getCodigo())){
      for (int cod:t.getAlunos().keys()) {
          Aluno a = t.getAlunos().get(cod);
          soma += a.age();
          contador++;
      }
    }else{
      System.out.println("A turma que pretende procurar, não é valida!");
      return;
    }
    media = soma / contador;
    System.out.println("A media de idades da turma " + t.getCodigo() + " é de " + media + " anos.");
  }
}