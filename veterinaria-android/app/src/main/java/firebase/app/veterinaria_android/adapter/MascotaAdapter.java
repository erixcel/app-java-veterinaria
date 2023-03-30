package firebase.app.veterinaria_android.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import firebase.app.veterinaria_android.R;
import firebase.app.veterinaria_android.modelo.Mascota;

public class MascotaAdapter extends FirestoreRecyclerAdapter<Mascota, MascotaAdapter.ViewHolder>{

    public MascotaAdapter(@NonNull FirestoreRecyclerOptions<Mascota> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int position, @NonNull Mascota mascota) {
        viewHolder.txtNombre.setText(mascota.getNombre());
        viewHolder.txtSexo.setText(mascota.getSexo());
        viewHolder.txtEdad.setText(mascota.getEdad());
        viewHolder.txtRaza.setText(mascota.getRaza());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_mascota, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtSexo, txtEdad, txtRaza;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtSexo = itemView.findViewById(R.id.txtSexo);
            txtEdad = itemView.findViewById(R.id.txtEdad);
            txtRaza = itemView.findViewById(R.id.txtRaza);
        }
    }
}
