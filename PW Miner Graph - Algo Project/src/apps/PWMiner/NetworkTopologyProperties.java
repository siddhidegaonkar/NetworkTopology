package apps.PWMiner;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class NetworkTopologyProperties {

    public static String[] getGraphNodes() {
        String[] graphNodes = {
                "2.1.3.3",
                "4.3.2.1",
                "6.3.4.5",
                "3.5.1.16",
                "2.6.1.11",
                "1.2.1.38",
                "2.7.2.8",
                "2.3.1.1",
        };

        return graphNodes;
    }

    public static int[][] getAdjacencyMatrix() {
        int adjacencyMatrix[][] = {
                {0, 0, 41, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 3, 0, 0, 0, 0, 0, 0},
                {16, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 24, 0, 0, 0, 0},
                {0, 0, 0, 0, 20, 0, 0, 0},
                {0, 0, 0, 0, 0, 10, 0, 0},
                {0, 0, 0, 0, 0, 0, 65, 0},
        };

        return adjacencyMatrix;
    }

    public static Map<String, Integer> getDegreeOfGraph(String[] graphNodes, int[][] adjacencyMatrix) {
        Map<String, Integer> graphDegree = new HashMap<>();

        for (int i = 0; i < graphNodes.length; i++) {
            String graphNode = graphNodes[i];
            int[] edges = adjacencyMatrix[i];
            int degree = 0;

            for (int edge : edges) {
                if (edge > 0) {
                    degree++;
                }
            }

            graphDegree.put(graphNode, degree);
        }

        return graphDegree;
    }

    public static Map<String, Integer> getStrengthOfNode(String[] graphNodes, int[][] adjacencyMatrix) {
        Map<String, Integer> graphStrength = new HashMap<>();

        for (int i = 0; i < graphNodes.length; i++) {
            String graphNode = graphNodes[i];
            int[] edges = adjacencyMatrix[i];
            int strength = 0;

            for (int edge : edges) {
                if (edge > 0) {
                    strength += edge;
                }
            }

            graphStrength.put(graphNode, strength);
        }

        return graphStrength;
    }

    public static Map<String, Integer> getAverageWeight(String[] graphNodes, int[][] adjacencyMatrix) {
        Map<String, Integer> averageWeight = new HashMap<>();

        for (int i = 0; i < graphNodes.length; i++) {
            String graphNode = graphNodes[i];
            int[] edges = adjacencyMatrix[i];
            int strength = 0;
            int degree = 0;

            for (int edge : edges) {
                if (edge > 0) {
                    strength += edge;
                    degree++;
                }
            }

            int avgStrength = 0;
            if (degree > 0) {
                avgStrength = strength / degree;
            }

            averageWeight.put(graphNode, avgStrength);
        }

        return averageWeight;
    }

    public static void main(String args[]) {
        Map<String, Integer> graphDegree = getDegreeOfGraph(getGraphNodes(), getAdjacencyMatrix());
        Map<String, Integer> graphStrength = getStrengthOfNode(getGraphNodes(), getAdjacencyMatrix());
        Map<String, Integer> graphAvgWeight = getAverageWeight(getGraphNodes(), getAdjacencyMatrix());

        System.out.println("Graph Node -> Degree");
        for (Map.Entry<String, Integer> stringIntegerEntry : graphDegree.entrySet()) {
            System.out.println(stringIntegerEntry.getKey() + " -> " + stringIntegerEntry.getValue());
        }

        System.out.println("");
        System.out.println("");

        System.out.println("Graph Node -> Strength");
        for (Map.Entry<String, Integer> stringIntegerEntry : graphStrength.entrySet()) {
            System.out.println(stringIntegerEntry.getKey() + " -> " + stringIntegerEntry.getValue());
        }

        System.out.println("");
        System.out.println("");

        System.out.println("Graph Node -> AvgWeight");
        for (Map.Entry<String, Integer> stringIntegerEntry : graphAvgWeight.entrySet()) {
            System.out.println(stringIntegerEntry.getKey() + " -> " + stringIntegerEntry.getValue());
        }

    }
}

