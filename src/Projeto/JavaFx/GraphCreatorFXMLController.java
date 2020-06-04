package Projeto.JavaFx;

import Projeto.Graph_project;
import edu.princeton.cs.algs4.In;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.Random;

public class GraphCreatorFXMLController {

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
