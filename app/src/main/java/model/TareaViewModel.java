package model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TareaViewModel extends AndroidViewModel {

    private MutableLiveData<List<Tarea>> listaTareasLiveData;
    private List<Tarea> listaTareas;

    public TareaViewModel(@NonNull Application application) {
        super(application);
        listaTareasLiveData = new MutableLiveData<>();
        crearDatos();
        listaTareasLiveData.setValue(listaTareas);
    }

    public LiveData<List<Tarea>> getTareaList() {
        return listaTareasLiveData;
    }

    public void addTarea(Tarea tarea) {
        int i = listaTareas.indexOf(tarea);
        if (i < 0)
            listaTareas.add(tarea);
        else {
            listaTareas.remove(i);
            listaTareas.add(i, tarea);
        }
        listaTareasLiveData.setValue(listaTareas);
    }

    public void delTarea(Tarea tarea) {
        if (listaTareas.size() > 0) {
            listaTareas.remove(tarea);
            listaTareasLiveData.setValue(listaTareas);
        }
    }

    private void crearDatos() {
        listaTareas = new ArrayList<Tarea>();
        Tarea tarea = new Tarea("Alta", "Mantenimiento", "Abierta", "Juan Perez", " Actualización de antivirus", " Lorem ipsum dolor sit amet, consectetur adipiscing elit.Mauris laoreet aliquam sapien, quis mattis diam pretium vel.Integer nec tincidunt turpis.Vestibulum interdum accumsan massa, sed blandit ex fringilla at.Vivamus non sem vitae nislviverra pharetra.Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum.Integer ornare lorem urna, eget consequat ante lacinia et.Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);
        tarea = new Tarea("Media", "Mantenimiento", "Terminada", "Maria Perez", "Actualización de S.O.Linux", " Lorem ipsum dolor sit amet, consectetur adipiscing elit.Mauris laoreet aliquam sapien, quis mattis diam pretium vel.Integer nec tincidunt turpis.Vestibulum interdum accumsan massa, sed blandit ex fringilla at.Vivamus non sem vitae nislviverra pharetra.Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum.Integer ornare lorem urna, eget consequat ante lacinia et.Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);
        tarea = new Tarea("Baja", "Reparación", "En curso", "Maria Lopez", "Sustitución de ratones", " Lorem ipsum dolor sit amet, consectetur adipiscing elit.Mauris laoreet aliquam sapien, quis mattis diam pretium vel.Integer nec tincidunt turpis.Vestibulum interdum accumsan massa, sed blandit ex fringilla at.Vivamus non sem vitae nislviverra pharetra.Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum.Integer ornare lorem urna, eget consequat ante lacinia et.Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);
        tarea = new Tarea("Media", "Comercial", "Abierta", "Fele Martinez", "Presentar presupuesto Web", " Lorem ipsum dolor sit amet, consectetur adipiscing elit.Mauris laoreet aliquam sapien, quis mattis diam pretium vel.Integer nec tincidunt turpis.Vestibulum interdum accumsan massa, sed blandit ex fringilla at.Vivamus non sem vitae nislviverra pharetra.Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum.Integer ornare lorem urna, eget consequat ante lacinia et.Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);
        tarea = new Tarea("Media", "Comercial", "Abierta", "Fele Martinez", "Presentar presupuesto Web", " Lorem ipsum dolor sit amet, consectetur adipiscing elit.Mauris laoreet aliquam sapien, quis mattis diam pretium vel.Integer nec tincidunt turpis.Vestibulum interdum accumsan massa, sed blandit ex fringilla at.Vivamus non sem vitae nislviverra pharetra.Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum.Integer ornare lorem urna, eget consequat ante lacinia et.Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);

    }
}