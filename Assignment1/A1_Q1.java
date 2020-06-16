/*	CSCI 3110 - Assignemnt 1 - Q1
	Li, Yansong B00755354 
	Wang, Le B00761974 2020-5-15*/

import java.util.*;
public class A1_Q1 {
	static Map<String, Integer> indexMap = new HashMap<>();
	static Map<Integer, String> intToString = new HashMap<>();
    public static void main(String[] args) {
        int k;
        Scanner in = new Scanner(System.in);
        int numberOfStrings = in.nextInt();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < numberOfStrings; i++) {
            list.add(in.next());
        }
        if (list.size() == 0) {
            return;
        }
        k = list.get(0).length();
        for (; k > 1; k--) {
        	//chop it
            Set<String> tuples = Chop(list, k);
            int index = 0;
            for(String s : tuples){
            	String u = s.substring(0, k-1);
            	String v = s.substring(1);
            	if(!indexMap.containsKey(u)){
            		indexMap.put(u, index);
            		intToString.put(index++, u);
				}
            	if(!indexMap.containsKey(v)){
            		indexMap.put(v, index);
            		intToString.put(index++, v);
				}
			}
            //make the graph
            int[][] graph = MakeGraph(tuples, k);
            //find the start of output string
            int startIndex = findStart(graph);
            if(startIndex == -1){
            	continue;
			}
            List<String> resultList = new ArrayList<>();
            //do dfs
            dfs(resultList, graph, startIndex);
            if(!checkAllUsed(graph)){
            	continue;
			}
            System.out.print(k + " ");
            String s = "";
            for(int i = resultList.size() - 1; i >= 0; i--){
            	s = s + (i == resultList.size() - 1 ? resultList.get(i)
						: resultList.get(i).charAt(resultList.get(i).length()-1));
			}
            System.out.print(s);
            return;
        }
        String result = "";
        int[] character = new int[26];
        for(String s : list){
        	for(int i = 0; i< s.length(); i++){
        		int index = s.charAt(i) - 'A';
        		if(character[index] == 0){
        			result = result + s.charAt(i);
					character[index] = 1;
				}
			}
		}
		System.out.print(k + " " + result);
    }
    //check the k-tuples have been all used or not
	private static boolean checkAllUsed(int[][] graph) {
    	for(int i = 0; i< graph.length; i++){
    		for(int j = 0; j< graph.length; j++){
    			if(graph[i][j] == 1){
    				return false;
				}
			}
		}
    	return true;
	}
	//do a depth first search
	private static void dfs(List<String> resultList, int[][] graph, int index) {
    	for(int j = 0; j< indexMap.size(); j++){
    		if(graph[index][j] == 1){
    			graph[index][j] = 0;
    			dfs(resultList, graph, j);
			}
		}
    	resultList.add(intToString.get(index));
	}
	//find the start of the output string
	private static int findStart(int[][] graph) {
		int startIndex = 0;
		boolean findStart = false;
		boolean findEnd = false;
		for(int i = 0; i< indexMap.size(); i++){
			//count in degree
			int degree_out = 0;
			int degree_in = 0;
			for(int j = 0; j< indexMap.size(); j++){
				if(graph[i][j] == 1){
					degree_out++;
				}
				if(graph[j][i] == 1){
					degree_in++;
				}
			}
			if(degree_out > 0){
				if(!findStart)
					startIndex = i;
			}
			if(degree_out > degree_in){
				if(!findStart){
					findStart = true;
					startIndex = i;
				}else{
					return -1;
				}
			}else if(degree_in > degree_out){
				if(!findEnd){
					findEnd = true;
				}else{
					return -1;
				}

			}else  if(degree_in != degree_out){
				return -1;
			}
		}
		return findEnd == findStart ? startIndex : -1;
	}
	//chop all input strings into k-tuple
    public static Set<String> Chop(ArrayList<String> list, int k) {
        Set<String> distinctKTuple = new HashSet<>();
        String tempKTuple;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length() - k + 1; j++) {
                tempKTuple = list.get(i).substring(j, j + k);
				distinctKTuple.add(tempKTuple);
            }
        }
        return distinctKTuple;
    }
    //make the graph of k-tuples
    public static int[][] MakeGraph(Set<String> set, int k) {
    	int[][] graph = new int[indexMap.size()][indexMap.size()];
    	for(String s : set){
    		String u = s.substring(0, k-1);
    		String v = s.substring(1);
    		graph[indexMap.get(u)][indexMap.get(v)] = 1;
		}
    	return graph;
    }
}
