package EX1;

import java.io.File;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


import java.util.Scanner;
import java.util.StringTokenizer;

import EX1.WGraph_DS.NodeInfo;

public class WGraph_Algo implements weighted_graph_algorithms
{
	private weighted_graph G;
	private  int Num=0;// a Number that will help checking if the graph is connected or not;
	private double Inf=Double.POSITIVE_INFINITY;
	private Scanner FR;// FR-File Read
	private Formatter FW;// FW-File Write
	private int loaderrors=0;
	
	public WGraph_Algo()
	{
		G=new WGraph_DS();
	}
	@Override
	public void init(weighted_graph g) {
		G=g;
		
	}

	@Override
	public weighted_graph getGraph()
	{
		return G;
	}

	@Override
	public weighted_graph copy() 
	{
		WGraph_DS G1=new WGraph_DS();
		G1.DeepCopy(G);
		return G1;
	}

	@Override
	public boolean isConnected()
	{
		
		if(G.nodeSize()==1 || G.nodeSize()==0) return true;
		else if(G.nodeSize()==2 && G.edgeSize()==1)return true;
		else if(G.edgeSize()+1<G.nodeSize()|| G.nodeSize()==0)return false;
		else 
		{
			SetInit();
			Iterator<node_info> ite=G.getV().iterator(); 
		    if(ite.hasNext())
		    	{
		    		node_info N=ite.next();
		    		N.setTag(0);
		    		Dijkstra((NodeInfo)N);
		    	}
		     if(Num==G.nodeSize())return true;
		     else return false;
		}
	}
	public void SetInit()// initialization of the graph 
	{
		Num=0; 
		Iterator<node_info> ite=G.getV().iterator(); 
	      while(ite.hasNext())
	      {
	    	  node_info N=ite.next();
	    	  N.setTag(Inf);
	    	  N.setInfo("White");
	    	  NodeInfo n=(NodeInfo)N;
	    	  n.SetPater(null);
	    	  
	      }
	}
	public void Dijkstra(NodeInfo S)// Dijkstra Algo with start node 
	{
		PriorityQueue<NodeInfo> Q = new PriorityQueue<NodeInfo>();
		S.setInfo("Black");
		S.setTag(0);
		Q.add(S);
		Num=Num+1; 
		while(!Q.isEmpty())
		{
			NodeInfo poll=Q.poll();
			poll.setInfo("Black");
			Iterator<node_info> ite=poll.getNi().iterator();
			while(ite.hasNext() )
			{
				NodeInfo Tmp=(NodeInfo)ite.next();
				if(Tmp.getInfo().equals("White"))
				{
					Tmp.setInfo("Grey");
					Q.add(Tmp);
					Num=Num+1; 
				}
				if(Tmp.getTag()>poll.getTag()+G.getEdge(Tmp.getKey(), poll.getKey()))
				{
					Tmp.setTag(poll.getTag()+G.getEdge(Tmp.getKey(), poll.getKey()));
					NodeInfo T=(NodeInfo)Tmp;
					T.SetPater(poll);
				}
				
			}
		}	
	}
	
	@Override
	public double shortestPathDist(int src, int dest)
	{
		NodeInfo S=(NodeInfo)G.getNode(src);
		NodeInfo E=(NodeInfo)G.getNode(dest);
		if(S==null || E==null) return -1;
		else if(src==dest)return 0;
		else
		{
			SetInit();
			Dijkstra(S);
			return E.getTag();
		}
	}

	@Override
	public List<node_info> shortestPath(int src, int dest) 
	{
		NodeInfo S=(NodeInfo)G.getNode(src);
		NodeInfo E=(NodeInfo)G.getNode(dest);
		double L=shortestPathDist(src,dest);
		List<node_info> arr = new ArrayList<node_info>();
		if(L==-1|| S==null||E==null||L==Inf)return null;
		else 
		{
			arr.add(E);
			if(src==dest)return arr;
			else
			{
				while(E.GetPater()!=null)
				{
					E=E.GetPater();
					arr.add(E);
				}
				return CounterList(arr);
			}
		}
		
	}
	public List<node_info> CounterList(List<node_info> L)// Crate a new List that is in reverse direction  of the giving list
	{
		List<node_info> L1=new ArrayList<node_info>();
		for (int i = L.size()-1; i >= 0; i--) 
		{
			L1.add(L.get(i));
		}
		return L1;
	}
	public static void PrintNodeList(List<node_info> L)// print the NodeList. I built this method for my own Test
	{
		for (int i = 0; i < L.size(); i++)
		{
			NodeInfo N=new NodeInfo(L.get(i));
			N.PrintNode();
			if(i+1<L.size())System.out.print("-->");
		}
		System.out.println();
	}

