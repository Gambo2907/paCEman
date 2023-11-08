
#ifndef SERVER_CONSOLE_H
#define SERVER_CONSOLE_H

#include <stdbool.h>

int select_player();

struct game{
    int points;
    int lives;
    bool state;

};
struct game ingame;

struct position {
    int row;
    int column;
};

struct fruit {
    struct position pos;
    char fruit_type;
    int points;
};
struct fruit fruit_selected;
struct position set_position();

void set_points(int points);

struct fruit add_fruit();

int change_speed();

void select_menu(int player);

void init_console();

char speed_level;

#endif //SERVER_CONSOLE_H
