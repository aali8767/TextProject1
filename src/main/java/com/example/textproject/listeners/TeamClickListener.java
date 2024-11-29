package com.example.textproject.listeners;

import com.example.textproject.Team;

public interface TeamClickListener {
    void onDelete(long id);

    void onItemClicked(Team team);
}
