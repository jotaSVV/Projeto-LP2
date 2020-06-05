/**
 Pedro Pinheiro nº 36763
 João Silvestre nº 36431
**/

package Projeto.JavaFx;
import Projeto.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GraphCreator extends Application {
    public static SymbolDigraphWeighted graph_pdpSalas ;    //cria o symbol graph de pdp
    private static final double floorSize = 90;
    public static final Faculdade f1 = new Faculdade("Unisersidade Fernando Pessoa");;

    public static void main(String[] args) {
        // Datas
        Data d1 = new Data(7,10,1999);
        Data d2 = new Data(9,3,1997);
        Data d3 = new Data(14,4,1980);
        Data d4 = new Data(1,3,1971);
        Data d5 = new Data(22,6,2001);
        Data d6 = new Data(3,11,1956);

        // Datas para horarios
        Data dh1 = new Data(1,2020,15,30);
        Data dh2 = new Data(1,2020,17,30);

        Data dh3 = new Data(1,2020,15,30);
        Data dh4 = new Data(1,2020,17,30);

        Data dh5 = new Data(1,2020,10,30);
        Data dh6 = new Data(1,2020,12,30);

        Data dh7 = new Data(2,2020,13,30);
        Data dh8 = new Data(2,2020,15,30);

        // Horario de atendimento Professores
        Data dap1 = new Data(1,2020,16,0);
        Data dap2 = new Data(3,2020,18,0);

        // Horarios
        Horario h1 = new Horario(dh1,dh2,2020);
        Horario h2 = new Horario(dh3,dh4,2020);
        Horario h3 = new Horario(dh5,dh6,2020);

        // Horarios de atendimento dos Professores
        Horario_Atendimento hap1 = new Horario_Atendimento(dap1,2);
        Horario_Atendimento hap2 = new Horario_Atendimento(dap2,1);
//
//        // Alunos
//        Aluno a1 = new Aluno("Pedro","Pinheiro",d1,36763);
//        Aluno a2 = new Aluno("Catarina","Monteiro",d5,42467);
//        Aluno a3 = new Aluno("Joao","Silvestre",d2,36431);
//        // Professores
//        Professor p1 = new Professor("Ricardo","Almeida",d3);
//        Professor p2 = new Professor("Jose","Jose",d4);
//        Professor p3 = new Professor("Georgina","Castro",d4);
//        Professor p4 = new Professor("Filonema","Almeida",d6);
//        // Disciplinas
//        Disciplina dis1 = new Disciplina("LP2",7,2);
//        Disciplina dis2 = new Disciplina("BIO",5,1);
//        Disciplina dis3 = new Disciplina("SO",6,2);
//
//        // Turmas
//        Turma t1 = new Turma(2020,"ERT");
//        Turma t2 = new Turma(2020,"WRTH");
//        Turma t3 = new Turma(2020,"TYH");
//
//        // Cursos
//          Curso c1 = new Curso("INF");
//        Curso c2 = new Curso("CBL");
//
//
//        //Regista alunos na base de dados
//        f1.alunos.put(a1.getNumeroAluno(),a1);
//        f1.alunos.put(a2.getNumeroAluno(),a2);
//        f1.alunos.put(a3.getNumeroAluno(),a3);
//
//        //Regista cursos na base de dados
//        f1.cursos.put(c1.getNome(),c1);
//        f1.cursos.put(c2.getNome(),c2);
//
//        //Regista disciplinas na base de dados
//        f1.disciplinas.put(dis1.getNome(),dis1);
//        f1.disciplinas.put(dis2.getNome(),dis2);
//        f1.disciplinas.put(dis3.getNome(),dis3);
//        //Regista os professores na base de dados
//        f1.professores.put(p1.getEmail(),p1);
//        f1.professores.put(p2.getEmail(),p2);
//        f1.professores.put(p3.getEmail(),p3);
//        f1.professores.put(p4.getEmail(),p4);
//        //Regista as turmas na base de dados
//        f1.turmas.put(t1.getCodigo(),t1);
//        f1.turmas.put(t2.getCodigo(),t2);
//        f1.turmas.put(t3.getCodigo(),t3);
//        //Regista as salas na base de dados
//        //salas.put(s1.getCodigo(),s1);
//       // salas.put(s2.getCodigo(),s2);
//        //salas.put(s3.getCodigo(),s3);
//        //Regista os edificios na faculdade
//
//        // Regista os horarios
//
//        // Associações
//        // Curso ( Fazemos as associacoes da turma ao curso e aluno, professores e disciplina à turma)
//
//        // Faculdade
//        f1.addEdificio("Informatica",point1);
//        f1.addCurso(c1);
//        f1.addCurso(c2);
//
//        Sala s1 = f1.addSala(210,50,f1,"Informatica");
//        Sala s2 = f1.addSala(122,60,f1,"Informatica");
//        Sala s3 = f1.addSala(123,30,f1,"Informatica");
//
//        f1.salas.put(s1.getCodigo(),s1); // adicionar as salas a ST de salas ( BASE DE DADOS )
//        f1.salas.put(s2.getCodigo(),s2);
//        f1.salas.put(s3.getCodigo(),s3);
//
//        // CURSO 1
//        c1.addTurma(f1.turmas,t1);
//
//        c1.addProfessor(f1.professores,f1.turmas,p1,t1);
//        c1.addDisciplina(f1.disciplinas,f1.turmas,dis1,t1);
//        c1.associar_prof_disci(f1.professores,f1.disciplinas,p1,dis1);
//        c1.addAluno(f1.alunos,f1.turmas,a1,t1); // Tem que se adicionar o aluno depois da disciplina, pq nesta funcao fazemos a ligacao do aluno à disciplina tambem
//        c1.addAluno(f1.alunos,f1.turmas,a3,t1);
//
//        // CURSO 2
//        c2.addTurma(f1.turmas,t2);
//        c2.addTurma(f1.turmas,t3);
//
//        c2.addProfessor(f1.professores,f1.turmas,p2,t2); // adiciono professor 2 à turma 2
//        c2.addDisciplina(f1.disciplinas,f1.turmas,dis2,t2); // adiciono disciplina 2 à turma 2
//        c2.associar_prof_disci(f1.professores,f1.disciplinas,p2,dis2);
//        c2.addAluno(f1.alunos,f1.turmas,a2,t2); // adiciono aluno 2 à turma 2
//
//        c2.addDisciplina(f1.disciplinas,f1.turmas,dis3,t3); // associo disciplina 3 à turma 3
//        c2.addProfessor(f1.professores,f1.turmas,p3,t3); // adiciono professor 3 à turma 3
//        c2.associar_prof_disci(f1.professores,f1.disciplinas,p3,dis2);
//        // CURSO 3
//
//        // Turmas
//        // Adicionar horário às turmas
//        f1.gerarhorarios();
//        //t1.horario_aulas(h1,s1);
//        //t2.horario_aulas(h2,s2);
//        //t3.horario_aulas(h3,s3);
//
//        p1.addHorario_atendimento(hap1); // Adiciona os horarios de atendimento aos professores
//        p1.addHorario_atendimento(hap2);

//        // Prints
//        System.out.println(a1.toString());
//        System.out.println(a3.toString());
//        System.out.println(p1.toString());
//
//        System.out.println(a2.toString());
//        System.out.println(p2.toString());
//        System.out.println("\n");
//
//        // Testes de funcoes
//        p1.listasAlunos();
//
//        p1.lancar_notas(f1.alunos,a1,15);
//        p1.lancar_notas(f1.alunos,a3,15);
//        // Ver notas dos alunos
//        a1.ver_notas();
//        a3.ver_notas();
//
//        System.out.println("\n");
//
//        p2.listasAlunos();
//        p2.lancar_notas(f1.alunos,a2,18);
//        a2.ver_notas();
//
//        // imprimir todos os alunos de uma turma
//        System.out.println("\n");
//        t1.imprimeTodosAlunos();
//
//        // listar todos os professores de uma disciplina
//        System.out.println("\n");
//        dis1.listarProfessores();
//
//        // listar todas as turmas de um professor
//        System.out.println("\n");
//        p2.turmas_do_professor();
//
//        // Horario dos Alunos
//        System.out.println("\nHorario dos Alunos:");
//        System.out.println("Aluno: "+a1.getNome());
//        a1.horario_aluno();
//        System.out.println("Aluno: "+a2.getNome());
//        a2.horario_aluno();
//        System.out.println("Aluno: "+a3.getNome());
//        a3.horario_aluno();
//
//        // Horarios dos Professores
//        System.out.println("\nHoraios dos Professores:");
//        System.out.println("Professor: "+p1.getNome());
//        p1.horario_professor();
//        System.out.println("Professor: "+p2.getNome());
//        p2.horario_professor();
//        System.out.println("Professor: "+p3.getNome());
//        p3.horario_professor();
//        // Horario de Atendimento dos professores
//        System.out.println("\nHorario de atendimento dos Professores");
//        p1.horario_atendimento();
//

        //R6- CARREGAR DE TXT
        f1.carregaredificiosST();
        f1.carregarsalasST();
        f1.carregarPdpST();
        f1.carregarcursosST();
        f1.carregarTURMASST();
        f1.carregar_STPROFESSORES();
        f1.carregardisciplinasST();
        f1.carregar_STALUNOS();

        System.out.println("\nCursos da faculdade:");
        f1.listarCursos();

        System.out.println("\nTurmas da faculdade:");
        f1.listarTurmas();

        // R3.Devem existir funções para inserir, remover, editar e listar toda a informação, para
        // cada uma das várias STs consideradas na base de dados. -> tem todas as funcoes na classe faculdade aqui so metemos algumas como exemplo
//        System.out.println("\nCursos da faculdade:");
//        f1.listarCursos();
//        System.out.println("\nTurmas da faculdade:");
//        f1.listarTurmas();
//        System.out.println("\nDisciplinas:");
//        //f1.listarDisciplina();
//        f1.listardisciplinasST();
//        System.out.println("\nProfessores:");
//        //f1.listarProfessores(); // como percorro os professores de todas as turma e existe a professores que estao em + que uma turma vao aparecer professores repetidos
//        f1.listar_professoresST();
//        System.out.println("\nAlunos:");
//        //f1.listarAlunos(); // como percorro os alunos de todas as turma e existe a alunos que estao em + que uma turma vao aparecer alunos repetidos
//        f1.listar_alunosST();

        f1.gerarhorarios();
        f1.gerarHorAtendimentos();
        // Horario dos Alunos
        System.out.println("\nHorario dos Professores:");
        f1.horariosProfessores();
        System.out.println("\nHorario dos Alunos:");
        f1.horariosAlunos();
        System.out.println("\nHorarios de atendimento dos professores:");
        f1.horariosAtendimento();

        // R8 -
        //8- a) Todas as salas livres num determinado horário
        System.out.println("\n");
        f1.salas_livres(h2);
        System.out.println("\nListar salas:");
        f1.listarsalasST();

        //8- b) Todos os professores de uma unidade curricular
        System.out.println("\n");
        f1.disciplinas.get("SO").listarProfessores();

        //8- c) Todas as turmas de um professor
        System.out.println("\n");
        f1.professores.get("JoseJose@ufp.edu.pt").turmas_do_professor();

        //8- d) Todos os horários disponíveis para marcação de atendimento (cruzar informação de horário disponível do aluno com o horário de atendimento do professor)
        System.out.println("\nHorarios de atendimento que o aluno escolhido pode ir:");
        f1.alunos.get(36763).horarios_atendimento();

        //8- e) A ocupação de uma sala entre datas;
        System.out.println("\nVerificar se as salas se encontram ocupadas no horario que queremos verificar:");
        f1.sala_entre_datas(dh5,dh3,f1.salas.get(112)); // neste caso ve os horarios em que a sala esta ocupada entre o horaio dh5 ( dia 1 - 10h) e dh3 (dia 1 - 15h)

        // Status das salas
        System.out.println("\nStatus das salas: ");
        f1.dados_das_salas(f1.salas.get(112));

        //9- f) Pesquisas de salas por diferentes critérios (ocupação, número de tomadas, piso)
        System.out.println("\n");
        f1.pesquisar_sala_nTomadas(50);
        f1.pesquisar_sala_piso(1);
        f1.pesquisar_sala_ocupacao(0.01f); // numeros baixos pq temos poucos alunos registados, e muitas cadeiras nas salas

        // R5-
        try{
            f1.removerSalas(f1.salas.get(110));
        }catch (Exception e){
            System.out.println("Something went wrong.");
        }

        //R9- Metodo now
        f1.now();

        //R7- GUARDAR EM TXT
        f1.guardarsalasST();
        f1.guardarpdpST();
        f1.guardaredificiosST();
        f1.guardarcursosST();
        f1.guardar_turmasST();
        f1.guardar_STPROFESSORES();
        f1.guardardisciplinasST();
        f1.guardar_STALUNOS();

        // Listar os cursos da faculdade
//        Curso c3 = new Curso("TEST");
//        f1.cursos.put(c3.getNome(),c3);
//        f1.addCurso(c3); // É o curso test
//
//        System.out.println("\n");
//        f1.listarCursos();
//        // Apagar um curso
//        System.out.println("\n");
//        f1.removerCurso(c3);
//
//        System.out.println("\n");
//        f1.listarCursos();
//
//        Turma t4 = new Turma(2020,"TEST_TURMA");
//        f1.turmas.put(t4.getCodigo(),t4);
//        c1.addTurma(f1.turmas,t4);
//        f1.listarTurmas();
//
//        f1.removerTurma(t4);
//        f1.listarTurmas();

        // Adicionar as Localizações das salas
        f1.addPosiSala(100.0, floorSize * 1, f1.salas.get(109).getPiso(), f1.salas.get(109));
        f1.addPosiSala(200.0, floorSize * 1, f1.salas.get(111).getPiso(), f1.salas.get(111));
        f1.addPosiSala(300.0, floorSize * 1, f1.salas.get(112).getPiso(), f1.salas.get(112));
        f1.addPosiSala(400.0, floorSize * 1, f1.salas.get(113).getPiso(), f1.salas.get(113));
        f1.addPosiSala(500.0, floorSize * 1, f1.salas.get(114).getPiso(), f1.salas.get(114));
        f1.addPosiSala(600.0, floorSize * 2, f1.salas.get(220).getPiso(), f1.salas.get(220));
        f1.addPosiSala(500.0, floorSize * 2, f1.salas.get(221).getPiso(), f1.salas.get(221));
        f1.addPosiSala(400.0, floorSize * 2, f1.salas.get(222).getPiso(), f1.salas.get(222));
        f1.addPosiSala(300.0, floorSize * 2, f1.salas.get(223).getPiso(), f1.salas.get(223));
        f1.addPosiSala(200.0, floorSize * 2, f1.salas.get(224).getPiso(), f1.salas.get(224));
        f1.addPosiSala(100.0, floorSize * 3, f1.salas.get(330).getPiso(), f1.salas.get(330));
        f1.addPosiSala(200.0, floorSize * 3, f1.salas.get(331).getPiso(), f1.salas.get(331));
        f1.addPosiSala(300.0, floorSize * 3, f1.salas.get(332).getPiso(), f1.salas.get(332));
        f1.addPosiSala(400.0, floorSize * 3, f1.salas.get(333).getPiso(), f1.salas.get(333));
        f1.addPosiSala(500.0, floorSize * 3, f1.salas.get(334).getPiso(), f1.salas.get(334));

        // Adicionar as Localizações dos Pontos de Passagem
        f1.addPosiPdp(600,floorSize * 1,1,f1.pdp.get(1));
        f1.addPosiPdp(100,floorSize * 2,2,f1.pdp.get(2));
        f1.addPosiPdp(600,floorSize * 3,3,f1.pdp.get(4));
        f1.addPosiPdp(30,20,0,f1.pdp.get(3));

        // Entrada
        f1.addPosiPdp(40,floorSize * 1,1,f1.pdp.get(0));
        // Biblioteca
        f1.addPosiPdp(600,20,0,f1.pdp.get(5));
        // BAR
        f1.addPosiPdp(350,20,0,f1.pdp.get(6));

        // GRAFO DE SALAS
        Graph_project<Point> g = new Graph_project<>();                          //para aceder a classe

        // GRAFO PONTOS DE PASSAGEM E SALAS
        String pdpSalastxt = ".//data//salasPdp.txt";
        g.guardar_pdp_txt_graph(pdpSalastxt);
        graph_pdpSalas = new SymbolDigraphWeighted(pdpSalastxt,";");
        // CONECTAR SALAS ENTRE SI
        // 1 PISO
        g.conectGraphs(f1.salas.get(109),f1.salas.get(111),graph_pdpSalas,pdpSalastxt,10);
        g.conectGraphs(f1.salas.get(111),f1.salas.get(112),graph_pdpSalas,pdpSalastxt,10);
        g.conectGraphs(f1.salas.get(112),f1.salas.get(113),graph_pdpSalas,pdpSalastxt,10);
        g.conectGraphs(f1.salas.get(113),f1.salas.get(114),graph_pdpSalas,pdpSalastxt,10);
        // 2 PISO
        g.conectGraphs(f1.salas.get(220),f1.salas.get(221),graph_pdpSalas,pdpSalastxt,10);
        g.conectGraphs(f1.salas.get(221),f1.salas.get(222),graph_pdpSalas,pdpSalastxt,10);
        g.conectGraphs(f1.salas.get(222),f1.salas.get(223),graph_pdpSalas,pdpSalastxt,10);
        g.conectGraphs(f1.salas.get(223),f1.salas.get(224),graph_pdpSalas,pdpSalastxt,10);
        // 3 PISO
        g.conectGraphs(f1.salas.get(330),f1.salas.get(331),graph_pdpSalas,pdpSalastxt,10);
        g.conectGraphs(f1.salas.get(331),f1.salas.get(332),graph_pdpSalas,pdpSalastxt,10);
        g.conectGraphs(f1.salas.get(332),f1.salas.get(333),graph_pdpSalas,pdpSalastxt,10);
        g.conectGraphs(f1.salas.get(333),f1.salas.get(334),graph_pdpSalas,pdpSalastxt,10);
        // CONECTAR SALAS E PDP
        // ENTRADA
        g.conectGraphs(f1.pdp.get(0),f1.salas.get(109),graph_pdpSalas,pdpSalastxt,5); // entrada à 1 sala do piso 1
        // 1 PISO
        g.conectGraphs(f1.pdp.get(1),f1.salas.get(114),graph_pdpSalas,pdpSalastxt,15); // Ligaçao do piso 1 ao piso 2
        g.conectGraphs(f1.pdp.get(1),f1.salas.get(220),graph_pdpSalas,pdpSalastxt,25);
        // 2 PISO
        g.conectGraphs(f1.pdp.get(2),f1.salas.get(109),graph_pdpSalas,pdpSalastxt,25); // p2 ao piso 1
        g.conectGraphs(f1.pdp.get(2),f1.salas.get(224),graph_pdpSalas,pdpSalastxt,15); // p2 as escadas
        g.conectGraphs(f1.pdp.get(2),f1.salas.get(330),graph_pdpSalas,pdpSalastxt,25); // p2 ao piso 3
        // 3 PISO
        g.conectGraphs(f1.pdp.get(4),f1.salas.get(334),graph_pdpSalas,pdpSalastxt,15); // p3 as escadas
        g.conectGraphs(f1.pdp.get(4),f1.salas.get(220),graph_pdpSalas,pdpSalastxt,25); // p3 ao piso 2

        // CONECTAR PONTOS DE PASSAGEM COM PONTOS DE PASSAGEM
        g.conectGraphs(f1.pdp.get(0),f1.pdp.get(3),graph_pdpSalas,pdpSalastxt,15); // entrada as escadas do bar
        g.conectGraphs(f1.pdp.get(3),f1.pdp.get(6),graph_pdpSalas,pdpSalastxt,30); // escadas do bar ao bar
        g.conectGraphs(f1.pdp.get(6),f1.pdp.get(5),graph_pdpSalas,pdpSalastxt,30); // bar à biblioteca
        g.conectGraphs(f1.pdp.get(5),f1.pdp.get(1),graph_pdpSalas,pdpSalastxt,20); // biblioteca as escadas P1

        launch(args);
        f1.listarPdp();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("graph_creator.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Graph Creator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
