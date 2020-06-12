package Projeto;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Out;
import javafx.collections.ObservableList;

import java.io.*;

import static Projeto.Faculdade.*;
import static Projeto.JavaFx.GraphCreator.graph_pdpSalas;

public class Graph_project <T extends Point> {
    public static SymbolDigraphWeighted subGraphPdpSalas;

    /**
     *
     * @param v1 Point 1
     * @param v2 Point 2
     * @param g graph
     * @param path caminho (txt) para onde vamos escrever as ligacoes
     * @param w peso
     * Estabelece a conexao entre 2 graph, para isso recebemos os 2 graphs e o peso da ligacao
     */
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

    /**
     *
     * @param s1 sala 1
     * @param s2 sala 2
     * @param g graph
     * @param path caminho ( txt )
     * @param w peso
     * Conecta a sala 1 à sala 2 e vice-versa, de seguida chama a funcao writeSalasPdpTxt que vai escrever as ligacoes no txt
     */
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

    /**
     *
     * @param p1 pdp 1
     * @param p2 pdp 2
     * @param g graph
     * @param path caminho ( txt )
     * @param w peso
     * Conecta o pdp 1 ao pdp 2 e vice-versa, de seguida chama a funcao writeSalasPdpTxt que vai escrever as ligacoes no txt
     */
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

    /**
     *
     * @param p1 pdp 1
     * @param s2 sala 2
     * @param g graph
     * @param path caminho ( txt )
     * @param w peso
     * Conecta o pdp 1 à sala 2 e vice-versa, de seguida chama a funcao writeSalasPdpTxt que vai escrever as ligacoes no txt
     */

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

    /**
     *
     * @param g graph
     * @param path caminho txt
     * Percorre o graph e vai verificar se o que percorremos é um pdp ou sala e escrevemos o vertice junto com as suas ligacoes
     */
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

    /**
     *
     * @param g graph
     * @param v vertice
     * @return 0 caso seja sala, 1 caso seja pdp e 2 caso nao seja nenhum
     * Verifica se o vertice é uma sala ou pdp
     */
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

    /**
     *
     * @param path caminho txt para onde vamos escrever
     * Escrevemos no txt as chaves dos pdp e salas para de seguida criar o graph
     */
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

    /**
     *
     * @param path caminho txt
     * @param sala sala que vamos eliminar
     * @param g graph
     * Percorremos os vertices , se estes forem salas entao vemos se é ou nao a sala que queremos eliminar
     * se for entao nao escrevemos nada , se nao for escrevemos a sala e de seguida vamos percorrer as suas conexoes
     * se numa das conexoes tiver a sala que queremos eliminar nao a escrevemos
     */
    public void removerSalasGraph(String path, Point sala,SymbolDigraphWeighted g) {
        edu.princeton.cs.algs4.Out out = new edu.princeton.cs.algs4.Out(path);
        boolean salasBoolean = false;
        boolean salasBoolean1 = false;
        boolean pdpBoolean = false;
        Sala s0 = (Sala) sala;
        // PDP 1
        for (int v = 0; v < g.digraph().V(); v++) {       //percorre os vertices
            if (salasOuPdp(g,v) == 1) {
                    out.print("pdp" + ";" + v + ";"); // escrevo sempre os vertices, pq apenas queremos cortar as ligacoes dos vertices
                    for (DirectedEdge d: g.digraph().adj(v)) { // percorro o directed edge para caso o vertice a que este vertice esteja conectado seja eliminado ele o elimine
                        if(salasOuPdp(g,d.to()) == 0){ // se a o PDP estiver ligada a uma sala que vai ser eliminada
                            if(Integer.parseInt(g.nameOf(d.to()) ) == s0.getCodigo()){ // se tiver a sala vou passar à frente
                                pdpBoolean = true; // para saber se a ligacao foi cortada ou nao
                            }
                            if(!pdpBoolean) { // se nao foi cortada entao pode escrever a ligacao
                                out.print(d.to() + ";" + d.weight() + ";"); // Só escreve as conexoes se estas nao forem a sala eliminada
                            }
                        }else if(salasOuPdp(g,d.to()) == 1){ // para o caso de estar ligado a um pdp que vai ser eliminado
                            out.print(d.to() + ";" + d.weight() + ";"); // como é um PDP e nao vamos eliminar os PDP escrevemos sempre
                        }
                        pdpBoolean = false;
                    }
                out.print("\n");
            }else if (salasOuPdp(g,v) == 0) {
                if(Integer.parseInt(g.nameOf(v)) == s0.getCodigo() ){
                    salasBoolean = true;
                }
                if(!salasBoolean){ // aqui so entrem os grafos que nao sao eliminados
                    out.print("s" + ";" + v + ";"); // só escreve a sala se esta nao foi eliminada
                    for (DirectedEdge d: g.digraph().adj(v)) { // percorro o directed edge para caso o vertice a que este vertice esteja conectado seja eliminado ele o elimine
                        if(salasOuPdp(g,d.to()) == 0){ // se a sala estiver ligada a uma sala que vai ser eliminada
                            if(Integer.parseInt(g.nameOf(d.to()) ) == s0.getCodigo()){ // se tiver a sala vou passar à frente
                                salasBoolean1 = true;
                            }
                            if(!salasBoolean1) {
                                out.print(d.to() + ";" + d.weight() + ";");
                            }
                        }if(salasOuPdp(g,d.to()) == 1){ // para o caso de estar ligado a um pdp escreve sempre pq nesta funcao nao vamos eliminar pdp
                            out.print(d.to() + ";" + d.weight() + ";");
                        }
                        salasBoolean1 = false;
                    }
                }
                if(!salasBoolean){
                    out.print("\n");
                }
                salasBoolean = false;
            }
        }
        out.close();
    }

