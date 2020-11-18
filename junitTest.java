package EX1;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import EX1.WGraph_DS.NodeInfo;

public class junitTest {

	@Test
	public void test0() 
	{
		WGraph_DS G1=GraphBuilder(1,0);
		//G1.PrintGData();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G1);
		 boolean flag=W.isConnected();
		 assertTrue(flag);
	}
	@Test
	public void test1a() 
	{
		WGraph_DS G1=GraphBuilder(2,0);
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G1);
		 boolean flag=W.isConnected();
		 assertFalse(flag);
	}
	@Test
	public void test1b() 
	{
		 WGraph_DS G2=GraphBuilder(2,1);
		 WGraph_Algo W2=new WGraph_Algo();
		 W2.init(G2);
		boolean flag=W2.isConnected();
		assertTrue(flag);
	}
	@Test
	public void test2a() 
	{
		 WGraph_DS G=Test2G();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		boolean flag=W.isConnected();
		assertTrue(flag);
	}
	@Test
	public void test2b() 
	{
		 WGraph_DS G=Test2G();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		int edge=W.getGraph().edgeSize();
		assertEquals(edge,7);
	}
	@Test
	public void test2C() 
	{
		 WGraph_DS G=Test2G();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		 WGraph_DS G1=(WGraph_DS) W.copy();
		 boolean flag=G.equals(G1);
		 assertTrue(flag);
	}
	@Test
	public void test2D() 
	{
		 WGraph_DS G=Test2G();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		 WGraph_DS G1=(WGraph_DS) W.copy();
		 G1.removeEdge(0, 1);
		 W.init(G1);
			int edge=W.getGraph().edgeSize();
			assertEquals(edge,6);
	}
	@Test
	public void test2E() 
	{
		 WGraph_DS G=Test2G();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		 G.removeNode(0);
		 assertEquals(W.getGraph().edgeSize(),4);
	}
	@Test
	public void test2F() 
	{
		 WGraph_DS G=Test2G();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		 WGraph_DS G1=(WGraph_DS) W.copy();
		 G.removeNode(0);
		 boolean flag=G.equals(G1);
		 assertFalse(flag);// will print different edge size in error 
	}
	@Test
	public void test3F() 
	{
		NodeInfo N1=new NodeInfo();
		NodeInfo N2=new NodeInfo();
		NodeInfo N3=new NodeInfo();
		NodeInfo N4=new NodeInfo();
		WGraph_DS G=new WGraph_DS();
		G.addNode(N1);
		G.addNode(N2);
		G.addNode(N3);
		G.addNode(N4);	
		G.connect(N1.getKey(), N3.getKey(), 1.5);
		G.connect(N1.getKey(), N2.getKey(), 2);
		G.connect(N2.getKey(), N3.getKey(), 0.25);
		G.connect(N3.getKey(), N4.getKey(), 2.5);
		G.connect(N2.getKey(), N4.getKey(), 0.2);
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		 boolean flag=(W.shortestPathDist(N1.getKey(), N4.getKey())==1.95);
		 assertTrue(flag);// will print different edge size in error 
	}
	@Test
	public void test3A() 
	{
		 WGraph_DS G=Test3G();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		 int L= W.shortestPath(4, 4).size();
		 assertEquals(L,1);
	}
	@Test
	public void test3B() 
	{
		 WGraph_DS G=Test3G();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		 double edge=W.getGraph().getEdge(0,2);
		 boolean flag=(edge==8.5);
		 assertTrue(flag);
	}
	@Test
	public void test3C() 
	{
		 WGraph_DS G=Test3G();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		 int L= W.shortestPath(0, 4).size();
		 assertEquals(L,4);
	}
	@Test
	public void test3D() 
	{
		 WGraph_DS G=Test3G();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		 W.getGraph().connect(0,4,2);
		 double dist= W.shortestPathDist(0, 4);
		 boolean flag=(dist==2);
		 assertTrue(flag);
	}
	@Test
	public void test3E() 
	{
		 WGraph_DS G=Test3G();
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G);
		 WGraph_DS G1=Test3G();
		 W.getGraph().connect(0,4,2);
		assertNotEquals(G1,(WGraph_DS)W.getGraph());
	}
	@Test
	public void test4() 
	{
		WGraph_DS G1=GraphBuilder(10,45);
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G1);
		 //G1.PrintGData();
		 boolean flag=W.isConnected();
		 assertTrue(flag);
	}
	@Test
	public void test5A() 
	{
		WGraph_DS G1=GraphBuilder(100,500);
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G1);
		 //G1.PrintGData();
		 boolean flag=W.isConnected();
		 assertTrue(flag);
	}
	@Test
	public void test5B() 
	{
		WGraph_DS G1=GraphBuilder(100000);
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G1);
		 //G1.PrintGData();
		 boolean flag=W.isConnected();
		 assertTrue(flag);
	}
	@Test
	public void test6A() 
	{
		WGraph_DS G1=GraphBuilder(1000000);
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G1);
		 boolean flag=W.isConnected();
		 assertTrue(flag);
	}
	@Test
	public void test6B() 
	{
		WGraph_DS G1=GraphBuilder(1000000);
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G1);
		 Iterator<node_info> ite=W.getGraph().getV().iterator();
		 node_info S=ite.next();
		 W.getGraph().removeNode(S.getKey());
		 boolean flag=W.isConnected();
		 assertTrue(flag);
	}
	@Test
	public void test6C() 
	{
		WGraph_DS G1=GraphBuilder(1000000);
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(G1);;
		 Iterator<node_info> ite=W.getGraph().getV().iterator();
		 node_info S=ite.next();
		 W.getGraph().removeNode(S.getKey());
		 assertEquals(W.getGraph().edgeSize(),999998);
	}
	@Test
	public void test7A() 
	{
		WGraph_DS G1=new WGraph_DS ();
		WGraph_Algo W=new WGraph_Algo();
		W.init(G1);
		 boolean flag=W.isConnected();
		 assertTrue(flag);
	}
	@Test
	public void test7B() 
	{
		WGraph_DS G1=new WGraph_DS ();
		WGraph_Algo W=new WGraph_Algo();
		W.init(G1);
		node_info n=W.getGraph().getNode(0);
		 boolean flag=(n==null);
		 assertTrue(flag);
	}
	
	@Test
	public void test8() 
	{
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(GraphBuildA());
		 W.isConnected();
		 boolean flag=W.save("JTest8.txt");
		 assertTrue(flag);
	}
	@Test
	public void test9A() 
	{
		WGraph_Algo W=new WGraph_Algo();
		 boolean flag=W.load("JTest8.txt");
		 assertTrue(flag);
	}
	@Test
	public void test9B() 
	{
		WGraph_Algo W=new WGraph_Algo();
		W.load("JTest8.txt");
		 assertEquals(W.getGraph().nodeSize(),5);
	}
	@Test
	public void test10() 
	{
		 WGraph_Algo W=new WGraph_Algo();
		 W.init(GraphBuildA());
		 W.isConnected();
		W.save("JTest8.txt");
		WGraph_Algo W1=new WGraph_Algo();
		W1.load("JTest8.txt");
		WGraph_DS G=(WGraph_DS)W.getGraph();
		WGraph_DS G1=(WGraph_DS)W1.getGraph();
		boolean flag=G.equals(G1);
		 assertTrue(flag);
	}	 
	public static WGraph_DS GraphBuilder(int Num,int Edge)
	{
		WGraph_DS G=new WGraph_DS();
		NodeInfo N=new NodeInfo();
		int S=N.getKey();
		G.addNode(N);
		for (int i = 1; i < Num; i++)
		{
			N=new NodeInfo();
			G.addNode(N);
		}
		if(Edge>((Num-1)*Num)/2)
		{
			Edge=(Num*Num-Num)/2;
			System.out.println("To much Edges");
			System.out.println("Num of Edges Change To: "+Edge);
		}
		if(Num>1)
		{
			for (int i = 0; i < Edge; i++)
			{
				int Rand1=(int)(Math.random()*Num)+S;
				int Rand2=(int)(Math.random()*Num)+S;
				double RandW=(int)(Math.random()*100+1);
				while(G.hasEdge(Rand1, Rand2) || Rand1==Rand2)
				{
					Rand1=(int)(Math.random()*Num)+S;
					Rand2=(int)(Math.random()*Num)+S;
				}
			//System.out.println("Node with key: "+Rand1+" is Connected to Node with key: "+Rand2+" the edge price is : "+RandW);
				G.connect(Rand1, Rand2, RandW);
			}
		}
		return G;
	}
	
	public static WGraph_DS GraphBuilder(int Num)//Crate a connected Graph by connecting the first two nodes to all the other nodes
	{
		WGraph_DS G=new WGraph_DS();
		NodeInfo N=new NodeInfo();
		G.addNode(N);
		int S=N.getKey();
		for (int i = 1; i < Num; i++)
		{
		 N=new NodeInfo();
			G.addNode(N);
		}
		if(Num>1)
		{
			for (int i = S+2; i <S+Num; i++)
			{
				double RandW=(int)(Math.random()*100+1);
			//System.out.println("Node with key: "+Rand1+" is Connected to Node with key: "+Rand2+" the edge price is : "+RandW);
				G.connect(S, i, RandW);
				G.connect(S+1, i, RandW);
			}
		}
		return G;
	}

	public static WGraph_DS Test2G()
	{
		NodeInfo N1=new NodeInfo();
		NodeInfo N2=new NodeInfo();
		NodeInfo N3=new NodeInfo();
		NodeInfo N4=new NodeInfo();
		NodeInfo N5=new NodeInfo();
		WGraph_DS G=new WGraph_DS();
		G.addNode(N1);
		G.addNode(N2);
		G.addNode(N3);
		G.addNode(N4);
		G.addNode(N5);
		 G.connect(N2.getKey(), N4.getKey(),1.5);
		 G.connect(N2.getKey(), N3.getKey(),1.5);
		 G.connect(N2.getKey(), N5.getKey(),7.5);
		 G.connect(N3.getKey(), N5.getKey(),1.5);
		 G.connect(N1.getKey(), N5.getKey(),25);
		 G.connect(N1.getKey(), N3.getKey(),8.5);
		 G.connect(N1.getKey(), N2.getKey(),2.5);
		 return G;
	}
	
	public static WGraph_DS Test3G()
	{
		NodeInfo N1=new NodeInfo();
		NodeInfo N2=new NodeInfo();
		NodeInfo N3=new NodeInfo();
		NodeInfo N4=new NodeInfo();
		NodeInfo N5=new NodeInfo();
		WGraph_DS G=new WGraph_DS();
		G.addNode(N1);
		G.addNode(N2);
		G.addNode(N3);
		G.addNode(N4);
		G.addNode(N5);
		 G.connect(N2.getKey(), N4.getKey(),1.5);
		 G.connect(N2.getKey(), N3.getKey(),1.5);
		 G.connect(N2.getKey(), N5.getKey(),7.5);
		 G.connect(N3.getKey(), N5.getKey(),1.5);
		 G.connect(N1.getKey(), N5.getKey(),25);
		 G.connect(N1.getKey(), N3.getKey(),8.5);
		 G.connect(N1.getKey(), N2.getKey(),2.5);
		 return G;
	}
	
	public static WGraph_DS GraphBuildA()
	{
		NodeInfo N1=new NodeInfo();
		NodeInfo N2=new NodeInfo();
		NodeInfo N3=new NodeInfo();
		NodeInfo N4=new NodeInfo();
		NodeInfo N5=new NodeInfo();
		WGraph_DS G=new WGraph_DS();
		G.addNode(N1);
		G.addNode(N2);
		G.addNode(N3);
		G.addNode(N4);
		G.addNode(N5);
		 G.connect(N2.getKey(), N4.getKey(),1.5);
		 G.connect(N2.getKey(), N3.getKey(),1.5);
		 G.connect(N2.getKey(), N5.getKey(),7.5);
		 G.connect(N3.getKey(), N5.getKey(),1.5);
		 G.connect(N1.getKey(), N5.getKey(),25);
		 G.connect(N1.getKey(), N3.getKey(),8.5);
		 G.connect(N1.getKey(), N2.getKey(),2.5);
		 return G;
	}
	

	
}
