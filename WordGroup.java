import java.util.ArrayList;


public class WordGroup {
	private ArrayList<String> list;
	public WordGroup(){
		list = new ArrayList<String>();
	}
	
	public void addWord(String word){
		list.add(word);
	}
	
	public ArrayList<String> getList(){
		return list;
	}
	
	public String getWord(int index){
		return list.get(index);
	}
	
	public boolean contains(String word){
		return list.contains(word);
	}
	
	public static WordGroup[] createGroup(String[] list){
		int maxLength = 0;
		for(int i=0;i<list.length;i++){
			if (list[i].length() > maxLength){
				maxLength = list[i].length();
			}
		}
		WordGroup[] wordGroup = new WordGroup[maxLength];
		for(int i=0;i<list.length;i++){
			int len = list[i].length()-1;
			if (wordGroup[len]==null){
				wordGroup[len] = new WordGroup();
			} 
			wordGroup[len].getList().add(list[i]);
		}
		return wordGroup;
	}
}
