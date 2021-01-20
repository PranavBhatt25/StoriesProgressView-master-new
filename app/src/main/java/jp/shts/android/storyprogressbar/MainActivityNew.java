package jp.shts.android.storyprogressbar;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import jp.shts.android.storiesprogressview.StoriesProgressView;

public class MainActivityNew extends AppCompatActivity {

    Context context;
    WrapContentHeightViewPager mPager;
    ProgressBar slider_progress;
    RelativeLayout main_drawer;
    StoriesProgressView storiesProgressView;

    final int NUM_PAGES = 6;
    private int currentPage2 = 0;
    private final int[] resources = new int[]{
            R.drawable.sample1,
            R.drawable.sample2,
            R.drawable.sample3,
            R.drawable.sample4,
            R.drawable.sample5,
            R.drawable.sample6,
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_new);
        context = this;

        mPager = findViewById(R.id.pager);
        storiesProgressView = (StoriesProgressView) findViewById(R.id.story_progress_view);
        slider_progress = findViewById(R.id.slider_progress);
        main_drawer = findViewById(R.id.main_drawer);

        try {
            storiesProgressView.setStoriesCount(NUM_PAGES);
            storiesProgressView.setStoryDuration(3000L);
            storiesProgressView.startStories();
            storiesProgressView.setStoriesListener(new StoriesProgressView.StoriesListener() {

                @Override
                public void onNext() {
                    if (NUM_PAGES == 1) {
                        currentPage2 = 0;
                        storiesProgressView.setStoriesCount(NUM_PAGES);
                        storiesProgressView.setStoryDuration(3000L);
                        storiesProgressView.startStories();
                        if (mPager != null) {
                            mPager.setCurrentItem(0);
                        }
                    } else {
                        currentPage2 = currentPage2 + 1;
                        mPager.setCurrentItem(currentPage2, true);
                    }
                }

                @Override
                public void onPrev() {
                    currentPage2 = currentPage2 - 1;
                    mPager.setCurrentItem(currentPage2--, true);
                }

                @Override
                public void onComplete() {
                    storiesProgressView.destroy();
                    currentPage2 = currentPage2 + 1;
                    if (currentPage2 == NUM_PAGES) {
                        currentPage2 = 0;
                        storiesProgressView.setStoriesCount(NUM_PAGES);
                        storiesProgressView.setStoryDuration(3000L);
                        storiesProgressView.startStories();
                        if (mPager != null) {
                            mPager.setCurrentItem(0);
                        }
                    }

//                        holder.storiesProgressView.skip();
//                        holder.storiesProgressView.resetPivot();
//                        holder.storiesProgressView.reverse();
//                        holder.storiesProgressView.destroy();
                }
            });

            mPager.addOnPageChangeListener(new WrapContentHeightViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
//                    int previousPos = position;
//                    int mPos = mPager.getCurrentItem();
//                    if (previousPos > mPos) {
//                        currentPage2 = mPos;
//                        storiesProgressView.reverse();
//                    } else {
//                        currentPage2 = mPos;
//                        storiesProgressView.skip();
//                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

            SlidingImage_Adapter2 adapter = new SlidingImage_Adapter2(context, resources);
            mPager.setAdapter(adapter);

        } catch (
                Exception e) {
            e.getStackTrace();
        }
    }
}
