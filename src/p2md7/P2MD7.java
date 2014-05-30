/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2md7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class P2MD7 {

    //public static List<Vertice> verticesVermelhos = new ArrayList<>();
    //public static List<Vertice> verticesAzuis = new ArrayList<>();
    public static int nrComponente = 0;
    public static HashMap<String, Vertice> ciclo = new HashMap<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Vertice> todosVertices = new ArrayList<>();

        Vertice v0 = new Vertice("0");
        Vertice v1 = new Vertice("1");
        Vertice v2 = new Vertice("2");
        Vertice v3 = new Vertice("3");
        Vertice v4 = new Vertice("4");
        Vertice v5 = new Vertice("5");
        Vertice v6 = new Vertice("6");

        Vertice v7 = new Vertice("7");
        Vertice v8 = new Vertice("8");
        Vertice v9 = new Vertice("9");
        Vertice v10 = new Vertice("10");
        Vertice v11 = new Vertice("11");
        Vertice v12 = new Vertice("12");

        // todosVertices.add(v0);
        todosVertices.add(v1);
        todosVertices.add(v2);
        todosVertices.add(v3);
        todosVertices.add(v4);
        todosVertices.add(v5);
        todosVertices.add(v6);
        todosVertices.add(v7);
        todosVertices.add(v8);

        List<Vertice> vertices = new ArrayList(todosVertices);

        //  HashMap<Integer, Vertice> ciclo = new HashMap<>();
        for (Vertice vertice : vertices) {
            ciclo.put(vertice.getId(), vertice);
        }
//        todosVertices.add(v7);
//        todosVertices.add(v8);
//        todosVertices.add(v9);
//        todosVertices.add(v10);
//        todosVertices.add(v11);
//        todosVertices.add(v12);

        // Aresta a1 = new Aresta(v0, v1);
        Aresta a2 = new Aresta(v1, v2);
        Aresta a3 = new Aresta(v1, v3);
        Aresta a4 = new Aresta(v2, v4);
        Aresta a5 = new Aresta(v2, v5);
        Aresta a6 = new Aresta(v4, v6);
        Aresta a7 = new Aresta(v4, v7);
        Aresta a8 = new Aresta(v7, v8);
//        Aresta a9 = new Aresta(v5, v8);

        //-----teste 
        //Aresta a5050 = new Aresta(v2, v4);
        //-----teste
//        Aresta a9 = new Aresta(v6, v7);
//        Aresta a10 = new Aresta(v7, v8);
//        Aresta a11 = new Aresta(v8, v10);
//        Aresta a12 = new Aresta(v10, v9);
//        Aresta a13 = new Aresta(v9, v11);
//        Aresta a14 = new Aresta(v10, v12);
//        Aresta a15 = new Aresta(v11, v12);
        if (isBipartido(todosVertices)) {
            System.out.println("É um grafo bipartido");
            System.out.print("Particao 1: ");
            for (Vertice vertice : vertices) {
                if (vertice.getNrComponente() == 1 && vertice.getCor() == Vertice.BLACK) {
                    System.out.print(vertice.getId() + " ");
                }
            }
            System.out.println();
            System.out.print("Particao 2: ");
            
            for (Vertice vertice : vertices) {
                if (vertice.getNrComponente() == 1 && vertice.getCor() == Vertice.RED) {
                    System.out.print(vertice.getId() + " ");
                }
            }

            System.out.println();

        } else {
            System.out.println("Não é um grafo bipartido");
            imprimeCircuitoImpar(vertices);
        }

        if (nrComponente < 2) {
            System.out.println("Grafo Conexo");
        } else {
            System.out.println("Grafo Desconexo");
        }

    }

    public static boolean isBipartido(List<Vertice> todosVertices) {
        List<Vertice> verticesVermelhos = new ArrayList<>();
        List<Vertice> verticesAzuis = new ArrayList<>();

        while (!todosVertices.isEmpty()) {

            if (todosVertices.get(0).getCor() != -1) {
                todosVertices.remove(0);
                continue;
            } else {
                Vertice v2 = todosVertices.get(0);
                todosVertices.remove(0);
                v2.setCor(Vertice.RED);
                verticesVermelhos.add(v2);

            }

            nrComponente++;

            while (!verticesVermelhos.isEmpty() || !verticesAzuis.isEmpty()) {
                try {
                    for (Vertice vertice : verticesVermelhos) {
                        verticesAzuis.addAll(vertice.pintarVizinhos(Vertice.BLACK));
                        vertice.setNrComponente(nrComponente);
                    }
                    verticesVermelhos.clear();

                    for (Vertice vertice : verticesAzuis) {
                        verticesVermelhos.addAll(vertice.pintarVizinhos(Vertice.RED));
                        vertice.setNrComponente(nrComponente);
                    }
                    verticesAzuis.clear();
                } catch (RuntimeException ex) {
                    return false;
                }
            }

            //System.out.println("Achei 1 componente");
        }
        return true;
    }

    public static void imprimeCircuitoImpar(List<Vertice> todosVertices) {
        System.out.println("Circuito impar:");
        Vertice destino = todosVertices.get(0);
        for (Vertice vertice : todosVertices) {
            if (vertice.circuitoDestino) {
                destino = vertice;
                break;
            }
        }
        System.out.print(destino.getId() + " ");
        Vertice v = ciclo.get(destino.getPai());

        while (!v.circuitoOrigem) {
            System.out.print(v.getId() + " ");
            v = ciclo.get(v.getPai());
        }
        System.out.print(v.getId() + " ");
        System.out.println(destino.getId());
    }

}
