package zen.zen.uri;

public interface MyPagePaths {

    /**
     * root : zuul server에서 routing 시 사용되는 prefix.
     */
    public static final String ROOT = "/api";

    /**
     * V1 : root 를 제외한 실제 path
     */
    public static interface MyPage {

        public static final String PATH = ROOT + "/mypage";

        public static interface myPage {
            public static final String MYPAGE = MyPage.PATH;
        }


        public static interface userInfoChange {
            public static final String USERINFOCHANGE = MyPage.PATH + "/userInfoChange";
        }

        public static interface transactionProceed {
            public static final String TRANSACTIONPROCEED = MyPage.PATH + "/proceed";
        }

        public static interface adopt {
            public static final String ADOPT = MyPage.PATH + "/myadopt";
        }

        public static interface parcel {
            public static final String PARCEL = MyPage.PATH + "/myparcel";
        }

        public static interface cancel {
            public static final String CANCEL = MyPage.PATH + "/cancel";
        }

        public static interface cancelbyseller {
            public static final String CANCELBYSELLER = MyPage.PATH + "/cancelbyseller";
        }

        public static interface completetransaction {
            public static final String COMPLETETRANSACTION = MyPage.PATH + "/completetransaction";
        }

    }
}
