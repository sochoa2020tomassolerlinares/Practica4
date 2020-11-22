package net.iessochoa.tomassolerlinares.practica4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import adapters.TareasAdapter;
import model.Tarea;
import model.TareaViewModel;

/**
 * Clase encargada de la ventana principal de la aplicación
 */
public class MainActivity extends AppCompatActivity {

    private final int OPTION_REQUEST_NUEVO = 1;
    private final int OPTION_REQUEST_EDIT = 2;

    private TareasAdapter tareasAdapter;
    private TareaViewModel tareaViewModel;

    /**
     * Método encargado de generar los campos cuando se abre la clase.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fabAddTarea = findViewById(R.id.fabAddTarea);

        tareasAdapter = new TareasAdapter();
        RecyclerView rvLista = findViewById(R.id.rvLista);
        rvLista.setHasFixedSize(true);
        rvLista.setLayoutManager(new LinearLayoutManager(this));
        rvLista.setAdapter(tareasAdapter);

        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);
        tareaViewModel.getTareaList().observe(this, tarea -> tareasAdapter.setTarea(tarea));

        fabAddTarea.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TareaActivity.class);
            startActivityForResult(intent, OPTION_REQUEST_NUEVO);
        });

        tareasAdapter.setOnClickBorrarListener(this::borrarTarea);
        tareasAdapter.setOnClickEditarListener(this::editarTarea);
    }

    /**
     * Método encargado de eliminar una tarea
     * @param tarea
     */
    private void borrarTarea(final Tarea tarea) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
        dialogo.setTitle(getString(R.string.Aviso));

        dialogo.setMessage(getString(R.string.AvisoMsn) + tarea.getId() + getString(R.string.AvisoMsn2));

        dialogo.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> tareaViewModel.delTarea(tarea));
        dialogo.setNegativeButton(android.R.string.no, (dialogInterface, i) -> Toast.makeText(this, R.string.CancelBorrar, Toast.LENGTH_SHORT).show());
        dialogo.show();
    }

    /**
     * Método encargado de editar una tarea.
     * @param tarea
     */
    private void editarTarea(final Tarea tarea) {
        Intent intent = new Intent(this, TareaActivity.class);
        intent.putExtra("EDIT-TAREA", tarea);
        startActivityForResult(intent, OPTION_REQUEST_EDIT);
    }

    /**
     * Método que genera el menú
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Método encargado de generar los items del menú
     * @param item
     * @return
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.ordenar:
                Toast toast = Toast.makeText(getApplicationContext(), R.string.msgOrdenar, Toast.LENGTH_SHORT);
                toast.show();

                return true;
            case R.id.moreInfo:
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogAcercaDe dialog = new DialogAcercaDe();
                dialog.show(fragmentManager, "tagAlerta");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Método encargado de recoger los datos recibidos por otra clase.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, R.string.MsnError, Toast.LENGTH_SHORT).show();
        } else {
            assert data != null;
            Bundle bundle = data.getExtras();
            Tarea tarea = bundle.getParcelable("TAREA");
            tareaViewModel.addTarea(tarea);
        }
    }


}