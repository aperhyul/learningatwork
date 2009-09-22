package test.vlc;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

import org.videolan.jvlc.JVLC;

/*****************************************************************************
 * MultipleVideosSample.java: VLC Java Bindings
 *****************************************************************************
 * Copyright (C) 1998-2008 the VideoLAN team
 *
 * Authors: Filippo Carone <filippo@carone.org>
 *
 *
 * $Id $
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111, USA.
 *****************************************************************************/

public class MultipleVideosSample
{

    private static int videosNumber = 1;
    private static String filename ="http://www.youtube.com/get_video?video_id=96QRVfto7OM&t=vjVQa1PpcFMlvKnA1mt78F0Lygm2TAYSghPnDJc_woM%3D&fmt=18";
    static String[] vlcArgs = new String[] { "-vvv", "--plugin-path=C:\\dev\\lib\\vlc\\vlc-0.9.9-win32\\vlc-0.9.9\\plugins" };
    
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {
        Frame frame = new java.awt.Frame();
        frame.setBounds(0, 0, 600, 700);
        JPanel jvcc = new JPanel();
        Canvas[] videoCanvasesArray = new Canvas[videosNumber];
        frame.add(jvcc);
        
        if (args.length > 0)
        {
            filename = args[0];
        }
        
        for (int i = 0; i < videosNumber; i++)
        {
            Canvas jvlcCanvas = new Canvas();
            videoCanvasesArray[i] = jvlcCanvas;
            jvlcCanvas.setSize(200, 200);
            jvlcCanvas.setBackground(new Color(0x0));
            jvcc.add(jvlcCanvas);
        }
        
        frame.addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent ev)
            {
                System.exit(0);
            }
        });
        
        frame.setVisible(true);
        JVLC[] jvlcArray = new JVLC[videosNumber];
        for (int i = 0; i < videosNumber; i++)
        {
            JVLC jvlc = new JVLC(vlcArgs);
            jvlcArray[i] = jvlc;
            jvlc.setVideoOutput(videoCanvasesArray[i]);
        }
        
        
        for (int i = 0; i < videosNumber; i++)
        {
            jvlcArray[i].play(filename);
            Thread.sleep(500);
        }
        
    }
    
}
