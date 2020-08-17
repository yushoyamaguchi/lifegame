package lifegame;

public class bEnable implements BoardListener {
	private Main ma;
	
	public bEnable(Main m) {
		ma=m;
		
	}
	@Override
	public void updated(BoardModel model) {
		if(model.isUndoable()==false) {
			//System.out.println("NG  ");
		ma.undoButton.setEnabled(false);
		}
		else if(model.isUndoable()==true) {
			//System.out.println("OK  ");
			ma.undoButton.setEnabled(true);
		}

	}
}
