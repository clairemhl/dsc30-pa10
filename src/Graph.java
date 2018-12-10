/**
 * NAME: Huilai Miao
 * PID: A14482851
 */

import java.util.*;

/*
 * TODO: add class header
 */
public class Graph {

    private HashMap<String, Vertex> vertices;
    private ArrayList<Edge> allUndirectedEdges;
    private ArrayList<Edge> resultMST;
    private boolean edgesGiven;
    private static final int SQUARE = 2;

    /**
     * Constructor for Graph
     */
    public Graph(boolean edgesGiven) {
        this.edgesGiven = edgesGiven;
        vertices = new HashMap<>();
        allUndirectedEdges = new ArrayList<>();
        resultMST = new ArrayList<>();
    }

    /**
     * Adds a vertex to the graph. Throws IllegalArgumentException if given vertex
     * already exist in the graph.
     *
     * @param v vertex to be added to the graph
     * @throws IllegalArgumentException if two vertices with the same name are added.
     */
    public void addVertex(Vertex v) throws IllegalArgumentException {
        if(vertices.containsKey(v.getName())){
            throw new IllegalArgumentException();
        }
        vertices.put(v.getName(),v);
        // TODO

    }

    /**
     * Gets a collection of all the vertices in the graph
     *
     * @return collection of all the vertices in the graph
     */
    public Collection<Vertex> getVertices() {

        // TODO
        return vertices.values();
    }

    /**
     * Adds a directed edge from vertex u to vertex v, Throws IllegalArgumentException if one of
     * the vertex does not exist. If edgesGiven is false, directly return at first.
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight weight of the edge between vertex u and v
     * @throws IllegalArgumentException if one of the vertex does not exist
     */
    public void addEdge(String nameU, String nameV, Double weight) throws IllegalArgumentException {
        if(!edgesGiven){
            return;
        }
        if(vertices.containsKey(nameU)==false||vertices.containsKey(nameV)==false){
            throw new IllegalArgumentException();
        }
        Vertex source = vertices.get(nameU);
        Vertex target = vertices.get(nameV);
        Edge e = new Edge(source,target,weight);
        source.addEdge(e);
        // TODO

    }

    /**
     * Adds an undirected edge between vertex u and vertex v by adding a directed
     * edge from u to v, then a directed edge from v to u. Then updates the allUndirectedEdges.
     * If edgesGiven is false, directly return at first.
     *
     * @param nameU name of vertex u
     * @param nameV name of vertex v
     * @param weight  weight of the edge between vertex u and v
     */
    public void addUndirectedEdge(String nameU, String nameV, double weight) {
        // TODO
        if(!edgesGiven){
            return;
        }
        if(vertices.containsKey(nameU)==false||vertices.containsKey(nameV)==false){
            throw new IllegalArgumentException();
        }
        Vertex source = vertices.get(nameU);
        Vertex target = vertices.get(nameV);
        Edge e1 = new Edge(source,target,weight);
        source.addEdge(e1);
        Edge e2 = new Edge(target,source,weight);
        target.addEdge(e2);
        Edge undirected = new Edge(source, target,weight);
        allUndirectedEdges.add(undirected);
    }

    /**
     * Calculates the euclidean distance for all edges in the graph and all edges in 
     * allUndirectedEdges. If edgesGiven is false, directly return at first.
     */
    public void computeAllEuclideanDistances() {
        
        // TODO
        if(!edgesGiven){
            return;
        }
        for(Edge e: allUndirectedEdges){//run through all undirected edges in graph
            double ux = e.getSource().getX();
            double uy = e.getSource().getY();
            double vx = e.getTarget().getX();
            double vy = e.getTarget().getY();
            e.setDistance(Math.sqrt(Math.pow((ux-vx),SQUARE)+Math.pow((uy-vy),SQUARE)));
        }

        Collection<Vertex> vCollection = getVertices();//run through all adjacent edges in all vertices
        for(Vertex v: vCollection){
            for(Edge e: v.adjacentEdges){
                double ux = v.getX();
                double uy = v.getY();
                double vx = e.getTarget().getX();
                double vy = e.getTarget().getY();
                e.setDistance(Math.sqrt(Math.pow((ux-vx),SQUARE)+Math.pow((uy-vy),SQUARE)));
            }
        }
    }

    /**
     * Populate all possible edges from all vertices in the graph. Only works when edgesGiven 
     * is false. If edgesGiven is true, directly return at first.
     */
    public void populateAllEdges() {
        
        // TODO
        if(edgesGiven){
            return;
        }
        Collection<Vertex> vCollection = getVertices();
        Vertex[] v = vCollection.toArray(new Vertex[vCollection.size()]);
        for(int i= 0; i<v.length-1;i++){
            for(int j = i+1; j<v.length; j++){
                //for each vertices, only connect with other vertices that it did not connect before
                double dist = Math.sqrt(Math.pow((v[i].getX()-v[j].getX()),SQUARE)
                                +Math.pow((v[i].getY()-v[j].getY()),SQUARE));
                //addUndirectedEdge(v[i].getName(),v[j].getName(),dist);
                Edge e = new Edge(v[i],v[j],dist);
                allUndirectedEdges.add(e);
            }
        }

    }

    /**
     * this method runs KruskalsAlg to find the minimum spanning tree for the graph
     * @return an arrayList containign the minimum spanning tree's edges
     */
    public ArrayList<Edge> runKruskalsAlg() {
        // if resultMST is already computed, return the resultMST at first
        // TODO
        if(!resultMST.isEmpty()){
            return resultMST;
        }
        int vNum = getVertices().size();
        Collections.sort(allUndirectedEdges, Comparator.comparingDouble(e -> e.getDistance()));
        DisjointSet ds = new DisjointSet();
        for(Edge e: allUndirectedEdges){
            if(ds.find(e.getTarget()).equals(ds.find(e.getSource()))){
                //check if edges' source and target vertices has been tested
                continue;
            }
            ds.union(e.getSource(),e.getTarget());//add source and target to tested set
            resultMST.add(e);
            if(resultMST.size()==vNum){
                return resultMST;
            }
        }
        return resultMST;
    }

//
}
