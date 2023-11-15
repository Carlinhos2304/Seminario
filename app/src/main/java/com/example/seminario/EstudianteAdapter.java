package com.example.seminario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.seminario.R; // Asegúrate de tener la referencia correcta a tu proyecto

import java.util.List;


public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.ViewHolder> {
    private List<UserEstudiante> estudiantes;
    private OnItemClickListener onItemClickListener;


    public EstudianteAdapter(List<UserEstudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    // Interfaz para manejar clics
    public interface OnItemClickListener {
        void onItemClick(UserEstudiante estudiante);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiante, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserEstudiante estudiante = estudiantes.get(position);

        // Usar los métodos getter para obtener valores y establecerlos en elementos de la interfaz de usuario
        holder.textViewNombre.setText(estudiante.getnombre_Apellido());
        holder.carreraTextView.setText(estudiante.getSpinner_carrera());
        // Agregar otras asignaciones según sea necesario
    }

    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre;
        TextView carreraTextView;
        // Agregar otros TextViews según sea necesario

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            carreraTextView = itemView.findViewById(R.id.textView_carrera);

            // Inicializar otros TextViews según sea necesario
            // Agregar el clic al itemView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        onItemClickListener.onItemClick(estudiantes.get(position));
                    }
                }
            });
        }
    }
}


