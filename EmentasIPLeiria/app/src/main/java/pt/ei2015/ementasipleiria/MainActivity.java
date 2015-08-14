package pt.ei2015.ementasipleiria;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends ActionBarActivity implements SiteBody.AsyncResponse{
    TextView textView;
    List<Dia> dias = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HttpClient h = new DefaultHttpClient();
        SiteBody s = new SiteBody(h,"http://ementaipleiria.heliohost.org/cantina2campus2pt.php",null);
        s.setDelegate(this );
        s.execute();
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        textView = (TextView) findViewById(R.id.tt);
        return true;
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

    @Override
    public void processFinish(String output) {
       // textView.setText(output);
        JSONArray a = null;
        try {
            a = new JSONArray(output);
            for(int x = 0; x<a.length() ;x++) {
                //Log.e("teste", a.getJSONObject(x).getString("almoco_sopa"));
                if(a.getJSONObject(x).getString("data")!=null){
                    Dia d = new Dia();
                    d.setDia(new SimpleDateFormat("yyyy-MM-dd").parse(a.getJSONObject(x).getString("data")));
                    if (a.getJSONObject(x).getInt("temAlmoco") == 1) {
                        Refeicao r = new Refeicao(a.getJSONObject(x).getString("almoco_sopa"), a.getJSONObject(x).getString("almoco_carne"), a.getJSONObject(x).getString("almoco_peixe"), a.getJSONObject(x).getString("almoco_sobremesa"));
                        d.setAlmoco(r);
                    }

                    if (a.getJSONObject(x).getInt("temJantar") == 1) {
                        Refeicao r = new Refeicao(a.getJSONObject(x).getString("jantar_sopa"), a.getJSONObject(x).getString("jantar_carne"), a.getJSONObject(x).getString("jantar_peixe"), a.getJSONObject(x).getString("jantar_sobremesa"));
                        d.setJantar(r);
                    }
                    dias.add(d);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        textView.setText(dias.get(0).dia.toString() + " - almoço: " +dias.get(0).getAlmoco().getCarne() + " - " + dias.get(0).getAlmoco().getPeixe());
    }
}
