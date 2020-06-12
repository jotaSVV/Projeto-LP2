package Projeto;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Faculdade {

    /// CAMINHOS

    private String alunostxt = (".//data//alunosST.txt");
    private String professorestxt = (".//data//professoresST.txt");
    private String turmastxt = (".//data//turmasST.txt");
    private String disciplinastxt = (".//data//disciplinasST.txt");
    private String cursostxt = (".//data//cursosST.txt");
    private String edificiostxt = (".//data//edificiosST.txt");
    private String salastxt = (".//data//salasST.txt");
    private String pdptxt = (".//data//pdpST.txt");

    private String name;
    public static SeparateChainingHashST<String, Curso> cursos = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<String, Edificio> edificios = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<String, Disciplina> disciplinas = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<String, Turma> turmas = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<Integer, Sala> salas = new SeparateChainingHashST<>();
    public static SeparateChainingHashST<Integer, PontosDePassagem> pdp = new SeparateChainingHashST<>();
    public static RedBlackBST<String, Professor> professores = new RedBlackBST<>();
    public static RedBlackBST<Integer, Aluno> alunos = new RedBlackBST<>();
    public static RedBlackBST<Data, Horario_Atendimento> horario_atendimento = new RedBlackBST<>();

    public Faculdade(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SeparateChainingHashST<String, Curso> getCursos() {
        return cursos;
    }

    public SeparateChainingHashST<String, Edificio> getEdificios() {
        return edificios;
    }

    /**
     *
     * @param c recebe o curso que queremos adicionar, verifica se este existe na bd e caso exista adiciono o curso à faculdade e vice-versa
     */
    public void addCurso(Curso c){
        if(!cursos.contains(c.getNome())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados ( cursos ) ");
            return;
        }
        this.getCursos().put(c.getNome(),c); // atribui-o o curso à faculdade e vice-versa
        c.setFaculdade(this);
    }

    /**
     *
     * @param0 h recebemos um horario, comparamos o horario com o horario das salas, se os horarios nao se "intersectarem" entao é pq a sala está livre neste horario
     */
    public void salas_livres(Horario h){
        String[] diasSemana = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado"};
        System.out.println("Salas livres no horário "+h.dataInicio.getHour()+":"+h.dataInicio.getMinute()+" - " +h.dataFim.getHour()+":"+h.dataFim.getMinute()+" - "+diasSemana[h.dataFim.getDay()] +"-feira");
        for (Integer si:salas.keys()) { // Percorremo a ST de salas
            if(salas.get(si).getHorarios().size() == 0){ // salas que nao têm aulas estao sempre livres
                System.out.println("Sala nº: " + salas.get(si).getCodigo()+ " piso " + salas.get(si).getPiso());
            }
            for (Horario hi : salas.get(si).getHorarios() ) { // Percorremos os horarios de cada sala
                if (!intersection_hor(hi,h)) { // se a aula for depois da hora
                    System.out.println("Sala nº: " + salas.get(si).getCodigo()+ " piso " + salas.get(si).getPiso());
                    break;
                }
            }
        }
    }

    /**
     *
     * @param d1 data 1
     * @param d2 data 2
     * @param s sala
     * No intervalo da data 1 e da data 2, imprimo os horarios em que a sala se encontra ocupada
     */
    public void sala_entre_datas(Data d1,Data d2,Sala s){
        if(!salas.contains(s.getCodigo())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        int i= 0;
        for (Horario h:s.getHorarios()) {
            if(h.getDataInicio().afterDate(d1) && h.getDataFim().beforeDate(d2)){
                i++;
                System.out.println("Sala ocupada no horario : " +h.getDataInicio().getHour()+":"+h.getDataInicio().getMinute()+" -> " +h.getDataFim().getHour()+":"+h.getDataFim().getMinute());
            }else if(h.getDataInicio().compareHorario(d1) == 0 && (h.getDataFim().beforeDate(d2) || h.dataFim.compareTo(d2) ==0)){ // se a data de inicio for igual (a d1) e a data de fim seja menor ou igual à d2
                i++;
                System.out.println("Sala ocupada no horario : " +h.getDataInicio().getHour()+":"+h.getDataInicio().getMinute()+" -> " +h.getDataFim().getHour()+":"+h.getDataFim().getMinute());
            }
        }
        if(i==0){
            System.out.println("A sala nao se enontra ocupada no horario que pretende");
        }
    }

    /**
     *
     * @param s recebe a sala e imprime os status da sala
     */
    public void dados_das_salas(Sala s){ // mostra os horarios em que a sala está ocupada
        String[] diasSemana = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado"};
        if(!salas.contains(s.getCodigo())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }

        System.out.println("Sala: "+s.getCodigo());
        System.out.println("Piso: "+s.getPiso());
        System.out.println("Nummero de tomadas: "+s.getNrTomadas());
        System.out.println("Nummero de cadeiras: "+s.getNrCadeiras());
        if(s.getHorarios().size() == 0){
            System.out.println("A sala nao esta a ser utilizada");
            return;
        }
        for (Horario h:s.getHorarios()) {
            System.out.println("A sala encontra-se ocupada "+diasSemana[h.getDataFim().getDay()] + " entre: " +h.getDataInicio().getHour()+"h : " + h.getDataFim().getHour()+" h");
        }
    }

    /**
     *
     * @param n funcoes de pesquisar salas por diferentes critérios
     */
    public void pesquisar_sala_nTomadas(int n){
        System.out.println("Salas com mais de " + n + " tomadas");
        for (Integer si:salas.keys()) {
            if(salas.get(si).getNrTomadas() >= n){
                System.out.println("Sala nº "+ salas.get(si).getCodigo() + " contem mais que " +n+" tomadas");
            }
        }

    }
    public void pesquisar_sala_piso(int n){
        System.out.println("Salas no " + n +" piso:");
        for (Integer si:salas.keys()) {
            if(salas.get(si).getPiso() == n){
                System.out.println("Sala nº "+ salas.get(si).getCodigo() + " esta no piso " +n);
            }
        }
    }
    public void pesquisar_sala_ocupacao(float n){ // taxa de ocupacao das salas tem de ser superior a n
        System.out.println("Salas com taxa de ocupacao superior a " + n);
        for (Integer si:salas.keys()) {
            for (Turma ti:salas.get(si).getTurmas()) { // percorro as turmas de cada sala para verificar os alunos que estao na sala
                float t = (float) ( ti.getAlunos().size() )/ (salas.get(si).getNrCadeiras() ); // numero de alunos a dividir pelo numero de lugares na sala
                if (t >= n) {
                    System.out.println("Sala nº " + salas.get(si).getCodigo() + " tem uma taxa de ocupacao superior a " + n +" (" + t +")");
                    break;
                }
            }
        }
    }

    /**
     *
     * @param n nome do edificio que queremos criar
     * @param p para ja nao é utilizado
     *  Cria um edificio e associa à faculdade
     */
    public void addEdificio(String n, Point p){ // para ja o Point nao é utilizado
        Edificio e = new Edificio(n,this);
        this.getEdificios().put(e.getNome(),e);
        edificios.put(e.getNome(),e);
    }

    /**
     *
     * @param codigo
     * @param nrCadeiras
     * @param f
     * @param nome
     *  Recebe os status da sala, cria um sala e associa a sala ao edificio e vice-versa
     * @return sala que adicionamos à st
     */
    public Sala addSala(int codigo, int nrCadeiras,Faculdade f,String nome){
        Sala s = new Sala(codigo,nrCadeiras); // associo a sala ao edificio e vice-versa
        f.getEdificios().get(nome).getSalas().add(s);
        s.addEdificio(this.getEdificios().get(nome));
        salas.put(s.getCodigo(),s);
        return s;
    }

    // Funcao que verifica se existe intersecao entre os horario, isto é se o horario 2

    /**
     *
     * @param h1
     * @param h2
     * Recebe 2 horarios e verifica se existe intersecao entre os 2 horarios
     * @return verdadeiro se os horarios se intersectarem e falso caso nao exista intersecao
     */
    public boolean intersection_hor(Horario h1,Horario h2){ // verifica se o horario 2 intersecta o horario 1
        if(h1.getDataInicio().afterHorario(h2.getDataInicio()) && h1.getDataInicio().beforeHorario(h2.getDataFim())) // se o horario 1 comecar entre o horario 2
        {
            return true;
        }
        if(h1.getDataInicio().compareHorario(h2.getDataInicio())==0){ // se h1 comecar ao mesmo tempo que h2
            return true;
        }
        if(h1.getDataInicio().beforeHorario(h2.getDataInicio()) && (h1.getDataFim().afterHorario(h2.getDataInicio()))){ // Se h1 comecar antes de h2 e terminar
            return true;
        }
        return false;
    }

    /**
     *
     * @param horarios array de horarios, para por exemplo adicionar o horario de atendimento, temos de ver se o horario de atendimento nao "intersecta" nenhum horario de aulas do professor
     * @param h horario a verificar se é ou nao compativel com o array
     * @return
     */
    public boolean intersectArrayHorHor(ArrayList<Horario>horarios,Horario h){
        for (Horario hi:horarios) {
            if(intersection_hor(hi,h)){
                return false; // caso haja intersecao dos horios nao é possivel adicionar o horario entao retorna false
            }
        }
        return true; // horario pode ser adicionado
    }

    /**
     * Funcoes de gerar horarios aleatórios automáticamente
     * @param h1 horario
     * @param t turma
     * Executa esta funcao quando é o primeiro horario de cada turma ( ou seja na 1 vez que adicionamos um horario a cada turma )
     * Como é o primeiro horario das turmas e do professor dessa turmas, nao temos de fazer nenhuma verificacao nessas classes
     * apenas percorremos as salas e verificamos se elas estao ocupadas ou nao nesse horario
     * @return true caso tenha adicionado com sucesso o horario à turma e falso caso nao tenha adicionado
     */
    public boolean inserir_1_horario(Horario h1, Turma t){
        if(t.getProfessor().horario_professor() == null){ // se a turma nao tiver horarios
            for (String e:this.getEdificios().keys()) { // Percorro os edificios para chegar as salas
                for (Sala s : this.getEdificios().get(e).getSalas()) { // Percorremos as salas
                    if(s.getHorarios().size() == 0){ // se a sala nao tiver horarios adicionamos
                        t.horario_aulas(h1,s);
                        return true;
                    }else { // se ja tiver horarios, verifico se sao compativeis com o horarios gerado
                        for (Horario hii:s.getHorarios()) {
                            if(!intersection_hor(hii,h1)){ // percorro os horarios das salas para ver se a sala é compativel com o horario
                                t.horario_aulas(h1,s);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Funcoes de gerar horarios aleatórios
     * @param h1 horario
     * @param t turma
     * Para adicionar o horario à turma verificamos:
     * 1- se o horario do professor da turma é compatível com o horario que geramos aleatóriamente
     * 2- percorremos as salas e vamos encontrar uma que esteja disponível no horario que geramos
     * @return true caso consiga adicionar o horario à turma e false caso nao consiga
     */
    public boolean inserir_horarios(Horario h1, Turma t){
        if(intersectArrayHorHor(t.getProfessor().horario_professor(), h1)){ // verifico se o horario do professor é compativel com o h1
            //for (Integer ai : t.getAlunos().keys()) { // Percorremos os aluno
            // for (Horario hi : t.getAlunos().get(ai).horario_aluno()) { // percorro os horarios dos alunos
            //  if (!intersection_hor(hi, h1)) { // Verificamos se o horario do aluno é compativel com o horario
            for (String e : this.getEdificios().keys()) { // Percorro os edificios para chegar as salas
                for (Sala s : this.getEdificios().get(e).getSalas()) { // Percorremos as salas
                    if (s.getHorarios().size() == 0) { // se a sala nao tiver horarios adicionamos
                        t.horario_aulas(h1, s);
                        //System.out.println("sala- " +s.getCodigo() +" horario- " +h1.getDataInicio() + " " +h1.getDataFim());
                        return true;
                    } else { // se ja tiver horarios, verifico se sao compativeis com o horarios gerado
                        for (Horario hii : s.getHorarios()) {
                            if (!intersection_hor(hii, h1)) { // percorro os horarios das salas para ver se a sala é compativel com o horario
                                t.horario_aulas(h1, s);
                                //System.out.println("sala- " +s.getCodigo() +" horario- " +h1.getDataInicio() + " " +h1.getDataFim());
                                return true;
                            }
                        }
                    }
                }
            }
            // }
            //}
            // }
        }
        return false;
    }

    /**
     * Funcoes de gerar horarios aleatórios
     * Geramos um horario aleatório, percorremos os cursos e as turmas e verificamos se é possível adicionar o horario, caso nao seja geramos um novo horario
     */
    public void gerarhorarios(){
        Random gerador = new Random();
        int dia_semana = gerador.nextInt(4) + 1; // inteiro que representa o dia da semana da aula
        int hora_aula = gerador.nextInt(12) + 8; // inteiro que representa a hora em que comeca a aula
        int duracao = gerador.nextInt(1) +1; // as aulas podem ter 1 ou 2 horas
        Data d1 = new Data(dia_semana,2020,hora_aula,0);// tentar melhorar isto
        Data d2 = new Data(dia_semana,2020,hora_aula + duracao,0); // tentar melhorar isto
        Horario h1 = new Horario(d1,d2,2020);
        boolean horario = false;
        // Antes de tudo temos de ver se cada turma já tem 1 horario criado, caso nao tenha, temos de criar o 1 horario
        for (String ci:this.getCursos().keys()) { // Percorremos os cursos
            for (String ti:this.getCursos().get(ci).getTurmas().keys()) { // Percorremos as turmas de cada curso
                do{ // Só passa para o curso seguinte quando todas as turma tiverem os horario colocados
                    if(! inserir_1_horario(h1,this.getCursos().get(ci).getTurmas().get(ti))){ // verifico se a turma ja tem ou nao horarios, caso nao tenha adiciona
                        // 1 geramos um numero aleatorio para o dia e depois para a hora da aula
                        // 2 ciclo enquanto as verificacoes nao estiverem corretas (disponibilidades) permanece no ciclo
                        // 3 Verificar se os horarios das turmas estao vazios, caso estejam pode ser um horario qualquer, nao precisa fazer as verificacoes
                        // 4 caso nao estejam vazios temos de percorrer os horarios dos professores e comparar com o horario e o mesmo com as salas e de seguida adicionar o horario
                        if(inserir_horarios(h1,this.getCursos().get(ci).getTurmas().get(ti))){
                            horario = true;
                        }
                    }else { // Quando adcionamos o horario pela 1 vez em uma turma
                        horario = true;
                    }
                    // VOLTO A GERAR UM HORARIO ALETORIO PARA AS OUTRAS TURMAS
                    gerador = new Random();
                    dia_semana = gerador.nextInt(4) + 1; // inteiro que representa o dia da semana da aula
                    hora_aula = gerador.nextInt(12) + 8; // inteiro que representa a hora em que comeca a aula
                    duracao = gerador.nextInt(1) +1; // as aulas podem ter 1 ou 2 horas
                    d1 = new Data(dia_semana,2020,hora_aula,0);// tentar melhorar isto
                    d2 = new Data(dia_semana,2020,hora_aula + duracao,0); // tentar melhorar isto
                    h1 = new Horario(d1,d2,2020);
                }while (! horario);
                horario = false;
            }
        }
    }

    /**
     * Funcoes de gerar horarios de atendimento
     * @param p professor
     * @param h1 horarios
     * Verificamos quais as salas disponíveis nos horarios que queremos marcar o atendimento do professor
     * @return true caso tenha adicionado corretamente o horario ou false caso as salas nao estejam disponiveis ( caso isso aconteca geramos um novo horario e voltamos a verificar )
     */
    public boolean inserirHorAtendimento(Professor p,Horario_Atendimento h1){
        for (String e : this.getEdificios().keys()) { // Percorro os edificios para chegar as salas
            for (Sala s : this.getEdificios().get(e).getSalas()) { // Percorremos as salas
                if (s.getHorarios().size() == 0) { // se a sala nao tiver horarios adicionamos
                    p.addHorario_atendimento(h1);
                    h1.setProfessores(p);
                    return true;
                } else { // se ja tiver horarios, verifico se sao compativeis com o horarios gerado
                    for (Horario hii : s.getHorarios()) {
                        if (!intersection_hor(hii, h1)) { // percorro os horarios das salas para ver se a sala é compativel com o horario
                            p.addHorario_atendimento(h1);
                            h1.setProfessores(p);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Funcoes de gerar horarios atendimento
     * Geramos um horario aleatório, percorremos os professores e verificamos se o horario do professor é compativel com o que geramos ( funcao intersectArrayHorHor)
     * se for compativel fazemos o mesmo com as salas (inserirHorAtendimento) caso sejam ambos compatíveis adiciona o horario ao professor
     */
    public void gerarHorAtendimentos(){
        Random gerador = new Random();
        int dia_semana = gerador.nextInt(4) + 1; // inteiro que representa o dia da semana da aula
        int hora_aula = gerador.nextInt(12) + 8; // inteiro que representa a hora em que comeca a aula
        int duracao = gerador.nextInt(1) +1; // as aulas podem ter 1 ou 2 horas
        Data d1 = new Data(dia_semana,2020,hora_aula,0);// tentar melhorar isto
        Horario_Atendimento h1 = new Horario_Atendimento(d1,duracao);
        boolean horario = false;
        for (String pi:professores.keys()) { // percorro os professores
            do { // Só passa para o curso seguinte quando todas os professores tiverem os horario colocados
                if(intersectArrayHorHor(professores.get(pi).horario_professor(), h1)){
                    if(inserirHorAtendimento(professores.get(pi),h1)){
                        horario = true; // correu tudo bem e adicionou corretamente
                    }else {
                        horario = false; // pq nenhum horario da sala estava disponivel para o horario de atendimento dos profs por isso temos de gerar um novo horario
                    }
                }
                // VOLTO A GERAR UM HORARIO ALETORIO PARA AS OUTRAS TURMAS
                gerador = new Random();
                dia_semana = gerador.nextInt(4) + 1; // inteiro que representa o dia da semana da aula
                hora_aula = gerador.nextInt(12) + 8; // inteiro que representa a hora em que comeca a aula
                duracao = gerador.nextInt(1) +1; // as aulas podem ter 1 ou 2 horas
                d1 = new Data(dia_semana,2020,hora_aula,0);// tentar melhorar isto
                h1 = new Horario_Atendimento(d1,duracao);

            }while (! horario);
            horario = false;
        }
    }

    public void horariosProfessores(){
        for (String p:professores.keys()) {
            System.out.println("Professor: " +professores.get(p).getNome());
            for (Horario h:professores.get(p).horario_professor()) {
                System.out.println(h.toString());
            }
        }
    }

    public void horariosAlunos(){
        for (String ti:turmas.keys()) {
            System.out.println("Turma: "+ turmas.get(ti).getCodigo());
            for (Integer ai:turmas.get(ti).getAlunos().keys()) {
                System.out.println(alunos.get(ai).getNome());
                System.out.println(turmas.get(ti).getHorario().toString() + "  "+"dis: "+turmas.get(ti).getDisciplina().getNome()+" professor: "+turmas.get(ti).getProfessor().getNome() +" sala: " +turmas.get(ti).getSala().getCodigo());
            }
        }
    }

    public void horariosAtendimento(){
        for (String p:professores.keys()) {
            System.out.println("Professor: " + professores.get(p).getNome());
            for (Horario_Atendimento h:professores.get(p).getHorario_atendimento()) {
                System.out.println(h.toString());
            }
        }
    }

    public void listarCursos(){
        System.out.println("Listar todas os cursos da Faculdade: "+this.getName());
        for (String c:this.getCursos().keys()) {
            System.out.println(this.getCursos().get(c).getNome());
        }
    }



    public void editarCurso(SeparateChainingHashST<String,Curso> cursos, Curso c, String nome){
        if(!cursos.contains(c.getNome())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        cursos.get(c.getNome()).setNome(nome);

    }

    public void guardarCursoHistorico (Curso c) throws IOException {
        FileWriter myWriter = new FileWriter(".//data//cursosHistorico.txt",true);

        myWriter.write("Curso:\n");
        myWriter.write(c.getNome() + ";" + c.getFaculdade().getName() + "\n");
        myWriter.write("TURMAS:\n");
        for (String codigo : c.getTurmas().keys()) {
            Turma t = c.getTurmas().get(codigo);
            myWriter.write(t.getAno() + ";" + t.getCodigo() + ";" + t.getCurso().getNome() + ";" + t.getDisciplina().getNome() + ";" + t.getSala().getCodigo() + ";" + "\n");
        }
        myWriter.write("\n");


        myWriter.close();
    }
    public void removerCurso(Curso c) throws IOException {
        if(!cursos.contains(c.getNome())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        // verificar se os cursos tem alunos associados, caso tenham nao podemos remover o curso, e a turma e tenho de apagar a associacao da sala com a turma
        for (String t:c.getTurmas().keys()) {
            if(c.getTurmas().get(t).getAlunos().size() >=1 ){ // caso tenha alunos retorno sem remover o curso
                System.out.println("Nao posso remover o curso porque temos alunos nas turmas do curso");
                return;
            }else if(c.getTurmas().get(t).getProfessor() != null){ // caso tenha professores retorno sem remover o curso
                System.out.println("Nao posso remover o curso porque temos professores nas turmas do curso");
                return;
            }
        }
        guardarCursoHistorico(c);
        // Tenho de apagar as sala associadas às turmas
        // Elimino as turmas
        for (String t:c.getTurmas().keys()) {
            c.getTurmas().get(t).setSala(null);
            c.getTurmas().delete(t);
        }
        // Removo o curso
        this.getCursos().delete(c.getNome());

        cursos.delete(c.getNome()); // removo o curso da bd
    }

    public void listarTurmas(){
        System.out.println("Listar todas as turmas da Faculdade: "+this.getName());
        for (String c:this.getCursos().keys()) {
            for (String t:this.getCursos().get(c).getTurmas().keys()) {
                System.out.println(this.getCursos().get(c).getTurmas().get(t).getCodigo());
            }
        }
    }

    public void editarTurma(SeparateChainingHashST<String,Turma> turmas , Turma t, Professor p, Sala s, Disciplina d,Horario h){ // podemos mudar o professor, a sala, a disciplina e o horario
        if(!turmas.contains(t.getCodigo())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }

        t.getProfessor().getTurmas().delete(t.getCodigo()); // tiramos a conexao do professor à turma
        turmas.get(t.getCodigo()).setProfessor(p); // alteramos o professor da turma

        t.getSala().getTurmas().remove(t); // tiramos a conexao da sala com a turma
        turmas.get(t.getCodigo()).setSala(s);


        t.getDisciplina().getTurmas().remove(t); // tiramos a conexao da disciplina com a turma
        turmas.get(t.getCodigo()).setDisciplina(d);


        t.getHorario().getTurmas().delete(t.getCodigo()); // tiramos a conexao dos horarios com a turma
        turmas.get(t.getCodigo()).setHorario(h);


    }

    public void guardarTurmaHistorico (Turma t) throws IOException {
        FileWriter myWriter = new FileWriter(".//data//turmasHistorico.txt",true);

        myWriter.write("TURMA:\n");
        myWriter.write(t.getAno() + ";" + t.getCodigo() + ";" + t.getCurso().getNome() + ";" + t.getDisciplina().getNome() + ";" + t.getSala().getCodigo() + ";" + "\n");
        myWriter.write("PROFESSOR:\n");
        myWriter.write(t.getProfessor().getNome() + ";" + t.getProfessor().getApelido() + ";" + t.getProfessor().getDataNascimento().getDay() + "/" + t.getProfessor().getDataNascimento().getMonth() + "/" + t.getProfessor().getDataNascimento().getYear() + ";" + t.getProfessor().age() + ";" + t.getProfessor().getEmail() + "\n");
        myWriter.write("ALUNOS:\n");
        for (int codigo : t.getAlunos().keys()
        ) {
            Aluno a = t.getAlunos().get(codigo);
            myWriter.write(a.getNome() + ";" + a.getApelido() + ";" + a.getDataNascimento().getDay() + "/" + a.getDataNascimento().getMonth() + "/" + a.getDataNascimento().getYear() + ";" + a.age() + ";" + a.getNumeroAluno() + "@ufp.edu.pt" + ";" + a.getNumeroAluno() + "\n");

        }
        myWriter.write("\n");


        myWriter.close();

    }

    public void removerTurma(Turma t) throws IOException {
        if(!turmas.contains(t.getCodigo())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        if(t.getAlunos().size() >=1 ){ // caso tenha alunos retorno sem remover a turma
            System.out.println("Nao posso remover o curso porque temos alunos nas turmas do curso");
            return;
        }else if(t.getProfessor() != null){ // caso tenha professores retorno sem remover a turma
            System.out.println("Nao posso remover o curso porque temos professores nas turmas do curso");
            return;
        }
        guardarTurmaHistorico(t);
        t.setSala(null);
        this.getCursos().get(t.getCurso().getNome()).getTurmas().delete(t.getCodigo());

        turmas.delete(t.getCodigo()); // removemos a turma da BD
    }

    public void listarDisciplina(){
        System.out.println("Listar todas as diciplinas da Faculdade: "+this.getName());
        for (String c:this.getCursos().keys()) {
            for (String t:this.getCursos().get(c).getTurmas().keys()) {
                System.out.println(this.getCursos().get(c).getTurmas().get(t).getDisciplina().getNome());
            }
        }
    }

    public void  editarDisciplina(SeparateChainingHashST<String,Disciplina> disciplinas, Disciplina d,Integer semestre){
        if(disciplinas.contains(d.getNome())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        disciplinas.get(d.getNome()).setSemestre(semestre);

    }

    public void guardarDisciplinaHistorico(Disciplina d) throws IOException {
        FileWriter myWriter = new FileWriter(".//data//disciplinasHistorico.txt",true);

        myWriter.write("Disciplina:\n");
        myWriter.write(d.getNome() + ";" + d.getEcts() + ";" + d.getSemestre() + "\n");
        myWriter.write("PROFESSORES:\n");
        for (String codigo : d.getProfessores().keys()
        ) {
            Professor p = d.getProfessores().get(codigo);
            myWriter.write(p.getNome() + ";" + p.getApelido() + ";" + p.getDataNascimento().getDay() + "/" + p.getDataNascimento().getMonth() + "/" + p.getDataNascimento().getYear() + ";" + p.age() + ";" + p.getEmail() + "\n");
        }
        int i = 0;
        myWriter.write("TURMA:\n");
        while (i < d.getTurmas().size()) {
            Turma t = d.getTurmas().get(i);
            myWriter.write(t.getAno() + ";" + t.getCodigo() + ";" + t.getCurso().getNome() + ";" + t.getDisciplina().getNome() + ";" + t.getSala().getCodigo() + ";" + "\n");
            i++;
        }
        myWriter.write("\n");

        myWriter.close();
    }

    public void removerDisciplina(Disciplina d) throws IOException {
        if(!disciplinas.contains(d.getNome())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        guardarDisciplinaHistorico(d);
        // percorremos os professores para quebrar a ligacao com a disciplina
        for (String c:this.getCursos().keys()) {
            for (String t:this.getCursos().get(c).getTurmas().keys()) {
                if(this.getCursos().get(c).getTurmas().get(t).getDisciplina() == d){
                    this.getCursos().get(c).getTurmas().get(t).getProfessor().getDisciplinas().delete(d.getNome()); // removemos os professores das disciplinas e vice-versa
                    d.getProfessores().delete(this.getCursos().get(c).getTurmas().get(t).getProfessor().getNome());
                }
                this.getCursos().get(c).getTurmas().get(t).setDisciplina(null); // removemos as disciplinas dos cursos
                d.getTurmas().remove(this.getCursos().get(c).getTurmas().get(t)); // removemos os cursos das disciplinas
            }
        }

        disciplinas.delete(d.getNome());// removemos a disciplina da BD


        // percorremos os professores para quebrar a ligacao com a disciplina
        for (String c:this.getCursos().keys()) {
            for (String t:this.getCursos().get(c).getTurmas().keys()) {
                if(this.getCursos().get(c).getTurmas().get(t).getDisciplina() == d){
                    this.getCursos().get(c).getTurmas().get(t).getProfessor().getDisciplinas().delete(d.getNome()); // removemos os professores das disciplinas e vice-versa
                    d.getProfessores().delete(this.getCursos().get(c).getTurmas().get(t).getProfessor().getNome());
                }
                this.getCursos().get(c).getTurmas().get(t).setDisciplina(null); // removemos as disciplinas dos cursos
                d.getTurmas().remove(this.getCursos().get(c).getTurmas().get(t)); // removemos os cursos das disciplinas
            }
        }
        disciplinas.delete(d.getNome());// removemos a disciplina da BD
    }

    public void listarProfessores(){
        System.out.println("Listar todas os professores da Faculdade: "+this.getName());
        for (String c:this.getCursos().keys()) {
            for (String t:this.getCursos().get(c).getTurmas().keys()) {
                //    if(this.getCursos().get(c).getTurmas().get(t).getProfessor() != null){
                System.out.println(this.getCursos().get(c).getTurmas().get(t).getProfessor().getNome()+ " "+this.getCursos().get(c).getTurmas().get(t).getCodigo());
                //  }
            }
        }
    }
    /**
     * Função de editar alunos
     * Vemos se o aluno a editar existe na ST , caso exista editamos diretamente na st os campos enviados por parametro
     *
     */


    public void editarAluno(Aluno a, String nome, String apelido){
        if(!alunos.contains(a.getNumeroAluno())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        alunos.get(a.getNumeroAluno()).setNome(nome);
        alunos.get(a.getNumeroAluno()).setApelido(apelido);

    }
    /**
     * Função de guardar aluno em txt recebido por parametro
     * Função usada para guardar um aluno no txt historico, criamos um ficheiro
     *
     */
    public void guardarAlunoHistorico(Aluno a) throws IOException {
        FileWriter myWriter = new FileWriter(".//data//alunosHistorico.txt",true);
        myWriter.write("ALUNO:\n");

        myWriter.write(a.getNome() + ";" + a.getApelido() + ";" + a.getDataNascimento().getDay() + "/" + a.getDataNascimento().getMonth() + "/" + a.getDataNascimento().getYear() + ";" + a.age() + ";" + a.getNumeroAluno() + "@ufp.edu.pt" + ";" + a.getNumeroAluno() + "\n");
        myWriter.write("TURMAS:\n");
        for (String c : a.getTurmas().keys()
        ) {
            Turma t = a.getTurmas().get(c);
            myWriter.write(t.getAno() + ";" + t.getCodigo() + ";" + t.getCurso().getNome() + ";" + t.getDisciplina().getNome() + ";" + t.getSala().getCodigo() + ";" + "\n");
        }
        myWriter.write("\n");

        myWriter.close();

    }
    /**
     * Função de remover aluno
     * Vemos se o aluno a remover existe na ST , caso exista ele é guardado num txt e removida da st
     *
     */


    public void removerAluno(Aluno a) throws Exceptions, IOException {

        if(!alunos.contains(a.getNumeroAluno())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        guardarAlunoHistorico(a);
        for (String c:this.getCursos().keys()) {
            for (String t:this.getCursos().get(c).getTurmas().keys()) {
                if(this.getCursos().get(c).getTurmas().get(t).getAlunos().get(a.getNumeroAluno()) != null){ // se a turma tiver o aluno que pretendemos eliminar
                    a.getDisciplinas().delete(a.getTurmas().get(t).getDisciplina().getNome()); // elimino a disciplina da st de disciplinas do aluno
                    this.getCursos().get(c).getTurmas().get(t).getAlunos().delete(a.getNumeroAluno()); // eliminamos o aluno da turma e vice-versa
                    a.getTurmas().delete(this.getCursos().get(c).getTurmas().get(t).getCodigo());
                    if(this.getCursos().get(c).getTurmas().get(t).getAlunos().size() == 0){
                        throw new Exceptions("Atencao, a turma "+this.getCursos().get(c).getTurmas().get(t).getCodigo() + " encontram-se sem alunos");
                    }
                }
            }
        }

        alunos.delete(a.getNumeroAluno()); // removemos o aluno da BD
    }

    public void listarAlunos(){
        System.out.println("Listar todas os alunos da Faculdade: "+this.getName());
        for (String c:this.getCursos().keys()) {
            for (String t:this.getCursos().get(c).getTurmas().keys()) {
                for (Integer al:this.getCursos().get(c).getTurmas().get(t).getAlunos().keys()) {
                    System.out.println(this.getCursos().get(c).getTurmas().get(t).getAlunos().get(al).getNome());
                }
            }
        }
    }

    public void editarProfessor(RedBlackBST<String, Professor> professores, Professor p,String nome,String apelido){
        if(!professores.contains(p.getNome())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        professores.get(p.getEmail()).setNome(nome);
        professores.get(p.getEmail()).setApelido(apelido);
    }

    public void guardarProfessorHistorico(Professor p) throws IOException {
        FileWriter myWriter = new FileWriter(".//data//professorHistorico.txt",true);
        myWriter.write("PROFESSOR:\n");
        myWriter.write(p.getNome() + ";" + p.getApelido() + ";" + p.getDataNascimento().getDay() + "/" + p.getDataNascimento().getMonth() + "/" + p.getDataNascimento().getYear() + ";" + p.age() + ";" + p.getEmail() + "\n");
        myWriter.write("TURMAS:\n");
        for (String c : p.getTurmas().keys()
        ) {
            Turma t = p.getTurmas().get(c);
            myWriter.write(t.getAno() + ";" + t.getCodigo() + ";" + t.getCurso().getNome() + ";" + t.getDisciplina().getNome() + ";" + t.getSala().getCodigo() + ";" + "\n");
        }
        myWriter.write("\n");
        myWriter.close();
    }

    public void removerProfessor(Professor p) throws Exceptions, IOException {
        if(!professores.contains(p.getNome())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        guardarProfessorHistorico(p);
        for (String c:this.getCursos().keys()) {
            for (String t:this.getCursos().get(c).getTurmas().keys()) {
                if(this.getCursos().get(c).getTurmas().get(t).getProfessor() != null){ // se a turma tiver o professor que pretendemos eliminar
                    if(this.getCursos().get(c).getTurmas().get(t).getAlunos().size() > 0){
                        throw new Exceptions("Os aluno da turma "+this.getCursos().get(c).getTurmas().get(t).getCodigo() + " encontram-se sem professor");
                    }
                    p.getDisciplinas().delete(p.getTurmas().get(t).getDisciplina().getNome()); // elimino a disciplina da st de disciplinas do professor e vice-versa
                    this.getCursos().get(c).getTurmas().get(t).getDisciplina().getProfessores().delete(p.getEmail()); // elimino o professor da disciplina
                    this.getCursos().get(c).getTurmas().get(t).setProfessor(null); // elimino o professor da turma e vice-versa
                    p.getTurmas().delete(this.getCursos().get(c).getTurmas().get(t).getCodigo());

                }
            }
        }

        professores.delete(p.getEmail()); // removemos o professor da BD
    }

    public void editarSala(SeparateChainingHashST<Integer,Sala> salas, Sala s,Edificio e, Integer cadeiras, Integer tomadas, Integer piso){
        if(!salas.contains(s.getCodigo())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }
        salas.get(s.getCodigo()).setEdificio(e);
        salas.get(s.getCodigo()).setNrCadeiras(cadeiras);
        salas.get(s.getCodigo()).setNrTomadas(tomadas);
        salas.get(s.getCodigo()).setPiso(piso);
    }

    public void guardarSalaHistorico(Sala s) throws IOException {
        FileWriter myWriter = new FileWriter(".//data//salasHistorico.txt",true);
        myWriter.write("Sala:\n");
        myWriter.write(s.getCodigo() + ";" + s.getPiso() + ";" + s.getNrTomadas() + ";" + s.getNrCadeiras() + ";" + s.getEdificio().getNome() + "\n");
        myWriter.write("TURMAS:\n");
        int i = 0;
        while(i < s.getTurmas().size()){
            Turma t = s.getTurmas().get(i);
            myWriter.write(t.getCodigo() + "\n");
            i++;
        }

        myWriter.write("\n");

        myWriter.close();
    }

    public void removerSalas(Sala s) throws IOException {
        Data d = new Data(); // data de hoje com hora e minuto (com tudo ) para ver se a esta hora posso eliminar a sala ou nao ( se tiver aulas nao posso eliminar, se nao tiverem aulas posso eliminar )
        Data d2 = new Data(); // so para fazer o horario
        Horario h = new Horario(d,d2,d.getYear());
        // 1 temos de verificar se a sala existe na base de dados
        // 2 ver se estao a decorrer aulas nesta hora
        // 3 caso nao estejam a decorrer eliminamos a sala
        // 4 ver se a sala tem aulas a decorrer no horario das turmas
        // 5 caso alguma turma esteja a ultilizar esta sala, temos de gerar um novo horario para a turma
        if(!salas.contains(s.getCodigo())){
            System.out.println("Impossivel, alguns dos dados nao estao registados nas bases de dados");
            return;
        }

        for (Horario hi:s.getHorarios()) {
            if(h.intersection_hor(h,hi)){
                System.out.println("Impossível apagar sala porque estao a decorrer aulas neste momento na sala");
                return;
            }
        }
        guardarSalaHistorico(s);
        // removo a sala da bd e tiro as ligacoes das salas com edificios e horarios
        for (String e:this.getEdificios().keys()) {
            if(this.getEdificios().get(e).getSalas().remove(s)){ // se o edificio tiver a sala e remover com successo, tenho de remover o edificio da sala tb
                s.setEdificio(null); // removo o edificio da sala
            }
        }

        salas.delete(s.getCodigo());
        // caso a sala eliminada tenha aulas temos de gerar novos horarios, para isso vamos aos cursos e verificamos se os cursos possuem a sala
        for (String c:this.getCursos().keys()) {
            for (String t:this.getCursos().get(c).getTurmas().keys()) {
                if(this.getCursos().get(c).getTurmas().get(t).getSala() == s){
                    gerarhorarios(); // como algumas turmas estavam a usar a sala que foi eliminada temos de gerar novos horarios
                }
            }
        }
    }

    public void removePdp(PontosDePassagem p){
        if(!pdp.contains(p.getCod())){
            System.out.println("Erro pdp nao esta na base de dados");
            return;
        }
        p.getEdificio().getPdp().remove(p); // remover o pdp do edificio e vice-versa
        p.setE(null);
        pdp.delete(p.getCod());
    }

    public void guardar_STALUNOS() {
        try {
            FileWriter myWriter = new FileWriter(".//data//alunosST.txt");
            for (int cod : alunos.keys()
            ) {
                myWriter.write("ALUNO:\n");
                Aluno a = alunos.get(cod);
                myWriter.write(a.getNome() + ";" + a.getApelido() + ";" + a.getDataNascimento().getDay() + "/" + a.getDataNascimento().getMonth() + "/" + a.getDataNascimento().getYear() + ";" + a.age() + ";" + a.getNumeroAluno() + "@ufp.edu.pt" + ";" + a.getNumeroAluno() + "\n");
                myWriter.write("TURMAS:\n");
                for (String c : a.getTurmas().keys()) {
                    Turma t = a.getTurmas().get(c);
                    myWriter.write(t.getAno() + ";" + t.getCodigo() + ";" + t.getCurso().getNome() + ";" + t.getDisciplina().getNome() + ";" + t.getSala().getCodigo() + ";" + "\n");
                }
                myWriter.write("POSICAO(XYZ):\n");
                myWriter.write(a.getX() + ";" + a.getY() + ";" + a.getZ() + ";" + "\n");
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void carregar_STALUNOS() {
        edu.princeton.cs.algs4.In in = new edu.princeton.cs.algs4.In(alunostxt);
        while (!in.isEmpty()) {
            if (in.readLine().compareTo("ALUNO:") == 0) {
                String line = in.readLine();
                String[] fields = line.split(";");
                String[] aux = fields[2].split("/");
                Data d1 = new Data(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2]));
                Aluno a = new Aluno(fields[0], fields[1], d1, Integer.parseInt(fields[5]));
                a.setEmail(fields[4]);
                if (in.readLine().compareTo("TURMAS:") == 0) {
                    String aux2 = in.readLine();
                    while((aux2.compareTo("POSICAO(XYZ):") != 0)){
                        String[] turmainfo = aux2.split(";");
                        for (String tur : this.turmas.keys()) {
                            Turma t = this.turmas.get(tur);
                            if (t.getCodigo().compareTo(turmainfo[1]) == 0) {
                                a.getTurmas().put(t.getCodigo(), t); // associamos o aluno à turma e vice-versa
                                t.getAlunos().put(a.getNumeroAluno(),a);
                                for (String disc : this.disciplinas.keys()) {
                                    Disciplina d = this.disciplinas.get(disc);
                                    if (d.getNome().compareTo(turmainfo[3]) == 0) {
                                        a.getDisciplinas().put(d.getNome(), d); // associamos a disciplina ao aluno
                                    }
                                }
                            }
                        }
                        aux2 = in.readLine();
                    }
                    // ADICIONAR AS COORDENADAS NOS ALUNOS
                    String aux3 = in.readLine();
                    String[] posix = aux3.split(";");
                    a.addCoords(Double.parseDouble(posix[0]),Double.parseDouble(posix[1]),Integer.parseInt(posix[2]));
                }
                alunos.put(a.getNumeroAluno(), a);
            }
        }
    }

    public void listar_alunosST() {
        for (int c : this.alunos.keys()) {
            Aluno a = this.alunos.get(c);
            System.out.println(a.getNome());
        }
    }

    public void guardar_STPROFESSORES() {

        try {
            FileWriter myWriter = new FileWriter(".//data//professoresST.txt");

            for (String cod : professores.keys()
            ) {
                myWriter.write("PROFESSOR:\n");
                Professor p = professores.get(cod);
                myWriter.write(p.getNome() + ";" + p.getApelido() + ";" + p.getDataNascimento().getDay() + "/" + p.getDataNascimento().getMonth() + "/" + p.getDataNascimento().getYear() + ";" + p.age() + ";" + p.getEmail() + "\n");
                myWriter.write("TURMAS:\n");
                for (String c : p.getTurmas().keys()
                ) {
                    Turma t = p.getTurmas().get(c);
                    myWriter.write(t.getAno() + ";" + t.getCodigo() + ";" + t.getCurso().getNome() + ";" + t.getDisciplina().getNome() + ";" + t.getSala().getCodigo() + ";" + "\n");
                }
                myWriter.write("POSICAO(XYZ):\n");
                myWriter.write(p.getX() + ";" + p.getY() + ";" + p.getZ() + ";" + "\n");
                myWriter.write("\n");
            }

            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void carregar_STPROFESSORES() {
        edu.princeton.cs.algs4.In in = new edu.princeton.cs.algs4.In(professorestxt);
        String linhaturma = null;
        while (!in.isEmpty()) {
            if (in.readLine().compareTo("PROFESSOR:") == 0) {
                String line = in.readLine();
                String[] fields = line.split(";");
                String[] aux = fields[2].split("/");
                Data d1 = new Data(Integer.parseInt(aux[0]), Integer.parseInt(aux[1]), Integer.parseInt(aux[2]));
                Professor p = new Professor(fields[0], fields[1], d1);
                p.setEmail(fields[4]);
                if (in.readLine().compareTo("TURMA:") != 0) {
                    linhaturma = in.readLine();
                    while(linhaturma.compareTo("POSICAO(XYZ):")!=0){
                        String[] turmainfo = linhaturma.split(";");
                        if (turmas.contains(turmainfo[1])) {
                            Turma t = turmas.get(turmainfo[1]);
                            t.setProfessor(p);
                            p.getTurmas().put(t.getCodigo(), t); // associo professor à turma
                        }
                        linhaturma = in.readLine();
                    }
                    // ADICIONAR AS COORDENADAS NOS ALUNOS
                    String aux3 = in.readLine();
                    String[] posix = aux3.split(";");
                    p.addCoords(Double.parseDouble(posix[0]),Double.parseDouble(posix[1]),Integer.parseInt(posix[2]));
                }
                professores.put(p.getEmail(), p);
            }
        }
    }

    public void listar_professoresST() {

        for (String c : this.professores.keys()
        ) {
            Professor p = this.professores.get(c);
            System.out.println(p.getNome());

        }
    }

    public void guardar_turmasST() {
        try {
            FileWriter myWriter = new FileWriter(".//data//turmasST.txt");

            for (String cod : turmas.keys()
            ) {
                Turma t = turmas.get(cod);
                myWriter.write("TURMA:\n");
                myWriter.write(t.getAno() + ";" + t.getCodigo() + ";" + t.getCurso().getNome() + ";" + t.getDisciplina().getNome() + ";" + t.getSala().getCodigo() + ";" + "\n");
                myWriter.write("PROFESSOR:\n");
                myWriter.write(t.getProfessor().getNome() + ";" + t.getProfessor().getApelido() + ";" + t.getProfessor().getDataNascimento().getDay() + "/" + t.getProfessor().getDataNascimento().getMonth() + "/" + t.getProfessor().getDataNascimento().getYear() + ";" + t.getProfessor().age() + ";" + t.getProfessor().getEmail() + "\n");
                myWriter.write("ALUNOS:\n");
                for (int codigo : t.getAlunos().keys()
                ) {
                    Aluno a = t.getAlunos().get(codigo);
                    myWriter.write(a.getNome() + ";" + a.getApelido() + ";" + a.getDataNascimento().getDay() + "/" + a.getDataNascimento().getMonth() + "/" + a.getDataNascimento().getYear() + ";" + a.age() + ";" + a.getNumeroAluno() + "@ufp.edu.pt" + ";" + a.getNumeroAluno() + "\n");

                }
                myWriter.write("\n");
            }

            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void carregarTURMASST() {
        edu.princeton.cs.algs4.In in = new edu.princeton.cs.algs4.In(turmastxt);
        while (!in.isEmpty()) {
            if (in.readLine().compareTo("TURMA:") == 0) {
                String line = in.readLine();
                String[] fields = line.split(";");
                Turma t = new Turma(Integer.parseInt(fields[0]), fields[1]);
                if (this.cursos.contains(fields[2])) {
                    Curso c = this.cursos.get(fields[2]);
                    this.turmas.put(t.getCodigo(), t); // adicionamos a turma à bd de turmas da faculdade
                    c.addTurma(turmas,t); // associo a turma ao curso e vice-versa
                }
            }
        }
    }

    public void listarTURMASST() {
        for (String c : this.turmas.keys()
        ) {
            Turma t = this.turmas.get(c);
            System.out.println("Turma:" + t.getCodigo() + "\nCurso:" + t.getCurso().getNome() + "\nDisciplina:" + t.getDisciplina().getNome() + "\nSala:" + t.getSala().getCodigo());
            System.out.println("\nProfessor:" + t.getProfessor().toString());
            System.out.println("\nAlunos:");
            for (int cod : t.getAlunos().keys()
            ) {
                Aluno a = t.getAlunos().get(cod);
                System.out.println(a.toString());
            }
        }
    }

    public void guardardisciplinasST() {
        try {
            FileWriter myWriter = new FileWriter(".//data//disciplinasST.txt");

            for (String cod : disciplinas.keys()
            ) {
                Disciplina d = disciplinas.get(cod);
                myWriter.write("Disciplina:\n");
                myWriter.write(d.getNome() + ";" + d.getEcts() + ";" + d.getSemestre() + "\n");
                myWriter.write("PROFESSORES:\n");
                for (String codigo : d.getProfessores().keys()
                ) {
                    Professor p = d.getProfessores().get(codigo);
                    myWriter.write(p.getNome() + ";" + p.getApelido() + ";" + p.getDataNascimento().getDay() + "/" + p.getDataNascimento().getMonth() + "/" + p.getDataNascimento().getYear() + ";" + p.age() + ";" + p.getEmail() + "\n");
                }
                int i = 0;
                myWriter.write("TURMA:\n");
                while (i < d.getTurmas().size()) {
                    Turma t = d.getTurmas().get(i);
                    myWriter.write(t.getAno() + ";" + t.getCodigo() + ";" + t.getCurso().getNome() + ";" + t.getDisciplina().getNome() + ";" + t.getSala().getCodigo() + ";" + "\n");
                    i++;
                }
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    public void carregardisciplinasST() {
        edu.princeton.cs.algs4.In in = new edu.princeton.cs.algs4.In(disciplinastxt);
        String aux = null;
        while (!in.isEmpty()) {
            if (in.readLine().compareTo("Disciplina:") == 0) {
                String line = in.readLine();
                String[] fields = line.split(";");
                Disciplina d = new Disciplina(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
                if (in.readLine().compareTo("PROFESSORES:") == 0) {
                    aux = in.readLine();
                    while (aux.compareTo("TURMA:") != 0) {
                        fields = aux.split(";");
                        if (professores.contains(fields[4])) {
                            Professor p = professores.get(fields[4]);
                            d.getProfessores().put(p.getEmail(), p); // associamos a disciplina ao professor e vice-versa
                            p.getDisciplinas().put(d.getNome(),d);
                        }
                        aux = in.readLine();
                    }
                }
                aux = in.readLine();
                while(aux.compareTo("")!=0){
                    String[] fieldsaux = aux.split(";");
                    if(turmas.contains(fieldsaux[1])){
                        Turma t = turmas.get(fieldsaux[1]);
                        d.getTurmas().add(t); // associamos a disciplina com a turma e vice-versa
                        t.setDisciplina(d);
                    }
                    aux = in.readLine();
                }
                disciplinas.put(d.getNome(), d);
            }
        }
    }

    public void listardisciplinasST(){
        for (String c: disciplinas.keys()) {
            Disciplina d = disciplinas.get(c);
            System.out.println("Disciplina: " + d.getNome() + " - Ects:" + d.getEcts() + " - Semestre:" + d.getSemestre());
        }
    }

    public void guardarcursosST(){
        try {
            FileWriter myWriter = new FileWriter(".//data//cursosST.txt");

            for (String cod : cursos.keys()
            ) {
                Curso c = cursos.get(cod);
                myWriter.write("Curso:\n");
                myWriter.write(c.getNome() + ";" + c.getFaculdade().getName() + "\n");
                myWriter.write("TURMAS:\n");
                for (String codigo : c.getTurmas().keys()) {
                    Turma t = c.getTurmas().get(codigo);
                    myWriter.write(t.getAno() + ";" + t.getCodigo() + ";" + t.getCurso().getNome() + ";" + t.getDisciplina().getNome() + ";" + t.getSala().getCodigo() + ";" + "\n");
                }
                myWriter.write("\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void carregarcursosST(){
        edu.princeton.cs.algs4.In in = new edu.princeton.cs.algs4.In(cursostxt);
        String aux = null;
        while (!in.isEmpty()) {
            if (in.readLine().compareTo("Curso:") == 0) {
                String line = in.readLine();
                String[] fields = line.split(";");
                Curso c = new Curso(fields[0]);
                Faculdade f = new Faculdade(this.name);
                c.setFaculdade(f); // associamos o curso com a faculdade e vice-versa
                f.getCursos().put(c.getNome(),c);
                cursos.put(c.getNome(), c);
            }
        }
    }

    public void listarcursosST(){
        for (String c: cursos.keys()
        ) {
            Curso cur = cursos.get(c);
            System.out.println(cur.getNome());
        }
    }

    public void guardaredificiosST(){
        try {
            FileWriter myWriter = new FileWriter(".//data//edificiosST.txt");

            for (String cod : edificios.keys()
            ) {
                Edificio e = edificios.get(cod);
                myWriter.write("Edificio:\n");
                myWriter.write(e.getNome() + ";" + e.getFaculdade().getName() + "\n");
                myWriter.write("SALAS:\n");
                int i = 0;
                while(i < e.getSalas().size()){
                    Sala s = e.getSalas().get(i);
                    myWriter.write(s.getCodigo() + ";" + s.getPiso() + ";" + s.getNrCadeiras() + ";" + s.getNrTomadas() + "\n");
                    i++;
                }

                myWriter.write("\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }




    public void carregaredificiosST(){
        edu.princeton.cs.algs4.In in = new edu.princeton.cs.algs4.In(edificiostxt);
        String aux = null;
        while (!in.isEmpty()) {

            if (in.readLine().compareTo("Edificio:") == 0) {
                String line = in.readLine();
                String[] fields = line.split(";");
                Faculdade f = new Faculdade(fields[1]);
                Edificio e = new Edificio(fields[0],f);
                f.getEdificios().put(e.getNome(),e); // associo os edificios à faculdade e vice-versa
                e.setFaculdade(f);
                edificios.put(e.getNome(), e);
            }
        }
    }

    public void listaredificiosST(){
        for (String c:edificios.keys()
        ) {

            Edificio e = edificios.get(c);
            System.out.println(e.getNome());
        }
    }

    public void guardarsalasST(){
        try {
            FileWriter myWriter = new FileWriter(".//data//salasST.txt");

            for (int cod : salas.keys()
            ) {
                Sala s = salas.get(cod);
                myWriter.write("Sala:\n");
                myWriter.write(s.getCodigo() + ";" + s.getPiso() + ";" + s.getNrTomadas() + ";" + s.getNrCadeiras() + ";" + s.getEdificio().getNome() + "\n");
                myWriter.write("TURMAS:\n");
                int i = 0;
                while(i < s.getTurmas().size()){
                    Turma t = s.getTurmas().get(i);
                    myWriter.write(t.getCodigo() + "\n");
                    i++;
                }
                myWriter.write("\n");
            }

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void guardarpdpST(){
        try {
            FileWriter myWriter = new FileWriter(".//data//pdpST.txt");
            for (int cod : pdp.keys()) {
                PontosDePassagem p = pdp.get(cod);
                myWriter.write("Pdp:\n");
                myWriter.write(p.getCod() + ";" + p.getName() +";" + p.getEdificio().getNome() + "\n");
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void carregarsalasST(){
        edu.princeton.cs.algs4.In in = new edu.princeton.cs.algs4.In(salastxt);
        while (!in.isEmpty()) {
            if (in.readLine().compareTo("Sala:") == 0) {
                String line = in.readLine();
                String[] fields = line.split(";");
                Sala s = new Sala(Integer.parseInt(fields[0]),Integer.parseInt(fields[3]));
                if(edificios.contains(fields[4])){
                    Edificio e = edificios.get(fields[4]);
                    s.addEdificio(e); // associo os edificios com as salas e vice-versa
                    e.getSalas().add(s);
                }
                salas.put(s.getCodigo(), s);
            }
        }
    }

    public void carregarPdpST(){
        edu.princeton.cs.algs4.In in = new edu.princeton.cs.algs4.In(pdptxt);
        while (!in.isEmpty()) {
            if (in.readLine().compareTo("Pdp:") == 0) {
                String line = in.readLine();
                String[] fields = line.split(";");
                PontosDePassagem p = new PontosDePassagem(Integer.parseInt(fields[0]),0,0,0,fields[1]);
                if(edificios.contains(fields[2])){
                    p.addEdificio(edificios.get(fields[2]));
                    edificios.get(fields[2]).getPdp().add(p);
                }
                pdp.put(p.getCod(),p);
            }
        }
    }

    public void listarPdp(){
        System.out.println("Pontos de Passagem: ");
        for (Integer pi:pdp.keys()) {
            System.out.println(pdp.get(pi).getName());
        }
    }

    public void listarsalasST(){
        for (int cod:salas.keys()
        ) {
            Sala s = salas.get(cod);
            System.out.println(s.getCodigo());
        }
    }

    /**
     *
     * @param d data do momento que corro o codigo, verifico as salas que estao ocupadas e calculo a % de salas ocupadas neste momento
     */
    public void percOcupacaoSalas(Data d){
        int salasOcupadas = 0;
        Horario horario = new Horario(d,d,d.getYear()); // horario do momento que corro o codigo
        for (Integer s:salas.keys()) {
            for (Horario hs:salas.get(s).getHorarios()) {
                if(intersection_hor(hs,horario)){
                    salasOcupadas++;
                }
            }
        }
        if(salasOcupadas == 0){
            System.out.println("De momento todas as salas encontram-se livres");
        }else{
            int aux = salasOcupadas / salas.size();
            System.out.println("A taxa de ocupação das salas neste momento é de " + aux+ "%");
        }
    }

    /**
     *
     * @param d data do momento que corro o codigo, verifico ucs a serem lecionadas neste momento e os professores que as lecionam
     */
    public void ucsLecionadas(Data d){
        Horario horario = new Horario(d,d,d.getYear()); // horario do momento que corro o codigo
        System.out.println("Turmas atualmente em aulas:");
        for (String t:turmas.keys()) { // percorro as turmas
            if(intersection_hor(turmas.get(t).getHorario(),horario)){ // verifico se o horario da turma "intersecta" a hora em que corro o programa, se sim e como as disciplinas, alunos e professores tem o mesmo horario com a turma, podemos ver quem esta a ter aulas neste momento
                System.out.println(turmas.get(t).getCodigo()+" Disciplina: " +turmas.get(t).getDisciplina().getNome()+ " Professor:"+turmas.get(t).getProfessor().getNome());
            }else { // se o professor nao estiver a dar aula entao está "disponivel"
                System.out.println("Professor " + turmas.get(t).getProfessor().getNome() +" está disponivel");
            }
        }
    }
    @Override
    public String toString() {
        return this.getName();
    }

    public void now(){
        Data d = new Data();
        percOcupacaoSalas(d);
        ucsLecionadas(d);
    }

    public void addPosiSala(double x, double y, int z,Sala s){
        if(!salas.contains(s.getCodigo())){
            System.out.println("Erro, sala nao existe!!!");
            return;
        }
        salas.get(s.getCodigo()).setX(x);
        salas.get(s.getCodigo()).setY(y);
        salas.get(s.getCodigo()).setZ(z);
        salas.get(s.getCodigo()).setName("sala "+String.valueOf(s.getCodigo()));
    }

    public void addPosiPdp(double x, double y, int z,PontosDePassagem p){
        if(!pdp.contains(p.getCod())){
            System.out.println("Erro, sala nao existe!!!");
            return;
        }
        pdp.get(p.getCod()).setX(x);
        pdp.get(p.getCod()).setY(y);
        pdp.get(p.getCod()).setZ(z);
        pdp.get(p.getCod()).setName(p.getName());
    }

}