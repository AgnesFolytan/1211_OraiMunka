package com.example.a1211_oraimunka;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

  private List<Item> itemList;
  private Context context;

  public ItemAdapter(List<Item> itemList, Context context) {
    this.itemList = itemList;
    this.context = context;
  }

  @Override
  public int getCount() {
    return itemList.size();
  }

  @Override
  public Object getItem(int i) {
    return null;
  }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    view = LayoutInflater.from(context).inflate(R.layout.bevasarlas_list_item, viewGroup, false);

    TextView nameTextView = view.findViewById(R.id.nameTextView);
    TextView mennyisegTextView = view.findViewById(R.id.mennyisegTextView);
    TextView darab_arTextView = view.findViewById(R.id.darab_arTextView);
    TextView kategoriaTextView = view.findViewById(R.id.kategoriaTextView);
    Button deleteButton = view.findViewById(R.id.deleteButton);

    Item item = itemList.get(i);
    nameTextView.setText(item.getName());
    mennyisegTextView.setText(item.getMennyiseg());
    darab_arTextView.setText(item.getDarab_ar());
    kategoriaTextView.setText(item.getKategoria());

    deleteButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        // Handle delete button click with alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Törlés");
        builder.setMessage("Biztosan törli a felhasználót?");
        builder.setPositiveButton("Igen", (dialog, which) -> {
          // Handle delete confirmation
          itemList.remove(i);
          notifyDataSetChanged();
          dialog.dismiss();
        });
        builder.setNegativeButton("Nem", null);
        AlertDialog dialog = builder.create();
        dialog.show();

      }
    });

    return view;
  }
}
