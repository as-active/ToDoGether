package com.as_active.todogether;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AddTDList mAddListTask;

    private View mProgressView;
    private ListView mAllToDoListView;


    String[] listContent = {"Oans", "Zwoa", "Gsuffa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mAllToDoListView = (ListView)findViewById(R.id.AllToDoListView);
        mAllToDoListView.setAdapter(new ArrayAdapter<String>(this,R.layout.list_main,R.id.url,listContent));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start a Background Activity to add a new TD List Item
                attemptAddTDList();

            }
        });

        mProgressView = findViewById(R.id.AddList_progress);
    }

    public void attemptAddTDList() {
        if (mAddListTask != null) {
            return;
        }

        showProgress(true);
        mAddListTask = new AddTDList(getApplicationContext());
        mAddListTask.execute((Void) null);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mAllToDoListView.setVisibility(show ? View.GONE : View.VISIBLE);
            mAllToDoListView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mAllToDoListView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mAllToDoListView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class AddTDList extends AsyncTask<Void, Void, Boolean> {

        private Context context;

        AddTDList(Context context) {
            this.context = context;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //TODO Read Data DB an show in ListView

            int lastC = (listContent.length - 1);
            listContent[lastC] = "Oida!";

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAddListTask = null;
            showProgress(false);
        }

        @Override
        protected void onCancelled() {
            mAddListTask = null;
            showProgress(false);
        }
    }

}
