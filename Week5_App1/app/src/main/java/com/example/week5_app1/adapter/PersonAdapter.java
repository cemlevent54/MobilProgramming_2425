package com.example.week5_app1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.week5_app1.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.week5_app1.data.Person;
import com.example.week5_app1.database.Database;

import android.view.ViewGroup;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Person> personList;
    private Context context;
    private Database db;

    public PersonAdapter(List<Person> personList, Context context) {
        this.personList = personList;
        this.context = context;
        this.db = new Database(context);
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.satir, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personList.get(position);

        if (person == null) return; // 🛡️ bu kontrol crash'leri engeller

        holder.textName.setText(person.getName());
        holder.textAge.setText(String.valueOf(person.getAge()));

        if (person.getGender()) {
            holder.imageProfile.setImageResource(R.drawable.man_icon);
        } else {
            holder.imageProfile.setImageResource(R.drawable.woman_icon);
        }

        // Tam güncelleme dialogu
        holder.btnUpdate.setOnClickListener(v -> {
            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_update, null);

            EditText editName = dialogView.findViewById(R.id.editName);
            EditText editAge = dialogView.findViewById(R.id.editAge);
            RadioGroup radioGender = dialogView.findViewById(R.id.radioGender);
            RadioButton radioWoman = dialogView.findViewById(R.id.radioWoman);
            RadioButton radioMan = dialogView.findViewById(R.id.radioMan);

            editName.setText(person.getName());
            editAge.setText(String.valueOf(person.getAge()));
            if (person.getGender()) {
                radioMan.setChecked(true);
            } else {
                radioWoman.setChecked(true);
            }

            new AlertDialog.Builder(context)
                    .setTitle("Kişiyi Güncelle")
                    .setView(dialogView)
                    .setPositiveButton("Güncelle", (dialog, which) -> {
                        String newName = editName.getText().toString().trim();
                        String ageStr = editAge.getText().toString().trim();

                        if (!newName.isEmpty() && !ageStr.isEmpty()) {
                            int newAge = Integer.parseInt(ageStr);
                            boolean isMale = radioMan.isChecked();

                            // ✅ Veritabanında güncelle
                            db.updatePerson(person.getId(), newName, newAge, person.getImageResId(), isMale);

                            // ✅ Listeyi güncelle
                            person.setName(newName);
                            person.setAge(newAge);
                            person.setGender(isMale);
                            notifyItemChanged(position);
                        }
                    })
                    .setNegativeButton("İptal", null)
                    .show();
        });

        // Sil
        holder.btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Sil")
                    .setMessage(person.getName() + " adlı kişi silinsin mi?")
                    .setPositiveButton("Sil", (dialog, which) -> {

                        db.deletePerson(person.getId());


                        personList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, personList.size());
                    })
                    .setNegativeButton("İptal", null)
                    .show();
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        ImageView imageProfile;
        TextView textName, textAge;
        Button btnUpdate, btnDelete;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.imageProfile);
            textName = itemView.findViewById(R.id.textName);
            textAge = itemView.findViewById(R.id.textAge);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
