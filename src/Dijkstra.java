import java.awt.Point;
import java.util.List;

import pl.mgrproject.api.Graph;
import pl.mgrproject.api.plugins.RoutingAlgorithm;


public class Dijkstra implements RoutingAlgorithm {
    private int[][] E;

    @Override
    public String getName() {
	return "Algorytm Dijkstry";
    }

    @Override
    public List<Point> getPath() {
	return null;
    }

    @Override
    public void setGraph(Graph<?> graph) {
	ListToMatrixConverter c = new ListToMatrixConverter();
	c.setGraph(graph);
	E = c.getMatrix();
	for (int i = 0; i < E.length; ++i) {
	    System.out.print("[");
	    for (int j = 0; j < E[i].length; ++j) {
		System.out.print(E[i][j]+",");
	    }
	    System.out.println("]");
	}
    }

}
