package lifegame;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.JLabel;
import java.awt.Container;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class CellView extends JPanel implements BoardListener{
	 private BoardModel boardstate;
	 private int crows,ccols;
	 private int generation;
	 private ArrayList<Integer> cellnum;
	
	   public CellView(BoardModel bm ){
		   cellnum=new ArrayList<Integer>();
		    boardstate=bm;		   
	   }
	   
	public void paint(Graphics g) {
		   int cellnum2[];
		   cellnum2=new int[100];
		   
		   int i;
		   int k;
		   int first=0;
		   int tate,yoko;
		   super.paint(g);
		  
		   if(generation<22) {
			   cellnum.add(LivingCells());
			   
			   generation++;
		   } else {
			   cellnum.remove(0);
			   cellnum.add(LivingCells());
			   generation++;
		   }
		   
		   g.drawLine(20,20, 20, 300);
		   g.drawLine(20,300, 270, 300);
		   
		   for(i=1;i<11;i++) {
			   g.drawLine(20+25*i,295, 20+25*i, 305);
			   
		   }
		   
		   for(i=1;i<10;i++) {
			   g.drawLine(17,300-30*i, 23, 300-30*i);
			   
		   }		   
		   
		   k=cellnum.size();
		   for(i=0;i<k-1;i++) {
			   g.drawLine(20+25*i, 300-6*cellnum.get(i), 45+25*i, 300-6*cellnum.get(i+1));
			   
		   }

		   
		  // tate=LivingCells()*15;
		  // g.drawLine(10, 300, 260, 300);
		   
	   }
	
	  @Override
	  public void updated(BoardModel model) {		
		  		repaint();
		  		generation++;
		}
	  
	  public int LivingCells() {
		  int num=0;
		  crows=boardstate.getRows();
		  ccols=boardstate.getCols();
		     for(int p=0;p<crows;p++) {
		         for(int q=0;q<ccols;q++) {
		        	 if(boardstate.getcells(p,q)==true) {
		        		 num++;
		        	 }
		        	 
		         }
		     }
		  
		  return num;
	  }

	

}
