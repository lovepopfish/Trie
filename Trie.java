import java.util.ArrayList;


public class Trie {
	private TrieNode root;
	public Trie(String[] list){
		root = new TrieNode();
		for(int i=0;i<list.length;i++){
			root.addWord(list[i], i);
		}
	}
	
	public Trie(ArrayList<String> list){
		root = new TrieNode();
		for(int i=0;i<list.size();i++){
			root.addWord(list.get(i), i);
		}
	}
	
	public boolean contains(String word){
		TrieNode cur = root;
		for(int i=0;i<word.length();i++){
			cur = cur.getChild(word.charAt(i));
			if (cur == null) return false;
		}
		return cur.getIndex()!=null;
	}
	
	public boolean containsPrefix(String word){
		TrieNode cur = root;
		for(int i=0;i<word.length();i++){
			cur = cur.getChild(word.charAt(i));
			if (cur==null) return false;
		}
		return true;
	}
	
	public ArrayList<Integer> getPrefixIndex(String word){
		ArrayList<Integer> index = new ArrayList<Integer>();
		TrieNode cur = root;
		for(int i=0;i<word.length();i++){
			cur = cur.getChild(word.charAt(i));
			if (cur == null) return index;
		}
		getPrefixIndexRec(index,cur);
		return index;
	}
	
	private void getPrefixIndexRec(ArrayList<Integer> index, TrieNode cur){
		if (cur.getIndex()!=null) {
			index.add(cur.getIndex());
		}
		if (cur.getChildren()!=null){
		for(TrieNode node:cur.getChildren()){
			getPrefixIndexRec(index,node);
			}
		}
	}
	
	public String filter(String word){
		for(int i=0;i<word.length();i++){
			TrieNode cur = root;
			for(int j=i;j<word.length();j++){
				cur = cur.getChild(word.charAt(j));
				if (cur==null) break;
				if (cur.getTerminate()==true){
					word  = replace(word,i,j);
					break;
				}
			}
		}
		return word;
	}
	
	private String replace(String word, int i, int j){
		int k = j - i;
		String replace = "";
		while(k >= 0){
			replace += "*";
			k--;
		}
		return word.substring(0,i) + replace + word.substring(j+1);
	}
}











