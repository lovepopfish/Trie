
public class Rectangle {
	int row;
	int col;
	char[][] matrix;
	public Rectangle(int col){
		this.col = col;
	}
	
	public Rectangle(int row, int col, char[][] matrix) {
		this.row = row;
		this.col = col;
		this.matrix = matrix;
	}
	
	public char getLetter(int l, int h){
		return matrix[l][h];
	}
	
	public String getColumn(int j){
		char[] tmp = new char[row];
		for(int i=0;i<row;i++){
			tmp[i] = getLetter(i,j);
		}
		return new String(tmp);
	}
	
	public Rectangle append(String word){
		char[][] tmp = new char[row+1][col];
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				tmp[i][j] = matrix[i][j]; 
			}
		}
		for(int j=0;j<col;j++){
			tmp[row][j] = word.charAt(j);
		}
		return new Rectangle(row+1,col,tmp);
	}
	
	public boolean isPartialOK(Trie trie){
		for(int j=0;j<col;j++){
			if (!trie.containsPrefix(getColumn(j))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isCompleteOK(WordGroup words){
		for(int j=0;j<col;j++){
			if (!words.contains(getColumn(j))) {
				return false;
			}
		}
		return true;
	}
	
	public void print(){
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("");
		}
	}
}














