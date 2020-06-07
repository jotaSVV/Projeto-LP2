package Projeto;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.RedBlackBST;
public class Curso {
    private String nome;
    private SeparateChainingHashST<String,Turma> turmas = new SeparateChainingHashST<>();
    private Faculdade faculdade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Faculdade getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(Faculdade faculdade) {
        this.faculdade = faculdade;
    }

    public SeparateChainingHashST<String, Turma> getTurmas() {
        return turmas;
    }

    public Curso(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    /**
     *
     * @param professores bd de professores
     * @param turmas bd turmas
     * @param p professor
     * @param t turma
     *  verifico se o professor e a turma existem na bd, caso existam associo o professor à turma e vice-versa
     */
    public void addProfessor(RedBlackBST<String, Professor> professores, SeparateChainingHashST<String,Turma> turmas, Professor p, Turma t){ // CORRIGIR NAO POSSO DEIXAR ADICIONAR MAIS QUE 1 PROFESSOR A MESMA TURMA
        if(!professores.contains(p.getEmail()) || !turmas.contains(t.getCodigo())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados ( professores, turma) ");
            return;
        }
          this.turmas.get(t.getCodigo()).setProfessor(p); // associo turma a professor
          p.getTurmas().put(t.getCodigo(),t); // associo professor à turma
    }

    /**
     *
     * @param turmas vd de turmas
     * @param t turma
     * Verificamos se a turma existe na bd, caso exista, vamos adicionar a turma e associar a turma ao curso
     */
    public void addTurma(SeparateChainingHashST<String,Turma> turmas,Turma t){
        if(!turmas.contains(t.getCodigo())) {
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados ( turmas )");
            return;
        }
        for (String ti:this.turmas.keys()){ // verificar se a turma já existe
            if(this.turmas.get(ti).getCodigo().compareTo(t.getCodigo())== 0){
                System.out.println("Imposível criar porque turma já existe");
                return;
            }
        }
        this.turmas.put(t.getCodigo(),t); // adiciono o curso à turma
        t.setCurso(this); // associo a turma ao curto e vice-versa
    }

    /**
     *
     * @param alunos bd de alunos
     * @param turmas bd de turmas
     * @param a aluno
     * @param t turma
     * Verificamos se o aluno e turma existem na bd, de seguida adicionamos o aluno à turma e à disciplina que a turma tem associada
     */
    public void addAluno(RedBlackBST<Integer, Aluno> alunos,SeparateChainingHashST<String,Turma> turmas,Aluno a, Turma t){
        if(!alunos.contains(a.getNumeroAluno()) || !turmas.contains(t.getCodigo())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados ( alunos, turmas) ");
            return;
        }

        for (String ti:this.turmas.keys()){ // verificar se a turma já existe
            if(this.turmas.get(ti).getCodigo().compareTo(t.getCodigo())== 0){
                this.turmas.get(ti).getAlunos().put(a.getNumeroAluno(),a); // adiciono a turma ao aluno
                a.getTurmas().put(t.getCodigo(),t); // adiciono o aluno à turma
                a.getDisciplinas().put(t.getDisciplina().getNome(),t.getDisciplina()); // associo o aluno à disciplina que a turma tem associada
            }
        }
    }

    /**
     *
     * @param disciplinas bd de disciplinas
     * @param turmas bd de turmas
     * @param d disciplina
     * @param t turmas
     * Verifica se d e t estao na bd, e de seguida adiciona a disciplina à turma e vice-versa
     */
    public void addDisciplina(SeparateChainingHashST<String,Disciplina> disciplinas, SeparateChainingHashST<String,Turma> turmas,Disciplina d, Turma t){ // CORRIGIR NAO POSSO DEIXAR ADICIONAR MAIS QUE 1 DISCIPLINA A MESMA TURMA
        if(!disciplinas.contains(d.getNome()) || !turmas.contains(t.getCodigo())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        for (String ti:this.turmas.keys()){ // verificar se a turma já existe
            if(this.turmas.get(ti).getCodigo().compareTo(t.getCodigo())== 0){
                this.turmas.get(ti).setDisciplina(d);
                d.getTurmas().add(t);
            }
        }
    }

    /**
     *
     * @param professores bd de professores
     * @param disciplinas bd de disciplinas
     * @param p professor
     * @param d disciplina
     *  Associa o professor à disciplina e vice-versa
     */
    public void associar_prof_disci(RedBlackBST<String, Professor> professores,SeparateChainingHashST<String,Disciplina> disciplinas,Professor p,Disciplina d){
        if(!disciplinas.contains(d.getNome()) || !professores.contains(p.getEmail()) ){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados (professores, disciplinas)");
            return;
        }
        p.getDisciplinas().put(d.getNome(),d);
        d.getProfessores().put(p.getEmail(),p);
    }

}
