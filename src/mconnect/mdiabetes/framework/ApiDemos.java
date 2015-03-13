package mconnect.mdiabetes.framework;


import mconnect.mdiabetes.framework.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;


public class ApiDemos extends Activity implements
        AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView mPhotosList;
    private ViewGroup mContainer;
    private ImageView mImageView;

    
    /*private static final String[] PHOTOS_NAMES = new String[] {
            "Record",
            "Reminder",
            "Education",
            "Setting",
            "Version Information",
            "Contact us"
    };*/
    
    private static final String[] PHOTOS_NAMES = new String[] {
        "记录",
        "提醒",
        "教育",
        "设置",
        "版本信息",
        "联系我们"
};

    
    private static final int[] PHOTOS_RESOURCES = new int[] {
            R.drawable.photo1,
            R.drawable.photo2,
            R.drawable.photo3,
            R.drawable.photo4,
            R.drawable.photo5,
            R.drawable.photo6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.animations_main_screen);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.i_title);
        TextView title = (TextView) findViewById(R.id.title);
        Button leftBtn = (Button) findViewById(R.id.title_left_btn);
        Button rightBtn = (Button) findViewById(R.id.title_right_btn);
        //title.setText("糖尿病信息");
        leftBtn.setVisibility(View.GONE);
        rightBtn.setText("返回");
        rightBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });

        mPhotosList = (ListView) findViewById(R.id.listdemo);
        mImageView = (ImageView) findViewById(R.id.picture);
        mContainer = (ViewGroup) findViewById(R.id.container);

        
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, PHOTOS_NAMES);
      
        mPhotosList.setAdapter(adapter);
        mPhotosList.setOnItemClickListener(this);

        
        mImageView.setClickable(true);
        mImageView.setFocusable(true);
        mImageView.setOnClickListener(this);

        
        mContainer.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
    }

   
    private void applyRotation(int position, float start, float end) {
        // Find the center of the container
        final float centerX = mContainer.getWidth() / 2.0f;
        final float centerY = mContainer.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(position));

        mContainer.startAnimation(rotation);
    }

    public void onItemClick(AdapterView parent, View v, int position, long id) {
        // Pre-load the image then start the animation
        mImageView.setImageResource(PHOTOS_RESOURCES[position]);
        applyRotation(position, 0, 90);
    }

    public void onClick(View v) {
        applyRotation(-1, 180, 90);
    }

    
    private final class DisplayNextView implements Animation.AnimationListener {
        private final int mPosition;

        private DisplayNextView(int position) {
            mPosition = position;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            mContainer.post(new SwapViews(mPosition));
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /**
     * This class is responsible for swapping the views and start the second
     * half of the animation.
     */
    private final class SwapViews implements Runnable {
        private final int mPosition;

        public SwapViews(int position) {
            mPosition = position;
        }

        public void run() {
            final float centerX = mContainer.getWidth() / 2.0f;
            final float centerY = mContainer.getHeight() / 2.0f;
            Rotate3dAnimation rotation;
            
            if (mPosition > -1) {
                mPhotosList.setVisibility(View.GONE);
                mImageView.setVisibility(View.VISIBLE);
                mImageView.requestFocus();

                rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 310.0f, false);
            } else {
                mImageView.setVisibility(View.GONE);
                mPhotosList.setVisibility(View.VISIBLE);
                mPhotosList.requestFocus();

                rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
            }

            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());

            mContainer.startAnimation(rotation);
        }
    }

}
