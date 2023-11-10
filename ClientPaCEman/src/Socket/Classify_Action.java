package Socket;
import game.ViewController;


public class Classify_Action {


    

    public static char action,fruit,numberOfClient;
    public static int row,col,value,speed,client;
    public static int messagePill = 0;


    

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
                    if(fruit == 'C') {
                    	row = 5;
                        col = 3;
                        value = Integer.parseInt(new_sms.substring(3, new_sms.lastIndexOf('.')));
                        ViewController.getInstance().addFruit(fruit, row, col, value);
                    }else if(fruit == 'F') {
                    	row = 6;
                        col = 3;
                        value = Integer.parseInt(new_sms.substring(3, new_sms.indexOf('.')));
                        ViewController.getInstance().addFruit(fruit, row, col, value);
                    }else if(fruit == 'N') {
                    	row = 4;
                        col = 3;
                        value = Integer.parseInt(new_sms.substring(3, new_sms.indexOf('.')));
                        ViewController.getInstance().addFruit(fruit, row, col, value);
                    }else if(fruit == 'M') {
                    	row = 8;
                        col = 3;
                        value = Integer.parseInt(new_sms.substring(3, new_sms.indexOf('.')));
                        ViewController.getInstance().addFruit(fruit, row, col, value);
                    }
                    else if(fruit == 'W') {
                    	row = 3;
                        col = 3;
                        value = Integer.parseInt(new_sms.substring(3, new_sms.indexOf('.')));
                        ViewController.getInstance().addFruit(fruit, row, col, value);
                    }
                     //1FC1000,
                     
                } if (action == 'M') {
                	if(client == '1') {
                		if(messagePill == 0) {
                    		row = 1;
                    		col = 1;
                    		ViewController.getInstance().addPill(row, col); // Agrega una pildora en el juego
                    		messagePill = 1;
                    	}else if(messagePill == 1) {
                    		row = 13;
                    		col = 1;
                    		ViewController.getInstance().addPill(row, col); // Agrega una pildora en el juego
                    		messagePill = 2;
                    	}else if(messagePill == 2) {
                    		row = 1;
                    		col = 11;
                    		ViewController.getInstance().addPill(row, col); // Agrega una pildora en el juego
                    		messagePill = 3;
                    	}else if(messagePill == 3) {
                    		row = 13;
                    		col = 11;
                    		ViewController.getInstance().addPill(row, col); // Agrega una pildora en el juego
                    		messagePill = 0;
                    	}
                	}if(client == '2') {
                		if(messagePill == 0) {
                    		row = 1;
                    		col = 1;
                    		ViewController.getInstance().addPill(row, col); // Agrega una pildora en el juego
                    		messagePill = 1;
                    	}else if(messagePill == 1) {
                    		row = 13;
                    		col = 1;
                    		ViewController.getInstance().addPill(row, col); // Agrega una pildora en el juego
                    		messagePill = 2;
                    	}else if(messagePill == 2) {
                    		row = 1;
                    		col = 11;
                    		ViewController.getInstance().addPill(row, col); // Agrega una pildora en el juego
                    		messagePill = 3;
                    	}else if(messagePill == 3) {
                    		row = 13;
                    		col = 11;
                    		ViewController.getInstance().addPill(row, col); // Agrega una pildora en el juego
                    		messagePill = 0;
                    	}
                	}
                	
                	
                    
                } if (action == 'G') {
                	row = 1;
                	col = 1;
                    ViewController.getInstance().addGhost(row, col); // Agrega un nuevo fantasma al juego
                }
            //} 
                else {
                System.out.println(new_sms);
            }
        }

    }

}
