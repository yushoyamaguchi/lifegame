package lifegame;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BoardView extends JPanel implements BoardListener,java.awt.event.MouseListener , java.awt.event.MouseMotionListener {

 private BoardModel boardstate;
 private int[] val3;
 
 private int[] val5;
 private int preMouseState=0;
 private int[] preMousePlace= {-10,-10};
 
 
 //private boolean[][] coodinate;

	   public BoardView(BoardModel bm ){

		    boardstate=bm;
		    this.addMouseListener(this);
		    this.addMouseMotionListener(this);
		   
	   }
	   
	   
	   public void mouseClicked(MouseEvent e) {
		   
	   }
	   public void mouseEntered(MouseEvent e) {
		  // System.err.println("aaa");
		   
	   }
	   public void mouseExited(MouseEvent e) {
		   
	   }
	   public void mousePressed(MouseEvent e) {
		//System.err.println("Pressed: " + e.getX() + ", " + e.getY());
		// (1, 1) のセルの状態を反転させる
		//boardstate.changeCellState(1, 1);
		   val3=ocExchange(e.getX(),e.getY());

		   if(val3[0]>=0&&val3[0]<12&&val3[1]>=0&&val3[1]<12) {
			   System.out.println(val3[0]);
			   System.out.println(val3[1]);
			   boardstate.changeCellState(val3[0], val3[1]);
		   }
		   else {
			   val3[0]=-10;
			   val3[1]=-10;		   
		   }
		   
		   preMouseState=10;
		   preMousePlace[0]=val3[0];
		   preMousePlace[1]=val3[1];
		   //repaint();
	   }
	   public void mouseReleased(MouseEvent e) {
		   
	   }
	   public void mouseDragged(MouseEvent e) {
		   int c,r,cc,rr;
		   val5=ocExchange(e.getX(),e.getY());
          if(preMouseState==10&&val5[0]==preMousePlace[0]&&val5[1]==preMousePlace[1]);
          else if(preMouseState==1&&val5[0]==preMousePlace[0]&&val5[1]==preMousePlace[1]);
          else {
        	  
        	  if(val5[0]>0&&val5[0]<12&&val5[1]>0&&val5[1]<12) {
        	  boardstate.changeCellState(val5[0], val5[1]);
        	  }
          }
          
          preMouseState=1;
		   preMousePlace[0]=val5[0];
		   preMousePlace[1]=val5[1];          
		   
	   }
	   public void mouseMoved(MouseEvent e) {
		   
	   }
	   
	   

	   @Override
	public void paint(Graphics g) {
	super.paint(g); // JPanel の描画処理（背景塗りつぶし）
	// 直線や塗りつぶしの例


	int val[];
	int val2[];

	g.drawLine(20, 20, 260, 20);
	g.drawLine(20, 40, 260, 40);
	g.drawLine(20, 60, 260, 60);
	g.drawLine(20, 80, 260, 80);
	g.drawLine(20, 100, 260, 100);
	g.drawLine(20, 120, 260, 120);
	g.drawLine(20, 140, 260, 140);
	g.drawLine(20, 160, 260, 160);
	g.drawLine(20, 180, 260, 180);
	g.drawLine(20, 200, 260, 200);
	g.drawLine(20, 220, 260, 220);
	g.drawLine(20, 240, 260, 240);
	g.drawLine(20, 260, 260, 260);



	g.drawLine(20, 20, 20, 260);
	g.drawLine(40, 20, 40, 260);
	g.drawLine(60, 20, 60, 260);
	g.drawLine(80, 20, 80, 260);
	g.drawLine(100, 20, 100, 260);
	g.drawLine(120, 20, 120, 260);
	g.drawLine(140, 20, 140, 260);
	g.drawLine(160, 20, 160, 260);
	g.drawLine(180, 20, 180, 260);
	g.drawLine(200, 20, 200, 260);
	g.drawLine(220, 20, 220, 260);
	g.drawLine(240, 20, 240, 260);
	g.drawLine(260, 20, 260, 260);


	for(int s=0;s<boardstate.getRows();s++) {
		for(int t=0;t<boardstate.getCols();t++) {
			if(isAlive(t,s)==true) {

             val=coExchange(t,s);

             g.fillRect(val[0]+1, val[1]+1,19, 19);
			}
		}

    }
  //  repaint();

	   }

	  public void printA() {
		  System.out.print("AAAA");
	  }

	  private boolean isAlive(int x , int y) {
		 if( boardstate.getcells(x,y)==true) return true;
		 else  return false;
	  }

	  private int[] coExchange(int x,int y){
		  int p,q;
		  int val[]=new int[2];
		 p=20*x+20;
		 val[0]=p;
		 q=20*y+20;
		 val[1]=q;
		 return val;

	  }
	  
	  private int[] ocExchange(int x,int y){
		  int p,q;
		  int val2[]=new int[2];
		 //p=20*x+20;
		  x=x-20;
		  p=x/20;
		 val2[0]=p;
		 y=y-20;
		 q=y/20;
		// q=20*y+20;
		 val2[1]=q;
		 return val2;

	  }
	  
	  @Override
	  public void updated(BoardModel model) {
		  
		  repaint();			
           
		}


}
