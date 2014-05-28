package at.fh.ooe.mc.android.model;

public class Note {
	private int id;
	private String title;
	private String text;
	private String date;
	private String pic_link;

	public Note(String _title, String _text) {
		title = _title;
		text = _text;
	}

	public Note() {

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPic_link() {
		return pic_link;
	}

	public void setPic_link(String pic_link) {
		this.pic_link = pic_link;
	}

	public String toString() {
		return title + " " + text;
	}

}
