package lifegame;

public class auto  implements Runnable {
	private Main ma;
	public auto(Main m) {
		ma=m;
		
	}
	
	public void run() {
		
		//for(int i=0;i<10;i++) {
		//int i=0;
			while(ma.con) {
            try {
            	Thread.sleep(500);
              ma.nextdo();
            //  System.out.println( (i + 1) + "度目の処理");
            } catch (InterruptedException e) {
                
            }
		//i++;
	  }
	}

}
