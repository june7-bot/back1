package zen.zen.blockchain.contracts;

import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;

public class Gas  extends StaticGasProvider {

    public static final BigInteger GAS_LIMIT = BigInteger.valueOf(4_000_000);
    public static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);


    public Gas() {
        super(GAS_PRICE, GAS_LIMIT);
    }
}
