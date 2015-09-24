package pt.ei2015.ementasipleiria;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SiteBody.AsyncResponse, PresenterDia.OnFragmentInteractionListener{

    public static final String PREF_FILE_NAME = "ementasIPL_file";

    public  static final String USER_CHOOSE_CAMPUS = "userChooseCampus";
    public  static final String USER_CHOOSE_CANTEEN= "userChooseCanteen";
    PresenterDia segunda = new PresenterDia();
    PresenterDia terca = new PresenterDia();
    PresenterDia quarta = new PresenterDia();
    PresenterDia quinta = new PresenterDia();
    PresenterDia sexta = new PresenterDia();

    TextView textView;
    String website = "http://ementaipleiria.uhostall.com/";
    List<Dia> dias = new ArrayList<>();
    MainActivity p = this;
    Boolean completo;
    NavigationDrawerFragment navigationDrawerFragment;
    Spinner mSpinner;
    String mCampusSpinner;
    boolean isSopa=false;


    private FragmentActivity myContext;
    private ViewPager mPager;
    private SlidingTabLayout mTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar  = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        toolbar.setTitle(null);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // textView = (TextView) findViewById(R.id.tt);
        navigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navigationDrawerFragment.setUp(this, R.id.fragment_navigation_drawer, toolbar, (DrawerLayout) findViewById(R.id.drawer_layout));

        mSpinner = (Spinner) findViewById(R.id.toolbar_spinner);

        updateSpinner(Integer.parseInt(readfromPreferences(this, USER_CHOOSE_CAMPUS, 0 + "")));

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.custom_tab, 0);
        mTabs.setViewPager(mPager);


    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

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

    @Override
    public void processFinish(String output) {
        JSONArray a = null;
        dias.removeAll(dias);
//        textView.setText(" ");
        try {
            a = new JSONArray(output);
            for(int x = 0; x<a.length() ;x++) {
                //Log.e("segunda", a.getJSONObject(x).getString("almoco_sopa"));
                if(a.getJSONObject(x).getString("data")!=null){
                    Dia d = new Dia();
                    d.setDia(new SimpleDateFormat("yyyy-MM-dd").parse(a.getJSONObject(x).getString("data")));
                    if(completo){
                        //if (a.getJSONObject(x).getInt("temAlmoco") == 1) {
                            String vegetariano=null;
                            if(a.getJSONObject(x).has("almoco_vegetariano"))
                                vegetariano=a.getJSONObject(x).getString("almoco_vegetariano");

                            Refeicao ra = new Refeicao(a.getJSONObject(x).getString("almoco_sopa"), a.getJSONObject(x).getString("almoco_carne"), a.getJSONObject(x).getString("almoco_peixe"), a.getJSONObject(x).getString("almoco_sobremesa"),vegetariano);
                            Refeicao rj = new Refeicao(a.getJSONObject(x).getString("jantar_sopa"), a.getJSONObject(x).getString("jantar_carne"), a.getJSONObject(x).getString("jantar_peixe"), a.getJSONObject(x).getString("jantar_sobremesa"),null);
                            d.setAlmoco(ra);
                            d.setJantar(rj);
                        //}

                        if (a.getJSONObject(x).getInt("temJantar") == 1) {
                            Refeicao r = new Refeicao(a.getJSONObject(x).getString("jantar_sopa"), a.getJSONObject(x).getString("jantar_carne"), a.getJSONObject(x).getString("jantar_peixe"), a.getJSONObject(x).getString("jantar_sobremesa"),null);
                            d.setJantar(r);
                        }
                    }
                    else{
                        d.setPrato(a.getJSONObject(x).getString("refeicao"));
                    }
                    dias.add(d);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
       // segunda.dia="";
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        int pos =0;
        for( Dia x: dias) {
            Log.e("data",cal.getTime()+"");

            // dateFormat.format(x.getDia()).equals(dateFormat.format(cal.getTime()))
            cal.setTime(x.getDia());
            if (cal.get(Calendar.WEEK_OF_YEAR)== Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)){

              /*  if(completo)
                  //  textView.setText(textView.getText() + "\n\n" + x.dia.toString() + " - almoço: " + x.getAlmoco().getCarne() + " - " + x.getAlmoco().getPeixe() + " - " + x.getAlmoco().getVegetariano());
                segunda.setDia(segunda.dia+ "\n\n" + x.dia.toString() + " - almoço: " + x.getAlmoco().getCarne() + " - " + x.getAlmoco().getPeixe() + " - " + x.getAlmoco().getVegetariano());

                else
                   // textView.setText(textView.getText() + "\n\n" + x.dia.toString() + " - prato: " + x.getPrato());
                    segunda.setDia(segunda.dia+ "\n\n" + x.dia.toString() + " - prato: " + x.getPrato());*/
                switch (pos) {
                    case 0:
                        segunda.setDia(x,completo,isSopa);
                        break;

                    case 1:
                        terca.setDia(x,completo,isSopa);
                        break;

                    case 2:
                        quarta.setDia(x,completo,isSopa);
                        break;

                    case 3:
                        quinta.setDia(x,completo,isSopa);
                        break;

                    case 4:
                        sexta.setDia(x,completo,isSopa);
                        break;
                }
                pos++;
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    public class SpinnerAdapter extends BaseAdapter {
        private List<String> mItems = new ArrayList<>();

        public void clear() {
            mItems.clear();
        }

        public void addItem(String yourObject) {
            mItems.add(yourObject);
        }

        public void addItems(List<String> yourObjectList) {
            mItems.addAll(yourObjectList);
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getDropDownView(int position, View view, ViewGroup parent) {
            if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
                view = getLayoutInflater().inflate(R.layout.toolbar_spinner_item_dropdown, parent, false);
                view.setTag("DROPDOWN");
            }

            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(getTitle(position));

            return view;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
                view = getLayoutInflater().inflate(R.layout.
                        toolbar_spinner_item_actionbar, parent, false);
                view.setTag("NON_DROPDOWN");
            }
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            TextView t = (TextView) view.findViewById(android.R.id.text2);
            t.setText(mCampusSpinner);

            textView.setText(getTitle(position));
            return view;
        }

        private String getTitle(int position) {
            return position >= 0 && position < mItems.size() ? mItems.get(position) : "";
        }
    }
    public void updateSpinner(int opcao){


        List<String> campus1 = new LinkedList<>();
        List<String> campus2 = new LinkedList<>();
        List<String> campus3 = new LinkedList<>();
        List<String> campus4 = new LinkedList<>();
        List<String> sede = new LinkedList<>();



        campus1.add("Cantina 1");
        campus1.add("Restaurante");

        campus2.add("Cantina 2");
        campus2.add("Cantina 3");
        campus2.add("Restaurante");
        campus2.add("Snack-Bar");
        campus2.add("Bar");

        campus3.add("Cantina 4");

        campus4.add("Cantina 5");

        sede.add("Bar");

        SpinnerAdapter spinnerAdapter = new SpinnerAdapter();
        saveToPreferences(this,USER_CHOOSE_CAMPUS,opcao+"");
        if(opcao==0) {
            spinnerAdapter.addItems(campus1);
            mCampusSpinner="Campus 1 - ESSECS";

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    isSopa=false;
                    //Toast.makeText(p, "Teste: " + cantinas.get(position), Toast.LENGTH_SHORT).show();
                    SiteBody s = null;
                    HttpClient h = new DefaultHttpClient();
                    if (position == 0) {
                        s = new SiteBody(h, website + "cantina1campus1pt.php", null);
                        completo = true;
                    } else if (position == 1) {
                        s = new SiteBody(h, website + "restaurantecampus1pt.php", null);
                        completo = false;
                    }

                    s.setDelegate(p);
                    clearFragments();
                    s.execute();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        else if(opcao==1) {
            spinnerAdapter.addItems(campus2);
            mCampusSpinner= "Campus 2 - ESTG & ESSLEI";

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    isSopa=false;
                    //Toast.makeText(p, "Teste: " + cantinas.get(position), Toast.LENGTH_SHORT).show();
                    SiteBody s = null;
                    HttpClient h = new DefaultHttpClient();
                    if (position == 0) {
                        s = new SiteBody(h, website + "cantina2campus2pt.php", null);
                        completo = true;
                    } else if (position == 1) {
                        s = new SiteBody(h, website + "cantina3campus2pt.php", null);
                        completo = true;
                    } else if (position == 2) {
                        s = new SiteBody(h, website + "restaurantecampus2pt.php", null);
                        completo = false;
                    } else if (position == 3) {
                        s = new SiteBody(h, website + "snackcampus2pt.php", null);
                        completo = true;
                    } else if (position == 4) {
                        s = new SiteBody(h, website + "barcampus2pt.php", null);
                        isSopa=true;
                        completo = false;
                }

                    s.setDelegate(p);
                    clearFragments();
                    s.execute();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
        else if(opcao==2) {
            spinnerAdapter.addItems(campus3);
            mCampusSpinner="Campus 3 - ESAD.CR";

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    isSopa=false;
                    //Toast.makeText(p, "Teste: " + cantinas.get(position), Toast.LENGTH_SHORT).show();
                    SiteBody s = null;
                    HttpClient h = new DefaultHttpClient();
                        s = new SiteBody(h, website + "cantina4campus3pt.php", null);
                        completo = true;

                    s.setDelegate(p);
                    clearFragments();
                    s.execute();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        else if(opcao==3) {
            spinnerAdapter.addItems(campus4);
            mCampusSpinner="Campus 4 - ESTM";

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    isSopa=false;
                    //Toast.makeText(p, "Teste: " + cantinas.get(position), Toast.LENGTH_SHORT).show();
                    SiteBody s = null;
                    HttpClient h = new DefaultHttpClient();
                    s = new SiteBody(h, website + "cantina5campus4pt.php", null);
                    completo = true;

                    s.setDelegate(p);
                    clearFragments();
                    s.execute();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
        else if(opcao==4) {
            spinnerAdapter.addItems(sede);
            mCampusSpinner="Serviços Centrais";

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    isSopa=true;
                    //Toast.makeText(p, "Teste: " + cantinas.get(position), Toast.LENGTH_SHORT).show();
                    SiteBody s = null;
                    HttpClient h = new DefaultHttpClient();
                    s = new SiteBody(h, website + "barsedept.php", null);
                    completo = false;

                    s.setDelegate(p);
                    clearFragments();
                    s.execute();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }

        mSpinner.setAdapter(spinnerAdapter);

        Drawable spinnerDrawable = mSpinner.getBackground().getConstantState().newDrawable();

        spinnerDrawable.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mSpinner.setBackground(spinnerDrawable);
        }else{
            mSpinner.setBackgroundDrawable(spinnerDrawable);
        }

    }

    public static void saveToPreferences (Context context, String preferenceName, String preferenceValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readfromPreferences (Context context, String preferenceName, String defaultValue){
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName,defaultValue);
    }

    public void clearFragments(){
        if(segunda!=null && terca!=null && quarta!=null && quinta!=null && sexta!=null) {
            segunda.clear();
            terca.clear();
            quarta.clear();
            quinta.clear();
            sexta.clear();
        }
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        String[] tabs={"Segunda","Terça","Quarta","Quinta","Sexta"};
     //String[] tabs={"Segunda","Terça"};
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);

            // tabs = getResources().getStringArray(R.array.tabsAccount);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            android.support.v4.app.Fragment fragment = null;
            if(position==0)
                fragment= segunda;
            else if (position==1)
                fragment= terca;
            else if (position==2)
                fragment= quarta;
            else if (position==3)
                fragment= quinta;
            else if (position==4)
                fragment= sexta;
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
          //  Toast.makeText(getParent(),"isto: ",Toast.LENGTH_LONG).show();
            Log.e("log","returning name");
            return tabs[position];
        }

        @Override
        public int getCount() {
            Log.e("log","returning size");
            return tabs.length;
        }
    }

}
