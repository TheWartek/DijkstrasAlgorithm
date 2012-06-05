import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
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
    private int start;

    @Override
    public String getName() {
	return "Algorytm Dijkstry";
    }

    @Override
    public List<Point> getPath(int stop) {
	System.out.println("E[][]");
	for (int i = 0; i < E.length; ++i) {
	    System.out.print("[");
	    for (int j = 0; j < E[0].length; ++j) {
		System.out.print(E[i][j] + ",");
	    }
	    System.out.println("]");
	}
	System.out.println("d[]: " + Arrays.toString(d));
	System.out.println("p[]: " + Arrays.toString(p));

	List<Point> path = new ArrayList<Point>();

	int index = stop;
	while (index != start) {
	    path.add(V.get(index));
	    index = p[index];
	}
	path.add(V.get(start));

	return path;
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
	this.start = start;
	int n = E.length;
	List<Integer> Q = new ArrayList<Integer>(n);
	p = new int[n];
	d = new int[n];
	Integer u = -1;

	for (int i = 0; i < n; ++i) {
	    p[i] = -1;
	    d[i] = INF;
	    Q.add(i);
	}
	d[start] = 0;
	p[start] = 0;

	while (!Q.isEmpty()) {
	    u = min(d, Q);
	    System.out.println("D p[]: " + Arrays.toString(p));
	    System.out.println("D d[]: " + Arrays.toString(d));
	    System.out.println("min: " + u);
	    Q.remove(u);
	    for (Integer v : Q) {
		if (E[u][v] != INF) {
		    if (d[u] + E[u][v] < d[v]) {
			d[v] = d[u] + E[u][v];
			p[v] = u;
		    }
		}
	    }
	}
    }

    private Integer min(int[] d, List<Integer> Q) {
	int min = INF + 1;
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
