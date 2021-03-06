import java.util.*;
import java.lang.*;

class Main {
	HashMap<String, HashMap> graph;
	HashMap<String, Double> costs;
	HashMap<String, String> parents;
	ArrayList<String> processed;

	public Main(){
		//初始化graph（图）
		this.graph = new HashMap<String, HashMap>();
			//start的邻居+start到达邻居的长度
		HashMap<String, Double> start = new HashMap<String, Double>();
		start.put("a", 6.0);
		start.put("b", 2.0);
			//a的邻居+a到达邻居的长度
		HashMap<String, Double> a = new HashMap<String, Double>();
		a.put("fin", 1.0);
			//b的邻居+b到达邻居的长度
		HashMap<String, Double> b = new HashMap<String, Double>();
		b.put("a", 3.0);
		b.put("fin", 5.0);
			//final的邻居+final到达邻居的长度
		HashMap<String, Double> fin = new HashMap<String, Double>();

			//把上面的关系汇总到graph里，形成：graph包含众多节点和其邻居的关系/长度
			//散列表（graph）包含散列表（节点node 和 和其邻居之间长度cost）
		graph.put("start", start);
		graph.put("a",a);
		graph.put("b",b);
		graph.put("fin",fin);

		//初始化Costs 和 Parents 和 Processed数组
			//Costs记录由起点到达该地点的最快/省的总额Cost
		this.costs = new HashMap<String, Double>();
		costs.put("a", 6.0);
		costs.put("b", 2.0);
		costs.put("fin", Double.POSITIVE_INFINITY);

		//Parents记录最省/快路线下的关系图/父节点
			//由final不断溯源可以重现最省路线
		this.parents = new HashMap<String, String>();
		parents.put("a", "start");
		parents.put("b", "start");
		parents.put("fin", null);

		this.processed = new ArrayList<String>();
	}

	///总作用就是返回processed列表里没有的，并且花费是costs散列表里最低的
	public String find_lowest_cost_node(){
		double lowest_cost = Double.POSITIVE_INFINITY;//每次用无限大来比较
		String lowest_cost_node = null;//先设为null, 用来终止 算法里的while loop
		//将Costs散列表里每个key/value拿出来对比，找出最小（且不存在于processed）,类似于选择排序
		for (String node : this.costs.keySet()){
			double cost = costs.get(node);
			if (cost < lowest_cost && !processed.contains(node)){
				lowest_cost = cost;
				lowest_cost_node = node;
			}
		}
		return lowest_cost_node;
	}

	/*
	算法本体：通过find_lowest_cost_node()找出对应 node 和 起点到该node的总花费，
			再把node丢回graph里，找出 它的邻居 和 它到邻居之间的cost，
			叠加 起点到该node的总花费，得出 起点到它邻居的总花费，
			如果 起点到它邻居的总花费 小于其他路线时的，则更新costs散列表和parents散列表
			更新processed列表防止重复操作
			循环
	*/
	public void Dijkstra_Algorithm(){
		String node = find_lowest_cost_node();//通过find_lowest_cost_node()找出对应 node
		while (node != null){
			double cost = costs.get(node);//起点到该node的总花费
			HashMap<String, Double> neighbors = graph.get(node);//再把node丢回graph里，找出 它的邻居 和 它到邻居之间的cost，也就是最初（散列表包含散列表）中的 子散列表
			for (String n: neighbors.keySet()){
				double new_cost = cost + neighbors.get(n);//叠加 起点到该node的总花费，得出 起点到它邻居的总花费
				if (costs.get(n) > new_cost){//如果 起点到它邻居的总花费 小于其他路线时的
					costs.replace(n, new_cost);//则更新costs散列表和parents散列表
					parents.replace(n, node);
				}
			}
			processed.add(node);//更新processed
			node = find_lowest_cost_node();//继续循环while loop
		}
	}

	public static void main(String[] args) {
		Main a = new Main();
		//System.out.println(a.find_lowest_cost_node());
		a.Dijkstra_Algorithm();
		//last cost to fin node
		System.out.println(a.costs.get("fin"));

		//最省路线
		System.out.print("fin->");
		String n = a.parents.get("fin");
		do{
			if (n != "start"){
				System.out.print(n+"->");}
			else{
				System.out.print(n);}
			n = a.parents.get(n);
		}while(n != null);
		System.out.println();

		//processed过的node
		System.out.println(Arrays.toString(a.processed.toArray()));
	}
}
