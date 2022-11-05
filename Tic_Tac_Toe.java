import java.util.*;

class Tic_Tac_Toe{
	static class Move{
		int x;
		int y;
		public Move(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	public static boolean movesleft(char[][] board){
		for(char[] c : board){
			for(char d : c){
				if(d=='_'){
					return true;
				}
			}
		}
		return false;
	}
	static int evaluate(char b[][])
	{
	    // Checking for Rows for X or O victory.
	    for (int row = 0; row < 3; row++)
	    {
	        if (b[row][0] == b[row][1] &&
	            b[row][1] == b[row][2])
	        {
	            if (b[row][0] == 'O')
	                return +10;
	            else if (b[row][0] == 'X')
	                return -10;
	        }
	    }
	 
	    // Checking for Columns for X or O victory.
	    for (int col = 0; col < 3; col++)
	    {
	        if (b[0][col] == b[1][col] &&
	            b[1][col] == b[2][col])
	        {
	            if (b[0][col] == 'O')
	                return +10;
	 
	            else if (b[0][col] == 'X')
	                return -10;
	        }
	    }
	 
	    // Checking for Diagonals for X or O victory.
	    if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
	    {
	        if (b[0][0] == 'O')
	            return +10;
	        else if (b[0][0] == 'X')
	            return -10;
	    }
	 
	    if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
	    {
	        if (b[0][2] == 'O')
	            return +10;
	        else if (b[0][2] == 'X')
	            return -10;
	    }
	 
	    // Else if none of them have won then return 0
	    return 0;
	}
	public static void printBoard(char[][] board){
		for(char[] c : board){
			for(char d : c){
				System.out.print(d+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static int minMax(char[][] board,boolean is){
		int score = evaluate(board);
		if(score==10 || score==-10){
			return score;
		}
		if(!movesleft(board)){
			return 0;
		}
		if(is){
			int best = -1000;
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(board[i][j]=='_'){
						board[i][j] = 'O';
						best = Math.max(best,minMax(board,!is));
						board[i][j] = '_';
					}
				}
			}
			return best;
		}
		else{
			int best = 1000;
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(board[i][j]=='_'){
						board[i][j] = 'X';
						best = Math.min(best,minMax(board,!is));
						board[i][j] = '_';
					}
				}
			}
			return best;
		}
	}

	public static Move bestMove(char[][] board){
		int bestval=-1000;
		Move m;
		int p=-1;
		int q=-1;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(board[i][j]=='_'){
					board[i][j] = 'O';
					int moveval = minMax(board,false);
					board[i][j] = '_';
					if(moveval>bestval){
						p = i;
						q = j;
						bestval = moveval;
					}
				}
			}
		}
		if(p==-1&&q==-1){
			return null;
		}
		else{
			return new Move(p,q);
		}
	}

	public static void main(String args[]){
		char[][] board = new char[3][3];
		Scanner sc = new Scanner(System.in);

		for(int i=0;i<3;i++){
			Arrays.fill(board[i],'_');
		}
		while(movesleft(board)){
			System.out.println("Please enter the cordinates where you want to place X");
			printBoard(board);
			int a = sc.nextInt();
			int b = sc.nextInt();
			a--;
			b--;
			if(a>=3||a<0||b>=3||b<0||board[a][b]!='_'){
				System.out.println("Invalid input try again!!");
				continue;
			}
			board[a][b]='X';

			printBoard(board);
			Move m = bestMove(board);
			if(m==null){
				System.out.println("Game over!");
				break;
			}
			board[m.x][m.y] = 'O';
			if(evaluate(board)==10){
				System.out.println("Computer won");
				System.out.println("Game over!");
				printBoard(board);
				break;
			}
			//printBoard(board);
		}
	}
}