package Projeto;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.util.ArrayList;
public class Professor extends Pessoa {

    private SeparateChainingHashST<String,Turma> turmas = new SeparateChainingHashST<>();

    private SeparateChainingHashST<String,Disciplina> disciplinas = new SeparateChainingHashST<>();

    private ArrayList<Horario_Atendimento> horario_atendimento = new ArrayList<>();
    private ArrayList<Horario> horario_professor = new ArrayList<>();

    public Professor(String nome, String apelido, Data dataNascimento) {
      super(nome, apelido, dataNascimento);
      email();
    }

    public ArrayList<Horario_Atendimento> getHorario_atendimento() {
        return horario_atendimento;
    }

    public SeparateChainingHashST<String, Turma> getTurmas() {
        return turmas;
    }

    public SeparateChainingHashST<String, Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public String email(){
        this.setEmail(this.getNome()+this.getApelido()+"@ufp.edu.pt");
        Point pos = new Point(0,0,0,this.getNome()); // crio a localização do professor (pos 0,0,0)
        this.setPoint(pos);
        return this.getNome()+this.getApelido()+"@ufp.edu.pt";

     }

    /**
     *
     * @param alunos base de dados de alunos, para verificar se o aluno que mando esta ou nao na bd
     * @param a aluno
     * @param n nota
     * Percorre as turmas e os alunos para atribuir a nota à disciplina que o aluno tem associada
     */
    public void lancar_notas(RedBlackBST<Integer, Aluno> alunos,Aluno a, float n){ // Professor lanca as notas das respectivas disciplinas
        if(!alunos.contains(a.getNumeroAluno())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados (alunos)");
            return;
        }
        for (String ti:this.getTurmas().keys()) { // percorre as turmas até chegar à correta
            for (Integer ai:this.getTurmas().get(ti).getAlunos().keys()) {
                if(this.getTurmas().get(ti).getAlunos().get(ai).getNumeroAluno() == a.getNumeroAluno()){
                    this.getTurmas().get(ti).getDisciplina().setNota(n);
                }
            }
        }
    }

    public void listasAlunos(){
        System.out.println("Alunos do Professor " + this.getNome());
        for (String t:this.getTurmas().keys()) {
            for (Integer i:this.getTurmas().get(t).getAlunos().keys()) {
                System.out.println(this.getTurmas().get(t).getAlunos().get(i).getNome() + " " + this.getTurmas().get(t).getAlunos().get(i).getApelido() + " numero: " + this.getTurmas().get(t).getAlunos().get(i).getNumeroAluno()+"\n");
            }
        }
    }

    public void turmas_do_professor(){
        System.out.println("Turmas do Professor: " +this.getNome());
        for (String t:this.getTurmas().keys()) {
            System.out.println(this.getTurmas().get(t).getCodigo());
        }
    }

    public void addHorario_atendimento(Horario_Atendimento h){
        this.getHorario_atendimento().add(h); // associamos o horario de atendimento ao professor e vice-versa
        h.setProfessores(this);
    }

    public void horario_atendimento(){
        for (Horario_Atendimento h:this.getHorario_atendimento()) {
            System.out.println("Inicio- "+ h.getDataInicio()+" Fim- " + h.getDataFim() );
        }
    }

    /**
     * como o professor tem um array list de turmas e cada turma tem 1 horario para saber o horario do professor basta percorrer os horarios das turmas todas
     * @return o horario do professor
     */
    public ArrayList<Horario> horario_professor(){ // Retorna o horario do professor
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

    @Override
    public String toString() {
      return "Professor:\n"+ "Nome: " + this.getNome() + '\n' +
            "Apelido: " + this.getApelido()+ '\n' +
            "Data de nascimento: " + this.getDataNascimento().getDay()+"/"+this.getDataNascimento().getMonth()+"/"+this.getDataNascimento().getYear() + '\n' +
            "Idade: " +age() + "\n" +
            "Email: " + email() + '\n';
    }
}