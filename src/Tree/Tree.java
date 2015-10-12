package Tree;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Tree<T> implements Iterable<Tree<T>> {

    T data;
    Tree<T> parent;
    List<Tree<T>> children;

    public Tree(T data) {
        this.data = data;
        this.children = new LinkedList<Tree<T>>();
    }

    public Tree<T> addChild(T child) {
        Tree<T> childNode = new Tree<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }
    
    public String toString(int num)
    {	
    	String ret = "";
    	ret += "("+num+")" + this.data.toString();
    	for(Tree<T> data : this.children)
    	{
    		ret += data.toString(num + 1);
    	}
    	
    	return ret;
    }
    
    public String toString()
    {
    	return this.toString(1);
    }
    
	@Override
	public Iterator<Tree<T>> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
