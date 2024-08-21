import java.util.Scanner;
import java.util.concurrent.locks.Lock;

public class HumanPlayer extends Player {

	private Scanner s = new Scanner(System.in);
	private Lock lock;
	
	public HumanPlayer(GameBoard g, char symbol, Lock l,int score, String name) {
		super(g,symbol,score);
		this.lock=l;
		this.name = name;
	}

	@Override
	public void run() {
		while(!super.GetGame().IsFinish()){
			this.lock.lock();	
			synchronized(super.GetGame()) {
				if(super.GetGame().IsFinish()) {
					lock.unlock();
					break;
				}
					
				System.out.println("please make move, select column ");
				this.SetPlace();
				super.GetGame().Print();
				this.WhoWon();
				
				this.lock.unlock();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			super.run();
		}
			
		}
	}

	@Override
	public void SetPlace() {
		boolean flag=true;
		int cols = super.GetGame().getColumns(); 
		int rows = super.GetGame().getRows();
		int p = 0;
		
		while (flag) {
			try {
				p = s.nextInt();
			}
			catch(Exception e) {
				System.out.println("wrong input, try again");
				s.nextLine();
				continue;
			}
			if(p >= cols) {
				System.out.println("columns out of bounds, try again");
				continue;
			}
			if (super.GetGame().GetSeq().get(p).equals(' '))
				flag=false;
			else
				System.out.println("this column is full please try again:\n");

		}
			
		for(int r=0; r<rows-1; r++)
			if(super.GetGame().GetSeq().get(p+cols).equals(' '))
				p = p+cols;
			else
				break;
		
		super.GetGame().DoMove(p,super.Getsymbol());
		
	}
	
	
}
