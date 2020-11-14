package zen.zen.blockchain;
import java.io.IOException;
import java.math.BigInteger;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import zen.zen.blockchain.contracts.Gas;
import zen.zen.temp.TempBlock;

@Component
public class BlockChain {

       private Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));
       private Credentials creds = Credentials.create("29adf9470f83a603e3af00b50586064e4645ab541c20aaf90ceed8f8824d8a7b");
       private Dog_sol_Dog registryContract;

     {
        try {
            registryContract = Dog_sol_Dog.deploy(web3, creds, new Gas()).send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public boolean setBlockchain(Long buyerId, String dogBirth, String dogNose) throws Exception {
        registryContract.setStudentInfo(BigInteger.valueOf(buyerId), dogBirth , dogNose).send();
          return true;
    }

    public TempBlock getBlockchain(Long id) throws Exception {


        RemoteFunctionCall<Tuple2<String, String>> studentInfo = registryContract.getStudentInfo(BigInteger.valueOf(id));
        String v1 = studentInfo.send().component1();
        String v2 = studentInfo.send().component2();

        System.out.println(v1 + "========================" + v2);
        TempBlock tempBlock = new TempBlock(id , v1, v2);
        return tempBlock;
    }


}