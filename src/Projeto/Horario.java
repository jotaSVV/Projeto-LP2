package Projeto;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.util.ArrayList;
public class Horario {

  public Data dataInicio;

  public Data dataFim;

  private int ano;


  private ArrayList<Data> datas = new ArrayList<>();
  private SeparateChainingHashST<String,Turma> turmas = new SeparateChainingHashST<>();
  private ArrayList<Horario_Atendimento> horarios_atendimento = new ArrayList<>();

  public Data getDataInicio() {
    return dataInicio;
  }

  public Data getDataFim() {
    return dataFim;
  }

  public int getAno() {
    return ano;
  }

  public ArrayList<Data> getDatas() {
    return datas;
  }

  public SeparateChainingHashST<String, Turma> getTurmas() {
    return turmas;
  }

  public ArrayList<Horario_Atendimento> getHorarios_atendimento() {
    return horarios_atendimento;
  }

  public Horario(Data dataInicio, Data dataFim, int ano) {
    this.dataInicio = dataInicio;
    this.dataFim = dataFim;
    this.ano = ano;
  }
  public Horario() {
  }

  /**
   *
   * @param h1 horario1
   * @param h2 horario 2
   * verifica se existe alguma intersecao nos horarios
   * @return caso exista retorna true, senao false
   */
  public boolean intersection_hor(Horario h1,Horario h2){ // verifica se o horario 2 intersecta o horario 1
    if(h1.getDataInicio().afterHorario(h2.getDataInicio()) && h1.getDataInicio().beforeHorario(h2.getDataFim())) // se o horario de inicio do atendimento comecar durante a aula
    {
      return true;
      // se os horarios de inicio da aula e atendimento forem os mesmos
    }else if(h1.getDataInicio().compareHorario(h2.getDataInicio())==0){ // o horario de atendimento comecar quando a aula comeca
      return true;
      // Comeca antes da aula comecar e terminar depois da aula comecar
    }else if(h1.getDataInicio().beforeHorario(h2.getDataInicio()) && (h1.getDataFim().afterDate(h2.getDataInicio()))){ // se o horario de inicio do atendimento for antes da aula mas terminar a meio da aula ( comecar antes da aula e terminar ou durante a aulo ou apos a aula (caso tenha um horario de atendimento grande ) )
      return true;
    }
    return false;
  }

  @Override
  public String toString() {
    String[] diasSemana = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado"};
    return diasSemana[dataInicio.getDay()]+" "+dataInicio.getHour() + ":"+dataInicio.getMinute()+"h - "+
           dataFim.getHour()+":" +dataFim.getMinute()+"h" ;
  }
}