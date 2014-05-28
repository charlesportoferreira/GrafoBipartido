/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2md6;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class Aresta {
    
    private Vertice verticeA;
    private Vertice verticeB;
    private int id;
    
    public Aresta(Vertice verticeA, Vertice verticeB) {
        this.verticeA = verticeA;
        this.verticeA.addAresta(this);
        this.verticeB = verticeB;
        this.verticeB.addAresta(this);
    }
    
    public Aresta() {
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Vertice getVerticeA() {
        return verticeA;
    }
    
    public void setVerticeA(Vertice verticeA) {
        if (this.verticeA != null) {
            throw new RuntimeException("Aresta ja possui vertice " + this.verticeA);
        }
        this.verticeA = verticeA;
    }
    
    public Vertice getVerticeB() {
        return verticeB;
    }
    
    public void setVerticeB(Vertice verticeB) {
        if (this.verticeB != null) {
            throw new RuntimeException("Aresta ja possui vertice " + this.verticeB);
        }
        this.verticeB = verticeB;
    }
    
}
