package Socket;

import game.ViewController;
import Observer.ObserverController;



public class Observer_Action {

    

    public static char action,fruit,client;
    public static int row, col,value,speed;


    

    public static void observerRecv(String new_sms){

        client = new_sms.charAt(0);

        if (String.valueOf(client).equals(String.valueOf(ObserverController.getInstance().getObserver()))){
            action = new_sms.charAt(1); // Palabra clave de la accion a ejecutar
            if (action == 'V') {
                speed = Integer.parseInt(new_sms.substring(new_sms.indexOf(',') + 1));
                System.out.println(speed);
                ObserverController.getInstance().changeSpeed(speed); // Ejecuta el cambio de velocidad del juego

            } else if (action == 'F' || action == 'M' || action == 'G' || action == 'U') {
                row = Integer.parseInt(new_sms.substring(new_sms.indexOf(',') + 1, new_sms.lastIndexOf(',')));
                col = Integer.parseInt(new_sms.substring(new_sms.lastIndexOf(',') + 1));

                if (action == 'U') {

                    if((ObserverController.getInstance().getPacmanBoxX()-row) == 1){
                        ObserverController.getInstance().mLeft();
                    }else if((ObserverController.getInstance().getPacmanBoxX()-row) == -1){
                        ObserverController.getInstance().mRight();
                    }else if((ObserverController.getInstance().getPacmanBoxY()- col) == 1){
                        ObserverController.getInstance().mUp();
                    }else if((ObserverController.getInstance().getPacmanBoxY()- col) == -1){
                        ObserverController.getInstance().mDown();
                    }
                    ObserverController.getInstance().pacmanLocation(row*60, col*60); // Nos indica la ubicacion de pacman
                }
                if (action == 'F') {
                    fruit = new_sms.charAt(2);
                    value = Integer.parseInt(new_sms.substring(3, new_sms.indexOf(',')));
                    ObserverController.getInstance().addFruit(fruit, row, col, value); // Agrega una nueva fruta al juego
                } else if (action == 'M') {
                    ObserverController.getInstance().addPill(row, col); // Agrega una pildora en el juego
                } else if (action == 'G') {
                    ObserverController.getInstance().addGhost(row, col); // Agrega un nuevo fantasma al juego
                }
            } else {
                System.out.println(new_sms);
            }
        }
    }
}

