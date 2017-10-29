/*
 *This is the Main Activity of my app, the main page will be made here.
 */

package com.example.testing;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //four buttons on the main page
    private Button button_search_by_university;

    private Button find_more_of_language;

    private Button more_about_ulpa;

    private Button faq;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        //set click response for every button
        button_search_by_university = (Button) findViewById(R.id.button1);

        button_search_by_university.setOnClickListener(new MainActivity.OnClickListenerImplone());

        find_more_of_language=(Button) findViewById(R.id.button2);

        find_more_of_language.setOnClickListener(new MainActivity.OnClickListenerImpltwo());

        more_about_ulpa=(Button) findViewById(R.id.but1);

        more_about_ulpa.setOnClickListener(new MainActivity.OnClickListenerImplthree());

        faq=(Button) findViewById(R.id.but2);

        faq.setOnClickListener(new MainActivity.OnClickListenerImplfour());

        //downloading the language detail used in another activity
        DownloadJSON_language downloading_sametime=new DownloadJSON_language();

        downloading_sametime.execute();
    }

    private class DownloadJSON_language extends AsyncTask<Void, Void, Void> {

        String raw_language_detail = new String();

        ArrayList<ArrayList<String>> all_language_detail_array = new ArrayList<>();

        @Override

        protected Void doInBackground(Void... params) {

            try {

                raw_language_detail = HtmlService.getHtml_states("http://10.0.2.2:3000/api/" +
                        "schools/allLanguageDetail/languageList/findall/all");

                all_language_detail_array = HtmlService.resolveJsonMessage_language_detail(raw_language_detail);

            } catch (Exception e) {

                Log.e("Error", e.getMessage());

            }

            return null;

        }

        @Override

        protected void onPostExecute(Void args) {

            temp_data_store.setLanguage_detail(all_language_detail_array);

        }

    }

    //click response method for the four buttons above
    private class OnClickListenerImplone implements View.OnClickListener {

        @Override

        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, search_main_page.class);

            startActivity(intent);

        }

    }

    private class OnClickListenerImpltwo implements View.OnClickListener {

        @Override

        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, language_detail.class);

            startActivity(intent);

        }

    }

    private class OnClickListenerImplthree implements View.OnClickListener {

        @Override

        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, more_about_ulpa.class);

            startActivity(intent);

        }

    }

    private class OnClickListenerImplfour implements View.OnClickListener {

        @Override

        public void onClick(View v) {

            Intent intent = new Intent(MainActivity.this, faq.class);

            startActivity(intent);

        }

    }

    @Override

    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();

        }

    }
    /* if wanna the setting option available, remove this comment.
        @Override

        /*public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.main, menu);

            return true;
        }
    */

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;

        }

        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override

    //the items on action bar will be added here
    public boolean onNavigationItemSelected(MenuItem item) {

         int id = item.getItemId();

         if (id == R.id.nav_why) {

             Intent intent = new Intent(MainActivity.this, why_study_language.class);

             startActivity(intent);

        } else if (id == R.id.nav_where) {

             Intent intent = new Intent(MainActivity.this, where_to_study.class);

             startActivity(intent);

        } else if (id == R.id.nav_can) {

             Intent intent = new Intent(MainActivity.this, can_i_study.class);

             startActivity(intent);

        } else if (id == R.id.nav_have) {

             Intent intent = new Intent(MainActivity.this, contact.class);

             startActivity(intent);

             //below are the funding information
        } else if (id == R.id.nav_z) {

             Uri uri = Uri.parse("http://www.australia.gov.au/");

             Intent it = new Intent(Intent.ACTION_VIEW, uri);

             startActivity(it);

        }else if (id == R.id.nav_a) {

             Uri uri = Uri.parse("http://www.olt.gov.au/");

             Intent it = new Intent(Intent.ACTION_VIEW, uri);

             startActivity(it);

        }else if (id == R.id.nav_b) {

             Uri uri = Uri.parse("http://www.lcnau.org/");

             Intent it = new Intent(Intent.ACTION_VIEW, uri);

             startActivity(it);

        }else if (id == R.id.nav_c) {

             Uri uri = Uri.parse("http://www.dynamicsoflanguage.edu.au/");

             Intent it = new Intent(Intent.ACTION_VIEW, uri);

             startActivity(it);

        }else if (id == R.id.nav_d) {

             Uri uri = Uri.parse("http://www.anu.edu.au/");

             Intent it = new Intent(Intent.ACTION_VIEW, uri);

             startActivity(it);

        }else if (id == R.id.nav_e) {

             Uri uri = Uri.parse("http://www.mq.edu.au/");

             Intent it = new Intent(Intent.ACTION_VIEW, uri);

             startActivity(it);

        }else if (id == R.id.nav_f) {

             Uri uri = Uri.parse("http://www.unimelb.edu.au/");

             Intent it = new Intent(Intent.ACTION_VIEW, uri);

             startActivity(it);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
