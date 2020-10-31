package zen.zen.uri;

public interface OrderPaths {

    /**
     * root : zuul server에서 routing 시 사용되는 prefix.
     */
    public static final String ROOT = "/api";

    /**
     * V1 : root 를 제외한 실제 path
     */
    public static interface Order {

        public static final String PATH = ROOT + "/order";

        public static interface orderDog {
            public static final String ORDER = Order.PATH + "/dog";
        }

    }
}
