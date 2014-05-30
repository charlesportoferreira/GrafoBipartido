/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2md7;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Vertice {

    public static int RED = 1;
    public static int BLACK = 0;

    private int cor;
    private final List<Aresta> arestas;
    private String id;
    private int nrComponente;
    private String pai;
    private int profundidade;
    boolean isVisitado = false;
    boolean circuitoOrigem = false;
    boolean circuitoDestino = false;

    public int getNrComponente() {
        return nrComponente;
    }

    public void setNrComponente(int nrComponente) {
        this.nrComponente = nrComponente;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }

    public Vertice(int id, int cor) {
        this.cor = cor;
        arestas = new ArrayList<>();
    }

    public Vertice(String id) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Vertice> pintarVizinhos(int cor) {
        List<Vertice> verticesPintados = new ArrayList<>();
        for (Aresta aresta : arestas) {
            if (aresta.getVerticeA() != null && !aresta.getVerticeA().getId().equals(this.id)) {
                if (aresta.getVerticeA().getCor() != this.cor) {
                    if (aresta.getVerticeA().getCor() != cor) {
                        aresta.getVerticeA().setCor(cor);
                        verticesPintados.add(aresta.getVerticeA());
                    }
                } else {
                    aresta.setId(101);
                    buscaCircuitoImpar(aresta);
                    throw new RuntimeException("Grafo não é bipartido");
                }
            } else if (aresta.getVerticeB() != null && aresta.getVerticeB().getCor() != this.cor) {
                if (aresta.getVerticeB().getCor() != cor) {
                    aresta.getVerticeB().setCor(cor);
                    verticesPintados.add(aresta.getVerticeB());
                }
            } else {
                aresta.setId(101);
                buscaCircuitoImpar(aresta);
                throw new RuntimeException("Grafo não é bipartido");
            }
        }
        return verticesPintados;
    }

    public void buscaCircuitoImpar(Aresta a) {
        
        Stack<Vertice> pilha = new Stack<>();
        Vertice vOrigem = a.getVerticeA();
        Vertice vDestino = a.getVerticeB();
        a.getVerticeA().setPai("");
        a.getVerticeA().setProfundidade(0);
        a.getVerticeA().isVisitado = true;
        a.getVerticeA().circuitoOrigem = true;
        a.getVerticeB().circuitoDestino = true;
        
        pilha.add(vOrigem);

        while (!pilha.empty()) {
            Vertice v = pilha.pop();
            for (Aresta ar : v.arestas) {
                if (ar.getId() == 101) {
                    continue;
                }
                if (!ar.getVerticeA().getId().equals(vOrigem.getId()) && !ar.getVerticeA().isVisitado) {
                    ar.getVerticeA().setPai(String.valueOf(v.getId()));
                    ar.getVerticeA().setProfundidade(v.profundidade + 1);
                    ar.getVerticeA().isVisitado = true;
                    pilha.push(ar.getVerticeA());
                    if (ar.getVerticeA().getId().equals(vDestino.getId())) {
                        //System.out.println("Achei");
                        return;
                    }
                } else {
                    if (!ar.getVerticeB().isVisitado) {
                        ar.getVerticeB().setPai(String.valueOf(v.getId()));
                        ar.getVerticeB().setProfundidade(v.profundidade + 1);
                        ar.getVerticeB().isVisitado = true;
                        pilha.push(ar.getVerticeB());
                        if (ar.getVerticeB().getId().equals(vDestino.getId())) {
                           // System.out.println("Achei");
                            return;
                        }
                    }
                }
            }
        }

    }

   

}
