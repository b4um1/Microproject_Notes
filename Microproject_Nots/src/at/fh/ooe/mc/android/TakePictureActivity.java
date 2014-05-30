package at.fh.ooe.mc.android;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Build;
import at.fh.ooe.mc.androi.R;
import at.fh.ooe.mc.androi.R.id;
import at.fh.ooe.mc.androi.R.layout;

public class TakePictureActivity extends Activity implements OnClickListener,
		SurfaceHolder.Callback, Camera.PictureCallback, Camera.ShutterCallback {

	private static final String LOG_TAG = "take picture";

	Camera mCam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_take_picture);

		ImageView imageViewTakePhoto = (ImageView) findViewById(R.id.imageViewTakePhoto);
		imageViewTakePhoto.setOnClickListener(this);

		SurfaceView surface = (SurfaceView) findViewById(R.id.surfaceView);

		SurfaceHolder surfaceHolder = surface.getHolder();

		surfaceHolder.addCallback(this);
	}

	/*
	 * based on Juliet Kemp (How to Call the Camera in Android)
	 */
	public static void storeImage(byte[] _data, String _dirName) {
		File storageFile = null;

		File dir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				_dirName);
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				Log.e(LOG_TAG, "Failed to create storage directory.");
				return;
			}
		}
		String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss",
				Locale.getDefault()).format(new Date());
		storageFile = new File(dir.getPath() + File.separator + "IMG_"
				+ timeStamp + ".jpg");
				
		try {
			FileOutputStream fos = new FileOutputStream(storageFile);
			fos.write(_data);
			fos.close();
			Log.i(LOG_TAG, "stored file ..." + storageFile);
		} catch (FileNotFoundException _e) {
			Log.e(LOG_TAG, "File not found: " + storageFile, _e);
		} catch (IOException _e) {
			Log.e(LOG_TAG, "I/O error writing file: " + storageFile, _e);
		}
	}

	/*
	 * Source Google
	 */
	public static void setCameraDisplayOrientation(Activity activity,
			int cameraId, android.hardware.Camera camera) {
		android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
		android.hardware.Camera.getCameraInfo(cameraId, info);
		int rotation = activity.getWindowManager().getDefaultDisplay()
				.getRotation();
		int degrees = 0;
		switch (rotation) {
		case Surface.ROTATION_0:
			degrees = 0;
			break;
		case Surface.ROTATION_90:
			degrees = 90;
			break;
		case Surface.ROTATION_180:
			degrees = 180;
			break;
		case Surface.ROTATION_270:
			degrees = 270;
			break;
		}

		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360; // compensate the mirror
		} else { // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}
		camera.setDisplayOrientation(result);
	}

	@Override
	public void onShutter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPictureTaken(byte[] _data, Camera _camera) {
		if (_data != null) {
			storeImage(_data, "notes");
			mCam.startPreview();
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder _holder) {
		try {
			mCam = Camera.open(0);
			TakePictureActivity.setCameraDisplayOrientation(
					TakePictureActivity.this, 1, mCam);
			mCam.setPreviewDisplay(_holder);
			mCam.startPreview();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		mCam.stopPreview();
		mCam.release();

	}

	@Override
	public void onClick(View _v) {
		mCam.takePicture(this, this, this);
		finish();
	}
}