    /**
     *
     * @param path caminho txt
     * @param pontoPdp pdp que vamos eliminar
     * @param g graph
     * Percorremos os vertices , se estes forem pdp entao vemos se é ou nao o pdp que queremos eliminar
     * se for entao nao escrevemos nada , se nao for escrevemos o pdp e de seguida vamos percorrer as suas conexoes
     * se numa das conexoes tiver o pdp que queremos eliminar nao o escrevemos
     */
    public void removerPdpGraph(String path, Point pontoPdp,SymbolDigraphWeighted g) { /// TEM UM BUG QUALQUER
        edu.princeton.cs.algs4.Out out = new edu.princeton.cs.algs4.Out(path);
        boolean salasBoolean = false;
        boolean salasBoolean1 = false;
        boolean pdpBoolean = false;
        PontosDePassagem p0 = (PontosDePassagem) pontoPdp;
        // PDP 1
        for (int v = 0; v < g.digraph().V(); v++) {       //percorre os vertices
            if (salasOuPdp(g,v) == 1) {
                if(Integer.parseInt(g.nameOf(v)) == p0.getCod() ){
                    salasBoolean = true;
                }
                if(!salasBoolean){ // aqui so entrem os grafos que nao sao eliminados
                    out.print("pdp" + ";" + v + ";"); // só escreve Pdp se esta nao foi eliminada
                    for (DirectedEdge d: g.digraph().adj(v)) { // percorro o directed edge para caso o vertice a que este vertice esteja conectado seja eliminado
                        if(salasOuPdp(g,d.to()) == 1){ // se o PDP estiver ligada a uma sala que vai ser eliminada
                            if(Integer.parseInt(g.nameOf(d.to()) ) == p0.getCod()){ // se tiver o Pdp vou passar à frente
                                salasBoolean1 = true;
                            }
                            if(!salasBoolean1) { // se nao tiver o pdp escrevo normal as conexoes
                                out.print(d.to() + ";" + d.weight() + ";");
                            }
                        }if(salasOuPdp(g,d.to()) == 0){ // para o caso de estar ligado a uma Sala escreve sempre pq nesta funcao nao vamos eliminar as Salas
                            out.print(d.to() + ";" + d.weight() + ";");
                        }
                        salasBoolean1 = false;
                    }
                }
                if(!salasBoolean){
                    out.print("\n");
                }
                salasBoolean = false;
            }else if (salasOuPdp(g,v) == 0) {
                out.print("s" + ";" + v + ";"); // escrevo sempre os vertices, pq apenas queremos cortar as ligacoes dos vertices
                for (DirectedEdge d: g.digraph().adj(v)) { // percorro o directed edge para caso o vertice a que este vertice esteja conectado seja eliminado ele o elimine
                    if(salasOuPdp(g,d.to()) == 1){
                        if(Integer.parseInt(g.nameOf(d.to()) ) == p0.getCod()){ // se tiver o PDP vou passar à frente
                            pdpBoolean = true; // para saber se a ligacao foi cortada ou nao
                        }
                        if(!pdpBoolean) { // se nao foi cortada entao pode escrever a ligacao
                            out.print(d.to() + ";" + d.weight() + ";"); // Só escreve as conexoes se estas nao forem a sala eliminada
                        }
                    }else if(salasOuPdp(g,d.to()) == 0){ // para o caso de estar ligado a uma sala
                        out.print(d.to() + ";" + d.weight() + ";"); // como é uma Sala e nao vamos eliminar as Salas escrevemos sempre
                    }
                    pdpBoolean = false;
                }
                out.print("\n");
            }
        }
        out.close();
    }

