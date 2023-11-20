package com.example.seminario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.List;

public class TokenAdapter extends RecyclerView.Adapter<TokenAdapter.TokenViewHolder> {

    private List<UserToken> tokens;
    private TokenAdapter.OnItemClickListener onItemClickListener;

    public TokenAdapter(List<UserToken> tokens) {
        this.tokens = tokens;
    }

    // Interfaz para manejar clics
    public interface OnItemClickListener {
        void onItemClick(UserToken tokens);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public TokenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_token_profesor, parent, false);
        return new TokenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TokenViewHolder holder, int position) {
        UserToken token = tokens.get(position);
        holder.textViewCodigo.setText(token.getCodigo());
    }

    @Override
    public int getItemCount() {
        return tokens.size();
    }
    public void setTokens(List<UserToken> tokens) {
        this.tokens = tokens;
        notifyDataSetChanged();
    }

    public class TokenViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCodigo;  // Reemplaza "otroDato" con el nombre correcto del dato que quieras mostrar

        public TokenViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCodigo = itemView.findViewById(R.id.textViewCodigo); // Reemplaza con el ID correcto del TextView en tu diseño
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

        public void bind(UserToken token) {
            textViewCodigo.setText(token.getCodigo());
        }
    }
}
