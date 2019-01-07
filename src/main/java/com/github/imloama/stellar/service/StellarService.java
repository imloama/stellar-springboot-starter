package com.github.imloama.stellar.service;


import com.github.imloama.stellar.config.StellarConfiguration;
import com.github.imloama.stellar.vm.TransactionResultVM;
import lombok.Data;
import org.springframework.util.StringUtils;
import org.stellar.sdk.Network;
import org.stellar.sdk.Server;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.xdr.TransactionResult;
import org.stellar.sdk.xdr.TransactionResultCode;
import org.stellar.sdk.xdr.XdrDataInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

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

    public KeyPair random(){
        return KeyPair.random();
    }

    public TransactionResultVM txResult(TransactionResult result){
        return TransactionResultVM.fromTransactionResult(result);
    }

    public TransactionResultVM txResult(String xdr) throws IOException {
        byte[] bytes = Base64.getDecoder().decode(xdr.getBytes("UTF-8"));
        TransactionResult res = TransactionResult.decode(new XdrDataInputStream(new ByteArrayInputStream(bytes)));
        return this.txResult(res);
    }










}