    /**
     *
     * @param path caminho txt
     * @param sala ObservableList de salas ( salas que vamos "eliminar" )
     * @param pontosPdP ObservableList de pdp ( pdp que vamos "eliminar" )
     * @param g graph
     * @param g1 novo graph (sub Graph)
     * Percorremos os graph e escrevemos os vertices todos, em cada vertice vamos verificar se as conexoes foram eliminadas
     * (isto e, se a sala ou pdp a que estao conectados foi eliminada, estiver nas ObservableLists), se forem eliminadas nao vamos
     *  escrever essas ligacoes, se nao forem escrevemos tudo para txt
     */
    public void conexoesDoSubGraph(String path, ObservableList<Sala> sala,ObservableList<String> pontosPdP,SymbolDigraphWeighted g,SymbolDigraphWeighted g1) {
        edu.princeton.cs.algs4.Out out = new edu.princeton.cs.algs4.Out(path);
        boolean salasBoolean = false;
        boolean salasBoolean1 = false;
        boolean pdpBoolean = false;
        boolean pdpBoolean1 = false;

        // PDP 1
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
                }
                out.print("pdp" + ";" + v + ";"); // escrevo sempre os vertices, pq apenas queremos cortar as ligacoes dos vertices
                if(!pdpBoolean){ // aqui so entrem os grafos que nao sao eliminados
                    for (DirectedEdge d: g.digraph().adj(v)) { // percorro o directed edge para caso o vertice a que este vertice esteja conectado seja eliminado ele o elimine
                        if(salasOuPdp(g,d.to()) == 0){ // se a sala estiver ligada a uma sala que vai ser eliminada
                            for (Sala sAux1 : sala) { // PERCORREMOS AS SALAS ENVIADAS ( AS QUE NAO QUEREMOS METER NO SUB GRAPH )
                                if(Integer.parseInt(graph_pdpSalas.nameOf(d.to()) ) == sAux1.getCodigo()){ // se tiver a sala vou passar à frente
                                    pdpBoolean1 = true; // para saber se a ligacao foi cortada ou nao
                                }
                            }
                            if(!pdpBoolean1) { // se nao foi cortada entao pode escrever a ligacao
                                g1.digraph().addEdge(new DirectedEdge(v, d.to(),d.weight())); //adiciona ligaçao entre salas e pdp
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
                                g1.digraph().addEdge(new DirectedEdge(v, d.to(),d.weight())); //adiciona ligaçao entre salas e pdp
                                out.print(d.to() + ";" + d.weight() + ";");
                            }
                        }
                        pdpBoolean1 = false;
                    }
                }
                out.print("\n");
                pdpBoolean = false;
            }
        }
        // Remover salas
        for (int v = 0; v < g.digraph().V(); v++) {       //percorre os vertices
            if (salasOuPdp(g,v) == 0) {
                for (Sala sAux : sala) { // PERCORREMOS AS SALAS ENVIADAS ( AS QUE NAO QUEREMOS METER NO SUB GRAPH )
                    if(Integer.parseInt(graph_pdpSalas.nameOf(v)) == sAux.getCodigo() ){
                        salasBoolean = true;
                    }
                }
                out.print("s" + ";" + v + ";");
                if(!salasBoolean){ // aqui so entrem os grafos que nao sao eliminados
                    for (DirectedEdge d: g.digraph().adj(v)) { // percorro o directed edge para caso o vertice a que este vertice esteja conectado seja eliminado ele o elimine
                        if(salasOuPdp(g,d.to()) == 0){ // se a sala estiver ligada a uma sala que vai ser eliminada
                            for (Sala sAux1 : sala) { // PERCORREMOS AS SALAS ENVIADAS ( AS QUE NAO QUEREMOS METER NO SUB GRAPH )
                                if(Integer.parseInt(graph_pdpSalas.nameOf(d.to()) ) == sAux1.getCodigo()){ // se tiver a sala vou passar à frente
                                    salasBoolean1 = true;
                                }
                            }
                            if(!salasBoolean1) {
                                g1.digraph().addEdge(new DirectedEdge(v, d.to(),d.weight())); //adiciona ligaçao entre salas e pdp
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
                                g1.digraph().addEdge(new DirectedEdge(v, d.to(),d.weight())); //adiciona ligaçao entre salas e pdp
                                out.print(d.to() + ";" + d.weight() + ";");
                            }
                        }
                        salasBoolean1 = false;
                    }
                }
                out.print("\n");
               salasBoolean = false;
            }
        }
        out.close();
    }

    /**
     *
     * @param a aluno
     * @return o Point (sala ou pdp) mais proxima do aluno
     * Como temos uma sala ou pdp em cada piso vamos apenas comparar com as salas/pdp do piso em que o aluno se encontra
     * de seguida vamos calcular a sala que se encontra mais proxima, atraves da coordenada x
     */
    public Point pdpOuSalaMaisProxima(Point a){ // dá a localizacao da sala ou pdp mais proxima do aluno
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

    /**
     *
     * @param a Point aluno ou professor
     * @param graph graph
     * @return array que retorna a distancia percorrida e o cod da saida de emergencia
     * Percorre apenas os pdp de saida (0,7,8,9), caso o aluno ou professor ainda nao esteja em uma sala ou pdp colocamo-lo na sala ou pdp mais proximo
     * de seguida fazemos a distancia entre a posicao do aluno e todas as saidas, a saida que estiver mais proxima (calculamos a distancia atraves do dijkstra)
     * é a que retornamos
     */
    public int[] saidaDeEmergencia(Point a, SymbolDigraphWeighted graph){ // dá a localizacao da sala ou pdp mais proxima do aluno
        if(a instanceof Aluno) {
            Aluno a1 = (Aluno) a;
            // se for um pdp
            double aux = 1000;
            int[] arrayRetorno = new int[2]; // array que vai passar a distancia percorrida e o cod da saida de emergencia
            for (Integer pontosPdp:pdp.keys()) {
                if(pontosPdp == 0 || pontosPdp == 7 || pontosPdp == 8 || pontosPdp == 9){ // Códigos das saídas
                    PontosDePassagem pontoPassagem = pdp.get(pontosPdp); // Pontos de passagem para aonde tenho de ir
                    if (alunos.contains(a1.getNumeroAluno())) {
                        Point p = pdpOuSalaMaisProxima(alunos.get(a1.getNumeroAluno())); // retorna o pdp mais proximo do alunos (isto se ele nao tiver em nenhuma sala ou pdp)
                        if (p instanceof PontosDePassagem) { // caso o aluno esteja em um pdp
                            PontosDePassagem p1 = (PontosDePassagem) p; // p1 = pdp ou sala aonde o aluno se encontra
                            for (int v = 0; v < graph.digraph().V(); v++) {
                                if (pdp.get(p1.getCod()).getCod() == Integer.parseInt(graph.nameOf(v))) { // vamos ver o vertice que corresponde ao pdp/sala que o aluno está
                                    for (int vi = 0; vi < graph.digraph().V(); vi++) {
                                        if (pontoPassagem.getCod() == Integer.parseInt(graph.nameOf(vi))) {
                                            DijkstraSP_Projeto sp = new DijkstraSP_Projeto(graph, v);
                                            if (sp.hasPathTo(vi)) {
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
                        }else if( p instanceof Sala){ // caso o aluno esteja em uma sala
                            Sala s1 = (Sala)p;
                            for (int v = 0; v < graph.digraph().V();v++){
                                if(salas.get(s1.getCodigo()).getCodigo() == Integer.parseInt(graph.nameOf(v))){ // vamos ver o vertice que corresponde ao pdp/sala que o aluno está
                                    for (int vi = 0; vi < graph.digraph().V(); vi++){
                                        if(pontoPassagem.getCod() == Integer.parseInt(graph.nameOf(vi))){
                                            DijkstraSP_Projeto sp = new DijkstraSP_Projeto(graph, v);
                                            if (sp.hasPathTo(vi)) {
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
        }else if(a instanceof Professor){ // PROFESSOR
            Professor a1 = (Professor) a;
            // se for um pdp
            double aux = 1000;
            int[] arrayRetorno = new int[2]; // array que vai passar a distancia percorrida e o cod da saida de emergencia
            for (Integer pontosPdp:pdp.keys()) {
                if(pontosPdp == 0 || pontosPdp == 7 || pontosPdp == 8 || pontosPdp == 9){ // Códigos das saídas
                    PontosDePassagem pontoPassagem = pdp.get(pontosPdp); // Pontos de passagem para aonde tenho de ir
                    if (professores.contains(a1.getEmail())) {
                        Point p = pdpOuSalaMaisProxima(professores.get(a1.getEmail())); // retorna o pdp mais proximo do alunos (isto se ele nao tiver em nenhuma sala ou pdp)
                        if (p instanceof PontosDePassagem) { // caso o professor esteja em um pdp
                            PontosDePassagem p1 = (PontosDePassagem) p; // p1 = pdp ou sala aonde o aluno se encontra
                            for (int v = 0; v < graph.digraph().V(); v++) {
                                if (pdp.get(p1.getCod()).getCod() == Integer.parseInt(graph.nameOf(v))) { // vamos ver o vertice que corresponde ao pdp/sala que o aluno está
                                    for (int vi = 0; vi < graph.digraph().V(); vi++) {
                                        if (pontoPassagem.getCod() == Integer.parseInt(graph.nameOf(vi))) {
                                            DijkstraSP_Projeto sp = new DijkstraSP_Projeto(graph, v);
                                            if (sp.hasPathTo(vi)) {
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
                        }else if( p instanceof Sala){ // caso o professor esteja em uma sala
                            Sala s1 = (Sala)p;
                            for (int v = 0; v < graph.digraph().V();v++){
                                if(salas.get(s1.getCodigo()).getCodigo() == Integer.parseInt(graph.nameOf(v))){ // vamos ver o vertice que corresponde ao pdp/sala que o aluno está
                                    for (int vi = 0; vi < graph.digraph().V(); vi++){
                                        if(pontoPassagem.getCod() == Integer.parseInt(graph.nameOf(vi))){
                                            DijkstraSP_Projeto sp = new DijkstraSP_Projeto(graph, v);
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
        return null;
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
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printSubGraph(SymbolDigraphWeighted g){
        for (int v = 0; v < g.digraph().V(); v++) {       //percorre os vertices
            int i = salasOuPdp(g,v); // procura se é sala ou pdp
            if(i == 0){
                System.out.print("s" + ";" + v + ";");
            }else if(i== 1){
                System.out.print("pdp" + ";" + v + ";");
            }else {
                System.out.println("Erro Vertice nao existe nas ST");
                return;
            }
            for (DirectedEdge d : g.digraph().adj(v)) {
                System.out.print(d.to() + ";" + d.weight() + ";");
            }
            System.out.println();
        }
    }
}
