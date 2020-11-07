package zen.zen.service;

import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

@Service
public class EthereumService {

    public void callEther() {
        System.out.println("connecting");
        Web3j web3j = Web3j.build(new HttpService("http://localhost:7545"));
        System.out.println("success connecting");

  try{
      Web3ClientVersion clientVersion = web3j.web3ClientVersion().send();
      EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
      EthGasPrice gasPrice = web3j.ethGasPrice().send();

      System.out.println("client version :" +clientVersion.getWeb3ClientVersion());
      System.out.println("block number :" +blockNumber.getBlockNumber());
      System.out.println("gas price:" +gasPrice.getGasPrice());
  }
  catch (IOException ex){
      throw new RuntimeException("error", ex);
  }}
    }

