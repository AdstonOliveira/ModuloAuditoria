/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerSide.Threads;

import ServerSide.Model.ServerBlockchainSocket;
import java.io.ObjectOutputStream;

/**
 *
 * @author adston
 */
public class ThSenderServer implements Runnable{
    private ServerBlockchainSocket sbs;
    
    public ThSenderServer(ServerBlockchainSocket sbs){
        this.sbs = sbs;
    }
    
    @Override
    public void run() {
        
    }


    
    
}
