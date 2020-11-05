import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

public class AppBodega {
    static String usu = ""; static String cont = "";
    static int NoBodega = 0;
    static String CualProducto = "";
    static int cantidadcomprada = 0;
    public static void main(String [] arg){
        
        //---------- Creación de paneles y frame
        JFrame app = new JFrame("Control de Bodegas");
        app.getContentPane();
        JPanel contrasena = new JPanel();
        JPanel pinicio = new JPanel();
        JPanel pdeBodegas = new JPanel();
        contrasena.setLayout(null);
        pinicio.setLayout(null);
        contrasena.setBounds(0, 0, 1240, 820);
        pinicio.setBounds(0, 0, 1240, 820);
        pdeBodegas.setBounds(0, 0, 1240, 820);
        pdeBodegas.setVisible(false);
        pinicio.setVisible(false);
        //----------------------------------------------------------
        //-------Parte de botones de incio de sesión----------------
        JTextField usuario, contrase;
        usuario = new JTextField();
        contrase = new JTextField();
        usuario.setBounds(500,100, 200, 30);
        contrase.setBounds(500,150, 200, 30);
        JButton iniciodesesion = new JButton("Iniciar Sesion");
        iniciodesesion.setBounds(500,200,200,30);
        iniciodesesion.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                usu = usuario.getText();
                cont = contrase.getText(); 
                contrasena.setVisible(false);
                pinicio.setVisible(true);
            }  
        });
        //-----------------------------------------------------------
        //---------Parte de pantalla de inicio-----------------------
        JMenuBar barradeMenuBar = new JMenuBar();
        JMenu TodasLasBodegas = new JMenu("Bodegas");
        JMenuItem Bodega1, Bodega2, Bodega3;
        pinicio.setLayout(new BorderLayout());
        Bodega1 = new JMenuItem("Bodega 1");
        Bodega1.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                pdeBodegas.setVisible(true);
                pinicio.setVisible(false);
                app.setContentPane(pdeBodegas);
                pdeBodegas.setLayout(null);
                NoBodega = 1;
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
            }  
        });
        TodasLasBodegas.add(Bodega1);TodasLasBodegas.add(Bodega2);TodasLasBodegas.add(Bodega3);
        barradeMenuBar.add(TodasLasBodegas);
        //-----------------------------------------------------------
        //---------------parte de bodegas----------------------------
        /*
            Aquí debería de ir la instancia de la clase de MySQL, y realizar la Query con respecto a la bodega que se haya seleccionado.
            Utilizando NoBodega. Luego recorrer la tabla y asigarle los valors de los encabezados a la variable que corresponte y añadir por medio de un 
            ciclo los valores que tenga cada uno en la variable de datos. 
        */
        String datos[][] = { 
        {"101","Amit","670000"},    
        {"102","Jai","780000"},    
        {"101","Sachin","700000"},{"101","Amit","670000"},    
        {"102","Jai","780000"},    
        {"101","Sachin","700000"}
                                    }; 
        String encabezados[] = {"ID","NAME","SALARY"};
        JTable tabladebodega = new JTable(datos, encabezados);
        JScrollPane scrollPane = new JScrollPane(tabladebodega);
        tabladebodega.setBounds(0,0,400,300);
        //tabladebodega.setVisible(true);
        JButton producNuevo = new JButton("Comprar un producto nuevo");
        JButton producExist = new JButton("Comprar productos existente");
        JButton comprar = new JButton("Realizar compra");
        String productos[] = {"15324 Guitarra", "13213546 Piano", "10215 Clarinete"};
        JComboBox<String> listado = new JComboBox<String>(productos);
        listado.setVisible(false);
        comprar.setVisible(false);
        comprar.setBounds(150, 500, 200, 30);
        listado.setBounds(150, 450, 400, 30);
        producExist.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                listado.setVisible(true);
                comprar.setVisible(true);
                producExist.setVisible(false);
                producNuevo.setVisible(false);
                comprar.addActionListener(new ActionListener(){  
                    public void actionPerformed(ActionEvent e){
                        CualProducto = "" + listado.getItemAt(listado.getSelectedIndex());
                        cantidadcomprada = 1;
                        producExist.setVisible(true);
                        producNuevo.setVisible(true);
                        listado.setVisible(false);
                        comprar.setVisible(false);
                    }  
                });
            }  
        });
        //-----------------------------------------------------------
        //--------------------agrego las cosas a los paneles---------
        //contraseña.setLayout(null);
        //pinicio.setLayout(null);
        //pdeBodegas.setLayout(null);
        contrasena.add(usuario); contrasena.add(contrase); contrasena.add(iniciodesesion);
        pinicio.add(barradeMenuBar, BorderLayout.NORTH);
        pdeBodegas.add(scrollPane, BorderLayout.CENTER);
        pdeBodegas.add(listado);pdeBodegas.add(producNuevo);pdeBodegas.add(producExist);pdeBodegas.add(comprar);
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
