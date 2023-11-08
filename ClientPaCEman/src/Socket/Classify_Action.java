package Socket;
import game.ViewController;


public class Classify_Action {


    

    public static char action,fruit,numberOfClient;
    public static int row,col,value,speed,client;


    

    public static void actionRecv(String new_sms){

        client = new_sms.charAt(0);
        if (client == 'C'){
            numberOfClient = new_sms.charAt(1);
            ViewController.getInstance().setClientType(Integer.parseInt(String.valueOf(numberOfClient))); // Nos indica el numero de cliente que somos en el server
        }
        if (client == '1' && (String.valueOf(ViewController.getInstance().getClientType())).equals("1")){
            action = new_sms.charAt(1); // Palabra clave de la accion a ejecutar
            System.out.println("ACTION: " + action + "\n");
            if (action == 'V') {
                speed = Integer.parseInt(new_sms.substring(new_sms.indexOf(',') + 1));
                System.out.println(speed);
                ViewController.getInstance().changeSpeed(speed); // Ejecuta el cambio de velocidad del juego

            } //else if (action == 'F' || action == 'M' || action == 'G') {
                //row = Integer.parseInt(new_sms.substring(new_sms.indexOf(',') + 1, new_sms.lastIndexOf(',')));
                //col = Integer.parseInt(new_sms.substring(new_sms.lastIndexOf(',') + 1));

                if (action == 'F') {
                    fruit = new_sms.charAt(2);
                    row = Integer.parseInt(new_sms.substring(new_sms.indexOf(',') + 1, new_sms.lastIndexOf(',')));
                    col = Integer.parseInt(new_sms.substring(new_sms.lastIndexOf(',') + 1));
                    value = Integer.parseInt(new_sms.substring(3, new_sms.indexOf(','))); //1FC1000,
                    ViewController.getInstance().addFruit(fruit, row, col, value); // Agrega una nueva fruta al juego
                } if (action == 'M') {
                	row = Integer.parseInt(new_sms.substring(new_sms.indexOf(',') + 1, new_sms.lastIndexOf(',')));
                	col = Integer.parseInt(new_sms.substring(new_sms.lastIndexOf(',') + 1));
                    ViewController.getInstance().addPill(row, col); // Agrega una pildora en el juego
                } else if (action == 'G') {
                	row = Integer.parseInt(new_sms.substring(new_sms.indexOf(',') + 1, new_sms.lastIndexOf(',')));
                	col = Integer.parseInt(new_sms.substring(new_sms.lastIndexOf(',') + 1));
                    ViewController.getInstance().addGhost(row, col); // Agrega un nuevo fantasma al juego
                }
            //} 
                else {
                System.out.println(new_sms);
            }
        }

    }

}
