package Projeto;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.util.ArrayList;

public class Aluno extends  Pessoa {

  private int numeroAluno;

  private SeparateChainingHashST<String,Turma> turmas = new SeparateChainingHashST<>();

  private SeparateChainingHashST<String,Disciplina> disciplinas = new SeparateChainingHashST<>();

  public SeparateChainingHashST<String, Disciplina> getDisciplinas() {
    return disciplinas;
  }

  public SeparateChainingHashST<String, Turma> getTurmas() {
    return turmas;
  }

  public int getNumeroAluno() {
    return numeroAluno;
  }






  public Aluno(String nome, String apelido, Data dataNascimento, int numeroAluno) {
    super(nome, apelido, dataNascimento);
    this.numeroAluno = numeroAluno;
    Point pos = new Point(0,0,0,this.getNome()); // crio a localização do professor (pos 0,0,0)
    this.setPoint(pos);
  }

  public String email(){
    String s1 = Integer.toString(this.getNumeroAluno());
    return s1 + "@ufp.edu.pt";
  }

  public void ver_notas(){
    System.out.println("Notas do " + "aluno: " +this.getNumeroAluno()+ " " +this.getNome());
    for (String d:this.getDisciplinas().keys()) {
      System.out.println(this.getDisciplinas().get(d).getNota() +" - "+ this.getDisciplinas().get(d).getNome());
    }
  }

  /**
   * Como cada aluno tem 1 turma, para saver o horario do aluno basta percorrer as turmas
   * @return o horario do aluno
   */
  public ArrayList<Horario> horario_aluno(){ // Retorna o horario do aluno
    ArrayList<Horario> h = new ArrayList<>();
    for (String t:this.getTurmas().keys()) {
      if(this.getTurmas().get(t).getHorario() != null){
        h.add(this.getTurmas().get(t).getHorario());
        //System.out.println("Inicio- " + this.getTurmas().get(t).getHorario().dataInicio + " Fim- " + this.getTurmas().get(t).getHorario().dataFim);
      }
    }
    if(h.size() >= 1){
     return h;
    }else {
      return null;
    }
  }

  /**
   * Todos os horarios livres para marcacao de atendimento, cruzar o horario do aluno (em que o aluno nao tem aulas ) com o horario de atendimento do professor
   */

  public void horarios_atendimento() { // O aluno tem o horario
    for (String t:this.getTurmas().keys()) { // percorro as turmas para chegar ao horario de atendimento dos professores
      if(this.getTurmas().get(t).getAlunos().contains(this.getNumeroAluno())){
        for (Horario_Atendimento hat:this.getTurmas().get(t).getProfessor().getHorario_atendimento()) { // percoro os horarios de atendimentos dos professores
          if(this.getTurmas().get(t).getHorario().getDataFim().beforeDate(hat.getDataInicio()) || this.getTurmas().get(t).getHorario().getDataInicio().afterDate(hat.getDataFim())){
            System.out.println("Professor: "+this.getTurmas().get(t).getProfessor().getNome()+" dia - "+ hat.getDataInicio().getDay() + "\nHora Inicio: " +hat.getDataInicio().getHour() + " h\nHora Fim: " + hat.getDataFim().getHour()+" h");
          }
        }
      }
    }
  }

  @Override
  public String toString() {
    return "Aluno/a:\n"+"Nome: " + this.getNome() + '\n' +
            "Apelido: " + this.getApelido()+ '\n' +
            "Data de nascimento: " + this.getDataNascimento().getDay()+"/"+this.getDataNascimento().getMonth()+"/"+this.getDataNascimento().getYear() + '\n' +
            "Idade: " +age() + "\n" +
            "Email: " + email() + '\n' +
            "Numero do Aluno: " + numeroAluno + '\n';
  }
}