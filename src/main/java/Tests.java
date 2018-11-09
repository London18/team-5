import mail.MailHandler;

public class Tests {

    public static void main(String[] args) {
        System.out.println(DatabaseHelper.instance().getConnection());
		MailHandler mailHandler = new MailHandler();
		if(mailHandler.sendEmail("liampugh@limelight.tech","Test Subject","Test Body") == 0){
			System.out.println("Email test successful");
		}
    }
}
