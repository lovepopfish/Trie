import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Question {
	private HashMap<String,Integer> map = new HashMap<String,Integer>();
	private ArrayList<String> list = new ArrayList<String>();
	private Trie trie;
	private final static int CAPACITY = 2;
	public void init(){
		String[] inputString = Resource.getStringList();
		for(String s:inputString){
			if (!map.containsKey(s)){
				map.put(s, 1);
				list.add(s);
			} else {
				map.put(s, map.get(s)+1);
			}
		}
		trie = new Trie(list);
	}
	
	public void initFilter(){
		String[] inputString = Resource.getDirtyWords();
		trie = new Trie(inputString);
	}
	
	public String filterWord(String word){
		return trie.filter(word);
	}
	
	public String[] googleSearch(String word){
		ArrayList<Integer> index = trie.getPrefixIndex(word);
		return getTopK(index);
	}
	
	private String[] getTopK(ArrayList<Integer> index){
		PriorityQueue<String> minHeap = new PriorityQueue<String>(CAPACITY, new Comparator<String>(){
			public int compare(String o1, String o2) {
				if (map.get(o1) > map.get(o2)) return 1;
				else if (map.get(o1) == map.get(o2)) return 0;
				else return -1;
			}
		});
		int count = 0;
		for(int i : index) {
			if (count < CAPACITY) {
				minHeap.add(list.get(i));
			} else {
				if (map.get(list.get(i)) > map.get(minHeap.peek())) {
					minHeap.poll();
					minHeap.add(list.get(i));
				}
			}
			count++;
		}
		return minHeap.toArray(new String[CAPACITY]);
	}
	
	public static void main(String[] args){
		Question q = new Question();
		q.initFilter();
		String ret = q.filterWord("fuckaaaaaaaaaaaafuckaaaaabbbcccccfuckaafuckshitshabikillabc");
		System.out.println(ret);
	}
}





