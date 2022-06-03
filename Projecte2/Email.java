package Projecte2;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
	private String email;

	public Email(String email) {
		this.email = email;
	}

	public Email() {
	}

	public Boolean validaEmail(String email) {
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) {
		if (validaEmail(email)) {
			this.email = email;
			return true;
		}
		return false;
	}

	public void setEmail() {
		
	}

}