package Projeto;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Out;
import javafx.collections.ObservableList;

import java.io.*;

import static Projeto.Faculdade.*;
import static Projeto.JavaFx.GraphCreator.graph_pdpSalas;

public class Graph_project <T extends Point> {
    public static SymbolDigraphWeighted subGraphPdpSalas;

    public void conectGraphs(T v1,T v2,SymbolDigraphWeighted g,String path, Integer w) {
        if(v1 instanceof PontosDePassagem){
            PontosDePassagem var1 = (PontosDePassagem)v1;
            if(v2 instanceof Sala){
                Sala var2 = (Sala)v2;
                conect_PdpSalas(var1,var2,g,path,w);
            }else if(v2 instanceof PontosDePassagem) {
                PontosDePassagem var2 = (PontosDePassagem)v2;
                conectPdpPdp(var1,var2,g,path,w);
            }
        }else {
            if(v1 instanceof Sala){
                Sala var1 = (Sala) v1;
                if(v2 instanceof Sala){
                    Sala var2 = (Sala) v2;
                    conectSalasSalas(var1,var2,g,path,w);
                }
            }
        }
    }

    public void conectSalasSalas(Sala s1,Sala s2,SymbolDigraphWeighted g,String path, Integer w) {
        for (int v = 0; v < g.digraph().V(); v++) {       //percorre os vertices
            if (Integer.parseInt(g.nameOf(v)) == s1.getCodigo()) {     //se o vertice for igual ao codigo da sala
                for (int vi = 0; vi < g.digraph().V(); vi++) { // percorro novamente os vertices
                    if (Integer.parseInt(g.nameOf(vi)) == s2.getCodigo()) { // se o vertice for igual ao codigo da sala 2 faco a ligacao do 2 vertices
                        g.digraph().addEdge(new DirectedEdge(v, vi, w)); //adiciona ligaçao entre salas
                        g.digraph().addEdge(new DirectedEdge(vi, v, w)); //adiciona ligaçao entre salas
                        writeSalasPdpTxt(g, path);
                    }
                }
            }
        }
    }

    public void conectPdpPdp(PontosDePassagem p1,PontosDePassagem p2,SymbolDigraphWeighted g,String path, Integer w) {
        for (int v = 0; v < g.digraph().V(); v++) {       //percorre os vertices
            if (Integer.parseInt(g.nameOf(v)) == p1.getCod()) {     //se o vertice for igual ao codigo da sala
                for (int vi = 0; vi < g.digraph().V(); vi++) { // percorro novamente os vertices
                    if (Integer.parseInt(g.nameOf(vi)) == p2.getCod()) { // se o vertice for igual ao codigo da sala 2 faco a ligacao do 2 vertices
                        g.digraph().addEdge(new DirectedEdge(v, vi, w)); //adiciona ligaçao entre salas
                        g.digraph().addEdge(new DirectedEdge(vi, v, w)); //adiciona ligaçao entre salas
                        writeSalasPdpTxt(g, path); // como vai ser igual as salas posso chamar o mesmo
                    }
                }
            }
        }
    }

    public void conect_PdpSalas(PontosDePassagem p1,Sala s2,SymbolDigraphWeighted g,String path, Integer w) {
        for (int v = 0; v < g.digraph().V(); v++) {       //percorre os vertices
            if (Integer.parseInt(g.nameOf(v)) == p1.getCod()) {     //se o vertice for igual ao codigo da sala
                for (int vi = 0; vi < g.digraph().V(); vi++) { // percorro novamente os vertices
                    if (Integer.parseInt(g.nameOf(vi)) == s2.getCodigo()) { // se o vertice for igual ao codigo da sala 2 faco a ligacao do 2 vertices
                        g.digraph().addEdge(new DirectedEdge(v, vi, w)); //adiciona ligaçao entre salas e pdp
                        g.digraph().addEdge(new DirectedEdge(vi, v, w)); //adiciona ligaçao entre salas e pdp
                        writeSalasPdpTxt(g, path);
                    }
                }
            }
        }
    }

    public void writeSalasPdpTxt(SymbolDigraphWeighted g, String path) {
        Out out = new Out(path);
        for (int v = 0; v < g.digraph().V(); v++) {       //percorre os vertices
            int i = salasOuPdp(g,v); // procura se é sala ou pdp
            if(i == 0){
                out.print("s" + ";" + v + ";");
            }else if(i== 1){
                out.print("pdp" + ";" + v + ";");
            }else {
                System.out.println("Erro Vertice nao existe nas ST");
                return;
            }
            for (DirectedEdge d : g.digraph().adj(v)) {
                out.print(d.to() + ";" + d.weight() + ";");
            }
            out.print("\n");
        }
        out.close();
    }

