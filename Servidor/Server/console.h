
#ifndef SERVER_CONSOLE_H
#define SERVER_CONSOLE_H

int select_player();

struct position {
    int row;
    int column;
};

struct fruit {
    struct position pos;
    char fruit_type;
    int points;
};

struct position set_position();

struct fruit add_fruit();

int change_speed();

void select_menu();

void init_console();

#endif //SERVER_CONSOLE_H
