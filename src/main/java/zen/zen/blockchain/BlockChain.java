package zen.zen.blockchain;
import java.math.BigInteger;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import zen.zen.blockchain.contracts.Gas;
import zen.zen.temp.TempBlock;

@Component
public class BlockChain {



       private Web3j web3 = Web3j.build(new HttpService("http://3.35.3.31:8545"));
       private Credentials creds = Credentials.create("0x7cd2ccca1333e4b9b5f417aca14cd177c88332613babba30625e511a1395c662");
       private Dog_sol_Dog registryContract;

        {
            try {
                System.out.println("==========Contract Connection==============");
                registryContract = Dog_sol_Dog.load("0x4d787D8D9b981424E5f0Ef270E18d0a3527e4CC7",web3, creds, new Gas());
            } catch (Exception e) {
                System.out.println("==========Contract  fail Connection==============");
                e.printStackTrace();
            }
        }



    public boolean setBlockchain(Long buyerId, String dogBirth, String dogNose) throws Exception {
        System.out.println("===================================setoutblockchain = " + buyerId);
        registryContract.setStudentInfo(BigInteger.valueOf(buyerId), dogBirth , dogNose).send();
          return true;
    }

    public TempBlock getBlockchain(Long id) throws Exception {
        System.out.println("===============GETBLOCKCHECK===================================" + id);
        RemoteFunctionCall<Tuple2<String, String>> studentInfo = registryContract.getStudentInfo(BigInteger.valueOf(id));

        String v1 = studentInfo.send().component1();
        System.out.println("======================check v1 = " + v1);

        String v2 = studentInfo.send().component2();

        TempBlock tempBlock = new TempBlock(id , v1, v2);
        return tempBlock;
    }


}
