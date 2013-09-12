
public class Question2 {
	WordGroup[] wordGroup;
	Trie[] tries;
	int maxWordLength;
	public Question2(){
		wordGroup = WordGroup.createGroup(Resource2.getListOfWords());
		maxWordLength = wordGroup.length;
		tries = new Trie[maxWordLength];
	}
	
	public Rectangle makeRectangle(){
		int maxArea = maxWordLength * maxWordLength;
		for(int z = maxArea;z>0;z--){
			for(int i=1;i<=maxWordLength;i++){
				if (z % i == 0){
					int j = z / i;
					if (j <= maxWordLength){
						Rectangle ans = makeParticalRectangle(i,j);
						if (ans!=null){
							return ans; 
						}
					}
				}
			}
		}
		return null;
	}
	
	public Rectangle makeParticalRectangle(int row, int col){
		if (wordGroup[row-1] == null || wordGroup[col-1] == null) {
			return null;
		}
		if (tries[row-1]==null){
			tries[row-1] = new Trie(wordGroup[row-1].getList());
			}
		return makeParticalRectangleRec(row, col, new Rectangle(col));
	}
	
	public Rectangle makeParticalRectangleRec(int row, int col, Rectangle rectangle){
		if (row == rectangle.row){
			if (rectangle.isCompleteOK(wordGroup[row-1])) {
				return rectangle;
			} 
			return null;
		}
		
		if (!rectangle.isPartialOK(tries[row-1])){
			return null;
		}
		
		for(int i=0;i<wordGroup[col-1].getList().size();i++){
			Rectangle tmp = makeParticalRectangleRec(row,col,rectangle.append(wordGroup[col-1].getWord(i)));
			if (tmp!=null){
				return tmp;
			}
		}
		
		return null;
	}
	
	public static void main(String[] args){
		Question2 q = new Question2();
		Rectangle r = q.makeRectangle();
		if (r!=null){
			r.print();
		}
	}
}









