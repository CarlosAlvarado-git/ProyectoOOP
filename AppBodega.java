import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList; 

public class AppBodega {
    static String usu = ""; static String contra = "";
    static int NoBodega = 0;
    static String IdProducto = "";
    static int cantidadcomprada = 0;
    static int cantidadventa = 0;
    static int NoBodegaCambio = 0;
    static String datos[][] = new String[1000][3];
    static String encabezados[] = new String[3];
    static Producto nuevoP;
    static LinkedList<Bodega> MisBodegas = new LinkedList<Bodega>();
    public static void main(String [] arg){
        //variables principales
        
        //
        //Creo mis bodegas y las lleno
        MySQL MiBaseDeDatos = new MySQL();
        MiBaseDeDatos.cargarBodegas();
        MiBaseDeDatos.cargarIntrumento_percucion();
        MiBaseDeDatos.cargarCantidad();
        for(int i = 0; i < MiBaseDeDatos.Linked_Bodegas.size(); i++){
            MisBodegas.add(MiBaseDeDatos.Linked_Bodegas.get(i));
        }
        
        for(int i = 0; i < MisBodegas.size(); i++){
            MisBodegas.get(i).LlenarBodega(MiBaseDeDatos.Linked_Cantidad,MiBaseDeDatos.Linked_Instrumentos_percucion);
        }
        //Creacion de todas las variables
        JFrame app = new JFrame("Control de Bodegas");
        JPanel contrasena = new JPanel();
        JPanel pinicio = new JPanel();
        JPanel pdeBodegas = new JPanel();
        JTextField usuario = new JTextField();
        JTextField contrase = new JTextField();
        //
        JTextField Id_ = new JTextField();
        JTextField Precio_ = new JTextField();
        JTextField Marca_ = new JTextField();
        JTextField Modelo_ = new JTextField();
        JTextField Nombre_ = new JTextField();
        JTextField Material_ = new JTextField();
        JTextField Peso_ = new JTextField();
        JLabel Id_Label = new JLabel("ID del producto: ");
        JLabel Precio_Label = new JLabel("Precio del producto: ");
        JLabel Marca_Label = new JLabel("Marca del producto: ");
        JLabel Modelo_Label = new JLabel("Modelo del producto: ");
        JLabel Nombre_Label = new JLabel("Nombre del producto: ");
        JLabel Material_Label = new JLabel("Material del producto: ");
        JLabel Peso_Label = new JLabel("Peso del producto: ");
        JLabel Cantidad_Label = new JLabel("Cantidad a comprar: ");
        //
        JButton iniciodesesion = new JButton("Iniciar Sesion");
        JMenuBar barradeMenuBar = new JMenuBar();
        JMenu TodasLasBodegas = new JMenu("Bodegas");
        JMenuItem Bodega1, Bodega2, Bodega3;
        JButton BproducNuevo = new JButton("Comprar un producto nuevo");
        JButton BproducExist = new JButton("Comprar productos existente");
        JButton Cancelar = new JButton("Cancelar");
        JButton VenderProduc = new JButton("Vender producto");
        JButton PercusionBoton = new JButton("Producto de Percusion");
        JButton MoverProduc = new JButton("Mover producto a otra bodega.");
        JComboBox<String> listado = new JComboBox<String>();
        JComboBox<Integer> bodegasList = new JComboBox<Integer>();
        JComboBox<Integer> cantidades = new JComboBox<Integer>();
        cantidades.addItem(0);
        for(int y = 1; y <= 100; y++){
            cantidades.addItem(y); 
        }
        JButton ACTIVAR = new JButton("Activar");
        JButton SalirBodegas = new JButton();
        JButton Regresar = new JButton("Regresar");
        //variable o instancia de la MySQL class
        
        //obtener los encabezados para las tablas con un ciclo y query
        encabezados[0] = "ID producto"; 
        encabezados[1] = "Nombre"; 
        encabezados[2] = "Cantidad";
        JTable tabladebodega = new JTable(datos, encabezados);
        JScrollPane scrollPane = new JScrollPane(tabladebodega);
        JButton comprarN = new JButton("Avanzar");
        JButton comprarE = new JButton("Realizar compra");
        JButton venderE = new JButton("Realizar venta");
        venderE.setVisible(false);
        venderE.setBounds(1008, 190, 150, 30);
        venderE.setText("Realizar venta");
        venderE.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                IdProducto = "" + listado.getItemAt(listado.getSelectedIndex());
                cantidadventa = cantidades.getItemAt(cantidades.getSelectedIndex());
                //proceso de valdar y hacer el query. 
                BproducExist.setVisible(true);
                BproducNuevo.setVisible(true);
                VenderProduc.setVisible(true);
                MoverProduc.setVisible(true);///// aqui nooooo
                listado.setVisible(false);
                cantidades.setVisible(false);
                venderE.setVisible(false);
                Cancelar.setVisible(false);
            }  
        });
        comprarE.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                IdProducto = "" + listado.getItemAt(listado.getSelectedIndex());
                cantidadcomprada = cantidades.getItemAt(cantidades.getSelectedIndex());
                //proceso de valdar y hacer el query. 
                BproducExist.setVisible(true);
                BproducNuevo.setVisible(true);
                VenderProduc.setVisible(true);
                MoverProduc.setVisible(true);
                listado.setVisible(false);
                cantidades.setVisible(false);
                comprarE.setVisible(false);
                Cancelar.setVisible(false);
            }  
        });
        comprarN.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                //System.out.println("Boton click");
                String IdProducto_n = Id_.getText();
                String NomProducto_n = Nombre_.getText();
                double PreProducto_n = 0.0;
                if(Precio_.getText().equals("")){
                    PreProducto_n = 0.0;
                }
                else{
                    PreProducto_n = Double.valueOf(Precio_.getText());
                }
                String MarProducto_n = Marca_.getText();
                String ModProducto_n = Modelo_.getText();
                String MatProducto_n = Material_.getText();
                int PesProducto_n = 0;
                if(Peso_.getText().equals("")){
                    // alertta
                    PesProducto_n = 0;
                }
                else{
                    PesProducto_n = Integer.valueOf(Peso_.getText());
                }
                int CantProducto_n = cantidades.getItemAt(cantidades.getSelectedIndex());
                if(IdProducto_n.equals("") || NomProducto_n.equals("") || MarProducto_n.equals("")|| ModProducto_n.equals("") || MatProducto_n.equals("") || PreProducto_n == 0.0 || PesProducto_n == 0 || CantProducto_n == 0){
                    // alerta de vacios o 0
                }
                else{
                    //
                    
                    comprarN.setVisible(false);
                    Id_Label.setVisible(false);
                    Nombre_Label.setVisible(false);
                    Precio_Label.setVisible(false);
                    Marca_Label.setVisible(false);
                    Modelo_Label.setVisible(false);
                    Material_Label.setVisible(false);
                    Peso_Label.setVisible(false);
                    Cantidad_Label.setVisible(false);
                    Id_.setVisible(false);
                    Nombre_.setVisible(false);
                    Precio_.setVisible(false);
                    Marca_.setVisible(false);
                    Modelo_.setVisible(false);
                    Material_.setVisible(false);
                    Peso_.setVisible(false);
                    cantidades.setVisible(false);
                    // despues de desaparecer todo, mostramos 3 botones, uno de percusion....
                    // si le da a percusion, dos label y dos text
                    //boton de realizar compra nuevo
                    nuevoP = new Producto(IdProducto_n, PreProducto_n, MarProducto_n, ModProducto_n, NomProducto_n, MatProducto_n, PesProducto_n);
                    MisBodegas.get(NoBodega-1).nuevoProductoPercusion(nuevoP, CantProducto_n);
                    int posig = 0;
                    for(int y = 0; y < MisBodegas.get(NoBodega-1).getsizePerscusion(); y++){
                        for(int x = 0; x < 3; x++){
                            if(x == 0)
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getIdProductoPercusion(y);
                            }
                            else if(x == 1)
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getNombreProductoPercusion(y);
                            }
                            else
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getCantidadProductoPerscusion(y);
                            }
                        }
                    }
                    posig = MisBodegas.get(NoBodega-1).getsizePerscusion();
                    //aqui los demas
                    tabladebodega.setFillsViewportHeight(true);
                    Cancelar.setVisible(false);
                    BproducExist.setVisible(true);
                    BproducNuevo.setVisible(true);
                    MoverProduc.setVisible(true);
                    VenderProduc.setVisible(true);
                } 
            }  
        });
        JButton moverE = new JButton("Mover de bodega");
        moverE.setText("Mover de bodega");
        moverE.setVisible(false);
        moverE.setBounds(1008, 190, 150, 30);
        moverE.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){
                IdProducto = "" + listado.getItemAt(listado.getSelectedIndex());
                cantidadventa = cantidades.getItemAt(cantidades.getSelectedIndex());
                NoBodegaCambio = bodegasList.getItemAt(bodegasList.getSelectedIndex());
                //proceso de valdar y hacer el query. 
                BproducExist.setVisible(true);
                BproducNuevo.setVisible(true);
                VenderProduc.setVisible(true);
                MoverProduc.setVisible(true);///// aqui nooooo
                listado.setVisible(false);
                cantidades.setVisible(false);
                moverE.setVisible(false);
                bodegasList.setVisible(false);
                Cancelar.setVisible(false);
            }  
        });
        PercusionBoton.setBounds(450, 300, 150, 30);
        PercusionBoton.setVisible(false);
        PercusionBoton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            }
        });
        //---------- Modificación de paneles y frame
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
                if(MisBodegas.get(NoBodega-1).getsizePerscusion() == 0 /* || si tiene 0 en cuerdas y viento*/){
                    for(int y = 0; y < 100; y++){
                        for(int x = 0; x < 3; x++){
                            datos[y][x] = "";
                        }
                    }
                }
                else{
                    int posig = 0;
                    for(int y = 0; y < MisBodegas.get(NoBodega-1).getsizePerscusion(); y++){
                        for(int x = 0; x < 3; x++){
                            if(x == 0)
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getIdProductoPercusion(y);
                                //System.out.println(MisBodegas.get(NoBodega-1).getIdProducto(y));
                            }
                            else if(x == 1)
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getNombreProductoPercusion(y);
                               // System.out.println(MisBodegas.get(NoBodega-1).getNombreProducto(y));
                            }
                            else
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getCantidadProductoPerscusion(y);
                                //System.out.println(MisBodegas.get(NoBodega-1).getCantidadProducto(y));
                            }
                        }
                    }
                    posig = MisBodegas.get(NoBodega-1).getsizePerscusion();
                    /*
                        Otro ciclo donde la "y" den datos[y], cambiaria a ser posig, para continuar
                    */
                }
                
                /*for(int y = 0; y < MisBodegas.get(NoBodega-1).getsizeProductos(); y++){
                    System.out.println(MisBodegas.get(NoBodega-1).getIdProducto(y));
                }*/
                bodegasList.removeAllItems();
                bodegasList.addItem(0);
                bodegasList.addItem(2);
                bodegasList.addItem(3);
                //Query para obtener los ID de los productos 
                
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
                bodegasList.removeAllItems();
                bodegasList.addItem(0);
                bodegasList.addItem(1);
                bodegasList.addItem(3);
                if(MisBodegas.get(NoBodega-1).getsizeProductos() == 0){
                    for(int y = 0; y < 100; y++){
                        for(int x = 0; x < 3; x++){
                            datos[y][x] = "";
                        }
                    }
                }
                else{
                    for(int y = 0; y < MisBodegas.get(NoBodega-1).getsizeProductos(); y++){
                        for(int x = 0; x < 3; x++){
                            if(x == 0)
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getIdProducto(y);
                            }
                            else if(x == 1)
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getNombreProducto(y);
                            }
                            else
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getCantidadProducto(y);
                            }
                        }
                    }
                }
                for(int y = 0; y < MisBodegas.get(NoBodega-1).getsizeProductos(); y++){
                    System.out.println(MisBodegas.get(NoBodega-1).getIdProducto(y));
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
                if(MisBodegas.get(NoBodega-1).getsizeProductos() == 0){
                    for(int y = 0; y < 100; y++){
                        for(int x = 0; x < 3; x++){
                            datos[y][x] = "";
                        }
                    }
                }
                else{
                    for(int y = 0; y < MisBodegas.get(NoBodega-1).getsizeProductos(); y++){
                        for(int x = 0; x < 3; x++){
                            if(x == 0)
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getIdProducto(y);
                            }
                            else if(x == 1)
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getNombreProducto(y);
                            }
                            else
                            {
                                datos[y][x] = MisBodegas.get(NoBodega-1).getCantidadProducto(y);
                            }
                        }
                    }
                }
                for(int y = 0; y < MisBodegas.get(NoBodega-1).getsizeProductos(); y++){
                    System.out.println(MisBodegas.get(NoBodega-1).getIdProducto(y));
                }
                bodegasList.removeAllItems();
                bodegasList.addItem(0);
                bodegasList.addItem(1);
                bodegasList.addItem(2);
                
            }  
        });
        TodasLasBodegas.add(Bodega1);TodasLasBodegas.add(Bodega2);TodasLasBodegas.add(Bodega3);
        barradeMenuBar.add(TodasLasBodegas);
        //-----------------------------------------------------------
        //---------------parte de bodegas----------------------------
        scrollPane.setBounds(0,0,400,400);
        scrollPane.setVisible(false);
        //botones
        BproducExist.setBounds(810, 150, 350, 30);
        BproducNuevo.setBounds(450, 150, 350, 30);
        VenderProduc.setBounds(810, 185, 350,30);
        MoverProduc.setBounds(450, 185, 350, 30);
        BproducExist.setVisible(false);
        Cancelar.setVisible(false);
        BproducNuevo.setVisible(false);
        MoverProduc.setVisible(false);
        VenderProduc.setVisible(false);
        listado.setVisible(false);
        bodegasList.setVisible(false);
        cantidades.setVisible(false);
        comprarN.setVisible(false);
        comprarE.setVisible(false);
        bodegasList.setBounds(1008,190,150,30);
        comprarE.setBounds(1008, 190, 150, 30);
        listado.setBounds(745, 190, 130, 30);
        comprarN.setBounds(450, 500, 150, 30);

        
        //label
        Id_Label.setBounds(450,190,150,30);
        Nombre_Label.setBounds(450,225,150,30);
        Precio_Label.setBounds(450,260,150,30);
        Marca_Label.setBounds(450,295,150,30);
        Modelo_Label.setBounds(450,330,150,30);
        Material_Label.setBounds(450,365,150,30);
        Peso_Label.setBounds(450,400,150,30);
        Cantidad_Label.setBounds(450,435,150,30);
        Id_Label.setVisible(false);
        Nombre_Label.setVisible(false);
        Precio_Label.setVisible(false);
        Marca_Label.setVisible(false);
        Modelo_Label.setVisible(false);
        Material_Label.setVisible(false);
        Peso_Label.setVisible(false);
        Cantidad_Label.setVisible(false);
        //text
        Id_.setBounds(610,190,150,30);
        Nombre_.setBounds(610,225,150,30);
        Precio_.setBounds(610,260,150,30);
        Marca_.setBounds(610,295,150,30);
        Modelo_.setBounds(610,330,150,30);
        Material_.setBounds(610,365,150,30);
        Peso_.setBounds(610,400,150,30);
        Id_.setVisible(false);
        Nombre_.setVisible(false);
        Precio_.setVisible(false);
        Marca_.setVisible(false);
        Modelo_.setVisible(false);
        Material_.setVisible(false);
        Peso_.setVisible(false);
        //
        BproducExist.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                listado.removeAllItems();
                listado.addItem(" ");
                for(int y = 0; y < MisBodegas.get(NoBodega-1).getsizeProductos(); y++){
                    listado.addItem(datos[y][0]);
                }
                listado.setVisible(true);
                listado.setBounds(745, 190, 130, 30);
                listado.setSelectedItem(" ");
                cantidades.setBounds(878, 190, 130, 30);
                cantidades.setVisible(true);
                cantidades.setSelectedItem(0);
                comprarE.setVisible(true);
                BproducExist.setVisible(false);
                BproducNuevo.setVisible(false);
                VenderProduc.setVisible(false);
                MoverProduc.setVisible(false);
                Cancelar.setBounds(1008, 220, 150, 30);
                Cancelar.setVisible(true);
                comprarE.setBounds(1008, 190, 150, 30);
                comprarE.setText("Realizar compra");
            }  
        });
        BproducNuevo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Cancelar.setBounds(610, 500, 150, 30);
                Cancelar.setVisible(true);
                Id_Label.setVisible(true);
                Nombre_Label.setVisible(true);
                Precio_Label.setVisible(true);
                Marca_Label.setVisible(true);
                Modelo_Label.setVisible(true);
                Material_Label.setVisible(true);
                Peso_Label.setVisible(true);
                Cantidad_Label.setVisible(true);
                Id_.setVisible(true);
                Id_.setText("");
                Nombre_.setVisible(true);
                Nombre_.setText("");
                Precio_.setVisible(true);
                Precio_.setText("");
                Marca_.setVisible(true);
                Marca_.setText("");
                Modelo_.setVisible(true);
                Modelo_.setText("");
                Material_.setVisible(true);
                Material_.setText("");
                Peso_.setVisible(true);
                Peso_.setText("");
                comprarN.setVisible(true);
                BproducExist.setVisible(false);
                BproducNuevo.setVisible(false);
                MoverProduc.setVisible(false);
                VenderProduc.setVisible(false);
                cantidades.setBounds(610, 435, 150, 30);
                cantidades.setVisible(true);
                cantidades.setSelectedItem(0);
            }
        });
        VenderProduc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                listado.removeAllItems();
                listado.addItem(" ");
                for(int y = 0; y < MisBodegas.get(NoBodega-1).getsizeProductos(); y++){
                    listado.addItem(datos[y][0]);
                }
                listado.setVisible(true);
                listado.setSelectedItem(" ");
                cantidades.setBounds(878, 190, 130, 30);
                cantidades.setVisible(true);
                cantidades.setSelectedItem(0);
                venderE.setVisible(true);
                BproducExist.setVisible(false);
                BproducNuevo.setVisible(false);
                VenderProduc.setVisible(false);
                MoverProduc.setVisible(false);
                Cancelar.setBounds(1008, 220, 150, 30);
                Cancelar.setVisible(true);
            }
        });
        MoverProduc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                listado.removeAllItems();
                listado.addItem(" ");
                for(int y = 0; y < MisBodegas.get(NoBodega-1).getsizeProductos(); y++){
                    listado.addItem(datos[y][0]);
                }
                listado.setVisible(true);
                listado.setSelectedItem(" ");
                bodegasList.setSelectedItem(0);
                bodegasList.setVisible(true);
                cantidades.setBounds(878, 190, 130, 30);
                cantidades.setVisible(true);
                cantidades.setSelectedItem(0);
                moverE.setVisible(true);
                moverE.setBounds(1008, 220, 150, 30);
                BproducExist.setVisible(false);
                BproducNuevo.setVisible(false);
                VenderProduc.setVisible(false);
                MoverProduc.setVisible(false);
                Cancelar.setBounds(1008, 250, 150, 30);
                Cancelar.setVisible(true);
            } 
        });
        Cancelar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(comprarE.isVisible()){
                    comprarE.setVisible(false);
                    listado.setVisible(false);
                    bodegasList.setVisible(false);
                    cantidades.setVisible(false);
                    BproducExist.setVisible(true);
                    BproducNuevo.setVisible(true);
                    MoverProduc.setVisible(true);
                    VenderProduc.setVisible(true);
                    Cancelar.setVisible(false);
                }
                else if(comprarN.isVisible()){
                    comprarN.setVisible(false);
                    Id_Label.setVisible(false);
                    Nombre_Label.setVisible(false);
                    Precio_Label.setVisible(false);
                    Marca_Label.setVisible(false);
                    Modelo_Label.setVisible(false);
                    Material_Label.setVisible(false);
                    Peso_Label.setVisible(false);
                    Cantidad_Label.setVisible(false);
                    Id_.setVisible(false);
                    Nombre_.setVisible(false);
                    Precio_.setVisible(false);
                    Marca_.setVisible(false);
                    Modelo_.setVisible(false);
                    Material_.setVisible(false);
                    Peso_.setVisible(false);
                    BproducExist.setVisible(true);
                    BproducNuevo.setVisible(true);
                    MoverProduc.setVisible(true);
                    VenderProduc.setVisible(true);
                    cantidades.setVisible(false);
                    Cancelar.setVisible(false);
                }
                else if(venderE.isVisible()){
                    venderE.setVisible(false);
                    listado.setVisible(false);
                    bodegasList.setVisible(false);
                    cantidades.setVisible(false);
                    BproducExist.setVisible(true);
                    BproducNuevo.setVisible(true);
                    MoverProduc.setVisible(true);
                    VenderProduc.setVisible(true);
                    Cancelar.setVisible(false);
                }
                else if(moverE.isVisible()){
                    moverE.setVisible(false);
                    listado.setVisible(false);
                    bodegasList.setVisible(false);
                    cantidades.setVisible(false);
                    BproducExist.setVisible(true);
                    BproducNuevo.setVisible(true);
                    MoverProduc.setVisible(true);
                    VenderProduc.setVisible(true);
                    Cancelar.setVisible(false);
                }
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
                Regresar.setVisible(true);
                scrollPane.setVisible(false);
                SalirBodegas.setVisible(false);
                MoverProduc.setVisible(false);
                VenderProduc.setVisible(false);
                BproducNuevo.setVisible(false);
                BproducExist.setVisible(false);
                comprarE.setVisible(false);
                venderE.setVisible(false);
                comprarN.setVisible(false);
                listado.setVisible(false);
                bodegasList.setVisible(false);
                cantidades.setVisible(false);
                Id_Label.setVisible(false);
                Nombre_Label.setVisible(false);
                Precio_Label.setVisible(false);
                Marca_Label.setVisible(false);
                Modelo_Label.setVisible(false);
                Material_Label.setVisible(false);
                Peso_Label.setVisible(false);
                Cantidad_Label.setVisible(false);
                Id_.setVisible(false);
                Nombre_.setVisible(false);
                Precio_.setVisible(false);
                Marca_.setVisible(false);
                Modelo_.setVisible(false);
                Material_.setVisible(false);
                Peso_.setVisible(false);
                Cancelar.setVisible(false);
                moverE.setVisible(false);
            }
        });
        ACTIVAR.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                BproducExist.setVisible(true);
                BproducNuevo.setVisible(true);
                MoverProduc.setVisible(true);
                VenderProduc.setVisible(true);
                ACTIVAR.setVisible(false);
                Regresar.setVisible(false);
                scrollPane.setVisible(true);
                SalirBodegas.setVisible(true);
                listado.removeAllItems();
                listado.addItem(" ");
                int posig = 0;
                for(int y = 0; y < MisBodegas.get(NoBodega-1).getsizePerscusion(); y++){
                    listado.addItem(datos[y][0]);
                }
                /*otro for con el posing en la y de datos, para optener los de viendo  y cuerdas */
            }
        });
        Regresar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                pdeBodegas.setVisible(false);
                pinicio.setVisible(true);
                app.setContentPane(pinicio);
                ACTIVAR.setVisible(true);
                Regresar.setVisible(true);
                scrollPane.setVisible(false);
                SalirBodegas.setVisible(false);
                MoverProduc.setVisible(false);
                VenderProduc.setVisible(false);
                BproducNuevo.setVisible(false);
                BproducExist.setVisible(false);
                comprarE.setVisible(false);
                venderE.setVisible(false);
                comprarN.setVisible(false);
                listado.setVisible(false);
                bodegasList.setVisible(false);
                cantidades.setVisible(false);
                Id_Label.setVisible(false);
                Nombre_Label.setVisible(false);
                Precio_Label.setVisible(false);
                Marca_Label.setVisible(false);
                Modelo_Label.setVisible(false);
                Material_Label.setVisible(false);
                Peso_Label.setVisible(false);
                Cantidad_Label.setVisible(false);
                Id_.setVisible(false);
                Nombre_.setVisible(false);
                Precio_.setVisible(false);
                Marca_.setVisible(false);
                Modelo_.setVisible(false);
                Material_.setVisible(false);
                Peso_.setVisible(false);
                Cancelar.setVisible(false);
                moverE.setVisible(false);
            }
        });
        //-----------------------------------------------------------
        //--------------------agrego las cosas a los paneles---------
        contrasena.add(usuario); contrasena.add(contrase); contrasena.add(iniciodesesion);
        pinicio.add(barradeMenuBar, BorderLayout.NORTH);
            //bodega
        pdeBodegas.add(scrollPane);
        pdeBodegas.add(Id_);pdeBodegas.add(Precio_);pdeBodegas.add(Marca_);pdeBodegas.add(Modelo_);pdeBodegas.add(Nombre_);
        pdeBodegas.add(Material_);pdeBodegas.add(Peso_);
        pdeBodegas.add(Id_Label);pdeBodegas.add(Precio_Label);pdeBodegas.add(Marca_Label);pdeBodegas.add(Modelo_Label);pdeBodegas.add(Nombre_Label);
        pdeBodegas.add(Material_Label);pdeBodegas.add(Peso_Label);pdeBodegas.add(Cantidad_Label);
        pdeBodegas.add(ACTIVAR, BorderLayout.CENTER);pdeBodegas.add(Regresar, BorderLayout.CENTER);pdeBodegas.add(bodegasList);pdeBodegas.add(venderE);pdeBodegas.add(moverE);
        pdeBodegas.add(SalirBodegas);pdeBodegas.add(cantidades);pdeBodegas.add(Cancelar);pdeBodegas.add(MoverProduc);pdeBodegas.add(VenderProduc);
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