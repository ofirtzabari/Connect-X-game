import java.util.Random;
import java.util.concurrent.locks.Lock; 

public class MachinePlayer extends Player{
	
	private Random rand = new Random();
	private Lock lock;
	
	public MachinePlayer(GameBoard g, char symbol, Lock l,int score) {
		super(g,symbol,score);
		this.lock=l;
		name = "Machine";
	}

	@Override
	public void run() {
		while(!super.GetGame().IsFinish()) {
		this.lock.lock();	
		synchronized(super.GetGame()) {
			if(super.GetGame().IsFinish()) {
				lock.unlock();
				break;
			}
				
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
			p = rand.nextInt(cols);
			if (super.GetGame().GetSeq().get(p).equals(' '))
				flag=false;
		}
			
		for(int r=0; r<rows-1; r++)
			if(super.GetGame().GetSeq().get(p+cols).equals(' '))
				p = p+cols;
			else
				break;
		
		super.GetGame().DoMove(p,super.Getsymbol());
		
	}
	
	
	
	

}
