package pe.edu.i1413562continental.proyectpelodeoso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.edu.i1413562continental.proyectpelodeoso.constants.Constantes;

public class ListarProductosActivity extends AppCompatActivity {

    private ListView lvProductos;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_productos);

        lvProductos=findViewById(R.id.lvProductos);
        adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1);

        lvProductos.setAdapter(adapter);

        AndroidNetworking.get(Constantes.URL_LISTAR_PRODUCTOS)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String respuesta = response.getString("respuesta");
                            if(respuesta.equals("200")){
                                JSONArray arrayProductos = response.getJSONArray("data");
                                for(int i=0;i<arrayProductos.length();i++){
                                    JSONObject jsonProducto = arrayProductos.getJSONObject(i);
                                    String id = jsonProducto.getString("id");
                                    String codigo = jsonProducto.getString("codigo");
                                    String nombre = jsonProducto.getString("nombre");
                                    String stock = jsonProducto.getString("stock");
                                    String precio = jsonProducto.getDouble("precio") + " Nuevos Soles";
                                    String genero = jsonProducto.getString("genero");
                                    String descripcion = jsonProducto.getString("descripcion");
                                    String idcategoria = jsonProducto.getString("idcategoria");

                                    String dataString = id + "\n" + codigo + "\n" + nombre + "\n" + stock + "\n" +precio + "\n" + genero + "\n" + descripcion + "\n" + idcategoria;
                                    adapter.add(dataString);
                                }
                                adapter.notifyDataSetChanged();
                            }else{
                                Toast.makeText(ListarProductosActivity.this, "No hay ningun producto disponible.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(ListarProductosActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(ListarProductosActivity.this, "Error: "+anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
