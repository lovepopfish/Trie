import java.util.LinkedList;


public class TrieNode {
	private LinkedList<TrieNode> children;
	private char character;
	private Integer index;
	private boolean terminate = false;
	public TrieNode(){
		children = new LinkedList<TrieNode>();
	}
	
	public TrieNode(char character){
		this();
		this.character = character;
	}
	
	public char getChar(){
		return character;
	}
	
	public void setChar(char character){
		this.character = character;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public Integer getIndex(){
		return index;
	}
	
	public boolean getTerminate(){
		return terminate;
	}
	
	public void setTerminate(boolean flag){
		terminate = flag;
	}
	
	public TrieNode getChild(char character){
		for(TrieNode child : children){
			if (child.getChar() == character) {
				return child;
			}
		}
		return null;
	}
	
	public LinkedList<TrieNode> getChildren(){
		return children;
	} 

	public void addWord(String word, int index){
		if (word.length()==0||word==null) return;
		char c = word.charAt(0);
		TrieNode child = getChild(c);
		if (child==null){
			child = new TrieNode(c);
			children.add(child);
		}
		if (word.length()>1) {
			child.addWord(word.substring(1), index);
		} else {
			child.setIndex(index);
			child.setTerminate(true);
		}
	}
}










