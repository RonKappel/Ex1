package EX1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;



public class WGraph_DS implements weighted_graph
{
	private HashMap<Integer,node_info> head;
	private int Size=0;// counter that represent the number of nodes in the graph
	private int EdgeSize=0;// counter that represent the number of edges in the graph
	private int Mc=0;// counter that represent the number of changes in the graph
	private static int id=0;
	
	protected static class NodeInfo implements node_info
	{
		private int key;
		private double tag;
		private String Info;
		private HashMap<Integer,node_info> Ni=new HashMap<Integer,node_info>();
		private HashMap<Integer,Edge> Ni_Edges=new HashMap<Integer,Edge>();
		private NodeInfo Pater;//father of each node only relvent when using shortestPath methed;
		
		public NodeInfo()
		{
			key=id;
			id++;
			tag=0;
			Info="White";
			Pater=null;
		}
		public NodeInfo(node_info n)
		{
			key=n.getKey();
			tag=n.getTag();
			Info=n.getInfo();
			Pater=null;
		}
		public NodeInfo(double tag,String Info)
		{
			key=id;
			id++;
			this.tag=tag;
			this.Info=Info;
			Pater=null;
		}
		public NodeInfo(double tag,String Info,int key)
		{
			this.key=key;
			id=key;
			id++;
			this.tag=tag;
			this.Info=Info;
			Pater=null;
		}
		@Override
		public int getKey()
		{
			return key;
		}

		@Override
		public String getInfo()
		{
			return Info;
		}

		@Override
		public void setInfo(String s)
		{
			Info=s;
		}
		@Override
		public double getTag() 
		{
			return tag;
		}

		@Override
		public void setTag(double t) 
		{
			tag=t;	
		}
		public String toString()
		{
			return ("["+key+","+Info+","+tag+"]");
		}
		public void PrintNode()
		{
			System.out.print(toString());
		}
		public void PrintlnNode()
		{
			System.out.println(toString());
		}
		public Collection<node_info> getNi() 
		{
			return Ni.values();
		}
		public HashMap<Integer,Edge> getNi_Edges() 
		{
			return Ni_Edges;
		}
		public boolean hasNi(int key)
		{
			if(Ni.containsKey(key))return true;
			else return false;
		}
		public void addNi(node_info node) 
		{
			if(node!=null)
				{
					Ni.put(node.getKey(),node);
					Edge E=new Edge();
					Ni_Edges.put(node.getKey(), E);
				}
		}
		public void addNi(node_info node,double w) 
		{
			if(node!=null)
				{
					Ni.put(node.getKey(),node);
					Edge E=new Edge(w);
					Ni_Edges.put(node.getKey(), E);
				}
		}
		public void removeNode(node_info node) 
		{
			if(hasNi(node.getKey()))
				{
					Ni.remove(node.getKey());
					Ni_Edges.remove(node.getKey());
				}
		}
		public NodeInfo GetPater()
		{
			return Pater;
		}
		public void SetPater(NodeInfo N)
		{
			Pater=N;
		}
		
	}
	
	protected static class Edge
	{
		private double Price;
		private String col;
		
