package com.github.imloama.stellar.service;


import com.github.imloama.stellar.config.StellarConfiguration;
import lombok.Data;
import org.springframework.util.StringUtils;
import org.stellar.sdk.Network;
import org.stellar.sdk.Server;
import org.stellar.sdk.KeyPair;

@Data
public class StellarService{

    private Server server;

    public StellarService(StellarConfiguration config){
        if(!StringUtils.isEmpty(config.getNetworkPassphrase())){
            Network.use(new Network(config.getNetworkPassphrase()));
        }else if("test".equals(config.getNetwork())){
            Network.useTestNetwork();
        }else{
            Network.usePublicNetwork();
        }
        this.server = new Server(config.getHorizon());
    }

    public Server resetServer(String network,String horizon){
        if("test".equals(network)){
            Network.useTestNetwork();
        }else{
            Network.usePublicNetwork();
        }
        this.server = new Server(horizon);
        return this.server;
    }
    
    public boolean isValidAccountId(String accountid){
      try{
          KeyPair.fromAccountId(accountid);
          return true;
      }catch(Exception e){
        return false;
      }    
    }
    
    




}
