package com.example.adamjk.onboardjccc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    final String EXTRA_MESSAGE = "Position selected";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));



    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragment
        android.support.v4.app.FragmentManager fragmentManager = null;
      Class activityName = MainActivity.class;

        switch (position) {
            case 0:
                fragmentManager = getSupportFragmentManager();
                break;
            case 1:
                fragmentManager = getSupportFragmentManager();
                break;
            case 2:
                fragmentManager = getSupportFragmentManager();
                break;
            case 3:
                fragmentManager = getSupportFragmentManager();
                break;
            case 4:
                fragmentManager = getSupportFragmentManager();
                break;
            case 5:
                fragmentManager = getSupportFragmentManager();
                break;
            case 6:
                fragmentManager = getSupportFragmentManager();
                break;
            case 7:
                activityName = LoginActivity.class;
                fragmentManager = null;
                break;
            case 8:
                fragmentManager = getSupportFragmentManager();
                break;
            case 9:
                fragmentManager = getSupportFragmentManager();
                break;
            case 10:
                fragmentManager = getSupportFragmentManager();
                break;
            case 11:
                activityName = JcccQuizActivity.class;
               fragmentManager = null;

                break;

        }

        if(fragmentManager != null)
        {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
        }
        else
        {

            this.startActivity(new Intent(this, activityName.getClass()));
        }


    }

    public void onSectionAttached(int number) {

        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;
            case 7:
                mTitle = getString(R.string.title_section7);
                break;
            case 8:
                mTitle = getString(R.string.title_section8);
                break;
            case 9:
                mTitle = getString(R.string.title_section9);
                break;
            case 10:
                mTitle = getString(R.string.title_section10);
                break;
            case 11:
                mTitle = getString(R.string.title_section11);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
/*
            //add submenu
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.main, menu);
            MenuItem menuItem1 = menu.findItem(R.id.action_application).getSubMenu().findItem(R.id.action_application).getSubMenu().add(Menu.NONE, 1, Menu.NONE, "Apply for Admission");
            MenuItem menuItem2 = menu.findItem(R.id.action_application).getSubMenu().findItem(R.id.action_application).getSubMenu().add(Menu.NONE, 2, Menu.NONE, "Selective Programs");
            */
        }
        return super.onCreateOptionsMenu(menu);


    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
   public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
*/
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            WebView myWebView = (WebView)rootView.findViewById(R.id.webview);
            myWebView.setWebChromeClient(new WebChromeClient());
            myWebView.getSettings().setJavaScriptEnabled(true);


            int i = getArguments().getInt(ARG_SECTION_NUMBER);
            String webUrl = "http://www.jccc.edu/";
            switch(i){
                case 1:
                    //About JCCC
                    webUrl= "file:///android_asset/about.html";
                    break;
                case 2:
                    //Campus Visit
                    webUrl= "file:///android_asset/campusVisit.html";

                    break;
                case 3:
                    //Campus Map
                    webUrl= "file:///android_asset/campusMap.html";
                    break;
                case 4:
                    //Areas of Study and Degrees
                    webUrl= "file:///android_asset/programs_and_degrees.html";
                    break;
                case 5:
                    //Meet a Counselor
                    webUrl= "file:///android_asset/counseling.html";
                    break;
                case 6:
                    //Admission
                    webUrl= "file:///android_asset/paperworkMenu.html";
                    break;
                case 7:
                    //Login
                    webUrl= null;
                    myWebView = null;
                    rootView = inflater.inflate(R.layout.activity_login, container, false);
                    break;
                case 8:
                    //Twitter
                    webUrl="https://twitter.com/successcenterjc";
                    break;
                case 9:
                    //Faceboo
                    webUrl="https://www.facebook.com/JCCC411";
                    break;
                case 10:
                    //Emergency
                    webUrl= "file:///android_asset/emergency.html";
                    break;
                case 11:
                    //QUIZ!
                    webUrl = null;
                    myWebView = null;
                    rootView = inflater.inflate(R.layout.activity_quiz, container, false);
                    break;


            }

            if(myWebView != null)
            {
                myWebView.loadUrl(webUrl);
            }



            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }


    }

}
