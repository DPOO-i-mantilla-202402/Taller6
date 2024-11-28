package uniandes.dpoo.swing.interfaz.mapa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import uniandes.dpoo.swing.interfaz.principal.VentanaPrincipal;
import uniandes.dpoo.swing.mundo.Restaurante;

@SuppressWarnings("serial")
public class VentanaMapa extends JFrame implements ActionListener
{
    /**
     * El comando para reconocer al radio que muestra sólo los restaurantes visitados
     */
    private static final String VISITADOS = "VISITADOS";

    /**
     * El comando para reconocer al radio que muestra todos los restaurantes
     */
    private static final String TODOS = "TODOS";

    /**
     * El panel con el mapa
     */
    private PanelMapaVisualizar panelMapa;

    /**
     * El radio button para hacer que se muestren todos los restaurantes. Si este está activo, radioVisitados debe estar inactivo.
     */
    private JRadioButton radioTodos;

    /**
     * El radio button para hacer que se muestren sólo los restaurantes visitados. Si este está activo, radioTodos debe estar inactivo.
     */
    private JRadioButton radioVisitados;

    /**
     * La referencia a la ventana principal
     */
    private VentanaPrincipal ventanaPrincipal;

    public VentanaMapa( VentanaPrincipal ventanaPrincipal, List<Restaurante> restaurantes )
    {
        this.ventanaPrincipal = ventanaPrincipal;

        setLayout(new BorderLayout());
        
        // Agrega el panel donde se muestra el mapa
        panelMapa = new PanelMapaVisualizar();
        panelMapa.actualizarMapa(restaurantes);
        add(panelMapa, BorderLayout.CENTER);

        // Agrega el panel con los RadioButtons y los configura
        JPanel panelRadios = new JPanel();
        
        panelRadios.setLayout(new GridLayout(1, 2, 20, 10));
        panelRadios.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        radioVisitados = new JRadioButton("Visitados");
        radioVisitados.setActionCommand(VISITADOS);
        radioVisitados.addActionListener(this);
        
        radioTodos = new JRadioButton("Todos");
        radioTodos.setActionCommand(TODOS);
        radioTodos.setSelected(true);
        radioTodos.addActionListener(this);
        
        radioVisitados = new JRadioButton("Visitados");
        radioVisitados.setActionCommand(VISITADOS);
        radioVisitados.addActionListener(this);
        panelRadios.add(radioVisitados);
        
        ButtonGroup grupoRadios = new ButtonGroup();
        grupoRadios.add(radioTodos);
        grupoRadios.add(radioVisitados);

        panelRadios.add(radioTodos);
        panelRadios.add(radioVisitados);
        
        panelRadios.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        add(panelRadios, BorderLayout.SOUTH);

        // Termina de configurar la ventana y la muestra
        setTitle("Mapa de Restaurantes");
        setSize(600, 400);
        pack( );
        setResizable( false );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setLocationRelativeTo( null );
    }

    @Override
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( TODOS.equals( comando ) )
        {
            panelMapa.actualizarMapa( ventanaPrincipal.getRestaurantes( true ) );
        }
        else if( VISITADOS.equals( comando ) )
        {
            panelMapa.actualizarMapa( ventanaPrincipal.getRestaurantes( false ) );
        }
    }

}
