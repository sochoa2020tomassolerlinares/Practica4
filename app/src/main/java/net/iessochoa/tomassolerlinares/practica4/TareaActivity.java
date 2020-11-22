package net.iessochoa.tomassolerlinares.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import adapters.TareasAdapter;
import model.Tarea;

public class TareaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);

        Spinner spnCategoria;
        Spinner spnPrioridad;
        Spinner spnEstado;
        ImageView ivEstadoTarea;
        EditText edtTecnicoAsig;
        EditText edtBreveDesc;
        EditText edtDesc;
        FloatingActionButton fabSaveTarea;

        spnCategoria = findViewById(R.id.spnCategoria);
        spnPrioridad = findViewById(R.id.spnPrioridad);
        spnEstado = findViewById(R.id.spnEstado);
        ivEstadoTarea = findViewById(R.id.ivEstadoTarea);
        edtTecnicoAsig = findViewById(R.id.edtTecnicoAsig);
        edtBreveDesc = findViewById(R.id.edtBreveDesc);
        edtDesc = findViewById(R.id.edtDesc);
        fabSaveTarea = findViewById(R.id.fabSaveTarea);

        Tarea editTarea = getIntent().getParcelableExtra("EDIT-TAREA");
        if (editTarea != null) {
            this.setTitle(getString(R.string.tarea) + editTarea.getId());
            for (int i = 0; i < spnCategoria.getCount(); i++) {
                if (spnCategoria.getItemAtPosition(i).toString().equalsIgnoreCase(editTarea.getCategoria())) {
                    spnCategoria.setSelection(i);
                }
            }
            for (int i = 0; i < spnPrioridad.getCount(); i++) {
                if (spnPrioridad.getItemAtPosition(i).toString().equalsIgnoreCase(editTarea.getPrioridad())) {
                    spnPrioridad.setSelection(i);
                }
            }
            for (int i = 0; i < spnEstado.getCount(); i++) {
                if (spnEstado.getItemAtPosition(i).toString().equalsIgnoreCase(editTarea.getEstado())) {
                    spnEstado.setSelection(i);
                }
            }
            edtTecnicoAsig.setText(editTarea.getTecnico());
            edtBreveDesc.setText(editTarea.getResumen());
            edtDesc.setText(editTarea.getDescripcion());
        } else {
            this.setTitle(R.string.NuevaTarea);
        }
        spnEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (spnEstado.getSelectedItem().toString()) {
                    case "Abierta":
                        ivEstadoTarea.setImageResource(R.drawable.ic_recycledview_start);
                        break;
                    case "En Curso":
                        ivEstadoTarea.setImageResource(R.drawable.ic_recycledview_executing);
                        break;
                    case "Terminada":
                        ivEstadoTarea.setImageResource(R.drawable.ic_recycledview_finish);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ivEstadoTarea.setImageResource(android.R.drawable.ic_menu_agenda);
            }
        });

        fabSaveTarea.setOnClickListener(v -> {
            if (edtTecnicoAsig.getText().length() != 0 && edtDesc.getText().length() != 0) {
                String categoria = spnCategoria.getSelectedItem().toString();
                String prioridad = spnPrioridad.getSelectedItem().toString();
                String estado = spnEstado.getSelectedItem().toString();
                String tecnico = edtTecnicoAsig.getText().toString();
                String breveDesc = edtBreveDesc.getText().toString();
                String desc = edtDesc.getText().toString();
                Tarea tarea;
                if(editTarea != null){
                    editTarea.setPrioridad(prioridad);
                    editTarea.setCategoria(categoria);
                    editTarea.setEstado(estado);
                    editTarea.setTecnico(tecnico);
                    editTarea.setResumen(breveDesc);
                    editTarea.setDescripcion(desc);
                    tarea = editTarea;
                }else{
                    tarea = new Tarea(prioridad, categoria, estado, tecnico, desc, breveDesc);
                }
                Intent intent = getIntent();
                intent.putExtra("TAREA", tarea);
                setResult(RESULT_OK, intent);
                finish();
            } else {
                Toast.makeText(this, "El nombre Técnico y la descripción son obligatorios", Toast.LENGTH_SHORT).show();
            }
        });
    }
}