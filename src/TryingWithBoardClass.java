
public class TryingWithBoardClass {

	public static void main(String[] args) {
		final int SIZE = 7;
		Board b = new Board(SIZE,SIZE);
		int counter = 0;
		Coordinate loc; 
		int row = 0, col = 0;
		int i = 0;
		boolean win = false;
		while (!(win)) {
			loc = b.getClick();
			row = loc.getRow();
			col = loc.getCol();

			counter++;
			if (counter%2!=0 && ifFullRow1(col, SIZE, loc, i)==false) {
				b.putPeg("red", SIZE-1, col);
			}
			if (counter%2==0 && ifFullRow1(col, SIZE, loc, i)==false) {
				b.putPeg("yellow", SIZE-1, col);
			}
		
		}
	
	}

	public static boolean ifFullRow1 (int col, int SIZE, Coordinate loc, int i) {
		boolean full = false;
		int [] fall;
		fall = new int[SIZE];
			col = loc.getCol()+1;
			fall[i] = col; //make it so it loops every time it clicks and fall[variable]
			for (int j = 0; j<SIZE; j++) {
				if (fall[j]==fall[i] && j!=i && fall[j]!=0) {
					full = true;
				}
		}
			i++;
		System.out.println(full);
		return full;
	}
		
}