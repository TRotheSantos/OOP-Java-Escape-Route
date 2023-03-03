package edu.kit.informatik.handling;

import java.util.ArrayList;

/**
 * This class contains the method for breadth first search.
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class BreadthFirstSearch {

    // utility-class constructor
    private BreadthFirstSearch() {
    }

    /**
     * This method uses breadth first search to determine a path between a start vertex and a target vertex
     *
     * @param startVertex     the start vertex, where to start
     * @param targetVertex    the target vertex
     * @param vertexList      an array list containing all vertexes
     * @param adjacencyMatrix the 2D array with adjacency matrix
     * @return a path in form of a String
     */
    public static String breadthFirstSearch(
            String startVertex, String targetVertex, ArrayList<String> vertexList, int[][] adjacencyMatrix) {

        int qtyVertexes = vertexList.size();                         // quantity of vertexes
        ArrayList<String> queue = new ArrayList<>();                 // queue with vertexes to be visited next
        ArrayList<String> searchableVertexes;                        // list of still not visited vertexes
        String[] previousVertex = new String[qtyVertexes];           // matrix of discovering vertex

        // initialization of search
        queue.add(startVertex);
        searchableVertexes = new ArrayList<>(vertexList);
        searchableVertexes.remove(startVertex);
        for (int i = 0; i < qtyVertexes; i++) {
            previousVertex[i] = null;
        }
        // do a Breadth First Search starting at startVertex
        while (queue.size() > 0) {
            String currentVertex = queue.get(0);
            queue.remove(currentVertex);

            int indexOfCurrentVertex = vertexList.indexOf(currentVertex);
            for (int i = 0; i < qtyVertexes; i++) {
                if ((adjacencyMatrix[indexOfCurrentVertex][i] > 0) && searchableVertexes.contains(vertexList.get(i))) {
                    queue.add(vertexList.get(i));
                    searchableVertexes.remove(vertexList.get(i));
                    previousVertex[i] = currentVertex;
                }
            }
        }
        // reconstruct the path
        ArrayList<String> path = new ArrayList<>();
        path.add(targetVertex);
        while (!(path.get(0) == null)) {
            path.add(0, previousVertex[vertexList.indexOf(path.get(0))]);
        }
        path.remove(0);
        String returnPath;
        if (path.get(0).equals(startVertex)) {
            returnPath = path.toString();
            returnPath = returnPath.replace("[", "");
            returnPath = returnPath.replace("]", "");
        } else {
            returnPath = "";
        }
        return returnPath;
    }
}
