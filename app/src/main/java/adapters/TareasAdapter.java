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

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {
    private List<Tarea> listaTareas;

    private OnItemClickBorrarListener listenerBorrar;
    private OnItemClickEditarListener listenerEditar;

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //fijaros que le pasamos el layout que representa cada elemento
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(itemView);
    }


    @SuppressLint("ResourceAsColor")
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

    @Override
    public int getItemCount() {
        if (listaTareas!= null)
            return listaTareas.size();
        else return 0;
    }

    public void setTarea(List<Tarea> listTarea) {
        listaTareas = listTarea;
        notifyDataSetChanged();
    }


    public class TareaViewHolder extends RecyclerView.ViewHolder {
        private TextView tvResumen;
        private TextView tvTecnico;
        private ImageView ivEstado;
        private ImageView ivEditar;
        private ImageView ivBorrar;
        private ConstraintLayout layout;

        private TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvResumen = itemView.findViewById(R.id.tvResumen);
            tvTecnico = itemView.findViewById(R.id.tvTecnico);
            ivEstado = itemView.findViewById(R.id.ivEstado);
            ivEditar = itemView.findViewById(R.id.ivEditar);
            ivBorrar = itemView.findViewById(R.id.ivBorrar);
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

    public interface OnItemClickBorrarListener {
        void onItemClickBorrar(Tarea tarea);
    }

    public interface OnItemClickEditarListener{
        void onItemClickEditar(Tarea tarea);
    }

    public void setOnClickBorrarListener(OnItemClickBorrarListener listener) {
        this.listenerBorrar = listener;
    }

    public void setOnClickEditarListener(OnItemClickEditarListener listener){
        this.listenerEditar = listener;
    }

}