package Projeto.JavaFx;

import Projeto.*;
import edu.princeton.cs.algs4.In;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Projeto.Faculdade.*;
import static Projeto.JavaFx.GraphCreator.f1;
import static Projeto.JavaFx.GraphCreator.graph_pdpSalas;
public class GraphCreatorFXMLController implements Initializable {


    public Pane graphPane;
    private static final int radius = 25;
    public HBox addSearchBox12;
    public TextField bipartiteTextField;
    public HBox addSearchBox121;
    public TextField printConexo;

    // ADD GRAFOS
    public ComboBox<String> salasComboBox;
    public ComboBox<String> alunosComboBox;
    public ComboBox<String> comboSala;
    public ComboBox<String> comboPdp;
    public TextField pesosPdp; ///////////////////
    public TextField pesosPdpSalas;////////////////////
    public TextField pesosSalas;///////////////////
    public ComboBox<String> comboPdpPdp;
    public ComboBox<String> comboPdpPdp1;
    public ComboBox<String> comboSalaSala;
    public ComboBox<String> comboSalaSala1;
    public TextField resultDistance;
    /// para colocar a distancia de sala para sala
    public TableColumn<Object, Integer> distToSalas;
    public TableColumn dadosAluno;
    public Button saidaEmergencia;
    public TextField resultSaidaEmergencia;
    public ComboBox<String> selectAlunoSEmergencia;
    public ListView<Sala> listViewAddGraph;
    public Pane graphPane1;
    public ListView<String> listViewAddGraph1;
    public static SymbolDigraphWeighted subGraphSalasPdp ;    // cria o symbol graph de pdp
    ///////////////////////////////////////////////////
    public ComboBox<String> selectGraph1;
    ///////////////////////////////////////////////////
    private String pdpSalastxt = ".//data//salasPdp.txt";
    private String pdpSalasBinFile = ".//data//SalasPdpBinFile.bin";
    private String subGraphTxt = ".//data//subGraph.txt";

    Graph_project<Point> gi = new Graph_project<>();
    /////////////////////////////////////////////////



    /*
    TABELA DOS PONTOS DE PASSAGEM
     */
    @FXML
    private javafx.scene.control.TableView<PontosDePassagem> TabelaPdps;
    @FXML
    private TableColumn<PontosDePassagem,String> nomePDP;
    @FXML
    private TableColumn<PontosDePassagem,Integer> xPDP;
    @FXML
    private TableColumn<PontosDePassagem,Integer> yPDP;
    @FXML
    private TableColumn<PontosDePassagem,Integer> zPDP;
    @FXML
    private TableColumn<PontosDePassagem,Integer> codPDP;
    @FXML
    private TableColumn<PontosDePassagem, String> edificioPDP;
    @FXML
    private Button listarPDPS;
    @FXML
    private Button adicionarPDPS;
    @FXML
    private Button removerPDPS;
    @FXML
    private ComboBox<Projeto.Edificio> edificiosPDPcombo;
    @FXML
    private TextField coddoPDP;
    @FXML
    private TextField nomedoPDP;
    @FXML
    private TextField xdoPDP;
    @FXML
    private TextField ydoPDP;
    @FXML
    private TextField zdoPDP;

    /*
    TABELA DAS SALAS
     */
    @FXML
    private javafx.scene.control.TableView<Sala> TabelaSalas;

    @FXML
    private TableColumn<Sala, Integer> codigoSala;
    @FXML
    private TableColumn<Sala, Integer> nrCadeiras;
    @FXML
    private TableColumn<Sala, Integer> nrTomadas;
    @FXML
    private TableColumn<Sala, Integer> piso;
    @FXML
    private TableColumn<Sala, String> Turmas;
    @FXML
    private TableColumn<Sala, String> Edificio;

    @FXML
    private Button adicionarSala;

    @FXML
    private TextField codigoSalaTextfield;

    @FXML
    private TextField nrCadeirasSalaTextfield;

    @FXML
    private ComboBox<Projeto.Edificio> edificiosSala;

    @FXML
    private Button listarSalas;
    @FXML
    private Button removerSalas;
    @FXML
    private Button pesquisarSalas;


    /// FIM TABLE VIEW SALAS
    /*
    TABELA DOS CURSOS
     */

    @FXML
    private javafx.scene.control.TableView<Curso> TabelaCursos;

    @FXML
    private TableColumn<Curso, String> nomeCurso;
    @FXML
    private TableColumn<Curso, String> faculdadeCurso;
    @FXML
    private TableColumn<Curso, String> turmaCurso;

    @FXML
    private Button listarCursos;

    @FXML
    private Button AdicionarCurso;

    @FXML
    private TextField nomeDoCurso;
    @FXML
    private ComboBox<Faculdade> faculdadeDoCurso;
    @FXML
    private Button pesquisarCursos;

/*
TABELA DISCIPLINAS
 */

    /*
    TABELA DISCIPLINAS
     */

    @FXML
    private javafx.scene.control.TableView<Disciplina> TabelaDisciplinas;

    @FXML
    private TableColumn<Disciplina, String> nomeDisciplina;
    @FXML
    private TableColumn<Disciplina, Integer> ectsDisciplina;
    @FXML
    private TableColumn<Disciplina, Integer> semestreDisciplina;

    @FXML
    private TableColumn<Disciplina, String> professorDisciplina;
    @FXML
    private TableColumn<Disciplina, String> turmasDisciplinas;
    @FXML
    private TextField nomedaDisciplina;
    @FXML
    private TextField ectsdaDisciplina;
    @FXML
    private TextField semestredaDisciplina;
    @FXML
    private ListView<Professor> professoresdaDisciplina;
    @FXML
    private Button listarDisciplinas;
    @FXML
    private Button adicionarDisciplinas;



    ///FIM TABELA Disciplinas

    /*
    TABELA DAS TURMAS
     */
    @FXML
    private javafx.scene.control.TableView<Turma> TabelaTurmas;

    @FXML
    private TableColumn<Turma, String> codigoTurma;
    @FXML
    private TableColumn<Turma, Integer> anoTurma;
    @FXML
    private TableColumn<Turma, String> cursoTurma;
    @FXML
    private TableColumn<Turma, String> professorTurma;
    @FXML
    private TableColumn<Turma, String> alunoTurma;
    @FXML
    private TableColumn<Turma, String> disciplinaTurma;
    @FXML
    private TableColumn<Turma, String> horarioTurma;
    @FXML
    private TableColumn<Turma, String> salaTurma;
    @FXML
    private ComboBox<Curso> cursosTurmas;
    @FXML
    private TextField codigosdaTurma;
    @FXML
    private TextField anodaTurma;
    @FXML
    private Button ListarTurmas;
    @FXML
    private Button AdicionarTurmas;
    @FXML
    private ComboBox<Disciplina> disciplinaDaTurma;
    @FXML
    private ComboBox<Sala> salasdaTurma;
    @FXML
    private ComboBox<Professor> professordaTurma;
    @FXML
    private Button pesquisarTurmas;
    @FXML
    private Button limparTabelaTurmas;

    ///Fim Tabela Turmas
    /// TABLE VIEW EDIFICIO

    @FXML
    private javafx.scene.control.TableView<Edificio> TabelaEdificio;

    @FXML
    private TableColumn<Edificio, String> nomeEdificio;
    @FXML
    private TableColumn<Edificio, String> faculdadeEdificio;
    @FXML
    private TableColumn<Edificio, String> salasEdificio;

    @FXML
    private ComboBox<Faculdade> comboBoxFaculdadesEdificio ;

    @FXML
    private Button adicionarEdificio;

    @FXML
    private TextField nomeEdificioTextField;

    @FXML
    private Button listarEdificios;


    /// FIM TABELA EDIFICIO

    /*
    TABELA ALUNOS
     */

    @FXML
    private javafx.scene.control.TableView<Aluno> TabelaAlunos;

    @FXML
    private TableColumn<Aluno, String> nomeAluno;
    @FXML
    private TableColumn<Aluno, String> apelidoAluno;
    @FXML
    private TableColumn<Aluno, Integer> numeroAluno;
    @FXML
    private TableColumn<Aluno, String> dataAluno;
    @FXML
    private TableColumn<Aluno, String> disciplinasAluno;
    @FXML
    private TableColumn<Aluno, String> turmasAluno;
    @FXML
    private TableColumn<Aluno, String> horarioAluno;
    @FXML
    private TextField nomedoAluno;
    @FXML
    private TextField apelidodoAluno;
    @FXML
    private TextField nrdoAluno;
    @FXML
    private TextField dianascimentoAluno;
    @FXML
    private TextField mesnascimentoAluno;
    @FXML
    private TextField anonascimentoAluno;
    @FXML
    private ListView<Turma> turmasdoAluno;
    @FXML
    private Button listarAlunos;
    @FXML
    private Button AdicionarAlunos;


