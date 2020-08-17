package lifegame;
import java.awt.*;

import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import  java.awt.BorderLayout;
import  java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import  javax.swing.JFrame;
import  javax.swing.JPanel;
import  javax.swing.SwingUtilities;
import java.awt.event.*;
public class Main implements Runnable ,java.awt.event.ActionListener  ,java.awt.event.WindowListener {

	JButton nextButton,undoButton,NewGameButton,AutoButton,aButton;
	boolean con;
	//BoardListener 

     BoardModel model = new BoardModel(12,12);
     
    /* ArrayList<BoardModel> model2;
     ArrayList<BoardView> view2;*/
     public Main() {
    	//this.addWindowListener(this);
    	 con=false;
     }
     
	public static void main(String[] args) {


		SwingUtilities.invokeLater(new Main());

	}




		public void run() {



			// BoardModel model = new BoardModel(12,12);
				model.addListener(new ModelPrinter());
				//model.addListener(new bEnable(this));
				//model.addListener(new Main());
			//	model.addListener(new ViewPrinter());
			    /* auto a= new auto(this);
			     Thread a2=new Thread(a);
			     a2.start();*/				
				

			// BoardModel の作成とchangeCellState の呼び出しを行う処理をここで実行。
			// next とundo の呼び出しを取り除き、「グライダー」が設置された状態としておく。
			// ウィンドウを作成する
			 JFrame frame = new JFrame();
			 frame.addWindowListener(this);
			 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			// ウィンドウ内部を占有する「ベース」パネルを作成する
			JPanel base = new JPanel();
			//JPanel base2 = new JPanel();
			//addWindowListener(this);
			 frame.setContentPane(base);
			 
			 frame.setTitle( "Lifegame" );
			 base.setPreferredSize(new Dimension(600, 450));
			 //base2.setPreferredSize(new Dimension(600, 450));// 希望サイズの指定
			 frame.setMinimumSize(new Dimension(450, 400)); // 最小サイズの指定
			 base.setLayout(new BorderLayout()); // base 上に配置するGUI 部品のルールを設定
			 


			 
			 
			 
			 BoardView view = new BoardView(model);
			 CellView view2 = new CellView(model);
			model.setV(view);
			model.setC(view2);
			 base.add(view, BorderLayout.CENTER);
			
			 base.add(view2, BorderLayout.EAST);

			 view2.setPreferredSize(new Dimension(300, 450));
			 
			 frame.pack(); // ウィンドウに乗せたものの配置を確定する
			 frame.setVisible(true); // ウィンドウを表示する
			 JPanel buttonPanel = new JPanel(); // ボタン用パネルを作成し
			 base.add(buttonPanel, BorderLayout.SOUTH); // base の下端に配置する
			 model.addListener(view);
			 model.addListener(view2);
			 //view2.setLayout(new FlowLayout());


			 //aButton=new JButton("a");
			 
			 nextButton=new JButton("Next");
			 undoButton=new JButton("Undo");
			 NewGameButton=new JButton("NewGame");
			 AutoButton=new JButton("Auto");
			 nextButton.addActionListener(this);
			 undoButton.addActionListener(this);
			 NewGameButton.addActionListener(this);
			 AutoButton.addActionListener(this);
			// model.addListener(listener);

			 buttonPanel.setLayout(new FlowLayout()); // java.awt.FlowLayout を設定
			 buttonPanel.add(nextButton);
			 buttonPanel.add(undoButton);
			 buttonPanel.add(NewGameButton);
			 buttonPanel.add(AutoButton);

			 //view2.add(aButton);
			 
			 //model.addListener(new Main());
			 model.addListener(new bEnable(this));
			 if(model.isUndoable()==false) {
				 undoButton.setEnabled(false);
			 }
			 
			 
		/*	 JFrame frame2 = new JFrame();
			 frame2.addWindowListener(this);
			 frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			// ウィンドウ内部を占有する「ベース」パネルを作成する
			JPanel base2 = new JPanel();
			//addWindowListener(this);
			 frame2.setContentPane(base2);
			 frame2.setTitle( "Lifegame" );
			 base2.setPreferredSize(new Dimension(600, 450)); // 希望サイズの指定
			 frame2.setMinimumSize(new Dimension(450, 400)); // 最小サイズの指定
			 base2.setLayout(new BorderLayout()); // base 上に配置するGUI 部品のルールを設定


			 
			 
			 
			 CellView view2 = new CellView(model);
			model.setC(view2);
			 base2.add(view2, BorderLayout.CENTER);
			 frame2.pack(); // ウィンドウに乗せたものの配置を確定する
			 frame2.setVisible(true); // ウィンドウを表示する
			 JPanel buttonPanel2 = new JPanel(); // ボタン用パネルを作成し
			 base2.add(buttonPanel2, BorderLayout.SOUTH); // base の下端に配置する
			 model.addListener(new CellView(model));		*/	 
			 
			 

		}

          public void nextdo() {
	            model.next();
	          // model.printForDebug();
	
          }


		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==nextButton) {
				model.next();
				//nextdo();
			}
			if(e.getSource()==NewGameButton) {
				Main ma=new Main();
				ma.run();

			}
			if(e.getSource()==undoButton) {
				model.undo();
			}
			if(e.getSource()==AutoButton) {
				con=true;
			     auto a= new auto(this);
			     Thread a2=new Thread(a);
			     a2.start();					
				
			}
		}
		
	    public void windowOpened(WindowEvent e) {       // 開かれた
	       // System.out.println("windowOpened");
	    }
	    public void windowClosing(WindowEvent e) {      // 閉じられている
	        System.out.println("windowClosing");
	        con=false;
	    }
	    public void windowClosed(WindowEvent e) {       // 閉じた
	        System.out.println("windowClosed");
	    }
	    public void windowIconified(WindowEvent e) {    // アイコン化された
	        //System.out.println("windowIconified");
	    }
	    public void windowDeiconified(WindowEvent e) {  // 非アイコン化された
	        //System.out.println("windowDeiconified");
	    }
	    public void windowActivated(WindowEvent e) {    // アクティブになった
	       // System.out.println("windowActivated");
	    }
	    public void windowDeactivated(WindowEvent e) {  // 非アクティブになった
	        //System.out.println("windowDeactivated");
	    }		
		
}




