/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2md6;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Vertice {

    public static int RED = 1;
    public static int BLACK = 0;

    private int cor;
    private final List<Aresta> arestas;
    private int id;
    private int nrComponente;

    public int getNrComponente() {
        return nrComponente;
    }

    public void setNrComponente(int nrComponente) {
        this.nrComponente = nrComponente;
    }
    
    

    public Vertice(int id, int cor) {
        this.cor = cor;
        arestas = new ArrayList<>();
    }

    public Vertice(int id) {
        this.id = id;
        this.cor = -1;
        arestas = new ArrayList<>();
    }

    public void addAresta(Aresta aresta) {
        arestas.add(aresta);
    }

    public int getCor() {
        return cor;
    }

    public void setCor(int cor) {
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Vertice> pintarVizinhos(int cor) {
        List<Vertice> verticesPintados = new ArrayList<>();
        for (Aresta aresta : arestas) {
            if (aresta.getVerticeA() != null && aresta.getVerticeA().getId() != this.id) {
                if (aresta.getVerticeA().getCor() != this.cor) {
                    if (aresta.getVerticeA().getCor() != cor) {
                        aresta.getVerticeA().setCor(cor);
                        verticesPintados.add(aresta.getVerticeA());
                    }
                } else {
                    throw new RuntimeException("Grafo não é bipartido");
                }
            } else if (aresta.getVerticeB() != null && aresta.getVerticeB().getCor() != this.cor) {
                if (aresta.getVerticeB().getCor() != cor) {
                    aresta.getVerticeB().setCor(cor);
                    verticesPintados.add(aresta.getVerticeB());
                }
            } else {
                throw new RuntimeException("Grafo não é bipartido");
            }
        }
        return verticesPintados;
    }

}
