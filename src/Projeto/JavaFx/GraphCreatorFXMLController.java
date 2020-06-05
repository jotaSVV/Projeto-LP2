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
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Callback;

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


    /// FIM TABLE VIEW SALAS
    /// TABELA CURSOS

    @FXML
    private javafx.scene.control.TableView<Curso> TabelaCursos;

    @FXML
    private TableColumn<Curso, String> nomeCurso;
    @FXML
    private TableColumn<Curso, String> faculdadeCurso;
    @FXML
    private TableColumn<Curso, Integer> turmaCurso;


    ///FIM TABELA CURSOS


    ///TABELA TURMAS
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

    /// FIM TABELA EDIFICIO

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





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ///Tabela Faculdade

        ColNome.setCellValueFactory(new PropertyValueFactory<Faculdade,String>("Name"));
        TableView.setItems(getLista());

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

        TabelaCursos.setItems(getCurso());

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


        TabelaEdificio.setItems(getEdificios());


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
                    return new SimpleStringProperty(t.getValue().getProfessor().getNome());
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