    /// FIM TABELA ALUNOS

   /*
   TABELA PROFESSORES
    */

    @FXML
    private javafx.scene.control.TableView<Professor> TabelaProfessores;

    @FXML
    private TableColumn<Professor, String> nomeProfessor;
    @FXML
    private TableColumn<Professor, String> apelidoProfessor;
    @FXML
    private TableColumn<Professor, String> dataProfessor;
    @FXML
    private TableColumn<Professor, String> emailProfessor;
    @FXML
    private TableColumn<Professor, String> disciplinasProfessor;
    @FXML
    private TableColumn<Professor, String> turmasProfessor;
    @FXML
    private Button listarProfessores;
    @FXML
    private Button AdicionarProfessores;
    @FXML
    private TextField nomedoProfessor;
    @FXML
    private TextField apelidodoProfessor;
    @FXML
    private TextField dianascProfessor;
    @FXML
    private TextField mesnscProfessor;
    @FXML
    private TextField anonascProfessor;
    @FXML
    private ListView<Disciplina> discProfessor;
    @FXML
    private ListView<Turma> turmasdoProfessor;


    /// FIM TABELA PROFESSORES

    @FXML
    private javafx.scene.control.TableView<Faculdade> TableView;

    @FXML
    private TableColumn<Faculdade, String> ColNome;

    public ObservableList<Faculdade> getLista(){
        ObservableList<Faculdade> lista = FXCollections.observableArrayList(
        );
        lista.add(f1);

        return lista;
    }

    public ObservableList<Professor> getProfessores(){
        ObservableList<Professor> lista = FXCollections.observableArrayList(
        );
        for (String cod: f1.professores.keys()
        ) {
            Professor a = f1.professores.get(cod);
            lista.add(a);
        }
        return lista;
    }

    public ObservableList<Aluno> getAlunos(){
        ObservableList<Aluno> lista = FXCollections.observableArrayList(
        );
        for (Integer cod: f1.alunos.keys()
        ) {
            Aluno a = f1.alunos.get(cod);
            lista.add(a);
        }
        return lista;
    }
    public ObservableList<Curso> getCurso(){
        ObservableList<Curso> lista = FXCollections.observableArrayList(
        );
        for (String s: f1.getCursos().keys()
        ) {
            Curso c = f1.getCursos().get(s);
            lista.add(c);
        }
        return lista;
    }
    public ObservableList<Edificio> getEdificios(){
        ObservableList<Edificio> lista = FXCollections.observableArrayList(
        );

        for (String s: f1.getEdificios().keys()
             ) {
            Edificio e = f1.getEdificios().get(s);
            lista.add(e);
        }

        return lista;
    }

    public ObservableList<Sala> getSalas(){
        ObservableList<Sala> lista = FXCollections.observableArrayList(
        );
        for (Integer cod: f1.salas.keys()
        ) {
            Sala s = f1.salas.get(cod);
            lista.add(s);
        }

        return lista;
    }

    public ObservableList<Turma> getTurmas(){
        ObservableList<Turma> lista = FXCollections.observableArrayList(
        );
        for (String cod: f1.turmas.keys()
        ) {
            Turma t = f1.turmas.get(cod);
            lista.add(t);
        }
        return lista;
    }

    public ObservableList<Disciplina> getDisciplinas(){
        ObservableList<Disciplina> lista = FXCollections.observableArrayList(
        );
        for (String cod: f1.disciplinas.keys()
        ) {
            Disciplina d = f1.disciplinas.get(cod);
            lista.add(d);
        }
        return lista;
    }

    public ObservableList<PontosDePassagem> getPdps(){
        ObservableList<PontosDePassagem> lista = FXCollections.observableArrayList(
        );
        for (Integer cod: f1.pdp.keys()
        ) {
            PontosDePassagem p = f1.pdp.get(cod);
            lista.add(p);
        }

        return lista;
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showSalasComboBox();
        showAlunosComboBox();
        showPdpComboBox();
        ///Tabela Faculdade
        ColNome.setCellValueFactory(new PropertyValueFactory<Faculdade,String>("Name"));
        TableView.setItems(getLista());
        viewAddGraph();
        showSelectGraph();
    }


    /*
    HANDLERS DOS PDPS
     */

    public void handleListarPDPS(ActionEvent actionEvent){
        codPDP.setCellValueFactory(new PropertyValueFactory<PontosDePassagem,Integer>("Cod"));
        edificioPDP.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PontosDePassagem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PontosDePassagem, String> p) {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(p.getValue().getEdificio().getNome());
                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        nomePDP.setCellValueFactory(new PropertyValueFactory<PontosDePassagem,String>("Name"));
        xPDP.setCellValueFactory(new PropertyValueFactory<PontosDePassagem,Integer>("X"));
        yPDP.setCellValueFactory(new PropertyValueFactory<PontosDePassagem,Integer>("Y"));
        zPDP.setCellValueFactory(new PropertyValueFactory<PontosDePassagem,Integer>("Z"));
        TabelaPdps.setItems(getPdps());
        edificiosPDPcombo.setItems(getEdificios());
    }

