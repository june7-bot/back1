package zen.zen.uri;

public interface DogPaths {
    /**
     * root : zuul server에서 routing 시 사용되는 prefix.
     */
    public static final String ROOT = "/api";

    /**
     * V1 : root 를 제외한 실제 path
     */
    public static interface Dog {

        public static final String PATH = ROOT + "/dog";

        public static interface register {
            public static final String REGISTER = Dog.PATH + "/register";
        }


        public static interface list {
            public static final String LIST = Dog.PATH + "/list";
        }
        public static interface photo {
            public static final String PHOTO = Dog.PATH + "/photo";
        }

    }
    public static interface nose {
        public static final String NOSE = Dog.PATH + "/nose";
    }
    public static interface one {
        public static final String ONE = Dog.PATH + "/one";
    }

}