		public Edge()
		{
			Price=0;
			col="White";
		}
		public Edge(double Price)
		{
			this.Price=Price;
			col="White";
		}
		public Edge(double Price,String col)
		{
			this.Price=Price;
			this.col=col;
		}
		public void SetPrice(double Price)
		{
			this.Price=Price;
		}
		public double GetPrice()
		{
			return Price;
		}
		public void SetCol(String col)
		{
			this.col=col;
		}
		public String GetCol()
		{
			return col;
		}
		public String toString()
		{
			return ("["+Price+","+col+"]");
		}
		
	}
	
	
	public WGraph_DS()
	{
		head=new HashMap<Integer,node_info>() ;
		id=0;
	}
	public void DeepCopy(weighted_graph g)
	{
		 Iterator<node_info> ite=g.getV().iterator(); 
		 while(ite.hasNext())
		 {
	    	  node_info N=ite.next();
	    	  head.put(N.getKey(), new NodeInfo(N));  
		 }
		 ite=g.getV().iterator(); 
	      while(ite.hasNext())
	      {
	    	  NodeInfo N=(NodeInfo)ite.next();
	    	  Iterator<node_info> iteNi=N.getNi().iterator(); 
	    	  while(iteNi.hasNext())
	    	  {
	    		  NodeInfo tmp=(NodeInfo)iteNi.next();
	    		  NodeInfo h=(NodeInfo)head.get(N.getKey());
	    		  h.addNi(head.get(tmp.getKey()),g.getEdge(h.getKey(),tmp.getKey()));
	    	  }
	      }
		this.Mc=g.getMC();
		this.Size=g.nodeSize();
		this.EdgeSize=g.edgeSize();
	}
	public void DeleteAll()
	{
		 Iterator<node_info> ite=head.values().iterator();
		 while(ite.hasNext())
		 {
			 NodeInfo N=(NodeInfo)ite.next();
			 Iterator<node_info> iteNi=N.getNi().iterator();
			 	while(iteNi.hasNext())
			 	{
			 		NodeInfo T=(NodeInfo)iteNi.next();
			 		T.removeNode(N);
			 	}
		 }
		 head.clear();
		 Size=0;
		 Mc=0;
		 EdgeSize=0;
	}
			 	
	@Override
	public node_info getNode(int key)
	{
		if(head.get(key)!=null)return head.get(key);
		else return null;
	}

	@Override
	public boolean hasEdge(int node1, int node2) {
		NodeInfo N1=(NodeInfo)getNode(node1);
		NodeInfo N2=(NodeInfo)getNode(node2);
		if(N1==null || N2==null|| N1.equals(N2))return false; // Check if N1 Or N2 are null Or N1 equals to N2  ;
		if(N1.hasNi(node2)&& N2.hasNi(node1))return true; // check if N1 is neighbor of N2 and if N2 is a neighbor of N1
		else return false;
	}

	@Override
	public double getEdge(int node1, int node2) 
	{
		NodeInfo N1=(NodeInfo)getNode(node1);
		NodeInfo N2=(NodeInfo)getNode(node2);
		if(N1!=null && N2!=null && node1!=node2 && hasEdge(node1,node2))
		{
			return N1.getNi_Edges().get(N2.getKey()).GetPrice();
		} 
		else return -1;
	}
	public void addNode(node_info n)
	{
		if(n!=null)
		{
			head.put(n.getKey(), (NodeInfo)n);
			Size=Size+1;
			Mc=Mc+1;
		}
	}

	@Override
	public void connect(int node1, int node2, double w) 
	{
		NodeInfo N1=(NodeInfo)getNode(node1);
		NodeInfo N2=(NodeInfo)getNode(node2);
		if(node1!=node2 && N1!=null && N2!=null && node1!=node2  && !hasEdge(N1.getKey(), N2.getKey()))// Check if N1 Or N2 are null and check if the nodes are not have edge
		{
			N1.addNi(N2,w);
			N2.addNi(N1,w);
			EdgeSize=EdgeSize+1;
			Mc=Mc+1;
		}
		else if(node1!=node2 && N1!=null && N2!=null && node1!=node2  && hasEdge(N1.getKey(), N2.getKey()))
		{
			ChancgeEdge(N1,N2,w);
		}
	}
	public void ChancgeEdge(NodeInfo N1,NodeInfo N2,double edge)
	{
		if(edge!=getEdge(N1.getKey(),N2.getKey()))
		{
			N1.getNi_Edges().get(N2.getKey()).SetPrice(edge);
			N2.getNi_Edges().get(N1.getKey()).SetPrice(edge);
			Mc=Mc+1;
		}
	}

	@Override
	public Collection<node_info> getV() 
	{
		return head.values();
	}

	@Override
	public Collection<node_info> getV(int node_id)
	{
		if(head.containsKey(node_id))
		{
			NodeInfo n =(NodeInfo)head.get(node_id);
			return n.getNi();
		}
		else return null;
	}

