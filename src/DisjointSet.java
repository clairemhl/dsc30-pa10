/**
 * NAME: Huilai Miao
 * PID: A14482851
 */

public class DisjointSet {

    public DisjointSet() {}

    /**
     * this class find the root of a vertex which indicate which set it belongs to
     * @param v the vertex that need to find the root
     * @return the root of the vertex
     */
    public Vertex find(Vertex v) {
        
        // TODO
        if(v.getRoot().equals(v)){
            return v;
        }
        Vertex curr = v;
        while(!curr.getRoot().equals(curr)){
            curr = curr.getRoot();//track into the root of vertex
        }
        return curr;
    }

    /**
     * unite two sets together that containing v1 and v2 respectively
     * @param v1 the first vertex that one set contains
     * @param v2 the second vertex that the second set contains
     */
    public void union(Vertex v1, Vertex v2) {
        // TODO
        Vertex root1 = find(v1);
        Vertex root2 = find(v2);
        if(root1.equals(root2)){// return if root1 and root2 are equal
            return;
        }
        if(root1.getSize()>root2.getSize()){//set the larger size vertex the root of smaller size vertex
            root2.setRoot(root1);
            root1.setSize(root1.getSize()+root2.getSize());
        }
        else{
            root1.setRoot(root2);
            root2.setSize(root1.getSize()+root2.getSize());
        }
    }
}
