package adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.iessochoa.tomassolerlinares.practica4.R;

import java.util.List;

import model.Tarea;

/**
 * Clase adaptador del RecyclerView
 */
public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {
    private List<Tarea> listaTareas;

    private OnItemClickBorrarListener listenerBorrar;
    private OnItemClickEditarListener listenerEditar;

    /**
     * Método que genera un nuevo item cuando se requiere.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(itemView);
    }

    /**
     * Método encargado del indicar la posición en lista del elemento pasa el holder o view requerido.
     * @param tareaViewHolder
     * @param position
     */
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull TareasAdapter.TareaViewHolder tareaViewHolder, int position) {
        if (listaTareas != null) {
            final Tarea tarea = listaTareas.get(position);
            tareaViewHolder.tvTecnico.setText(tarea.getId()+"-"+tarea.getTecnico());
            tareaViewHolder.tvResumen.setText(tarea.getResumen());

            switch (tarea.getEstado()) {
                case "Abierta":
                    tareaViewHolder.ivEstado.setImageResource(R.drawable.ic_recycledview_start);
                    break;
                case "En Curso":
                    tareaViewHolder.ivEstado.setImageResource(R.drawable.ic_recycledview_executing);
                    break;
                case "Terminada":
                    tareaViewHolder.ivEstado.setImageResource(R.drawable.ic_recycledview_finish);
                    break;
            }
            if(tarea.getPrioridad().equals("Alta")){
                tareaViewHolder.layout.setBackgroundResource(R.color.colorImportante);
            }else{
                tareaViewHolder.layout.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    /**
     * Método encargado de mostrar el número de elementos
     * @return
     */
    @Override
    public int getItemCount() {
        if (listaTareas!= null)
            return listaTareas.size();
        else return 0;
    }

    /**
     * Método que asigna la lista al adaptador
     * @param listTarea
     */
    public void setTarea(List<Tarea> listTarea) {
        listaTareas = listTarea;
        notifyDataSetChanged();
    }

    /**
     * Clase extendida de RecyclerView que se encarga de representar a cada item
     */
    public class TareaViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvResumen;
        private final TextView tvTecnico;
        private final ImageView ivEstado;
        private final ConstraintLayout layout;

        private TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvResumen = itemView.findViewById(R.id.tvResumen);
            tvTecnico = itemView.findViewById(R.id.tvTecnico);
            ivEstado = itemView.findViewById(R.id.ivEstado);
            ImageView ivEditar = itemView.findViewById(R.id.ivEditar);
            ImageView ivBorrar = itemView.findViewById(R.id.ivBorrar);
            layout = itemView.findViewById(R.id.item_tarea);
            ivBorrar.setOnClickListener(v -> {
                if (listenerBorrar != null)
                    listenerBorrar.onItemClickBorrar(listaTareas.get( TareaViewHolder.this.getAdapterPosition()));
            });
            ivEditar.setOnClickListener(v -> {
                if (listenerEditar != null)
                    listenerEditar.onItemClickEditar(listaTareas.get( TareaViewHolder.this.getAdapterPosition()));
            });

        }
    }

    /**
     * Interfaz para que el ImageView Borrar tenga función
     */
    public interface OnItemClickBorrarListener {
        void onItemClickBorrar(Tarea tarea);
    }
    /**
     * Interfaz para que el ImageView Editar tenga función
     */
    public interface OnItemClickEditarListener{
        void onItemClickEditar(Tarea tarea);
    }

    /**
     * Método que permite crear el listener de borrar
     * @param listener
     */
    public void setOnClickBorrarListener(OnItemClickBorrarListener listener) {
        this.listenerBorrar = listener;
    }
    /**
     * Método que permite crear el listener de editar
     * @param listener
     */
    public void setOnClickEditarListener(OnItemClickEditarListener listener){
        this.listenerEditar = listener;
    }

}