	@Override
	public node_info removeNode(int key) 
	{
		if(!head.containsKey(key))return null;
		
		NodeInfo N1=(NodeInfo)getNode(key);
		  Iterator<node_info> ite = N1.getNi().iterator(); // iterator that will run on neighbors HashMap
	      while(ite.hasNext())
	      {
	    	  node_info tmp=ite.next();
	    	  ((NodeInfo) tmp).removeNode(N1);// Remove N1 from the neighbors HashMap of the node tmp;
	    	  EdgeSize=EdgeSize-1;
	    	  Mc=Mc+1;
	      }
	      N1.getNi().clear();// Remove all the neighbors of N1;
	      N1.getNi_Edges().clear();
	      Size=Size-1;
	      Mc=Mc+1;
	      head.remove(key);
	      return N1;
	}

	@Override
	public void removeEdge(int node1, int node2) 
	{
		NodeInfo N1=(NodeInfo)getNode(node1);
		NodeInfo N2=(NodeInfo)getNode(node2);
		if(hasEdge(node1,node2)&& N1!=null && N2!=null && node1!=node2)
		{
			N1.removeNode(N2);
			N2.removeNode(N1);
			EdgeSize=EdgeSize-1;
			Mc=Mc+1;
		}	
	}
	@Override
	public int nodeSize() 
	{
		return Size;
	}

	@Override
	public int edgeSize() 
	{
		return EdgeSize;
	}

	@Override
	public int getMC() 
	{
		return Mc;
	}
	public void PrintGData()// print the data of the Graph. I built this method for my own Test
	{
		System.out.println("["+Size+","+EdgeSize+","+Mc+"]");
	}
	public String StringGDataWithOutMc()// print the data of the Graph. I built this method for my own Test
	{
		return("["+Size+","+EdgeSize+"]");
	}
	public String StringGData()//return the data of of the graph
	{
		return("["+Size+","+EdgeSize+","+Mc+"]");
	}
	@Override
	public void addNode(int key) 
	{
		NodeInfo N=new NodeInfo(0,"White",key);
		if(!head.containsKey(key))addNode(N);

	}
	public boolean equals(Object G1)
	{
		return equals((WGraph_DS)G1);
	}

	public boolean equals(WGraph_DS G1)//Method that check is the graphs are equals 
	{
		WGraph_DS G2=this;
		if(G1==null || G2==null)
			{
			 System.err.println("Null Graph");
				return false;
			}
		if(G1.nodeSize()!=G2.nodeSize())
			{
			 System.err.println("diffrent Size");
				return false;
			}
		if(G1.edgeSize()!=G2.edgeSize())
			{
			 System.err.println("diffrent edge Size");
				return false; 
			}
		 Iterator<node_info> iteG1=G1.getV().iterator();
		 while(iteG1.hasNext() )
		 {
			 node_info N1=iteG1.next();
			 node_info N2=G2.getNode(N1.getKey());
			 if(!EquelsNode(N1,N2))
				 {
				 System.err.println("Not equels Node"+" Node key is:"+N1.getKey());
				 return false;
				 }
			 Iterator<node_info> iteNiG1=G1.getV(N1.getKey()).iterator();
			 int L1=G1.getV(N1.getKey()).size();
			 int L2=G2.getV(N2.getKey()).size();
			 if(L1!=L2)
				 {
				 System.err.println("nighbor Size is diffrent"+" Node key is:"+N1.getKey());
				 	return false;
				 }
			 while(iteNiG1.hasNext())
			 {
				 node_info Ni1=iteNiG1.next();
				 node_info Ni2=G2.getNode(Ni1.getKey());
				 if(!EquelsNode(Ni1,Ni2))
					 {
					 System.err.println("Not equels Node Nighbor"+" Node key is:"+Ni1.getKey());
					 return false;
					 }
				 if(G1.getEdge(N1.getKey(), Ni1.getKey())!=G2.getEdge(N2.getKey(), Ni2.getKey()))
					 {
					 	System.out.println("Diffrent edge ");
					 	return false;
					 }
			 } 
		 }
		 return true;
		 
	}
	public  boolean EquelsNode(node_info n1,node_info n2)
	{
		if(n1==null || n2==null)return false;
		if(n1.getInfo().equals(n2.getInfo()) && n1.getTag()==n2.getTag()) return true;
		else return false;
	}
	
	

}