    public void handleAdicionarPDP(ActionEvent actionEvent){
        if(codPDP.getText().isEmpty()  || xdoPDP.getText().isEmpty() || ydoPDP.getText().isEmpty()|| zdoPDP.getText().isEmpty()|| nomedoPDP.getText().isEmpty() || edificiosPDPcombo.getSelectionModel().isEmpty() ){
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setTitle("Ponto de Passagem");
            a1.setContentText("Por favor preencha o campo que se encontra vazio!");
            a1.setHeaderText(null);
            a1.showAndWait();
        }else {
            PontosDePassagem p = new PontosDePassagem(Integer.parseInt(coddoPDP.getText()),Integer.parseInt(xdoPDP.getText()),Integer.parseInt(ydoPDP.getText()),Integer.parseInt(zdoPDP.getText()),nomedoPDP.getText() );
            Edificio e = f1.edificios.get(edificiosPDPcombo.getSelectionModel().getSelectedItem().toString());
            p.addEdificio(e);
            if(f1.pdp.contains(p.getCod())){
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("Ponto de Passagem");
                a1.setContentText("Já existe um Ponto de Passagem com esse codigo!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }else{
                for (Integer c : f1.pdp.keys()
                     ) {
                    PontosDePassagem aux = f1.pdp.get(c);
                    if(p.getX() == aux.getX() && p.getY() == aux.getY() && p.getZ() == aux.getZ() ){
                        Alert a1 = new Alert(Alert.AlertType.ERROR);
                        a1.setTitle("Ponto de Passagem");
                        a1.setContentText("Por favor altere as coordenadas!");
                        a1.setHeaderText(null);
                        a1.showAndWait();
                        break;
                    }else{
                        f1.pdp.put(p.getCod(),p);
                        TabelaPdps.getItems().add(p);
                        break;
                    }
                }



            }
        }




    }

    /*
    HANDLER DOS PROFESSORES
     */

    public void handleListarProfessores(ActionEvent actionEvent){
        ///Tabela Professores
        nomeProfessor.setCellValueFactory(new PropertyValueFactory<Professor,String>("Nome"));
        apelidoProfessor.setCellValueFactory(new PropertyValueFactory<Professor,String>("Apelido"));
        dataProfessor.setCellValueFactory(new PropertyValueFactory<Professor,String>("DataNascimento"));
        emailProfessor.setCellValueFactory(new PropertyValueFactory<Professor,String>("Email"));
        turmasProfessor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Professor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Professor, String> p) {
                if (p.getValue() != null) {
                    StringBuilder turmas = new StringBuilder();
                    for(String cod : p.getValue().getTurmas().keys()) {
                        Turma a = p.getValue().getTurmas().get(cod);
                        turmas.append(a.getCodigo()).append("\n");
                    }
                    return new SimpleStringProperty(turmas.toString());

                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        disciplinasProfessor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Professor, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Professor, String> p) {
                if (p.getValue() != null) {
                    StringBuilder disciplinas = new StringBuilder();
                    for(String cod : p.getValue().getDisciplinas().keys()) {
                        Disciplina d = p.getValue().getDisciplinas().get(cod);
                        disciplinas.append(d.getNome()).append("\n");
                    }
                    return new SimpleStringProperty(disciplinas.toString());

                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        TabelaProfessores.setItems(getProfessores());
        discProfessor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        discProfessor.setItems(getDisciplinas());
        turmasdoProfessor.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        turmasdoProfessor.setItems(getTurmas());
    }

 /*
 Funçao adicionar professor por acabar , falta verificar as ligaçoes , por ex um professor nao pode ser adicionado a uma turma que já tem um professor, entre outros
  */
//    public void handleAdicionarProfessor(ActionEvent actionEvent){
//        if(nomedoProfessor.getText().isEmpty()  || apelidodoAluno.getText().isEmpty() || dianascProfessor.getText().isEmpty()|| mesnscProfessor.getText().isEmpty()|| anonascProfessor.getText().isEmpty() ){
//            Alert a1 = new Alert(Alert.AlertType.ERROR);
//            a1.setTitle("Professor");
//            a1.setContentText("Por favor preencha o campo que se encontra vazio!");
//            a1.setHeaderText(null);
//            a1.showAndWait();
//        }else{
//            ///Crio um objeto Professor com os dados
//            Data d = new Data(Integer.parseInt(dianascProfessor.getText()),Integer.parseInt(mesnscProfessor.getText()),Integer.parseInt(anonascProfessor.getText()));
//            Professor p = new Professor(nomedoProfessor.getText(),apelidodoProfessor.getText(),d);
//            ObservableList<Disciplina> disciplinas = discProfessor.getSelectionModel().getSelectedItems();
//            ObservableList<Turma> turmas = turmasdoProfessor.getSelectionModel().getSelectedItems();
//            ///Vou percorrer as disciplinas selecionadas e verificar junto das turmas selecionadas e ver se coincidem , se sim adiciona a informaçao
//            for(Disciplina d : disciplinas){
//                ///Percorro as turmas selecionadas
//                for(Turma t : turmas){
//                    ///Vamos percorrer as turmas dentro da disciplina atual e ver se existe um match entre a turma selecionada e a turma da disciplina , caso sim podemos adicionar tudo
//                    for(Turma aux : d.getTurmas()){
//                        ///Caso exista um match entao adicionamos o professor, caso contrario nao e mandamos o aviso
//                        if(t.getCodigo().compareTo(aux.getCodigo())==0){
//                            p.getTurmas().put(aux.getCodigo(),aux);
//                            f1.turmas.get(aux.getCodigo()).
//                            f1.professores.put(p.getEmail(),p);
//
//                            TabelaProfessores.getItems().add(p);
//                        }
//                    }
//
//                }
//            }
//
//
//
//
//
//
//
//            if(f1.disciplinas.contains(d.getNome())){
//                Alert a1 = new Alert(Alert.AlertType.ERROR);
//                a1.setTitle("Disciplina");
//                a1.setContentText("Já existe uma disciplina com esse codigo!");
//                a1.setHeaderText(null);
//                a1.showAndWait();
//            }else{
//                //adiciona o curso à ST Cursos da faculdade
//                f1.disciplinas.put(d.getNome(),d);
//                //Adiciona ao respetivo curso a turma
//
//
//            }
//        }
//    }


    /*
    HANDLERS DISCIPLINAS
     */

    public void handleListarDisciplinas(ActionEvent actionEvent){
    ///Tabela Disciplinas

        nomeDisciplina.setCellValueFactory(new PropertyValueFactory<Disciplina,String>("Nome"));
        ectsDisciplina.setCellValueFactory(new PropertyValueFactory<Disciplina,Integer>("Ects"));
        semestreDisciplina.setCellValueFactory(new PropertyValueFactory<Disciplina,Integer>("Semestre"));
        turmasDisciplinas.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Disciplina, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Disciplina, String> d) {
                if (d.getValue() != null) {
                    StringBuilder turmas = new StringBuilder();
                    for(Turma t : d.getValue().getTurmas()) {
                        turmas.append(t.getCodigo()).append("\n");
                    }
                    return new SimpleStringProperty(turmas.toString());

                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        professorDisciplina.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Disciplina, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Disciplina, String> d) {
                if (d.getValue() != null) {
                    StringBuilder disciplinas = new StringBuilder();
                    for(String cod : d.getValue().getProfessores().keys()) {
                        Professor p = d.getValue().getProfessores().get(cod);
                        disciplinas.append(p.getNome()).append("\n");
                    }
                    return new SimpleStringProperty(disciplinas.toString());

                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        TabelaDisciplinas.setItems(getDisciplinas());
        professoresdaDisciplina.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        professoresdaDisciplina.setItems(getProfessores());


    }

    public void handleAdicionarDisciplina(ActionEvent actionEvent){
        if(nomedaDisciplina.getText().isEmpty()  || ectsdaDisciplina.getText().isEmpty() || semestredaDisciplina.getText().isEmpty() ){
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setTitle("Disciplina");
            a1.setContentText("Por favor preencha o campo que se encontra vazio!");
            a1.setHeaderText(null);
            a1.showAndWait();
        }else{

            Disciplina d = new Disciplina(nomedaDisciplina.getText(),Integer.parseInt(ectsdaDisciplina.getText()),Integer.parseInt(semestredaDisciplina.getText()));
            ObservableList<Professor> professores;
            professores = professoresdaDisciplina.getSelectionModel().getSelectedItems();
            for(Professor p : professores){
                d.getProfessores().put(p.getEmail(),p);
            }
            if(f1.disciplinas.contains(d.getNome())){
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("Disciplina");
                a1.setContentText("Já existe uma disciplina com esse codigo!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }else{
                //adiciona o curso à ST Cursos da faculdade
                f1.disciplinas.put(d.getNome(),d);
                //Adiciona ao respetivo curso a turma

                TabelaDisciplinas.getItems().add(d);
            }
        }
    }

    /*
    HANDLERS ALUNOS
     */

    public void handleListarAlunos(ActionEvent actionEvent){
        ///Tabela Alunos

        nomeAluno.setCellValueFactory(new PropertyValueFactory<Aluno,String>("Nome"));
        numeroAluno.setCellValueFactory(new PropertyValueFactory<Aluno,Integer>("NumeroAluno"));
        apelidoAluno.setCellValueFactory(new PropertyValueFactory<Aluno,String>("Apelido"));
        dataAluno.setCellValueFactory(new PropertyValueFactory<Aluno,String>("DataNascimento"));
        turmasAluno.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Aluno, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Aluno, String> t) {
                if (t.getValue() != null) {
                    StringBuilder turmas = new StringBuilder();
                    for(String cod : t.getValue().getTurmas().keys()) {
                        Turma a = t.getValue().getTurmas().get(cod);
                        turmas.append(a.getCodigo()).append("\n");
                    }
                    return new SimpleStringProperty(turmas.toString());

                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        disciplinasAluno.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Aluno, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Aluno, String> a) {
                if (a.getValue() != null) {
                    StringBuilder disciplinas = new StringBuilder();
                    for(String cod : a.getValue().getDisciplinas().keys()) {
                        Disciplina d = a.getValue().getDisciplinas().get(cod);
                        disciplinas.append(d.getNome()).append("\n");
                    }
                    return new SimpleStringProperty(disciplinas.toString());

                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });


        TabelaAlunos.setItems(getAlunos());
        turmasdoAluno.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        turmasdoAluno.setItems(getTurmas());

    }

    public void handleAdicionarAlunos(ActionEvent actionEvent){
        if(nomedoAluno.getText().isEmpty() || apelidodoAluno.getText().isEmpty() || nrdoAluno.getText().isEmpty() || dianascimentoAluno.getText().isEmpty() || mesnascimentoAluno.getText().isEmpty() || anonascimentoAluno.getText().isEmpty() ){
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setTitle("Aluno");
            a1.setContentText("Por favor preencha o campo que se encontra vazio!");
            a1.setHeaderText(null);
            a1.showAndWait();
        }else{
            Data d = new Data(Integer.parseInt(dianascimentoAluno.getText()),Integer.parseInt(mesnascimentoAluno.getText()),Integer.parseInt(anonascimentoAluno.getText()));
            Aluno a = new Aluno(nomedoAluno.getText(),apelidodoAluno.getText(),d,Integer.parseInt(nrdoAluno.getText()));
            ObservableList<Turma> turmas;
            turmas = turmasdoAluno.getSelectionModel().getSelectedItems();
            for(Turma t : turmas){
               a.getTurmas().put(t.getCodigo(),t);
               a.getDisciplinas().put(a.getTurmas().get(t.getCodigo()).getDisciplina().getNome(),a.getTurmas().get(t.getCodigo()).getDisciplina());
               f1.turmas.get(t.getCodigo()).getAlunos().put(a.getNumeroAluno(),a);
            }
            if(f1.alunos.contains(a.getNumeroAluno())){
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("Aluno");
                a1.setContentText("Já existe um aluno com esse codigo!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }else{
                //adiciona o curso à ST Cursos da faculdade
                f1.alunos.put(a.getNumeroAluno(),a);
                //Adiciona ao respetivo curso a turma

                TabelaAlunos.getItems().add(a);
            }
        }
    }

    /*
    HANDLERS DAS TURMAS
     */


    public void handlePesquisarTurmas(ActionEvent actionEvent){
        ObservableList<Turma> results = FXCollections.observableArrayList(
        );

        ///se o textfield codigos turma nao estiver vazia , significa que temos uma pesquisa por codigo
        if(!codigosdaTurma.getText().isEmpty()){
            //no caso da procura por codigo , como apenas existe um codigo unico por turma , basta usarmos o contains
            if(f1.turmas.contains(codigosdaTurma.getText())){
                results.add(f1.turmas.get(codigosdaTurma.getText()));
            }
        }

        //Agora vamos pesquisar turmas pelo ano

        //se o textfield ano nao vazio, vamos  procurar
        if(!anodaTurma.getText().isEmpty()){
            ///Se o results estiver vazio ou nao tivemos correspondencia no campo anterior ou este nao foi procurado
            ///portanto temos q procurar na ST diretamente
            if(results.isEmpty()){
                ///Vamos percorrer a ST turmas
                for (String cod: f1.turmas.keys()
                     ) {
                    Turma t = f1.turmas.get(cod);
                    ///Se a turma atual tiver um ano igual ao ano que procuramos , metemos no results
                    if(Integer.parseInt(anodaTurma.getText())==t.getAno()){
                        results.add(t);
                    }
                }
            }else{
                ///Só entramos aqui se o results tiver algo lá dentro
                for (Turma t: results
                     ) {
                    //Se o ano da turma atual for diferente do ano a procurar , removemos do results
                    if(t.getAno() != Integer.parseInt(anodaTurma.getText())){
                        results.remove(t);
                    }
                }
            }
        }

        ///Agora vamos procurar pelos cursos nas turmas

        ///Se o campo cursos nao estiver vazio, entao procuramos
        if(!cursosTurmas.getSelectionModel().isEmpty()){
           ///Se o results estiver vazio , entao temos que procurar na st
            if(results.isEmpty()){
                for (String cod: f1.turmas.keys()
                     ) {
                    Turma t = f1.turmas.get(cod);
                    ///Se o curso a procurar for igual ao curso atual , entao adicionamos ao result
                    if(t.getCurso().getNome().compareTo(cursosTurmas.getSelectionModel().getSelectedItem().toString())==0){
                        results.add(t);
                    }
                }
            }else{
                for (Turma t : results
                     ) {
                    ///Vamos percorrer os results , caso a turma atual tenha um curso diferente do que procuramos , removemos
                    if(t.getCurso().getNome().compareTo(cursosTurmas.getSelectionModel().getSelectedItem().toString())!=0){
                        results.remove(t);
                    }
                }
            }
        }

        ///Se a combo box dos alunos nao estiver vazia , entao vamos procurar
        if(!alunosComboBox.getSelectionModel().isEmpty()){
            //Se a results estiver vazia, entao temos que procurar diretamente na  ST
            if(results.isEmpty()){
                for (String cod: f1.turmas.keys()
                     ) {
                    Turma t = f1.turmas.get(cod);
                    ///Para a turma atual , procuramos nos seus alunos se o aluno que queremos está dentro da turma
                    for (Integer c : t.getAlunos().keys()
                         ) {
                        Aluno a = f1.alunos.get(c);
                        ///Se os numeros de aluno coincidirem , entao podemos adicionar aos results
                        if(a.getNumeroAluno() == Integer.parseInt(alunosComboBox.getSelectionModel().getSelectedItem().toString())){
                            results.add(t);
                        }
                    }
                }
            }else{
                for (Turma t :results
                     ) {
                    for (Integer cod: t.getAlunos().keys()
                         ) {
                        Aluno a = t.getAlunos().get(cod);
                        ///Se a turma atual do results nao tiver o aluno pretendido , entao removemos essa turma dos results
                        if(!t.getAlunos().contains(Integer.parseInt(alunosComboBox.getSelectionModel().getSelectedItem().toString()))){
                            results.remove(t);
                        }
                    }
                }
            }
        }

        ///Se o campo dos professores nao estiver vazio , entao procuramos
        if(!professordaTurma.getSelectionModel().isEmpty()){
            ///Se o results estiver vazio , procuramos na ST
            if(results.isEmpty()){
                for (String cod: f1.turmas.keys()
                     ) {
                    Turma t = f1.turmas.get(cod);
                    ///Se o professor que procuramos coincidir com o da turma , entao adicionamos aos results
                    if(professordaTurma.getSelectionModel().getSelectedItem().toString().compareTo(t.getProfessor().getEmail())==0){
                        results.add(t);
                    }
                }
            }else{
                for (Turma t: results
                     ) {
                    ///Se o professor da turma atual dos results for diferente do que procuramos , entao removemos a turma do results
                    if(professordaTurma.getSelectionModel().getSelectedItem().toString().compareTo(t.getProfessor().getEmail())!=0){
                        results.remove(t);
                    }
                }
            }
        }


        ///Pesquisa de turmas por disciplina
        if(!disciplinaDaTurma.getSelectionModel().isEmpty()){
            if(results.isEmpty()){
                for (String c: f1.turmas.keys()
                     ) {
                    Turma t = f1.turmas.get(c);
                    ///Se a disciplina que procuramos coincidir com a disciplina da turma , entao vai para os results
                    if(t.getDisciplina().getNome().compareTo(disciplinaDaTurma.getSelectionModel().getSelectedItem().toString())==0){
                        results.add(t);
                    }
                }
            }else{
                for (Turma t: results
                     ) {
                    ///Se a disciplina atual em results for diferente da que procuramos , entao removemos
                    if(t.getDisciplina().getNome().compareTo(disciplinaDaTurma.getSelectionModel().getSelectedItem().toString())!=0){
                        results.remove(t);
                    }
                }
            }
        }

        ///Pesquisa de turmas por sala
        if(!salasdaTurma.getSelectionModel().isEmpty()){
            if(results.isEmpty()){
                for (String c: f1.turmas.keys()
                ) {
                    Turma t = f1.turmas.get(c);
                    ///Se a sala que procuramos coincidir com a sala da turma , entao vai para os results
                    if(t.getSala().getCodigo() == Integer.parseInt(salasdaTurma.getSelectionModel().getSelectedItem().toString())){
                        results.add(t);
                    }
                }
            }else{
                for (Turma t: results
                ) {
                    ///Se o numero da sala atual em results for diferente da que procuramos , entao removemos
                    if(t.getSala().getCodigo() != Integer.parseInt(salasdaTurma.getSelectionModel().getSelectedItem().toString())){
                        results.remove(t);

                    }
                }
            }
        }





        ///ULTIMA TRIAGEM PARA QUANDO UNS FILTROS NAO SAO ADICIONADOS PELA ORDEM
        if(!codigosdaTurma.getText().isEmpty()){
            for (Turma t: results
                 ) {
                if(t.getCodigo().compareTo(codigosdaTurma.getText())!=0){
                    results.remove(t);


                }
            }
        }
        if(!anodaTurma.getText().isEmpty()){
            for (Turma t: results
            ) {
                if(t.getAno()!= Integer.parseInt(anodaTurma.getText())){
                    results.remove(t);

                }
            }
        }

        if(!cursosTurmas.getSelectionModel().isEmpty()){
            for (Turma t: results
            ) {
                if(t.getCurso().getNome().compareTo(cursosTurmas.getSelectionModel().getSelectedItem().toString())!=0){
                    results.remove(t);

                }
            }
        }
        if(!professordaTurma.getSelectionModel().isEmpty()){
            for (Turma t: results
            ) {
                if(t.getProfessor().getNome().compareTo(professordaTurma.getSelectionModel().getSelectedItem().toString())!=0){
                    results.remove(t);

                }
            }
        }
        if(!disciplinaDaTurma.getSelectionModel().isEmpty()){
            for (Turma t: results
            ) {
                if(t.getDisciplina().getNome().compareTo(disciplinaDaTurma.getSelectionModel().getSelectedItem().toString())!=0){
                    results.remove(t);

                }
            }
        }
        if(!salasdaTurma.getSelectionModel().isEmpty()){
            for (Turma t: results
            ) {
                if(t.getSala().getCodigo()!= Integer.parseInt(salasdaTurma.getSelectionModel().getSelectedItem().toString())){
                    results.remove(t);

                }
            }
        }



            TabelaTurmas.getItems().clear();
            TabelaTurmas.setItems(results);



    }

    public void handleGuardarSTSparaTXT(ActionEvent actionEvent){
        f1.guardarsalasST();
        f1.guardarpdpST();
        f1.guardaredificiosST();
        f1.guardarcursosST();
        f1.guardar_turmasST();
        f1.guardar_STPROFESSORES();
        f1.guardardisciplinasST();
        f1.guardar_STALUNOS();
    }

    public void handleListarTurmas(ActionEvent actionEvent){
        ///Tabela Turma
        codigoTurma.setCellValueFactory(new PropertyValueFactory<Turma,String>("Codigo"));
        anoTurma.setCellValueFactory(new PropertyValueFactory<Turma,Integer>("Ano"));
        cursoTurma.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Turma, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Turma, String> t) {
                if (t.getValue() != null) {
                    return new SimpleStringProperty(t.getValue().getCurso().getNome());
                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        alunoTurma.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Turma, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Turma, String> t) {
                if (t.getValue() != null) {
                    StringBuilder alunos = new StringBuilder();
                    for(Integer cod : t.getValue().getAlunos().keys()) {
                        Aluno a = t.getValue().getAlunos().get(cod);
                        alunos.append(a.getNumeroAluno() + " " + a.getNome()  + " " + a.getApelido()).append("\n");
                    }
                    return new SimpleStringProperty(alunos.toString());

                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        professorTurma.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Turma, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Turma, String> t) {
                if (t.getValue() != null) {
                    return new SimpleStringProperty(t.getValue().getProfessor().getNome() + " " + t.getValue().getProfessor().getApelido());
                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        disciplinaTurma.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Turma, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Turma, String> t) {
                if (t.getValue() != null) {
                    return new SimpleStringProperty(t.getValue().getDisciplina().getNome());
                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        salaTurma.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Turma, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Turma, String> t) {
                if (t.getValue() != null) {
                    return new SimpleStringProperty(Integer.toString(t.getValue().getSala().getCodigo()));
                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        TabelaTurmas.setItems(getTurmas());
        cursosTurmas.setItems(getCurso());
        disciplinaDaTurma.setItems(getDisciplinas());
        salasdaTurma.setItems(getSalas());
        professordaTurma.setItems(getProfessores());
        TabelaTurmas.setEditable(true);
        anoTurma.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    public void handleAddTurma(ActionEvent actionEvent){
        if(codigosdaTurma.getText().isEmpty() || anodaTurma.getText().isEmpty() || cursosTurmas.getSelectionModel().isEmpty() || salasdaTurma.getSelectionModel().isEmpty() || disciplinaDaTurma.getSelectionModel().isEmpty() || professordaTurma.getSelectionModel().isEmpty()){
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setTitle("Turma");
            a1.setContentText("Por favor preencha o campo que se encontra vazio!");
            a1.setHeaderText(null);
            a1.showAndWait();
        }else{

            Turma t = new Turma(Integer.parseInt(anodaTurma.getText()),codigosdaTurma.getText());
            Curso c = f1.cursos.get(cursosTurmas.getValue().toString());
            Disciplina d = f1.disciplinas.get(disciplinaDaTurma.getValue().toString());
            Sala s = f1.salas.get(Integer.parseInt(salasdaTurma.getValue().toString()));

            for (String discod: c.getTurmas().keys()
                 ) {
                Turma tur = c.getTurmas().get(discod);
                if(tur.getDisciplina().getNome() == d.getNome()){
                    for (String cod: d.getProfessores().keys()
                    ) {
                        if(d.getProfessores().contains(professordaTurma.getValue().toString())){
                            Professor p = d.getProfessores().get(professordaTurma.getValue().toString());
                            t.setProfessor(p);
                            t.setDisciplina(d);
                            t.setCurso(c);
                            t.setSala(s);
                            if(f1.turmas.contains(t.getCodigo())){
                                Alert a1 = new Alert(Alert.AlertType.ERROR);
                                a1.setTitle("Turma");
                                a1.setContentText("Já existe uma turma com esse codigo!");
                                a1.setHeaderText(null);
                                a1.showAndWait();
                            }else{
                                //adiciona o curso à ST Cursos da faculdade
                                f1.turmas.put(t.getCodigo(),t);
                                //Adiciona ao respetivo curso a turma
                                f1.cursos.get(t.getCurso().getNome()).getTurmas().put(t.getCodigo(),t);
                                f1.professores.get(t.getProfessor().getEmail()).getTurmas().put(t.getCodigo(),t);
                                f1.salas.get(t.getSala().getCodigo()).getTurmas().add(t);
                                f1.disciplinas.get(t.getDisciplina().getNome()).getTurmas().add(t);
                                TabelaTurmas.getItems().add(t);
                            }
                            break;
                        }else{
                            Alert a1 = new Alert(Alert.AlertType.ERROR);
                            a1.setTitle("Turma");
                            a1.setContentText("Por favor selecione um professor da disciplina!");
                            a1.setHeaderText(null);
                            a1.showAndWait();
                        }
                    }
                }else {
                    Alert a1 = new Alert(Alert.AlertType.ERROR);
                    a1.setTitle("Turma");
                    a1.setContentText("Por favor selecione uma disciplina do curso!");
                    a1.setHeaderText(null);
                    a1.showAndWait();
                }
                break;
            }





        }
    }

    public void handleEditarAnoTurma(TableColumn.CellEditEvent editedcell){
        Turma t = TabelaTurmas.getSelectionModel().getSelectedItem();
        //Edita na ST o nome do Edificio
        if(f1.turmas.get(t.getCodigo())==null){

        }else{
            String aux = editedcell.getNewValue().toString();
            f1.turmas.get(t.getCodigo()).setAno(Integer.parseInt(aux));
        }
    }

    /*
    HANDLERS DOS CURSOS
     */

    public void handlePesquisarCursos(ActionEvent actionEvent){
        ObservableList<Curso> results = FXCollections.observableArrayList(
        );

        if(!nomeDoCurso.getText().isEmpty()){
            for (String c: f1.cursos.keys()
                 ) {
                Curso aux = f1.cursos.get(c);
                if(nomeDoCurso.getText().compareTo(aux.getNome())==0){
                    results.add(aux);
                }
            }
        }

        TabelaCursos.getItems().clear();
        TabelaCursos.setItems(results);
    }


    public void handleListarCursos(ActionEvent actionEvent){
        ///Tabela Curso

        nomeCurso.setCellValueFactory(new PropertyValueFactory<Curso,String>("Nome"));
        faculdadeCurso.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Curso, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Curso, String> c) {
                if (c.getValue() != null) {

                    return new SimpleStringProperty(c.getValue().getFaculdade().getName());
                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        turmaCurso.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Curso, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Curso, String> t) {
                if (t.getValue() != null) {
                    StringBuilder turmas = new StringBuilder();
                    for(String cod : t.getValue().getTurmas().keys()) {
                        Turma a = t.getValue().getTurmas().get(cod);
                        turmas.append(a.getCodigo()).append("\n");
                    }
                    return new SimpleStringProperty(turmas.toString());

                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });


        TabelaCursos.setItems(getCurso());
        faculdadeDoCurso.setItems(getLista());
    }

    public void handleAddCurso(ActionEvent actionEvent){
        if(nomeDoCurso.getText().isEmpty() || faculdadeDoCurso.getSelectionModel().isEmpty()){
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setTitle("Curso");
            a1.setContentText("Por favor preencha o campo que se encontra vazio!");
            a1.setHeaderText(null);
            a1.showAndWait();
        }else{
            Curso c = new Curso(nomeDoCurso.getText());
            c.setFaculdade(f1);
            if(f1.cursos.contains(c.getNome())){
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("Curso");
                a1.setContentText("Já existe um curso com esse codigo!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }else{
                //adiciona o curso à ST Cursos da faculdade
                f1.cursos.put(c.getNome(),c);
                TabelaCursos.getItems().add(c);
            }
        }
    }

    /*
         HANDLERS DA SALA
     */

    public void handlePesquisarSalas(ActionEvent actionEvent){
        ObservableList<Sala> results = FXCollections.observableArrayList(
        );

        ///Primeiro Vamos procurar salas pelo requisito codigo

        if(!codigoSalaTextfield.getText().isEmpty()){
            for (Integer cod: f1.salas.keys()
            ) {
                Sala s = f1.salas.get(cod);
                if(Integer.parseInt(codigoSalaTextfield.getText()) == s.getCodigo() ){
                    results.add(s);
                }
            }
        }

        ///Pesquisar agora de acordo com o nr de tomadas

        ///Caso a observable list esteja vazia , significa que o campo codigo nao está a ser procurado e entao
        ///temos que procurar diretamente na st , caso contrario procuramos apenas na observable list
        if(!nrCadeirasSalaTextfield.getText().isEmpty()){
            if(results.isEmpty()){
                for (Integer c: f1.salas.keys()
                     ) {
                    Sala s = f1.salas.get(c);
                    if(s.getNrCadeiras() == Integer.parseInt(nrCadeirasSalaTextfield.getText()) ){
                        results.add(s);
                    }
                }
            }else{
                for (Sala s: results
                     ) {
                    ///Vamos percorrer a result e remover todas as salas que tenham nrCadeiras diferente
                    if(s.getNrCadeiras() !=Integer.parseInt(nrCadeirasSalaTextfield.getText()) ){
                        results.remove(s);
                    }
                }
            }
        }

        TabelaSalas.getItems().clear();
        TabelaSalas.setItems(results);

    }


    public void handleAddSala(ActionEvent actionEvent){

        if(codigoSalaTextfield.getText().isEmpty() || nrCadeirasSalaTextfield.getText().isEmpty() || edificiosSala.getSelectionModel().isEmpty()){
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setTitle("Sala");
            a1.setContentText("Por favor preencha o campo que se encontra vazio!");
            a1.setHeaderText(null);
            a1.showAndWait();
        }else{
            Sala s = new Sala(Integer.parseInt( codigoSalaTextfield.getText()),Integer.parseInt(nrCadeirasSalaTextfield.getText()));
            Edificio e = f1.edificios.get(edificiosSala.getValue().toString());
            s.addEdificio(e);
            if(f1.salas.contains(s.getCodigo())){
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("Sala");
                a1.setContentText("Já existe uma sala com esse codigo!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }else{
                //adiciona a sala à ST SALAS da faculdade
                f1.salas.put(s.getCodigo(),s);
                // adiciona ao edificio a sala acaba de criar
                f1.edificios.get(s.getEdificio().getNome()).getSalas().add(s);
                TabelaSalas.getItems().add(s);
            }
        }

    }

    public void handleListarSalas(ActionEvent actionEvent){

        ///Tabela Salas
        codigoSala.setCellValueFactory(new PropertyValueFactory<Sala,Integer>("Codigo"));
        nrCadeiras.setCellValueFactory(new PropertyValueFactory<Sala,Integer>("NrCadeiras"));
        nrTomadas.setCellValueFactory(new PropertyValueFactory<Sala,Integer>("NrTomadas"));
        piso.setCellValueFactory(new PropertyValueFactory<Sala,Integer>("Piso"));


        Turmas.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sala, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sala, String> s) {
                if (s.getValue() != null) {
                    StringBuilder turmas = new StringBuilder();
                    for(Turma t : s.getValue().getTurmas()) {
                        turmas.append(t.getCodigo()).append("\n");
                    }
                    return new SimpleStringProperty(turmas.toString());
                    //return new SimpleStringProperty(s.getValue().getTurmas().getCodigo());
                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        Edificio.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sala, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sala, String> s) {
                if (s.getValue() != null) {
                    return new SimpleStringProperty(s.getValue().getEdificio().getNome());
                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });

        TabelaSalas.setItems(getSalas());

        edificiosSala.setItems(getEdificios());

        TabelaSalas.setEditable(true);
        nrCadeiras.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        nrTomadas.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }

    public void handleEditarNrCadeirasSala(TableColumn.CellEditEvent editedcell){
        Sala s = TabelaSalas.getSelectionModel().getSelectedItem();
        //Edita na ST o nome do Edificio
        if(f1.salas.get(s.getCodigo())==null){

        }else{
            String aux = editedcell.getNewValue().toString();
            f1.salas.get(s.getCodigo()).setNrCadeiras(Integer.parseInt(aux));
        }
    }
    public void handleEditarNrTomadasSala(TableColumn.CellEditEvent editedcell){
        Sala s = TabelaSalas.getSelectionModel().getSelectedItem();
        //Edita na ST o nome do Edificio
        if(f1.salas.get(s.getCodigo())==null){

        }else{
            String aux = editedcell.getNewValue().toString();
            f1.salas.get(s.getCodigo()).setNrTomadas(Integer.parseInt(aux));
        }
    }


    public void handleRemoverSala(ActionEvent actionEvent) throws IOException {
        ///Recebo o codigo da sala a remover
        Sala s = f1.salas.get(Integer.parseInt(codigoSalaTextfield.getText()));
        f1.removerSalas(s);
    }


    /*
        HANDLERS EDIFICIO
     */

    public void handleAddEdificio(ActionEvent actionEvent){

        Edificio e = new Edificio(nomeEdificioTextField.getText(),f1);
        if(nomeEdificioTextField.getText().isEmpty()){
            Alert a1 = new Alert(Alert.AlertType.ERROR);
            a1.setTitle("Edificio");
            a1.setContentText("Por favor preencha o campo Nome!");
            a1.setHeaderText(null);
            a1.showAndWait();
        }else{
            if(f1.edificios.contains(e.getNome())){
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("Edificio");
                a1.setContentText("Já existe um edificio com esse nome!");
                a1.setHeaderText(null);
                a1.showAndWait();
            }else{
                f1.edificios.put(e.getNome(),e);

                TabelaEdificio.getItems().add(e);
            }
        }

    }

    public void handleListarEdificios(ActionEvent actionEvent){
    ///Tabela Edificio
        nomeEdificio.setCellValueFactory(new PropertyValueFactory<Edificio,String>("Nome"));
        faculdadeEdificio.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Edificio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Edificio, String> e) {
                if (e.getValue() != null) {
                    return new SimpleStringProperty(e.getValue().getFaculdade().getName());
                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });
        salasEdificio.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Edificio, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Edificio, String> e) {
                if (e.getValue() != null) {
                    StringBuilder salas = new StringBuilder();
                    for(Sala s : e.getValue().getSalas()) {

                        salas.append(s.getCodigo() + "\n");
                    }
                    return new SimpleStringProperty(salas.toString());

                } else {
                    return new SimpleStringProperty("<no info>");
                }
            }
        });

        TabelaEdificio.setEditable(true);
        nomeEdificio.setCellFactory(TextFieldTableCell.forTableColumn());
        TabelaEdificio.setItems(getEdificios());

//        comboBoxFaculdadesEdificio.setCellFactory(new Callback<ListView<Faculdade>, ListCell<Faculdade>>() {
//
//            @Override
//            public ListCell<Faculdade> call(ListView<Faculdade> l) {
//                return new ListCell<Faculdade>() {
//
//                    @Override
//                    protected void updateItem(Faculdade item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (item == null || empty) {
//                            setGraphic(null);
//                        } else {
//                            setText(item.getName());
//                        }
//                    }
//                } ;
//            }
//        });
        comboBoxFaculdadesEdificio.setItems(getLista());
    }


    public void handleLimparTabelTurmas(ActionEvent actionEvent){
        TabelaTurmas.getItems().clear();
    }


    public void handleAddTurma() {
    }

    public void gerarVerticesSalasGraph(String v)
    {
        int codSala = Integer.parseInt(v);
        double posX = salas.get(codSala).getX();
        double posY = salas.get(codSala).getY();
        int piso = salas.get(codSala).getZ();
        Circle c = new Circle(posX,posY,radius);
        c.setOpacity(0.6);
        c.setFill(Color.RED);
        c.setId(""+v);
        Text text = new Text(""+v);
        StackPane stack = new StackPane();
        stack.setLayoutX(posX-radius);
        stack.setLayoutY(posY-radius);
        stack.getChildren().addAll(c,text);
        graphPane.getChildren().add(stack);
    }

    public void gerarVerticesPdpGraph(String v)
    {
        int codPdp = Integer.parseInt(v);
        String name = pdp.get(codPdp).getName();
        double posX = pdp.get(codPdp).getX();
        double posY = pdp.get(codPdp).getY();
        int piso = pdp.get(codPdp).getZ();
        Circle c = new Circle(posX,posY,radius);
        c.setOpacity(0.6);
        c.setFill(Color.BLACK);
        c.setId(""+v);
        Text text = new Text(""+name);
        StackPane stack = new StackPane();
        stack.setLayoutX(posX-radius);
        stack.setLayoutY(posY-radius);
        stack.getChildren().addAll(c,text);
        graphPane.getChildren().add(stack);
    }

    public void drawGraph()
    {
        String delimiter = ";";
        In in = new In(pdpSalastxt);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            String type = a[0];
            int v = Integer.parseInt(a[1]);
            if(type.compareTo("s")==0)
            {
                gerarVerticesSalasGraph(graph_pdpSalas.nameOf(v));
            }else{
                if(type.compareTo("pdp")==0){
                    gerarVerticesPdpGraph(graph_pdpSalas.nameOf(v));
                }else {
                    System.out.println("Erro no txt");
                }
            }
        }
        in.close();
    }
///////////////////////////////////////////////////////////////////////
    public void handleGerarGrafoSalas(ActionEvent actionEvent) {
        graphPane.getChildren().clear();
        drawGraph();
        String delimiter = ";";
        In in = new In(pdpSalastxt);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = Integer.parseInt(a[1]);
            StackPane spv = (StackPane) graphPane.getChildren().get(v);
            Circle cv = (Circle) spv.getChildren().get(0);
            for(int i = 2;i<a.length;i=i+2)
            {
                int w=Integer.parseInt(a[i]); // a que grafo estao conectadoss
                StackPane spw = (StackPane) graphPane.getChildren().get(w);
                Circle cw = (Circle) spw.getChildren().get(0);
                Line line = new Line(cv.getCenterX(),cv.getCenterY(),cw.getCenterX(),cw.getCenterY());
                graphPane.getChildren().add(line);
            }
        }
        in.close();
    }

    /// Graph Bipartite
    public void handleBipartite(ActionEvent event) {
        Bipartite_Projeto bp = new Bipartite_Projeto(graph_pdpSalas);
        bipartiteTextField.setText(""+bp.isBipartite());
    }
    /// Graph Conexo
    public void handleConexo(ActionEvent event) {
        DepthFirstSearch_Project dfs = new DepthFirstSearch_Project(graph_pdpSalas,1);
        if (dfs.count() != graph_pdpSalas.digraph().V())
            printConexo.setText("Conexo");
        else
            printConexo.setText("Not Conexo");
    }

    public void showSalasComboBox(){
        for (Integer s:salas.keys()) {
            salasComboBox.getItems().addAll(salas.get(s).getName());
            comboSala.getItems().addAll(salas.get(s).getName());
            comboSalaSala.getItems().addAll(salas.get(s).getName());
            comboSalaSala1.getItems().addAll(salas.get(s).getName());
        }
        for (Integer p:pdp.keys()) {
            salasComboBox.getItems().addAll(pdp.get(p).getName()+ " (" +pdp.get(p).getCod()+")");
        }
    }

    public void showPdpComboBox(){
        for (Integer p:pdp.keys()) {
            comboPdp.getItems().addAll(pdp.get(p).getName() + " (" +pdp.get(p).getCod()+")");
            comboPdpPdp.getItems().addAll(pdp.get(p).getName()+ " (" +pdp.get(p).getCod()+")");
            comboPdpPdp1.getItems().addAll(pdp.get(p).getName()+ " (" +pdp.get(p).getCod()+")");
        }
    }

    public void showAlunosComboBox(){
        for (int c : alunos.keys()) {
            Aluno a = alunos.get(c);
            alunosComboBox.getItems().addAll(a.getNome() + " (" + a.getNumeroAluno() + ")");
            selectAlunoSEmergencia.getItems().addAll(a.getNome() + " (" + a.getNumeroAluno() + ")");
        }
    }

    public void showSelectGraph(){
        selectGraph1.getItems().add("Graph");
        selectGraph1.getItems().add("Sub Graph");
    }

    public void handleCalculeDistance(ActionEvent event){ // FALTA IMPRIMIR PASSO POR PASSO PARA QUE SALA/PDP O ALUNO PASSOU ATÉ CHEGAR AO DESTINO, COM OS RESPECTIVOS CUSTOS
        String nomeSala=salasComboBox.getValue();
        String nomeAluno=alunosComboBox.getValue();

        int numAluno = Integer.parseInt(nomeAluno.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira
        int num = Integer.parseInt(nomeSala.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira


        if(!salas.contains(num)){ // É porque é um pdp
            String[] numbers = nomeSala.split(" ");
            int aux= 0;
            num = 0; // como nao vai ser uma sala, vou ter de atualizar a variavel num
            // Para pegar apenas no cod do PDP
            for (String s : numbers) {
                if(aux == 1){
                    num = Integer.parseInt(s.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira
                }
                aux++;
            }
        }

        // Se for o ponto aonde quero ir for uma sala
        if(salas.contains(num)){
            Sala sala = salas.get(num);
            if(alunos.contains(numAluno)){
                Aluno a1 = alunos.get(numAluno);
                Point p = gi.pdpOuSalaMaisProxima(alunos.get(numAluno));
                if ( p instanceof PontosDePassagem){
                    PontosDePassagem p1 = (PontosDePassagem)p;
                    for (int v = 0; v < graph_pdpSalas.digraph().V();v++){
                        if(pdp.get(p1.getCod()).getCod() == Integer.parseInt(graph_pdpSalas.nameOf(v))){
                            for (int vi = 0; vi < graph_pdpSalas.digraph().V(); vi++){
                                if(sala.getCodigo() == Integer.parseInt(graph_pdpSalas.nameOf(vi))){
                                    DijkstraSP_Projeto sp = new DijkstraSP_Projeto(graph_pdpSalas, v);
                                    if(sp.hasPathTo(vi)){
                                        a1.setX(sala.getX());
                                        a1.setY(sala.getY());
                                        a1.setZ(sala.getZ());
                                        f1.guardar_STALUNOS();
                                        double dist = sp.distTo(vi)+p1.distAlunoPdpProx;
                                        resultDistance.setText(String.valueOf(dist));
                                     //   distToSalas.setText("test");
                                      //  distToSalas.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                                     //   distToSalas;
                                      //  public TableColumn dadosAluno;
                                    }else {
                                        resultDistance.setText("Sem Caminho Possivel");
                                    }
                                }
                            }
                        }
                    }
                }else if( p instanceof Sala){
                    Sala s1 = (Sala)p;
                    for (int v = 0; v < graph_pdpSalas.digraph().V();v++){
                        if(salas.get(s1.getCodigo()).getCodigo() == Integer.parseInt(graph_pdpSalas.nameOf(v))){
                            for (int vi = 0; vi < graph_pdpSalas.digraph().V(); vi++){
                                if(sala.getCodigo() == Integer.parseInt(graph_pdpSalas.nameOf(vi))){
                                    DijkstraSP_Projeto sp = new DijkstraSP_Projeto(graph_pdpSalas, v);
                                    if(sp.hasPathTo(vi)){
                                        a1.setX(sala.getX());
                                        a1.setY(sala.getY());
                                        a1.setZ(sala.getZ());
                                        f1.guardar_STALUNOS();
                                        double dist = sp.distTo(vi)+s1.distAlunoSalaProx;
                                        resultDistance.setText(String.valueOf(dist));
                                    }else {
                                        resultDistance.setText("Sem Caminho Possivel");
                                    }
                                }
                            }
                        }
                    }
                }else {
                    System.out.println("Erro !!!!!!");
                }
            }
        }
        // se for um pdp
        if(pdp.contains(num)){
            PontosDePassagem pontoPassagem = pdp.get(num);
            if(alunos.contains(numAluno)){
                Aluno a1 = alunos.get(numAluno);
                Point p = gi.pdpOuSalaMaisProxima(alunos.get(numAluno));
                if ( p instanceof PontosDePassagem){
                    PontosDePassagem p1 = (PontosDePassagem)p;
                    for (int v = 0; v < graph_pdpSalas.digraph().V();v++){
                        if(pdp.get(p1.getCod()).getCod() == Integer.parseInt(graph_pdpSalas.nameOf(v))){
                            for (int vi = 0; vi < graph_pdpSalas.digraph().V(); vi++){
                                if(pontoPassagem.getCod() == Integer.parseInt(graph_pdpSalas.nameOf(vi))){
                                    DijkstraSP_Projeto sp = new DijkstraSP_Projeto(graph_pdpSalas, v);
                                    if(sp.hasPathTo(vi)){
                                        a1.setX(pontoPassagem.getX());
                                        a1.setY(pontoPassagem.getY());
                                        a1.setZ(pontoPassagem.getZ());
                                        f1.guardar_STALUNOS();
                                        double dist = sp.distTo(vi)+p1.distAlunoPdpProx;
                                        resultDistance.setText(String.valueOf(dist));
                                        //   distToSalas.setText("test");
                                        //  distToSalas.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
                                        //   distToSalas;
                                        //  public TableColumn dadosAluno;
                                    }else {
                                        resultDistance.setText("Sem Caminho Possivel");
                                    }
                                }
                            }
                        }
                    }
                }else if( p instanceof Sala){
                    Sala s1 = (Sala)p;
                    for (int v = 0; v < graph_pdpSalas.digraph().V();v++){
                        if(salas.get(s1.getCodigo()).getCodigo() == Integer.parseInt(graph_pdpSalas.nameOf(v))){
                            for (int vi = 0; vi < graph_pdpSalas.digraph().V(); vi++){
                                if(pontoPassagem.getCod() == Integer.parseInt(graph_pdpSalas.nameOf(vi))){
                                    DijkstraSP_Projeto sp = new DijkstraSP_Projeto(graph_pdpSalas, v);
                                    if(sp.hasPathTo(vi)){
                                        a1.setX(pontoPassagem.getX());
                                        a1.setY(pontoPassagem.getY());
                                        a1.setZ(pontoPassagem.getZ());
                                        f1.guardar_STALUNOS();
                                        double dist = sp.distTo(vi)+s1.distAlunoSalaProx;
                                        resultDistance.setText(String.valueOf(dist));
                                        distToSalas.setText("test");
                                    }else {
                                        resultDistance.setText("Sem Caminho Possivel");
                                    }
                                }
                            }
                        }
                    }
                }else {
                    System.out.println("Erro !!!!!!");
                }
            }
        }
    }

    /**
     *
     * FALTA GUARDAR ISTO PARA TXT, E NO GRAPH CREATOR SO TENHO DE CARREGAR !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    public void handlerButtonConnectPdpSala(ActionEvent event) {
        String p1 = comboPdp.getValue();
        String s1 = comboSala.getValue();
        Integer i = Integer.parseInt(pesosPdpSalas.getText());

        String[] numbers = p1.split(" ");
        int aux= 0;
        int numPdp = 0;
        // Para pegar apenas no cod do PDP
        for (String s : numbers) {
            if(aux == 1){
                numPdp = Integer.parseInt(s.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira
            }
            aux++;
        }

        int numSala = Integer.parseInt(s1.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira

        if(salas.contains(numSala)){
            Sala sala = salas.get(numSala);
            if(pdp.contains(numPdp)){
                PontosDePassagem pontosDePassagem = pdp.get(numPdp);
                gi.conectGraphs(pontosDePassagem,sala,graph_pdpSalas,pdpSalastxt,i);
            }
        }
        handleGerarGrafoSalas(null); // atualiza o grafo
    }

    public void handlerButtonConnectSalas(ActionEvent event) {
        String s1 = comboSalaSala.getValue();
        String s2 = comboSalaSala1.getValue();
        Integer i = Integer.parseInt(pesosSalas.getText());

        int numSala = Integer.parseInt(s1.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira
        int numSala1 = Integer.parseInt(s2.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira

        if(salas.contains(numSala) && salas.contains(numSala1)){
            Sala sala = salas.get(numSala);
            Sala sala1 = salas.get(numSala1);

            gi.conectGraphs(sala,sala1,graph_pdpSalas,pdpSalastxt,i);
        }
    }

    public void handlerButtonConnectPdpPdp(ActionEvent event) {
        String p1 = comboPdpPdp.getValue();
        String p2 = comboPdpPdp1.getValue();
        Integer i = Integer.parseInt(pesosPdp.getText());

        String[] numbers = p1.split(" ");
        int aux= 0;
        int numPdp = 0;
        // Para pegar apenas no cod do PDP
        for (String s : numbers) {
            if(aux == 1){
                numPdp = Integer.parseInt(s.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira
            }
            aux++;
        }
        String[] numbers1 = p2.split(" ");
        int aux1= 0;
        int numPdp1 = 0;
        // Para pegar apenas no cod do PDP
        for (String si : numbers1) {
            if(aux1 == 1){
                numPdp1 = Integer.parseInt(si.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira
            }
            aux1++;
        }
        if(pdp.contains(numPdp)){
            PontosDePassagem pontosDePassagem = pdp.get(numPdp);
            if(pdp.contains(numPdp1)){
                PontosDePassagem pontosDePassagem1 = pdp.get(numPdp1);
                gi.conectGraphs(pontosDePassagem,pontosDePassagem1,graph_pdpSalas,pdpSalastxt,i);
            }
        }
        handleGerarGrafoSalas(null); // atualiza o grafo
    }

    public void buttonSave(ActionEvent event) {
        gi.saveBinGraph(graph_pdpSalas,pdpSalasBinFile);
    }

    public void buttonLoad(ActionEvent event) {
        graph_pdpSalas = gi.readBinGraph(graph_pdpSalas,pdpSalasBinFile);
        gi.writeSalasPdpTxt(graph_pdpSalas,pdpSalastxt);
        handleGerarGrafoSalas(null);
    }

    public void handlerSaidaEmergencia(ActionEvent event) { ///// FALTA MELHORAR O IMPRIMIR, COLOCAR A IMPRIMIR DE QUE SALA PARA QUE PDP FOI E O CUSTO TOTAL
        String nomeAluno = selectAlunoSEmergencia.getValue();
        int numAluno = Integer.parseInt(nomeAluno.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira
        Aluno a = alunos.get(numAluno);
        int []codSaida = gi.saidaDeEmergencia(a);
        PontosDePassagem p1 = pdp.get(codSaida[1]);
        a.setX(p1.getX());
        a.setY(p1.getY());
        a.setZ(p1.getZ());

        resultSaidaEmergencia.setText(String.valueOf(codSaida[0]));

    }

    public void viewAddGraph(){
        listViewAddGraph.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listViewAddGraph.getItems().addAll(getSalas());
        listViewAddGraph1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (Integer p:pdp.keys()) {
            listViewAddGraph1.getItems().addAll(pdp.get(p).getName() +" (" +pdp.get(p).getCod() +")");
        }
    }

    public void addSubGraph(ActionEvent event) {
        ObservableList<Sala> s;
        ObservableList<String> pontosDePassagem;
        s = listViewAddGraph.getSelectionModel().getSelectedItems();
        pontosDePassagem = listViewAddGraph1.getSelectionModel().getSelectedItems();

        gi.guardar_pdp_txt_graph(subGraphTxt);
        subGraphSalasPdp = new SymbolDigraphWeighted(subGraphTxt,";"); // crio o subGraph
        gi.conexoesDoSubGraph(subGraphTxt,s,pontosDePassagem,graph_pdpSalas,subGraphSalasPdp);
        //gi.printSubGraph(subGraphSalasPdp); // APENAS PARA TESTAR SE O SUBGRAPH ESTA OU NAO CORRETO
        createSubGraph();
    }

    public void createSubGraph() {
        graphPane1.getChildren().clear();
        drawSubGraph();
        String delimiter = ";";
        In in = new In(subGraphTxt);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = Integer.parseInt(a[1]);
            StackPane spv = (StackPane) graphPane1.getChildren().get(v);
            Circle cv = (Circle) spv.getChildren().get(0);
            for(int i = 2;i<a.length;i=i+2)
            {
                int w=Integer.parseInt(a[i]); // a que grafo estao conectadoss
                StackPane spw = (StackPane) graphPane1.getChildren().get(w);
                Circle cw = (Circle) spw.getChildren().get(0);
                Line line = new Line(cv.getCenterX(),cv.getCenterY(),cw.getCenterX(),cw.getCenterY());
                graphPane1.getChildren().add(line);
            }
        }
        in.close();
    }

    public void drawSubGraph()
    {
        String delimiter = ";";
        In in = new In(subGraphTxt);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            String type = a[0];
            int v = Integer.parseInt(a[1]);
            if(type.compareTo("s")==0)
            {
                gerarVerticesSalasSubGraph(subGraphSalasPdp.nameOf(v));
            }else{
                if(type.compareTo("pdp")==0){
                    gerarVerticesPdpSubGraph(subGraphSalasPdp.nameOf(v));
                }else {
                    System.out.println("Erro no txt");
                }
            }
        }
        in.close();
    }

    public void gerarVerticesSalasSubGraph(String v)
    {
        int codSala = Integer.parseInt(v);
        double posX = salas.get(codSala).getX();
        double posY = salas.get(codSala).getY();
        int piso = salas.get(codSala).getZ();
        Circle c = new Circle(posX,posY,radius);
        c.setOpacity(0.6);
        c.setFill(Color.RED);
        c.setId(""+v);
        Text text = new Text(""+v);
        StackPane stack = new StackPane();
        stack.setLayoutX(posX-radius);
        stack.setLayoutY(posY-radius);
        stack.getChildren().addAll(c,text);
        graphPane1.getChildren().add(stack);
    }

    public void gerarVerticesPdpSubGraph(String v)
    {
        int codPdp = Integer.parseInt(v);
        String name = pdp.get(codPdp).getName();
        double posX = pdp.get(codPdp).getX();
        double posY = pdp.get(codPdp).getY();
        int piso = pdp.get(codPdp).getZ();
        Circle c = new Circle(posX,posY,radius);
        c.setOpacity(0.6);
        c.setFill(Color.BLACK);
        c.setId(""+v);
        Text text = new Text(""+name);
        StackPane stack = new StackPane();
        stack.setLayoutX(posX-radius);
        stack.setLayoutY(posY-radius);
        stack.getChildren().addAll(c,text);
        graphPane1.getChildren().add(stack);
    }
}
