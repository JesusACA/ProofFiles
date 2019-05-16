package mx.itson.proof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.mandar).setOnClickListener(this);
    }


    public void onClick(final View v){
        switch (v.getId()){
            case R.id.mandar:{
                String url = "http://192.168.0.16/service/examples/test/WebServiceProducto.php/";
                final TextView texto = findViewById(R.id.texto);
                final String[] data = {""};
                RequestQueue queue = Volley.newRequestQueue(this);
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                        (url, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                try{
                                    for (int i = 0; i < response.length(); i++){
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        data[0] += "id: " + jsonObject.getString("id") + "\nNombre: " + jsonObject.getString("nombre") +
                                        "\nTelefono: " + jsonObject.getString("telefono") + "\nCorreo: " + jsonObject.getString("correo") +
                                        "\n\n";
                                    }
                                    texto.setText(data[0]);

                                }catch (Exception e){

                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error
texto.setText(error.toString());
                            }
                        });

// Access the RequestQueue through your singleton class.
                queue.add(jsonObjectRequest);
                break;
            }
            default:{
                break;
            }
        }
    }

}
