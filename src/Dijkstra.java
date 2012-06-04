import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import pl.mgrproject.api.Graph;
import pl.mgrproject.api.plugins.RoutingAlgorithm;


public class Dijkstra implements RoutingAlgorithm {
    private int[][] E = new int[0][0];
    private List<Point> V;
    private int[] p;
    private int[] d;
    private static final int INF = 9999;

    @Override
    public String getName() {
	return "Algorytm Dijkstry";
    }

    @Override
    public List<Point> getPath(int stop) {
	return null;
    }

    @Override
    public void setGraph(Graph<?> graph) {
	ListToMatrixConverter c = new ListToMatrixConverter();
	c.setGraph(graph);
	V = graph.getVertices();
	E = c.getMatrix();
    }

    @Override
    public void run(int start) {
	int n = E.length;
	List<Integer> Q = new ArrayList<Integer>(n);
	p = new int[n];
	d = new int[n];
	Integer u = -1;
	d[start] = 0;
	
	for (int i = 0; i < n; ++i) {
	    p[i] = 0;
	    d[i] = INF;
	    Q.add(i);
	}
	
	while (!Q.isEmpty()) {
	    System.out.println(Q);
	    u = min(d, Q);
	    Q.remove(u);
	    for (Integer v : Q) {
		if (E[u][v] == INF) 
		    continue;
		if (d[u] + E[u][v] < d[v]) {
		    d[v] = d[u] + E[u][v];
		    p[v] = u;
		}
	    }
	}
    }
    
    private Integer min(int[] d, List<Integer> Q) {
	int min = INF+1;
	int index = -1;
	for (int i = 0; i < d.length; ++i) {
	    if (d[i] < min && Q.contains(new Integer(i))) {
		min = d[i];
		index = i;
	    }
	}
	return new Integer(index);
    }
}
