package zen.zen.blockchain;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.16.
 */
@SuppressWarnings("rawtypes")
public class Dog_sol_Dog extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506104b7806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063473edc0a1461003b57806365a81d7d14610171575b600080fd5b61016f6004803603606081101561005157600080fd5b8135919081019060408101602082013564010000000081111561007357600080fd5b82018360208201111561008557600080fd5b803590602001918460018302840111640100000000831117156100a757600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092959493602081019350359150506401000000008111156100fa57600080fd5b82018360208201111561010c57600080fd5b8035906020019184600183028401116401000000008311171561012e57600080fd5b91908080601f01602080910402602001604051908101604052809392919081815260200183838082843760009201919091525092955061026c945050505050565b005b61018e6004803603602081101561018757600080fd5b50356102aa565b604051808060200180602001838103835285818151815260200191508051906020019080838360005b838110156101cf5781810151838201526020016101b7565b50505050905090810190601f1680156101fc5780820380516001836020036101000a031916815260200191505b50838103825284518152845160209182019186019080838360005b8381101561022f578181015183820152602001610217565b50505050905090810190601f16801561025c5780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b6000838152602081815260409091208351909161028d9183918601906103e0565b5081516102a390600183019060208501906103e0565b5050505050565b60008181526020818152604091829020805483516002600180841615610100026000190190931604601f81018590048502820185019095528481526060948594928401928491908301828280156103425780601f1061031757610100808354040283529160200191610342565b820191906000526020600020905b81548152906001019060200180831161032557829003601f168201915b5050845460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152959750869450925084019050828280156103d05780601f106103a5576101008083540402835291602001916103d0565b820191906000526020600020905b8154815290600101906020018083116103b357829003601f168201915b5050505050905091509150915091565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282610416576000855561045c565b82601f1061042f57805160ff191683800117855561045c565b8280016001018555821561045c579182015b8281111561045c578251825591602001919060010190610441565b5061046892915061046c565b5090565b5b80821115610468576000815560010161046d56fea26469706673582212205c4ea6b67069924a5654c9e0c77abc34c4d79c8c2ec4fb948481149d4b37f16064736f6c63430007040033";

    public static final String FUNC_GETSTUDENTINFO = "getStudentInfo";

    public static final String FUNC_SETSTUDENTINFO = "setStudentInfo";

    @Deprecated
    protected Dog_sol_Dog(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Dog_sol_Dog(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Dog_sol_Dog(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Dog_sol_Dog(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Tuple2<String, String>> getStudentInfo(BigInteger _dogId) {
        final Function function = new Function(FUNC_GETSTUDENTINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_dogId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple2<String, String>>(function,
                new Callable<Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> setStudentInfo(BigInteger _dogId, String _name, String _photo) {
        final Function function = new Function(
                FUNC_SETSTUDENTINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_dogId), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Utf8String(_photo)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Dog_sol_Dog load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Dog_sol_Dog(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Dog_sol_Dog load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Dog_sol_Dog(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Dog_sol_Dog load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Dog_sol_Dog(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Dog_sol_Dog load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Dog_sol_Dog(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Dog_sol_Dog> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Dog_sol_Dog.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Dog_sol_Dog> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Dog_sol_Dog.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Dog_sol_Dog> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Dog_sol_Dog.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Dog_sol_Dog> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Dog_sol_Dog.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
