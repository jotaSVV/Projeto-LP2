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
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import static Projeto.JavaFx.GraphCreator.f1;

public class GraphCreatorFXMLController implements Initializable {


    ///TABLE VIEW SALAS
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
    private ComboBox edificiosSala;

    @FXML
    private Button listarSalas;


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
    private ComboBox faculdadeDoCurso;

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
    private ListView professoresdaDisciplina;
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
    private ComboBox cursosTurmas;
    @FXML
    private TextField codigosdaTurma;
    @FXML
    private TextField anodaTurma;
    @FXML
    private Button ListarTurmas;
    @FXML
    private Button AdicionarTurmas;
    @FXML
    private ComboBox disciplinaDaTurma;
    @FXML
    private ComboBox salasdaTurma;
    @FXML
    private ComboBox professordaTurma;

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
    private ComboBox comboBoxFaculdadesEdificio ;

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
    private ListView turmasdoAluno;
    @FXML
    private Button listarAlunos;
    @FXML
    private Button AdicionarAlunos;


    /// FIM TABELA ALUNOS

    /// TABLE VIEW PROFESSORES

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





    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ///Tabela Faculdade

        ColNome.setCellValueFactory(new PropertyValueFactory<Faculdade,String>("Name"));
        TableView.setItems(getLista());








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







    }


    public Group graphGroup;
    private double radius = 30.0;
    private String salas_txt = ".//data//salasGraph.txt";
    Graph_project gi = new Graph_project();

    public void create_vertice_in_ProGraph(int v)
    {
        Random r = new Random();
        double posX = r.nextDouble()*300;
        double posY = r.nextDouble()*300;
        Circle c = new Circle(posX,posY,radius);
        c.setOpacity(0.6);
        c.setFill(Color.RED);
        c.setId(""+v);
        Text text = new Text(""+v);
        StackPane stack = new StackPane();
        stack.setLayoutX(posX-radius);
        stack.setLayoutY(posY-radius);
        stack.getChildren().addAll(c,text);
        graphGroup.getChildren().add(stack);
    }

    public void drawGraph_salas()
    {
        String delimiter = ";";
        In in = new In(salas_txt);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = Integer.parseInt(a[0]);
            create_vertice_in_ProGraph(v);
        }
        in.close();
    }



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
        professoresdaDisciplina.setItems(getProfessores());
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
            }
            if(f1.alunos.contains(a.getNumeroAluno())){
                Alert a1 = new Alert(Alert.AlertType.ERROR);
                a1.setTitle("Turma");
                a1.setContentText("Já existe uma turma com esse codigo!");
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
            Professor p = f1.professores.get(professordaTurma.getValue().toString());
            t.setDisciplina(d);
            t.setCurso(c);
            t.setSala(s);
            t.setProfessor(p);
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



    public void handleCreateSalasGraphAction(ActionEvent actionEvent) {
        drawGraph_salas();
        String delimiter = ";";
        In in = new In(salas_txt);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = Integer.parseInt(a[0]);
            StackPane spv = (StackPane) graphGroup.getChildren().get(v);
            Circle cv = (Circle) spv.getChildren().get(0);
            for(int i = 1;i<a.length;i=i+2)
            {
                int w=Integer.parseInt(a[i]);
                StackPane spw = (StackPane) graphGroup.getChildren().get(w);
                Circle cw = (Circle) spw.getChildren().get(0);
                Line line = new Line(cv.getCenterX(),cv.getCenterY(),cw.getCenterX(),cw.getCenterY());
                graphGroup.getChildren().add(line);
            }
        }
        in.close();
    }







}
