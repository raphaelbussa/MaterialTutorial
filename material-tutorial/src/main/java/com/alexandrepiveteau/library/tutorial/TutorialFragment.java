package com.alexandrepiveteau.library.tutorial;


import android.app.PendingIntent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class TutorialFragment extends Fragment implements CustomAction{

    public static class Builder {

        private int mImageResource;
        private int mImageResourceBackground;
        private int mImageResourceForeground;

        private boolean mIsImageResourceAnimated;
        private boolean mIsImageResourceBackgroundAnimated;
        private boolean mIsImageResourceForegroundAnimated;

        private String mTitle;
        private String mDescription;

        private int mTitleGravity;
        private int mDescriptionGravity;

        private CustomAction mCustomAction;

        public Builder() {
            mImageResource = NO_IMAGE;
            mImageResourceBackground = NO_IMAGE;
            mImageResourceForeground = NO_IMAGE;

            mIsImageResourceAnimated = false;
            mIsImageResourceBackgroundAnimated = false;
            mIsImageResourceForegroundAnimated = false;

            mTitleGravity = Gravity.CENTER_VERTICAL;
            mDescriptionGravity = Gravity.CENTER_VERTICAL;

            mCustomAction = new CustomAction.Builder(null).build();
        }

        public Builder setImageResource(int imageResource) {
            mImageResource = imageResource;
            mIsImageResourceAnimated = false;
            return this;
        }

        public Builder setImageResource(int imageResource, boolean isAnimated) {
            mImageResource = imageResource;
            mIsImageResourceAnimated = isAnimated;
            return this;
        }

        public Builder setImageResourceBackground(int imageResource) {
            mImageResourceBackground = imageResource;
            mIsImageResourceBackgroundAnimated = false;
            return this;
        }

        public Builder setImageResourceBackground(int imageResource, boolean isAnimated) {
            mImageResourceBackground = imageResource;
            mIsImageResourceBackgroundAnimated = isAnimated;
            return this;
        }

        public Builder setImageResourceForeground(int imageResource) {
            mImageResourceForeground = imageResource;
            mIsImageResourceForegroundAnimated = false;
            return this;
        }

        public Builder setImageResourceForeground(int imageResource, boolean isAnimated) {
            mImageResourceForeground = imageResource;
            mIsImageResourceForegroundAnimated = isAnimated;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setDescription(String description) {
            mDescription = description;
            return this;
        }

        public Builder setCustomAction(CustomAction customAction) {
            mCustomAction = customAction;
            return this;
        }

        public Builder setTitleGravity(int titleGravity) {
            mTitleGravity = titleGravity;
            return this;
        }

        public Builder setDescriptionGravity(int descriptionGravity) {
            mDescriptionGravity = descriptionGravity;
            return this;
        }

        public TutorialFragment build() {
            return TutorialFragment.getInstance(mTitle, mDescription, mImageResource, mImageResourceBackground, mImageResourceForeground, mIsImageResourceAnimated, mIsImageResourceBackgroundAnimated, mIsImageResourceForegroundAnimated, mCustomAction.getCustomActionIcon(), mCustomAction.getCustomActionPendingIntent(), mCustomAction.getCustomActionTitle(), mTitleGravity, mDescriptionGravity);
        }
    }

    public static final int NO_IMAGE = -1;

    private static final String ARGUMENTS_HAS_ANIMATED_IMAGE_BACKGROUND = "ARGUMENTS_HAS_ANIMATED_IMAGE_BACKGROUND";
    private static final String ARGUMENTS_HAS_ANIMATED_IMAGE_FOREGROUND = "ARGUMENTS_HAS_ANIMATED_IMAGE_FOREGROUND";
    private static final String ARGUMENTS_HAS_ANIMATED_IMAGE = "ARGUMENTS_HAS_ANIMATED_IMAGE";

    private static final String ARGUMENTS_TUTORIAL_IMAGE_BACKGROUND = "ARGUMENTS_TUTORIAL_NAME_IMAGE_BACKGROUND";
    private static final String ARGUMENTS_TUTORIAL_IMAGE_FOREGROUND = "ARGUMENTS_TUTORIAL_NAME_IMAGE_FOREGROUND";
    private static final String ARGUMENTS_TUTORIAL_IMAGE = "ARGUMENTS_TUTORIAL_NAME_IMAGE";
    private static final String ARGUMENTS_TUTORIAL_NAME = "ARGUMENTS_TUTORIAL_NAME";
    private static final String ARGUMENTS_TUTORIAL_DESCRIPTION = "ARGUMENTS_TUTORIAL_NAME_DESCRIPTION";
    private static final String ARGUMENTS_TUTORIAL_NAME_GRAVITY = "ARGUMENTS_TUTORIAL_NAME_GRAVITY";
    private static final String ARGUMENTS_TUTORIAL_DESCRIPTION_GRAVITY = "ARGUMENTS_TUTORIAL_DESCRIPTION_GRAVITY";

    private static final String ARGUMENTS_CUSTOM_ACTION_ICON = "ARGUMENTS_CUSTOM_ACTION_ICON";
    private static final String ARGUMENTS_CUSTOM_ACTION_TITLE = "ARGUMENTS_CUSTOM_ACTIION_TITLE";
    private static final String ARGUMENTS_CUSTOM_ACTION_PENDING_INTENT = "ARGUMENTS_CUSTOM_ACTION_PENDING_INTENT";

    private static TutorialFragment getInstance(String name, String description, int imageResource, int imageResourceBackground, int imageResourceForeground, boolean hasAnimatedImageResource, boolean hasAnimatedImageResourceBackground, boolean hasAnimatedImageResourceForeground, int customActionIcon, PendingIntent pendingIntent, String customActionTitle, int titleGravity, int descriptionGravity) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENTS_TUTORIAL_IMAGE, imageResource);
        bundle.putInt(ARGUMENTS_TUTORIAL_IMAGE_BACKGROUND, imageResourceBackground);
        bundle.putInt(ARGUMENTS_TUTORIAL_IMAGE_FOREGROUND, imageResourceForeground);
        bundle.putString(ARGUMENTS_TUTORIAL_NAME, name);
        bundle.putString(ARGUMENTS_TUTORIAL_DESCRIPTION, description);
        bundle.putBoolean(ARGUMENTS_HAS_ANIMATED_IMAGE, hasAnimatedImageResource);
        bundle.putBoolean(ARGUMENTS_HAS_ANIMATED_IMAGE_BACKGROUND, hasAnimatedImageResourceBackground);
        bundle.putBoolean(ARGUMENTS_HAS_ANIMATED_IMAGE_FOREGROUND, hasAnimatedImageResourceForeground);
        bundle.putInt(ARGUMENTS_CUSTOM_ACTION_ICON, customActionIcon);
        bundle.putParcelable(ARGUMENTS_CUSTOM_ACTION_PENDING_INTENT, pendingIntent);
        bundle.putString(ARGUMENTS_CUSTOM_ACTION_TITLE, customActionTitle);

        bundle.putInt(ARGUMENTS_TUTORIAL_NAME_GRAVITY, titleGravity);
        bundle.putInt(ARGUMENTS_TUTORIAL_DESCRIPTION_GRAVITY, descriptionGravity);

        TutorialFragment tutorialFragment = new TutorialFragment();
        tutorialFragment.setArguments(bundle);
        return tutorialFragment;
    }

    public static ViewPager.PageTransformer getParallaxPageTransformer() {
        ParallaxPagerTransformer parallaxPagerTransformer = new ParallaxPagerTransformer();
        parallaxPagerTransformer.addViewToParallax(new ParallaxPagerTransformer.ParallaxTransformInformation(R.id.tutorial_image, 0, 0));
        parallaxPagerTransformer.addViewToParallax(new ParallaxPagerTransformer.ParallaxTransformInformation(R.id.tutorial_image_background, 10f, 10f));
        parallaxPagerTransformer.addViewToParallax(new ParallaxPagerTransformer.ParallaxTransformInformation(R.id.tutorial_image_foreground, -10f, -10f));
        return parallaxPagerTransformer;
    }

    public static ViewPager.PageTransformer getParallaxPageTransformer(float parallaxStrength) {
        ParallaxPagerTransformer parallaxPagerTransformer = new ParallaxPagerTransformer();
        parallaxPagerTransformer.addViewToParallax(new ParallaxPagerTransformer.ParallaxTransformInformation(R.id.tutorial_image, 0, 0));
        parallaxPagerTransformer.addViewToParallax(new ParallaxPagerTransformer.ParallaxTransformInformation(R.id.tutorial_image_background, 10f/parallaxStrength, 10f/parallaxStrength));
        parallaxPagerTransformer.addViewToParallax(new ParallaxPagerTransformer.ParallaxTransformInformation(R.id.tutorial_image_foreground, -10f/parallaxStrength, -10f/parallaxStrength));
        return parallaxPagerTransformer;
    }

    private int mTutorialImage;
    private int mTutorialImageBackground;
    private int mTutorialImageForeground;
    private String mTutorialName;
    private String mTutorialDescription;

    private boolean mHasAnimatedImage;
    private boolean mHasAnimatedImageBackground;
    private boolean mHasAnimatedImageForeground;

    private ImageView mTutorialImageImageView;
    private ImageView mTutorialImageImageViewBackground;
    private ImageView mTutorialImageImageViewForeground;

    /*
     * Implemented methods for the CustomAction
     */

    @Override
    public PendingIntent getCustomActionPendingIntent() {
        return getArguments().getParcelable(ARGUMENTS_CUSTOM_ACTION_PENDING_INTENT);
    }

    @Override
    public int getCustomActionIcon() {
        return getArguments().getInt(ARGUMENTS_CUSTOM_ACTION_ICON);
    }

    @Override
    public String getCustomActionTitle() {
        return getArguments().getString(ARGUMENTS_CUSTOM_ACTION_TITLE);
    }

    @Override
    public boolean isEnabled() {
        return getArguments().getParcelable(ARGUMENTS_CUSTOM_ACTION_PENDING_INTENT) != null;
    }

    @Override
    public boolean hasCustomIcon() {
        return getArguments().getInt(ARGUMENTS_CUSTOM_ACTION_ICON) != NO_IMAGE;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle arguments = getArguments();

        mTutorialImage = arguments.getInt(ARGUMENTS_TUTORIAL_IMAGE);
        mTutorialImageBackground = arguments.getInt(ARGUMENTS_TUTORIAL_IMAGE_BACKGROUND);
        mTutorialImageForeground = arguments.getInt(ARGUMENTS_TUTORIAL_IMAGE_FOREGROUND);

        mTutorialName = arguments.getString(ARGUMENTS_TUTORIAL_NAME);
        mTutorialDescription = arguments.getString(ARGUMENTS_TUTORIAL_DESCRIPTION);

        mHasAnimatedImage = arguments.getBoolean(ARGUMENTS_HAS_ANIMATED_IMAGE);
        mHasAnimatedImageBackground = arguments.getBoolean(ARGUMENTS_HAS_ANIMATED_IMAGE_BACKGROUND);
        mHasAnimatedImageForeground = arguments.getBoolean(ARGUMENTS_HAS_ANIMATED_IMAGE_FOREGROUND);

        View rootView = inflater.inflate(R.layout.fragment_tutorial, container, false);

        mTutorialImageImageView = (ImageView) rootView.findViewById(R.id.tutorial_image);
        mTutorialImageImageViewBackground = (ImageView) rootView.findViewById(R.id.tutorial_image_background);
        mTutorialImageImageViewForeground = (ImageView) rootView.findViewById(R.id.tutorial_image_foreground);

        TextView mTutorialNameTextView = (TextView) rootView.findViewById(R.id.tutorial_name);
        TextView mTutorialDescriptionTextView = (TextView) rootView.findViewById(R.id.tutorial_description);

        mTutorialNameTextView.setGravity(Gravity.CENTER_HORIZONTAL|arguments.getInt(ARGUMENTS_TUTORIAL_NAME_GRAVITY));
        mTutorialDescriptionTextView.setGravity(Gravity.CENTER_HORIZONTAL|arguments.getInt(ARGUMENTS_TUTORIAL_DESCRIPTION_GRAVITY));

        if(mTutorialImage != NO_IMAGE) {
            if (!mHasAnimatedImage) {
                Picasso.with(getActivity()).load(mTutorialImage).into(mTutorialImageImageView);
            } else {
                mTutorialImageImageView.setImageResource(mTutorialImage);
                AnimationDrawable animationDrawable = (AnimationDrawable) mTutorialImageImageView.getDrawable();
                animationDrawable.start();
            }
        }
        if(mTutorialImageBackground != NO_IMAGE) {
            if(!mHasAnimatedImageBackground) {
                Picasso.with(getActivity()).load(mTutorialImageBackground).into(mTutorialImageImageViewBackground);
            } else {
                mTutorialImageImageViewBackground.setImageResource(mTutorialImageBackground);
                AnimationDrawable animationDrawable = (AnimationDrawable) mTutorialImageImageViewBackground.getDrawable();
                animationDrawable.start();
            }
        }
        if(mTutorialImageForeground != NO_IMAGE) {
            if(!mHasAnimatedImageForeground) {
                Picasso.with(getActivity()).load(mTutorialImageForeground).into(mTutorialImageImageViewForeground);
            } else {
                mTutorialImageImageViewForeground.setImageResource(mTutorialImageForeground);
                AnimationDrawable animationDrawable = (AnimationDrawable) mTutorialImageImageViewForeground.getDrawable();
                animationDrawable.start();
            }
        }


        mTutorialNameTextView.setText(Html.fromHtml(mTutorialName));
        mTutorialDescriptionTextView.setText(Html.fromHtml(mTutorialDescription));

        return rootView;
    }
}
