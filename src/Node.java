
import java.util.*;

/* 
 * this is the class containing the implementation of 
 * the node of the decision tree data structure .
 * 
 */

public class Node
{
	String Name="null";
	String parent="null";
	String edge="null";
	List<String> edges=new ArrayList<String>();
	List<Node> children=new ArrayList<Node>();
	
	/* implementation for level order traversal of the decision tree */
	
	static void traverse(Node root)
	{
		System.out.println("\n*********************************************\nLevel Order Traversal");
		int cur=1,next=0;
		LinkedList<Node> q=new LinkedList<Node>();
		q.add(root);
		while(!q.isEmpty())
		{
			Node no=q.remove(0);
			if(cur==0)
			{
				System.out.println("\n");
				cur=next;
				next=0;
			}
			for (Node r:no.children) 
			{  
				q.add(r); 
		         next++; 
			}
		cur--;
		System.out.print(no+"\t");
		}
		
	}
	
	/* toString() is modified to display the Node of the decision tree . */
	
	public String toString ()
	{
		return parent+"----"+edge+"---->"+Name;
	}
}
