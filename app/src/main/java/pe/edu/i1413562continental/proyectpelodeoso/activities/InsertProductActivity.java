package pe.edu.i1413562continental.proyectpelodeoso.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import pe.edu.i1413562continental.proyectpelodeoso.constants.Constantes;
import pe.edu.i1413562continental.proyectpelodeoso.R;

public class InsertProductActivity extends AppCompatActivity {

    private EditText edtid;
    private EditText edtcodigo;
    private EditText edtnombre;
    private EditText edtstock;
    private EditText edtprecio;
    private EditText edtgenero;
    private EditText edtdescripcion;
    private EditText edtidcategoria;

    private Button btnguardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);

        AndroidNetworking.initialize(getApplicationContext());

        edtid=findViewById(R.id.edtid);
        edtcodigo=findViewById(R.id.edtcodigo);
        edtnombre=findViewById(R.id.edtnombre);
        edtstock=findViewById(R.id.edtstock);
        edtprecio=findViewById(R.id.edtprecio);
        edtgenero=findViewById(R.id.edtgenero);
        edtdescripcion=findViewById(R.id.edtdescripcion);
        edtidcategoria=findViewById(R.id.edtidcategoria);


        btnguardar = findViewById(R.id.btnguardar);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarProducto();
            }
        });


    }

    private void guardarProducto(){
        if(isValidarCampos()){

            String idp = edtid.getText().toString();
            String codigo = edtcodigo.getText().toString();
            String nombre = edtnombre.getText().toString();
            String stock = edtstock.getText().toString();
            String precio = edtprecio.getText().toString();
            String genero = edtgenero.getText().toString();
            String descripcion = edtdescripcion.getText().toString();
            String idcategoria = edtidcategoria.getText().toString();

            Map<String,String> datos = new HashMap<>();
            datos.put("idp",idp);
            datos.put("codigo",codigo);
            datos.put("nombre",nombre);
            datos.put("stock",stock);
            datos.put("precio",precio);
            datos.put("genero",genero);
            datos.put("descripcion",descripcion);
            datos.put("idcategoria",idcategoria);
            JSONObject jsonData = new JSONObject(datos);

            AndroidNetworking.post(Constantes.URL_INSERTAR_PRODUCTO)
                    .addJSONObjectBody(jsonData)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String estado = response.getString("estado");
                                String error = response.getString("error");
                                Toast.makeText(InsertProductActivity.this, estado, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(GuardarProductoActivity.this, "Error: "+error, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(InsertProductActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(InsertProductActivity.this, "Error: "+anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            Toast.makeText(this, "No se puede insertar un producto si existen campos vacios", Toast.LENGTH_SHORT).show();
        }
    }

    //devuelve verdadero si es que no hay campos vacios
    //devuelve falso si es que hay como minimo un campo vacio
    //"     01  " => "01"
    private boolean isValidarCampos(){
        return !edtid.getText().toString().trim().isEmpty() &&
                !edtcodigo.getText().toString().trim().isEmpty() &&
                !edtnombre.getText().toString().trim().isEmpty() &&
                !edtstock.getText().toString().trim().isEmpty() &&
                !edtprecio.getText().toString().trim().isEmpty() &&
                !edtgenero.getText().toString().trim().isEmpty() &&
                !edtdescripcion.getText().toString().trim().isEmpty() &&
                !edtidcategoria.getText().toString().trim().isEmpty();
    }


}
