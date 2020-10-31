package zen.zen.uri;

public interface AdminPaths {

    /**
     * root : zuul server에서 routing 시 사용되는 prefix.
     */
    public static final String ROOT = "/api";

    /**
     * V1 : root 를 제외한 실제 path
     */
    public static interface Admin {

        public static final String PATH = ROOT + "/admin";

        public static interface home {
            public static final String HOME = Admin.PATH + "/home";
        }

        public static interface deal {
            public static final String DEAL = Admin.PATH + "/login";
        }

        public static interface complete {
            public static final String COMPLETE = Admin.PATH + "/complete";
        }

        public static interface seeUser {
            public static final String ALLUSER = Admin.PATH + "/allUser";
        }
        public static interface transaction {
            public static final String TRANSACTION = Admin.PATH + "/transaction";
        }



    }
}
