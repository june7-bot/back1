package zen.zen.uri;

public interface UserPaths {

    /**
     * root : zuul server에서 routing 시 사용되는 prefix.
     */
    public static final String ROOT = "/api";

    /**
     * V1 : root 를 제외한 실제 path
     */
    public static interface User {

        public static final String PATH = ROOT + "/user";

        public static interface register {
            public static final String REGISTER = User.PATH + "/register";
        }

        public static interface login {
            public static final String LOGIN = User.PATH + "/login";
        }

        public static interface auth {
            public static final String AUTH = User.PATH + "/auth";
        }
        //로그아웃 find id find password change password

        public static interface logout {
            public static final String LOGOUT = User.PATH + "/logout";
        }
        public static interface findId {
            public static final String FINDID = User.PATH + "/findId";
        }
        public static interface findPassword {
            public static final String FINDPW = User.PATH + "/findpw";
        }
        public static interface changePassword {
            public static final String CHANGEPW = User.PATH + "/changepassword";
        }



    }
}
