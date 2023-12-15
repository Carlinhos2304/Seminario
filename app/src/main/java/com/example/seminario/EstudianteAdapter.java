package com.example.seminario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter personalizado para manejar la visualización de datos de estudiantes en un RecyclerView.
 */
public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.ViewHolder> {
    private List<UserEstudiante> estudiantes;
    private OnItemClickListener onItemClickListener;

    /**
     * Constructor que recibe la lista de estudiantes.
     *
     * @param estudiantes La lista de estudiantes a mostrar en el RecyclerView.
     */
    public EstudianteAdapter(List<UserEstudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    /**
     * Interfaz para manejar eventos de clic en elementos del RecyclerView.
     */
    // Interfaz para manejar clics
    public interface OnItemClickListener {
        /**
         * Método invocado cuando se hace clic en un estudiante.
         *
         * @param estudiante El estudiante en el que se hizo clic.
         */
        void onItemClick(UserEstudiante estudiante);
    }

    /**
     * Establece el listener para los clics en los elementos del RecyclerView.
     *
     * @param listener El listener que maneja los eventos de clic.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    /**
     * Crea nuevas vistas (invocado por el layout manager).
     *
     * @param parent   El ViewGroup en el que se incluirán las vistas.
     * @param viewType El tipo de vista del nuevo elemento.
     * @return Un nuevo ViewHolder que contiene una vista del elemento.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiante, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Reemplaza el contenido de una vista (invocado por el layout manager).
     *
     * @param holder   El ViewHolder que debe actualizarse para representar el contenido del elemento en la posición dada en los datos.
     * @param position La posición del elemento dentro del conjunto de datos.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserEstudiante estudiante = estudiantes.get(position);

        // Usar los métodos getter para obtener valores y establecerlos en elementos de la interfaz de usuario
        holder.textViewNombre.setText(estudiante.getnombre_Apellido());
        holder.carreraTextView.setText(estudiante.getSpinner_carrera());
        // Agregar otras asignaciones según sea necesario
    }

    /**
     * Devuelve la cantidad de elementos en el conjunto de datos.
     *
     * @return El tamaño del conjunto de datos (lista de estudiantes).
     */
    @Override
    public int getItemCount() {
        return estudiantes.size();
    }

    /**
     * Clase ViewHolder que representa cada elemento en la lista.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre;
        TextView carreraTextView;
        // Agregar otros TextViews según sea necesario

        /**
         * Constructor que asigna vistas a sus respectivos elementos.
         *
         * @param itemView La vista de cada elemento en el RecyclerView.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNombre = itemView.findViewById(R.id.textViewNombre);
            carreraTextView = itemView.findViewById(R.id.textView_carrera);

            /**
             * Maneja el evento de clic en un elemento de la lista dentro del RecyclerView.
             *
             * @param view La vista en la que se hizo clic.
             */
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


