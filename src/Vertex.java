/**
 * NAME: Huilai Miao
 * PID: A14482851
 */

import java.util.ArrayList;

public class Vertex {

    private String name; // the name of this vertex
    private int x; // the x coordinates of this vertex on map
    private int y; // the y coordinates of this vertex on map
    private Vertex root;
    private int size;


    public ArrayList<Edge> adjacentEdges; // the adjacent edges of this vertex

    // TODO: add additional instance variables to work with Disjoint Set

    public Vertex(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
        adjacentEdges = new ArrayList<>();
        root = this;
        size = 1;
        // TODO: initialize your new instance variables
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // TODO: add getters and setters for your new instance variables
    public void setRoot(Vertex root){this.root = root;}

    public Vertex getRoot(){return root;}

    public void setSize(int s){this.size = s;}

    public int getSize(){return size;}

    public double getDistanceTo(Vertex o) {
        double squareDis = Math.pow(x - o.getX(), 2) + Math.pow(y - o.getY(), 2);
        return Math.sqrt(squareDis);
    }

    public void addEdge(Edge edge) {
        adjacentEdges.add(edge);
    }

    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Vertex)) {
            return false;
        }
        Vertex oVertex = (Vertex) o;

        return name.equals(oVertex.name) && x == oVertex.x && y == oVertex.y;
    }

    public String toString() {
        return name + " (" + x + ", " + y + ")";
    }

}