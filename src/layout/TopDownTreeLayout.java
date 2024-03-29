package layout;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.BitSet;

import model.Graph;
import planar.Edge;
import planar.Vertex;
import tree.TreeCenterFinder;
import draw.ParameterSet;
import draw.Representation;

public class TopDownTreeLayout implements SimpleLayout {
	
	private ParameterSet params;
    
    private int totalLeafCount;
    
    private double xSep;
    
    private double ySep;
    
    private double width;
    
    private double height;
    
    private Representation representation;
    
    public TopDownTreeLayout() {
    	this(new ParameterSet());
    }
    
    public TopDownTreeLayout(ParameterSet params) {
    	this.params = params;
    }
    
    public ParameterSet getParameters() {
    	return params;
    }
    
    public Representation layout(Graph tree, Rectangle2D canvas) {
        representation = new Representation();
        double rootLabel = params.getDouble("rootLabel");	// XXX
        int root;
        if (rootLabel == 1) {	// XXX very wrong!
        	root = TreeCenterFinder.findUniqueCenter(tree);
        } else {
        	root = (int) rootLabel;
        }
        int[] depthList = new int[tree.getVertexCount()];
        int maxDepth = calculateDepth(tree, root, depthList, 1);
        
        int treeWidth = getWidth(tree, root);
        System.out.println(treeWidth + " " + maxDepth + " " + Arrays.toString(depthList));
        
        width = canvas.getWidth();
        height = canvas.getHeight();
        
        totalLeafCount = 0;
        
        layoutTree(tree, root, treeWidth, maxDepth, depthList);
        return representation;
    }
    
    public void layoutTree(Graph tree, int root, int leafCount, int maxDepth, int[] depthList) {
        this.xSep = width / (leafCount + 1);
        this.ySep = height / (maxDepth + 1);
        layout(tree, root, -1, depthList, new BitSet());
    }
    
    public double layout(Graph tree, int root, int parent, int[] depthList, BitSet visited) {
        visited.set(root);
        
        double y  = depthList[root] * ySep;
        double min = 0;
        double max = 0;
        boolean isChild = true;
        for (int child : tree.getConnected(root)) {
//            if (visited.get(child)) {
            if (child == parent) {
                continue;
            } else {
                isChild = false;
                double childCenter = layout(tree, child, root, depthList, visited);
                if (min == 0) {
                    min = childCenter;
                }
                max = childCenter;
            }
            
        }
        
        double x;
        if (isChild) {
            totalLeafCount += 1;
            x = totalLeafCount * xSep;
        } else {
            if (min == max) {
                x = min;
            } else {
                x = min + (max - min) / 2;
            }
        }
        Point2D p = new Point2D.Double(x, y);
        Vertex rootVertex = new Vertex(root);
        representation.addPoint(rootVertex, p);
        for (int child : tree.getConnected(root)) {
            if (child != parent) {
                Vertex childVertex = representation.getVertex(child);
                if (childVertex != null) {
                    representation.addEdge(new Edge(childVertex, rootVertex));
                }
            }
        }
        return x;
    }
    
    private int calculateDepth(Graph tree, int root, int[] depthList, int depth) {
        depthList[root] = depth;
        int maxDepth = depth;
        for (int child : tree.getConnected(root)) {
            if (depthList[child] == 0) {
                maxDepth = Math.max(calculateDepth(tree, child, depthList, depth + 1), maxDepth);
            } else {
                continue;
            }
        }
        return maxDepth;
    }
    
    private int getWidth(Graph tree, int root) {
        BitSet visited = new BitSet();
        return getWidth(tree, root, visited);
    }
    
    private int getWidth(Graph tree, int root, BitSet visited) {
        visited.set(root);
        int numberOfChildren = 0;
        boolean isChild = true;
        for (int child : tree.getConnected(root)) {
            if (visited.get(child)) {
                continue;
            } else {
                isChild = false;
                numberOfChildren += getWidth(tree, child, visited);
            }
        }
        if (isChild) {
            return 1;
        } else {
            return numberOfChildren;
        }
    }
    
}
