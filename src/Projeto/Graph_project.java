package Projeto;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.Out;

import static Projeto.Faculdade.pdp;
import static Projeto.Faculdade.salas;
public class Graph_project <T extends Point> {

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


}
