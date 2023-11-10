
#ifndef SERVER_CONSOLE_H
#define SERVER_CONSOLE_H

#include <stdbool.h>
#include "constants.h"
int select_player();

struct game{
    int clientnumber;
    int points;
    int lives;
    bool state;

};
struct position {
    int row;
    int column;
};

struct position set_position();

struct game createstructingame(int player);

struct game ingame[MAX_PLAYERS];

struct fruit {
    struct position pos;
    char fruit_type;
    int points;
};
struct fruit fruit_selected;

void set_points(int points);

struct fruit add_fruit();

int change_speed();

void select_menu(int player);

void init_console();

char speed_level;

int intValuePoints;

#endif //SERVER_CONSOLE_H
