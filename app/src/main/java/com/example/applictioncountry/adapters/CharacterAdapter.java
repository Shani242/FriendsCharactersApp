package com.example.applictioncountry.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applictioncountry.R;
import com.example.applictioncountry.models.Character;

import java.util.ArrayList;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private Context context;
    private ArrayList<Character> charactersList;
    private ArrayList<Character> charactersFull;

    public CharacterAdapter(Context context, ArrayList<Character> characters) {
        this.context = context;
        this.charactersList = characters;
        this.charactersFull = new ArrayList<>();  // מאתחל רשימה ריקה
    }

    public void setCharacters(ArrayList<Character> characters) {
        this.charactersFull = new ArrayList<>(characters);  // עדכון רשימת הגיבוי
        filter("");  // מציג את כל הדמויות
    }
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.character_item, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = charactersList.get(position);
        holder.characterImage.setImageResource(character.getImageResource());
        holder.characterName.setText(character.getName());
        holder.characterDescription.setText(character.getDescription());

        holder.itemView.setOnClickListener(v -> showCharacterDialog(character));
    }

    @Override
    public int getItemCount() {
        return charactersList.size();
    }

    public void filter(String text) {
        charactersList.clear();
        if (text.isEmpty()) {
            charactersList.addAll(charactersFull);
        } else {
            text = text.toLowerCase();
            for (Character character : charactersFull) {
                if (character.getName().toLowerCase().contains(text) ||
                        character.getDescription().toLowerCase().contains(text)) {
                    charactersList.add(character);
                }
            }
        }
        notifyDataSetChanged();
    }

    private void showCharacterDialog(Character character) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(character.getName())
                .setMessage(character.getDescription())
                .setPositiveButton("סגור", (dialog, which) -> dialog.dismiss())
                .show();
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        ImageView characterImage;
        TextView characterName;
        TextView characterDescription;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            characterImage = itemView.findViewById(R.id.characterImage);
            characterName = itemView.findViewById(R.id.characterName);
            characterDescription = itemView.findViewById(R.id.characterDescription);
        }
    }
}