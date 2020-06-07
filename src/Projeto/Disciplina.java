package Projeto;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.util.ArrayList;
public class Disciplina {

  private String nome;

  private int ects;

  private int semestre;

  private float nota;

  private ArrayList<Turma> turmas = new ArrayList<>();

  private SeparateChainingHashST<String,Professor> professores = new SeparateChainingHashST<>();

  public Disciplina(String nome, int ects, int semestre) {
    this.nome = nome;
    this.ects = ects;
    this.semestre = semestre;
  }

  @Override
  public String toString() {
    return this.nome;
  }

  public String getNome() {
    return nome;
  }

  public int getEcts() {
    return ects;
  }

  public int getSemestre() {
    return semestre;
  }

  public void setSemestre(int semestre) {
    this.semestre = semestre;
  }

  public SeparateChainingHashST<String, Professor> getProfessores() {
    return professores;
  }

  public float getNota() {
    return nota;
  }

  public void setNota(float nota) {
    this.nota = nota;
  }

  public ArrayList<Turma> getTurmas() {
    return turmas;
  }

  public void listarProfessores(){
    System.out.println("Professores da disciplina: " +this.getNome());
    for (String pi:this.getProfessores().keys()) {
      System.out.println(this.getProfessores().get(pi).getNome());
    }
  }
}