package edu.kit.informatik.application.commands;

import edu.kit.informatik.data.Data;
import edu.kit.informatik.data.Network;
import edu.kit.informatik.handling.BreadthFirstSearch;

import java.util.ArrayList;

/**
 * This class contains the method for the command 'flow' and various private methods for calculating the flow
 *
 * @author Tilmann Rothe Santos
 * @version 1.0
 */
public final class Flow {

    // utility-class constructor
    private Flow() {
    }

    /**
     * This method calculates the max flow.
     *
     * @param splitInputLine the split and checked input line
     * @return String with the value of the max flow result or an error message if the requested flow is not valid or
     * 0 if there is no path between start and target vertex
     */
    public static String comFlow(String[] splitInputLine) {
        if (!isRequestedFlowValid(splitInputLine)) {
            return "Error, invalid arguments.";
        }
        String networkName = splitInputLine[1];
        String startVertex = splitInputLine[2];
        String targetVertex = splitInputLine[3];
        Network network = Data.networkStorage.getNetwork(networkName);
        ArrayList<String> flowResultList = network.getFlowResults();
        if (flowResultList.size() > 0) {
            String maxFlowResult = calculatedFlow(flowResultList, splitInputLine);
            if (!maxFlowResult.equals("")) {
                return maxFlowResult;
            }
        }
        String[] path = BreadthFirstSearch.breadthFirstSearch(
                startVertex, targetVertex, network.getVertexList(), network.getAdjacencyMatrix()).split(", ");
        if (path[0].equals("")) {
            return String.valueOf(0);       // no path between startVertex and targetVertex
        }
        // initial flow is zero
        int[][] flow = new int[network.getVertexList().size()][network.getVertexList().size()];
        // initial residual capacity is same as network capacity
        int[][] residualCapa = new int[network.getAdjacencyMatrix().length][];
        for (int i = 0; i < network.getAdjacencyMatrix().length; i++) {
            residualCapa[i] = network.getAdjacencyMatrix()[i].clone();      // copy of matrix
        }
        // Ford and Fulkerson algorithm (wikipedia)
        while (path.length > 1) {           // while there is path between start and target vertex
            int calcFlow = calcFlow(path, network.getVertexList(), residualCapa);
            calcFlowMatrix(path, network.getVertexList(), flow, calcFlow);
            calcResidualCapa(path, network.getVertexList(), residualCapa, calcFlow);
            path = BreadthFirstSearch.breadthFirstSearch(
                    startVertex, targetVertex, network.getVertexList(), residualCapa).split(", ");
        }
        int indexStartVertex = network.getVertexList().indexOf(startVertex);
        long maxFlow = 0;
        for (int i = 0; i < flow.length; i++) {
            maxFlow = maxFlow + flow[indexStartVertex][i];
        }
        String maxFlowResult = String.valueOf(maxFlow).replace(".0", "");
        network.addToResultList(startVertex, targetVertex, maxFlowResult);
        return maxFlowResult;
    }


    /**
     * Method to calculate the residual capacity graph.
     *
     * @param path         an array of Strings, the path from requested start to requested target
     * @param vertexList   the vertex list
     * @param residualCapa the matrix containing the residual capacity (2D array)
     * @param calcFlow     the calculated flow
     */
    private static void calcResidualCapa(
            String[] path, ArrayList<String> vertexList, int[][] residualCapa, int calcFlow) {
        int indexVertexIn = vertexList.indexOf(path[0]);
        int indexVertexOut = vertexList.indexOf(path[1]);
        residualCapa[indexVertexIn][indexVertexOut] = residualCapa[indexVertexIn][indexVertexOut]
                - calcFlow;
        residualCapa[indexVertexOut][indexVertexIn] = residualCapa[indexVertexOut][indexVertexIn]
                + calcFlow;
        for (int i = 1; i < path.length - 1; i++) {
            indexVertexIn = vertexList.indexOf(path[i]);
            indexVertexOut = vertexList.indexOf(path[i + 1]);
            residualCapa[indexVertexIn][indexVertexOut] = residualCapa[indexVertexIn][indexVertexOut]
                    - calcFlow;
            residualCapa[indexVertexOut][indexVertexIn] = residualCapa[indexVertexOut][indexVertexIn]
                    + calcFlow;
        }
    }

    /**
     * Method to calculate the flow matrix, represents the real directed flow and will be used to calculate the max flow
     * when no further path can be identified in the residual capacity graph
     *
     * @param path       the array of the path
     * @param vertexList the array list of vertexes
     * @param flow       the flow matrix in an 2D array
     * @param calcFlow   the calculated flow
     */
    private static void calcFlowMatrix(String[] path, ArrayList<String> vertexList, int[][] flow, int calcFlow) {
        int indexVertexIn = vertexList.indexOf(path[0]);
        int indexVertexOut = vertexList.indexOf(path[1]);
        flow[indexVertexIn][indexVertexOut] = flow[indexVertexIn][indexVertexOut] + calcFlow;
        for (int i = 1; i < path.length - 1; i++) {
            indexVertexIn = vertexList.indexOf(path[i]);
            indexVertexOut = vertexList.indexOf(path[i + 1]);
            flow[indexVertexIn][indexVertexOut] = flow[indexVertexIn][indexVertexOut] + calcFlow;
        }
    }

    /**
     * The calculation of flow along the path
     *
     * @param path         the array of the path
     * @param vertexList   the array list of vertexes
     * @param residualCapa the matrix with the residual capacity
     * @return int, the calculated flow
     */
    private static int calcFlow(String[] path, ArrayList<String> vertexList, int[][] residualCapa) {
        int indexStartVertex = vertexList.indexOf(path[0]);
        int indexTargetVertex = vertexList.indexOf(path[1]);
        int calcFlow = residualCapa[indexStartVertex][indexTargetVertex];
        for (int i = 1; i < path.length - 1; i++) {
            indexStartVertex = vertexList.indexOf(path[i]);
            indexTargetVertex = vertexList.indexOf(path[i + 1]);
            calcFlow = Math.min(residualCapa[indexStartVertex][indexTargetVertex], calcFlow);
        }
        return calcFlow;
    }

    /**
     * Method to check if the requested flow is valid, means there must be start and target vertex in graph
     *
     * @param splitInputLine the checked and split input array
     * @return true: if it is valid, false: if not
     */
    private static boolean isRequestedFlowValid(String[] splitInputLine) {
        String networkName = splitInputLine[1];
        String startVertex = splitInputLine[2];
        String targetVertex = splitInputLine[3];
        Network network = Data.networkStorage.getNetwork(networkName);
        if ((network == null) || !network.isStartVertex(startVertex) || !network.isTargetVertex(targetVertex)) {
            return false;
        }
        return true;
    }

    /**
     * This method gets the flow result, so the max flow does not need to be calculated again
     *
     * @param flowResultList the array list containing all the calculated results of a session
     * @param splitInputLine the array input, for start and target vertex
     * @return String with the max flow or empty String if this result was not calculated yet
     */
    private static String calculatedFlow(ArrayList<String> flowResultList, String[] splitInputLine) {
        String startVertex = splitInputLine[2];
        String targetVertex = splitInputLine[3];

        for (int i = 0; i < flowResultList.size(); i++) {
            String[] maxFlow = flowResultList.get(i).split(" ");
            String calcedStartVertex = maxFlow[1];
            String calcedTargetVertex = maxFlow[2];
            if (startVertex.equals(calcedStartVertex) && targetVertex.equals(calcedTargetVertex)) {
                return maxFlow[0];
            }
        }
        return "";
    }
}
