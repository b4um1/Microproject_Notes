package at.fh.ooe.mc.android;


public class Note {
	private int id;
	private String title;
	private String text;

	public Note(String _title, String _text) {
		title = _title;
		text = _text;
	}
	public Note(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	public String toString(){
		return title + " " + text ;
	}

}
