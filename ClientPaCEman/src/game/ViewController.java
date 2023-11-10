package game;
import Objects.*;


import AbstractFactory.Characters;
import AbstractFactory.Ghost;
import AbstractFactory.GhostFactory;
import AbstractFactory.SimpleGhostFactory;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import Socket.Classify_Action;
import Socket.Client;
import menu.MainMenu;
import Objects.Maps;

//import gnu.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ViewController extends JPanel implements ActionListener {
    private InputStream input;
    private OutputStream output;
    //private SerialPort serialPort;


    GhostFactory ghostFactory = new SimpleGhostFactory();
    private Timer timer;
    private int clientType;
    private Characters blinky, pinky, clyde ,inky;
    private Pacman pacman;
    private ArrayList <Characters> characters;
    private ArrayList <Intersection> intersections;
    private boolean life, stop, panic, success;
    private boolean prueba = true;
    private Maps maps;
    private int score, panicTimer, dots;
    private java.lang.Integer gameSpeed = 30;
    private java.lang.Integer houseTimer = 0;
    private java.lang.Integer totalGhost = 0;
    private java.lang.Integer appleScore, orangeScore, melonScore, strawberryScore, cherryScore;
    private static ViewController instance = null;
    private AudioClip eatDot, eatPill, eatGhost, death;

    // Conexion Socket
    private String messageReceived;
    private Client client;

    
    public ViewController(){
        //Arduino connection
/**
        try {
            // Identifica el puerto serial de Arduino (cambia el nombre del puerto según tu configuración)
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier("COM3");

            if (portIdentifier.isCurrentlyOwned()) {
                System.err.println("Error: El puerto está siendo utilizado");
            } else {
                CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

                if (commPort instanceof SerialPort) {
                    serialPort = (SerialPort) commPort;
                    serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                    input = serialPort.getInputStream();
                    output = serialPort.getOutputStream();
                } else {
                    System.err.println("Error: Solo se pueden utilizar puertos seriales");
                }
            }
        } catch (PortInUseException | UnsupportedCommOperationException | IOException | gnu.io.NoSuchPortException e) {
            e.printStackTrace();
        }
**/
        // Connection
        Client client = new Client("127.0.0.1", 19200);
        this.client = client; // instantiate a client
        connect(); // client connect
        send("P/");
              
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                newGame();
            }
        });
        thread1.start();
    }

    //////////////////////////////////////////////////// Getter and Setter Section ////////////////////////////////////////////////////////////

    public Integer getAppleScore() {
        return appleScore;
    }

    public void setAppleScore(Integer appleScore) {
        this.appleScore = appleScore;
    }

    public Integer getOrangeScore() {
        return orangeScore;
    }

    public void setOrangeScore(Integer orangeScore) {
        this.orangeScore = orangeScore;
    }

    public Integer getMelonScore() {
        return melonScore;
    }

    public void setMelonScore(Integer melonScore) {
        this.melonScore = melonScore;
    }

    public Integer getStrawberryScore() {
        return strawberryScore;
    }

    public void setStrawberryScore(Integer strawberryScore) {
        this.strawberryScore = strawberryScore;
    }

    public Integer getCherryScore() {
        return cherryScore;
    }

    public void setCherryScore(Integer cherryScore) {
        this.cherryScore = cherryScore;
    }

    public void setDots(Integer dots) {
        this.dots += dots;
    }

    public Integer getTotalGhost() {
        return totalGhost;
    }

    public void setTotalGhost(Integer totalGhost) {
        this.totalGhost += totalGhost;
    }

    public ArrayList<Characters> getCharacters() {
        return characters;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score -= score;
    }

    public void setCharacters(ArrayList<Characters> characters) {
        this.characters = characters;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public Integer getGameSpeed() {return gameSpeed;}

    public void setGameSpeed(Integer gameSpeed) {this.gameSpeed = gameSpeed;}




    public void newGame(){

        //Opciones del JPanel
        setFocusable(true);
        setDoubleBuffered(true);
        setVisible(true);
        setBackground(Color.BLACK);
        //Creamos un timer y añadimos keylistener
        timer = new Timer(150, this);
        timer.start();
        addKeyListener(new TAdapter());
        //Creamos el laberinto
        maps = new Maps();
        dots = maps.totalDots();
        //Creamos el array para las casillas con los cruces
        intersections = new ArrayList <Intersection>();
        createIntersections();
        // Creamos el array para introducir todos los personajes que se mueven en el juego.
        characters = new ArrayList <Characters>();
        //Creamos Pacman
        pacman = new Pacman();
        characters.add(pacman);
        //Inicializamos las variables del juego
        panic =false;
        stop =false;
        life =true;
        success =false;
        score = 0;

        //Genero los audios
        URL url = ViewController.class.getResource("/Resources/eatDot.wav");
        eatDot = Applet.newAudioClip(url);
        URL url1 = ViewController.class.getResource("/Resources/eatPill.wav");
        eatPill = Applet.newAudioClip(url1);
        URL url2= ViewController.class.getResource("/Resources/eatGhost.wav");
        eatGhost = Applet.newAudioClip(url2);
        URL url3 = ViewController.class.getResource("/Resources/death.wav");
        death = Applet.newAudioClip(url3);
    }


    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d=(Graphics2D)g;
        for(int x = 0; x< maps.sizeMapX(); x++){
            for (int y = 0; y< maps.sizeMapY(); y++){
                g2d.drawImage(maps.getImage(x,y), x*60, y*60, this);
            }
        }
        for(Characters character: characters) {
            g2d.drawImage(character.getImage(), character.getX(), character.getY(), this);
        }
        if(stop){
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/Resources/pause.png"));
            Image imageStop = imageIcon.getImage();
            g2d.drawImage(imageStop,0,0, this);
        }
        if(!life){
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/Resources/game_over.png"));
            Image imageDeath = imageIcon.getImage();
            g2d.drawImage(imageDeath,0,0, this);
        }

        if(success){
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/Resources/success.png"));
            Image imageSuccess = imageIcon.getImage();
            g2d.drawImage(imageSuccess,0,0, this);
        }

        g2d.setFont(new Font("SansSerif", Font.BOLD, 35));
        g2d.setColor(Color.green);
        g2d.drawString("Score: "+ score,700,45);
        if(panic){
            // Dibuja el contador del momento en que pacman puede comer fantasmas
            int time = panicTimer *(125)/1000;
            g2d.drawString("Time: "+ time,350,45);
        }

        // Dibuja los corazones que representan la vida de pacman
        for (int i = 0; i < pacman.pacmanLives(); i++) { // cuantas vidas tiene pacman
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/Resources/heart.png"));
            Image imageHeart = imageIcon.getImage();
            g2d.drawImage(imageHeart,i*60,maps.sizeMapX(), this);
        }

        //Opciones del método paint()
        Toolkit.getDefaultToolkit().sync();
        setDoubleBuffered(true);
        g2d.dispose();
        repaint();
    }

    
    public void createIntersections(){
        for(int x = 0; x< maps.sizeMapX(); x++){
            for (int y = 0; y< maps.sizeMapY(); y++){
                if(maps.getValue(x,y) != 0){
                    if( (maps.left(x,y) || maps.right(x,y)) && ( maps.down(x,y) || maps.up(x,y) )){
                        Intersection intersection = new Intersection(x,y);
                        intersections.add(intersection);
                    }
                }
            }
        }
    }

