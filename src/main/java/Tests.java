import auth.AuthenticationHelper;

public class Tests {

    public static void main(String[] args) throws Exception {
        System.out.println(AuthenticationHelper.authenticate("tobichammings", "tobipass").getState());
    }
}
