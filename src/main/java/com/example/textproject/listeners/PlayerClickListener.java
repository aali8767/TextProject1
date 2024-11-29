package com.example.textproject.listeners;

import com.example.textproject.Player;

public interface PlayerClickListener {
    void onDelete(long id);

    void onItemClicked(Player player);
}