	@Override
	public boolean save(String file) 
	{
		try{FW=new Formatter(file);}
		catch(Exception e)
		{
			System.err.println("Coundn't Write to  file");
			return false;
		}
		FW.format("%s","Graph");
		FW.format("%n"," ");
		FW.format("%s%s%s", "nodesize : "+G.nodeSize()," edgesize : "+G.edgeSize()," Mc : "+G.getMC());
		FW.format("%n"," ");
		Iterator<node_info> ite=G.getV().iterator();
		while(ite.hasNext())
		{
			node_info N1=ite.next();
			FW.format("%s%s%s", "node key : "+N1.getKey()," node info : "+N1.getInfo()," node tag : "+N1.getTag());
			Iterator<node_info> iteNi=G.getV(N1.getKey()).iterator();
			while(iteNi.hasNext())
			{
				node_info Ni1=iteNi.next();
				FW.format("%s%s"," Connected to node with key : "+Ni1.getKey()," With edge of : "+G.getEdge(Ni1.getKey(),N1.getKey()));
			}
			FW.format("%n"," ");
		}
		FW.close();
		return true;
	}

	@Override
	public boolean load(String file) 
	{
		ArrayList<String> W=LineSArrayList(file);
		loaderrors=0;
		if(W==null)
			{
			System.err.println("loading error");
				return false;
			}
		WGraph_DS G=new WGraph_DS();
		String GraphData=GetGraphDataFromArray(W);
		AddAllNodes(W,G);
		ConnectNodes(W,G);
		if(!G.StringGData().equals(GraphData))
			{
			System.err.println(G.StringGData()+" Not equels to the given data "+GraphData);
				if(G.StringGDataWithOutMc().equals(GetGraphDataFromArrayWithOutMc(W)))
				{
					System.err.println("Wrong input of Mc whille build the graph probley becuse changes that hepend before the save");
					return true;
				}
				return false;
			}
		init(G);
		if(loaderrors==0)return true;
		else return false;
	}
	
	public ArrayList<String> LineSArrayList(String file)
	{
		ArrayList<String> W=new ArrayList<>();
		try {FR=new Scanner(new File(file));}
		catch(Exception e)
		{
			System.err.println("Coundn't find file");
			return null;
		}
		while(FR.hasNextLine())
		{
			String S=FR.nextLine();
			W.add(S);
		}
		FR.close();
		return W;
	}
	
	public String GetGraphDataFromArray(ArrayList<String> W)
	{
		ArrayList<String> S=ListOfWordsInLine(W,1);
		if(S==null)return "error";
		else return "["+S.get(2)+","+S.get(5)+","+S.get(8)+"]";
	}
	public String GetGraphDataFromArrayWithOutMc(ArrayList<String> W)
	{
		ArrayList<String> S=ListOfWordsInLine(W,1);
		if(S==null)return "error";
		else return "["+S.get(2)+","+S.get(5)+"]";
	}
	public ArrayList<String> ListOfWordsInLine(ArrayList<String> W,int Line)
	{
		if(Line>W.size() || Line<0)return null;
		ArrayList<String> S=new ArrayList<String>();
		String L=W.get(Line);
		StringTokenizer tokenizer = new StringTokenizer(L);
		while(tokenizer.hasMoreTokens()) S.add(tokenizer.nextToken());
		return S;
	}
	public void AddAllNodes(ArrayList<String> W,WGraph_DS G)
	{
		int i=2;
		while(i<W.size())
		{
			ArrayList<String> S=ListOfWordsInLine(W,i);
			try
			{
				int Key=Integer.parseInt(S.get(3));
				double tag=Double.parseDouble(S.get(11));
				String Info=S.get(7);
				NodeInfo N=new NodeInfo(tag,Info,Key);
				G.addNode(N);
			}
			catch(Exception e)
			{
				loaderrors++;
				System.err.println("Wrong Data");
			}
			i++;
		}
	}
	
	public void ConnectNodes(ArrayList<String> W,WGraph_DS G)
	{
		int i=2;
		while(i<W.size())
		{
			int j=18;
			ArrayList<String> S=ListOfWordsInLine(W,i);
			int KeyN1=Integer.parseInt(S.get(3));
			while(j<S.size())
			{
				try
				{
					int KeyN2=Integer.parseInt(S.get(j));
					double edge=Double.parseDouble(S.get(j+5));
					if(!G.hasEdge(KeyN1,KeyN2))G.connect(KeyN1, KeyN2, edge);
					else
					{
						if(G.getEdge(KeyN1,KeyN2)!=edge)
							{
								System.err.println("wrong edge data at node :"+KeyN1+" while trying to connect him to node :"+KeyN2);
								loaderrors++;
							}
					}
					j=j+12;
				}
				catch(Exception e)
				{
					loaderrors++;
					System.err.println("Wrong Data");
				}
				
			}
			i++;
		}
	}

	
	

}
