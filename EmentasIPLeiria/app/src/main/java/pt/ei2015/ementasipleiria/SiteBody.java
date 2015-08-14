package pt.ei2015.ementasipleiria;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by alves_000 on 04/05/2015.
 */
public class SiteBody extends AsyncTask<String, Void,String> {
    private AsyncResponse delegate = null;
    private String responseBody = null;
    private HttpClient httpclient;
    private String url;
    private List<NameValuePair> nameValuePairs = null;

    public SiteBody(HttpClient httpclient,String url,List<NameValuePair> nameValuePairs) {
        this.httpclient = httpclient;
        this.nameValuePairs=nameValuePairs;
        this.url = url;
    }

    protected String doInBackground(String... urls) {

        try {
            HttpPost httppost = new HttpPost(url);

            // Add your data
            if(nameValuePairs!=null)
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

            responseBody = EntityUtils.toString(response.getEntity());

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            Log.e("erro1",e.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("erro2",e.toString());
        }
        //activity.textView.setText(responseBody);
        return responseBody;
    }
    protected void onPostExecute(String result)
    {
        delegate.processFinish(result);
    }

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public AsyncResponse getDelegate() {
        return delegate;
    }

    public void setDelegate(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    public static class CheckNetwork {


        private static final String TAG = CheckNetwork.class.getSimpleName();



        public static boolean isInternetAvailable(Context context){
            NetworkInfo info = (NetworkInfo) ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (info == null)
                return false;
            else{
                if(info.isConnected()){
                    return true;
                }
                else{
                    return true;
                }
            }
        }
    }
}

