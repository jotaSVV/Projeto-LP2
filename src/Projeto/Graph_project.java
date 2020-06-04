package Projeto;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.Out;

import static Projeto.Faculdade.salas;

public class Graph_project {

    public void guardar_salas_txt_graph(String path) {
        edu.princeton.cs.algs4.Out out = new edu.princeton.cs.algs4.Out(path);
        for (Integer s : salas.keys()) {
            out.print(s + ";" + "\n");
        }
        out.close();
    }
    public void conect_2_salas(Sala s1,Sala s2,SymbolGraphWheighted g,String path, Integer w) {
        for (int v = 0; v < g.graph().V(); v++) {       //percorre os vertices
            if (Integer.parseInt(g.nameOf(v)) == s1.getCodigo()) {     //se o vertice for igual ao codigo da sala
                for (int vi = 0; vi < g.graph().V(); vi++) { // percorro novamente os vertices
                    if (Integer.parseInt(g.nameOf(vi)) == s2.getCodigo()) { // se o vertice for igual ao codigo da sala 2 faco a ligacao do 2 vertices
                        g.graph().addEdge(new Edge(v, vi, w)); //adiciona ligaçao entre salas
                        write_salas_txt(g, path);
                    }
                }
           }
        }
    }

    public void write_salas_txt(SymbolGraphWheighted g, String path) {
        Out out = new Out(path);
        for (int v = 0; v < g.graph().V(); v++) {       //percorre os vertices
            out.print(v + ";");
            for (Edge d : g.graph().adj(v)) {
                out.print(d.other(v) + ";" + d.weight() + ";");
            }
            out.print("\n");
        }
        out.close();
    }


}