    public int salasOuPdp(SymbolDigraphWeighted g, int v) {
        for (Integer s : salas.keys()) {
            if (s.toString().compareTo(g.nameOf(v)) == 0) {     //se a o cod coicidir entao é uma sala
                return 0;
            }
        }
        for (Integer p : pdp.keys()) {
            if (p == Integer.parseInt(g.nameOf(v))) {     //se o cod coicidir entao é pdp
                return 1;
            }
        }
        return 2;
    }

    public void guardar_pdp_txt_graph(String path) {
        edu.princeton.cs.algs4.Out out = new edu.princeton.cs.algs4.Out(path);
        for (Integer s : pdp.keys()) {
            out.print(s + ";" + "\n");
        }
        for (Integer s:salas.keys()) {
            out.print(s + ";" + "\n");
        }
        out.close();
    }

    public void guardarTxtSubGraph(String path, ObservableList<Sala> sala,ObservableList<String> pontosPdP,SymbolDigraphWeighted g) {
        edu.princeton.cs.algs4.Out out = new edu.princeton.cs.algs4.Out(path);
        boolean salasBoolean = false;
        boolean salasBoolean1 = false;
        boolean pdpBoolean = false;
        boolean pdpBoolean1 = false;

        if(pontosPdP.isEmpty() && sala.isEmpty()){
            guardar_pdp_txt_graph(path); // como nao selecionei nada vai criar um graph igual ao original
        }

        //////////////// PDP 1

        for (int v = 0; v < g.digraph().V(); v++) {       //percorre os vertices
            if (salasOuPdp(g,v) == 1) {
                for (String pdpAux : pontosPdP) {
                    String[] numbers = pdpAux.split(" ");
                    int aux = 0;
                    int numPdp = 0;
                    // Para pegar apenas no cod do PDP
                    for (String s : numbers) {
                        if (aux == 1) {
                            numPdp = Integer.parseInt(s.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira
                            if(Integer.parseInt(graph_pdpSalas.nameOf(v)) == numPdp){ // se tiver o pdp vou passar à frente
                                pdpBoolean = true;
                            }
                        }
                        aux++;
                    }
                }if(!pdpBoolean){ // aqui so entrem os grafos que nao sao eliminados
                   // System.out.print("pdp" + ";" + v + ";");
                    out.print("pdp" + ";" + v + ";");
                    //  out.print(salas.get(stSalas) + ";" + "\n");
                    for (DirectedEdge d: g.digraph().adj(v)) { // percorro o directed edge para caso o vertice a que este vertice esteja conectado seja eliminado ele o elimine
                        if(salasOuPdp(g,d.to()) == 0){ // se a sala estiver ligada a uma sala que vai ser eliminada
                            for (Sala sAux1 : sala) { // PERCORREMOS AS SALAS ENVIADAS ( AS QUE NAO QUEREMOS METER NO SUB GRAPH )
                                if(Integer.parseInt(graph_pdpSalas.nameOf(d.to()) ) == sAux1.getCodigo()){ // se tiver a sala vou passar à frente
                                    pdpBoolean1 = true;
                                }
                            }
                            if(!pdpBoolean1) {
                               // System.out.print(d.to() + ";" + d.weight() + ";");
                                out.print(d.to() + ";" + d.weight() + ";");
                            }
                        }if(salasOuPdp(g,d.to()) == 1){ // para o caso de estar ligado a um pdp que vai ser eliminado
                            for (String pdpAux : pontosPdP) {
                                String[] numbers = pdpAux.split(" ");
                                int aux = 0;
                                int numPdp = 0;
                                // Para pegar apenas no cod do PDP
                                for (String s : numbers) {
                                    if (aux == 1) {
                                        numPdp = Integer.parseInt(s.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira
                                        if(Integer.parseInt(graph_pdpSalas.nameOf(d.to()) ) == numPdp){ // se tiver o pdp vou passar à frente
                                            pdpBoolean1 = true;
                                        }
                                    }
                                    aux++;
                                }
                            }
                            if(!pdpBoolean1) {
                               // System.out.print(d.to() + ";" + d.weight() + ";");
                                out.print(d.to() + ";" + d.weight() + ";");
                            }
                        }
                        pdpBoolean1 = false;
                    }
                    System.out.println();
                    out.print("\n");
                }
                pdpBoolean = false;
            }
        }

        for (int v = 0; v < g.digraph().V(); v++) {       //percorre os vertices
            if (salasOuPdp(g,v) == 0) {
                for (Sala sAux : sala) { // PERCORREMOS AS SALAS ENVIADAS ( AS QUE NAO QUEREMOS METER NO SUB GRAPH )
                    if(Integer.parseInt(graph_pdpSalas.nameOf(v)) == sAux.getCodigo() ){
                        salasBoolean = true;
                    }
                }
                if(!salasBoolean){ // aqui so entrem os grafos que nao sao eliminados
                   // System.out.print("s" + ";" + v + ";");
                    out.print("s" + ";" + v + ";");
                  //  out.print(salas.get(stSalas) + ";" + "\n");
                    for (DirectedEdge d: g.digraph().adj(v)) { // percorro o directed edge para caso o vertice a que este vertice esteja conectado seja eliminado ele o elimine
                        if(salasOuPdp(g,d.to()) == 0){ // se a sala estiver ligada a uma sala que vai ser eliminada
                            for (Sala sAux1 : sala) { // PERCORREMOS AS SALAS ENVIADAS ( AS QUE NAO QUEREMOS METER NO SUB GRAPH )
                                if(Integer.parseInt(graph_pdpSalas.nameOf(d.to()) ) == sAux1.getCodigo()){ // se tiver a sala vou passar à frente
                                    salasBoolean1 = true;
                                }
                            }
                            if(!salasBoolean1) {
                               // System.out.print(d.to() + ";" + d.weight() + ";");
                                out.print(d.to() + ";" + d.weight() + ";");
                            }
                        }if(salasOuPdp(g,d.to()) == 1){ // para o caso de estar ligado a um pdp que vai ser eliminado
                            for (String pdpAux : pontosPdP) {
                                String[] numbers = pdpAux.split(" ");
                                int aux = 0;
                                int numPdp = 0;
                                // Para pegar apenas no cod do PDP
                                for (String s : numbers) {
                                    if (aux == 1) {
                                        numPdp = Integer.parseInt(s.replaceAll("[\\D]", "")); // para pegar apenas na parte inteira
                                        if(Integer.parseInt(graph_pdpSalas.nameOf(d.to()) ) == numPdp){ // se tiver o pdp vou passar à frente
                                            salasBoolean1 = true;
                                        }
                                    }
                                    aux++;
                                }
                            }
                            if(!salasBoolean1) {
                               // System.out.print(d.to() + ";" + d.weight() + ";");
                                out.print(d.to() + ";" + d.weight() + ";");
                            }
                        }
                        salasBoolean1 = false;
                    }
                   // System.out.println();
                    out.print("\n");
                }
               salasBoolean = false;
            }
        }
        out.close();
    }



    public Point pdpOuSalaMaisProxima(Aluno a){ // dá a localizacao da sala ou pdp mais proxima do aluno
        double dist_sala = 1000; // valor aleatorio
        double dist_pdp = 1000;
        int cod_sala = 0;
        int cod_pdp = 0;
        for (Integer s:salas.keys()) {
            Sala si = salas.get(s);
            if (salas.get(s).getZ() == a.getZ()){ // Só vale a pena verificar as salas do meu piso, pq as do piso superior tem um custo maior (estao + longe ) pq tenho de subir as escadas
                double aux = si.getX();
                if(aux - a.getX() < dist_sala && aux - a.getX() > 0){ // caso a diferenca entre a pos do aluno e da sala seja positiva
                    dist_sala = aux - a.getX();
                    cod_sala = si.getCodigo();
                }else if(aux - a.getX() < 0){
                    double num_negat = (aux - a.getX()) * -1; // torno a diferenca positiva
                    if(num_negat < dist_sala){
                        dist_sala = num_negat;
                        cod_sala = si.getCodigo();
                    }
                }else if(aux == a.getX()){
                    return si;
                }
            }
        }
        for (Integer p:pdp.keys()) {
            if (pdp.get(p).getZ() == a.getZ()){ // Só vale a pena verificar as salas do meu piso, pq as do piso superior tem um custo maior (estao + longe ) pq tenho de subir as escadas
                PontosDePassagem pi = pdp.get(p);
                double aux = pi.getX();
                if(aux - a.getX() < dist_pdp && aux - a.getX() > 0){ // caso a diferenca entre a pos do aluno e da sala seja positiva
                    dist_pdp = aux - a.getX();
                    cod_pdp = pi.getCod();
                }else if(aux - a.getX() < 0){
                    double num_negat = (aux - a.getX()) * -1; // torno a diferenca positiva
                    if(num_negat < dist_pdp){
                        dist_pdp = num_negat;
                        cod_pdp = pi.getCod();
                    }
                }else if(aux == a.getX()){
                    return pi;
                }
            }
        }
        if(dist_sala < dist_pdp){
            salas.get(cod_sala).distAlunoSalaProx = dist_sala; // para saber qt é que o aluno teve de andar para chegar à sala mais proxima
            return salas.get(cod_sala);
        }else if(dist_pdp < dist_sala){
            pdp.get(cod_pdp).distAlunoPdpProx = dist_pdp;
            return pdp.get(cod_pdp);
        }
        return null;
    }

    public int[] saidaDeEmergencia(Aluno a){ // dá a localizacao da sala ou pdp mais proxima do aluno
        // se for um pdp
        double aux = 1000;
        int[] arrayRetorno = new int[2]; // array que vai passar a distancia percorrida e o cod da saida de emergencia
        for (Integer pontosPdp:pdp.keys()) {
            if(pontosPdp == 0 || pontosPdp == 7 || pontosPdp == 8 || pontosPdp == 9){ // Códigos das saídas
                PontosDePassagem pontoPassagem = pdp.get(pontosPdp); // Pontos de passagem para aonde tenho de ir
                if (alunos.contains(a.getNumeroAluno())) {
                    Point p = pdpOuSalaMaisProxima(alunos.get(a.getNumeroAluno())); // retorna o pdp mais proximo do alunos (isto se ele nao tiver em nenhuma sala ou pdp)
                    if (p instanceof PontosDePassagem) {
                        PontosDePassagem p1 = (PontosDePassagem) p; // p1 = pdp ou sala aonde o aluno se encontra
                        for (int v = 0; v < graph_pdpSalas.digraph().V(); v++) {
                            if (pdp.get(p1.getCod()).getCod() == Integer.parseInt(graph_pdpSalas.nameOf(v))) { // vamos ver o vertice que corresponde ao pdp/sala que o aluno está
                                for (int vi = 0; vi < graph_pdpSalas.digraph().V(); vi++) {
                                    if (pontoPassagem.getCod() == Integer.parseInt(graph_pdpSalas.nameOf(vi))) {
                                        DijkstraSP_Projeto sp = new DijkstraSP_Projeto(graph_pdpSalas, v);
                                        if (sp.hasPathTo(vi)) {
                                            //f1.guardar_STALUNOS();
                                            double dist = sp.distTo(vi) + p1.distAlunoPdpProx; // distancia entre o ponto em que o aluno se encontra e as saidas
                                            if (dist < aux) {
                                                aux = dist;
                                                arrayRetorno[0] = (int) dist;
                                                arrayRetorno[1] = pontoPassagem.getCod();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }else if( p instanceof Sala){
                        Sala s1 = (Sala)p;
                        for (int v = 0; v < graph_pdpSalas.digraph().V();v++){
                            if(salas.get(s1.getCodigo()).getCodigo() == Integer.parseInt(graph_pdpSalas.nameOf(v))){ // vamos ver o vertice que corresponde ao pdp/sala que o aluno está
                                for (int vi = 0; vi < graph_pdpSalas.digraph().V(); vi++){
                                    if(pontoPassagem.getCod() == Integer.parseInt(graph_pdpSalas.nameOf(vi))){
                                        DijkstraSP_Projeto sp = new DijkstraSP_Projeto(graph_pdpSalas, v);
                                        if (sp.hasPathTo(vi)) {
                                            //f1.guardar_STALUNOS();
                                            double dist = sp.distTo(vi) + s1.distAlunoSalaProx; // distancia entre o ponto em que o aluno se encontra e as saidas
                                            if (dist < aux) {
                                                aux = dist;
                                                arrayRetorno[0] = (int) dist;
                                                arrayRetorno[1] = pontoPassagem.getCod();
                                            }
                                        }else {
                                            System.out.println("Sem Caminho Possivel");
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
        return arrayRetorno;
    }

    /**
     * FILES
     */
    public void saveBinGraph(SymbolDigraphWeighted g, String path) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
            oos.writeObject(g);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SymbolDigraphWeighted readBinGraph(SymbolDigraphWeighted g, String path) {
        ObjectInputStream ios = null;
        try {
            ios = new ObjectInputStream(new FileInputStream(new File(path)));
            g = (SymbolDigraphWeighted) ios.readObject();
            return g;
//            System.out.println(g.digraph());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