//ARDUINO
    /**
public void readFromArduino() {
    try {
        int availableBytes = input.available();
        byte[] data = new byte[availableBytes];
        int bytesRead = input.read(data);

        if (bytesRead > 0) {
            System.out.println(data[0]);
            if(data[0]==52){
                pacman.moveRight();
            }else if(data[0]==50){
                pacman.moveLeft();
            }else if(data[0]==60){
                pacman.moveUp();
            }else if(data[0]==70){
                pacman.moveDown();
            }

            String messageReceived = new String(data, 0, bytesRead);

            System.out.println("Mensaje recibido de Arduino: " + messageReceived);

        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
**/
    public void actionPerformed(ActionEvent e){
        //ARDUINO
        /**
         try {
         readFromArduino();
         } catch (Exception ex) {
         throw new RuntimeException(ex);
         }
         **/
        if(!stop){
            if(getClientType() != 0) {
                setLivesScreen();
            	send(getClientType() + "U"+  "," + pacman.getBoxX()+","+pacman.getBoxY() + "/");
            }
            for(Characters character: characters){
                verifyDirections(character);
                verifyIntersection(character);
                if(character instanceof Ghost){
                    Ghost ghost = (Ghost) character;
                    ghost.artificialIntelligence(pacman.getX(),pacman.getY());
                }
                character.move();
            }
            verifyCollider();//Método que comprueba las diferentes colisiones del juego y toma una desicion
            eatDots();//Método que gestiona los dots comindos por parte de Pacman
            //Método que gestiona el fin de partida cuando Pacman acaba con todos los dots
            if(dots == 0){
                success=true;
                endGame();
                removeAll();
            }
            //Contador del pánico
            if((panic) && (!stop)){
                panicTimer--;
            }
            //Contador de los fantasmas en casa
            if(!stop){
                houseTimer--;
            }
            //Llamada al método panicoFin() cuando el contador llega a 0
            if(panicTimer == 0){
                endPanic();
            }
            //Llamada al método salirCasa(( cuando el contador llega a 0
            if(houseTimer == 0){
                leaveHouse();
            }
        }
        
        
    }
    
    public void setLivesScreen() {
    	if(getScore()%1000 == 0 && getScore() != 0){
        	
        	pacman.setLives(pacman.pacmanLives() + 1);
            send(getClientType() + "L" + "," + "1");  //ENVIO DE INFO
            
        }
    }


   
    public void eatDots(){
        //Sistema para comer los dots
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 1){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            send(getClientType() + "D"+ "," + getScore() + "/"); //ENVIO INFO
            eatDot.play();
            score += 10;
            dots--;
        }
        //Sistema para comer las pildoras
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 2){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            // send("M" + getClientType() + "/"); ENVIO INFO
            eatPill.play();
            score += 50;
            panicTimer = 40;
            panic();
            dots--;
        }
        //Sistema para comer frutas

        //Cereza
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 4){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            send(getClientType() + getCherryScore() + "/"); //ENVIO INFO
            score += getCherryScore();
            dots--;
        }

        //Melon
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 5){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            send(getClientType() + getMelonScore() + "/"); //ENVIO INFO
            score += getMelonScore();
            dots--;
        }

        //Manzana
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 6){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            send(getClientType() + getAppleScore() + "/"); //ENVIO INFO
            score += getAppleScore();
            dots--;
        }

        //Naranja
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 7){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            send(getClientType() + getOrangeScore() + "/"); //ENVIO INFO
            score += getOrangeScore();
            dots--;
        }

        //Fresa
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 8){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            send(getClientType() + getStrawberryScore() + "/"); //ENVIO INFO
            score += getStrawberryScore();
            dots--;
        }

    }


    
    public void verifyCollider()
    {
        for(Characters character: characters){
            //Se recorre el laberinto con sus diferentes valores, si es 0, que corresponde con el muro, se paran los personajes.
            for(int x =0; x< maps.sizeMapX(); x++){
                for (int y =0; y< maps.sizeMapY(); y++){
                    if (maps.getValue(x,y) == 0){
                        Rectangle characterRect = character.createRectangle();
                        Rectangle wall = new Rectangle(x*60,y*60,60,60);
                        if(characterRect.intersects(wall)){
                            character.stop();
                        }
                    }
                }

                //Colisiones entre fantasmas
                if(character instanceof Ghost){
                    Rectangle blinkyRect = character.createRectangle();
                    Rectangle pinkyRect = character.createRectangle();
                    Rectangle clydeRect = character.createRectangle();
                    Rectangle inkyRect = character.createRectangle();

                    if (getTotalGhost() >= 2) {
                        if (blinkyRect.intersects(pinkyRect)) {
                            blinky.back();
                            pinky.back();
                        }
                        if (getTotalGhost() >= 3) {
                            if (blinkyRect.intersects(clydeRect)) {
                                blinky.back();
                                clyde.back();
                            }

                            if (clydeRect.intersects(pinkyRect)) {
                                pinky.back();
                                clyde.back();
                            }

                            if (getTotalGhost() >= 4) {

                                if(blinkyRect.intersects(inkyRect)){
                                    blinky.back();
                                    inky.back();
                                }
                                if(clydeRect.intersects(inkyRect)){
                                    inky.back();
                                    clyde.back();
                                }
                                if(inkyRect.intersects(pinkyRect)){
                                    pinky.back();
                                    inky.back();
                                }

                            }

                        }
                    }

                }

            }
            //Sistema de colision entre pacman y los fantasmas
                if(character instanceof Ghost){
                    Rectangle ghostRect = character.createRectangle();
                    Rectangle pacmanRect = pacman.createRectangle();
                    if(ghostRect.intersects(pacmanRect)){
                        int lives = pacman.pacmanLives();
                        if(!panic){
                            death.play();
                            pacman.pacmanDeath();
                            //send("L" + getClientType() + "-/"); ENVIO DE INFO
                            if(lives == 0) {
                                endGame();
                            }
                        }
                        if(panic){
                            score = score + 500;
                            eatGhost.play();
                            // send("G"+getClientType()+"/"); ENVIO INFO
                            Ghost ghost = (Ghost)character; //Cast al personaje para declararlo de la clase Fantasmas
                            deathGhost(ghost);
                        }
                    }
                }
        }
    }


    
    public void verifyIntersection(Characters character)
    {
        Rectangle r1 = character.createRectangle();
        for(Intersection intersection: intersections){
            Rectangle r2 = intersection.createRectangle();
            if(r1.contains(r2)){
                character.intersection();
            }
        }

    }


    
    public void verifyDirections(Characters character)
    {
        int x = character.getBoxX();
        int y = character.getBoxY();
        character.availableDirections(maps.up(x,y), maps.down(x,y), maps.right(x,y), maps.left(x,y));
    }


    
    public void panic()
    {
        panic = true;
        for(Characters character: characters){
            if(character instanceof Ghost){
                Ghost ghost = (Ghost) character;
                ghost.panic();
            }
        }
    }


   
    public void endPanic()
    {
        panic = false;
        for(Characters character: characters){
            if(character instanceof Ghost){
                Ghost ghost = (Ghost) character;
                ghost.finalPanic();
            }
        }
    }


    
    public void deathGhost(Ghost ghost)
    {
        ghost.death();
        houseTimer=20;
    }


    
    public void leaveHouse(){
        for(Characters character: characters){
            if(character instanceof Ghost){
                Ghost ghost = (Ghost) character;
                if(ghost.isDeath()){
                    ghost.exit();
                }
            }
        }
    }


    
    public boolean stop(){
        stop = !stop;
        return stop;
    }


    
    public void nextGame()
    {
        if((success) || (!life)){
            newGame();
            System.out.println("SE DEBERIA INICIAR UN NUEVO JUEGO");
        }
    }


   
    public void endGame()
    {
        life = false;
        removeAll();
        timer.stop();
    }


    
    public int calcX(int x)
    {
        return x*60 ;
    }

    
    public int calcY(int y)
    {
        return y*60;
    }


    

    public void addPill(java.lang.Integer row, java.lang.Integer col){
        maps.addPill(row,col);
    }


    
    public void changeSpeed(java.lang.Integer newSpeed){

        if(newSpeed == 1){
            setGameSpeed(20);
        }else if(newSpeed == 2){
            setGameSpeed(30);
        }else if(newSpeed == 3){
            setGameSpeed(60);
        }
    }
    
    public void addFruit(Character fruit, Integer row, Integer col, Integer value){


        //fresa
        if(fruit == 'F'){
            maps.addFruit(row,col,8);
            setStrawberryScore(value);
            
        }
        //naranja
        if(fruit == 'N'){
            maps.addFruit(row,col,7);
            setOrangeScore(value);
           
        }
        //manzana
        if(fruit == 'M'){
            maps.addFruit(row,col,6);
            setAppleScore(value);
            
        }
        //melon
        if(fruit == 'W'){
            maps.addFruit(row,col,5);
            setMelonScore(value);
            
        }

        //cereza
        if(fruit == 'C'){
            maps.addFruit(row,col,4);
            setCherryScore(value);
            
        }
    }

    
    public void addGhost(java.lang.Integer row, java.lang.Integer col){

        if(maps.verifyBox(row,col)){
            if (getTotalGhost() == 0){
                blinky = ghostFactory.createGhostBlinky(calcX(row),calcY(col));
                getCharacters().add(blinky);
                setTotalGhost(1);

            }else if(getTotalGhost() == 1){
                //Creamos Pinky
                pinky = ghostFactory.createGhostPinky(calcX(row),calcY(col));
                getCharacters().add(pinky);
                setTotalGhost(1);

            }else if(getTotalGhost() == 2) {

                //Creamos Clyde
                clyde = ghostFactory.createGhostClyde(calcX(row),calcY(col));
                getCharacters().add(clyde);
                setTotalGhost(1);

            }else if(getTotalGhost() == 3){
                //Creamos Inky
                inky = ghostFactory.createGhostInky(calcX(row),calcY(col));
                getCharacters().add(inky);
                setTotalGhost(1);
            }



        }else{
            System.out.println("NO SE PUEDE AGREGAR EL FANTASMA EN EL LUGAR SOLICITADO YA QUE ES UN MUERO, INTENTA CON OTRO");
        }

    }

    
    public static ViewController getInstance(){
        if(instance == null){
            instance = new ViewController();
        }
        return instance;
    }


    //--------------Funciones para conexion socket---------------//
    
    public void connect() {
        if (client.connect()) {
            System.out.println("Conexion exitosa!");
            startReading();
        } else {
            System.out.println("No se pudo establecer conexion con el servidor.");
        }
    }

   
    public void startReading() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    messageReceived = client.read();
                    if (messageReceived != "-1") {
                        System.out.println("Recibido: " + messageReceived);
                        Classify_Action.actionRecv(messageReceived);
                    } else {
                    	System.out.println("Servidor desconectado.");
                    	client.disconnect();
                        break;
                    }
                }
                //System.out.println("Desconectado.");
                //client.disconnect();
                
            }
        });
        thread.start();
    }

   
    public void send(String msg) {
    	
    	 if (messageReceived != "-1") {
    		 System.out.println("Enviando: " + msg);
    		 client.send(msg);
    	 }else {
    		 System.out.println("Servidor desconectado.");
             client.disconnect();
             
    	 }
    }
    

   
    public void keyPress(KeyEvent e)
    {
        int code = e.getKeyCode();
        switch (code)
        {
            case 38:
                pacman.moveUp();
                break;
            case 39:
                pacman.moveRight();
                break;
            case 40:
                pacman.moveDown();
                break;
            case 37:
                pacman.moveLeft();
                break;
            case 80:
                stop();
                break;
            case 10:
                newGame();
                break;
        }
    }

    
    private class TAdapter extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            keyPress(e);
        }
    }
}
