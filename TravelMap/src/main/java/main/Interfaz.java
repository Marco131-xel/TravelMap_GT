package main;

import archivo.*;
import grafos.*;
import java.io.*;
import java.awt.Color;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import datos.*;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import nodos.Rutas;

/**
 * @author marco
 */
public class Interfaz extends javax.swing.JFrame {

    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    GestionArchivos gestion = new GestionArchivos();
    Grafos g = new Grafos();
    private List<datos.Datos> listaDatos = new ArrayList<>();
    private List<Trafico> listaTrafico;

    /**
     * Creates new form Main
     */
    int xMouse, yMouse;
    private List<String> listOrigen = new ArrayList<>();
    private List<String> listDestino = new ArrayList<>();

    public Interfaz() {
        initComponents();
        // Textpane no editable
        areaDetalles.setEditable(false);
        areaDetalles.setFocusable(false);
        areaDetalles.setCursor(null);
        Resultados.setEditable(false);
        Resultados.setFocusable(false);
        Resultados.setCursor(null);
        // Listener de cambio de seleccion al JComboBox de Origen
        Corigen.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    mostrarDetallesViaje();
                }
            }
        });
        // Listener de cambio de seleccion al JComboBox de Destino
        Cdestino.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    mostrarDetallesViaje();
                }
            }
        });
        // ActionListener para el botón BT_caminar
        BT_caminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar JComboBox
                JRUTAS.removeAllItems();

                // Agregar opciones al JComboBox
                JRUTAS.addItem("La mejor ruta al desgaste");
                JRUTAS.addItem("La mejor ruta a la distancia c");
                JRUTAS.addItem("La mejor ruta al desgaste y la distancia");
            }
        });
        // ActionListener para el botón BT_vehiculo
        BT_vehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar JComboBox
                JRUTAS.removeAllItems();

                // Agregar opciones al JComboBox
                JRUTAS.addItem("La mejor ruta al combustible");
                JRUTAS.addItem("La mejor ruta a la distancia v");
                JRUTAS.addItem("La mejor ruta al combustible y la distancia");
            }
        });

        // ActionListener para el JComboBox JRUTAS
        JRUTAS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la opción seleccionada
                String opcionSeleccionada = (String) JRUTAS.getSelectedItem();

                // Calcular y mostrar las rutas
                if (opcionSeleccionada != null) {
                    switch (opcionSeleccionada) {
                        case "La mejor ruta al desgaste":
                            // Calcular y mostrar la mejor ruta en base al desgaste físico si es caminando
                            mostrarMejorRutaPorDesgasteFisico();
                            break;
                        case "La mejor ruta a la distancia c":
                            // Calcular y mostrar la mejor ruta en base a la distancia
                            mostrarMejorRutaPorDistancia();
                            break;
                        case "La mejor ruta al desgaste y la distancia":
                            // Calcular y mostrar la mejor ruta en base al desgaste físico y la distancia si es caminando
                            mostrarMejorRutaPorDesgasteFisicoYDistancia();
                            break;
                        case "La mejor ruta al combustible":
                            // Calcular y mostrar la mejor ruta en vehiculo
                            mostrarMejorRutaCombustible();
                            break;
                        case "La mejor ruta a la distancia v":
                            // Calcular y mostrar la mejor distancia en vehiculo
                            mostarMejoRutaDistanciaV();
                            break;
                        case "La mejor ruta al combustible y la distancia":
                            // Calcular y mostar la mejor ruta en combustible y distancia 
                            mostrarMejorRutaCombustibleDistancia();
                            break;
                        default:
                            // No hacer nada
                            break;
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        detalles = new javax.swing.JLabel();
        mapa = new javax.swing.JLabel();
        bt_buscar = new javax.swing.JLabel();
        bt_abrir = new javax.swing.JLabel();
        Corigen = new javax.swing.JComboBox<>();
        Cdestino = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaDetalles = new javax.swing.JTextPane();
        origen = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        mapaGrafo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lamapa = new javax.swing.JLabel();
        destino = new javax.swing.JLabel();
        bt_iniciar = new javax.swing.JPanel();
        ini = new javax.swing.JLabel();
        bt_abriDo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        BT_caminar = new javax.swing.JRadioButton();
        BT_vehiculo = new javax.swing.JRadioButton();
        JRUTAS = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        Resultados = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        detalles.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        detalles.setForeground(new java.awt.Color(255, 255, 204));
        detalles.setText("Detalles");
        jPanel1.add(detalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        mapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-mapa-64.png"))); // NOI18N
        mapa.setText("jLabel5");
        mapa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mapa.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                mapaMouseDragged(evt);
            }
        });
        mapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mapaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mapaMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mapaMousePressed(evt);
            }
        });
        jPanel1.add(mapa, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 64, -1));

        bt_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-bote-de-basura-60.png"))); // NOI18N
        bt_buscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_buscar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bt_buscarMouseDragged(evt);
            }
        });
        bt_buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_buscarMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bt_buscarMousePressed(evt);
            }
        });
        jPanel1.add(bt_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 670, 50, 60));

        bt_abrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-abrir-carpeta-50.png"))); // NOI18N
        bt_abrir.setText("jLabel4");
        bt_abrir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_abrir.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bt_abrirMouseDragged(evt);
            }
        });
        bt_abrir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_abrirMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_abrirMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bt_abrirMousePressed(evt);
            }
        });
        jPanel1.add(bt_abrir, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 13, 53, -1));

        Corigen.setBackground(new java.awt.Color(255, 255, 204));
        Corigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorigenActionPerformed(evt);
            }
        });
        jPanel1.add(Corigen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 150, -1));

        Cdestino.setBackground(new java.awt.Color(255, 255, 204));
        Cdestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CdestinoActionPerformed(evt);
            }
        });
        jPanel1.add(Cdestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 150, -1));

        jScrollPane3.setViewportView(areaDetalles);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 230, 150));

        origen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        origen.setForeground(new java.awt.Color(255, 255, 255));
        origen.setText("Origen");
        jPanel1.add(origen, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/xd.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century", 1, 34)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 127, 0));
        jLabel2.setText("Travel Map GT");

        lamapa.setFont(new java.awt.Font("Liberation Sans", 1, 24)); // NOI18N
        lamapa.setForeground(new java.awt.Color(0, 127, 0));
        lamapa.setText("Mapa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lamapa, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(34, 34, 34))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mapaGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, 1227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lamapa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapaGrafo, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(252, -3, 1250, 760));

        destino.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        destino.setForeground(new java.awt.Color(255, 255, 255));
        destino.setText("Destino");
        jPanel1.add(destino, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 81, -1));

        bt_iniciar.setBackground(new java.awt.Color(51, 153, 255));
        bt_iniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_iniciar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bt_iniciarMouseDragged(evt);
            }
        });
        bt_iniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_iniciarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bt_iniciarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bt_iniciarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bt_iniciarMousePressed(evt);
            }
        });

        ini.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ini.setForeground(new java.awt.Color(255, 255, 204));
        ini.setText("Iniciar");

        javax.swing.GroupLayout bt_iniciarLayout = new javax.swing.GroupLayout(bt_iniciar);
        bt_iniciar.setLayout(bt_iniciarLayout);
        bt_iniciarLayout.setHorizontalGroup(
            bt_iniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bt_iniciarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(ini)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        bt_iniciarLayout.setVerticalGroup(
            bt_iniciarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bt_iniciarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ini)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(bt_iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 700, -1, -1));

        bt_abriDo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-abrir-carpeta-50.png"))); // NOI18N
        bt_abriDo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_abriDo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bt_abriDoMouseDragged(evt);
            }
        });
        bt_abriDo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_abriDoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bt_abriDoMousePressed(evt);
            }
        });
        jPanel1.add(bt_abriDo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 50, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Abrir Rutas");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mapa");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Abrir Trafico");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        BT_caminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BT_caminar.setForeground(new java.awt.Color(255, 255, 255));
        BT_caminar.setText("Caminar");
        BT_caminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_caminarActionPerformed(evt);
            }
        });
        jPanel1.add(BT_caminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));

        BT_vehiculo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BT_vehiculo.setForeground(new java.awt.Color(255, 255, 255));
        BT_vehiculo.setText("Vehiculo");
        jPanel1.add(BT_vehiculo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 460, -1, -1));

        jPanel1.add(JRUTAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 220, -1));

        jScrollPane1.setViewportView(Resultados);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 220, 140));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Funcion para abrir el primer archivo
    public void leerArchivo(String contenido) {
        try {
            // Abre el archivo
            listaDatos = new ArrayList<>();
            // Dividir el  contenido del arhivo en lineas
            String[] lineas = contenido.split("\n");
            // Leer cada linea del archivo
            for (String linea : lineas) {
                //System.out.println("Linea leida: " + linea);
                // Divide la linea en partes utilizando "|" como delimitador
                String[] partes = linea.split("\\|");
                // Extrae los datos de cada parte
                String origen = partes[0];
                String destino = partes[1];
                int tiempoVehiculo = Integer.parseInt(partes[2]);
                int tiempoPie = Integer.parseInt(partes[3]);
                int consumoGas = Integer.parseInt(partes[4]);
                int desgastePersona = Integer.parseInt(partes[5]);
                int distancia = Integer.parseInt(partes[6]);
                // Agregar los lugares a las listas
                if (!listOrigen.contains(origen)) {
                    listOrigen.add(origen);
                }
                if (!listDestino.contains(destino)) {
                    listDestino.add(destino);
                }

                // Creamos objetos
                Datos datos = new Datos(origen, destino, tiempoVehiculo, tiempoPie, consumoGas, desgastePersona, distancia);
                listaDatos.add(datos);
            }

            // agregar los lugares a mi Jcombox 
            Corigen.setModel(new DefaultComboBoxModel<>(listOrigen.toArray(new String[0])));
            Cdestino.setModel(new DefaultComboBoxModel<>(listDestino.toArray(new String[0])));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Funcion para abrir el segundo archivo 
    public void leerArchiDos(String contenido) {
        try {
            // analisis del archivo
            listaTrafico = new ArrayList<>();
            String[] lineas = contenido.split("\n");
            // Leer cada linea del archivo 
            for (String linea : lineas) {
                // Divide la linea en partes utilizando "|" como delimitador
                String[] partes = linea.split("\\|");
                // Extrae los datos de cada parte
                String torigen = partes[0];
                String tdestino = partes[1];
                int hinicio = Integer.parseInt(partes[2]);
                int hllegada = Integer.parseInt(partes[3]);
                int probabilidad = Integer.parseInt(partes[4]);

                // Creamos objetos
                Trafico trafico = new Trafico(torigen, tdestino, hinicio, hllegada, probabilidad);
                listaTrafico.add(trafico);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Configurando COMBO BOX
    private void mostrarDetallesViaje() {
        String origenSelect = (String) Corigen.getSelectedItem();
        String destinoSelect = (String) Cdestino.getSelectedItem();

        // Crear una instancia de la clase Rutas con la lista de datos
        Rutas rutas = new Rutas(listaDatos);
        // Llamar a la función recorreRutas para encontrar todas las rutas desde el origen seleccionado hasta el destino seleccionado
        Map<String, List<List<Datos>>> todasLasRutas = rutas.recorreRutas(origenSelect, destinoSelect);

        // Construir el texto para mostrar en el JTextPane
        StringBuilder detalles = new StringBuilder();
        detalles.append("      --Viajes Disponibles--\n");
        detalles.append("Rutas desde: ").append(origenSelect).append("\nhasta: ").append(destinoSelect).append("\n");

        // Mostrar las rutas en el JTextPane
        for (Map.Entry<String, List<List<Datos>>> entry : todasLasRutas.entrySet()) {
            String destino = entry.getKey();
            List<List<Datos>> rutasHaciaDestino = entry.getValue();
            //detalles.append("Rutas hacia ").append(destino).append(":\n");
            for (List<Datos> ruta : rutasHaciaDestino) {
                detalles.append("\nRuta:\n");
                String pasoAnterior = null;
                for (Datos dato : ruta) {
                    String origenRuta = dato.getOrigen();
                    String destinoRuta = dato.getDestino();
                    if (pasoAnterior != null && !origenRuta.equals(pasoAnterior)) {
                        detalles.append("-Origen: ").append(pasoAnterior).append("\n");
                        detalles.append("Destino: ").append(origenRuta).append("\n");
                    }
                    detalles.append("-Origen: ").append(origenRuta).append("\n");
                    detalles.append("Destino: ").append(destinoRuta).append("\n");
                    pasoAnterior = destinoRuta;
                }
                // Agregar datos calculados al final de la ruta
                List<Datos> rutaCombinada = rutas.combinarRutaParcial(ruta);
                for (Datos dato : rutaCombinada) {
                    detalles.append("Tiempo en vehículo: ").append(Integer.toString(dato.getTiempo_vehiculo())).append(" Horas\n");
                    detalles.append("Tiempo a pie: ").append(Integer.toString(dato.getTiempo_pie())).append(" Horas\n");
                    detalles.append("Combustible: ").append(Integer.toString(dato.getConsumo_gas())).append(" Galones\n");
                    detalles.append("Desgaste persona: ").append(Integer.toString(dato.getDesgaste_persona())).append(" Cal\n");
                    detalles.append("Distancia: ").append(Integer.toString(dato.getDistancia())).append(" Km\n");
                }
            }
            detalles.append("\n");
        }

        // Establecer el texto en el JTextPane
        areaDetalles.setText(detalles.toString());
    }

    // Creando funcion
    private void cambiarNodos() {
        String nodOrigen = (String) Corigen.getSelectedItem();
        String nodDestino = (String) Cdestino.getSelectedItem();

        // Bucle para buscar los datos de la listadatos
        for (Datos datos : listaDatos) {
            // Verficar si los datos coinciden con el origen y destino seleccionados
            if (datos.getOrigen().equals(nodOrigen) && datos.getDestino().equals(nodDestino)) {
                g.selecGrafos(nodOrigen, nodDestino);
                mostrarGrafo();
            }
        }
    }

    // Mostar Grafo osea mapa
    private void mostrarGrafo() {
        // Carga la imagen del grafo y la establece 
        ImageIcon ig = new ImageIcon("grafo.png");
        mapaGrafo.setIcon(ig);
        // vuelve a pintar 
        mapaGrafo.repaint();
        // mejorar grafo
        SetImagen(mapaGrafo, "grafo.png", this);

    }

    // Arreglando imagen 
    public void SetImagen(JLabel label, String url, JFrame ventana) {
        ImageIcon imagen = new ImageIcon(url);
        Icon icon = new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        label.setIcon(icon);
        ventana.repaint();
    }

    // funcion para limpiar datos 
    private void limpiarDatos() {
        // Limpiar el JLabel 
        mapaGrafo.setIcon(null);
        mapaGrafo.repaint();
        // Limpiar el textarea
        areaDetalles.setText("");
        Resultados.setText("");
        // Limpiar los JComboBox
        Corigen.removeAllItems();
        Cdestino.removeAllItems();
        listOrigen.clear();
        listDestino.clear();
    }

    // Función para mostrar la mejor ruta en base al desgaste físico si es caminando
    private void mostrarMejorRutaPorDesgasteFisico() {
        // Crear una instancia de la clase Rutas con la lista de datos
        String origenSelect = (String) Corigen.getSelectedItem();
        String destinoSelect = (String) Cdestino.getSelectedItem();

        Rutas rutas = new Rutas(listaDatos);
        rutas.encontrarRutas(origenSelect, destinoSelect);
        List<Datos> mejorRuta = rutas.menorDesgaste();
        if (mejorRuta != null) {
            // Mostrar los detalles de la mejor ruta
            StringBuilder detalles = new StringBuilder();
            detalles.append("Mejor Ruta por Desgaste Físico (Caminando):\n");
            detalles.append("Tiempo a pie: ").append(mejorRuta.get(0).getTiempo_pie()).append(" Horas\n");
            detalles.append("Desgaste físico: ").append(mejorRuta.get(0).getDesgaste_persona()).append(" Cal\n");
            detalles.append("Distancia: ").append(mejorRuta.get(0).getDistancia()).append(" Km\n");
            Resultados.setText(detalles.toString());
        } else {
            Resultados.setText("No se encontro ninguna ruta");
        }
    }

    // Función para mostrar la mejor ruta en base a la distancia
    private void mostrarMejorRutaPorDistancia() {
        // Implementa la lógica para calcular la mejor ruta por distancia y muestra los resultados
        String origenSelect = (String) Corigen.getSelectedItem();
        String destinoSelect = (String) Cdestino.getSelectedItem();

        Rutas rutas = new Rutas(listaDatos);
        rutas.encontrarRutas(origenSelect, destinoSelect);
        List<Datos> mejorRuta = rutas.menorDistancia();
        if (mejorRuta != null) {
            // Mostrar los detalles de la mejor ruta
            StringBuilder detalles = new StringBuilder();
            detalles.append("Mejor Ruta por menor Distancia (Caminando):\n");
            detalles.append("Tiempo a pie: ").append(mejorRuta.get(0).getTiempo_pie()).append(" Horas\n");
            detalles.append("Desgaste físico: ").append(mejorRuta.get(0).getDesgaste_persona()).append(" Cal\n");
            detalles.append("Distancia: ").append(mejorRuta.get(0).getDistancia()).append(" Km\n");
            Resultados.setText(detalles.toString());
        } else {
            Resultados.setText("No se encontro ninguna ruta");
        }
    }

    // Función para mostrar la mejor ruta en base al desgaste físico y la distancia si es caminando
    private void mostrarMejorRutaPorDesgasteFisicoYDistancia() {
        // Implementa la lógica para calcular la mejor ruta por desgaste físico y distancia si es caminando y muestra los resultados en RESULTADOS
        String origenSelect = (String) Corigen.getSelectedItem();
        String destinoSelect = (String) Cdestino.getSelectedItem();

        Rutas rutas = new Rutas(listaDatos);
        rutas.encontrarRutas(origenSelect, destinoSelect);
        List<Datos> mejorRuta = rutas.menorDistanciaYDesgaste();
        if (mejorRuta != null) {
            // Mostrar los detalles de la mejor ruta
            StringBuilder detalles = new StringBuilder();
            detalles.append("Mejor Ruta por menor Distancia y Desgaste Fisico (Caminando):\n");
            detalles.append("Tiempo a pie: ").append(mejorRuta.get(0).getTiempo_pie()).append(" Horas\n");
            detalles.append("Desgaste físico: ").append(mejorRuta.get(0).getDesgaste_persona()).append(" Cal\n");
            detalles.append("Distancia: ").append(mejorRuta.get(0).getDistancia()).append(" Km\n");
            Resultados.setText(detalles.toString());
        } else {
            Resultados.setText("No se encontro ninguna ruta");
        }
    }

    // Funcion para mostrar la mejor ruta en base al combustible
    private void mostrarMejorRutaCombustible() {
        String origenSelect = (String) Corigen.getSelectedItem();
        String destinoSelect = (String) Cdestino.getSelectedItem();

        Rutas rutas = new Rutas(listaDatos);
        rutas.encontrarRutas(origenSelect, destinoSelect);
        List<Datos> mejorRuta = rutas.menorCombustible();
        if (mejorRuta != null) {
            // Mostrar los detalles de la mejor ruta
            StringBuilder detalles = new StringBuilder();
            detalles.append("Mejor Ruta por menor Combustible (Vehiculo):\n");
            detalles.append("Tiempo en Vehiculo: ").append(mejorRuta.get(0).getTiempo_vehiculo()).append(" Horas\n");
            detalles.append("Combustible: ").append(mejorRuta.get(0).getConsumo_gas()).append(" Galones\n");
            detalles.append("Distancia: ").append(mejorRuta.get(0).getDistancia()).append(" Km\n");
            Resultados.setText(detalles.toString());
        } else {
            Resultados.setText("No se encontro ninguna ruta");
        }
    }

    // Funcion para mostrar la mejor ruta en distancia para vehiculo
    private void mostarMejoRutaDistanciaV() {
        String origenSelect = (String) Corigen.getSelectedItem();
        String destinoSelect = (String) Cdestino.getSelectedItem();

        Rutas rutas = new Rutas(listaDatos);
        rutas.encontrarRutas(origenSelect, destinoSelect);
        List<Datos> mejorRuta = rutas.menorDistancia();
        if (mejorRuta != null) {
            // Mostrar los detalles de la mejor ruta
            StringBuilder detalles = new StringBuilder();
            detalles.append("Mejor Ruta por menor Distancia (Vehiculo):\n");
            detalles.append("Tiempo en Vehiculo: ").append(mejorRuta.get(0).getTiempo_vehiculo()).append(" Horas\n");
            detalles.append("Combustible: ").append(mejorRuta.get(0).getConsumo_gas()).append(" Galones\n");
            detalles.append("Distancia: ").append(mejorRuta.get(0).getDistancia()).append(" Km\n");
            Resultados.setText(detalles.toString());
        } else {
            Resultados.setText("No se encontro ninguna ruta");
        }
    }

    // Funcion para mostrar la mejor ruta en base combustible y distancia 
    private void mostrarMejorRutaCombustibleDistancia() {
        String origenSelect = (String) Corigen.getSelectedItem();
        String destinoSelect = (String) Cdestino.getSelectedItem();

        Rutas rutas = new Rutas(listaDatos);
        rutas.encontrarRutas(origenSelect, destinoSelect);
        List<Datos> mejorRuta = rutas.menorDistanciaYCombustible();
        if (mejorRuta != null) {
            // Mostrar los detalles de la mejor ruta
            StringBuilder detalles = new StringBuilder();
            detalles.append("Mejor Ruta por menor Distancia y Combustible (Vehiculo):\n");
            detalles.append("Tiempo en Vehiculo: ").append(mejorRuta.get(0).getTiempo_vehiculo()).append(" Horas\n");
            detalles.append("Combustible: ").append(mejorRuta.get(0).getConsumo_gas()).append(" Galones\n");
            detalles.append("Distancia: ").append(mejorRuta.get(0).getDistancia()).append(" Km\n");
            Resultados.setText(detalles.toString());
        } else {
            Resultados.setText("No se encontro ninguna ruta");
        }

    }

    // Funciones de todos lo botones 
    private void bt_iniciarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_iniciarMousePressed
        // TODO add your handling code here
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_bt_iniciarMousePressed

    private void bt_iniciarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_iniciarMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_bt_iniciarMouseDragged

    private void bt_iniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_iniciarMouseClicked
        // TODO add your handling code here:
        cambiarNodos();
        System.out.println("hubo click");
    }//GEN-LAST:event_bt_iniciarMouseClicked

    private void bt_iniciarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_iniciarMouseEntered
        // TODO add your handling code here:
        bt_iniciar.setBackground(Color.BLUE);
    }//GEN-LAST:event_bt_iniciarMouseEntered

    private void bt_iniciarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_iniciarMouseExited
        // TODO add your handling code here:
        bt_iniciar.setBackground(new Color(51, 153, 255));
    }//GEN-LAST:event_bt_iniciarMouseExited

    // BOTON BUSCAR
    private void bt_buscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_buscarMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_bt_buscarMousePressed

    private void bt_buscarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_buscarMouseDragged
        // TODO add your handling code here:
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_bt_buscarMouseDragged

    private void bt_buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_buscarMouseClicked
        // TODO add your handling code here:
        //JOptionPane.showMessageDialog(null, "valor buscar", "Buscar", JOptionPane.INFORMATION_MESSAGE);
        limpiarDatos();
    }//GEN-LAST:event_bt_buscarMouseClicked

    // BOTON ABRIR
    private void bt_abrirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_abrirMouseEntered

    }//GEN-LAST:event_bt_abrirMouseEntered

    private void bt_abrirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_abrirMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_bt_abrirMousePressed

    private void bt_abrirMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_abrirMouseDragged
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_bt_abrirMouseDragged

    private void bt_abrirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_abrirMouseClicked
        // TODO add your handling code here:
        if (seleccionado.showDialog(null, "Abrir Archivo") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionado.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {
                    String contenido = gestion.Abrir(archivo);
                    // Agregamos el lugar donde se manda el archivo
                    leerArchivo(contenido);
                    g.generarGrafo(contenido);
                    mostrarDetallesViaje();
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese el archivo de entrada");
                }
            }
        }
    }//GEN-LAST:event_bt_abrirMouseClicked

    private void mapaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapaMouseClicked
        // TODO add your handling code here:
        mostrarGrafo();
    }//GEN-LAST:event_mapaMouseClicked

    private void mapaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mapaMouseEntered

    private void mapaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapaMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_mapaMousePressed

    private void mapaMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mapaMouseDragged
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_mapaMouseDragged

    private void CdestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CdestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CdestinoActionPerformed

    private void CorigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CorigenActionPerformed

    // FUNCIONES PARA TRABAJAR CON EL SEGUNDO BOTON 
    private void bt_abriDoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_abriDoMouseClicked
        // TODO add your handling code here:
        if (seleccionado.showDialog(null, "Abrir Segundo Archivo") == JFileChooser.APPROVE_OPTION) {
            archivo = seleccionado.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {
                    String contenido = gestion.Abrir(archivo);
                    // Agregamos el segundo archivo de entrada 
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese el archivo de entrada");
                }
            }
        }
    }//GEN-LAST:event_bt_abriDoMouseClicked

    private void bt_abriDoMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_abriDoMouseDragged
        // TODO add your handling code here:
        int x = evt.getX();
        int y = evt.getY();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_bt_abriDoMouseDragged

    private void bt_abriDoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_abriDoMousePressed
        // TODO add your handling code here:
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_bt_abriDoMousePressed

    private void BT_caminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_caminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BT_caminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BT_caminar;
    private javax.swing.JRadioButton BT_vehiculo;
    private javax.swing.JComboBox<String> Cdestino;
    private javax.swing.JComboBox<String> Corigen;
    private javax.swing.JComboBox<String> JRUTAS;
    private javax.swing.JTextPane Resultados;
    private javax.swing.JTextPane areaDetalles;
    private javax.swing.JLabel bt_abriDo;
    private javax.swing.JLabel bt_abrir;
    private javax.swing.JLabel bt_buscar;
    private javax.swing.JPanel bt_iniciar;
    private javax.swing.JLabel destino;
    private javax.swing.JLabel detalles;
    private javax.swing.JLabel ini;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lamapa;
    private javax.swing.JLabel mapa;
    private javax.swing.JLabel mapaGrafo;
    private javax.swing.JLabel origen;
    // End of variables declaration//GEN-END:variables
}
