//
// Created by Gustavo on 6/11/2023.
//

#ifndef WIN32_LEAN_AND_MEAN
#define WIN32_LEAN_AND_MEAN
#endif

#define _WINSOCK_DEPRECATED_NO_WARNINGS

#include <winsock2.h>
#include <windows.h>
#include <ws2tcpip.h>
#include <iphlpapi.h>
#include <stdio.h>

#pragma comment(lib, "Ws2_32.lib")

#define BUFLEN 512          // Tamaño del bufer
#define PORT 19200         // Puerto de conexion
#define ADDRESS "127.0.0.1" // "localhost"
#define MAX_CLIENTS 4       // Maximo de clientes simultaneos
#define MAX_PLAYERS 2
#define MAX_OBSERVERS 2
#define LAST_CLIENT -1
#define CLIENT_TYPE 1
