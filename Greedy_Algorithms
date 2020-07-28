import java.util.*;

class Main {
	HashSet<String> states_needed;//集合：要覆盖的州
	HashMap<String, HashSet> stations;//散列表：可供选择的广播台清单
	HashSet<String> final_stations;//集合：最终选择的广播台

	public Main(){
		//导入 要覆盖的州 的数据
		String[] s = {"mt","wa","or","id","nv","ut","ca","az"};
		states_needed = new HashSet<String>(Arrays.asList(s));

		//广播台及其覆盖的州
		String[] kone = {"id","nv","ut"};
		String[] ktwo = {"mt","wa","id"};
		String[] kthree = {"or","nv","ca"};
		String[] kfour = {"nv","ut"};
		String[] kfive = {"ca","az"};

		//把 广播台及其覆盖的州 导入散列表内
		this.stations = new HashMap<String, HashSet>();
		stations.put("kone", new HashSet<String>(Arrays.asList(kone)));
		stations.put("ktwo", new HashSet<String>(Arrays.asList(ktwo)));
		stations.put("kthree", new HashSet<String>(Arrays.asList(kthree)));
		stations.put("kfour", new HashSet<String>(Arrays.asList(kfour)));
		stations.put("kfive", new HashSet<String>(Arrays.asList(kfive)));

		//初始化集合
		this.final_stations = new HashSet<String>();
	}

	/*算法本体：当还有州没有被覆盖时不断循环（从states_needed里检查）｛
			设定best_station和states_covered储存每次递归最优结果（即覆盖最多“未覆盖”州的station和对应州），
			从stations散列表里不断递归（找出以上所指最佳结果），
			递归完毕后把station记录到final_stations中，并从state_needed里移除此次station覆盖的州
			｝
	*/
	public void Greedy_Algorithms(){
		while(!states_needed.isEmpty()){//当还有州没有被覆盖时不断循环（从states_needed里检查）
			
			//设定best_station和states_covered储存每次以下递归最优结果
			String best_station = null;
			HashSet<String> states_covered = new HashSet<String>();

			for (String station: stations.keySet()){
				//设定covered储存单次递归结果
				HashSet<String> covered = new HashSet<String>();
				//找出单次递归里station所能覆盖的“未覆盖”州，效果等同于原文“covered = states_needed & states”
				for (String state : (HashSet<String>)stations.get(station)){
					if (states_needed.contains(state)){
						covered.add(state);
					}
				}
				//如果本次递归station“有效覆盖率”比已储存station高，则替换
				if (covered.size()>states_covered.size()){
					best_station = station;
					states_covered = covered;
				}
			}
			/*上面for loop循环完之后得出本次while loop最优结果（即best station + states_covered）
			储存最优结果到全局（class）变量里，并更新states_needed*/
			for (String state: states_covered){
				if(states_needed.contains(state)){
					states_needed.remove(state);
				}
			}
			final_stations.add(best_station);
		}
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.Greedy_Algorithms();

		//最终结果，由于java里集合和散列表都没有顺序，所以结果可能会和书本有出入
		System.out.println("final_solution:"+ m.final_stations);
	}
}
