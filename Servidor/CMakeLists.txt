cmake_minimum_required(VERSION 3.26)
project(Server C)

set(CMAKE_C_STANDARD 11)



add_executable(server
        Server/console.h
        Server/constants.h
        Server/main.c
        Server/server.h)

# Link with Winsock library
target_link_libraries(server ws2_32)
