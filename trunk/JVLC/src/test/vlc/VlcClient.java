package test.vlc;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

import org.videolan.jvlc.JVLC;
import org.videolan.jvlc.Playlist;
import org.videolan.jvlc.VLCException;


class VLCPlayerFrame extends Frame
{

    private Playlist playlist;

    public Canvas jvcanvas;

    public VLCPlayerFrame(String[] args)
    {
        initComponents(args);
    }

    private void initComponents(String[] args)
    {

        java.awt.GridBagConstraints gridBagConstraints;

        fullScreenButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        setButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        stopButton = new javax.swing.JButton();

        jvcc = new JPanel();
        jvcanvas = new java.awt.Canvas();
        jvcanvas.setSize(200, 200);
        jvcc.add(jvcanvas);
        
        jvlc = new JVLC(args);
        
        playlist = new Playlist(jvlc);

        setLayout(new java.awt.GridBagLayout());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.CENTER;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(jvcc, gridBagConstraints);

        fullScreenButton.setText("FullScreen");
        fullScreenButton.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                fullScreenButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(fullScreenButton, gridBagConstraints);

        jTextField1.setText("file://a.avi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(jTextField1, gridBagConstraints);

        setButton.setText("Set item");
        setButton.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                setButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(setButton, gridBagConstraints);

        pauseButton.setText("Play/Pause");
        pauseButton.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                pauseButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(pauseButton, gridBagConstraints);

        stopButton.setText("Stop");
        stopButton.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                stopButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        add(stopButton, gridBagConstraints);

        pack();

    }

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        try
        {
            playlist.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        try
        {
            playlist.togglePause();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void setButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        try
        {
            jvlc.setVideoOutput(jvcanvas);
            playlist.add(jTextField1.getText(), "a.avi");
            playlist.play();
        }
        catch (VLCException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void fullScreenButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
         //jvlc.fullScreen();
    	
    }

    private javax.swing.JButton setButton;

    private javax.swing.JButton pauseButton;

    private javax.swing.JButton stopButton;

    private javax.swing.JButton fullScreenButton;

    private javax.swing.JTextField jTextField1;

    private JPanel jvcc;

    public JVLC jvlc;
    // MediaControlInstance mci;

}


public class VlcClient
{

    public static void main(String[] args)
    {
        Frame f = new VLCPlayerFrame(args);
        f.setBounds(0, 0, 500, 500);
        f.addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent ev)
            {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }
}
