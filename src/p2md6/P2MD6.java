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
public class P2MD6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Vertice> verticesVermelhos = new ArrayList<>();
        List<Vertice> verticesAzuis = new ArrayList<>();

        while (!verticesVermelhos.isEmpty() && !verticesAzuis.isEmpty()) {
            for (Vertice vertice : verticesVermelhos) {
                verticesAzuis.addAll(vertice.pintarVizinhos(Vertice.BLACK));
            }
            verticesVermelhos.clear();

            for (Vertice vertice : verticesAzuis) {
                verticesVermelhos.addAll(vertice.pintarVizinhos(Vertice.RED));
            }
        }

        for (Vertice vertice : verticesAzuis) {
            System.out.print(vertice.getId() + " ");
        }
        System.out.println();
        for (Vertice vertice : verticesVermelhos) {
            System.out.print(vertice.getId() + " ");
        }

    }

}
