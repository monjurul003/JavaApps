/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgraphdraw;

/**
 *
 * @author Rasel
 */
public class TestGraphDraw {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GraphDraw frame = new GraphDraw("Test Window");

        frame.setSize(400, 300);

        frame.setVisible(true);

        frame.addNode("a", 50, 50);
        frame.addNode("b", 100, 100);
        frame.addNode("longNode", 200, 200);
        frame.addEdge(0, 1);
        frame.addEdge(0, 2);
    }

}
