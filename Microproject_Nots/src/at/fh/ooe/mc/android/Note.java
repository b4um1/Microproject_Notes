package at.fh.ooe.mc.android;

public class Note {
	private String title;
	private String text;
	
	public Note(String _title,String _text){
		title = _title;
		text = _text;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
}
