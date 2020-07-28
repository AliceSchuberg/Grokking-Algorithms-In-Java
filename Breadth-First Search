import java.util.*;

class Main {
	HashMap<String, HashSet<String>> graph;

	public Main(){
		//Prepare for HashMap
		String[] you = {"alice", "bob","claire"};
		String[] bob = {"alice", "anuj","peggy"};
		String[] alice = {"alice", "peggy"};
		String[] claire = {"alice", "thom","jonny"};
		String[] anuj = {};
		String[] peggy = {};
		String[] thom = {};
		String[] jonny = {};
		
		//Insert all neighbor and their relationships into HashMap
		this.graph = new HashMap<String, HashSet<String>>();
		graph.put("you", new HashSet<String>(Arrays.asList(you)));
		graph.put("bob", new HashSet<String>(Arrays.asList(bob)));
		graph.put("alice", new HashSet<String>(Arrays.asList(alice)));
		graph.put("claire", new HashSet<String>(Arrays.asList(claire)));
		graph.put("anuj", new HashSet<String>(Arrays.asList(anuj)));
		graph.put("peggy", new HashSet<String>(Arrays.asList(peggy)));
		graph.put("thom", new HashSet<String>(Arrays.asList(thom)));
		graph.put("jonny", new HashSet<String>(Arrays.asList(jonny)));
	}

	/*The Algorithm: loop through search_queue to search for mango seller{
						if someone is mango seller {return true;}
						else{go to graph and put his/her neighbor(s) into search_queue}
					}
					after looping, if no one is mango seller, return false;*/
	/* a little note: the original python function begin checking at "you" 's neighbor, while mine begin checking at "you." 
	 ie. "you" was the first thing to check in my function while the python ver. has "alice" /"bob"/"claire" as the first element in the search_queue.*/
	public boolean Breadth_First_Search(){
		Queue<String> search_queue = new LinkedList<String>();//prepare the search queue
		search_queue.offer("you"); //insert the first element to begin the search
		while(!search_queue.isEmpty()){//loop until search_queue is empty
			String person = search_queue.poll();//pop out the first element in quque
			if (person_is_seller(person)){//using person_is_seller() to check if mango seller
				System.out.println(person+" is a mango seller!");
				return true;
			}
			else{
				for (String neighbor : (HashSet<String>)graph.get(person)){
					search_queue.offer(neighbor);//go to graph and put his/her neighbor(s) into search_queue
				}
			}
		}
		return false;
	}

	//simple check function for whether the person is mango seller
	public boolean person_is_seller(String s){
		return s.endsWith("m");
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.Breadth_First_Search();
	}
}
