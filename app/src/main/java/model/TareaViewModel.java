package model;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de mantener los datos en las reconstrucciones.
 * La clase ViewModel nos permite mantener los datos en las reconstruciones. En el onCreate recuperamos el viewmodel
 *  * si venimos de una reconstrucción o creará uno nuevo si es nueva la app.
 */
public class TareaViewModel extends AndroidViewModel {

    private MutableLiveData<List<Tarea>> listaTareasLiveData;
    private List<Tarea> listaTareas;

    /**
     * Constructor de la clase
     * @param application
     */
    public TareaViewModel(@NonNull Application application) {
        super(application);
        listaTareasLiveData = new MutableLiveData<>();
        crearDatos();
        listaTareasLiveData.setValue(listaTareas);
    }

    /**
     * Método encargado de recuperar el LiveData
     * @return
     */
    public LiveData<List<Tarea>> getTareaList() {
        return listaTareasLiveData;
    }

    /**
     * Método que añade una tarea
     * @param tarea
     */
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

    /**
     * Método que elimina una tarea
     * @param tarea
     */
    public void delTarea(Tarea tarea) {
        if (listaTareas.size() > 0) {
            listaTareas.remove(tarea);
            listaTareasLiveData.setValue(listaTareas);
        }
    }

    /**
     * Método que genera tareas por defecto
     */
    private void crearDatos() {
        listaTareas = new ArrayList<Tarea>();
        Tarea tarea = new Tarea("Alta", "Mantenimiento", "Abierta", "Juan Perez", " Lorem ipsum dolor sit amet, consectetur adipiscing elit.Mauris laoreet aliquam sapien, quis mattis diam pretium vel.Integer nec tincidunt turpis.Vestibulum interdum accumsan massa, sed blandit ex fringilla at.Vivamus non sem vitae nislviverra pharetra.Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum.Integer ornare lorem urna, eget consequat ante lacinia et.Phasellus ut diam et diam euismod convallis", " Actualización de antivirus");
        listaTareas.add(tarea);
        tarea = new Tarea("Media", "Mantenimiento", "Terminada", "Maria Perez", " Lorem ipsum dolor sit amet, consectetur adipiscing elit.Mauris laoreet aliquam sapien, quis mattis diam pretium vel.Integer nec tincidunt turpis.Vestibulum interdum accumsan massa, sed blandit ex fringilla at.Vivamus non sem vitae nislviverra pharetra.Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum.Integer ornare lorem urna, eget consequat ante lacinia et.Phasellus ut diam et diam euismod convallis", "Actualización de S.O.Linux");
        listaTareas.add(tarea);
        tarea = new Tarea("Baja", "Reparación", "En curso", "Maria Lopez", " Lorem ipsum dolor sit amet, consectetur adipiscing elit.Mauris laoreet aliquam sapien, quis mattis diam pretium vel.Integer nec tincidunt turpis.Vestibulum interdum accumsan massa, sed blandit ex fringilla at.Vivamus non sem vitae nislviverra pharetra.Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum.Integer ornare lorem urna, eget consequat ante lacinia et.Phasellus ut diam et diam euismod convallis", "Sustitución de ratones");
        listaTareas.add(tarea);
        tarea = new Tarea("Media", "Comercial", "Abierta", "Fele Martinez", " Lorem ipsum dolor sit amet, consectetur adipiscing elit.Mauris laoreet aliquam sapien, quis mattis diam pretium vel.Integer nec tincidunt turpis.Vestibulum interdum accumsan massa, sed blandit ex fringilla at.Vivamus non sem vitae nislviverra pharetra.Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum.Integer ornare lorem urna, eget consequat ante lacinia et.Phasellus ut diam et diam euismod convallis", "Presentar presupuesto Web");
        listaTareas.add(tarea);
        tarea = new Tarea("Media", "Comercial", "Abierta", "Fele Martinez", " Lorem ipsum dolor sit amet, consectetur adipiscing elit.Mauris laoreet aliquam sapien, quis mattis diam pretium vel.Integer nec tincidunt turpis.Vestibulum interdum accumsan massa, sed blandit ex fringilla at.Vivamus non sem vitae nislviverra pharetra.Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum.Integer ornare lorem urna, eget consequat ante lacinia et.Phasellus ut diam et diam euismod convallis", "Presentar presupuesto Web");
        listaTareas.add(tarea);

    }
}