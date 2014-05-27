package at.fh.ooe.mc.android;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewDebug.FlagToString;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.android.database.DatabaseHelper;

class MyArrayAdapter extends ArrayAdapter<Note> {
	private final String LOG_TAG = "ArrayAdapter";
	List<Note> mList;
	Activity mContext;
	DatabaseHelper mDb;

	public MyArrayAdapter(Activity _activity, int _textViewResourceId,
			List<Note> _objects, DatabaseHelper _db) {
		super(_activity.getApplicationContext(), _textViewResourceId, _objects);
		mList = _objects;
		mContext = _activity;
		mDb = _db;
	}

	@Override
	public View getView(int _position, View _convertView, ViewGroup _parent) {
		if (_convertView == null) {
			Context c = getContext();
			LayoutInflater inflater = (LayoutInflater) c
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			_convertView = inflater.inflate(R.layout.mainlist_layout, null);
		}
		final Note element = mList.get(_position);
		if (element != null) {
			TextView v = (TextView) _convertView
					.findViewById(R.id.textView_title);
			v.setText(element.getTitle());

			ImageView image = (ImageView) _convertView
					.findViewById(R.id.imageView_delete);
			image.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Log.i(LOG_TAG, "try to delete " + element.getTitle());
					mDb.delete(element.getId());
					refill(mDb.getAllNotes());
				}

			});
		}
		return _convertView;
	}

	public void refill(List<Note> _list) {
		mList.clear();
		mList.addAll(_list);
		notifyDataSetChanged();
		Toast.makeText(getContext(), "Notiz wurde erfolgreich gelöscht", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void notifyDataSetChanged() {
		Log.i(LOG_TAG, "Data set changed");
		super.notifyDataSetChanged();
	}
}
