import auth.AuthenticationHelper;

public class Tests {

    public static void main(String[] args) throws Exception {
//        AuthenticationHelper.addUserWithPassword(1, "Alice", "password");
//        System.out.println(AuthenticationHelper.authenticate("alice", "password"));


        for(String pswrd : new String[] { "liam", "guy", "imran", "tobi" }) {
            String[] data = AuthenticationHelper.generateHash(pswrd + "pass");
            System.out.println(data[0]);
            System.out.println(data[1]);
            System.out.println();
        }
    }
}
