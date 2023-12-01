package com.example.seminario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.List;

/**
 * Adaptador personalizado para mostrar una lista de tokens en un RecyclerView.
 */
public class TokenAdapter extends RecyclerView.Adapter<TokenAdapter.TokenViewHolder> {

    private List<UserToken> tokens;
    private TokenAdapter.OnItemClickListener onItemClickListener;

    /**
     * Constructor del adaptador TokenAdapter.
     *
     * @param tokens La lista de tokens a mostrar en el RecyclerView.
     */
    public TokenAdapter(List<UserToken> tokens) {
        this.tokens = tokens;
    }

    /**
     * Interfaz para manejar los clics en los elementos del RecyclerView.
     */
    public interface OnItemClickListener {
        void onItemClick(UserToken tokens);
    }

    /**
     * Establece el Listener para los clics en los elementos del RecyclerView.
     *
     * @param listener El Listener para manejar los clics.
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    /**
     * Crea y devuelve un nuevo ViewHolder asociado a una vista de elementos individuales.
     *
     * @param parent   El ViewGroup al que se adjuntará la nueva vista después de que se haya agregado al RecyclerView.
     * @param viewType El tipo de vista del nuevo ViewHolder.
     * @return Un nuevo ViewHolder que contiene una vista de un elemento de la lista.
     */
    @NonNull
    @Override
    public TokenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_token_profesor, parent, false);
        return new TokenViewHolder(view);
    }

    /**
     * Vincula los datos de los tokens a los elementos individuales en el RecyclerView.
     *
     * @param holder   El ViewHolder que debe ser actualizado para representar el contenido del elemento en la posición dada en los datos.
     * @param position La posición del elemento dentro del conjunto de datos del adaptador.
     */
    @Override
    public void onBindViewHolder(@NonNull TokenViewHolder holder, int position) {
        UserToken token = tokens.get(position);
        holder.textViewCodigo.setText(token.getCodigo());
    }

    /**
     * Devuelve el número total de elementos en el conjunto de datos que el adaptador está manejando.
     *
     * @return El número total de elementos en el conjunto de datos del adaptador.
     */
    @Override
    public int getItemCount() {
        return tokens.size();
    }

    /**
     * Establece una nueva lista de tokens y notifica al RecyclerView sobre el cambio.
     *
     * @param tokens La nueva lista de tokens a mostrar en el RecyclerView.
     */
    public void setTokens(List<UserToken> tokens) {
        this.tokens = tokens;
        notifyDataSetChanged();
    }

    /**
     * Clase interna para representar cada elemento individual en el RecyclerView.
     */
    public class TokenViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCodigo;  // Reemplaza "otroDato" con el nombre correcto del dato que quieras mostrar

        /**
         * Constructor para la clase interna TokenViewHolder que representa cada elemento individual en el RecyclerView.
         *
         * @param itemView La vista individual que representa un elemento del RecyclerView.
         */
        public TokenViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCodigo = itemView.findViewById(R.id.textViewCodigo); // Reemplaza con el ID correcto del TextView en tu diseño

            // Configura el OnClickListener para el elemento de la vista
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                        onItemClickListener.onItemClick(tokens.get(position));
                    }
                }
            });
        }

        /**
         * Método para enlazar los datos de un token con los elementos de la vista.
         *
         * @param token El token que se mostrará en la vista.
         */
        public void bind(UserToken token) {
            textViewCodigo.setText(token.getCodigo());
        }
    }
}
