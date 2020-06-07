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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Callback;

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

    public javafx.scene.control.TableView tabelaparadistancia;
    public javafx.scene.control.TableView tabelaparadistancia1;
    public TableColumn shortestPathAluno;
    public TableColumn shortestPathSala;

    // ADD GRAFOS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
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

    private String pdpSalastxt = ".//data//salasPdp.txt";

    Graph_project<Point> gi = new Graph_project<>();

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

    public void handleSelectAlunos(ActionEvent event) {
    }

    public void handleSelectSalas(ActionEvent event) {
    }

    public void showSalasComboBox(){
        for (Integer s:salas.keys()) {
            salasComboBox.getItems().addAll(salas.get(s).getName());
            comboSala.getItems().addAll(salas.get(s).getName());
            comboSalaSala.getItems().addAll(salas.get(s).getName());
            comboSalaSala1.getItems().addAll(salas.get(s).getName());
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
        }
    }

    public void handleCalculeDistance(ActionEvent event){ // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        String nomeSala=salasComboBox.getValue();
        String nomeAluno=alunosComboBox.getValue();

        int numAluno = Integer.parseInt(nomeAluno.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira
        int numSala = Integer.parseInt(nomeSala.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira

        if(salas.contains(numSala)){
            Sala sala = salas.get(numSala);
            if(alunos.contains(numAluno)){
                Aluno a1 = alunos.get(numAluno);
                Point p = gi.pdpOuSalaMaisProxima(alunos.get(numAluno));
                if ( p instanceof PontosDePassagem){
                    PontosDePassagem p1 = (PontosDePassagem)p;
                    System.out.println("RETORNOU UM PDP " +p1.getName());
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
                                        System.out.println(dist);
                                        System.out.println("Aluno POS:" +a1.getX() +" "+ a1.getY() +" "+a1.getZ());
                                        resultDistance.setText(String.valueOf(dist));
                                    }else {
                                        resultDistance.setText("Sem Caminho Possivel");
                                    }
                                }
                            }
                        }
                    }
                }else if( p instanceof Sala){
                    Sala s1 = (Sala)p;
                    System.out.println("RETORNOU UMA SALA " +s1.getCodigo());
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
        handleGerarGrafoSalas(null); // atualiza o grafo
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


    public ObservableList<Faculdade> getLista() {
        ObservableList<Faculdade> lista = FXCollections.observableArrayList(
        );
        lista.add(f1);

        return lista;
    }

    public ObservableList<Curso> getCurso() {
        ObservableList<Curso> lista = FXCollections.observableArrayList(
        );
        for (String s : f1.getCursos().keys()
        ) {
            Curso c = f1.getCursos().get(s);
            lista.add(c);
        }
        return lista;
    }

    public ObservableList<Edificio> getEdificios() {
        ObservableList<Edificio> lista = FXCollections.observableArrayList(
        );

        for (String s : f1.getEdificios().keys()
        ) {
            Edificio e = f1.getEdificios().get(s);
            lista.add(e);
        }

        return lista;
    }

    public ObservableList<Sala> getSalas() {
        ObservableList<Sala> lista = FXCollections.observableArrayList(
        );
        for (Integer cod : f1.salas.keys()
        ) {
            Sala s = f1.salas.get(cod);
            lista.add(s);
        }

        return lista;
    }

    public ObservableList<Turma> getTurmas() {
        ObservableList<Turma> lista = FXCollections.observableArrayList(
        );
        for (String cod : f1.turmas.keys()
        ) {
            Turma t = f1.turmas.get(cod);
            lista.add(t);
        }
        return lista;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ///Tabela Faculdade

        ColNome.setCellValueFactory(new PropertyValueFactory<Faculdade, String>("Name"));
        TableView.setItems(getLista());

        ///Tabela Salas
        codigoSala.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("Codigo"));
        nrCadeiras.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("NrCadeiras"));
        nrTomadas.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("NrTomadas"));
        piso.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("Piso"));


        Turmas.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Sala, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Sala, String> s) {
                if (s.getValue() != null) {
                    StringBuilder turmas = new StringBuilder();
                    for (Turma t : s.getValue().getTurmas()) {
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

        nomeCurso.setCellValueFactory(new PropertyValueFactory<Curso, String>("Nome"));
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
        nomeEdificio.setCellValueFactory(new PropertyValueFactory<Edificio, String>("Nome"));
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
                    for (Sala s : e.getValue().getSalas()) {

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
        codigoTurma.setCellValueFactory(new PropertyValueFactory<Turma, String>("Codigo"));
        anoTurma.setCellValueFactory(new PropertyValueFactory<Turma, Integer>("Ano"));
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
                    for (Integer cod : t.getValue().getAlunos().keys()) {
                        Aluno a = t.getValue().getAlunos().get(cod);
                        alunos.append(a.getNumeroAluno() + " " + a.getNome() + " " + a.getApelido()).append("\n");
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

        showSalasComboBox();
        showAlunosComboBox();
        showPdpComboBox();
    }

}
