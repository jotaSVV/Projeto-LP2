package Projeto;
import java.util.ArrayList;

public class Horario_Atendimento extends Horario {

    private Data dataInicio;
    private Data dataFim;
    private int duracao;
    private ArrayList<Data> datas = new ArrayList<>();
    private Professor professores;
    public Professor getProfessores() {
        return professores;
    }
    private Horario horario;

    public Data getDataInicio() {
        return dataInicio;
    }

    public int getDuracao() {
        return duracao;
    }

    public Data getDataFim() {
        return dataFim;
    }

    public ArrayList<Data> getDatas() {
        return datas;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public void setProfessores(Professor professores) {
        this.professores = professores;
    }

    public Horario_Atendimento(Data dataInicio, int duracao) {
        Data d = new Data(dataInicio.getDay(),dataInicio.getYear(),dataInicio.getHour()+duracao,dataInicio.getMinute());
        this.dataInicio = dataInicio;
        this.dataFim = d;
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        String[] diasSemana = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado"};
        return diasSemana[dataInicio.getDay()]+" "+dataInicio.getHour() + ":"+dataInicio.getMinute()+"h - "+
                dataFim.getHour()+":" +dataFim.getMinute()+"h" ;
    }
}