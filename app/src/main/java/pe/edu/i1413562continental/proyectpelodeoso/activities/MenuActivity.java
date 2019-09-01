package pe.edu.i1413562continental.proyectpelodeoso.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pe.edu.i1413562continental.proyectpelodeoso.ListarProductosActivity;
import pe.edu.i1413562continental.proyectpelodeoso.R;

public class MenuActivity extends AppCompatActivity {

    private Button btnInsertarProducto;
    private Button btnActualizarProducto;
    private Button btnEliminarProducto;
    private Button btnListarProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnInsertarProducto=findViewById(R.id.btnInsertarProducto);
        btnActualizarProducto=findViewById(R.id.btnActualizarProducto);
        btnEliminarProducto=findViewById(R.id.btnEliminarProducto);
        btnListarProductos=findViewById(R.id.btnListarProductos);

        btnInsertarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irActivityInsertar();
            }
        });

        btnActualizarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irActivityActualizar();
            }
        });

        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irActivityEliminar();
            }
        });

        btnListarProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irActivityListar();
            }
        });

    }

    private void irActivityInsertar(){

        Intent intent=new Intent(MenuActivity.this, InsertProductActivity.class);
        startActivity(intent);

    }

    private void irActivityActualizar(){

        Intent intent=new Intent(MenuActivity.this, ActualizarProductoActivity.class);
        startActivity(intent);

    }

    private void irActivityEliminar(){

        Intent intent=new Intent(MenuActivity.this, EliminarProductoActivity.class);
        startActivity(intent);

    }

    private void irActivityListar(){

        Intent intent=new Intent(MenuActivity.this, ListarProductosActivity.class);
        startActivity(intent);

    }

}
