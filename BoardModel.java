package lifegame;

import java.util.ArrayList;

public class BoardModel {
	private int cols;
	private int rows;
    private boolean[][] cells;
    private ArrayList<BoardListener> listeners;
    private ArrayList<boolean[][]> BoardHistory;
    private BoardView v;
    private CellView c;


    public BoardModel(int c ,int r) {
		cols = c;
		rows = r;
		cells = new boolean[rows][cols];
		listeners = new ArrayList<BoardListener>();
		BoardHistory=new ArrayList<boolean[][]>();

	  }

    synchronized public void setV(BoardView vv) {
    	this.v=vv;

    }
    
    synchronized public void setC(CellView cc) {
    	this.c=cc;

    }

    synchronized public int getCols() {
		return cols;
	}
    synchronized public boolean getcells(int x,int y) {
		if(cells[y][x]==true) return true;
		else  return false;
	}

    synchronized public  int getRows() {
		return rows;
	}

    synchronized public void printForDebug() {
		int p;
		int q;

		for(p=0;p<rows;p++) {
			for(q=0;q<cols;q++) {
			if(cells[p][q]==true)
				System.out.print("*");
		    else if(cells[p][q]==false)
		    	System.out.print(".");
		}
			System.out.println(" ");
		}
		System.out.println(" ");
		}

    synchronized public void repaintCall(){

		v.repaint();

	}

    synchronized public void printAA() {
		v.printA();
	}

    synchronized public void changeCellState(int x,int y) {
    	history(cells);
			if(cells[y][x]==true)
				cells[y][x]=false;
			else if(cells[y][x]==false)
			    cells[y][x]=true;
			fireUpdate();

		}
	
	/*public void buttonEnabled() {
		
	}*/



    synchronized public void addListener(BoardListener listener) {
	  listeners.add(listener);

	   }


    synchronized private void fireUpdate() {
	 for (BoardListener listener: listeners) {
       listener.updated(this);
        }
	 }


    synchronized public void next() {
     int p,q,count,i,pp,qq;
     int vecr[]= {0,1,1,1,0,-1,-1,-1};
     int vecc[]= {-1,-1,0,1,1,1,0,-1};
     boolean nextcells[][];
     nextcells = new boolean[rows][cols];
     history(cells);
     //System.out.println("a   ");
     for(p=0;p<rows;p++) {
         for(q=0;q<cols;q++) {
           count=0;
           for(i=0;i<8;i++) {
            if(noCellCheck(p,q,i)==1) ;
            else if(cells[p+vecr[i]][q+vecc[i]]==true) count++;

            }

			if(cells[p][q]==true) {
             if(count==2||count==3)nextcells[p][q]=true;
             else nextcells[p][q]=false;
			}
			else if(cells[p][q]==false) {
	             if(count==3)nextcells[p][q]=true;
	             else nextcells[p][q]=false;
			}

         }
     }


     for(pp=0;pp<rows;pp++) {
         for(qq=0;qq<cols;qq++) {
          cells[pp][qq]=nextcells[pp][qq];
         }
     }

          //history(cells);

           fireUpdate();

   }

    synchronized private int noCellCheck (int ro , int co , int ii) {
int rr=getRows();
int cc=getCols();
if((ii==3||ii==4||ii==5) && co==cc-1) return 1;
else if((ii==7||ii==0||ii==1) && co==0) return 1;
else if((ii==1||ii==2||ii==3) && ro==rr-1) return 1;
else if((ii==5||ii==6||ii==7) && ro==0) return 1;
else return 0;
}



    synchronized public void undo() {
int pp,qq;
int k=BoardHistory.size();
boolean cells2[][];
cells2 = new boolean[rows][cols];

k=k-1;
cells2=BoardHistory.get(k).clone();

for(pp=0;pp<rows;pp++) {
    for(qq=0;qq<cols;qq++) {
    	cells[pp][qq]=cells2[pp][qq];
    }
}

BoardHistory.remove(k);
fireUpdate();

}



    synchronized private void history(boolean now[][]) {

int pp,qq;

boolean prevcells[][];
prevcells = new boolean[rows][cols];


for(pp=0;pp<rows;pp++) {
    for(qq=0;qq<cols;qq++) {
    	prevcells[pp][qq]=cells[pp][qq];
    }
}

BoardHistory.add(prevcells);

if(BoardHistory.size()>=32) BoardHistory.remove(0);


//BoardHistory.add(now);
}

    synchronized public boolean isUndoable() {

	if(BoardHistory.size()>0)  return true;
	else return false;

}




}


