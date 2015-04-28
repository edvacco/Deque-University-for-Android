package com.dequesystems.accessibility101;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import com.dequesystems.accessibility101.contentdescriptions.ContDescAboutFragment;
import com.dequesystems.accessibility101.contentdescriptions.ContDescBrokenFragment;
import com.dequesystems.accessibility101.introduction.AboutDequeFragment;
import com.dequesystems.accessibility101.introduction.AppIntroductionFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chrismcmeeking on 4/24/15.
 */
public class StoryManager {

    private static final String LOG_TAG = StoryManager.class.getSimpleName();

    List<Story> mStories;

    MainActivity mActivity;

    private Story mActiveStory = null;

    StoryManager(MainActivity activity) {
        mActivity = activity;

        ArrayList<Story> tempList = new ArrayList<>();

        Story tempStory = new Story(mActivity.getString(R.string.intro_title));
        tempStory.addTab(mActivity.getString(R.string.intro_tab_1), new AppIntroductionFragment());
        tempStory.addTab(mActivity.getString(R.string.intro_tab_2), new AboutDequeFragment());
        tempList.add(tempStory);

        tempStory = new Story(mActivity.getString(R.string.labels_title));
        tempStory.addTab(mActivity.getString(R.string.story_about), LabelsAboutFragment.newInstance("Blarg", "Blargety"));
        tempStory.addTab(mActivity.getString(R.string.story_broken), LabelsBrokenFragment.newInstance("Blarg", "Blargety"));
        tempList.add(tempStory);

        tempStory = new Story(mActivity.getString(R.string.cont_desc_title));
        tempStory.addTab(mActivity.getString(R.string.story_about), ContDescAboutFragment.newInstance("Blarg", "Blargety"));
        tempStory.addTab(mActivity.getString(R.string.story_broken), ContDescBrokenFragment.newInstance("Blarg", "BLBLBLB"));
        tempList.add(tempStory);

        mStories = tempList;
    }

    public void setActiveStory(int index, TabHost tabHost) {
        mStories.get(index).makeActiveStory(tabHost);
    }

    public Story getActiveStory() {
        return mActiveStory;
    }

    Iterator<Story> getStoryIterator() {
        return mStories.iterator();
    }

    Story getStory(int index) {
        return mStories.get(index);
    }

    class Story {
        static final String TAB_ID = "TAB_ID_";

        final String mTitle;

        ArrayList<Tab> mTabs;

        int mTabOrderCounter = 0;

        private Story (String title) {
            mTitle = title;
            mTabs = new ArrayList<>();
        }

        private void addTab(String tabTitle, Fragment content) {
            mTabs.add(new Tab(tabTitle, content, mTabOrderCounter++));
        }

        private void makeActiveStory(TabHost tabHost) {
            tabHost.clearAllTabs();

            for (Iterator it = this.getTabIterator(); it.hasNext(); ) {
                Story.Tab tab = (Story.Tab)it.next();

                TabHost.TabSpec tabSpec = tabHost.newTabSpec(tab.getTabID());
                tabSpec.setContent(tab);
                tabSpec.setIndicator(tab.getTitle());
                tabHost.addTab(tabSpec);
            }

            StoryManager.this.mActiveStory = this;
        }

        String getTitle() { return mTitle;}

        Tab getTabByTitle(String tabTitle) {

            for (Iterator<Tab> it = mTabs.iterator(); it.hasNext(); ) {
                Tab tab = it.next();
                if (tabTitle == tab.mTitle) {
                    return tab;
                }
            }

            return null;
        }

        Tab getTabByID(String tabId) {

            for (Iterator<Tab> it = mTabs.iterator(); it.hasNext(); ) {
                Tab tab = it.next();
                if (tabId == tab.getTabID()) {
                    return tab;
                }
            }

            return null;
        }

        Iterator<Tab> getTabIterator() {
            return mTabs.iterator();
        }

        class Tab implements  TabHost.TabContentFactory{
            private final String mTitle;

            private final String mTabID;

            private Fragment mFragment;

            private Tab(String title, Fragment fragment, int order) {
                mFragment = fragment;
                mTitle = title;
                mTabID = TAB_ID + order;
            }

            public String getTitle() { return mTitle;}

            public Fragment getFragment() { return mFragment;}

            public String getTabID() { return mTabID;}

            @Override
            public View createTabContent(String tag) {
                final int viewId = Math.abs(mTabID.hashCode());

                View view = new RelativeLayout(mActivity);
                view.setId(viewId);

                FragmentTransaction fragmentTransaction = mActivity.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(viewId, mFragment);
                fragmentTransaction.commit();

                return view;
            }
        }
    }
}
