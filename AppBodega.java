import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList; 

public class AppBodega {
    static String usu = ""; static String contra = "";
    static int NoBodega = 0;
    static String CualProducto = "";
    static int cantidadcomprada = 0;
    static String datos[][] = new String[100][3];
    static String encabezados[] = new String[3];
    public static void main(String [] arg){
        //Creacion de todas las variables
        JFrame app = new JFrame("Control de Bodegas");
        JPanel contrasena = new JPanel();
        JPanel pinicio = new JPanel();
        JPanel pdeBodegas = new JPanel();
        JTextField usuario, contrase;
        JButton iniciodesesion = new JButton("Iniciar Sesion");
        JMenuBar barradeMenuBar = new JMenuBar();
        JMenu TodasLasBodegas = new JMenu("Bodegas");
        JMenuItem Bodega1, Bodega2, Bodega3;
        JButton BproducNuevo = new JButton("Comprar un producto nuevo");
        JButton BproducExist = new JButton("Comprar productos existente");
        //variable o instancia de la MySQL class
        
        //obtener los encabezados para las tablas con un ciclo y query
        encabezados[0] = "ID producto"; 
        encabezados[1] = "Nombre"; 
        encabezados[2] = "Cantidad";
        JTable tabladebodega = new JTable(datos, encabezados);
        JScrollPane scrollPane = new JScrollPane(tabladebodega);
        JButton comprarE = new JButton("Realizar compra");
        JButton comprarN = new JButton("Realizar compra");
        String productos[] = {"","15324 Guitarra", "13213546 Piano", 
        "13213546 Piano","13213546 Piano","13213546 Piano","13213546 Piano","13213546 Piano",
        "13213546 Piano","13213546 Piano","13213546 Piano","13213546 Piano","13213546 Piano",
        "10215 Clarinete"};
        //LinkedList<String[]> Linked_Productos = new LinkedList<String[]>();
        //se recibe el linkedlist y se recorre y se hace addItem para agregarlo al listado. O en la variable productos[]
        JComboBox<String> listado = new JComboBox<String>(productos);
        JButton ACTIVAR = new JButton("Activar compras");
        JButton SalirBodegas = new JButton();
        //---------- Creación de paneles y frame
        
        app.getContentPane();
        
        contrasena.setLayout(null);
        pinicio.setLayout(null);
        contrasena.setBounds(0, 0, 1240, 820);
        pinicio.setBounds(0, 0, 1240, 820);
        pdeBodegas.setBounds(0, 0, 1240, 820);
        pdeBodegas.setVisible(false);
        pinicio.setVisible(false);
        //----------------------------------------------------------
        //-------Parte de botones de incio de sesión----------------
        
        usuario = new JTextField();
        contrase = new JTextField();
        usuario.setBounds(500,100, 200, 30);
        contrase.setBounds(500,150, 200, 30);
        
        iniciodesesion.setBounds(500,200,200,30);
        iniciodesesion.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                usu = usuario.getText();
                contra = contrase.getText(); 
                if(usu.equals("") || contra.equals("")){
                    //mensaje 
                }
                else{
                    contrasena.setVisible(false); 
                    pinicio.setVisible(true);
                }
            }  
        });
        //-----------------------------------------------------------
        //---------Parte de pantalla de inicio-----------------------
        
        pinicio.setLayout(new BorderLayout());
        Bodega1 = new JMenuItem("Bodega 1");
        Bodega1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                pdeBodegas.setVisible(true);
                pinicio.setVisible(false);
                app.setContentPane(pdeBodegas);
                pdeBodegas.setLayout(null);
                NoBodega = 1;
                SalirBodegas.setText("Salir de la bodega: " + NoBodega);
                /*
                    Aquí realizar la Query con respecto a la bodega que se haya seleccionado.
                    Utilizando NoBodega. Luego recorrer la tabla y  añadir por medio de un 
                    ciclo los valores que tenga cada uno en la variable de datos. con el MySQL
                */
                for(int y = 0; y < 30; y++){
                    for(int x = 0; x < 3; x++){
                        datos[y][x] = "1 en y: "+y+" en x: " + x;
                    }
                }
            }  
        });
        Bodega2 = new JMenuItem("Bodega 2");
        Bodega2.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                pdeBodegas.setVisible(true);
                pinicio.setVisible(false);
                app.setContentPane(pdeBodegas);
                pdeBodegas.setLayout(null);
                NoBodega = 2;
                SalirBodegas.setText("Salir de la bodega: " + NoBodega);
                /*
                    Aquí realizar la Query con respecto a la bodega que se haya seleccionado.
                    Utilizando NoBodega. Luego recorrer la tabla y  añadir por medio de un 
                    ciclo los valores que tenga cada uno en la variable de datos. con el MySQL
                */
                for(int y = 0; y < 30; y++){
                    for(int x = 0; x < 3; x++){
                        datos[y][x] = "2 en y: "+y+" en x: " + x;
                    }
                }
            }  
        });
        Bodega3 = new JMenuItem("Bodega 3");
        Bodega3.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                pdeBodegas.setVisible(true);
                pinicio.setVisible(false);
                app.setContentPane(pdeBodegas);
                pdeBodegas.setLayout(null);
                NoBodega = 3;
                SalirBodegas.setText("Salir de la bodega: " + NoBodega);
                /*
                    Aquí realizar la Query con respecto a la bodega que se haya seleccionado.
                    Utilizando NoBodega. Luego recorrer la tabla y  añadir por medio de un 
                    ciclo los valores que tenga cada uno en la variable de datos. con el MySQL
                */
                for(int y = 0; y < 30; y++){
                    for(int x = 0; x < 3; x++){
                        datos[y][x] = "3 en y: "+y+" en x: " + x;
                    }
                }
            }  
        });
        TodasLasBodegas.add(Bodega1);TodasLasBodegas.add(Bodega2);TodasLasBodegas.add(Bodega3);
        barradeMenuBar.add(TodasLasBodegas);
        //-----------------------------------------------------------
        //---------------parte de bodegas----------------------------
        
        scrollPane.setBounds(0,0,400,400);
        scrollPane.setVisible(false);
        
        BproducExist.setBounds(450, 150, 150, 30);
        BproducNuevo.setBounds(450, 150, 150, 30);
        BproducExist.setVisible(false);
        BproducNuevo.setVisible(false);
        
        listado.addItem("Hola el ultimo");
        listado.setVisible(false);
        comprarN.setVisible(false);
        comprarE.setVisible(false);
        comprarE.setBounds(1050, 250, 150, 30);
        comprarN.setBounds(1050, 350, 150, 30);
        listado.setBounds(650, 250, 400, 30);
        BproducExist.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                listado.setVisible(true);
                comprarE.setVisible(true);
                comprarN.setVisible(true);
                BproducExist.setVisible(false);
                BproducNuevo.setVisible(false);
                comprarE.addActionListener(new ActionListener(){  
                    public void actionPerformed(ActionEvent e){
                        CualProducto = "" + listado.getItemAt(listado.getSelectedIndex());
                        cantidadcomprada = 1;
                        BproducExist.setVisible(true);
                        BproducNuevo.setVisible(true);
                        listado.setVisible(false);
                        comprarE.setVisible(false);
                    }  
                });
            }  
        });
        BproducNuevo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

            }
        });
        
        
        SalirBodegas.setVisible(false);
        SalirBodegas.setBounds(450, 10, 150,30);
        SalirBodegas.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pdeBodegas.setVisible(false);
                pinicio.setVisible(true);
                app.setContentPane(pinicio);
                ACTIVAR.setVisible(true);
                scrollPane.setVisible(false);
                SalirBodegas.setVisible(false);
                BproducNuevo.setVisible(false);
                BproducExist.setVisible(false);
                comprarE.setVisible(false);
                comprarN.setVisible(false);
                listado.setVisible(false);
            }
        });
        
        ACTIVAR.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                BproducExist.setVisible(true);
                BproducNuevo.setVisible(true);
                ACTIVAR.setVisible(false);
                scrollPane.setVisible(true);
                SalirBodegas.setVisible(true);
            }
        });
        //-----------------------------------------------------------
        //--------------------agrego las cosas a los paneles---------
        contrasena.add(usuario); contrasena.add(contrase); contrasena.add(iniciodesesion);
        pinicio.add(barradeMenuBar, BorderLayout.NORTH);
        pdeBodegas.add(scrollPane);
        pdeBodegas.add(ACTIVAR, BorderLayout.CENTER);
        pdeBodegas.add(SalirBodegas);
        pdeBodegas.add(listado);pdeBodegas.add(BproducNuevo);pdeBodegas.add(BproducExist);pdeBodegas.add(comprarE);pdeBodegas.add(comprarN);
        //-----------------------------------------------------------
        //------------agregar los paneles al app frame---------------
        //app.setLayout(null); 
        app.add(pinicio); app.add(pdeBodegas); app.add(contrasena);
        app.setSize(contrasena.getWidth(),contrasena.getHeight());   
        app.setVisible(true);
        //
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }
    

}
